package com.bluekjg.admin.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.admin.mapper.AnalyticsMapper;
import com.bluekjg.admin.mapper.UserPortraitMapper;
import com.bluekjg.admin.model.Analytics;
import com.bluekjg.admin.model.UserPortrait;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.admin.service.IAnalyticsService;

@Service
public class AnalyticsServiceImpl extends ServiceImpl<AnalyticsMapper, Analytics> implements IAnalyticsService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private AnalyticsMapper mapper;
	@Autowired
	private UserPortraitMapper userPortraitMapper;

	@Override
	public void analyticsDataGrid(PageInfo pageInfo, Analytics analytics) {
		Page<Analytics> page = new Page<Analytics>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Analytics> analyticsList = mapper.analyticsDataGrid(page,analytics);
		List<UserPortrait> userPortraitList = userPortraitMapper.selectTop5(analytics);
		analytics.setCity(userPortraitList.get(0).getCity());
		List<Integer> top0 = userPortraitMapper.selectNumByCity(analytics);
		analytics.setCity(userPortraitList.get(1).getCity());
		List<Integer> top1 = userPortraitMapper.selectNumByCity(analytics);
		analytics.setCity(userPortraitList.get(2).getCity());
		List<Integer> top2 = userPortraitMapper.selectNumByCity(analytics);
		analytics.setCity(userPortraitList.get(3).getCity());
		List<Integer> top3 = userPortraitMapper.selectNumByCity(analytics);
		analytics.setCity(userPortraitList.get(4).getCity());
		List<Integer> top4 = userPortraitMapper.selectNumByCity(analytics);
		for(int i = 0;i<analyticsList.size();i++) {
			analyticsList.get(i).setTop0(top0.get(i).toString());
			analyticsList.get(i).setTop1(top1.get(i).toString());
			analyticsList.get(i).setTop2(top2.get(i).toString());
			analyticsList.get(i).setTop3(top3.get(i).toString());
			analyticsList.get(i).setTop4(top4.get(i).toString());
		}
		pageInfo.setRows(analyticsList);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void exportAnalytics(HttpServletResponse response, Analytics analytics) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("用户流量统计报表");
		sheet.createFreezePane(1, 0);
		Row headerRow = sheet.createRow(0);
		headerRow.setHeight((short) (25*25));
		
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 10);//设置字体大小
		font.setBold(true);
		style.setFont(font);
		
		HSSFCellStyle headStyle = workbook.createCellStyle();
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
		headStyle.setAlignment(HorizontalAlignment.CENTER);//水平
		font.setFontHeightInPoints((short) 10);
		font.setBold(true);
		headStyle.setFont(font);
		
		List<Analytics> list = mapper.analyticsDataGrid(analytics);
		List<UserPortrait> userPortraitList = userPortraitMapper.selectTop5(analytics);
		analytics.setCity(userPortraitList.get(0).getCity());
		List<Integer> top0 = userPortraitMapper.selectNumByCity(analytics);
		analytics.setCity(userPortraitList.get(1).getCity());
		List<Integer> top1 = userPortraitMapper.selectNumByCity(analytics);
		analytics.setCity(userPortraitList.get(2).getCity());
		List<Integer> top2 = userPortraitMapper.selectNumByCity(analytics);
		analytics.setCity(userPortraitList.get(3).getCity());
		List<Integer> top3 = userPortraitMapper.selectNumByCity(analytics);
		analytics.setCity(userPortraitList.get(4).getCity());
		List<Integer> top4 = userPortraitMapper.selectNumByCity(analytics);
		
		String[] head0 = new String[]{"日期","访问流量统计","访问流量统计","访问流量统计","访问流量统计","访问流量统计","访问流量来源"
				,"访问流量来源","访问流量来源","访问流量来源","用户活跃度","用户活跃度","用户活跃度","用户活跃度","用户活跃度","访问流量城市分布"};
		String[] headnum0 = new String[] { "0,1,0,0", "0,0,1,5", "0,0,6,9","0,0,10,14","0,0,15,19"};
		
		List<String> headList = new ArrayList<>();
		headList.add("访问次数");
		headList.add("打开次数");
		headList.add("访问人数");
		headList.add("人均停留时长(秒)");
		headList.add("次均停留时长(秒)");
		headList.add("直接访问");
		headList.add("搜索引擎");
		headList.add("分享");
		headList.add("其他");
		headList.add("分享次数");
		headList.add("分享人数");
		headList.add("活跃用户");
		headList.add("新用户数");
		headList.add("累计用户数");
		for(UserPortrait userPortrait: userPortraitList) {
			headList.add(userPortrait.getCity());
		}
		
		String[] head1 = new String[headList.size()];
		headList.toArray(head1);
		
		for(int i=0;i<head0.length;i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(head0[i]);
			cell.setCellStyle(headStyle);
		}
		for (int i = 0; i < headnum0.length; i++) {
            String[] temp = headnum0[i].split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,startcol, overcol));
        }
		
		headerRow = sheet.createRow(1);
		headerRow.setHeight((short) (25*25));
		for(int i=0;i<head1.length;i++) {
			Cell cell = headerRow.createCell(i+1);
			cell.setCellValue(head1[i]);
			cell.setCellStyle(headStyle);
			if(i==4||i==5) {
				sheet.setColumnWidth(i, 20*256);
			}else {
				sheet.setColumnWidth(i, 12*256);
			}
		}
		
		List<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
		for(int i=0;i<list.size();i++) {
			Analytics a = list.get(i);
			ArrayList<String> propertys = new ArrayList<String>();
			
			propertys.add(a.getRefDate()==null?"0":a.getRefDate().toString());
			propertys.add(a.getVisitPv()==null?"0":a.getVisitPv().toString());
			propertys.add(a.getSessionCnt()==null?"0":a.getSessionCnt().toString());
			propertys.add(a.getVisitUv()==null?"0":a.getVisitUv().toString());
			propertys.add(a.getStayTimeUv()==null?"0":a.getStayTimeUv().toString());
			propertys.add(a.getStayTimeSession()==null?"0":a.getStayTimeSession().toString());
			propertys.add(a.getSession()==null?"0":a.getSession().toString());
			propertys.add(a.getSearch()==null?"0":a.getSearch().toString());
			propertys.add(a.getShare()==null?"0":a.getShare().toString());
			propertys.add(a.getOther()==null?"0":a.getOther().toString());
			propertys.add(a.getSharePv()==null?"0":a.getSharePv().toString());
			propertys.add(a.getShareUv()==null?"0":a.getShareUv().toString());
			propertys.add(a.getVisitUv()==null?"0":a.getVisitUv().toString());
			propertys.add(a.getVisitUvNew()==null?"0":a.getVisitUvNew().toString());
			propertys.add(a.getVisitTotal()==null?"0":a.getVisitTotal().toString());
			
			propertys.add(top0.get(i).toString());
			propertys.add(top1.get(i).toString());
			propertys.add(top2.get(i).toString());
			propertys.add(top3.get(i).toString());
			propertys.add(top4.get(i).toString());
			
			dataList.add(propertys);
		}
		
		for(int i = 0;i<dataList.size();i++) {
			Row bodyRow = sheet.createRow(i+2);
			bodyRow.setHeight((short) (20*20));
			ArrayList<String> propertys = dataList.get(i);
			for(int j=0;j<propertys.size();j++) {
				Cell cell = bodyRow.createCell(j);
				cell.setCellValue(propertys.get(j));
			}
		}
		
		try {
			response.setHeader("content-disposition", "attachement;filename="+ new String(("用户流量统计报表.xls").getBytes("gb2312"), "ISO8859-1" ));
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
			workbook.close();
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}

	@Override
	public List<Analytics> loadOne(Analytics analytics) {
		return mapper.analyticsDataGrid(analytics);
	}

	@Override
	public List<UserPortrait> selectTop5(Analytics analytics) {
		return userPortraitMapper.selectTop5(analytics);
	}

	@Override
	public Analytics querySourceSum(Analytics analytics) {
		return mapper.querySourceSum(analytics);
	}

	@Override
	public List<Analytics> selectVisitUvNew(Analytics analytics) {
		return mapper.selectVisitUvNew(analytics);
	}
	
}
