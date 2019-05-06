package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.WxUserInfo;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 用户积分表 Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2019-01-14
 */
public interface WxUserIntegralMapper extends BaseMapper<WxUserInfo> {
	
	/**
	 * 查询会员用户积分列表
	 * @param page
	 * @param userInfo
	 * @return
	 */
	List<WxUserInfo> queryUserIntegralList(Page<WxUserInfo> page, WxUserInfo userInfo);
	/**
	 * 查询积分明细
	 * @param openId
	 * @return
	 */
	List<WxUserInfo> queryUserIntegralDetailList(Page<WxUserInfo> page, WxUserInfo userInfo);
	/**
	 * 修改积分信息
	 * @param userInfo
	 * @return
	 */
	Integer updateUserIntegral(WxUserInfo userInfo);
	/**
	 * 导出积分
	 * @return
	 */
	List<Map<String,Object>> downLoadIntegral(WxUserInfo userInfo);
	/**
	 * 导出积分明细
	 * @return
	 */
	List<Map<String,Object>> downLoadIntegralDetail(WxUserInfo userInfo);
	
}