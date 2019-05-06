package com.bluekjg.wxapp.service;

import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxCoupon;


/**
 * @description：优惠券信息
 * @author：pincui.Tom
 * @date：2018/9/27 14:51
 */
public interface IWxCouponService extends IService<WxCoupon> {

	/**
	 * 查询我的优惠券
	 * @param dto
	 * @return
	 */
	List<WxCoupon> queryCouponMy(PagingDto dto);
	/**
	 * 查询优惠券列表
	 * @param dto
	 * @return
	 */
	List<WxCoupon> queryCouponList(PagingDto dto);
	/**
	 * 查询我的可用优惠券
	 * @param dto
	 * @return
	 */
	List<WxCoupon> queryOrderCoupon(PagingDto dto);
	/**
	 * 查询优惠券
	 * @param id
	 * @return
	 */
	WxCoupon queryCouponById(Integer id);
	/**
	 * 查询优惠券活动
	 * @param id
	 * @return
	 */
	WxCoupon queryCouponActivityById(Integer id,Integer type,Integer formType);
	/**
	 * 查询用户当前优惠券领取数量
	 * @param couponId
	 * @return
	 */
	Integer queryUserCouponActivityCount(String openId,Integer couponId);
	Integer queryUserCouponActivityDayCount(String openId,Integer couponId);
	/**
	 * 优惠券库存-1
	 * @param id
	 * @return
	 */
	void updateCouponCouponNum(Integer id);
	/**
	 * 活动优惠券库存-1
	 * @param id
	 * @return
	 */
	void updateCouponActivityCouponNum(Integer id);
	/**
	 * 领取优惠券
	 * @param bean
	 * @return
	 */
	void insertUserCoupon(String openId,Integer couponId,Integer sourceType,Integer sourceId);
}