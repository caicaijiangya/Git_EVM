package com.bluekjg.wxapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.WxAccessFlow;
import com.bluekjg.wxapp.service.IWxAccessFlowService;

@Controller
@RequestMapping("/xcx/accessFlow")
public class WxAccessFlowController extends BaseController{
	@Autowired
	private IWxAccessFlowService accessFlowService;
	/**
     * 访问流量
     *
     * @param resource
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object queryBuyOrderNum(@Valid WxAccessFlow flow,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	//门店二维码
        	if(flow.getType() == 1) {
        		accessFlowService.updateUserStore(flow);
        	}
        	accessFlowService.addAccessFlow(flow);
        	obj = renderSuccess("ok");
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
	
}
