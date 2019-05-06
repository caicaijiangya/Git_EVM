package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.QuestDimension;
import com.bluekjg.admin.model.QuestGoods;
import com.bluekjg.admin.mapper.QuestDimensionMapper;
import com.bluekjg.admin.service.IQuestDimensionService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问卷测试维度表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-10-11
 */
@Service
public class QuestDimensionServiceImpl extends ServiceImpl<QuestDimensionMapper, QuestDimension> implements IQuestDimensionService {
	
	@Autowired private QuestDimensionMapper questDimensionMapper;
	
	@Override
	public List<QuestDimension> selectDimensionList() {
		return questDimensionMapper.selectDimensionList();
	}

	@Override
	public void selectDataGrid(PageInfo pageInfo, QuestDimension questDimension) {
		Page<QuestDimension> page = new Page<QuestDimension>(pageInfo.getNowpage(),pageInfo.getSize());
		List<QuestDimension> list = questDimensionMapper.selectDataGrid(page,questDimension);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
}
