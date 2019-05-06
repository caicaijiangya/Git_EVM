package com.bluekjg.wxapp.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This sample demonstrates how to post object under specfied bucket from Aliyun
 * OSS using the OSS SDK for Java.
 // The local file path to upload.
    private String localFilePath = "<localFile>";
    // OSS domain, such as http://oss-cn-hangzhou.aliyuncs.com
    private String endpoint = "oss-cn-shanghai.aliyuncs.com";
    // Access key Id. Please get it from https://ak-console.aliyun.com
    private String accessKeyId = "LTAIkzTTWIwZ2z0W";
    private String accessKeySecret = "HhHuo3JGALDydlsquiegHlZYt2Et8s";
    // The existing bucket name
    private String bucketName = "inoherbweshop";
    // The key name for the file to upload.
    private String key = "<key>";
 */
public class PostObjectSample {
	
	private static Logger logger = LoggerFactory.getLogger(PostObjectSample.class);
    public static Integer PostObject(String fileName,String contentType,InputStream inputStream) {
        try {
        	fileName = "evm/images/"+fileName;
			// 提交表单的URL为bucket域名
			String urlStr = WxappConfigUtil.ALICLOUD_ENDPOINT.replace("http://", "http://" + WxappConfigUtil.ALICLOUD_BUCKETNAME+ ".");
			
			// 表单域
			Map<String, String> formFields = new LinkedHashMap<String, String>();
			
			// key
			formFields.put("key", fileName);
			// Content-Disposition
			formFields.put("Content-Disposition", "filename="
			        + fileName);
			// OSSAccessKeyId
			formFields.put("OSSAccessKeyId", WxappConfigUtil.ALICLOUD_ACCESSKEYID);
			// policy
			String policy = "{\"expiration\": \"2120-01-01T12:00:00.000Z\",\"conditions\": [[\"content-length-range\", 0, 104857600]]}";
			String encodePolicy = new String(Base64.encodeBase64(policy.getBytes()));
			formFields.put("policy", encodePolicy);
			// Signature
			String signaturecom = computeSignature(WxappConfigUtil.ALICLOUD_ACCESSKEYSECRET, encodePolicy);
			formFields.put("Signature", signaturecom);

			formUpload(urlStr, formFields, fileName,contentType,inputStream);
			
			return 1;
		} catch (Exception e) {
			logger.error("上传文件至云服务器异常",e);
			return 0;
		}
    }
    
    private static String computeSignature(String accessKeySecret, String encodePolicy) 
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        // convert to UTF-8
        byte[] key = accessKeySecret.getBytes(WxappConfigUtil.WX_CODING_FORMAT);
        byte[] data = encodePolicy.getBytes(WxappConfigUtil.WX_CODING_FORMAT);
        
        // hmac-sha1
        Mac mac = Mac.getInstance(WxappConfigUtil.ALICLOUD_MAX_KEY);
        mac.init(new SecretKeySpec(key, WxappConfigUtil.ALICLOUD_MAX_KEY));
        byte[] sha = mac.doFinal(data);
        
        // base64
        return new String(Base64.encodeBase64(sha));
    }

    private static String formUpload(String urlStr, Map<String, String> formFields,String fileName,String contentType,InputStream inputStream)
            throws Exception {
        String res = "";
        HttpURLConnection conn = null;
        String boundary = "9431149156168";
        OutputStream out = null;
        DataInputStream in = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(100000);
            conn.setReadTimeout(100000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", 
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + boundary);
            out = new DataOutputStream(conn.getOutputStream());
            
            // text
            if (formFields != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator<Entry<String, String>> iter = formFields.entrySet().iterator();
                int i = 0;
                
                while (iter.hasNext()) {
                    Entry<String, String> entry = iter.next();
                    String inputName = entry.getKey();
                    String inputValue = entry.getValue();
                    
                    if (inputValue == null) {
                        continue;
                    }
                    
                    if (i == 0) {
                        strBuf.append("--").append(boundary).append("\r\n");
                        strBuf.append("Content-Disposition: form-data; name=\""
                                + inputName + "\"\r\n\r\n");
                        strBuf.append(inputValue);
                    } else {
                        strBuf.append("\r\n").append("--").append(boundary).append("\r\n");
                        strBuf.append("Content-Disposition: form-data; name=\""
                                + inputName + "\"\r\n\r\n");
                        strBuf.append(inputValue);
                    }

                    i++;
                }
                out.write(strBuf.toString().getBytes());
            }

            // file
//            File file = new File(localFile);
//            String filename = file.getName();
//            String contentType = new MimetypesFileTypeMap().getContentType(file);
            if (contentType == null || contentType.equals("")) {
                contentType = "image/png";
            }
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("\r\n").append("--").append(boundary)
                    .append("\r\n");
            strBuf.append("Content-Disposition: form-data; name=\"file\"; "
                    + "filename=\"" + fileName + "\"\r\n");
            strBuf.append("Content-Type: " + contentType + "\r\n\r\n");

            out.write(strBuf.toString().getBytes());

            in = new DataInputStream(inputStream);
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();

            byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 读取返回数据
            strBuf = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
        	
            throw e;
        } finally {
        	if(in != null) {
        		in.close();
        	}
        	if(out != null) {
        		out.flush();
                out.close();
        	}
        	if(reader != null) {
        		reader.close();
        	}
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        
        return res;
    }
    
}