package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.QuestConclusion;
import com.bluekjg.admin.mapper.QuestConclusionMapper;
import com.bluekjg.admin.service.IQuestConclusionService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问卷测试结论表 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2018-10-11
 */
@Service
public class QuestConclusionServiceImpl extends ServiceImpl<QuestConclusionMapper, QuestConclusion> implements IQuestConclusionService {
	
	@Autowired private QuestConclusionMapper questConclusionMapper;
	@Override
	public void selectDataGrid(PageInfo pageInfo, QuestConclusion questConclusion) {
		Page<QuestConclusion> page = new Page<QuestConclusion>(pageInfo.getNowpage(),pageInfo.getSize());
		List<QuestConclusion> list = questConclusionMapper.selectDataGrid(page,questConclusion);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
}
