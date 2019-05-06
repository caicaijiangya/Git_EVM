package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.UserIssueCoupon;
import com.bluekjg.admin.model.WxUserInfo;
import com.bluekjg.admin.mapper.UserIssueCouponMapper;
import com.bluekjg.admin.service.IUserIssueCouponService;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.wxapp.service.IWxCouponService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券人工发放 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2019-02-27
 */
@Service
public class UserIssueCouponServiceImpl extends ServiceImpl<UserIssueCouponMapper, UserIssueCoupon> implements IUserIssueCouponService {
	
	@Autowired
	private UserIssueCouponMapper userIssueCouponMapper;
	@Autowired
	private IWxCouponService couponService;

	@Override
	public void selectDataGrid(PageInfo pageInfo, UserIssueCoupon userIssueCoupon) {
		Page<UserIssueCoupon> page = new Page<UserIssueCoupon>(pageInfo.getNowpage(),pageInfo.getSize());
		List<UserIssueCoupon> list = userIssueCouponMapper.selectDataGrid(page,userIssueCoupon);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void selectUserDataGrid(PageInfo pageInfo, WxUserInfo userInfo) {
		Page<WxUserInfo> page = new Page<WxUserInfo>(pageInfo.getNowpage(),pageInfo.getSize());
		List<WxUserInfo> list = userIssueCouponMapper.selectUserDataGrid(page,userInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public List<String> selectUserOpenId(WxUserInfo userInfo) {
		// TODO Auto-generated method stub
		return userIssueCouponMapper.selectUserOpenId(userInfo);
	}

	@Override
	public void insertUserIssueCoupon(List<String> openIdList,Integer userNum,Integer couponId,Long userId) {
		if(openIdList != null && openIdList.size() > 0) {
			if(userNum != null &&userNum > 0 && couponId != null && couponId > 0) {
				for(String openId:openIdList) {
					int successNum = 0;
					for(int i=0;i<userNum;i++) {
						try {
							couponService.insertUserCoupon(openId, couponId, 5, 0);
							successNum ++ ;
						} catch (Exception e) {}
					}
					UserIssueCoupon userIssueCoupon = new UserIssueCoupon();
					userIssueCoupon.setOpenId(openId);
					userIssueCoupon.setCouponId(couponId);
					userIssueCoupon.setUserNum(userNum);
					userIssueCoupon.setSuccessNum(successNum);
					userIssueCoupon.setErrorNum(userNum - successNum);
					userIssueCoupon.setUserId(Integer.valueOf(userId.toString()));
					userIssueCouponMapper.insertUserIssueCoupon(userIssueCoupon);
				}
			}
		}
		
	}
}
