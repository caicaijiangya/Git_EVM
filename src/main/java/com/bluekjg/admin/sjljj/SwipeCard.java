package com.bluekjg.admin.sjljj;

import java.util.List;

public class SwipeCard {
	
	private List<String> use_mid_list;  //适用商家商户号列表，创建卡券后将发送通知至适用商户号，需制券商家激活。
	
	private String create_mid;  //创建代金券的商户号，可登录公众平台-微信支付查看。需创建代金券的商户号登录支付后台进行激活。
	
	private boolean is_swipe_card;  //卡券提交前需检查是否有传："pay_info"、"is_swipe_card": true 字段

	public List<String> getUse_mid_list() {
		return use_mid_list;
	}

	public void setUse_mid_list(List<String> use_mid_list) {
		this.use_mid_list = use_mid_list;
	}

	public String getCreate_mid() {
		return create_mid;
	}

	public void setCreate_mid(String create_mid) {
		this.create_mid = create_mid;
	}

	public boolean isIs_swipe_card() {
		return is_swipe_card;
	}

	public void setIs_swipe_card(boolean is_swipe_card) {
		this.is_swipe_card = is_swipe_card;
	}

}
 