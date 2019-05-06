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
 * 扫码支付
 * @author tim
 *
 */
public class MicropayUtil {
	
	private static final Logger log = LoggerFactory.getLogger(MicropayUtil.class);
	
	// 
	public final static String GET_MICROPAY_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/micropay";

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
			packageParams.put("attach", "2222");
			packageParams.put("body", "333");
			packageParams.put("mch_id", WxappConfigUtil.WX_MERCHANTS_APPID);
			//packageParams.put("detail", "<![CDATA[{\"goods_detail\":[{\"goods_id\":\"iphone6s_16G\",\"wxpay_goods_id\":\"1001\",\"goods_name\":\"iPhone6s 16G\",\"quantity\":1,\"price\":528800,\"goods_category\":\"123456\",\"body\":\"abc\"},{\"goods_id\":\"iphone6s_32G\",\"wxpay_goods_id\":\"1002\",\"goods_name\":\"iPhone6s 32G\",\"quantity\":1,\"price\":608800,\"goods_category\":\"123789\",\"body\":\"abc\"}]}]]>");
			packageParams.put("nonce_str", WxappConfigUtil.WX_SECRET);
			packageParams.put("notify_url", "http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php");
			packageParams.put("openid", "o3YrW5QdOsfvixx32EtIuP0queRw");
			packageParams.put("out_trade_no", WxappConfigUtil.WX_GRANTTYPE);
			packageParams.put("spbill_create_ip", "36.5.157.234");
			packageParams.put("total_fee", "502");
			packageParams.put("trade_type", "JSAPI");
			String sign = createSign(packageParams, WxappConfigUtil.WX_MERCHANTS_SECRET);
			StringBuilder xml = new StringBuilder();
			xml.append("<xml>").append("\n");
			xml.append("<appid>"+WxappConfigUtil.WX_APPID+"</appid>").append("\n");
			xml.append("<attach>2222</attach>").append("\n");
			xml.append("<body>333</body>").append("\n");
			xml.append("<mch_id>"+WxappConfigUtil.WX_MERCHANTS_APPID+"</mch_id>").append("\n");
			//xml.append("<detail><![CDATA[{\"goods_detail\":[{\"goods_id\":\"iphone6s_16G\",\"wxpay_goods_id\":\"1001\",\"goods_name\":\"iPhone6s 16G\",\"quantity\":1,\"price\":528800,\"goods_category\":\"123456\",\"body\":\"abc\"},{\"goods_id\":\"iphone6s_32G\",\"wxpay_goods_id\":\"1002\",\"goods_name\":\"iPhone6s 32G\",\"quantity\":1,\"price\":608800,\"goods_category\":\"123789\",\"body\":\"abc\"}]}]]></detail>").append("\n");
			xml.append("<nonce_str>"+WxappConfigUtil.WX_SECRET+"</nonce_str>").append("\n");
			xml.append("<notify_url>http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php</notify_url>").append("\n");
			xml.append("<openid>o3YrW5QdOsfvixx32EtIuP0queRw</openid>").append("\n");
			xml.append("<out_trade_no>"+WxappConfigUtil.WX_GRANTTYPE+"</out_trade_no>").append("\n");
			xml.append("<spbill_create_ip>36.5.157.234</spbill_create_ip>").append("\n");
			xml.append("<total_fee>502</total_fee>").append("\n");
			xml.append("<trade_type>JSAPI</trade_type>").append("\n");
			xml.append("<sign>239EF1BD465BEF9AFE1F0E98DF5B897F</sign>").append("\n");
			xml.append("</xml>");
			System.out.println(xml.toString());
			String result = HttpClientWX.httpRequestStr(GET_MICROPAY_URL, "POST", xml.toString());
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
		WxappConfigUtil.WX_GRANTTYPE = "2018102141967100002";//支付订单号（一次生成）
		WxappConfigUtil.WX_APPID = "wxa0e6d57953e42b52";
		WxappConfigUtil.WX_SECRET = "cce5650236204594b656839c92f95143";//随机生成（一次生成）
		WxappConfigUtil.WX_MERCHANTS_APPID = "1500047572";
		WxappConfigUtil.WX_MERCHANTS_SECRET = "72df524593c2c8a0b347c95642f6b49c";//调用getsignkey（一次生成）
		getSignKey();
	}

}
