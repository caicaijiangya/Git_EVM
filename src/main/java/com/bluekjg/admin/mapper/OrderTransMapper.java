package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.OrderTrans;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-10-09
 */
public interface OrderTransMapper extends BaseMapper<OrderTrans> {

	List<OrderTrans> selectDataGrid(Page<OrderTrans> page, OrderTrans orderTrans);

}