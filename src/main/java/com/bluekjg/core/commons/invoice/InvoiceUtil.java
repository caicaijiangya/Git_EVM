package com.bluekjg.core.commons.invoice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.Inflater;

public class InvoiceUtil {
	/**
   	 * 将map转化为XML格式的字符串
   	 * @param resmap
   	 * @param simfor 简易格式
   	 * @return
   	 */
   	public static String requesttoxml(Map<String, Object> resmap,boolean simfor){
   		StringBuffer soapResultData = new StringBuffer();
   		for(String key : resmap.keySet()){
   			if(simfor == true && resmap.get(key)==null) {
   				soapResultData.append("<");
   	   			soapResultData.append(key);
   	   			soapResultData.append("/>");
   			}else {
   				soapResultData.append("<");
   	   			soapResultData.append(key);
   	   			soapResultData.append(">");
   	   			soapResultData.append(resmap.get(key)==null?"":resmap.get(key));
   	   			soapResultData.append("</");
   	   			soapResultData.append(key);
   	   			soapResultData.append(">");
   			}
   			
   		}
   		return soapResultData.toString();
   	}
   	/**
   	 * 失效首字母小写
   	 * @param name
   	 * @return
   	 */
   	public static String lowerFirst(String name) {
   		char[] chars = name.toCharArray();
    	chars[0] += 32;
    	return String.valueOf(chars);
   	}
}
