package com.bluekjg.wxapp.controller;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.exception.StoreProcessException;
import com.bluekjg.core.commons.utils.ConstantUtils;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.core.commons.utils.StringUtils;
import com.bluekjg.core.commons.utils.Util;
import com.bluekjg.core.utils.QEncodeUtil;
import com.bluekjg.redis.dao.JedisClient;
import com.bluekjg.redis.key.RedisKeyUtil;
import com.bluekjg.wxapp.model.QrCode;
import com.bluekjg.wxapp.model.ToolResource;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.wx.DataModel;
import com.bluekjg.wxapp.service.IWxUserService;
import com.bluekjg.wxapp.utils.AesCbcUtil;
import com.bluekjg.wxapp.utils.CommonUtil;
import com.bluekjg.wxapp.utils.ParameterInfoUtil;
import com.bluekjg.wxapp.utils.PostObjectSample;
import com.bluekjg.wxapp.utils.QRCodeUtil;
import com.bluekjg.wxapp.utils.RedisUtil;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * @description：数据解码
 * @author：pincui.tom
 * @date：2018/3/23 14:51
 */
@Controller
@RequestMapping("/xcx/wxtool")
public class WxToolController extends BaseController {
	@Autowired
	private IWxUserService wxUserBeanService;
	@Autowired
	private RedisUtil redisUtil;

    /**
     * AES算法解码
     *
     * @param resource
     * @return
     */
    @RequestMapping("/aes")
    @ResponseBody
    public Object aes(@Valid ToolResource resource,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	if(resource != null) {
            	String code = resource.getCode();
    	       	String  iv = resource.getIv();
    	       	String  encryptedData = resource.getEncryptedData();
    	       	code = URLDecoder.decode(code, WxappConfigUtil.WX_CODING_FORMAT);
    	       	iv = URLDecoder.decode(iv, WxappConfigUtil.WX_CODING_FORMAT);
    	       	encryptedData = URLDecoder.decode(encryptedData, WxappConfigUtil.WX_CODING_FORMAT);
    	       	if(code != null && !code.isEmpty() 
            			&& iv != null && !iv.isEmpty() 
            			&& encryptedData != null && !encryptedData.isEmpty()) {
    	       		obj = renderError("Not get sessionKey");
            		String requestUrl = WxappConfigUtil.WX_JSCODE_URL.replace("APPID", WxappConfigUtil.WX_APPID).
                            replace("SECRET", WxappConfigUtil.WX_SECRET).replace("JSCODE", code);
                    // 发起GET请求获取凭证
            		Map<String,String>  jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
                    if (jsonObject != null) {
	               		String sessionKey = jsonObject.get("session_key");
	               		if(sessionKey != null) {
	               			Map<String,String> resultMap = null;
	               			//重复AES解密 5 次，直到解密成功
	               			for(int i = 0; i < 5; i++) {
	               				resultMap = AesCbcUtil.decrypt(encryptedData, sessionKey, iv);
	               				if(resultMap != null) {break;}
	               			}
	               			
	               			obj = renderSuccess(resultMap);
	               		}
                    }
            	} else {
            		obj = renderError("Parameter cannot be empty");
            	}
            }
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    /**
     * 获取openId，sessionKey
     *
     * @param resource
     * @return
     */
    @RequestMapping("/jscode2session")
    @ResponseBody
    public Object jscode2session(@Valid ToolResource resource,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	if(resource != null) {
            	String code = resource.getCode();
    	       	if(code != null && !code.isEmpty()) {
            		String requestUrl = WxappConfigUtil.WX_JSCODE_URL.replace("APPID", WxappConfigUtil.WX_APPID).
                            replace("SECRET", WxappConfigUtil.WX_SECRET).replace("JSCODE", code);
                    // 发起GET请求获取凭证
            		Map<String,String>  jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
                    if (jsonObject != null && jsonObject.get("session_key") != null) {
	               		obj = renderSuccess(jsonObject);
                    }else {
                    	obj = renderError("Not get sessionKey");
                    }
            	} else {
            		obj = renderError("Parameter cannot be empty");
            	}
            }
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    /**
     * 获取access_token
     *
     * @param resource
     * @return
     */
    @RequestMapping("/token")
    @ResponseBody
    public Object token(@Valid ToolResource resource,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	String requestUrl = WxappConfigUtil.WX_TOKEN_URL.replace("APPID", WxappConfigUtil.WX_APPID).
                    replace("SECRET", WxappConfigUtil.WX_SECRET);
            // 发起GET请求获取凭证
    		Map<String,String>  jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
            if (jsonObject != null && jsonObject.get("access_token") != null) {
            	String accessToken = jsonObject.get("access_token");
           		if(accessToken != null) {
           			obj = renderSuccess(jsonObject);
           		}
            }else {
            	obj = renderError("Not get sessionKey");
            }
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 生成小程序二维码
     *
     * @param resource
     * @return
     */
    @RequestMapping("/qrcode")
    @ResponseBody
    public String qrcode(@Valid QrCode code,HttpServletResponse response) {
    	String msg = null;
        try {
        	String requestUrl = WxappConfigUtil.WX_TOKEN_URL.replace("APPID", WxappConfigUtil.WX_APPID).
                    replace("SECRET", WxappConfigUtil.WX_SECRET);
            // 发起GET请求获取凭证
    		Map<String,String>  jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
            if (jsonObject != null && jsonObject.get("access_token") != null) {
            	String accessToken = jsonObject.get("access_token");
           		if(accessToken != null) {
           			String qrcodeUrl = WxappConfigUtil.WX_QRCODE_URL.replace("ACCESS_TOKEN", accessToken);
           			Map<String, Object> param = new HashMap<String, Object>();
           			param.put("page", URLDecoder.decode(code.getPage(), "UTF-8"));
        			param.put("width", code.getWidth());
        			String scene = code.getScene();
        			String key = Util.qrcodeKey();
        			redisUtil.set(RedisKeyUtil.QR_CODE_KEY+":"+key, scene);
        			param.put("scene", key);
        			param.put("auto_color", code.isAuto_color());
        			Map<String, Object> line_color = new HashMap<>();
        			line_color.put("r", code.getR());
        			line_color.put("g", code.getG());
        			line_color.put("b", code.getB());
        			param.put("line_color", line_color);
        			logger.info("调用生成微信URL接口传参:" + param);
        			InputStream inputStream = CommonUtil.httpsPostParame(qrcodeUrl, param);
        			if(inputStream != null) {
    					// 生成新文件名
    					String newName = UUID.randomUUID().toString().replaceAll("-", "");
    					newName = newName + ".png";
    					// 文件上传
    					String imagePath = DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD);
    					// 文件上传
    					Integer result = PostObjectSample.PostObject(imagePath + "/" + newName, "image/png",inputStream);
    					// 返回结果
    					if (result > 0) {
    						msg = WxappConfigUtil.ALICLOUD_IMAGE_BASE_URL + imagePath + "/" + newName;
    					}
        			}
           		}
            }
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return msg;
    }
    /**
     * 二维码生成 （新）
     * @param resource
     * @return
     */
    @RequestMapping("/qrcodeNew")
    @ResponseBody
    public Object qrcodeNew(@Valid QrCode qrCode,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	if(qrCode.getStoreId() == null) {qrCode.setStoreId(0);}
        	JSONStringer code = new JSONStringer();
			code.object();
			code.key("codeType").value(qrCode.getCodeType());//0门店订单核销
			code.key("storeId").value(qrCode.getStoreId());
			code.key("dataId").value(qrCode.getDataId());
			code.endObject();
			String str = QEncodeUtil.aesEncrypt(code.toString(), WxappConfigUtil.WX_ENCRYPT_KEY, WxappConfigUtil.WX_ENCRYPT_IV);
			InputStream in = QRCodeUtil.generateQRCodeStream(str, qrCode.getWidth(), qrCode.getHeight(), "png");
			String imagePath = DateUtil.getCurrentDate("yyyyMMdd") + "/" + System.nanoTime() + ".png";
			Integer result = PostObjectSample.PostObject(imagePath , "application/x-png", in);
			if(result > 0 ) {
				obj = renderSuccess(WxappConfigUtil.ALICLOUD_IMAGE_BASE_URL + imagePath);
			}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        
        return obj;
    }
    /**
	 * 解密
	 */
	@PostMapping("/decrypt")
	@ResponseBody
	public String decrypt(String param) {
		try {
			return QEncodeUtil.aesDecrypt(param, WxappConfigUtil.WX_ENCRYPT_KEY, WxappConfigUtil.WX_ENCRYPT_IV);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	/**
	 * 获取二维码场景值
	 */
	@PostMapping("/codeScene")
	@ResponseBody
	public String codeScene(String key) {
		try {
			String scene  = (String) redisUtil.get(RedisKeyUtil.QR_CODE_KEY+":"+key);
			return scene;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	/**
	 * 核销订单数据
	 */
	@PostMapping("/writeOffData")
	@ResponseBody
	public Object writeOffData(DataModel dataModel) {
		Object obj = renderError("核销失败");
		logger.info("进入扫二维码核销订单数据");
		if (StringUtils.isEmpty(dataModel.getDataId()) || StringUtils.isEmpty(dataModel.getOpenId())
				|| StringUtils.isEmpty(dataModel.getCodeType())) {
			return renderError(ConstantUtils.NO_PARAM);
		}
		Integer status = wxUserBeanService.writeOffOrderStatus(dataModel.getDataId());
		if(status == null || status != 1) {
			return renderError("订单错误");
		}
		UserBean user = wxUserBeanService.selectByOpenId(dataModel.getOpenId());
		if(user == null || user.getStoreId() == null || !user.getStoreId().equals(dataModel.getStoreId())) {
			return renderError("不可核销");
		}
		
		try {
			wxUserBeanService.writeOffData(dataModel);
			obj = renderSuccess("核销成功");
		} catch (StoreProcessException e) {
			logger.error(e.getMessage(),e);
		}
		logger.info("离开扫二维码核销订单数据");
		return obj;
	}

	/**
	 * 获取二维码场景值
	 */
	@PostMapping("/pushMessage")
	@ResponseBody
	public String pushMessage(String key) {
		try {
			String scene  = (String) redisUtil.get(RedisKeyUtil.QR_CODE_KEY+":"+key);
			return scene;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
    
	
	public static void main(String[] arge) {
		WxappConfigUtil.WX_CODING_FORMAT = "UTF-8";
		WxappConfigUtil.WX_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?appid=APPID&secret=SECRET&grant_type=client_credential";
		WxappConfigUtil.WX_APPID = "wxe0439672492bd6ec";
		WxappConfigUtil.WX_SECRET = "853c4eeb7376be55ea0d0ec639fdb7df";
		String requestUrl = WxappConfigUtil.WX_TOKEN_URL.replace("APPID", WxappConfigUtil.WX_APPID).
                replace("SECRET", WxappConfigUtil.WX_SECRET);
        // 发起GET请求获取凭证
		Map<String,String>  jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (jsonObject != null && jsonObject.get("access_token") != null) {
        	String accessToken = jsonObject.get("access_token");
        	System.out.println(accessToken);
       		if(accessToken != null) {
       			String requestUrl1 = "https://api.weixin.qq.com/card/create?access_token=ACCESS_TOKEN";
       			Map<String,String>  jsonObject1 = CommonUtil.httpsRequest(requestUrl1.replace("ACCESS_TOKEN", accessToken), "POST", outputStr());
       			System.out.println(jsonObject1);
       		}
        }
		
	}
	
	public static String outputStr() {
		JSONObject base_info = new JSONObject();
		base_info.put("logo_url", "");//卡券的商户logo，建议像素为300*300
		JSONObject swipe_card = new JSONObject();
		swipe_card.put("create_mid", "1500047572");//商户号
		swipe_card.put("is_swipe_card", true);
		JSONObject pay_info = new JSONObject();
		pay_info.put("swipe_card", swipe_card);
		base_info.put("pay_info", pay_info);
		base_info.put("brand_name", "测试");//商户名字,字数上限为12个汉字。
		/*
		 *	码型： 
		 *"CODE_TYPE_TEXT"文 本 ； 
		 *"CODE_TYPE_BARCODE"一维码
		 * "CODE_TYPE_QRCODE"二维码 
		 * "CODE_TYPE_ONLY_QRCODE",二维码无code显示； 
		 * "CODE_TYPE_ONLY_BARCODE",一维码无code显示；
		 * CODE_TYPE_NONE， 不显示code和条形码类型 
		 */
		base_info.put("code_type", "CODE_TYPE_NONE");
		base_info.put("title", "10元代金券");//卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。
		base_info.put("color", "Color010");//代金券背景颜色
		base_info.put("service_phone", "020-88888888");//商户号码
		base_info.put("description", "不可与其他优惠同享");//卡券使用说明
		JSONObject date_info = new JSONObject();
		date_info.put("type", "DATE_TYPE_FIX_TIME_RANGE");
		date_info.put("begin_timestamp", "1397577600");
		date_info.put("end_timestamp", "1472724261");
		base_info.put("date_info", date_info);//使用日期
		base_info.put("can_share", false);//卡券页面是否可分享
		base_info.put("center_title", "立即使用");//卡券顶部居中的按钮，仅在卡券状 态正常(可以核销)时显示
		base_info.put("center_app_brand_user_name", "gh_7195ea80d2e6@app");//卡券跳转的小程序的user_name，仅可跳转该 公众号绑定的小程序 。
		base_info.put("center_app_brand_pass", "page/index/index");//卡券跳转的小程序的path
		base_info.put("can_give_friend", false);//卡券是否可转赠
		JSONObject sku = new JSONObject();
		sku.put("quantity", 10000);//卡券库存的数量，上限为100000000。
		base_info.put("sku", sku);
		base_info.put("get_limit", 30);//每人可领券的数量限制,不填写默认为50。
		base_info.put("custom_url_name", "立即使用");//自定义跳转外链的入口名字
		base_info.put("custom_url", "http://www.qq.com");//自定义跳转的URL
		base_info.put("custom_url_sub_title", "更多惊喜");//显示在入口右侧的提示语
		base_info.put("promotion_url_name", "更多优惠");//营销场景的自定义入口名称
		base_info.put("promotion_url", "http://www.qq.com");//入口跳转外链的地址链接
		
		
		JSONObject cash = new JSONObject();
		cash.put("card_type", "CASH");
		cash.put("base_info", base_info);
		//cash.put("least_cost", 100);//满减金额,满足条件立减
		cash.put("reduce_cost", 5);//免减金额
		JSONObject card = new JSONObject();
		card.put("card", card);
		return card.toString();
	}
}
