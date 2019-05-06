package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.DataAnalysisDto;
import com.bluekjg.admin.mapper.DataAnalysisChannelMapper;
import com.bluekjg.admin.service.IDataAnalysisChannelService;
import com.bluekjg.admin.upload.ExcelUtil;
import com.bluekjg.admin.upload.WriteExcelInterface;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.DateUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据分析-渠道 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2019-03-01
 */
@Service
public class DataAnalysisChannelServiceImpl extends ServiceImpl<DataAnalysisChannelMapper, DataAnalysisDto> implements IDataAnalysisChannelService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired private DataAnalysisChannelMapper dataAnalysisChannelMapper;

	@Override
	public void selectOddDataGrid(PageInfo pageInfo, DataAnalysisDto channel) {
		Page<DataAnalysisDto> page = new Page<DataAnalysisDto>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Map<String,Object>> list = dataAnalysisChannelMapper.selectOddDataGrid(page,channel);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	@Override
	public List<Map<String,Object>> selectOddGraphics(DataAnalysisDto channel) {
		List<Map<String,Object>> list = dataAnalysisChannelMapper.selectOddDataGrid(channel);
		return list;
	}
	@Override
	public void downLoadOddChannel(DataAnalysisDto channel,HttpServletResponse response) {
		List<Map<String,Object>> list = dataAnalysisChannelMapper.selectOddDataGrid(channel);
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "日期", "dateTime" });
		metadata.add(new String[] { "订单标准金额", "totalBalances" });
		metadata.add(new String[] { "优惠金额", "discoutPrice" });
		metadata.add(new String[] { "销售额", "transBalances" });
		metadata.add(new String[] { "销量", "transGoodsNum" });
		metadata.add(new String[] { "订单量", "orderNum" });
		metadata.add(new String[] { "客人数", "userNum" });
		metadata.add(new String[] { "客单件", "userOddNum" });
		metadata.add(new String[] { "客单价", "userOddPrice" });
		metadata.add(new String[] { "实付折扣", "discount" });
		metadata.add(new String[] { "退货数量", "refundNum" });
		metadata.add(new String[] { "退货金额", "refundBalances" });
		metadata.add(new String[] { "退货率", "refundRate" });
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("单渠道统计");
			name.append(DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"));
			name.append(".xls");
			response.setHeader("Content-Disposition", "attachment; filename="+ new String(name.toString().getBytes(), "ISO8859-1"));
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	@Override
	public void selectMoreDataGrid(PageInfo pageInfo, DataAnalysisDto channel) {
		Page<DataAnalysisDto> page = new Page<DataAnalysisDto>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Map<String,Object>> list = dataAnalysisChannelMapper.selectMoreDataGrid(page,channel);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	@Override
	public List<Map<String, Object>> selectMoreGraphics(DataAnalysisDto channel) {
		List<Map<String,Object>> list = dataAnalysisChannelMapper.selectMoreDataGrid(channel);
		return list;
	}
	@Override
	public List<Map<String, Object>> selectMoreRateDataGrid(DataAnalysisDto channel) {
		// TODO Auto-generated method stub
		return dataAnalysisChannelMapper.selectMoreRateDataGrid(channel);
	}
	@Override
	public void downLoadMoreChannel(DataAnalysisDto channel,HttpServletResponse response) {
		List<Map<String,Object>> list = dataAnalysisChannelMapper.selectMoreDataGrid(channel);
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		List<String[]> metadata = new ArrayList<String[]>();
		List<String> storeNames = new ArrayList<String>();
		if(list != null && list.size() > 0) {
			for(Map<String,Object> map0:list) {
				boolean bool = true;
				if(storeNames != null && storeNames.size() > 0) {
					for(String storeName:storeNames) {
						if(map0.get("storeName").toString().equals(storeName)) {
							bool = false;
							break;
						}
					}
					
				}
				if(bool) {
					storeNames.add(map0.get("storeName").toString());
				}
			}
			
			metadata.add(new String[] { "日期", "dateTime" });
			metadata.add(new String[] { "粉丝数", "fans" });
			metadata.add(new String[] { "新增粉丝数", "newFans" });
			
			Map<String,Object> data0 = new HashMap<String,Object>();
			data0.put("dateTime", "");
			data0.put("fans", 0);
			data0.put("newFans", 0);
			if(storeNames != null && storeNames.size() > 0) {
				for(int i=1;i<storeNames.size()+1;i++) {
					int size = i * 2;
					data0.put("balances"+(size + 2), "销量");
					data0.put("balancesRate"+(size + 3), "销量占比");
					metadata.add(new String[] { storeNames.get((i-1)), "balances"+(size + 2) });
					metadata.add(new String[] { "", "balancesRate"+(size + 3) });
				}
			}
			dataList.add(data0);
			dataProcessing(list,dataList);
		}
		
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, dataList,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		HSSFSheet sheet = workbook.getSheetAt(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
		if(storeNames != null && storeNames.size() > 0) {
			int size = 2;
			for(int i=0;i<storeNames.size();i++) {
				sheet.addMergedRegion(new CellRangeAddress(0, 0, (size + 1), (size + 2)));
				size = size + 2;
			}
		}
		
		try {
			StringBuffer name = new StringBuffer();
			name.append("多渠道统计");
			name.append(DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"));
			name.append(".xls");
			response.setHeader("Content-Disposition", "attachment; filename="+ new String(name.toString().getBytes(), "ISO8859-1"));
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	
	@Override
	public void dataProcessing(List<Map<String,Object>> list,List<Map<String,Object>> dataList) {
		List<String> storeNames = new ArrayList<String>();
		for(Map<String,Object> map:list) {
			Map<String,Object> data = new HashMap<String,Object>();
			boolean bool = true;
			if(dataList != null && dataList.size() > 0) {
				for(Map<String,Object> map1:dataList) {
					if(map.get("dateTime").toString().equals(map1.get("dateTime").toString())) {
						bool = false;
						break;
					}
				}
			}
			if(bool == true) {
				data.put("dateTime", map.get("dateTime"));
				data.put("fans", map.get("fans"));
				data.put("newFans", map.get("newFans"));
				dataList.add(data);
			}
			
			
			bool = true;
			if(storeNames != null && storeNames.size() > 0) {
				for(String storeName:storeNames) {
					if(map.get("storeName").toString().equals(storeName)) {
						bool = false;
						break;
					}
				}
				
			}
			if(bool) {
				storeNames.add(map.get("storeName").toString());
			}
		}
		for(Map<String,Object> map:list) {
			if(dataList != null && dataList.size() > 0) {
				for(int i=0;i<dataList.size();i++) {
					Map<String,Object> map1 = dataList.get(i);
					if(map.get("dateTime").toString().equals(map1.get("dateTime").toString())) {
						if(storeNames != null && storeNames.size() > 0) {
							for(int j=1;j<storeNames.size()+1;j++) {
								int size = j * 2;
								if(map.get("storeName").toString().equals(storeNames.get((j-1)).toString())) {
									dataList.get(i).put("storeName" + (size + 2), map.get("storeName"));
									dataList.get(i).put("balances" + (size + 2), map.get("balances"));
									dataList.get(i).put("balancesRate" + (size + 3), map.get("balancesRate"));
								}
							}
						}
						
					}
				}
			}
		}
	}
	
}
