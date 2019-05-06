package com.bluekjg.admin.sjljj;

public class CardInfoList {
	
	private String card_id;  //卡券ID
	
	private Integer min_amt;  //最少支付金额，单位是分
	
	//private String membership_appid;  //奖品指定的会员卡appid。如用户标签有选择商户会员，则需要填写会员卡appid，该appid需要跟所有发放商户号有绑定关系。
	
	private boolean total_user;

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public Integer getMin_amt() {
		return min_amt;
	}

	public void setMin_amt(Integer min_amt) {
		this.min_amt = min_amt;
	}

	public boolean isTotal_user() {
		return total_user;
	}

	public void setTotal_user(boolean total_user) {
		this.total_user = total_user;
	}

//	public String getMembership_appid() {
//		return membership_appid;
//	}
//
//	public void setMembership_appid(String membership_appid) {
//		this.membership_appid = membership_appid;
//	}

}
