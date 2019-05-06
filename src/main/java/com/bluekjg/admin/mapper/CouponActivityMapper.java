package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.CouponActivity;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 优惠券活动 Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2019-02-26
 */
public interface CouponActivityMapper extends BaseMapper<CouponActivity> {

	List<CouponActivity> selectDataGrid(Page<CouponActivity> page, CouponActivity couponActivity);

	CouponActivity selectCouponActivityById(@Param("id") Integer id);
	
	boolean updateCouponActivityLikeUrl(@Param("id") String id,@Param("likeUrl") String likeUrl);
	
	boolean updateCouponActivity(CouponActivity couponActivity);
}