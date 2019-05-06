package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Address;
import com.bluekjg.admin.model.OrderRefund;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 订单退款表 Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2019-01-14
 */
public interface OrderRefundMapper extends BaseMapper<OrderRefund> {

	List<OrderRefund> selectDataGrid(Page<OrderRefund> page, OrderRefund or);
	List<OrderRefund> selectDataGrid(OrderRefund or);
	List<Address> selectAdminAddress();
	List<Address> selectAreasList(@Param("id") Integer id);
	Address selectRefundAddress(@Param("refundId") Integer refundId);
	boolean insertAdminAddress(Address address);
	boolean insertRefundAddress(Address address);
}