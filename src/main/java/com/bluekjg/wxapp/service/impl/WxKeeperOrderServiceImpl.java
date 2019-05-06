package com.bluekjg.wxapp.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluekjg.wxapp.mapper.WxKeeperOrderMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxKeeperOrder;
import com.bluekjg.wxapp.model.wx.DataModel;
import com.bluekjg.wxapp.service.IWxKeeperOrderService;

import net.sf.json.util.JSONStringer;

@Service
public class WxKeeperOrderServiceImpl implements IWxKeeperOrderService {

	@Autowired
	private WxKeeperOrderMapper mapper;
	
	//查询所有订单
	public List<WxKeeperOrder> queryOrderList(PagingDto page) {
		return mapper.queryOrderList(page);
	}

	//查询到店取货的订单:销售额 , 订单数 , 未取货商品数
	public String queryOrderCount(WxKeeperOrder wxOrder) {
		Double totalMoney = mapper.queryTotalMoney(wxOrder);//总金额
		Double youhui= mapper.queryYouhuiMoney(wxOrder);//总金额
		Integer totalOrder = mapper.queryOrderNum(wxOrder);//总订单数
		Integer totalWth = mapper.queryNotPickOrderCount(wxOrder);//未取货
		if(totalMoney == null) {totalMoney = 0.0;}
		JSONStringer json = new JSONStringer();
		json.object();
		json.key("totalMoney").value(totalMoney);
		json.key("totalOrder").value(totalOrder);
		json.key("totalWth").value(totalWth);
		json.key("youhui").value(youhui);
		json.endObject();
		
		return json.toString();
	}
	
	//查询已核销订单
	@Override
	public List<WxKeeperOrder> queryRecodeList(PagingDto page) {
		return mapper.queryRecodeList(page);
	}

	@Override
	public WxKeeperOrder selectOrderDetail(PagingDto page) {
		return mapper.selectOrderDetail(page);
	}

	@Override
	public void confirmReceipt(DataModel dataModel) {
		// TODO Auto-generated method stub
		mapper.confirmReceipt(dataModel);
		mapper.confirmReceiptTrans(dataModel);
	}

}
