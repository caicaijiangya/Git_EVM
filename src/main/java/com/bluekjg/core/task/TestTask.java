package com.bluekjg.core.task;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.service.IWxErpService;
import com.bluekjg.wxapp.service.IWxOrderService;
import com.bluekjg.wxapp.service.IWxTestTaskService;

/**
 * spring task 定时任务测试，适用于单系统
 * 注意：不适合用于集群
 * @author L.cm
 */
@Component
public class TestTask {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private IWxTestTaskService testTaskService;
	@Autowired
	private IWxErpService erpService;
	@Autowired
	private IWxOrderService orderService;
	/**
	 * 零点执行
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void timedtask0() {
		try {
			logger.info("定时任务-活动");
			testTaskService.testtask0();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	/**
	 * 一点执行
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void timedtask1() {
		try {
			logger.info("定时任务-优惠券");
			testTaskService.testtask1();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	/**
	 * 两点执行
	 */
	@Scheduled(cron = "0 0 2 * * ?")
	public void timedtask2() {
		try {
			logger.info("定时任务-订单");
			testTaskService.testtask2();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	/**
	 * 三点执行
	 */
	@Scheduled(cron = "0 0 3 * * ?")
	public void timedtask3() {
		try {
			logger.info("定时任务-ERP发货单");
			List<WxOrder> list = orderService.queryOrderByExpressList();
			if(list != null && list.size() > 0) {
				for(WxOrder order:list) {
					if(order.getOrderNo() != null) {
						erpService.pushDeliveryErp(order.getOrderNo());
					}
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 	四点执行
	 */
	@Scheduled(cron = "0 0 4 * * ?")
	public void timedtask4() {
		try {
			logger.info("定时任务-更新数据分析表");
			testTaskService.updateAnalytics();
			testTaskService.updateUserPortrait();
			testTaskService.updateAnalyticsPage();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 每隔一小时执行
	 */
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void timedtask_1() {
		try {
			logger.info("定时任务-拼团");
			testTaskService.testtask_1();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 每隔十分钟执行
	 */
	@Scheduled(cron = "0 0/10 * * * ?")
	public void timedtask_10i() {
		try {
			logger.info("定时任务-取消订单");
			testTaskService.timedtask_10i();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
