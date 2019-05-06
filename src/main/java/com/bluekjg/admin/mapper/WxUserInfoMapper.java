package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.IndexModel;
import com.bluekjg.admin.model.WxUserInfo;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Max
 * @since 2018-03-30
 */
public interface WxUserInfoMapper extends BaseMapper<WxUserInfo> {
	
	/**
	 * 获取所有用户信息
	 * @param page
	 * @param userInfo
	 * @return
	 */
	List<WxUserInfo> queryUserInfoList(Page<WxUserInfo> page, WxUserInfo userInfo);

	//查询店主未赋予的操作权限模块
	List<WxUserInfo> queryModelList(Page<WxUserInfo> page, WxUserInfo wxUserInfo);
	//导出微信用户
	List<Map<String,Object>> downLoadUserInfo(WxUserInfo userInfo);
	
	//赋予权限
	Integer addModelUser(List<IndexModel> imList);

	//查看已赋予的操作权限
	List<WxUserInfo> queryModelUserList(Page<WxUserInfo> page, WxUserInfo wxUserInfo);

	//批量取消权限
	Integer deleteByIds(List<Integer> idList);

	/**
	 * 黑名单
	 * @param page
	 * @param userInfo
	 * @return
	 */
	List<WxUserInfo> queryUserBlacklistDataGrid(Page<WxUserInfo> page, WxUserInfo userInfo);
	Integer queryUserBlacklistById(@Param("openId") String openId);
	boolean deleteUserBlacklist(@Param("openId") String openId);
	boolean insertUserBlacklist(WxUserInfo userInfo);
}