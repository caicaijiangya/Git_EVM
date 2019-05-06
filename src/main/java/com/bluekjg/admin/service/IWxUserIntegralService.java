package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Order;
import com.bluekjg.admin.model.WxUserInfo;
import com.bluekjg.core.commons.result.PageInfo;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户积分表 服务类
 * </p>
 *
 * @author Tom
 * @since 2019-01-14
 */
public interface IWxUserIntegralService extends IService<WxUserInfo> {
	
	/**
	 * 查询会员用户积分列表
	 * @param pageInfo
	 * @param userInfo
	 */
	void queryUserIntegralList(PageInfo pageInfo, WxUserInfo userInfo);
	/**
	 * 查询积分明细列表
	 * @param pageInfo
	 * @param userInfo
	 */
	void queryUserIntegralDetailList(PageInfo pageInfo, WxUserInfo userInfo);
	/**
	 * 修改积分信息
	 * @param userInfo
	 * @return
	 */
	void updateUserIntegral(WxUserInfo userInfo);
	/**
	 * 导出积分
	 * @param userInfo
	 * @param response
	 */
	void downLoadIntegral(WxUserInfo userInfo, HttpServletResponse response);
	/**
	 * 导出积分明细
	 * @param openId
	 * @param response
	 */
	void downLoadIntegralDetail(WxUserInfo userInfo, HttpServletResponse response);
}
