package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.QuestDimension;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 问卷测试维度表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-10-11
 */
public interface QuestDimensionMapper extends BaseMapper<QuestDimension> {
	List<QuestDimension> selectDataGrid(Page<QuestDimension> page, QuestDimension questDimension);
	List<QuestDimension> selectDimensionList();

}