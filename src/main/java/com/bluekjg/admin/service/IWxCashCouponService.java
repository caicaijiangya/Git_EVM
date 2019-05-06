package com.bluekjg.admin.service;

import com.bluekjg.admin.model.WxCashCoupon;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 地市表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-10-01
 */
public interface IWxCashCouponService extends IService<WxCashCoupon> {
	void selectDataGrid(PageInfo pageInfo, WxCashCoupon cashCoupon);

	void delById(Integer id);
	
}
