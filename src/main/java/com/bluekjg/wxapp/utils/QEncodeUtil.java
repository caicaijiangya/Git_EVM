package com.bluekjg.wxapp.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/** 
 * 编码工具类 
 */  
public class QEncodeUtil {
	public static Logger logger = LogManager.getLogger(QEncodeUtil.class.getName());
	
	 public static void main(String[] args) throws Exception {  
//	        String content = "Mp_User=Mp_Inoherb&Data=%7b%22a%22%3a1%2c%22b%22%3a2%7d&Time=2345678910";
//	        System.out.println(content);
//	        content = URLEncoder.encode("{\"a\":1,\"b\":2}","UTF-8");
//	        System.out.println("加密前：" + content);  
//	        String key = "inoherb_httpserv";  
//	        String encrypt = aesEncrypt(content, key,"1234567812345678");  
//	        System.out.println(encrypt);
//	        encrypt = encrypt.replaceAll("\r|\n", "");
//	        encrypt = URLEncoder.encode(encrypt,"UTF-8");
//	        System.out.println(encrypt);
	        
	        
	        System.out.println(QEncodeUtil.aesEncrypt("ok4e84j3tjlrWMXwYMWNuTSL32_o,1,1", "zhkdxybc2342pc@!","inoherbpc!@#$%19"));
	    }  
	      
	    /** 
	     * 将byte[]转为各种进制的字符串 
	     * @param bytes byte[] 
	     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制 
	     * @return 转换后的字符串 
	     */  
	    public static String binary(byte[] bytes, int radix){  
	        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数  
	    }  
	      
	    /** 
	     * base 64 encode 
	     * @param bytes 待编码的byte[] 
	     * @return 编码后的base 64 code 
	     */  
	    public static String base64Encode(byte[] bytes){  
	        return new BASE64Encoder().encode(bytes);  
	    }  
	      
	    /** 
	     * base 64 decode 
	     * @param base64Code 待解码的base 64 code 
	     * @return 解码后的byte[] 
	     * @throws Exception 
	     */  
	    public static byte[] base64Decode(String base64Code) throws Exception{  
	        return isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);  
	    }  
	      
	    /** 
	     * 获取byte[]的md5值 
	     * @param bytes byte[] 
	     * @return md5 
	     * @throws Exception 
	     */  
	    public static byte[] md5(byte[] bytes) throws Exception {  
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        md.update(bytes);  
	          
	        return md.digest();  
	    }  
	      
	    /** 
	     * 获取字符串md5值 
	     * @param msg  
	     * @return md5 
	     * @throws Exception 
	     */  
	    public static byte[] md5(String msg) throws Exception {  
	        return isEmpty(msg) ? null : md5(msg.getBytes());  
	    }  
	      
	    /** 
	     * 结合base64实现md5加密 
	     * @param msg 待加密字符串 
	     * @return 获取md5后转为base64 
	     * @throws Exception 
	     */  
	    public static String md5Encrypt(String msg) throws Exception{  
	        return isEmpty(msg) ? null : base64Encode(md5(msg));  
	    }  
	      
	    /** 
	     * AES加密 
	     * @param content 待加密的内容 
	     * @param encryptKey 加密密钥 
	     * @return 加密后的byte[] 
	     * @throws Exception 
	     */  
	    public static byte[] aesEncryptToBytes(String content, String encryptKey,String ivStr) throws Exception {
	    	 if (encryptKey == null) {
	             System.out.print("Key为空null");
	             return null;
	         }
	         // 判断Key是否为16位
	         if (encryptKey.length() != 16) {
	             System.out.print("Key长度不是16位");
	             return null;
	         }
	         byte[] raw = encryptKey.getBytes();
	         SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	         Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
	         IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
	         cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	         byte[] encrypted = cipher.doFinal(content.getBytes());
	         return encrypted; 
	    }  
	      
	    /** 
	     * AES加密为base 64 code 
	     * @param content 待加密的内容 
	     * @param encryptKey 加密密钥 
	     * @return 加密后的base 64 code 
	     * @throws Exception 
	     */  
	    public static String aesEncrypt(String content, String encryptKey,String ivStr) throws Exception {  
	        return base64Encode(aesEncryptToBytes(content, encryptKey,ivStr));  
	    }  
	      
	    /** 
	     * AES解密 
	     * @param encryptBytes 待解密的byte[] 
	     * @param decryptKey 解密密钥 
	     * @return 解密后的String 
	     * @throws Exception 
	     */  
	    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey,String ivStr) throws Exception {  
	    	   // 判断Key是否正确
            if (decryptKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (decryptKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = decryptKey.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            try {
                byte[] original = cipher.doFinal(encryptBytes);
                String originalString = new String(original,"UTF-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
	    }  
	      
	    /** 
	     * 将base 64 code AES解密 
	     * @param encryptStr 待解密的base 64 code 
	     * @param decryptKey 解密密钥 
	     * @return 解密后的string 
	     * @throws Exception 
	     */  
	    public static String aesDecrypt(String encryptStr, String decryptKey,String ivStr) throws Exception {  
	        return isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey,ivStr);  
	    }
	    
	    
	    
	    //
	    public static String encode(String str){
	    	if(isEmpty(str)){
	    		return null;
	    	}
	    	try {
				return URLEncoder.encode(str,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(),e);
			}
	    	return null;
	    }
	    
	    private static boolean isEmpty(String obj){
	    	if("".equals(obj) || obj==null){
	    		return true;
	    	}
	    	return false;
	    }
}
