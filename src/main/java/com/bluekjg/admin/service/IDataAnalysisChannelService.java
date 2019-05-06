package com.bluekjg.admin.service;

import com.bluekjg.admin.model.DataAnalysisDto;
import com.bluekjg.core.commons.result.PageInfo;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 数据分析-渠道 服务类
 * </p>
 *
 * @author Tom
 * @since 2019-03-01
 */
public interface IDataAnalysisChannelService extends IService<DataAnalysisDto> {

	void selectOddDataGrid(PageInfo pageInfo, DataAnalysisDto channel);
	
	List<Map<String,Object>> selectOddGraphics(DataAnalysisDto channel);
	
	void downLoadOddChannel(DataAnalysisDto channel,HttpServletResponse response);
	
	void selectMoreDataGrid(PageInfo pageInfo, DataAnalysisDto channel);
	
	List<Map<String,Object>> selectMoreGraphics(DataAnalysisDto channel);
	
	List<Map<String,Object>> selectMoreRateDataGrid(DataAnalysisDto channel);
	
	void downLoadMoreChannel(DataAnalysisDto channel,HttpServletResponse response);
	
	void dataProcessing(List<Map<String,Object>> list,List<Map<String,Object>> dataList);
}
