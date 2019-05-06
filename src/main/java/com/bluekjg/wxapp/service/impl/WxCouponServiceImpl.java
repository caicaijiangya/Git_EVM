package com.bluekjg.wxapp.service.impl;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.core.commons.utils.WebUtils;
import com.bluekjg.wxapp.mapper.WxCouponMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxCoupon;
import com.bluekjg.wxapp.service.IWxCouponService;

/**
 * @description：优惠券数据
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
public class WxCouponServiceImpl extends ServiceImpl<WxCouponMapper, WxCoupon>implements IWxCouponService {

	protected Logger logger = LoggerFactory.getLogger(WxCouponServiceImpl.class);

	@Autowired
	private WxCouponMapper couponMapper;

	@Override
	public List<WxCoupon> queryCouponMy(PagingDto dto) {
		// TODO Auto-generated method stub
		return couponMapper.queryCouponMy(dto);
	}

	@Override
	public List<WxCoupon> queryCouponList(PagingDto dto) {
		// TODO Auto-generated method stub
		return couponMapper.queryCouponList(dto);
	}

	@Override
	public void insertUserCoupon(String openId,Integer couponId,Integer sourceType,Integer sourceId) {
		WxCoupon bean = new WxCoupon();
    	bean.setOpenId(openId);
    	bean.setId(couponId);
    	bean.setSourceType(sourceType);
    	bean.setSourceId(sourceId);
    	bean.setStatus(0);
    	bean.setCouponNo(WebUtils.getItemID(14));
		couponMapper.insertUserCoupon(bean);
	}

	@Override
	public List<WxCoupon> queryOrderCoupon(PagingDto dto) {
		// TODO Auto-generated method stub
		return couponMapper.queryOrderCoupon(dto);
	}
	
	@Override
	public WxCoupon queryCouponById(Integer id) {
		// TODO Auto-generated method stub
		return couponMapper.queryCouponById(id);
	}
	
	@Override
	public WxCoupon queryCouponActivityById(Integer id,Integer type,Integer formType) {
		// TODO Auto-generated method stub
		return couponMapper.queryCouponActivityById(id,type,formType);
	}

	@Override
	public Integer queryUserCouponActivityCount(String openId,Integer couponId) {
		// TODO Auto-generated method stub
		return couponMapper.queryUserCouponActivityCount(openId,String.valueOf(couponId));
	}

	@Override
	public void updateCouponActivityCouponNum(Integer id) {
		// TODO Auto-generated method stub
		couponMapper.updateCouponActivityCouponNum(id);
	}

	@Override
	public Integer queryUserCouponActivityDayCount(String openId,Integer couponId) {
		// TODO Auto-generated method stub
		return couponMapper.queryUserCouponActivityDayCount(openId,String.valueOf(couponId));
	}

	@Override
	public void updateCouponCouponNum(Integer id) {
		// TODO Auto-generated method stub
		couponMapper.updateCouponCouponNum(id);
	}
}