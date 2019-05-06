package com.bluekjg.admin.sjljj;

public class BasicInfo {

	private String activity_bg_color;  //活动封面的背景颜色
	
	private String activity_tinyappid;  //用户点击链接后可静默添加到列表的小程序appid；
	
	private Integer begin_time;  //活动开始时间
	 
	private Integer end_time;  //活动结束时间
	
	private Integer gift_num;  //单个礼包社交立减金数量（3-15个）
	
	private Integer max_partic_times_act;  //每个用户活动期间最大领取次数,最大为50，不填默认为1
	
	private Integer max_partic_times_one_day;  //每个用户活动期间单日最大领取次数,最大为50，不填默认为1
	
	private String mch_code;  //支付商户号

	public String getActivity_bg_color() {
		return activity_bg_color;
	}

	public void setActivity_bg_color(String activity_bg_color) {
		this.activity_bg_color = activity_bg_color;
	}

	public String getActivity_tinyappid() {
		return activity_tinyappid;
	}

	public void setActivity_tinyappid(String activity_tinyappid) {
		this.activity_tinyappid = activity_tinyappid;
	}

	public Integer getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Integer begin_time) {
		this.begin_time = begin_time;
	}

	public Integer getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Integer end_time) {
		this.end_time = end_time;
	}

	public Integer getGift_num() {
		return gift_num;
	}

	public void setGift_num(Integer gift_num) {
		this.gift_num = gift_num;
	}

	public Integer getMax_partic_times_act() {
		return max_partic_times_act;
	}

	public void setMax_partic_times_act(Integer max_partic_times_act) {
		this.max_partic_times_act = max_partic_times_act;
	}

	public Integer getMax_partic_times_one_day() {
		return max_partic_times_one_day;
	}

	public void setMax_partic_times_one_day(Integer max_partic_times_one_day) {
		this.max_partic_times_one_day = max_partic_times_one_day;
	}

	public String getMch_code() {
		return mch_code;
	}

	public void setMch_code(String mch_code) {
		this.mch_code = mch_code;
	}
	
	
}
