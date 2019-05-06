package com.bluekjg.wxapp.service;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.WxAccessFlow;

/**
 * @description：用户访问日志信息
 * @author：pincui.Tom
 * @date：2018/11/29 14:51
 */
public interface IWxAccessFlowService extends IService<WxAccessFlow> {

	/**
	 * 访问次数累计
	 * @param flow
	 * @return
	 */
	void addAccessFlow(WxAccessFlow flow);
	
	/**
	 * 修改用户所属门店
	 * @param flow
	 * @return
	 */
	void updateUserStore(WxAccessFlow flow);
}