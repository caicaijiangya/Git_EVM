package com.bluekjg.wxapp.service;

import java.util.List;

import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxKeeperOrder;
import com.bluekjg.wxapp.model.wx.DataModel;

public interface IWxKeeperOrderService {
	//查询订单列表
	List<WxKeeperOrder> queryOrderList(PagingDto page);
	//查询订单量,订单总额,未取货
	String queryOrderCount(WxKeeperOrder wxOrder);
	//查询核销订单
	List<WxKeeperOrder> queryRecodeList(PagingDto page);
	//获取订单详情
	WxKeeperOrder selectOrderDetail(PagingDto page);
	//确认收款
	void confirmReceipt(DataModel dataModel);

}
