package com.bluekjg.admin.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bluekjg.admin.model.CityFreight;

/**
 *
 * CityFreight 表数据库控制层接口
 *
 */
public interface CityFreightMapper extends BaseMapper<CityFreight> {

	List<CityFreight> selectDataGrid(Page<CityFreight> page, CityFreight freight);

}