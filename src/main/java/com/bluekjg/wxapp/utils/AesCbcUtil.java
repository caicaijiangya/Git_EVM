package com.bluekjg.wxapp.utils;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dreamlu.module.ueditor.JsonUtils;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @description：小程序解码工具类
 * @author：pincui.tom
 * @date：2018/3/26 14:51
 */
public class AesCbcUtil {
	private static Logger logger = LoggerFactory.getLogger(AesCbcUtil.class);
	 static {
	        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
	        Security.addProvider(new BouncyCastleProvider());
	    }

	    /**
	     * AES解密
	     *
	     * @param data           //密文，被加密的数据
	     * @param key            //秘钥
	     * @param iv             //偏移量
	     * @param encodingFormat //解密后的结果需要进行的编码
	     * @return
	     * @throws Exception
	     */
	    @SuppressWarnings("unchecked")
		public static Map<String,String> decrypt(String data, String key, String iv) throws Exception {
//	        initialize();
	    	Map<String,String> resultMap = null;
	        //被加密的数据
	        byte[] dataByte = Base64.decodeBase64(data);
	        //加密秘钥
	        byte[] keyByte = Base64.decodeBase64(key);
	        //偏移量
	        byte[] ivByte = Base64.decodeBase64(iv);

	        try {
	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

	            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

	            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
	            parameters.init(new IvParameterSpec(ivByte));

	            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化

	            byte[] resultByte = cipher.doFinal(dataByte);
	            if (null != resultByte && resultByte.length > 0) {
	                String result = new String(resultByte, WxappConfigUtil.WX_CODING_FORMAT);
	                resultMap = JsonUtils.parse(result, Map.class);
	            }
	        } catch (NoSuchAlgorithmException e) {
	        	logger.error("AES解密参数"+data+":"+dataByte+":"+key+":"+keyByte+":"+iv+":"+ivByte);
	        	logger.error("AES密文解密NoSuchAlgorithmException异常",e);
	        } catch (NoSuchPaddingException e) {
	        	logger.error("AES解密参数"+data+":"+dataByte+":"+key+":"+keyByte+":"+iv+":"+ivByte);
	        	logger.error("AES密文解密NoSuchPaddingException异常",e);
	        } catch (InvalidParameterSpecException e) {
	        	logger.error("AES解密参数"+data+":"+dataByte+":"+key+":"+keyByte+":"+iv+":"+ivByte);
	        	logger.error("AES密文解密InvalidParameterSpecException异常",e);
	        } catch (InvalidKeyException e) {
	        	logger.error("AES解密参数"+data+":"+dataByte+":"+key+":"+keyByte+":"+iv+":"+ivByte);
	        	logger.error("AES密文解密InvalidKeyException异常",e);
	        } catch (InvalidAlgorithmParameterException e) {
	        	logger.error("AES解密参数"+data+":"+dataByte+":"+key+":"+keyByte+":"+iv+":"+ivByte);
	        	logger.error("AES密文解密InvalidAlgorithmParameterException异常",e);
	        } catch (IllegalBlockSizeException e) {
	        	logger.error("AES解密参数"+data+":"+dataByte+":"+key+":"+keyByte+":"+iv+":"+ivByte);
	        	logger.error("AES密文解密IllegalBlockSizeException异常",e);
	        } catch (BadPaddingException e) {
	        	logger.error("AES解密参数"+data+":"+dataByte+":"+key+":"+keyByte+":"+iv+":"+ivByte);
	        	logger.error("AES密文解密BadPaddingException异常",e);
	        } catch (UnsupportedEncodingException e) {
	        	logger.error("AES解密参数"+data+":"+dataByte+":"+key+":"+keyByte+":"+iv+":"+ivByte);
	        	logger.error("AES密文解密UnsupportedEncodingException异常",e);
	        }
	        return resultMap;
	    }
	    
	    public static void main(String[] arge) {
	    	WxappConfigUtil.WX_CODING_FORMAT = "UTF-8";
	    	try {
	    		String data = "ilXs+HWAmXCKBd1cevUZ6pp7nGJx/LVDL01+5T8gd55j0WXPMkmTR6NB43tdp2lbSgbD9f4cjSTYwbnksfyOQY2wvaQL6TkWsIGV+4S50yXaOTbZDu14VSs3GFaxrdPXsKO5RFu/3IOZUNd3yjvBlJW3QoS6W3NlrZn/SOXTJcxkLN1SA9Vle0o+yyx7RHAA0QO2k/wnzBw00PhpqyuoZQ==";
	    		String key = "0njrKNvxSMhII7XMt6W6Mw==";
	    		String iv = "ckRj58BcoPd3t5hyWp93/w==";
	    		
	    		Map<String,String> map = AesCbcUtil.decrypt(data, key, iv);;
	    		System.out.println(map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			}
	    }
}
