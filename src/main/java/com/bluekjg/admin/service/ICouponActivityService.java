package com.bluekjg.admin.service;

import com.bluekjg.admin.model.CouponActivity;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 优惠券活动 服务类
 * </p>
 *
 * @author Tom
 * @since 2019-02-26
 */
public interface ICouponActivityService extends IService<CouponActivity> {

	void selectDataGrid(PageInfo pageInfo, CouponActivity couponActivity);

	CouponActivity selectCouponActivityById(Integer id);
	
	void updateCouponActivityLikeUrl(String id,String likeUrl);
	
	void updateCouponActivity(CouponActivity couponActivity);
}
