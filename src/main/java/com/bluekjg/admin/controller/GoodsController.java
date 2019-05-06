package com.bluekjg.admin.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.service.IGoodsService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.ConvertBean;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.wxapp.utils.CommonUtil;
import com.bluekjg.wxapp.utils.PostObjectSample;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

/**
 * <p>
 * 商品管理 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2018-09-25
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @Autowired private IGoodsService goodsService;
    
    @GetMapping("/manager")
    public String manager(Model model) {
    	model.addAttribute("classifys", goodsService.selectClassifyList());
    	model.addAttribute("brands", goodsService.selectBrandList());
        return "admin/goods/list";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Goods g, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	goodsService.selectDataGrid(pageInfo,g);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Model model) {
    	model.addAttribute("brands", goodsService.selectBrandList());
        return "admin/goods/add";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Goods g,HttpServletRequest request) {
    	Object obj = renderError("操作失败");
    	try {
    		g.setUserId(1);
    		g.setStatus(0);
    		if(g.getGoodsAmount() == null) {g.setGoodsAmount(0);}
    		g.setGoodsRemAmount(g.getGoodsAmount());
    		g.setGoodsTotalAmount(g.getGoodsAmount());
    		if(g.getGoodsName() != null) {
    			g.setGoodsName(request.getParameter("goodsName"));
    		}
    		goodsService.insertGoods(g);
    		obj = renderSuccess("添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    /**
     * 编辑页面
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model,Integer id) {
    	model.addAttribute("brands", goodsService.selectBrandList());
    	model.addAttribute("goods", goodsService.selectGoodsObj(id));
        return "admin/goods/edit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Goods g,HttpServletRequest request) {
    	Object obj = renderError("操作失败");
    	try {
    		if(g.getAddAmount() == null) {
    			g.setAddAmount(0);
    		}
    		if(g.getGoodsName() != null) {
    			g.setGoodsName(request.getParameter("goodsName"));
    		}
    		goodsService.updateGoods(g);
    		obj = renderSuccess("修改成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 删除
     * @param 
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(@Valid Goods g) {
    	Object obj = renderError("操作失败");
    	try {
    		goodsService.deleteGoods(g);
    		obj = renderSuccess("操作成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    /**
     * 商品二维码
     * @param 
     * @return
     */
    @PostMapping("/qrCode")
    @ResponseBody
    public Object qrCode(@Valid Goods g) {
    	Object obj = renderError("操作失败");
    	try {
    		String requestUrl = WxappConfigUtil.WX_TOKEN_URL.replace("APPID", WxappConfigUtil.WX_APPID).
                    replace("SECRET", WxappConfigUtil.WX_SECRET);
            // 发起GET请求获取凭证
    		Map<String,String>  jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
            if (jsonObject != null && jsonObject.get("access_token") != null) {
            	String accessToken = jsonObject.get("access_token");
           		if(accessToken != null) {
           			String qrcodeUrl = WxappConfigUtil.WX_QRCODE_URL.replace("ACCESS_TOKEN", accessToken);
           			Map<String, Object> param = new HashMap<String, Object>();
           			param.put("page", "page/customer/pages/goods/detail/detail");
        			param.put("width", 430);
        			param.put("scene", g.getId());
        			param.put("auto_color", false);
        			Map<String, Object> line_color = new HashMap<>();
        			line_color.put("r", 255);
        			line_color.put("g", 255);
        			line_color.put("b", 255);
        			param.put("line_color", line_color);
        			logger.info("调用生成微信URL接口传参:" + param);
        			InputStream inputStream = CommonUtil.httpsPostParame(qrcodeUrl, param);
        			if(inputStream != null) {
    					// 生成新文件名
    					String newName = UUID.randomUUID().toString().replaceAll("-", "");
    					newName = newName + ".png";
    					// 文件上传
    					String imagePath = DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD);
    					// 文件上传
    					Integer result = PostObjectSample.PostObject(imagePath + "/" + newName, "image/png",inputStream);
    					// 返回结果
    					if (result > 0) {
    						String qrCode = WxappConfigUtil.ALICLOUD_IMAGE_BASE_URL + imagePath + "/" + newName;
    						g.setQrCode(qrCode);
    						goodsService.updateGoodsQrCode(g);
    						obj = renderSuccess("ok");
    					}
        			}
           		}
            }
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    /**
     * 导出商品
     * @param 
     * @return
     */
    @RequestMapping("/downLoadGoods")
    @ResponseBody
    public Object downLoadGoods(@Valid Goods g, HttpServletResponse response) {
    	g.setGoodsName(ConvertBean.twoTimesDecode(g.getGoodsName()));
    	goodsService.downLoadOrder(g, response);
        return renderSuccess("导出成功");
    }
    
    
    
    
    /**
     * 商品规格列表页
     * @param model
     * @return
     */
    @GetMapping("/specManager")
    public String specManager(Model model,Integer id) {
    	model.addAttribute("id", id);
        return "admin/goods/specList";
    }
    
    @PostMapping("/dataSpecGrid")
    @ResponseBody
    public PageInfo dataSpecGrid(Goods g, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	goodsService.selectSpecDataGrid(pageInfo,g);
        return pageInfo;
    }
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addSpecPage")
    public String addSpecPage(Model model,Integer id) {
    	model.addAttribute("id", id);
        return "admin/goods/specAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/addSpec")
    @ResponseBody
    public Object addSpec(@Valid Goods g,HttpServletRequest request) {
    	Object obj = renderError("操作失败");
    	try {
    		if(g.getGoodsAmount() == null) {g.setGoodsAmount(0);}
    		g.setGoodsRemAmount(g.getGoodsAmount());
    		g.setGoodsTotalAmount(g.getGoodsAmount());
    		if(g.getSpecName() != null) {
    			g.setSpecName(request.getParameter("specName"));
    		}
    		goodsService.insertGoodsSpec(g);
    		obj = renderSuccess("添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 编辑页面
     * @return
     */
    @GetMapping("/editSpecPage")
    public String editSpecPage(Model model,Integer id) {
    	model.addAttribute("goods", goodsService.selectGoodsSpecObj(id));
        return "admin/goods/specEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/editSpec")
    @ResponseBody
    public Object editSpec(@Valid Goods g,HttpServletRequest request) {
    	Object obj = renderError("操作失败");
    	try {
    		if(g.getAddAmount() == null) {
    			g.setAddAmount(0);
    		}
    		if(g.getSpecName() != null) {
    			g.setSpecName(request.getParameter("specName"));
    		}
    		goodsService.updateGoodsSpec(g);
    		obj = renderSuccess("修改成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 删除
     * @param 
     * @return
     */
    @PostMapping("/deleteSpec")
    @ResponseBody
    public Object deleteSpec(@Valid Goods g) {
    	Object obj = renderError("操作失败");
    	try {
    		goodsService.updateGoodsSpec(g);
    		obj = renderSuccess("操作成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    @PostMapping("/selectGoodsInfo")
    @ResponseBody
    public PageInfo selectGoodsInfo(Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	goodsService.selectGoodsInfo(pageInfo);
        return pageInfo;
    }
}
