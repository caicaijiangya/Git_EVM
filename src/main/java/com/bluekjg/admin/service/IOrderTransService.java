package com.bluekjg.admin.service;

import com.bluekjg.admin.model.OrderTrans;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tim
 * @since 2018-10-09
 */
public interface IOrderTransService extends IService<OrderTrans> {

	void selectDataGrid(PageInfo pageInfo, OrderTrans orderTrans);
	
}
