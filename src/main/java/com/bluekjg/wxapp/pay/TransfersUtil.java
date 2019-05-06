package com.bluekjg.wxapp.pay;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletContext;

import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluekjg.core.commons.utils.ConstantUtils;
import com.bluekjg.core.utils.StringUtil;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

public class TransfersUtil {

	private static final Logger logger = LoggerFactory.getLogger(TransfersUtil.class);
	/**
	 * 退款操作
	 * @param xml
	 * @return
	 * ServletContext servletContext
	 */
	public static ResultBean refund(String xml) {
		ResultBean resultBean = new ResultBean();
		HttpPost httpost = getPostMethod(MdlPayUtil.REFUND_URL);
		try {
			//获得密匙库
			KeyStore keyStore  = KeyStore.getInstance("PKCS12");
			InputStream instream = new FileInputStream(new File(WxappConfigUtil.SERVICE_PAGE+MdlPayUtil.CERT_PATH));//servletContext.getResourceAsStream(MdlPayUtil.CERT_PATH);// 放退款证书的路径
			//密匙库的密码
			keyStore.load(instream, WxappConfigUtil.WX_MERCHANTS_APPID.toCharArray());
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore,  WxappConfigUtil.WX_MERCHANTS_APPID.toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
//			System.out.println("======>>>>xmlParam:"+xml);
			logger.info("退款请求的xml="+xml);
			httpost.setEntity(new StringEntity(xml, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			Map<String,String> map = doXMLParse(jsonStr);
			System.out.println("=====>>>>jsonStr:"+jsonStr);
			logger.info("退款请求返回的的jsonStr="+jsonStr);
			if (!StringUtil.isEmpty(map) && "SUCCESS".equals(map.get("result_code"))) {
				resultBean.setResult(ConstantUtils.SUCCESS);
			}else{
				resultBean.setResult(ConstantUtils.FAIL);
			}
			resultBean.setTitle(map.get("return_code"));
			resultBean.setInfo(map.get("return_msg"));
			return resultBean;
		} catch (Exception e) {
			resultBean.setResult(ConstantUtils.FAIL);
			logger.error(e.getMessage(),e);
		}
		return resultBean;
	}
	
	public static String initetransfersXml(WxTransfersDto transfersBean){
		// 随机字符串
		Random random = new Random();
		String randomStr = MD5.GetMD5String(String.valueOf(random.nextInt(10000)));
		String nonce_str = MD5Util.MD5Encode(randomStr, "utf-8").toLowerCase();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("mch_appid", transfersBean.getMch_appid());
		packageParams.put("mchid", transfersBean.getMchid());
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("partner_trade_no", transfersBean.getPartner_trade_no());
		packageParams.put("check_name", transfersBean.getCheck_name());
		packageParams.put("re_user_name", transfersBean.getRe_user_name());
		packageParams.put("amount", transfersBean.getAmount());
		packageParams.put("desc", transfersBean.getDesc());
		packageParams.put("openid", transfersBean.getOpenid());
		packageParams.put("spbill_create_ip", transfersBean.getSpbill_create_ip());
		RequestHandler reqHandler = new RequestHandler(null, null);
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>"
						+ "<mch_appid>" + transfersBean.getMch_appid() + "</mch_appid>"
						+ "<mchid>" + transfersBean.getMchid() + "</mchid>"
						+ "<nonce_str>" + nonce_str + "</nonce_str>"
						+ "<partner_trade_no>" + transfersBean.getPartner_trade_no() + "</partner_trade_no>"
						+ "<openid>" + transfersBean.getOpenid() + "</openid>"
						+ "<check_name>" + transfersBean.getCheck_name() + "</check_name>"
						+ "<re_user_name>" + transfersBean.getRe_user_name() + "</re_user_name>"
						+ "<amount>" + transfersBean.getAmount() + "</amount>"
						+ "<desc>"+ transfersBean.getDesc() +"</desc>"
						+ "<spbill_create_ip>"+ transfersBean.getSpbill_create_ip() +"</spbill_create_ip>"
						+ "<sign>" + sign + "</sign>"
					+ "</xml>";
		
		return xml;
	}
	
	
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map doXMLParse(String strxml) throws Exception {
		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map m = new HashMap();
		InputStream in = String2Inputstream(strxml);
		//SAXBuilder builder = new SAXBuilder();
		//Document doc = builder.build(in);
		Document doc = org.dom4j.DocumentHelper.parseText(strxml);
		Element root = doc.getRootElement();
		List list = root.elements();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.elements();
			if (children.isEmpty()) {
				v = e.getText();
			} else {
				v = getChildrenText(children);
			}

			m.put(k, v);
		}

		// 关闭流
		in.close();

		return m;
	}
	
	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (org.dom4j.Element) it.next();
				String name = e.getName();
				String value = e.getText();
				List list = e.elements();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}
	
	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
	
	public static String getRequestXml(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
	
	public static String createSign(String charSet, SortedMap<Object, Object> parameters, String mch_key) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + mch_key);
		String sign = MD5Util.MD5Encode(sb.toString(), charSet).toUpperCase();
		return sign;
	}
	
	
	/**
	 * 元转换成分
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if(amount==null){
			return "";
		}
		// 金额转化为分为单位
		String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if(index == -1){
			amLong = Long.valueOf(currency+"00");
		}else if(length - index >= 3){
			amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));
		}else if(length - index == 2){
			amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);
		}else{
			amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");
		}
		return amLong.toString();
	}
	
	/**
	 * 模拟浏览器post提交
	 * 
	 * @param url
	 * @return
	 */
	public static HttpPost getPostMethod(String url) {
		HttpPost pmethod = new HttpPost(url); // 设置响应头信息
		pmethod.addHeader("Connection", "keep-alive");
		pmethod.addHeader("Accept", "*/*");
		pmethod.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		pmethod.addHeader("Host", "api.mch.weixin.qq.com");
		pmethod.addHeader("X-Requested-With", "XMLHttpRequest");
		pmethod.addHeader("Cache-Control", "max-age=0");
		pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
		return pmethod;
	}
}
