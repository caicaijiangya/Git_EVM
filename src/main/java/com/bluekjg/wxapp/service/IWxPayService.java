package com.bluekjg.wxapp.service;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PayDto;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderRefund;
import com.bluekjg.wxapp.model.WxOrderTrans;


/**
 * @description：支付信息
 * @author：pincui.Tom
 * @date：2018/9/27 14:51
 */
public interface IWxPayService extends IService<WxOrder> {
	/**
	 * 支付成功后处理
	 * @param payDto
	 */
	void paySuccesssNotice(PayDto payDto);
	/**
	 * 退款成功后处理
	 * @param payDto
	 */
	void refundSuccesssNotice(PayDto payDto);
	
	/**
	 * 退款操作
	 * @param trans
	 * @return
	 */
	Integer wxrefund(HttpServletRequest request,WxOrderRefund refund);
}