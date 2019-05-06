package com.bluekjg.wxapp.erp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpUtil {

	private static Logger logger = LogManager.getLogger((new Object() {
		public String getClassName() {
			String className = this.getClass().getName();
			return className.substring(0, className.indexOf("$"));
		}
	}).getClassName());

	public static String sendPost(String url, String data) {
		logger.info("url: " + url);
		logger.info("request: " + data);
		String response = null;
		try {
			CloseableHttpClient httpclient = null;
			CloseableHttpResponse httpresponse = null;
			try {
				httpclient = HttpClients.createDefault();
				HttpPost httppost = new HttpPost(url);
				 StringEntity stringentity = new StringEntity(URLEncoder.encode(data,"utf-8"),
				 ContentType.create("text/json", "UTF-8"));
				httppost.setEntity(stringentity);
				httpresponse = httpclient.execute(httppost);
				response = EntityUtils.toString(httpresponse.getEntity());
				logger.info("response: " + response);
				
			} finally {
				if (httpclient != null) {
					httpclient.close();
				}
				if (httpresponse != null) {
					httpresponse.close();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return response;
	}
	
	public static void sendPost(String url, String appkey, String sessionkey,
			String secret, String version, String method, String data) {
		logger.info("data: " + data);
		logger.info("url: " + url);
		StringBuffer requestContent = new StringBuffer();
		requestContent.append("appkey=");
		requestContent.append(appkey);
		requestContent.append("&sessionkey=");
		requestContent.append(sessionkey);
		requestContent.append("&sign=");
		requestContent.append(SignUtil.sign(data, secret));
		requestContent.append("&version=");
		requestContent.append(version);
		requestContent.append("&method=");
		requestContent.append(method);
		requestContent.append("&data=");
		try {
			requestContent.append(URLEncoder.encode(data, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
		}
		logger.info("request: " + requestContent.toString());
		try {
			CloseableHttpClient httpclient = null;
			CloseableHttpResponse httpresponse = null;
			try {
				httpclient = HttpClients.createDefault();
				HttpPost httppost = new HttpPost(url);
				StringEntity stringentity = new StringEntity(
						requestContent.toString(), ContentType.create(
								"text/plain", "UTF-8"));
				httppost.setEntity(stringentity);
				httpresponse = httpclient.execute(httppost);
				String response = EntityUtils
						.toString(httpresponse.getEntity());
				logger.info("response: " + response);
			} finally {
				if (httpclient != null) {
					httpclient.close();
				}
				if (httpresponse != null) {
					httpresponse.close();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
