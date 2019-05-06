package com.bluekjg.admin.service;

import com.bluekjg.admin.model.ExportDto;
import com.bluekjg.admin.model.QuestProblem;
import com.bluekjg.core.commons.result.PageInfo;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 问卷测试问题表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-10-11
 */
public interface IQuestProblemService extends IService<QuestProblem> {

	void selectDataGrid(PageInfo pageInfo, QuestProblem questProblem);

	void selectAnswerList(PageInfo pageInfo, QuestProblem questProblem);

	Integer updateAnsewrById(QuestProblem questProblem);

	Integer insertAnswer(QuestProblem questProblem);

	QuestProblem selectAnswerById(Integer id);

	Integer updateAnswerById(QuestProblem questProblem);
	
	List<Map<String,Object>> queryQuestExportData(ExportDto dto);
}
