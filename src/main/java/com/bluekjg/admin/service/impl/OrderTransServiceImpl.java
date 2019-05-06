package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.OrderTrans;
import com.bluekjg.admin.mapper.OrderTransMapper;
import com.bluekjg.admin.service.IOrderTransService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-10-09
 */
@Service
public class OrderTransServiceImpl extends ServiceImpl<OrderTransMapper, OrderTrans> implements IOrderTransService {

	@Autowired private OrderTransMapper orderTransMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, OrderTrans orderTrans) {
		Page<OrderTrans> page = new Page<OrderTrans>(pageInfo.getNowpage(),pageInfo.getSize());
		List<OrderTrans> list = orderTransMapper.selectDataGrid(page,orderTrans);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	
}
