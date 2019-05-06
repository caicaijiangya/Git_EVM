package com.bluekjg.wxapp.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bluekjg.core.commons.utils.MapUtils;

import net.dreamlu.module.ueditor.JsonUtils;
import net.sf.json.JSONObject;

/**
 * @description：Http请求工具类
 * @author：pincui.tom
 * @date：2018/3/26 14:51
 */
public class CommonUtil {
	
	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	/**
     * 发送https请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return Map(通过Map.get(key)的方式获取json对象的属性值)
     */
    @SuppressWarnings("unchecked")
	public static Map<String,String> httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        Map<String,String> jsonObject = null;
        HttpsURLConnection conn = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                CommonUtil.outputStreamRequest(conn.getOutputStream(), outputStr);
            }
            String buffer = CommonUtil.inputStreamRequest(conn.getInputStream());
            if(buffer!= null && !buffer.isEmpty())
            jsonObject = JsonUtils.parse(buffer, Map.class);
        } catch (ConnectException ce) {
        	logger.error("调用HTTPS发送请求ConnectException异常", ce);
        } catch (Exception e) {
        	logger.error("调用HTTPS发送请求Exception异常", e);
        } finally {
        	if (conn != null) {
                conn.disconnect();
                conn = null;
            }
		}
        return jsonObject;
    }
    /**
     * 发送http请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return Map(通过Map.get(key)的方式获取json对象的属性值)
     */
	public static Map<String,Object> httpRequest(String requestUrl, String requestMethod, String outputStr)
    {
		Map<String,Object> jsonObject = null;
		HttpURLConnection conn = null;
		PrintStream ps = null;
		BufferedReader bReader = null;
		try {
	        URL restURL = new URL(requestUrl);
	        /*
	         * 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类 的子类HttpURLConnection
	         */
	        conn = (HttpURLConnection) restURL.openConnection();
	        //请求方式
	        conn.setRequestMethod(requestMethod);
	        //设置是否从httpUrlConnection读入，默认情况下是true; httpUrlConnection.setDoInput(true);
	        conn.setDoOutput(true);
	        //allowUserInteraction 如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
	        conn.setAllowUserInteraction(false);

	        ps = new PrintStream(conn.getOutputStream());
	        ps.print(outputStr);
	        bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line,resultStr="";
	        while(null != (line=bReader.readLine()))
	        {
	        	resultStr +=line;
	        }
	        System.out.println(resultStr);
	        if(resultStr != null) {
	        	//jsonObject = JsonUtils.parse(resultStr, Map.class);
	        	jsonObject = MapUtils.xmlStr2Map(resultStr);
	        }
		}catch (ConnectException ce) {
        	logger.error("调用HTTP发送请求ConnectException异常", ce);
        } catch (Exception e) {
        	logger.error("调用HTTP发送请求Exception异常", e);
        } finally {
        	if(ps != null) {
        		ps.close();
        	}
        	if(bReader != null) {
        		try {
					bReader.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
        	}
        	if (conn != null) {
                conn.disconnect();
                conn = null;
            }
		}
		
        return jsonObject;
    }
    public static void httpsRequestImage(String requestUrl,String filePage) {
    	OutputStream os = null;
    	InputStream is = null;
    	try {
    		// 构造URL    
            URL url = new URL(requestUrl);    
            // 打开连接    
            URLConnection con = url.openConnection();    
            //设置请求超时为5s    
            con.setConnectTimeout(10*1000);    
            // 输入流    
            is = con.getInputStream();    
            
            // 1K的数据缓冲    
            byte[] bs = new byte[1024];    
            // 读取到的数据长度    
            int len;    
            
           os = new FileOutputStream(filePage);    
            // 开始读取    
            while ((len = is.read(bs)) != -1) {    
              os.write(bs, 0, len);    
            }    
            
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}finally {
			// 完毕，关闭所有链接    
			try {
				if(os != null) {
					os.close();
				}
				if(is != null) {
	            	 is.close();   
	             }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(),e);
			} 
		}
    }
    /**
     * url换取二维码字节流
     * @param requestUrl
     * @param parame
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static InputStream httpsPostParame(String requestUrl, Map<String, Object> parame) {
    	InputStream inputStream = null;
    	try {
    		RestTemplate rest = new RestTemplate();
    		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			HttpEntity requestEntity = new HttpEntity(parame, headers);
			ResponseEntity<byte[]> entity = rest.exchange(requestUrl, HttpMethod.POST, requestEntity, byte[].class,
					new Object[0]);
			logger.info("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
			byte[] result = entity.getBody();
			inputStream = new ByteArrayInputStream(result);
		} catch (Exception e) {
			logger.error("调用小程序生成微信永久小程序码URL接口异常", e);
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("调用小程序生成微信永久小程序码URL接口关闭输入流异常", e);
				}
			}
		}
    	
		return inputStream;
    }
    /**
     * 从输入流读取返回内容
     */
    public static void outputStreamRequest(OutputStream outputStream,String outputStr) throws Exception{
    	// 注意编码格式
        outputStream.write(outputStr.getBytes(WxappConfigUtil.WX_CODING_FORMAT));
        outputStream.close();
    }
    /**
     * 从输入流读取返回内容
     */
    public static String inputStreamRequest(InputStream inputStream){
    	InputStreamReader inputStreamReader = null;
    	BufferedReader bufferedReader = null;
    	StringBuffer buffer = new StringBuffer();
    	try {
    		inputStreamReader = new InputStreamReader(inputStream, WxappConfigUtil.WX_CODING_FORMAT);
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
		} catch (Exception e) {
			logger.error("读取输入流返回内容异常", e);
		} finally {
			try {
				// 释放资源
				if(bufferedReader != null)
	            bufferedReader.close();
				if(inputStreamReader != null)
	            inputStreamReader.close();
				if(inputStream != null)
	            inputStream.close();
	            inputStream = null;
			} catch (Exception e2) {
				logger.error("读取输入流返回内容资源释放异常", e2);
			}
		}
        return buffer.toString();
    }
    
    
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }  
    /**
     * 保存文件到本地服务器
     * @param fileName
     * @param inputStream
     * @return
     */
    public static String saveOutputStream(String fileName,String imagePath,InputStream inputStream) {
    	FileOutputStream fileOut = null;
    	String msg = null;
    	/*try {
    		File sf=new File(WxappConfigUtil.SERVICE_TEMP_IMAGE+imagePath);    
           if(!sf.exists()){    
               sf.mkdirs();    
           } 
    		// 定义一个文件名字进行接收获取文件  
        	fileOut = new FileOutputStream(new File(WxappConfigUtil.SERVICE_TEMP_IMAGE+imagePath+"/"+fileName));  
            byte[] buf = new byte[1024 * 8];  
            while (true) {  
                int read = 0;  
                if (inputStream != null) {  
                    read = inputStream.read(buf);  
                }  
                if (read == -1) {  
                    break;  
                }  
                System.out.println(read);
                fileOut.write(buf, 0, read);  
            }  
            
            // 查看文件获取是否成功  
            msg = WxappConfigUtil.SERVICE_URL_IMAGE+imagePath+"/"+fileName;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			
			try {
				if(inputStream != null) {
					inputStream.close();
				}
				if(fileOut != null) {
					fileOut.flush();
					fileOut.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}  
		}*/
    	return msg;
    }
    
    /**
	 * https请求
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return JSONObject
	 */
	public static JSONObject httpsRequestToJson(String requestUrl,String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			//URL url = new URL(requestUrl);
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			// log.info("http请求后返回的字符串为：" + buffer.toString());
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			logger.error("https请求接口超时");
		} catch (Exception e) {
			logger.error("https请求接口返回错误:", e.getMessage());
		}
		return jsonObject;
	}
	
	/**
	 * http请求
	 * @param requestUrl
	 * @param requestMethod
	 * @return JSONObject
	 */
	public static JSONObject httpRequestToJson(String requestUrl,String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			logger.error("http请求接口超时");
		} catch (Exception e) {
			logger.error("http请求接口返回错误:", e.getMessage());
		}
		return jsonObject;
	}
	
	public static JSONObject requestPostJSONObject(String requestUrl, Map<String, String> param) {
		JSONObject jsonObject = null;
    	try {
    		RestTemplate restTemplate = getRestTemplate();
			HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(param, new HttpHeaders());
			ResponseEntity<String> entity = restTemplate.postForEntity(requestUrl, requestEntity, String.class);
			jsonObject = JSONObject.fromObject(entity);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} 
		return jsonObject;
    }
	
	public static String requestPostXml(String requestUrl, String requestXml) {
		RestTemplate restTemplate = getRestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> requestEntity = new HttpEntity<String>(requestXml,requestHeaders);
		ResponseEntity<String> entity = restTemplate.postForEntity(requestUrl, requestEntity, String.class);
		return entity.getBody();
	}
	
	private static RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
		for (HttpMessageConverter<?> httpMessageConverter : list) {
		    if(httpMessageConverter instanceof StringHttpMessageConverter) {
		        ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("UTF-8"));
		    }
		}
		return restTemplate;
	}
	
    public static String filterEmoji(String source) {
		if (source != null && source.length() > 0) {
			return source.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
		} else {
			return source;
		}
	}
}
