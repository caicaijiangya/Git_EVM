package com.bluekjg.wxapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxCoupon;

/**
 * @description：优惠券表数据库控制层接口
 * @author：pincui.tom
 * @date：2018/9/27 14:51
 */
public interface WxCouponMapper extends BaseMapper<WxCoupon> {
	/**
	 * 查询优惠券列表
	 * @param dto
	 * @return
	 */
	List<WxCoupon> queryCouponList(PagingDto dto);
	/**
	 * 查询我的优惠券
	 * @param dto
	 * @return
	 */
	List<WxCoupon> queryCouponMy(PagingDto dto);
	/**
	 * 查询我的可用优惠券
	 * @param dto
	 * @return
	 */
	List<WxCoupon> queryOrderCoupon(PagingDto dto);
	/**
	 * 查询我的优惠券
	 * @param id
	 * @return
	 */
	WxCoupon queryUserCouponById(@Param("id") Integer id);
	/**
	 * 查询优惠券
	 * @param id
	 * @return
	 */
	WxCoupon queryCouponById(@Param("id") Integer id);
	/**
	 * 查询优惠券活动
	 * @param id
	 * @return
	 */
	WxCoupon queryCouponActivityById(@Param("id") Integer id,@Param("type") Integer type,@Param("formType") Integer formType);
	/**
	 * 查询用户当前优惠券领取数量
	 * @param couponId
	 * @return
	 */
	Integer queryUserCouponActivityCount(@Param("openId") String openId,@Param("couponId") String couponId);
	Integer queryUserCouponActivityDayCount(@Param("openId") String openId,@Param("couponId") String couponId);
	/**
	 * 优惠券库存-1
	 * @param id
	 * @return
	 */
	boolean updateCouponCouponNum(@Param("id") Integer id);
	/**
	 * 活动优惠券库存-1
	 * @param id
	 * @return
	 */
	boolean updateCouponActivityCouponNum(@Param("id") Integer id);
	/**
	 * 领取优惠券
	 * @param bean
	 * @return
	 */
	boolean insertUserCoupon(WxCoupon bean);
}