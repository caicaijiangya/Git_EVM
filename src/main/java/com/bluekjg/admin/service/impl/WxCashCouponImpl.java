package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.WxCashCoupon;
import com.bluekjg.admin.mapper.WxCashCouponMapper;
import com.bluekjg.admin.service.IWxCashCouponService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动主表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-09-28
 */
@Service
public class WxCashCouponImpl extends ServiceImpl<WxCashCouponMapper, WxCashCoupon> implements IWxCashCouponService {
	
	@Autowired WxCashCouponMapper cashCouponMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, WxCashCoupon cashCoupon) {
		Page<WxCashCoupon> page = new Page<WxCashCoupon>(pageInfo.getNowpage(),pageInfo.getSize());
		List<WxCashCoupon> list = cashCouponMapper.selectDataGrid(page,cashCoupon);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void delById(Integer id) {
		cashCouponMapper.delById(id);
	}

}
	
