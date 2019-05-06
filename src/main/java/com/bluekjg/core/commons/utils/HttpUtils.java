package com.bluekjg.core.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** 
 * 通过url取得文件返回InputStream类型数据 
 * 
 */ 
public class HttpUtils {
	public static Logger logger = LogManager.getLogger(HttpUtils.class.getName());
	/** 
     * 通过图片url返回图片Bitmap 
     * @param url 
     * @return 
     */  
    public static InputStream returnBitMap(String path) {  
        URL url = null;  
        InputStream is =null;  
        try {  
            url = new URL(path);  
        } catch (MalformedURLException e) {  
            logger.error(e.getMessage(),e); 
        }  
        try {  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//利用HttpURLConnection对象,我们可以从网络中获取网页数据.  
            conn.setDoInput(true);  
            conn.connect();  
            is = conn.getInputStream(); //得到网络返回的输入流  
              
        } catch (IOException e) {  
            logger.error(e.getMessage(),e); 
        }  
        return is;  
    }  

}
