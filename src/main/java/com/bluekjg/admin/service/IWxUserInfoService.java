package com.bluekjg.admin.service;

import com.bluekjg.admin.model.IndexModel;
import com.bluekjg.admin.model.WxUserInfo;
import com.bluekjg.core.commons.result.PageInfo;
import javax.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Max
 * @since 2018-03-30
 */
public interface IWxUserInfoService extends IService<WxUserInfo> {
	
	/**
	 * 获取用户信息列表
	 * @param pageInfo
	 * @param userInfo
	 */
	void queryUserInfoList(PageInfo pageInfo, WxUserInfo userInfo);

	//查询店主未指派的权限
	void queryModelList(PageInfo pageInfo, WxUserInfo wxUserInfo);

	//确认赋予权限
	boolean addModel(IndexModel indexModel);

	//查询已指派的权限
	void queryModelUserList(PageInfo pageInfo, WxUserInfo wxUserInfo);

	//批量取消权限
	boolean deleteByIds(IndexModel indexModel);

	//导出用户
	void downLoadUserInfo(WxUserInfo wxUserInfo, HttpServletResponse response);
	
	/**
	 * 黑名单
	 * @param page
	 * @param userInfo
	 * @return
	 */
	void queryUserBlacklistDataGrid(PageInfo pageInfo, WxUserInfo userInfo);
	Integer queryUserBlacklistById(String openId);
	void deleteUserBlacklist(String openId);
	void insertUserBlacklist(WxUserInfo userInfo);
}
