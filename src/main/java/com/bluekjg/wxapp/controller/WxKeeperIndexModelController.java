package com.bluekjg.wxapp.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.WxModelUser;
import com.bluekjg.wxapp.service.IWxKeeperIndexModelService;

/**
 * @description：店主、店长、店员权限模块
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
@Controller
@RequestMapping("/xcx/indexModel")
public class WxKeeperIndexModelController extends BaseController {
	@Autowired
	private IWxKeeperIndexModelService indexModelService;
	
	//添加员工权限
	@RequestMapping("/addUserAuth")
	@ResponseBody
	public Object addUserAuth(@Valid WxModelUser modelUser,HttpServletRequest request,HttpServletResponse response) {
	    Object obj = renderError("The request failed");
	    try {
	    	Integer b = indexModelService.addUserAuth(modelUser);
	        obj = renderSuccess(b);
	    }catch(Exception e) {
	    	logger.error(e.getMessage(),e);
		}
	    return obj;
	    }
	//取消店员权限
	@RequestMapping("/delUserAuth")
	@ResponseBody
	public Object delUserAuth(@Valid WxModelUser modelUser,HttpServletRequest request,HttpServletResponse response) {
	    Object obj = renderError("The request failed");
	    try {
	    	Integer b = indexModelService.delUserAuth(modelUser);
	        obj = renderSuccess(b);
	    }catch(Exception e) {
	    	logger.error(e.getMessage(),e);
		}
	    return obj;
	    }
}

