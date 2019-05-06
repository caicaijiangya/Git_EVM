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
import com.bluekjg.wxapp.model.WxKeeperMessage;
import com.bluekjg.wxapp.model.wx.DataModel;
import com.bluekjg.wxapp.service.IWxKeeperMessageService;

/**
 * @description：店主、店长、店员消息通知
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
@Controller
@RequestMapping("/xcx/message")
public class WxKeeperMessageController extends BaseController {
	@Autowired
	private IWxKeeperMessageService messageService;
	
	/**
     * 查询个人所有消息--店主店员
     *
     * @param resource
     * @return
     */
    @RequestMapping("/messageList")
    @ResponseBody
    public Object messageList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxKeeperMessage> list = messageService.queryMessage(page);
        	obj = renderSuccess(list);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
	
    /**
     * 查询个人所有消息--消费者
     *
     * @param resource
     * @return
     */
    @RequestMapping("/customerMessageList")
    @ResponseBody
    public Object customerMessageList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxKeeperMessage> list = messageService.queryCustomerMessage(page);
        	obj = renderSuccess(list);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 查询退款详情
     *
     * @param resource
     * @return
     */
    @RequestMapping("/refundDetail")
    @ResponseBody
    public Object refundDetail(@Valid DataModel dataModel,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxKeeperMessage message = messageService.queryRefundDetail(dataModel);
        	obj = renderSuccess(message);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    //修改是否已读状态
    @RequestMapping("/editIsRead")
    @ResponseBody
    public Object editIsRead(@Valid WxKeeperMessage wxMessage,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Integer b = messageService.editIsRead(wxMessage);
        	obj = renderSuccess(b);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
}

