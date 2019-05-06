package com.bluekjg.wxapp.utils;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bluekjg.core.wx.HttpUtils;

import net.sf.json.JSONObject;

public class AliApiUtil {
	public static Logger logger = LogManager.getLogger(AliApiUtil.class.getName());
	private static final String APIURL = "https://wuliu.market.alicloudapi.com";
	/**
	 * 查询阿里云物流信息
	 * @param no 运单号
	 * @return
	 */
	public static JSONObject kdi(String no) {
		JSONObject json = null;
	    String path = "/kdi";
	    String method = "GET";
	    String appcode = "ca78ce544ab44728afe7974a30d92a71";  // !!!替换填写自己的AppCode 在买家中心查看
	    Map<String, String> headers = new HashMap<String, String>();
	    headers.put("Authorization", "APPCODE " + appcode); 
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("no", no);// !!! 请求参数
	    //快递公司字母简写：不知道可不填 95%能自动识别，填写查询速度会更快【见产品详情】
	    //querys.put("type", "zto");// !!! 请求参数
	    try {
	    	HttpResponse response = HttpUtils.doGet(APIURL, path, method, headers, querys);
            //System.out.println(response.toString()); //输出头部
	    	String jsonStr = EntityUtils.toString(response.getEntity());
	    	if(jsonStr != null && jsonStr.length() > 0) {
	    		json = JSONObject.fromObject(jsonStr);
	    	}
	    } catch (Exception e) {
	        logger.error(e.getMessage(),e);
	    }
	    return json;
	}
}


