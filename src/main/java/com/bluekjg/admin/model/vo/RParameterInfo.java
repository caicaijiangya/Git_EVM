package com.bluekjg.admin.model.vo;

/**
 * redis使用 参数
 * @author tim
 *
 */
public class RParameterInfo {
	
	/**
     * 小程序AppId
     */
	private String appid;
    /**
     * 小程序商户号
     */
	private String partnerld;
    /**
     * 小程序密钥
     */
	private String appsecret;
	
	/**
	 * 商户密钥
	 */
	private String merchantKey;
    /**
     * 门店ID
     */
	private Integer storeId;
	
	/**
	 * 小程序openId
	 */
	private String openId;
	
	/**
	 * 微信appid
	 */
	private String wxAppid;
	
	/**
	 * 微信密钥
	 */
	private String wxAppsecret;
	
	/**
	 * 小程序原始ID
	 */
	private String  brandUserName;
	
	/**
	 * 跳入小程序路径
	 */
	private String brandPass;
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPartnerld() {
		return partnerld;
	}
	public void setPartnerld(String partnerld) {
		this.partnerld = partnerld;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getMerchantKey() {
		return merchantKey;
	}
	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getWxAppid() {
		return wxAppid;
	}
	public void setWxAppid(String wxAppid) {
		this.wxAppid = wxAppid;
	}
	public String getWxAppsecret() {
		return wxAppsecret;
	}
	public void setWxAppsecret(String wxAppsecret) {
		this.wxAppsecret = wxAppsecret;
	}
	public String getBrandUserName() {
		return brandUserName;
	}
	public void setBrandUserName(String brandUserName) {
		this.brandUserName = brandUserName;
	}
	public String getBrandPass() {
		return brandPass;
	}
	public void setBrandPass(String brandPass) {
		this.brandPass = brandPass;
	}
}
