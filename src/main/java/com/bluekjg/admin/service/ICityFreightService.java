package com.bluekjg.admin.service;

import com.bluekjg.admin.model.CityFreight;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 地区运费表 服务类
 * </p>
 *
 * @author Tom
 * @since 2018-11-8
 */
public interface ICityFreightService extends IService<CityFreight> {
	
	void selectDataGrid(PageInfo pageInfo, CityFreight freight);
	
}
