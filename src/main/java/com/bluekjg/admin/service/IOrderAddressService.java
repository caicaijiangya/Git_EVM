package com.bluekjg.admin.service;

import com.bluekjg.admin.model.OrderAddress;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 订单地址表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-10-10
 */
public interface IOrderAddressService extends IService<OrderAddress> {

	Integer insertExpress(OrderAddress orderAddress);
	
}
