package com.bluekjg.core.commons.print;
 
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
/**
 * @Description: MD5工具类
 * @author: Timor
 * @date: 2018年7月7日 下午4:56:45
 * 
 */
public class MD5Utils
{
	public static Logger logger = LogManager.getLogger(MD5Utils.class.getName());
	/**
	 * @Title: encodeToBytes
	 * @Description: 将源字符串通过MD5进行加密为字节数组
	 * @param source 源字符串
	 * @return byte[] 通过MD5进行加密后的字节数组
	 * 
	 * @author: Timor
	 * @date: 2018年7月7日 下午4:56:19
	 */
	public static byte[] encodeToBytes(String source)
	{
		byte[] result = null;
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();// 重置
			md.update(source.getBytes("UTF-8"));// 添加需要加密的源
			result = md.digest();// 加密
		} catch (NoSuchAlgorithmException e)
		{
			logger.error(e.getMessage(),e);
		} catch (UnsupportedEncodingException e)
		{
			logger.error(e.getMessage(),e);
		}
		return result;
	}
 
	/**
	 * @Title: encodeToHex
	 * @Description: 将源字符串通过MD5加密成32位16进制数
	 * @param source 源字符
	 * @return 通过MD5加密成32位16进制数后的字符串
	 * 
	 * @author: Timor
	 * @date: 2018年7月7日 下午4:55:20
	 */
	public static String encodeToHex(String source)
	{
		byte[] data = encodeToBytes(source);// 先加密为字节数组
		StringBuffer hexSb = new StringBuffer();
		for (int i = 0; i < data.length; i++)
		{
			String hex = Integer.toHexString(0xff & data[i]);
			if (hex.length() == 1)
			{
				hexSb.append("0");
			}
			hexSb.append(hex);
		}
		return hexSb.toString();
	}
 
	/**
	 * @Title: validate
	 * @Description: 验证字符串是否匹配
	 * @param unknown 待验证的字符串
	 * @param okHex   使用MD5加密后的16进制字符串
	 * @return 是否匹配
	 * 
	 * @author: Timor
	 * @date: 2018年7月7日 下午4:54:55
	 */
	public static boolean validate(String unknown, String okHex)
	{
		return okHex.equals(encodeToHex(unknown));
	}
}
