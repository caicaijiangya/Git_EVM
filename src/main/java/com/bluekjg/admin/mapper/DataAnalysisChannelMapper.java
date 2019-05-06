package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.DataAnalysisDto;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 数据分析-渠道 Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2019-03-01
 */
public interface DataAnalysisChannelMapper extends BaseMapper<DataAnalysisDto> {

	List<Map<String,Object>> selectOddDataGrid(Page<DataAnalysisDto> page,DataAnalysisDto channel);
	List<Map<String,Object>> selectOddDataGrid(DataAnalysisDto channel);
	
	List<Map<String,Object>> selectMoreDataGrid(Page<DataAnalysisDto> page,DataAnalysisDto channel);
	List<Map<String,Object>> selectMoreDataGrid(DataAnalysisDto channel);
	List<Map<String,Object>> selectMoreRateDataGrid(DataAnalysisDto channel);
}