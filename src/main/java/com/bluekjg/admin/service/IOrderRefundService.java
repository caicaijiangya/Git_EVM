package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Address;
import com.bluekjg.admin.model.Order;
import com.bluekjg.admin.model.OrderRefund;
import com.bluekjg.core.commons.result.PageInfo;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 订单退款表 服务类
 * </p>
 *
 * @author Tom
 * @since 2019-01-14
 */
public interface IOrderRefundService extends IService<OrderRefund> {

	void selectDataGrid(PageInfo pageInfo, OrderRefund or);
	void selectAddressDataGrid(PageInfo pageInfo);
	void selectAreaDataGrid(PageInfo pageInfo,Integer id);
	Address selectRefundAddress(Integer refundId);
	void insertAdminAddress(Address address);
	void insertRefundAddress(Address address);
	
	void downLoadOrderRefund(OrderRefund or, HttpServletResponse response);
}
