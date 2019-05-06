package com.bluekjg.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 字符串常用工具类
 *   提供一些JDK 中没有提供的常用的方法
 * */
public final class StringUtil {
	public static Logger logger = LogManager.getLogger(StringUtil.class.getName());
	public static final String ERROR_STATUS_SUCCESS = "0";// 错误状态 成功

    public static final String ERROR_STATUS_ERROR = "-1";// 错误状态 错误
    
    public static final String ERROR_REQUEST = "-8888";// 请求异常
    
    public static final Integer PAGESIZE = 6;  //每页请求数据
    
    public static final String notifyUrl = "https://weshop.inoherb.com/xcx/pay/inoherb/notice";
    
    //电商ID
    public static final String EBusinessID="1274337";
  	//电商加密私钥，快递鸟提供，注意保管，不要泄漏
    public static final String AppKey="ff609c50-debf-465d-b3ff-6a00c249cfaf";
  	//请求url
    public static final String ReqURL = "http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";
    
    
  //阿里云-快递物流轨迹查询请求URL
    public static final String ALIYUN_REQURL_HOST = "http://jisukdcx.market.alicloudapi.com";
    public static final String ALIYUN_REQURL_PATH = "/express/query";
    
  //品牌商活动扣除点数
    public static final String BRANDER_POINT = "BRANDER_POINT";
    
    //字符串转换为集合
    public static List<Integer> stringConvertList(String str){
    	List<Integer> list = new ArrayList<Integer>();
    	if(isEmpty(str)){
    		return list;
    	}
    	String [] strs = str.split(",");
    	for(String s :strs){
    		list.add(Integer.valueOf(s));
    	}
    	return list;
    }
    
    //字符串转换为集合
    public static List<String> stringConvertList(String str,String seperator){
    	List<String> list = new ArrayList<String>();
    	if(isEmpty(str)){
    		return list;
    	}
    	String [] strs = str.split(seperator);
    	for(String s :strs){
    		list.add(s);
    	}
    	return list;
    }
	
    //字符串转换为字符串集合
    public static List<String> stringConvertStringList(String str){
    	List<String> list = new ArrayList<String>();
    	if(isEmpty(str)){
    		return list;
    	}
    	String [] strs = str.split(",");
    	for(String s :strs){
    		list.add(s);
    	}
    	return list;
     }
	public static boolean isBank(String str) {
		if (str == null || str.length() < 1) {
			return true;
		}
		return false;
	}

	public static boolean isNotBank(String str) {
		return !isBank(str);
	}
	
	/**
	 * 判断字符串是否为空 
	 *   当 字符串 为null、空串、空格组成时 返回 true 
	 *   其他返回false
	 * */
	public static boolean isEmpty(String str){
		if(null==str||"".equals(str)||str.length()==0||str.replaceAll(" ", "").length()==0){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(Object obj) {
		if (obj == null || isEmpty(obj.toString())) {
			return true;
		}
		return false;
	}
	

	/**
	 * 去除 string 前后空格
	 * */
	public static String  removeLeftSpace(String str){
		if(isEmpty(str)){
			return null;
		}
		int i = 0;
		for(;i<str.length();i++){
			char c = str.charAt(i);
			if(c!=32){
				break;
			}
		}

		return str.substring(i);
	}
	
	/**
	 * 去除 string 前后空格
	 * */
	public static String  removeRightSpace(String str){
		if(isEmpty(str)){
			return null;
		}
		int i = str.length() - 1;
		for(;i >= 0;i--){
			char c = str.charAt(i);
			if(c!=32){
				break;
			}
		}

		return str.substring(0,i+1);
	}
	
	/**
	 * 首字母大写
	 * */
	public static String toFirstUpper(String str){
		if(isEmpty(str)){
			return null;
		}
		
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}
	
	
	/**
	 * 首字母小写
	 * */
	public static String toFirstLowwer(String str){
		if(isEmpty(str)){
			return null;
		}
		
		return str.substring(0, 1).toLowerCase()+str.substring(1);
	}
	
	/**
	 * 在左边增加字符 以拿到等长字符串
	 *   不够指定长度 则在其左侧 加 ch 字符
	 * */
	public static String addLeftToEqualLength(String str,int length,char ch){
		if(isEmpty(str)){
			return null;
		}
		if(str.length() >= length){
			return str;
		}
		StringBuffer sb = new StringBuffer();
		for(int i = str.length();i < length ;i++){
			sb.append(ch);
		}
		sb.append(str);
		return sb.toString();
	}
	
	/**
	 * 在右边增加字符 以得到等长字符串
	 *   不够指定长度 则在其右侧 加 ch 字符
	 * */
	public static String addRightToEqualLength(String str,int length,char ch){
		if(isEmpty(str)){
			return null;
		}
		if(str.length() >= length){
			return str;
		}
		StringBuffer sb = new StringBuffer(str);
		for(int i = str.length();i < length ;i++){
			sb.append(ch);
		}

		return sb.toString();
	}
	/**
	 * 在左边增加字符 以拿到等长字符串
	 *   不够指定长度 则在其左侧 加  0  字符
	 * */
	public static String addLeftToEqualLength(String str,int length){
		return addLeftToEqualLength(str,length,'0');
	}
	/**
	 * 在左边增加字符 以拿到等长字符串
	 *   不够指定长度 则在其右侧 加  0  字符
	 * */
	public static String addRightToEqualLength(String str,int length){
	    return  addRightToEqualLength(str,length,'0');
	}
	
	/**
	 * 将数据库字段名 转换为Java变量的命名规范(驼峰命名)
	 * */
	public static String columnNameToDeclareVar(String columnName){
		String[] words = columnName.split("_");
		StringBuilder  varName = new StringBuilder();
		for(int i=0;i<words.length;i++){
			varName.append(toFirstUpper(words[i].toLowerCase()));
		}
		
		return toFirstLowwer(varName.toString());
	}
	
	//获取随机数
	public static final Integer getRandomNum(int num){
		Random r = new Random();
		return r.nextInt(num) + 1;
	}
	
	
	/**
	 * 获取精确到两位数的数字
	 * @param columnName
	 * @return
	 */
	public static String getTwoPointStr(String str){
		 DecimalFormat df = new DecimalFormat("#,##0.00");
		 return df.format(Double.parseDouble(str));
	}
	
	
	public static String fliterNull(String s){
		return (s==null)?"":s.trim();
	}
	
	/**
     * MD5加密
     * @param str 内容       
     * @param charset 编码方式
	 * @throws Exception 
     */
	public static String MD5(String str, String charset) throws Exception {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(str.getBytes(charset));
	    byte[] result = md.digest();
	    StringBuffer sb = new StringBuffer(32);
	    for (int i = 0; i < result.length; i++) {
	        int val = result[i] & 0xff;
	        if (val <= 0xf) {
	            sb.append("0");
	        }
	        sb.append(Integer.toHexString(val));
	    }
	    return sb.toString().toLowerCase();
	}
	
	/**
     * base64编码
     * @param str 内容       
     * @param charset 编码方式
	 * @throws UnsupportedEncodingException 
     */
	public static String base64(String str, String charset) throws UnsupportedEncodingException{
		String encoded = base64Encode(str.getBytes(charset));
		return encoded;    
	}	
	
	public static String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
		String result = URLEncoder.encode(str, charset);
		return result;
	}
	
	/**
     * 电商Sign签名生成
     * @param content 内容   
     * @param keyValue Appkey  
     * @param charset 编码方式
	 * @throws UnsupportedEncodingException ,Exception
	 * @return DataSign签名
     */
	public static String encrypt (String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception
	{
		if (keyValue != null)
		{
			return base64(MD5(content + keyValue, charset), charset);
		}
		return base64(MD5(content, charset), charset);
	}
	
	 /**
     * 向指定 URL 发送POST方法的请求     
     * @param url 发送请求的 URL    
     * @param params 请求的参数集合     
     * @return 远程资源的响应结果
     */
	public static String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;        
        StringBuilder result = new StringBuilder(); 
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数            
            if (params != null) {
		          StringBuilder param = new StringBuilder(); 
		          for (Map.Entry<String, String> entry : params.entrySet()) {
		        	  if(param.length()>0){
		        		  param.append("&");
		        	  }	        	  
		        	  param.append(entry.getKey());
		        	  param.append("=");
		        	  param.append(entry.getValue());		        	  
		        	  //System.out.println(entry.getKey()+":"+entry.getValue());
		          }
		          //System.out.println("param:"+param.toString());
		          out.write(param.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {            
        	logger.error(e.getMessage(),e);
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
	
    private static char[] base64EncodeChars = new char[] { 
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
            'w', 'x', 'y', 'z', '0', '1', '2', '3', 
            '4', '5', '6', '7', '8', '9', '+', '/' }; 
    	
    public static String base64Encode(byte[] data) { 
        StringBuffer sb = new StringBuffer(); 
        int len = data.length; 
        int i = 0; 
        int b1, b2, b3; 
        while (i < len) { 
            b1 = data[i++] & 0xff; 
            if (i == len) 
            { 
                sb.append(base64EncodeChars[b1 >>> 2]); 
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]); 
                sb.append("=="); 
                break; 
            } 
            b2 = data[i++] & 0xff; 
            if (i == len) 
            { 
                sb.append(base64EncodeChars[b1 >>> 2]); 
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]); 
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]); 
                sb.append("="); 
                break; 
            } 
            b3 = data[i++] & 0xff; 
            sb.append(base64EncodeChars[b1 >>> 2]); 
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]); 
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]); 
            sb.append(base64EncodeChars[b3 & 0x3f]); 
        } 
        return sb.toString(); 
    }
    
    public static void main(String[] args) {
    	
	}

    
    /**
     * 集合转换为字符串
     * @param brandDescList
     * @return
     */
	public static String listConvertToString(List<String> strList) {
		StringBuffer sb = new StringBuffer();
		if(strList!=null && strList.size()>0){
			for(int i = 0;i<strList.size();i++){
				String str = strList.get(i);
				sb.append(str);
				if(i<strList.size()-1){
					sb.append("~");
				}
			}
			return sb.toString();
		}else{
			return sb.toString();
		}
	}
}
