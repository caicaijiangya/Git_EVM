package com.bluekjg.wxapp.service;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.WxOrder;


/**
 * @description：定时任务服务
 * @author：pincui.Tom
 * @date：2018/9/27 14:51
 */
public interface IWxTestTaskService extends IService<WxOrder> {
	/**
	 * 凌晨0点时段执行
	 * @return
	 */
	void testtask0();
	/**
	 * 凌晨1点时段执行
	 */
	void testtask1();
	/**
	 * 凌晨2点时段执行
	 * @return
	 */
	void testtask2();
	/**
	 * 每隔一个小时执行一次
	 * @return
	 */
	void testtask_1();
	/**
	 * 每隔十分钟执行一次
	 * @return
	 */
	void timedtask_10i();
	/**
	 * 定期更新 t_evm_analytics
	 */
	void updateAnalytics();
	/**
	 * 定期更新 t_evm_user_portrait
	 */
	void updateUserPortrait();
	/**
	 * 定期更新 t_evm_analytics_page
	 */
	void updateAnalyticsPage();
}