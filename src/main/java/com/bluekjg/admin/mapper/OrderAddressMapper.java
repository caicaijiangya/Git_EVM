package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.OrderAddress;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 订单地址表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-10-10
 */
public interface OrderAddressMapper extends BaseMapper<OrderAddress> {

	Integer insertExpress(OrderAddress orderAddress);

}