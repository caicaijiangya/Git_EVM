package com.bluekjg.wxapp.pay;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import net.sf.json.JSONObject;

/**
 * 微信调起支付类
 * 
 * @author max
 * 
 */
public class WXPay {
	//支付
	public static SortedMap<String, String> createPackageValue(String appid, String appKey, String prepay_id)  {
		SortedMap<String, String> nativeObj = new TreeMap<String, String>();
		nativeObj.put("appId", appid);
		nativeObj.put("timeStamp", OrderUtil.GetTimestamp());
		Random random = new Random();
		String randomStr = MD5.GetMD5String(String.valueOf(random.nextInt(10000)));
		nativeObj.put("nonceStr", MD5Util.MD5Encode(randomStr, "utf-8").toLowerCase());
		nativeObj.put("package", "prepay_id=" + prepay_id);
		nativeObj.put("signType", "MD5");
		nativeObj.put("paySign", createSign(nativeObj, appKey));
		System.out.println(JSONObject.fromObject(nativeObj).toString());
		return nativeObj;
	}
	//退款
	public static SortedMap<String, String> createRefundPackageValue(String appid, String appKey, WXRefundPrepay refund)  {
		SortedMap<String, String> nativeObj = new TreeMap<String, String>();
		nativeObj.put("appId", appid);
		nativeObj.put("mch_id", refund.getMch_id());
		Random random = new Random();
		String randomStr = MD5.GetMD5String(String.valueOf(random.nextInt(10000)));
		nativeObj.put("nonce_str", MD5Util.MD5Encode(randomStr, "utf-8").toLowerCase());
		nativeObj.put("out_trade_no", refund.getOut_trade_no());
		nativeObj.put("out_refund_no", refund.getOut_refund_no());
		nativeObj.put("total_fee", refund.getTotal_fee());
		nativeObj.put("refund_fee", refund.getRefund_fee());
		nativeObj.put("refund_fee_type", refund.getRefund_fee_type());
		nativeObj.put("refund_desc", refund.getRefund_desc());
		nativeObj.put("refund_account", refund.getRefund_account());
		nativeObj.put("notify_url", refund.getNotify_url());
		nativeObj.put("sign_type", "MD5");
		nativeObj.put("sign", createSign(nativeObj, appKey));
		System.out.println(JSONObject.fromObject(nativeObj).toString());
		return nativeObj;
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
}
