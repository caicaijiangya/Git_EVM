package com.bluekjg.wxapp.model;

/**
 * @description：数据解析接口参数
 * @author：pincui.tom
 * @date：2018/3/26 14:51
 */
public class ToolResource {
	//用户code
	private String code;
	//密文内容
	private String encryptedData;
	//密文偏移量
	private String iv;
	//页面地址
	private String page;
	//参数
	private String scene;
	private String image;
	private Integer type;
	private String name;
	private Integer animation;
	private Double startX;
	private Double startY;
	private Double endX;
	private Double endY;
	private Double rate;//倍率
	
	//消息推送
	private String formId;
	private String state;
	private String access_token;
	private String expires_in;
	private String token_type;
	private String scope;
	private String refresh_token;
	private String error;
	private String error_description;
	
	private String appId;  //小程序appId
	
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getError_description() {
		return error_description;
	}
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getStartX() {
		return startX;
	}
	public void setStartX(Double startX) {
		this.startX = startX;
	}
	public Double getStartY() {
		return startY;
	}
	public void setStartY(Double startY) {
		this.startY = startY;
	}
	public Double getEndX() {
		return endX;
	}
	public void setEndX(Double endX) {
		this.endX = endX;
	}
	public Double getEndY() {
		return endY;
	}
	public void setEndY(Double endY) {
		this.endY = endY;
	}
	public Integer getAnimation() {
		return animation;
	}
	public void setAnimation(Integer animation) {
		this.animation = animation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getScene() {
		return scene;
	}
	public void setScene(String scene) {
		this.scene = scene;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEncryptedData() {
		return encryptedData;
	}
	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	public String getIv() {
		return iv;
	}
	public void setIv(String iv) {
		this.iv = iv;
	}
	
}
