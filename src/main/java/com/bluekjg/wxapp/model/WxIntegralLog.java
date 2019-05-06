package com.bluekjg.wxapp.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：积分记录表实体类
 * @author：pincui.tom
 * @date：2018/9/26 14:51
 */
@TableName("t_evm_wx_user_integral_log")
public class WxIntegralLog implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer rid;
	/**
	 * openid
	 */
	private String openId;
	/**
	 * 描述
	 */
	private String title;
	/**
	 * 积分
	 */
	private Double integral;
	/**
	 * 创建日期
	 */
	private String createdTime;
	/**
	 * 类型（-1退货返还，0签到，1兑换，2会员注册，3个人资料完善，4消费积分，5分享积分，6邀请好友，10系统赠送）
	 */
	private Integer type;
	
	private String note;
	
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getIntegral() {
		return integral;
	}
	public void setIntegral(Double integral) {
		this.integral = integral;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	
}
