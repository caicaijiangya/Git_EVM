package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.QuestConclusion;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 问卷测试结论表 Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2018-10-11
 */
public interface QuestConclusionMapper extends BaseMapper<QuestConclusion> {
	List<QuestConclusion> selectDataGrid(Page<QuestConclusion> page, QuestConclusion questConclusion);

}