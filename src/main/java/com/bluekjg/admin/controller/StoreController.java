package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.Area;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.Store;
import com.bluekjg.admin.service.IAreaService;
import com.bluekjg.admin.service.IStoreService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.wxapp.utils.CommonUtil;
import com.bluekjg.wxapp.utils.PostObjectSample;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

/**
 * <p>
 * 门店信息表 前端控制器
 * </p>
 *
 * @author Tim
 * @since 2018-09-30
 */
@Controller
@RequestMapping("/store")
public class StoreController extends BaseController {

    @Autowired private IStoreService storeService;
    @Autowired private IAreaService	 areaService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/store/storeList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Store store, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        storeService.selectDataGrid(pageInfo,store);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(HttpServletRequest request,HttpServletResponse response) {
    	List<Area> provinceList = areaService.queryProvinceList();
    	request.setAttribute("provinceList", provinceList);
        return "admin/store/storeAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Store store) {
        store.setLastModifiedTime(new Date());
        Integer b = storeService.insertStore(store);
        if (b>0) {
            return renderSuccess("添加成功！");
        } else {
            return renderError("添加失败！");
        }
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
        Store store = new Store();
        store.setId(id);
        store.setLastModifiedTime(new Date());
        store.setIsDel(1);
        Integer b = storeService.updateStoreById(store);
        if (b>0) {
            return renderSuccess("删除成功！");
        } else {
            return renderError("删除失败！");
        }
    }
    
    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Integer id,Integer provinceId,
    		Integer cityId,HttpServletRequest request,HttpServletResponse response) {
    	List<Area> provinceList = areaService.queryProvinceList();
    	List<Area> cityList = areaService.queryCityList(provinceId);
    	List<Area> areaList = areaService.queryAreaList(cityId);
    	request.setAttribute("provinceList", provinceList);
    	request.setAttribute("cityList", cityList);
    	request.setAttribute("areaList", areaList);
        Store store = storeService.selectStoreById(id);
        model.addAttribute("store", store);
        return "admin/store/storeEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Store store) {
        store.setLastModifiedTime(new Date());
        Integer b = storeService.updateInfoById(store);
        if (b>0) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    //查询市
    @PostMapping("/queryCityList")
    @ResponseBody
    public List<Area> queryCityList(Integer provinceId){
    	return areaService.queryCityList(provinceId);
    }
    
  //查询区
    @PostMapping("/queryAreaList")
    @ResponseBody
    public List<Area> queryAreaList(Integer cityId){
    	return areaService.queryAreaList(cityId);
    }
    
    
    
    /**
     * 门店二维码
     * @param 
     * @return
     */
    @PostMapping("/qrCode")
    @ResponseBody
    public Object qrCode(@Valid Store s) {
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
           			param.put("page", "page/index/index");
        			param.put("width", 430);
        			param.put("scene", s.getId()+",1");
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
    						s.setQrCode(qrCode);
    						storeService.updateStoreQrCode(s);
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
    
    
    @PostMapping("/storeDataGrid")
    @ResponseBody
    public PageInfo storeDataGrid(Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	storeService.queryStoreDataGrid(pageInfo);
        return pageInfo;
    }
}
