package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.ExportDto;
import com.bluekjg.admin.model.QuestProblem;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 问卷测试问题表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-10-11
 */
public interface QuestProblemMapper extends BaseMapper<QuestProblem> {

	List<QuestProblem> selectDataGrid(Page<QuestProblem> page, QuestProblem questProblem);

	List<QuestProblem> selectAnswerList(Page<QuestProblem> page, QuestProblem questProblem);

	Integer updateAnsewrById(QuestProblem questProblem);

	Integer insertAnswer(QuestProblem questProblem);

	QuestProblem selectAnswerById(Integer id);

	Integer updateAnswerById(QuestProblem questProblem);

	List<Map<String,Object>> queryQuestExportData(ExportDto dto);
}