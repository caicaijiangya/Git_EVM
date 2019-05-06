package com.bluekjg.wxapp.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxCouponMapper;
import com.bluekjg.wxapp.mapper.WxIntegralMapper;
import com.bluekjg.wxapp.mapper.WxOrderMapper;
import com.bluekjg.wxapp.mapper.WxUserMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.PayDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxCoupon;
import com.bluekjg.wxapp.model.WxIntegralGoods;
import com.bluekjg.wxapp.model.WxIntegralLog;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderAddress;
import com.bluekjg.wxapp.model.WxOrderDetail;
import com.bluekjg.wxapp.model.WxOrderTrans;
import com.bluekjg.wxapp.service.IWxCouponService;
import com.bluekjg.wxapp.service.IWxIntegralService;
import com.bluekjg.wxapp.service.IWxPayService;
import com.bluekjg.wxapp.service.IWxUserService;

/**
 * @description：商品数据
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
@Transactional
public class WxIntegralServiceImpl extends ServiceImpl<WxIntegralMapper, WxIntegralGoods>implements IWxIntegralService {

	protected Logger logger = LoggerFactory.getLogger(WxIntegralServiceImpl.class);

	@Autowired
	private WxIntegralMapper integralMapper;
	@Autowired
	private WxOrderMapper orderMapper;
	@Autowired
	private IWxCouponService couponService;
	@Autowired
	private IWxUserService userService;
	@Autowired
	private IWxPayService payService;
	
	@Override
	public WxIntegralGoods queryGoodsDetail(PagingDto page) {
		// TODO Auto-generated method stub
		return integralMapper.queryGoodsDetail(page);
	}
	@Override
	public WxCoupon queryCouponDetail(PagingDto page) {
		// TODO Auto-generated method stub
		return integralMapper.queryCouponDetail(page);
	}

	@Override
	public WxIntegralGoods queryGoodsAmount(String openId,String goodsId, String type) {
		// TODO Auto-generated method stub
		return integralMapper.queryGoodsAmount(openId,goodsId, type);
	}
	@Override
	public Integer queryUserIntegral(String openId) {
		// TODO Auto-generated method stub
		Integer integral = integralMapper.queryUserIntegral(openId);
		if(integral == null) {
			integral = 0;
		}
		return integral;
	}

	@Override
	@Transactional
	public void insertOrder(WxOrder order, WxOrderDetail detail,WxOrderAddress address) {
		if(address.getFreight() == null) {address.setFreight(0.0);}
		orderMapper.insertOrder(order);
		address.setOrderId(order.getId());
		orderMapper.insertOrderAddress(address);
		//orderMapper.updateIntegralGoodsAmount(detail.getGoodsId(), -1);
		Double integral = order.getTotalBalances() * -1;
		integralMapper.updateIntegralGoodsAmount(detail.getSpecId(),0, -1);
		detail.setOrderId(order.getId());
		orderMapper.insertOrderDetail(detail);
		//订单支付
		WxOrderTrans trans = new WxOrderTrans();
		trans.setOpenId(order.getOpenId());
		trans.setOrderId(order.getId());
		trans.setTransNo(order.getOrderNo()+ (int)((Math.random()*9+1)*1000));
		trans.setRefundNo(order.getOrderNo()+ (int)((Math.random()*9+1)*1000));
		trans.setBalances(BigDecimal.valueOf(address.getFreight()));
		trans.setTransName("积分兑换商品产生记录");
		trans.setRefundName("积分兑换商品退款产生记录");
		trans.setStatus(0);
		//添加支付记录
		orderMapper.insertOrderTrans(trans);
		orderMapper.updateOrderTransStatus(trans);
		userService.insertIntegralLog(order.getOpenId(),null,1,integral.intValue());
		
		//支付金额为0、到店付款    不跳转支付页面
		//免除支付
		if(trans.getBalances().doubleValue() == 0.0) {
			order.setId(0);
			PayDto payDto = new PayDto();
			payDto.setOutTradeNo(trans.getTransNo());
			payService.paySuccesssNotice(payDto);
		}
	}
	@Override
	public void addCoupon(WxCoupon coupon) {
		couponService.insertUserCoupon(coupon.getOpenId(), coupon.getId(), 7, 0);
		Double integral = coupon.getGoodsPrice() * -1;
		integralMapper.updateIntegralGoodsAmount(coupon.getId(),1, -1);
		userService.insertIntegralLog(coupon.getOpenId(),null,1,integral.intValue());
	}
	@Override
	public List<WxCoupon> queryCouponList(PagingDto page) {
		// TODO Auto-generated method stub
		return integralMapper.queryCouponList(page);
	}
	@Override
	public List<WxIntegralGoods> queryGoodsList(PagingDto page) {
		// TODO Auto-generated method stub
		return integralMapper.queryGoodsList(page);
	}
	
}