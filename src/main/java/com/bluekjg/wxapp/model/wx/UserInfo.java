package com.bluekjg.wxapp.model.wx;

import java.util.ArrayList;
import java.util.List;

import com.bluekjg.core.commons.utils.RedisUtils;
import com.bluekjg.wxapp.utils.RedisUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户信息
 * @author tim
 *
 */
public class UserInfo {
	
	@JsonIgnore
	private String appId;     //小程序appid
	private Integer storeId;  //门店ID
	@JsonIgnore
	private String appSecret; //小程序密钥
	@JsonIgnore
	private String partnerld;  //商户号
	private String openId;  //小程序openId
	private Integer userType; //用户类型
	
	
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getPartnerld() {
		return partnerld;
	}
	public void setPartnerld(String partnerld) {
		this.partnerld = partnerld;
	}
	
	/**
	 * 获取用户信息
	 * @param userInfo
	 * @return
	 */
	public static UserInfo getUserInfo(UserInfo userInfo){
		UserInfo uInfo = null;
		List<UserInfo> dataList = new ArrayList<UserInfo>();
		for(UserInfo user :  dataList){
			if(user.getAppId().equals(userInfo.getAppId()) || user.getStoreId().equals(userInfo.getStoreId())){
				return user;
			}
		}
		return uInfo;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}
