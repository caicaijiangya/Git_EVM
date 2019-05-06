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
import com.bluekjg.admin.model.CouponActivity;
import com.bluekjg.admin.service.ICouponActivityService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.wxapp.utils.CommonUtil;
import com.bluekjg.wxapp.utils.PostObjectSample;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

/**
 * <p>
 * 优惠券活动 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2019-02-26
 */
@Controller
@RequestMapping("/couponActivity")
public class CouponActivityController extends BaseController {

    @Autowired 
    private ICouponActivityService couponActivityService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/couponActivity/couponActivityList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(CouponActivity couponActivity, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	couponActivityService.selectDataGrid(pageInfo,couponActivity);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/couponActivity/couponActivityAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid CouponActivity couponActivity,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("添加失败！");
    	try {
    		couponActivity.setStatus(1);
    		couponActivityService.insert(couponActivity);
        	obj = renderSuccess("添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Integer id) {
    	CouponActivity couponActivity = couponActivityService.selectCouponActivityById(id);
        model.addAttribute("couponActivity", couponActivity);
        return "admin/couponActivity/couponActivityEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid CouponActivity couponActivity,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("编辑失败！");
    	try {
    		couponActivityService.updateCouponActivity(couponActivity);
        	obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
    	Object obj = renderError("删除失败！");
        try {
        	CouponActivity couponActivity = new CouponActivity();
        	couponActivity.setId(id);
        	couponActivity.setIsDel(1);
            couponActivityService.updateById(couponActivity);
            obj =  renderSuccess("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 修改状态
     * @param id
     * @return
     */
    @PostMapping("/editStatus")
    @ResponseBody
    public Object editStatus(Integer id,Integer status) {
    	Object obj = renderError("操作失败");
        try {
        	CouponActivity couponActivity = new CouponActivity();
        	couponActivity.setId(id);
        	couponActivity.setLastModifiedTime(new Date());
        	couponActivity.setStatus(status);
            couponActivityService.updateById(couponActivity);
            obj = renderSuccess("操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 优惠券活动链接
     * @param 
     * @return
     */
    @PostMapping("/likeUrl")
    @ResponseBody
    public Object likeUrl(@Valid Integer id,Integer couponId,Integer type) {
    	Object obj = renderError("操作失败");
    	try {
    		String likeUrl = null;
    		String page = "page/callback/coupon";
    		if(type == 0) {
    			/*
    			 * 生成二维码
    			 */
    			String requestUrl = WxappConfigUtil.WX_TOKEN_URL.replace("APPID", WxappConfigUtil.WX_APPID).
                        replace("SECRET", WxappConfigUtil.WX_SECRET);
                // 发起GET请求获取凭证
        		Map<String,String>  jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
                if (jsonObject != null && jsonObject.get("access_token") != null) {
                	String accessToken = jsonObject.get("access_token");
               		if(accessToken != null) {
               			String qrcodeUrl = WxappConfigUtil.WX_QRCODE_URL.replace("ACCESS_TOKEN", accessToken);
               			Map<String, Object> param = new HashMap<String, Object>();
               			param.put("page", page);
            			param.put("width", 430);
            			param.put("scene", id+","+couponId+","+type);
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
        						likeUrl = WxappConfigUtil.ALICLOUD_IMAGE_BASE_URL + imagePath + "/" + newName;
        					}
            			}
               		}
                }
    		}else if(type == 1) {
    			/*
    			 * 生成链接
    			 */
    			likeUrl = "/"+page+"?scene="+id+","+couponId+","+type;
    		}
    		if(likeUrl != null) {
    			couponActivityService.updateCouponActivityLikeUrl(String.valueOf(id), likeUrl);
    			obj = renderSuccess("ok");
    		}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
}
