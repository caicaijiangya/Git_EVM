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
import com.bluekjg.wxapp.model.WxBrandStory;
import com.bluekjg.wxapp.service.IWxBrandService;

@Controller
@RequestMapping("/xcx/brand")
public class WxBrandController extends BaseController{
	@Autowired
	private IWxBrandService brandService;
	
	
	/**
     * 查询品牌故事列表
     * @param resource
     * @return
     */
    @RequestMapping("/queryBrandList")
    @ResponseBody
    public Object queryMyOrder(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxBrandStory> list = brandService.queryBrandList(page);
        	obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询品牌故事详情
     *
     * @param resource
     * @return
     */
    @RequestMapping("/brandZan")
    @ResponseBody
    public Object brandZan(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Integer result = brandService.brandZan(page);
        	obj = renderSuccess(result);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 点赞操作
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryBrandDetail")
    @ResponseBody
    public Object queryOrderDetail(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxBrandStory brandStory = brandService.queryBrandDetail(page);
        	obj = renderSuccess(brandStory);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
}
