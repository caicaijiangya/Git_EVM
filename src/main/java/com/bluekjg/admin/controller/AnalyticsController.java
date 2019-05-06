package com.bluekjg.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluekjg.admin.model.Analytics;
import com.bluekjg.admin.model.UserPortrait;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.admin.service.IAnalyticsService;
import com.bluekjg.wxapp.service.IWxTestTaskService;

@Controller
@RequestMapping("analytics")
public class AnalyticsController {
	
	@Autowired
	private IWxTestTaskService taskService;
	@Autowired
	private IAnalyticsService service;
	
    @GetMapping("manager")
    public String manager() {
        return "admin/analytics/list";
    }

    @GetMapping("dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Analytics analytics, Integer page, Integer rows) {
    	if(analytics.getFormat() == null) {analytics.setFormat("%Y");}
    	PageInfo pageInfo = new PageInfo(page, rows);
    	service.analyticsDataGrid(pageInfo, analytics);
        return pageInfo;
    }
	
    @GetMapping("exportAnalytics")
	public void exportAnalytics(Analytics analytics,HttpServletResponse response) {
    	if(analytics.getFormat() == null) {analytics.setFormat("%Y");}
    	service.exportAnalytics(response,analytics);
	}
    
    @GetMapping("selectTop5")
    @ResponseBody
    public List<String> selectTop5(Analytics analytics) {
    	List<UserPortrait> list = service.selectTop5(analytics);
		List<String> topList =new ArrayList<>();
		for(UserPortrait userPortrait:list) {
			topList.add(userPortrait.getCity());
		}
		return topList;
	}
    
    @GetMapping("showCharts")
    public String showCharts() {
        return "admin/analytics/charts";
    }
    
    @GetMapping("loadOne")
    @ResponseBody
    public Map<String, List<String>> loadOne(Analytics analytics) {
    	if(analytics.getFormat() == null) {analytics.setFormat("%Y");}
    	List<Analytics> list = service.loadOne(analytics);
    	Map<String, List<String>> map = new HashMap<>();
    	List<String> refDateList = new ArrayList<String>();
    	List<String> visitPvList = new ArrayList<String>();
    	List<String> sessionCntList = new ArrayList<String>();
    	List<String> visitUvList = new ArrayList<String>();
    	List<String> stayTimeUvList = new ArrayList<String>();
    	List<String> stayTimeSessionList = new ArrayList<String>();
    	for(Analytics a: list) {
    		refDateList.add(a.getRefDate());
    		visitPvList.add(a.getVisitPv()==null?"0":a.getVisitPv().toString());
    		sessionCntList.add(a.getSessionCnt()==null?"0":a.getSessionCnt().toString());
    		visitUvList.add(a.getVisitUv()==null?"0":a.getVisitUv().toString());
    		stayTimeUvList.add(a.getStayTimeUv()==null?"0":a.getStayTimeUv().toString());
    		stayTimeSessionList.add(a.getStayTimeSession()==null?"0":a.getStayTimeSession().toString());
    	}
    	map.put("refDate", refDateList);
    	map.put("visitPv", visitPvList);
    	map.put("sessionCnt", sessionCntList);
    	map.put("visitUv", visitUvList);
    	map.put("stayTimeUv", stayTimeUvList);
    	map.put("stayTimeSession", stayTimeSessionList);
    	
    	return map;
    }
    
    @GetMapping("loadTwo")
    @ResponseBody
    public Map<String, Object> loadTwo(Analytics analytics) {
    	List<UserPortrait> list = service.selectTop5(analytics);
    	Map<String, Object> map = new HashMap<>();
    	List<String> cityList = new ArrayList<String>();
    	List<String> numList = new ArrayList<String>();
    	for(UserPortrait up: list) {
    		cityList.add(up.getCity());
    		numList.add(up.getSum().toString());
    	}
    	String start = analytics.getStartDate()==null?"2018":analytics.getStartDate();
    	String end = analytics.getEndDate()==null?"2099":analytics.getEndDate();
    	map.put("date", start + "-" + end);
    	map.put("cityList", cityList);
    	map.put("numList", numList);
    	return map;
    }
    
    @GetMapping("loadThree")
    @ResponseBody
    public Analytics loadThree(Analytics analytics) {
    	return service.querySourceSum(analytics);
    }
    
    @GetMapping("loadFour")
    @ResponseBody
    public Map<String, Object> loadFour(Analytics analytics) {
    	if(analytics.getFormat() == null) {analytics.setFormat("%Y");}
    	List<Analytics> list = service.selectVisitUvNew(analytics);
    	Map<String, Object> map = new HashMap<>();
    	List<String> dateList = new ArrayList<String>();
    	List<Integer> numList = new ArrayList<Integer>();
    	for(Analytics a: list) {
    		dateList.add(a.getRefDate());
    		numList.add(a.getVisitUvNew());
    	}
    	map.put("dateList", dateList);
    	map.put("numList", numList);
    	return map;
    }
    
	@GetMapping("syncData")
	public void syncData() {
		taskService.updateAnalyticsPage();
//		taskService.updateAnalytics();
//		taskService.updateUserPortrait();
	}
//	
//	@GetMapping("getAnalysisDailyRetain")
//	public String getAnalysisDailyVisitTrend() {
//		return AnalyticsUtils.getAnalysisDailyRetain("20190302", "20190302");
//	}
//	
//	@GetMapping("getAnalysisMonthlyVisitTrend")
//	public String getAnalysisMonthlyVisitTrend() {
//		return AnalyticsUtils.getAnalysisMonthlyVisitTrend("20190201", "20190228");
//	}
//	
//	@GetMapping("getAnalysisWeeklyVisitTrend")
//	public String getAnalysisWeeklyVisitTrend() {
//		return AnalyticsUtils.getAnalysisWeeklyVisitTrend("20190218", "20190224");
//	}
//	
//	@GetMapping("getAnalysisUserPortrait")
//	@ResponseBody
//	public String getAnalysisUserPortrait() {
//		return AnalyticsUtils.getAnalysisUserPortrait("20190303", "20190303");
//	}
//	
//	@GetMapping("getAnalysisVisitDistribution")
//	public String getAnalysisVisitDistribution() {
//		return AnalyticsUtils.getAnalysisVisitDistribution("20190228", "20190228");
//	}
//	
//	@GetMapping("getAnalysisVisitPage")
//	public String getAnalysisVisitPage() {
//		return AnalyticsUtils.getAnalysisVisitPage("20190228", "20190228");
//	}
	
}
