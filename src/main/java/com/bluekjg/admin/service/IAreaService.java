package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Area;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 地市表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-10-01
 */
public interface IAreaService extends IService<Area> {
	//查询所有省份
	List<Area> queryProvinceList();
	
	//查询市
	List<Area> queryCityList(Integer provinceId);

	//查询区
	List<Area> queryAreaList(Integer cityId);
	
}
