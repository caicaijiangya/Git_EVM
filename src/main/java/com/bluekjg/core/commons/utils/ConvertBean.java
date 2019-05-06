package com.bluekjg.core.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Clob;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



public class ConvertBean {
	
	public static Logger logger = LogManager.getLogger(ConvertBean.class.getName());
	private static final String DEFAULT_CHARSETNAME = "utf-8";
	
	/**
	 * 将字符串用BASE64编码
	 * @param s
	 * @return
	 */
	public static String base64Encode(String s) {
		if (s == null) {
			return null;
		}
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}
	
	
	/**
	 * base64编码，从byte数组到字符串
	 * @param byte[]
	 * @return String
	 */
	public static String base64Encode(byte[] b) {
		if (b == null) {
			return "";
		}
		BASE64Encoder encoder = new BASE64Encoder();
		try {
			String result = encoder.encode(b);
			return result;
		} catch (Exception e) {
			return "";
		}
	}
	
	
	/**
	 * 将被BASE64编码的字符串解码 
	 * @param s
	 * @return
	 */
	public static String base64Decode(String s) {
		if (s == null) {
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 将被BASE64编码的字符串解码 
	 * @param s
	 * @param encoding 编码格式
	 * @return
	 */
	public static String base64Decode(String s,String encoding) {
		if (s == null) {
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b,encoding);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 将被BASE64编码的字符串解码 
	 * @param s
	 * @return byte[]
	 */
	public static byte[] base64DecodeToByte(String s) {
		BASE64Decoder decoder;
		if (s == null) {
			return null;
		}
		decoder = new BASE64Decoder();
		try {
			byte b[] = decoder.decodeBuffer(s);
			return b;
		} catch (Exception ie) {
			return null;
		}
	}


	/**
	 * 方法功能: 将其他编码格式的数据串转换为 ISO-8859-1 编码格式 1) jdk是按照 ISO-8859-1格式的编译java原文件的.
	 * @return String
	 */
	public static String ISO88591TOGBK(String temp) {
		if (temp == null) {
			return null;
		}
		byte byteTemp[] = null;
		try {
			byteTemp = temp.getBytes("ISO-8859-1");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return new String(byteTemp);
	}

	
	/**
	 * urlEncode 一次
	 * @param value
	 * @param charsetName 编码格式
	 * @return
	 */
	public static String urlEncode(String value, String charsetName) {
		try {
			return java.net.URLEncoder.encode(value, charsetName);
		} catch (UnsupportedEncodingException e) {
		}
		return "";
	}
	
	/**
	 * urlEncode 一次
	 * @param value
	 * @return
	 */
	public static String urlEncode(String value) {
		return urlEncode(value, DEFAULT_CHARSETNAME);
	}
	
	
	/**
	 * urlEncode 两次
	 * @param value
	 * @param charsetName 编码格式
	 * @return
	 */
	public static String twoTimesEncode(String value, String charsetName) {
		return urlEncode(urlEncode(value, charsetName), charsetName);
	}
	
	/**
	 * urlEecode两次
	 * @param value
	 * @return
	 */
	public static String twoTimesEncode(String value) {
		return twoTimesEncode(value, DEFAULT_CHARSETNAME);
	}
	
	/**
	 * urlDecode一次
	 * @param value
	 * @param charsetName
	 * @return
	 */
	public static String urlDecode(String value, String charsetName) {
		if (StringUtils.isBlank(value)) {
			return "";
		}
		if (StringUtils.isBlank(charsetName)) {
			charsetName = DEFAULT_CHARSETNAME;
		}
		try {
			return java.net.URLDecoder.decode(value, charsetName);
		} catch (UnsupportedEncodingException e) {
			// 不处理
		}
		return "";
	}
	
	/**
	 * urlDecode一次
	 * @param value
	 * @return
	 */
	public static String urlDecode(String value) {
		return urlDecode(value, DEFAULT_CHARSETNAME);
	}

	/**
	 * urlDecode两次
	 * @param value
	 * @return
	 */
	public static String twoTimesDecode(String value) {
		return urlDecode(urlDecode(value));
	}
	
	
	protected static  String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	
	/**
	 * md5加密
	 * @param password
	 * @return
	 */
	public static String md5(String password){
		if(password==null||"".equals(password)){
			return null;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(password.getBytes());
		    return byte2hex(bytes);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	public static boolean checkValue(String desString,String checkValue) {
		String value = md5(desString);
		System.out.println("服务端签名前字符:" + desString + "|服务端签名后字符:" + value+"|请求checkValue字符:" + checkValue);
		if (checkValue.equalsIgnoreCase(value)) {
			System.out.println("签名验证通过");
			return true;
		}
		System.out.println("签名验证失败");
		return false;
	}
	
	/**
	 * 用户密码DES解密
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String userPwdDesDecode(String str) throws Exception {
		if ("".equals(str) || null == str) {
			return "";
		}
		return AESCoder.aesDncrypt(str);
	}

	/**
	 * 用户密码DES加密
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String userPwdDesEncode(String str) throws Exception {
		return (str == null || str.equals("")) ? "" : AESCoder.aesEncrypt(str);
	}
	
	public static String ClobToString(Clob clob) {
		String reString = "";
		Reader is = null;
		try {
			is = clob.getCharacterStream();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}
		// 得到流
		BufferedReader br = new BufferedReader(is);
		String s = null;
		try {
			s = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}
		StringBuffer sb = new StringBuffer();
		while (s != null) {
			// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
			sb.append(s);
			try {
				s = br.readLine();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
		reString = sb.toString();
		return reString;
	}

	public static void main(String args[]) {
		//System.out.println(md5("sendCtrTextMsgHDFH78sdhs+sdfhsDFGhj"));
		System.out.println(base64Decode("aHR0cDovL2x0eXguYmx1ZWtqZy5jb20vd3gvaW5kZXgvYXV0aFVzZXI/b3Blbl9pZD1vQ21WeXd4d3h2cmF5UUcySmNoZ29jcHRTN1M4JnNoYXJlX3R5cGU9MSZyYW5kb209MQ==","utf-8"));
	    System.out.println(base64Encode("http://ltyx.bluekjg.com/wx/index/authUser?open_id=oCmVywxwxvrayQG2JchgocptS7S8&share_type=1&random=1"));
		//System.out.println(base64Encode("111111"));
	}
}
