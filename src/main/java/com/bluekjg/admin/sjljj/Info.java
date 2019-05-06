package com.bluekjg.admin.sjljj;

import java.util.List;

public class Info {
	
	  private BasicInfo basic_info;
	  
	  private List<CardInfoList> card_info_list;
	  
	  private CustomInfo custom_info;

	public BasicInfo getBasic_info() {
		return basic_info;
	}

	public void setBasic_info(BasicInfo basic_info) {
		this.basic_info = basic_info;
	}

	public List<CardInfoList> getCard_info_list() {
		return card_info_list;
	}

	public void setCard_info_list(List<CardInfoList> card_info_list) {
		this.card_info_list = card_info_list;
	}

	public CustomInfo getCustom_info() {
		return custom_info;
	}

	public void setCustom_info(CustomInfo custom_info) {
		this.custom_info = custom_info;
	}

}
