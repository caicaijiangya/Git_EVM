package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.ExportDto;
import com.bluekjg.admin.model.QuestProblem;
import com.bluekjg.admin.mapper.QuestProblemMapper;
import com.bluekjg.admin.service.IQuestProblemService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问卷测试问题表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-10-11
 */
@Service
public class QuestProblemServiceImpl extends ServiceImpl<QuestProblemMapper, QuestProblem> implements IQuestProblemService {

	@Autowired private QuestProblemMapper questProblemMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, QuestProblem questProblem) {
		Page<QuestProblem> page = new Page<QuestProblem>(pageInfo.getNowpage(),pageInfo.getSize());
		List<QuestProblem> list = questProblemMapper.selectDataGrid(page,questProblem);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void selectAnswerList(PageInfo pageInfo, QuestProblem questProblem) {
		Page<QuestProblem> page = new Page<QuestProblem>(pageInfo.getNowpage(),pageInfo.getSize());
		List<QuestProblem> list = questProblemMapper.selectAnswerList(page,questProblem);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public Integer updateAnsewrById(QuestProblem questProblem) {
		return questProblemMapper.updateAnsewrById(questProblem);
	}

	@Override
	public Integer insertAnswer(QuestProblem questProblem) {
		return questProblemMapper.insertAnswer(questProblem);
	}

	@Override
	public QuestProblem selectAnswerById(Integer id) {
		return questProblemMapper.selectAnswerById(id);
	}

	@Override
	public Integer updateAnswerById(QuestProblem questProblem) {
		return questProblemMapper.updateAnswerById(questProblem);
	}

	@Override
	public List<Map<String,Object>> queryQuestExportData(ExportDto dto) {
		// TODO Auto-generated method stub
		return questProblemMapper.queryQuestExportData(dto);
	}
	
}
