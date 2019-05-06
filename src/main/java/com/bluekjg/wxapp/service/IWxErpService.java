package com.bluekjg.wxapp.service;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.WxErpOrder;


/**
 * @description：ERP接口服务
 * @author：pincui.Tom
 * @date：2018/10/19 14:51
 */
public interface IWxErpService extends IService<WxErpOrder> {
	/**
	 * 推送订单信息给ERP
	 * @param id
	 */
	void pushOrderErp(Integer id);
	/**
	 * 推送会员信息给ERP
	 * @param id
	 */
	void pushVipErp(String openId);
	/**
	 * 发货单查询
	 * @param orderNo
	 */
	void pushDeliveryErp(String orderNo);
	/**
	 * 修改订单退款状态
	 * @param orderNo
	 * @param status 0:未退款 1:退款完成 2:退款中
	 */
	void pushOrderRefundErp(String orderNo,Integer status);
	/**
	 * 推送订单退货单给ERP
	 * @param id
	 */
	void pushOrderReturnErp(Integer id);
}