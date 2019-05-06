package com.bluekjg.core.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import com.bluekjg.wxapp.utils.PostObjectSample;
import com.bluekjg.wxapp.utils.WxappConfigUtil;
import net.sf.json.JSONObject;

/**
 * 继承自Spring util的工具类，减少jar依赖
 * @author L.cm
 */
public class StringUtils extends org.springframework.util.StringUtils {
	public static Logger logger = LogManager.getLogger(StringUtils.class.getName());
    /**
     * Check whether the given {@code CharSequence} contains actual <em>text</em>.
     * <p>More specifically, this method returns {@code true} if the
     * {@code CharSequence} is not {@code null}, its length is greater than
     * 0, and it contains at least one non-whitespace character.
     * <p><pre class="code">
     * StringUtils.isBlank(null) = true
     * StringUtils.isBlank("") = true
     * StringUtils.isBlank(" ") = true
     * StringUtils.isBlank("12345") = false
     * StringUtils.isBlank(" 12345 ") = false
     * </pre>
     * @param str the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not {@code null},
     * its length is greater than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
    public static boolean isBlank(final CharSequence cs) {
        return !StringUtils.isNotBlank(cs);
    }
    
    /**
     * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is
     *  not empty and not null and not whitespace
     * @see Character#isWhitespace
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return StringUtils.hasText(cs);
    }
    
    /**
     * Convert a {@code Collection} into a delimited {@code String} (e.g. CSV).
     * <p>Useful for {@code toString()} implementations.
     * @param coll the {@code Collection} to convert
     * @param delim the delimiter to use (typically a ",")
     * @return the delimited {@code String}
     */
    public static String join(Collection<?> coll, String delim) {
        return StringUtils.collectionToDelimitedString(coll, delim);
    }
    
    /**
     * Convert a {@code String} array into a delimited {@code String} (e.g. CSV).
     * <p>Useful for {@code toString()} implementations.
     * @param arr the array to display
     * @param delim the delimiter to use (typically a ",")
     * @return the delimited {@code String}
     */
    public static String join(Object[] arr, String delim) {
        return StringUtils.arrayToDelimitedString(arr, delim);
    }
    
    public static boolean isEmpty(Object obj) {
		if (obj == null || isEmpty(obj.toString())) {
			return true;
		}
		return false;
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
    
    /**
     * 生成uuid
     * @return UUID
     */
    public static String getUUId() {
        return UUID.randomUUID().toString();
    }
    
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
    public static List<String> stringConvertToList(String str){
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
    
  //字符串转换为Integer集合
    public static List<Integer> integerConvertToList(String str){
    	List<Integer> list = new ArrayList<Integer>();
    	if(isEmpty(str)){
    		return list;
    	}
    	String [] strs = str.split(",");
    	for(String s :strs){
    		list.add(Integer.parseInt(s));
    	}
    	return list;
    }
    
    public static String uploadImage(MultipartFile multipartFile) throws Exception{
    	String ret = null;
    	// 生成一个新的文件名
		// 取原始文件名
		String oldName = multipartFile.getOriginalFilename();
		
		// 生成新文件名
		String newName = UUID.randomUUID().toString().replaceAll("-", "");
		newName = newName + oldName.substring(oldName.lastIndexOf("."));
		// 文件上传
		String imagePath = DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD);
		Integer result = PostObjectSample.PostObject(imagePath + "/" + newName, multipartFile.getContentType(),
				multipartFile.getInputStream());
		// 返回结果
		if (result > 0) {
			ret = WxappConfigUtil.ALICLOUD_IMAGE_BASE_URL + imagePath + "/" + newName;
		}
		return ret;
    }
    
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
    	StringBuilder result = new StringBuilder(); 
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
            	result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            logger.error(e.getMessage(),e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
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
	
	public static String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
		String result = URLEncoder.encode(str, charset);
		return result;
	}
	
	public static void main(String[] args){
		String url = "http://apis.map.qq.com/ws/geocoder/v1/";
		String para = "location=39.984154,116.307490&key=2WDBZ-HL7CP-P4WDI-VHKJ5-5T34S-B7FFS&get_poi=0&poi_options=address_format=short";
		String json = StringUtils.sendGet(url, para);
		JSONObject obj = JSONObject.fromObject(json.toString());
        System.out.println(obj.getJSONObject("result").getString("address"));
	}
}
