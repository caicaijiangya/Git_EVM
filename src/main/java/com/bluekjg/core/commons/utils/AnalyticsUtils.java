package com.bluekjg.core.commons.utils;

import java.util.HashMap;
import java.util.Map;

import com.bluekjg.wxapp.utils.CommonUtil;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

import net.sf.json.JSONObject;

/*
 * 小程序统计分析服务
 */
public class AnalyticsUtils {
	
	public static String getResponse(String Url,String beginDate,String endDate) {
		String accessToken =getAccessToken();
		String requestUrl = Url.replace("ACCESS_TOKEN", accessToken);
		Map<String, String> param = new HashMap<String, String>();
		param.put("begin_date", beginDate);
		param.put("end_date", endDate);
		JSONObject jsonObject = CommonUtil.requestPostJSONObject(requestUrl, param);
		String statusCode = jsonObject.getString("statusCode");
		if(statusCode!=null&&statusCode.equals("OK")) {
			JSONObject body = jsonObject.getJSONObject("body");
			if(body.containsKey("errcode")) {
				throw new RuntimeException("数据分析接口错误,响应码:"+body.getString("errcode")+"---"+body.getString("errmsg"));
			}
			return jsonObject.getString("body");
		}else {
			throw new RuntimeException("网络超时!");
		}
	}

	/**
	 * 获取 access_token
	 */
	private static String getAccessToken() {
		String requestUrl = WxappConfigUtil.WX_TOKEN_URL.replace("APPID", WxappConfigUtil.WX_APPID).
                replace("SECRET", WxappConfigUtil.WX_SECRET);
		Map<String,String>  jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (jsonObject != null && jsonObject.get("access_token") != null) {
        	return jsonObject.get("access_token");
        }else {
        	throw new RuntimeException("获取accessToken失败");
        }
	}

	public static String getAnalysisDailySummary(String beginDate,String endDate) {
		return getResponse(WxappConfigUtil.DAILY_SUMMARY_URL,beginDate,endDate);
	}

	public static String getAnalysisDailyVisitTrend(String beginDate,String endDate) {
		return getResponse(WxappConfigUtil.DAILY_VISIT_TREND_URL,beginDate,endDate);
	}

	public static String getAnalysisMonthlyVisitTrend(String beginDate,String endDate) {
		return getResponse(WxappConfigUtil.MONTHLY_VISIT_TREND_URL,beginDate,endDate);
	}

	public static String getAnalysisWeeklyVisitTrend(String beginDate,String endDate) {
		return getResponse(WxappConfigUtil.WEEKLY_VISIT_TREND_URL,beginDate,endDate);
	}

	public static String getAnalysisUserPortrait(String beginDate,String endDate) {
		return getResponse(WxappConfigUtil.USER_PORTRAIT_URL,beginDate,endDate);
	}

	public static String getAnalysisVisitDistribution(String beginDate,String endDate) {
		return getResponse(WxappConfigUtil.VISIT_DISTRIBUTION_URL,beginDate,endDate);
	}

	public static String getAnalysisVisitPage(String beginDate,String endDate) {
		return getResponse(WxappConfigUtil.VISIT_PAGE_URL,beginDate,endDate);
	}
	
	public static String getAnalysisDailyRetain(String beginDate,String endDate) {
		return getResponse(WxappConfigUtil.DAILY_RETAIN_URL,beginDate,endDate);
	}
	
	public static String getAnalysisMonthlyRetain(String beginDate,String endDate) {
		return getResponse(WxappConfigUtil.MONTHLY_RETAIN_URL,beginDate,endDate);
	}
	
	public static String getAnalysisWeeklyRetain(String beginDate,String endDate) {
		return getResponse(WxappConfigUtil.WEEKLY_RETAIN_URL,beginDate,endDate);
	}

}
