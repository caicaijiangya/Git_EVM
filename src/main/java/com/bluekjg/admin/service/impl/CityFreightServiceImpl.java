package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.CityFreight;
import com.bluekjg.admin.model.Order;
import com.bluekjg.admin.mapper.CityFreightMapper;
import com.bluekjg.admin.service.ICityFreightService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地区运费表 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2018-11-8
 */
@Service
public class CityFreightServiceImpl extends ServiceImpl<CityFreightMapper, CityFreight> implements ICityFreightService {

	@Autowired 
	private CityFreightMapper cityFreightMapper;

	@Override
	public void selectDataGrid(PageInfo pageInfo, CityFreight freight) {
		Page<CityFreight> page = new Page<CityFreight>(pageInfo.getNowpage(),pageInfo.getSize());
		List<CityFreight> list = cityFreightMapper.selectDataGrid(page,freight);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	
	
}
