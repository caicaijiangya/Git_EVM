package com.bluekjg.admin.sjljj.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluekjg.core.wx.HttpClientWX;
import com.bluekjg.core.wx.MD5Util;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 下载对账单
 * @author tim
 *
 */
public class DownloadUtil {
	
	private static final Logger log = LoggerFactory.getLogger(DownloadUtil.class);
	
	// 
	public final static String GET_DOWNLOAD_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/downloadbill";

	/**
	 * 
	 * f1901f55453b53896bdfa306c44b4e68
	 * 
	 * @return
	 */
	public static synchronized String getSignKey() {
		try {
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("appid", WxappConfigUtil.WX_APPID);
			packageParams.put("bill_date", "20181023");
			packageParams.put("bill_type", "ALL");
			packageParams.put("mch_id", WxappConfigUtil.WX_MERCHANTS_APPID);
			packageParams.put("nonce_str", WxappConfigUtil.WX_SECRET);
			String sign = createSign(packageParams, WxappConfigUtil.WX_MERCHANTS_SECRET);
			StringBuilder xml = new StringBuilder();
			xml.append("<xml>").append("\n");
			xml.append("<appid>"+WxappConfigUtil.WX_APPID+"</appid>").append("\n");
			xml.append("<bill_date>20181023</bill_date>").append("\n");
			xml.append("<bill_type>ALL</bill_type>").append("\n");
			xml.append("<mch_id>"+WxappConfigUtil.WX_MERCHANTS_APPID+"</mch_id>").append("\n");
			xml.append("<nonce_str>"+WxappConfigUtil.WX_SECRET+"</nonce_str>").append("\n");
			xml.append("<sign>"+sign+"</sign>").append("\n");
			xml.append("</xml>");
			System.out.println(xml.toString());
			String result = HttpClientWX.httpRequestStr(GET_DOWNLOAD_URL, "POST", xml.toString());
			System.out.println(result);
		} catch (Exception e) {
			log.info("执行HttpClientWX中获取优惠券信息  异常=" + e.getMessage());
		}
		return null;
	}

	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	public static String createSign(SortedMap<String, String> packageParams, String AppKey) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + AppKey);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign;
	}

	public static void main(String[] args) {
		WxappConfigUtil.WX_GRANTTYPE = "2018102141967100001";//支付订单号（一次生成）
		WxappConfigUtil.WX_APPID = "wxe0439672492bd6ec";
		WxappConfigUtil.WX_SECRET = "cce5650236204594b656839c92f95143";//随机生成（一次生成）
		WxappConfigUtil.WX_MERCHANTS_APPID = "1500047572";
		WxappConfigUtil.WX_MERCHANTS_SECRET = "72df524593c2c8a0b347c95642f6b49c";//调用getsignkey一次生成
		getSignKey();
	}

}
