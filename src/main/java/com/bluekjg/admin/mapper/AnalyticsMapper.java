package com.bluekjg.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bluekjg.admin.model.Analytics;
import com.bluekjg.admin.model.AnalyticsPage;

public interface AnalyticsMapper extends BaseMapper<Analytics> {

	List<Analytics> analyticsDataGrid(Page<Analytics> page, Analytics analytics);

	List<Analytics> analyticsDataGrid(Analytics analytics);
	
	/**
	 * 统计一段时间内的访问流量来源
	 */
	Analytics querySourceSum(Analytics analytics);

	List<Analytics> selectVisitUvNew(Analytics analytics);

	/**
	 * 批量插入 t_evm_analytics_page
	 */
	void insertPageBatch(List<AnalyticsPage> list);
	
	/**
	 * 验证是否已经插入当日数据 
	 */
	Integer selectCountByRefDate(@Param("refDate")String refDate);

}
