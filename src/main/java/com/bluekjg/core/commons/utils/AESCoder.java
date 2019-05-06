package com.bluekjg.core.commons.utils;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



public class AESCoder {
	public static Logger logger = LogManager.getLogger(AESCoder.class.getName());
	// 密钥  
    private final static String secretKey = "wtyt@EAAE8A86B904044Aeww45680583"; 
    // 向量  
    private final static String iv = "76534836";  
    // 加解密统一使用的编码方式  
    private final static String encoding = "utf-8";  
  
	/**
	 * 密钥算法
	 */
	private static final String KEY_ALGORITHM = "1234567812345678";
	private static final String IV_ALGORITHM = "1234567812345678";

	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    
    /** 
     * 3DES加密 
     * @param plainText 普通文本 
     * @return 
     * @throws Exception  
     */  
    public static String encode(String plainText) throws Exception {  
        Key deskey = null;  
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");  
        deskey = keyfactory.generateSecret(spec);  
  
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);  
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));  
        return Base64.encode(encryptData);  
    }  
  
    /** 
     * 3DES解密 
     *  
     * @param encryptText 加密文本 
     * @return 
     * @throws Exception 
     */  
    public static String decode(String encryptText) throws Exception {  
        Key deskey = null;  
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");  
        deskey = keyfactory.generateSecret(spec);  
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        System.out.println("111111111111="+new String(Base64.decode(encryptText), encoding));
        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));  
  
        return new String(decryptData, encoding);  
    }  
	

	/**
	 * 初始化密钥
	 * 
	 * @return byte[] 密钥
	 * @throws Exception
	 */
	public static byte[] initSecretKey() {
		// 返回生成指定算法的秘密密钥的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(),e);
			return new byte[0];
		}
		// 初始化此密钥生成器，使其具有确定的密钥大小
		// AES 要求密钥长度为 128
		kg.init(128, new SecureRandom(KEY_ALGORITHM.getBytes()));
		// 生成一个密钥
		return kg.generateKey().getEncoded();
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 *            二进制密钥
	 * @return 密钥
	 */
	private static Key toKey(byte[] key) {
		// 生成密钥
		return new SecretKeySpec(key, "AES");
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, Key key) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            二进制密钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            二进制密钥
	 * @param cipherAlgorithm
	 *            加密算法/工作模式/填充方式
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm)
			throws Exception {
		// 还原密钥
		Key k = toKey(key);
		return encrypt(data, k, cipherAlgorithm);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @param cipherAlgorithm
	 *            加密算法/工作模式/填充方式
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm)
			throws Exception {
		// 实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);

		// byte[] ciphertextBytes = hexStringToByteArray(IV_ALGORITHM);
		// byte[] ivBytes = Arrays.copyOf(Arrays.copyOf(ciphertextBytes, 8),
		// 16);
		IvParameterSpec ivSpec = new IvParameterSpec(IV_ALGORITHM.getBytes());// 需要一个向量iv，可增加加密算法的强度
		// 使用密钥初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            二进制密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, Key key) throws Exception {
		return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            二进制密钥
	 * @param cipherAlgorithm
	 *            加密算法/工作模式/填充方式
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm)
			throws Exception {
		// 还原密钥
		Key k = toKey(key);
		return decrypt(data, k, cipherAlgorithm);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @param cipherAlgorithm
	 *            加密算法/工作模式/填充方式
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm)
			throws Exception {
		// 实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		// byte[] ciphertextBytes = hexStringToByteArray(IV_ALGORITHM);
		// byte[] ivBytes = Arrays.copyOf(Arrays.copyOf(ciphertextBytes, 8),
		// 16);
		IvParameterSpec iv = new IvParameterSpec(IV_ALGORITHM.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		// 使用密钥初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		// 执行操作
		return cipher.doFinal(data);
	}

	// 默认key加密
	public static String encrypt(String str) throws Exception {
		Key k = toKey(initSecretKey());
		byte[] encryptData = encrypt(str.getBytes(), k);
		return asHex(encryptData);
	}

	// 默认key解密
	public static String decrypt(String str) throws Exception {
		Key k = toKey(initSecretKey());
		byte[] decryptData = decrypt(hexStringToByteArray(str), k);
		return new String(decryptData);
	}

	public static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		for (i = 0; i < buf.length; i++) {
			if ((buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString(buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}

	private static byte[] hexStringToByteArray(String hex) {
		Pattern replace = Pattern.compile("^0x");
		String s = replace.matcher(hex).replaceAll("");

		byte[] b = new byte[s.length() / 2];
		for (int i = 0; i < b.length; i++) {
			int index = i * 2;
			int v = Integer.parseInt(s.substring(index, index + 2), 16);
			b[i] = (byte) v;
		}
		return b;
	}

	public static String aesEncrypt(String data) throws Exception{
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = data.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength
						+ (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspec = new SecretKeySpec(KEY_ALGORITHM.getBytes(),
					"AES");
			IvParameterSpec ivspec = new IvParameterSpec(IV_ALGORITHM
					.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);
			return new BASE64Encoder().encode(encrypted);
	}

	public static String aesDncrypt(String data) throws Exception{
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = new BASE64Decoder().decodeBuffer(data);
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength
						+ (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspec = new SecretKeySpec(KEY_ALGORITHM.getBytes(),
					"AES");
			IvParameterSpec ivspec = new IvParameterSpec(IV_ALGORITHM
					.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(dataBytes);
			return remove0(new String(encrypted));
	}

	public String decoder(InputStream is) throws IOException {
		ByteArrayOutputStream bos = null;
		try {

			bos = new ByteArrayOutputStream();
			new BASE64Decoder().decodeBuffer(is, bos);
			return bos.toString();
		} finally {
			if (bos != null) {
				bos.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		
		/*System.out.println("秘钥:"+secretKey+"|IV:"+iv);
		System.out.println("加密结果:"+encode("qwerty"));
		System.out.println("解密结果:"+decode(encode("qwerty")));*/
		System.out.println(doEncodeStr("13866095453"));
		System.out.println(doDecodeStr("VUhGdWVVcEdaVTUzUm1WaVVtMTBVRVpWYVZBNGR6MDk="));
		 
		//C/0OohDmxNg=
	}

	private static String remove0(String str) {
		char[] chars = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {// 输出结果
			if (chars[i] != 0) {
				sb.append(chars[i]);
			}
		}
		return sb.toString();
	}
	/**
	 * 获取解密后的字符串
	 * @return
	 */
	public static String doDecodeStr(String str){
		if(StringUtils.isEmpty(str)){
			return "";
		}			
		str = Util.base64DecodeTwice(str);				
		try{			
			str = decode(str);			
		}catch(Exception e){
			Util.printExceptionInfo(e);
			str = "-2";
		}
		//log.info("------------解密后的用户ID是：----------"+str);
		return str;
	}
	
	
	/**
	 * 对字符串加密
	 */
	public static String doEncodeStr(String str){
		try{		
			str = encode(str);	
			if(!StringUtils.isEmpty(str)){				
				str = Util.base64EncodeTwice(str);
			}			
		}catch(Exception e){
			Util.printExceptionInfo(e);		
		}			
		return str;
	}
	
}
