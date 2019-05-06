package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.IndexModel;
import com.bluekjg.admin.mapper.IndexModelMapper;
import com.bluekjg.admin.service.IIndexModelService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 模块管理表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
@Service
public class IndexModelServiceImpl extends ServiceImpl<IndexModelMapper, IndexModel> implements IIndexModelService {

	@Autowired private IndexModelMapper indexModelMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, IndexModel indexModel) {
		Page<IndexModel> page = new Page<IndexModel>(pageInfo.getNowpage(),pageInfo.getSize());
		List<IndexModel> list = indexModelMapper.selectDataGrid(page,indexModel);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
}
