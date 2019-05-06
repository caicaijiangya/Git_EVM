package com.bluekjg.wxapp.model;

import java.io.Serializable;

/**
 * @description：访问日志实体类
 * @author：pincui.tom
 * @date：2019/03/05 14:51
 */
public class AccessLog implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String openId;
	private Integer goodsId;
	private Integer specId;
	
	private Integer activityId;
	private Integer activityType;
	private Integer newOld;
	private Integer type;//0访问，1分享
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getActivityType() {
		return activityType;
	}
	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
	public Integer getNewOld() {
		return newOld;
	}
	public void setNewOld(Integer newOld) {
		this.newOld = newOld;
	}
	
}
