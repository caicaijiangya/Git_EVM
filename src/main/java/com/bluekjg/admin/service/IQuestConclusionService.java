package com.bluekjg.admin.service;

import com.bluekjg.admin.model.QuestConclusion;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 问卷测试维度表 服务类
 * </p>
 *
 * @author Tom
 * @since 2018-10-11
 */
public interface IQuestConclusionService extends IService<QuestConclusion> {

	void selectDataGrid(PageInfo pageInfo, QuestConclusion questConclusion);
	
}
