package com.bluekjg.wxapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.AccessLog;
import com.bluekjg.wxapp.service.IWxAccessLogService;

@Controller
@RequestMapping("/xcx/accessLog")
public class WxAccessLogController extends BaseController{
	@Autowired
	private IWxAccessLogService accessLogService;
	/**
     * 商品访问日志
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addGoodsLog")
    @ResponseBody
    public Object addGoodsLog(@Valid AccessLog log,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	accessLogService.insertGoodsAccessLog(log);
        	obj = renderSuccess("ok");
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 活动访问日志
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addActivityLog")
    @ResponseBody
    public Object addActivityLog(@Valid AccessLog log,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	int newOld = accessLogService.queryUserNewOld(log.getOpenId());
        	if(newOld > 0) {
        		newOld = 1;
        	}
        	log.setNewOld(newOld);
        	accessLogService.insertActivityAccessLog(log);
        	obj = renderSuccess("ok");
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
}
