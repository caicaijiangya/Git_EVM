package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.CouponActivity;
import com.bluekjg.admin.mapper.CouponActivityMapper;
import com.bluekjg.admin.service.ICouponActivityService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券活动 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2019-02-26
 */
@Service
public class CouponActivityServiceImpl extends ServiceImpl<CouponActivityMapper, CouponActivity> implements ICouponActivityService {
	
	@Autowired
	private CouponActivityMapper couponActivityMapper;

	@Override
	public void selectDataGrid(PageInfo pageInfo, CouponActivity couponActivity) {
		Page<CouponActivity> page = new Page<CouponActivity>(pageInfo.getNowpage(),pageInfo.getSize());
		List<CouponActivity> list = couponActivityMapper.selectDataGrid(page,couponActivity);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public CouponActivity selectCouponActivityById(Integer id) {
		return couponActivityMapper.selectCouponActivityById(id);
	}

	@Override
	public void updateCouponActivityLikeUrl(String id, String likeUrl) {
		// TODO Auto-generated method stub
		couponActivityMapper.updateCouponActivityLikeUrl(id, likeUrl);
	}

	@Override
	public void updateCouponActivity(CouponActivity couponActivity) {
		// TODO Auto-generated method stub
		couponActivityMapper.updateCouponActivity(couponActivity);
	}
	
}
