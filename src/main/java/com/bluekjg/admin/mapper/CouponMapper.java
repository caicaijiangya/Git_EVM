package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Coupon;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 优惠券 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-09-29
 */
public interface CouponMapper extends BaseMapper<Coupon> {

	List<Coupon> selectDataGrid(Page<Coupon> page, Coupon coupon);
	
	List<Coupon> selectCombogridDataGrid(@Param("type") Integer type);

	List<Coupon> queryUserCoupon(Page<Coupon> page, Coupon coupon);

	void addFilePath(Coupon coupon);

	Coupon selectCouponById(Integer id);

	void deleteFilePath(Coupon coupon);
	
	List<Coupon> queryAllCoupon(Coupon coupon);
	
	List<Integer> selectCouponStore(@Param("couponId") Integer couponId);

	boolean insertCouponStore(@Param("couponId") Integer couponId,@Param("storeId") Integer storeId);
	
	boolean deleteCouponStore(@Param("couponId") Integer couponId);
	
	List<Integer> selectCouponGoods(@Param("couponId") Integer couponId);

	boolean insertCouponGoods(@Param("couponId") Integer couponId,@Param("goodsId") Integer goodsId);
	
	boolean deleteCouponGoods(@Param("couponId") Integer couponId);
	
	List<Coupon> selectUserCouponDataGrid(Page<Coupon> page, Coupon coupon);
	
	boolean updateCoupon(Coupon coupon);
}