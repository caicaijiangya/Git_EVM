package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Area;
import com.bluekjg.core.commons.result.City;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 地市表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-10-01
 */
public interface AreaMapper extends BaseMapper<Area> {
	//查询所有省份
	List<Area> queryProvinceList();

	//查询市
	List<Area> queryCityList(Integer provinceId);

	//查询区
	List<Area> queryAreaList(Integer cityId);
	
	List<Map<String,Object>> getCityLevel(Integer pid);
	
	List<City> getCity();

}