package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.OrderAddress;
import com.bluekjg.admin.mapper.OrderAddressMapper;
import com.bluekjg.admin.service.IOrderAddressService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单地址表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-10-10
 */
@Service
public class OrderAddressServiceImpl extends ServiceImpl<OrderAddressMapper, OrderAddress> implements IOrderAddressService {

	@Autowired private OrderAddressMapper orderAddressMapper;
	
	@Override
	public Integer insertExpress(OrderAddress orderAddress) {
		return orderAddressMapper.insertExpress(orderAddress);
	}
	
}
