package com.bluekjg.wxapp.service;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxStore;

/**
 * @description：卖家信息
 * @author：pincui.Tom
 * @date：2018/7/27 14:51
 */
public interface IWxKeeperService extends IService<WxStore> {

	/**
	 * 查询取货量
	 * @param storeId 门店id
	 * @param status （状态=null 查询所有订单）
	 * @return
	 */
	Integer queryOrderDetailCount(PagingDto page);
	/**
	 * 查询订单总金额
	 * @param page
	 * @return
	 */
	Double queryOrderDetailPrice(PagingDto page);
	//查询订单量
	Integer queryOrderCount(PagingDto page);
	Double queryDiscountPrice(PagingDto page);
	
}