package com.bluekjg.wxapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.service.IWxCollageService;
import com.bluekjg.wxapp.service.IWxGoodsService;

@Controller
@RequestMapping("/xcx/activity")
public class WxActivityController extends BaseController{
	@Autowired
	private IWxCollageService collageService;
	@Autowired
	private IWxGoodsService goodsService;
	/**
     * 查询拼团详情
     *
     * @param resource
     * @return
     */
    @RequestMapping("/collage")
    @ResponseBody
    public Object list(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxCollageGoods collage = collageService.queryCollageObj(page);
        	if(collage != null) {
        		collage.setTime(goodsService.activityDate(collage.getStartTime(), collage.getEndTime()));
        	}
        	obj = renderSuccess(collage);
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
	
}
