package com.bluekjg.core.commons.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sun.misc.BASE64Decoder;

public class Util {
	public static Logger logger = LogManager.getLogger(Util.class.getName());
	/**号码类型:中国移动*/
	public static final int MOBILE_TYPE_MOBILE =1;
	/**号码类型:中国电信*/
	public static final int MOBILE_TYPE_TELECOM =2;
	/**号码类型:中国联通*/
	public static final int MOBILE_TYPE_UNICOM =3;
	
	private final static String NUMBER_REGULAR_EXPRESSIONS_STR = "[0-9]*";
	
	
	
	/**
	 * 对IP地址鉴权
	 * */
	public static boolean isLegalIp(String ip, String ipList) {
		String ss[] = ipList.split(",");
		for (int i = 0; i < ss.length; i++) {
			if (ss[i].equals(ip)) {
				return true;
			}
		}
		return false;
	}


	/**
	 *中文转码
	 * 
	 * @param str
	 * @return String
	 * @see java.net.URLEncoder.encode(String s, String enc)
	 */
	public static String URLEncode2UTF8(String str) {
		if (null == str)
			return "";
		String strRet = "";
		try {
			strRet = java.net.URLEncoder.encode(str, "utf-8");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return strRet;
	}

	/**
	 * 对查询参数进行两次编码 2013-3-28
	 * 
	 * @param str
	 * @return
	 */
	public static String URLEncodeUTF8ForTwoTimes(String str) {
		return URLEncode2UTF8(URLEncode2UTF8(str));
	}

	public static String URLDecode2UTF8(String str) {
		if (null == str)
			return "";
		String strRet = "";
		try {
			strRet = java.net.URLDecoder.decode(str, "utf-8");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return strRet;
	}

	/**
	 * 以UTF-8编码encode参数的值
	 * 
	 * @param parameters
	 * @return
	 */
	public static String encodeParameters2GBK(String parameters) {
		if (null == parameters || "".equals(parameters))
			return parameters;

		String[] parArray = parameters.split("&");
		if (null == parArray || parArray.length == 0)
			return parameters;

		StringBuffer bufRet = new StringBuffer();
		for (int i = 0; i < parArray.length; i++) {
			String[] keyAndValue = parArray[i].split("=");
			if (null != keyAndValue && keyAndValue.length == 2) {
				String key = keyAndValue[0];
				String value = keyAndValue[1];
				String encodeValue = URLEncode2UTF8(value);
				Util.addParameter(bufRet, key, encodeValue);
			} else {
				bufRet.append("&" + parArray[i]);
			}
		}

		return bufRet.toString();
	}

	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}

	/**
	 * 添加参数
	 * 
	 * @param buf
	 * @param parameterName
	 *            参数名
	 * @param parameterValue
	 *            参数值
	 * @return StringBuffer
	 */
	public static StringBuffer addParameter(StringBuffer buf,
			String parameterName, String parameterValue) {

		if ("".equals(buf.toString())) {
			buf.append(parameterName);
			buf.append("=");
			buf.append(parameterValue);
		} else {
			buf.append("&");
			buf.append(parameterName);
			buf.append("=");
			buf.append(parameterValue);
		}

		return buf;
	}

	/**
	 * 添加参数,若参数值不为空串,则添加。反之,不添加。
	 * 
	 * @param buf
	 * @param parameterName
	 * @param parameterValue
	 * @return StringBuffer
	 */
	public static StringBuffer addBusParameter(StringBuffer buf,
			String parameterName, String parameterValue) {
		if (null == parameterValue || "".equals(parameterValue)) {
			return buf;
		}

		if ("".equals(buf.toString())) {
			buf.append(parameterName);
			buf.append("=");
			buf.append(parameterValue);
		} else {
			buf.append("&");
			buf.append(parameterName);
			buf.append("=");
			buf.append(parameterValue);
		}
		return buf;
	}

	/**
	 * 跳转到显示的页面
	 * 
	 * @param url
	 *            显示的页面,以绝对地址出现
	 * @param name
	 *            参数名字
	 * @param value
	 *            参数值
	 * @return String 返回跳转的js字符串代码
	 */
	public static String gotoShow(String url, String name, String value) {
		String strScript = "<script language='javascript' type='text/javascript'>";
		strScript += "window.location.href='";
		strScript += url + "?" + name + "=" + value;
		strScript += "'</script>";
		return strScript;
	}

	public static String gotoShow(String url) {
		String strScript = "<script language='javascript' type='text/javascript'>";
		strScript += "window.location.href='";
		strScript += url;
		strScript += "'</script>";
		return strScript;
	}


	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */

	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */

	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return round(b1.multiply(b2).doubleValue());
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */

	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */

	public static double sub(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供把double转化为需要的精确值
	 * 
	 * @param v
	 *            要精确的double值
	 * @param scale
	 *            精确的位数，如果scale 小于零，则采用默认的精确值，精确到小数位 2 位
	 * */
	public static double round(double v, int scale) {
		if (scale < 0) {
			scale = 2;
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, 2);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static float divFloat(float v1, int v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	/**
	 * 提供把double 精确到 2 位 小数
	 * 
	 * @param v
	 *            要精确的double值
	 * 
	 * */
	public static double round(double v) {
		return round(v, 2);
	}

	private Util() {
	}


	/**
	 * 
	 * 获取用户的ip地址
	 * 
	 * @param request
	 * @return 用户ip
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String result = request.getHeader("x-forwarded-for");
		if (result != null && result.trim().length() > 0) {
			// 可能有代理
			if (result.indexOf(".") == -1) {
				// 没有"."肯定是非IPv4格式
				result = null;
			} else {
				if (result.indexOf(",") != -1) {
					// 有","，估计多个代理。取第一个不是内网的IP。
					result = result.trim().replace("'", "");
					String[] temparyip = result.split(",");
					for (int i = 0; i < temparyip.length; i++) {
						if (isIPAddress(temparyip[i])
								&& temparyip[i].substring(0, 3).equals("10.")
								&& temparyip[i].substring(0, 7).equals(
										"192.168")
								&& temparyip[i].substring(0, 7).equals(
										"172.16.")) {
							return temparyip[i]; // 找到不是内网的地址
						}
					}
				} else if (isIPAddress(result)) {// 代理即是IP格式
					return result;
				} else {
					result = null; // 代理中的内容 非IP，取IP
				}
			}
		}

		if (result == null || result.trim().length() == 0) {
			result = request.getHeader("Proxy-Client-IP");
		}
		if (result == null || result.length() == 0) {
			result = request.getHeader("WL-Proxy-Client-IP");
		}
		if (result == null || result.trim().length() == 0) {
			result = request.getRemoteAddr();
		}
		return result;
	}

	/**
	 * 判断是否是IP地址格式
	 * 
	 * @param str1
	 * @return
	 */
	private static boolean isIPAddress(String str1) {
		if (str1 == null || str1.trim().length() < 7
				|| str1.trim().length() > 15) {
			return false;
		}
		return true;
	}

	/**
	 * 除法 保留2位,四舍五入 有任何异常时返回null
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static String DivisionForPublicFun(String num1, String num2) {
		try {
			if (StringUtils.isNotBlank(num1) && StringUtils.isNotBlank(num2)) {
				BigDecimal bd1 = new BigDecimal(num1);
				BigDecimal bd2 = new BigDecimal(num2);
				return bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_UP).toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 乘法 保留2位小数 四舍五入
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static String mulStringForPublicFun(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return (b1.multiply(b2)).setScale(2, BigDecimal.ROUND_HALF_UP)
				.toString();
	}

	/**
	 * 加法 保留2位小数 四舍五入
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static String addStringForPublicFun(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return (b1.add(b2)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	
	/**
	 * 金额格式化 保留2位小数
	 * 
	 * @param v1
	 * @return
	 */
	public static String moneyFormat(String v1) {
		BigDecimal b1 = new BigDecimal(v1);
		return b1.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	private static String IMG_ALLOW_SUFFIX = "jpg";

	public static boolean isAllowImg(String imgName) {
		String suffix = imgName.substring(imgName.lastIndexOf(".") + 1, imgName
				.length());
		return IMG_ALLOW_SUFFIX.indexOf(suffix.toLowerCase()) != -1 ? true
				: false;
	}

	public static boolean isNotAllowImg(String imgName) {
		return !isAllowImg(imgName);
	}
	
	/**获得根路径*/
	public static String getBaseUrl(HttpServletRequest request){
		String realPath=null;
		if(request.getServerPort()==80){
			String serverName = request.getServerName();
			if(!serverName.contains("evm_admin")){
				realPath=  "http://"+request.getServerName()+ "/evm_admin/"; 
			}else{
				realPath=  "http://"+request.getServerName()+ "/"; 
			}
		}else{
			realPath=  "http://" + request.getServerName()+ ":" +request.getServerPort() + request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);  
		}
	    return realPath;
	}
	
	/**
	 * 打印浏览器信息
	 * @param request
	 * @return
	 */
	public static String printBrowder(HttpServletRequest request){
		Enumeration e = request.getHeaderNames();
		String result = "";
		try {
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				if ("user-agent".equals(name)) {
					result = result + request.getHeader(name).toLowerCase();
				}
			}
		} catch (Exception ee) {
			logger.error(ee.getMessage(),ee);
		}
		return result;
	}
	
	public static UUID getUUID(){
		return UUID.randomUUID();
	}
	
	
	/**
	 * 获得随机字符串参数
	 * @return
	 */
	public static String getRandom(){
		return "random="+getUUID();
	}
	
	/**
	 * 验证手机号码
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNo(String mobiles){
		String reg = System.getProperty("MobileNoPattern");
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(mobiles);
		return m.matches();
    }
	
	/**
	 * 判断是否移动手机号码
	 */
	public static boolean  isMobileNoForMobile(String mobileNo) {
		String reg = System.getProperty("CmccNoPattern");
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(mobileNo);
		return m.matches();
	}
	
	/**
	 * 判断是否电信手机号码
	 */
	public static boolean  isMobileNoForTelecom(String mobileNo) {
		String reg = System.getProperty("TelecomNoPattern");
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(mobileNo);
		return m.matches();
	}
	
	
	/**
	 * 判断是否联通手机号码
	 */
	public static boolean  isMobileNoForUnicom(String mobileNo) {
		String reg = System.getProperty("UnicomNoPattern");
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(mobileNo);
		return m.matches();
	}
	
	/***
	 * 验证手机号码类型
	 * @param mobileNo
	 * @return 1-移动 2-电信 3-联通
	 */
	public static int validMobileType(String mobileNo){
		boolean  result = isMobileNoForMobile(mobileNo);
		if(result){//移动
			return MOBILE_TYPE_MOBILE;
		}else{//联通、电信
			boolean result2 = Util.isMobileNoForTelecom(mobileNo);
			if(result2){//电信
				return MOBILE_TYPE_TELECOM;
			}else{//联通
				return MOBILE_TYPE_UNICOM;
			}
		}
	}
	
	
	//返回六个随机数字
	public static String getSixRandomNum(){
		return Math.round(Math.random()*(999999-100000)+100000)+"";
	}
 
	/**
	 * 返回多少位数字字符串
	 * @param number
	 * @return
	 */
	public static String generateWord(int number) {  
        String[] beforeShuffle = new String[] {"0","1","2", "3", "4", "5", "6", "7",  
                "8", "9" };  
        List list = Arrays.asList(beforeShuffle);  
        Collections.shuffle(list);  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(list.get(i));  
        }  
        String afterShuffle = sb.toString(); 
        int startIndex = afterShuffle.length()-number;
        String result = afterShuffle.substring(startIndex, afterShuffle.length());  
        return result;  
    }  
	
	
	/**
	 * 检测手机号码是否是中国移动手机号码 返回true表示是移动手机号码 返回false表示是联通手机号码
	 * @param mobileNo
	 * String
	 * @return boolean
	 */
	public static boolean isCmccMobileNo(String mobileNo) {
		Pattern p = Pattern.compile(System.getProperty("CmccNoPattern"));
		Matcher m = p.matcher(mobileNo);
		return m.matches();
	}
	
	/**
	 * 判断是否是中国联通的手机号码
	 * @param mobileNo
	 * String
	 * @return boolean
	 */
	public static boolean isUnicomMobileNo(String mobileNo) {
		Pattern p = Pattern.compile(System.getProperty("UnicomNoPattern"));
		Matcher m = p.matcher(mobileNo);
		return m.matches();
	}

	/**
	 * 判断是否是中国电信的手机号码
	 * @param mobileNo
	 * String
	 * @return boolean
	 */
	public static boolean isTelecomMobileNo(String mobileNo) {
		Pattern p = Pattern.compile(System.getProperty("TelecomNoPattern"));
		Matcher m = p.matcher(mobileNo);
		return m.matches();
	}

	/**
	 * 函数功能: 检查字符串是否是数字
	 * @param str
	 * String
	 * @return boolean
	 */
	public static boolean isNumber(String str) {
		if(str == null || str.length() == 0){
			return false;
		}
		Pattern p = Pattern.compile(NUMBER_REGULAR_EXPRESSIONS_STR);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/**
	 * 过来用户区号获取
	 * @param str
	 * @return
	 */
	public static String formatPosition(HashMap<String, Object> map) {
		if(map == null){
			return "";
		}
//		for(String str : map.keySet()){
//			System.out.println(String.format("key:%s || value:%s", str,map.get(str)));
//		}
		String position = (String) map.get("CURPOSITION");
		String positionCode = (String) map.get("CURPOSITIONCODE");
		String t = "-1";
		if (Util.isNumber(position)) {
			t = position.replaceAll("^(502|503|504|4)$", "");
		}
		//如果结果大于4.获取code值
		if(t.length() > 4 || "-1".equals(t)){
			t = positionCode;
		}
		//判断是否为null
		if(StringUtils.isBlank(t)){
			t = "";
		}
		return t;
	}
	 
	/**
	 * 判断是否是数字，支持0x11，9.5， 1E3等格式
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean isDigitalNumber(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		char[] chars = str.toCharArray();
		int sz = chars.length;
		boolean hasExp = false;
		boolean hasDecPoint = false;
		boolean allowSigns = false;
		boolean foundDigit = false;
		int start = (chars[0] == '-') ? 1 : 0;
		if (sz > start + 1) {
			if (chars[start] == '0' && chars[start + 1] == 'x') {
				int i = start + 2;
				if (i == sz) {
					return false;
				}
				for (; i < chars.length; i++) {
					if ((chars[i] < '0' || chars[i] > '9')
							&& (chars[i] < 'a' || chars[i] > 'f')
							&& (chars[i] < 'A' || chars[i] > 'F')) {
						return false;
					}
				}
				return true;
			}
		}
		sz--;
		int i = start;
		while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				foundDigit = true;
				allowSigns = false;

			} else if (chars[i] == '.') {
				if (hasDecPoint || hasExp) {
					return false;
				}
				hasDecPoint = true;
			} else if (chars[i] == 'e' || chars[i] == 'E') {
				if (hasExp) {
					return false;
				}
				if (!foundDigit) {
					return false;
				}
				hasExp = true;
				allowSigns = true;
			} else if (chars[i] == '+' || chars[i] == '-') {
				if (!allowSigns) {
					return false;
				}
				allowSigns = false;
				foundDigit = false;
			} else {
				return false;
			}
			i++;
		}
		if (i < chars.length) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				return true;
			}
			if (chars[i] == 'e' || chars[i] == 'E') {
				return false;
			}
			if (!allowSigns
					&& (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F')) {
				return foundDigit;
			}
			if (chars[i] == 'l' || chars[i] == 'L') {
				return foundDigit && !hasExp;
			}
			return false;
		}
		return !allowSigns && foundDigit;
	}	
	
	
	public static String getNumberCHFormat(String s){
		String[]num = {"1","2","3","4","5","6","7","8","9"};
		String[]numCh = {"一","二","三","四","五","六","七","八","九"};
		Map<String,String> chMap = new HashMap<String,String>();
		int len = num.length;
		for(int i=0;i<len;i++){
			chMap.put(num[i],numCh[i]);
		}
		return chMap.get(s)==null?"":chMap.get(s);
	}
	
	public static String getMobileNoFormat(String s){
		if(StringUtils.isEmpty(s)){
			return "";
		}
		s = s.trim();
		if(s.length()!=11){
			return s.trim();
		}
		return s.substring(0,3)+"****"+s.substring(s.length()-4,s.length());
	}
	//将被BASE64编码的字符串解码
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
	public static String base64DecodeTwice(String s){		
		return base64Decode(base64Decode(s));
	}
	
	public static String base64EncodeTwice(String s){		
		return base64Encode(base64Encode(s));
	}
	//将字符串用BASE64编码
	public static String base64Encode(String s) {
		if (s == null) {
			return null;
		}
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}
	/**
	 * 将错误信息以字符串形式打印出来
	 * 
	 * @param e
	 */
	public static void printExceptionInfo(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw, true));
		System.err.println("--warning exception ！异常信息为：-" + sw.toString());
	}
	
	//保留2位小数  
	public static String get2Double(double a){  
	    DecimalFormat df = new DecimalFormat("######0.00"); 
	    return df.format(a).toString();  
	} 
	
	//保留1位小数  
	public static String get1Double(double a){  
	    DecimalFormat df = new DecimalFormat("######0.0"); 
	    return df.format(a).toString();  
	} 
	/**
	 * 判断字符串是否为空
	 * 
	 * @param seq
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return null == str || "".equals(str.trim()) || "null".equals(str);
	}
	/**
	 * 截取字符串
	 * @param str
	 * @param size
	 * @return
	 */
	public static String subString(String str,int size){
		if(!Util.isEmpty(str)&&str.length()>size){
			return str.substring(0, size);
		}
		return str;
	}
	
	/**
	 * 截取字符串
	 * @param str
	 * @param size
	 * @return
	 */
	public static String toTrim(String str){
		if(!Util.isEmpty(str)){
			return str.replaceAll(" ", "");
		}else{
			return "";
		}
	}
	
	
	public static void main(String[] args) throws ParseException {
		System.out.println(getMobileNoFormat("13865944130"));
	}


	public static String qrcodeKey(){
		String key = UUID.randomUUID().toString();
		key = key.replace("-", "");
		return key;
	}
}