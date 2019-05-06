package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Coupon;
import com.bluekjg.core.commons.result.PageInfo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 优惠券 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-09-29
 */
public interface ICouponService extends IService<Coupon> {

	void selectDataGrid(PageInfo pageInfo, Coupon coupon);
	
	void selectCombogridDataGrid(PageInfo pageInfo, Integer type);

	//优惠券领取详情
	void queryUserCoupon(PageInfo pageInfo, Coupon coupon);

	boolean insertCoupon(Coupon coupon);

	Coupon selectCouponById(Integer id);

	boolean updateCouponById(Coupon coupon);

	//查询所有优惠券---pjf
	List<Coupon> queryAllCoupon(Coupon coupon);
	//优惠券门店
	List<Integer> selectCouponStore(Integer couponId);
	void insertCouponStore(Integer couponId,Integer storeId);
	void deleteCouponStore(Integer couponId);
	//优惠券商品
	List<Integer> selectCouponGoods(Integer couponId);
	void insertCouponGoods(Integer couponId,Integer goodsId);
	void deleteCouponGoods(Integer couponId);
	
	
	void selectUserCouponDataGrid(PageInfo pageInfo, Coupon coupon);
}
