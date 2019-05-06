package com.bluekjg.admin.sjljj;

/**
 * 使用日期，有效期的信息
 * @author tim
 *
 */
public class DateInfo {
	
	
	//必填字段
	private String type;  //使用时间的类型  DATE_TYPE_FIX _TIME_RANGE 表示固定日期区间，DATETYPE FIX_TERM 表示固定时长 （自领取后按天算。
	
	private Integer begin_timestamp;  //起用时间
	
	private Integer end_timestamp;  //结束时间
	
	//private Integer fixed_term;  //	type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，不支持填写0。

	//private Integer fixed_begin_term;  //type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效，领取后当天生效填写0。（单位为天）

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getBegin_timestamp() {
		return begin_timestamp;
	}

	public void setBegin_timestamp(Integer begin_timestamp) {
		this.begin_timestamp = begin_timestamp;
	}

	public Integer getEnd_timestamp() {
		return end_timestamp;
	}

	public void setEnd_timestamp(Integer end_timestamp) {
		this.end_timestamp = end_timestamp;
	}

//	public Integer getFixed_term() {
//		return fixed_term;
//	}
//
//	public void setFixed_term(Integer fixed_term) {
//		this.fixed_term = fixed_term;
//	}
//
//	public Integer getFixed_begin_term() {
//		return fixed_begin_term;
//	}
//
//	public void setFixed_begin_term(Integer fixed_begin_term) {
//		this.fixed_begin_term = fixed_begin_term;
//	}
	
}
