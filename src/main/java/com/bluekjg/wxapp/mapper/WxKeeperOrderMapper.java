package com.bluekjg.wxapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxKeeperOrder;
import com.bluekjg.wxapp.model.wx.DataModel;

public interface WxKeeperOrderMapper extends BaseMapper<WxKeeperOrder> {
	/**
	 * 查询订单状态
	 * @param id
	 * @return
	 */
	Integer queryOrderStatus(@Param("id") Integer id);
	//查询订单列表
	List<WxKeeperOrder> queryOrderList(PagingDto page);
	
	//到店取货订单销售总额
	Double queryTotalMoney(WxKeeperOrder wxOrder);
	//到底取货订单中未取货的商品总数
	Integer queryNotPickOrderCount(WxKeeperOrder wxOrder);
	//到店取货订单数
	Integer queryOrderNum(WxKeeperOrder wxOrder);
	
	//查询已核销订单
	List<WxKeeperOrder> queryRecodeList(PagingDto page);

	//查询订单核销码
	WxKeeperOrder queryOrderCodeDetail(DataModel dataModel);

	//核销订单
	Integer writeOffOrderInfo(DataModel dataModel);

	//获取订单详情
	WxKeeperOrder selectOrderDetail(PagingDto page);
	Double queryYouhuiMoney(WxKeeperOrder wxOrder);
	//确认收款
	Integer confirmReceipt(DataModel dataModel);
	Integer confirmReceiptTrans(DataModel dataModel);
}
