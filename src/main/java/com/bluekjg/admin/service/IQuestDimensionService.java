package com.bluekjg.admin.service;

import com.bluekjg.admin.model.QuestDimension;
import com.bluekjg.admin.model.QuestGoods;
import com.bluekjg.core.commons.result.PageInfo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 问卷测试维度表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-10-11
 */
public interface IQuestDimensionService extends IService<QuestDimension> {

	void selectDataGrid(PageInfo pageInfo, QuestDimension questDimension);
	List<QuestDimension> selectDimensionList();
	
}
