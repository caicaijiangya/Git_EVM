package com.bluekjg.wxapp.utils;

import com.bluekjg.admin.model.vo.RParameterInfo;
import com.bluekjg.core.commons.scan.SpringUtils;
import com.bluekjg.core.commons.utils.JsonUtils;
import com.bluekjg.core.commons.utils.StringUtils;
import com.bluekjg.core.utils.QEncodeUtil;
import com.bluekjg.redis.dao.JedisClient;
import com.bluekjg.redis.key.RedisKey;
import com.bluekjg.wxapp.service.IWxParameterInfoService;

public class ParameterInfoUtil {
	
	/**
	 * 获取指定数据
	 * @param appId
	 * @return
	 */
	public static RParameterInfo getParamInfo(String appId){
		RParameterInfo paramInfo = null;
		JedisClient jedisClient = null;
	    jedisClient = SpringUtils.getBean("jedisClient", JedisClient.class);
		String json = "";
		try {
			json = jedisClient.get(RedisKey.NEW_RETAIL_PARAMETER_APPID+appId);
		} catch (Exception e) {
			paramInfo = getInfo(appId,jedisClient);
		}
		if(!StringUtils.isEmpty(json)){
			paramInfo = JsonUtils.parse(json, RParameterInfo.class);
		}else{
			paramInfo = getInfo(appId,jedisClient);
		}
		return paramInfo;
	}
	
	/**
	 * 获取信息  防止reids中断异常
	 * @param appId
	 * @param jedisClient
	 * @return
	 */
	public static RParameterInfo getInfo(String appId,JedisClient jedisClient){
		RParameterInfo paramInfo = null;
		try {
			//获取数据库内容
			IWxParameterInfoService parameterInfoService = SpringUtils.getBean(IWxParameterInfoService.class);
		    paramInfo = parameterInfoService.getParameterInfoByAppId(appId);
		    if(paramInfo!=null){
				String paramJson = JsonUtils.toJson(paramInfo);
				try {
					jedisClient.set(RedisKey.NEW_RETAIL_PARAMETER_APPID+appId, paramJson);
				} catch (Exception e1) {
					return paramInfo;
				}
			}
			return paramInfo;
		} catch (Exception e) {
			return paramInfo;
		}
	}
	
	
	/**
	 * 获取商户号
	 * @param appId
	 * @return
	 */
	public static String getPartnerld(String appId){
		if(StringUtils.isEmpty(appId)){
			return "";
		}
		RParameterInfo paramInfo = getParamInfo(appId);
		if(paramInfo==null){
			return "";
		}
		String partnerld  = paramInfo.getPartnerld();
		if(StringUtils.isEmpty(partnerld)){
			return "";
		}
		try {
			String decPar = QEncodeUtil.aesDecrypt(partnerld, WxappConfigUtil.WX_ENCRYPT_KEY, WxappConfigUtil.WX_ENCRYPT_IV);
			return decPar;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 获取商户秘钥
	 * @param appId
	 * @return
	 */
	public static String getMerchantKey(String appId){
		if(StringUtils.isEmpty(appId)){
			return "";
		}
		RParameterInfo paramInfo = getParamInfo(appId);
		if(paramInfo==null){
			return "";
		}
		String merchantKey  = paramInfo.getMerchantKey();
		if(StringUtils.isEmpty(merchantKey)){
			return "";
		}
		try {
			String decMerchantKey = QEncodeUtil.aesDecrypt(merchantKey, WxappConfigUtil.WX_ENCRYPT_KEY, WxappConfigUtil.WX_ENCRYPT_IV);
			return decMerchantKey;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 获取秘钥
	 * @param appId
	 * @return
	 */
	public static String getAppsecret(String appId){
		if(StringUtils.isEmpty(appId)){
			return "";
		}
		RParameterInfo paramInfo = getParamInfo(appId);
		if(paramInfo==null){
			return "";
		}
		String secret  = paramInfo.getAppsecret();
		if(StringUtils.isEmpty(secret)){
			return "";
		}
		try {
			String decSec = QEncodeUtil.aesDecrypt(secret, WxappConfigUtil.WX_ENCRYPT_KEY, WxappConfigUtil.WX_ENCRYPT_IV);
			return decSec;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 获取公众号APPID
	 * @param appId
	 * @return
	 */
	public static String getWxAppId(String appId){
		if(StringUtils.isEmpty(appId)){
			return "";
		}
		RParameterInfo paramInfo = getParamInfo(appId);
		if(paramInfo==null){
			return "";
		}
		String wxAppId  = paramInfo.getWxAppid();
		if(StringUtils.isEmpty(wxAppId)){
			return "";
		}
		try {
			String decSec = QEncodeUtil.aesDecrypt(wxAppId, WxappConfigUtil.WX_ENCRYPT_KEY, WxappConfigUtil.WX_ENCRYPT_IV);
			return decSec;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 获取公众号APPID
	 * @param appId
	 * @return
	 */
	public static String getWxAppsecret(String appId){
		if(StringUtils.isEmpty(appId)){
			return "";
		}
		RParameterInfo paramInfo = getParamInfo(appId);
		if(paramInfo==null){
			return "";
		}
		String wxAppsecret  = paramInfo.getWxAppsecret();
		if(StringUtils.isEmpty(wxAppsecret)){
			return "";
		}
		try {
			String decSec = QEncodeUtil.aesDecrypt(wxAppsecret, WxappConfigUtil.WX_ENCRYPT_KEY, WxappConfigUtil.WX_ENCRYPT_IV);
			return decSec;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 获取公小程序原始ID
	 * @param appId
	 * @return
	 */
	public static String getBrandUserName(String appId){
		if(StringUtils.isEmpty(appId)){
			return "";
		}
		RParameterInfo paramInfo = getParamInfo(appId);
		if(paramInfo==null){
			return "";
		}
		String brandUserName  = paramInfo.getBrandUserName();
		if(StringUtils.isEmpty(brandUserName)){
			return "";
		}
		try {
			String decSec = QEncodeUtil.aesDecrypt(brandUserName, WxappConfigUtil.WX_ENCRYPT_KEY, WxappConfigUtil.WX_ENCRYPT_IV);
			return decSec;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 获取跳入小程序路径
	 * @param appId
	 * @return
	 */
	public static String getBrandPass(String appId){
		if(StringUtils.isEmpty(appId)){
			return "";
		}
		RParameterInfo paramInfo = getParamInfo(appId);
		if(paramInfo==null){
			return "";
		}
		String brandPass = paramInfo.getBrandPass();
		if(StringUtils.isEmpty(brandPass)){
			return "";
		}
		try {
			String decSec = QEncodeUtil.aesDecrypt(brandPass, WxappConfigUtil.WX_ENCRYPT_KEY, WxappConfigUtil.WX_ENCRYPT_IV);
			return decSec;
		} catch (Exception e) {
			return "";
		}
	}
	

	
	
}
