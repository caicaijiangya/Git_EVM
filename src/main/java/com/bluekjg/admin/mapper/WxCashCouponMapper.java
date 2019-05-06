package com.bluekjg.admin.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bluekjg.admin.model.WxCashCoupon;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-08-03
 */
public interface WxCashCouponMapper extends BaseMapper<WxCashCoupon> {

	//获取优惠券
	List<WxCashCoupon> selectDataGrid(Page<WxCashCoupon> page, WxCashCoupon wxCashCoupon);

	void delById(Integer id);

}