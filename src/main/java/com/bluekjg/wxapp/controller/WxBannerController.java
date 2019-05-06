package com.bluekjg.wxapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxBanner;
import com.bluekjg.wxapp.service.IWxBannerService;

@Controller
@RequestMapping("/xcx/banner")
public class WxBannerController extends BaseController{
	@Autowired
	private IWxBannerService bannerService;
	/**
     * 查询BANNER列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/obj")
    @ResponseBody
    public Object obj(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	List<WxBanner> banner = bannerService.queryBannerList(page.getType());
        	if(banner != null && banner.size() > 0) {
        		obj = renderSuccess(banner.get(0));
        	}else {
        		obj = renderSuccess(null);
        	}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
	/**
     * 查询BANNER列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	List<WxBanner> banner = bannerService.queryBannerList(page.getType());
    		obj = renderSuccess(banner);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
}
