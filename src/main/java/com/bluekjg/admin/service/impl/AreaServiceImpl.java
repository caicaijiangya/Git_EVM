package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Area;
import com.bluekjg.admin.mapper.AreaMapper;
import com.bluekjg.admin.service.IAreaService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地市表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-10-01
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

	@Autowired private AreaMapper areaMapper;
	
	@Override
	public List<Area> queryProvinceList() {
		return areaMapper.queryProvinceList();
	}

	@Override
	public List<Area> queryCityList(Integer provinceId) {
		return areaMapper.queryCityList(provinceId);
	}

	@Override
	public List<Area> queryAreaList(Integer cityId) {
		return areaMapper.queryAreaList(cityId);
	}
	
}
