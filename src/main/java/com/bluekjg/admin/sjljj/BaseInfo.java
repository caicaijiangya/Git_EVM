package com.bluekjg.admin.sjljj;

/**
 * 卡券基础信息
 * @author tim
 *
 */
public class BaseInfo {
	
	
	//必填字段
	private String logo_url;  //卡券的商户logo，建议像素为300*300
	
	private String brand_name;  //商户名字,字数上限为12个汉字。
	
	private String code_type;
	
	private PayInfo pay_info;
	
	private String title;  //卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)
	
	private String color;  //券颜色。按色彩规范标注填写Color010-Color100。
	
	private String service_phone;   //卡券使用提醒，字数上限为16个汉字。
	
	private String description;  //卡券使用说明，字数上限为1024个汉字
	
	private Sku sku;  //商品信息
	
	private DateInfo date_info;  //使用日期，有效期的信息。
	
	private boolean can_share;
	
	private String center_title;  //立即使用
	
	private String center_app_brand_user_name;  //原始ID+@app
	
	private String center_app_brand_pass;  //配置代金券跳转小程序path
	
	private boolean can_give_friend;
	
	private Integer get_limit;
	
//	private String custom_url_name;
	
//	private String custom_url;
	
//	private String custom_url_sub_title;
	
//	private String promotion_url_name;
	
//	private String promotion_url;

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getCode_type() {
		return code_type;
	}

	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getService_phone() {
		return service_phone;
	}

	public void setService_phone(String service_phone) {
		this.service_phone = service_phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public DateInfo getDate_info() {
		return date_info;
	}

	public void setDate_info(DateInfo date_info) {
		this.date_info = date_info;
	}

	public boolean isCan_share() {
		return can_share;
	}

	public void setCan_share(boolean can_share) {
		this.can_share = can_share;
	}

	public String getCenter_title() {
		return center_title;
	}

	public void setCenter_title(String center_title) {
		this.center_title = center_title;
	}

	public String getCenter_app_brand_user_name() {
		return center_app_brand_user_name;
	}

	public void setCenter_app_brand_user_name(String center_app_brand_user_name) {
		this.center_app_brand_user_name = center_app_brand_user_name;
	}

	public String getCenter_app_brand_pass() {
		return center_app_brand_pass;
	}

	public void setCenter_app_brand_pass(String center_app_brand_pass) {
		this.center_app_brand_pass = center_app_brand_pass;
	}

	public boolean isCan_give_friend() {
		return can_give_friend;
	}

	public void setCan_give_friend(boolean can_give_friend) {
		this.can_give_friend = can_give_friend;
	}

	public Integer getGet_limit() {
		return get_limit;
	}

	public void setGet_limit(Integer get_limit) {
		this.get_limit = get_limit;
	}

//	public String getCustom_url_name() {
//		return custom_url_name;
//	}
//
//	public void setCustom_url_name(String custom_url_name) {
//		this.custom_url_name = custom_url_name;
//	}
//
//	public String getCustom_url() {
//		return custom_url;
//	}
//
//	public void setCustom_url(String custom_url) {
//		this.custom_url = custom_url;
//	}
//
//	public String getCustom_url_sub_title() {
//		return custom_url_sub_title;
//	}
//
//	public void setCustom_url_sub_title(String custom_url_sub_title) {
//		this.custom_url_sub_title = custom_url_sub_title;
//	}
//
//	public String getPromotion_url_name() {
//		return promotion_url_name;
//	}
//
//	public void setPromotion_url_name(String promotion_url_name) {
//		this.promotion_url_name = promotion_url_name;
//	}
//
//	public String getPromotion_url() {
//		return promotion_url;
//	}
//
//	public void setPromotion_url(String promotion_url) {
//		this.promotion_url = promotion_url;
//	}

	public PayInfo getPay_info() {
		return pay_info;
	}

	public void setPay_info(PayInfo pay_info) {
		this.pay_info = pay_info;
	}
	
}
