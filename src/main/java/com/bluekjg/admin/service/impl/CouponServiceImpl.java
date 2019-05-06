package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Coupon;
import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.admin.mapper.CouponMapper;
import com.bluekjg.admin.mapper.StaticFilesMapper;
import com.bluekjg.admin.service.ICouponService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-09-29
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {
	
	@Autowired
	private CouponMapper couponMapper;
	@Autowired private StaticFilesMapper staticFilesMapper;

	@Override
	public void selectDataGrid(PageInfo pageInfo, Coupon coupon) {
		Page<Coupon> page = new Page<Coupon>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Coupon> list = couponMapper.selectDataGrid(page,coupon);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	
	@Override
	public void selectCombogridDataGrid(PageInfo pageInfo, Integer type) {
		List<Coupon> list = couponMapper.selectCombogridDataGrid(type);
		pageInfo.setRows(list);
	}

	@Override
	public void queryUserCoupon(PageInfo pageInfo, Coupon coupon) {
		Page<Coupon> page = new Page<Coupon>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Coupon> list = couponMapper.queryUserCoupon(page,coupon);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}



	@Override
	public boolean insertCoupon(Coupon coupon) {
		Integer result = couponMapper.insert(coupon);
		if (result>0) {
			StaticFiles staticFiles = new StaticFiles();
			staticFiles.setFilePath(coupon.getFilePath());
			staticFiles.setRelationId(coupon.getId());
			staticFiles.setFileType(0);
			staticFiles.setBigType(1);
			staticFiles.setSmallType(0);
			Integer n  = staticFilesMapper.addFilePath(staticFiles);
			if (n>0) {
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public Coupon selectCouponById(Integer id) {
		return couponMapper.selectCouponById(id);
	}

	@Override
	public boolean updateCouponById(Coupon coupon) {
		couponMapper.deleteFilePath(coupon);
		couponMapper.updateCoupon(coupon);
		StaticFiles staticFiles = new StaticFiles();
		staticFiles.setFilePath(coupon.getFilePath());
		staticFiles.setRelationId(coupon.getId());
		staticFiles.setFileType(0);
		staticFiles.setBigType(1);
		staticFiles.setSmallType(0);
		Integer n = staticFilesMapper.addFilePath(staticFiles);
		if (n>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Coupon> queryAllCoupon(Coupon coupon) {
		return couponMapper.queryAllCoupon(coupon);
	}

	@Override
	public void insertCouponStore(Integer couponId, Integer storeId) {
		// TODO Auto-generated method stub
		couponMapper.insertCouponStore(couponId, storeId);
	}

	@Override
	public void deleteCouponStore(Integer couponId) {
		// TODO Auto-generated method stub
		couponMapper.deleteCouponStore(couponId);
	}

	@Override
	public List<Integer> selectCouponStore(Integer couponId) {
		// TODO Auto-generated method stub
		return couponMapper.selectCouponStore(couponId);
	}

	@Override
	public List<Integer> selectCouponGoods(Integer couponId) {
		// TODO Auto-generated method stub
		return couponMapper.selectCouponGoods(couponId);
	}

	@Override
	public void insertCouponGoods(Integer couponId, Integer goodsId) {
		// TODO Auto-generated method stub
		couponMapper.insertCouponGoods(couponId, goodsId);
	}

	@Override
	public void deleteCouponGoods(Integer couponId) {
		// TODO Auto-generated method stub
		couponMapper.deleteCouponGoods(couponId);
	}

	@Override
	public void selectUserCouponDataGrid(PageInfo pageInfo, Coupon coupon) {
		Page<Coupon> page = new Page<Coupon>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Coupon> list = couponMapper.selectUserCouponDataGrid(page,coupon);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	
}
