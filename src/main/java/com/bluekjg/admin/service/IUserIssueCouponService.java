package com.bluekjg.admin.service;

import com.bluekjg.admin.model.UserIssueCoupon;
import com.bluekjg.admin.model.WxUserInfo;
import com.bluekjg.core.commons.result.PageInfo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 优惠券人工发放 服务类
 * </p>
 *
 * @author Tom
 * @since 2019-02-27
 */
public interface IUserIssueCouponService extends IService<UserIssueCoupon> {

	void selectDataGrid(PageInfo pageInfo, UserIssueCoupon userIssueCoupon);
	
	void selectUserDataGrid(PageInfo pageInfo, WxUserInfo userInfo);
	
	List<String> selectUserOpenId(WxUserInfo userInfo);
	
	void insertUserIssueCoupon(List<String> openIdList,Integer userNum,Integer couponId,Long userId);
}
