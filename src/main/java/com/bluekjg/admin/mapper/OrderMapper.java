package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Order;
import com.bluekjg.admin.model.OrderAddress;
import com.bluekjg.admin.model.OrderDetail;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 订单主表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-09-18
 */
public interface OrderMapper extends BaseMapper<Order> {

	List<Order> selectDataGrid(Page<Order> page, Order o);

	//导出订单
	List<Map<String, Object>> downLoadOrder(Order order);
	//导出订单详情
	List<Map<String, Object>> downLoadOrderDetail(Order order);
	//导出订单赠品
	List<Map<String,Object>> downLoadOrderGift(Order order);

	//订单详情
	Order queryOrderDetailById(Integer orderId);

	//订单商品详情
	List<OrderDetail> queryOrderGoodsList(Integer orderId);

	//每日订单统计
	List<Order> queryOrderCount(Page<Order> page, Order od);

	//导出每日订单数据
	List<Map<String, Object>> exportDayOrderExcelList(Order order);

	//每月订单统计
	List<Order> queryOrderCountByMonth(Page<Order> page, Order od);

	//导出每月订单数据
	List<Map<String, Object>> exportMonthOrderExcelList(Order order);

	void editStatus(OrderAddress orderAddress);

	List<Order> queryAllStatus();

}