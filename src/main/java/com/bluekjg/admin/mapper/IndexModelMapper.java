package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.IndexModel;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 模块管理表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
public interface IndexModelMapper extends BaseMapper<IndexModel> {

	//查询所有模块信息
	List<IndexModel> selectDataGrid(Page<IndexModel> page, IndexModel indexModel);

}