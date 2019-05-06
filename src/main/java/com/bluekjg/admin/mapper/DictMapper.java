package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.DictData;
import com.bluekjg.admin.model.ParameterData;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 数据字典表 Mapper 接口
 * </p>
 *
 * @author Max
 * @since 2018-03-30
 */
public interface DictMapper extends BaseMapper<DictData> {

	List<DictData> queryDictList(Page<DictData> page, DictData dict);
	
	List<DictData> queryDictByCode(@Param("code") String code);
	
	List<ParameterData> queryParameterList(Page<ParameterData> page, ParameterData param);
	
	boolean updateParam(ParameterData param);
	
	boolean addParam(ParameterData param);
	
	ParameterData queryParameterById(Integer id);
	
	List<DictData> queryDictList(DictData dict);
	
	List<DictData> queryIconList();

	Integer countDictNum(DictData wxappDict);
	
}