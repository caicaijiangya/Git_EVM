package com.bluekjg.wxapp.service;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.AccessLog;

/**
 * @description：用户访问日志信息
 * @author：pincui.Tom
 * @date：2019/03/05 14:51
 */
public interface IWxAccessLogService extends IService<AccessLog> {
	/**
	 * 查询是否新用户
	 * @param openId
	 * @return
	 */
	Integer queryUserNewOld(String openId);
	/**
	 * 添加新商品访问记录
	 * @param log
	 * @return
	 */
	void insertGoodsAccessLog(AccessLog log);
	/**
	 * 添加新活动访问记录
	 * @param log
	 * @return
	 */
	void insertActivityAccessLog(AccessLog log);
}