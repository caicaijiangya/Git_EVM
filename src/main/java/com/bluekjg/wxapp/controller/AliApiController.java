package com.bluekjg.wxapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.service.IWxOrderService;
import com.bluekjg.wxapp.utils.AliApiUtil;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/xcx/ali")
public class AliApiController extends BaseController{
	@Autowired
	private IWxOrderService orderService;
	/**
     * 查询物流信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/kdi")
    @ResponseBody
    public Object list(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	//Map<String,String> express = orderService.queryOrderExpressMap(page);
        	String no = orderService.queryOrderExpressNo(page);
        	if(no != null && no.length() > 4) {
        		JSONObject json = AliApiUtil.kdi(no);
            	if(json != null && json.get("status") != null && json.get("status").toString().equals("0")) {
            		obj = renderSuccess(json.get("result"));
            	}
        	}
        	//obj = renderSuccess(express);
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
	
}
