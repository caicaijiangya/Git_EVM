package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Order;
import com.bluekjg.admin.model.OrderAddress;
import com.bluekjg.admin.model.OrderDetail;
import com.bluekjg.core.commons.result.PageInfo;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 订单主表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-09-18
 */
public interface IOrderService extends IService<Order> {

	void selectDataGrid(PageInfo pageInfo, Order o);

	//导出订单
	void downLoadOrder(Order order, HttpServletResponse response);
	//导出订单详情
	void downLoadOrderDetail(Order order, HttpServletResponse response);
	//导出订单赠品
	void downLoadOrderGift(Order order, HttpServletResponse response);
	//查询订单详情
	Order queryOrderDetailById(Integer orderId);
	//查询订单商品详情
	List<OrderDetail> queryOrderGoodsList(Integer orderId);

	//每日订单统计
	void queryOrderCount(PageInfo pageInfo, Order od);

	//导出每日订单统计
	void exportDayOrderExcelList(Order order, HttpServletResponse response);

	//每月订单统计
	void queryOrderCountByMonth(PageInfo pageInfo, Order od);

	//导出每月订单统计
	void exportMonthOrderExcelList(Order order, HttpServletResponse response);

	void editStatus(OrderAddress orderAddress);

}
