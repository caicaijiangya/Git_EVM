package com.bluekjg.wxapp.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxCoupon;
import com.bluekjg.wxapp.model.WxIntegralGoods;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderAddress;
import com.bluekjg.wxapp.model.WxOrderDetail;


/**
 * @description：积分商品信息
 * @author：pincui.Tom
 * @date：2018/9/27 14:51
 */
public interface IWxIntegralService extends IService<WxIntegralGoods> {
	/**
	 * 查询优惠券列表
	 * @param page
	 * @return
	 */
	List<WxCoupon> queryCouponList(PagingDto page);
	/**
	 * 查询商品列表
	 * @param page
	 * @return
	 */
	List<WxIntegralGoods> queryGoodsList(PagingDto page);
	/**
	 * 查询商品详情
	 * @param page
	 * @return
	 */
	WxIntegralGoods queryGoodsDetail(PagingDto page);
	/**
	 * 查询优惠券详情
	 * @param page
	 * @return
	 */
	WxCoupon queryCouponDetail(PagingDto page);
	/**
	 * 查询积分商品库存
	 * @param goodsId
	 * @param type
	 * @return
	 */
	WxIntegralGoods queryGoodsAmount(String openId,String goodsId,String type);
	/**
	 * 查询余额
	 * @param openId
	 * @return
	 */
	Integer queryUserIntegral(String openId);
	/**
	 * 新增订单
	 * @param order
	 * @param detail
	 */
	void insertOrder(WxOrder order,WxOrderDetail detail,WxOrderAddress address);
	/**
	 * 兑换优惠券
	 * @param coupon
	 */
	void addCoupon(WxCoupon coupon);
}