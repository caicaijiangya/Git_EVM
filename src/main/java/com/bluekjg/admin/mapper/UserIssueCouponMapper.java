package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.UserIssueCoupon;
import com.bluekjg.admin.model.WxUserInfo;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 优惠券人工发放 Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2019-02-27
 */
public interface UserIssueCouponMapper extends BaseMapper<UserIssueCoupon> {

	List<UserIssueCoupon> selectDataGrid(Page<UserIssueCoupon> page, UserIssueCoupon userIssueCoupon);
	
	List<WxUserInfo> selectUserDataGrid(Page<WxUserInfo> page, WxUserInfo userInfo);
	
	List<String> selectUserOpenId(WxUserInfo userInfo);
	
	boolean insertUserIssueCoupon(UserIssueCoupon userIssueCoupon);
}