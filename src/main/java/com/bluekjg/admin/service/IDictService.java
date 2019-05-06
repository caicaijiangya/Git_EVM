package com.bluekjg.admin.service;

import com.bluekjg.admin.model.DictData;
import com.bluekjg.admin.model.ParameterData;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.result.Tree;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 数据字典表 服务类
 * </p>
 *
 * @author Max
 * @since 2018-03-30
 */
public interface IDictService extends IService<DictData> {
	
	void queryDictList(PageInfo pageInfo, DictData dict);
	
	void queryParameterList(PageInfo pageInfo, ParameterData param);
	
	boolean updateParam(ParameterData param);
	
	boolean addParam(ParameterData param);
	
	ParameterData queryParameterById(Integer id);
	
	List<Tree> selectTree(String params);
	DictData queryDictByCode(String code);
	List<DictData> queryDictByCodeList(String code);
	Integer countDictNum(DictData wxappDict);
	
}
