package com.bluekjg.wxapp.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.AccessLog;

/**
 * @description：用户访问日志表数据库控制层接口
 * @author：pincui.tom
 * @date：2019/03/05 14:51
 */
public interface WxAccessLogMapper extends BaseMapper<AccessLog> {
	/**
	 * 查询是否新用户
	 * @param openId
	 * @return
	 */
	Integer queryUserNewOld(@Param("openId") String openId);
	/**
	 * 添加新商品访问记录
	 * @param log
	 * @return
	 */
	boolean insertGoodsAccessLog(AccessLog log);
	/**
	 * 添加新活动访问记录
	 * @param log
	 * @return
	 */
	boolean insertActivityAccessLog(AccessLog log);
}