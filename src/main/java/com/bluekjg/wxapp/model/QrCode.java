package com.bluekjg.wxapp.model;

public class QrCode {
	
	//最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，
	//其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
	private String scene;
	
	//跳转页面路径
	private String page;
	
	//二维码的宽度
	private Integer width;
	private Integer height;
	
	//自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
	private boolean auto_color;
	private boolean hyaline;//背景是否透明(true透明，false不透明)
	private String r;
	
	private String g;
	
	private String b;
	private String appId;
	private String openId;
	private Integer storeId;
	private Integer dataId;
	private Integer codeType;//1优惠券，2抽奖商品，3订单，4预售卡


	public boolean isHyaline() {
		return hyaline;
	}

	public void setHyaline(boolean hyaline) {
		this.hyaline = hyaline;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public Integer getCodeType() {
		return codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	
	public Integer getWidth() {
		return width;
	}

	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public boolean isAuto_color() {
		return auto_color;
	}

	public void setAuto_color(boolean auto_color) {
		this.auto_color = auto_color;
	}

}
