package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：活动商品拼团表实体类
 * @author：pincui.tom
 * @date：2018/12/10 14:51
 */
@TableName("t_evm_activity_goods_collage")
public class WxActivityGoodsCollage implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 活动商品ID
	 */
	private Integer activityGoodsId;
	private Integer activityType;
	/**
	 * 拼团人数
	 */
	private Integer collageNum;
	/**
	 * 拼团时长（默认24小时）
	 */
	private Integer collageTime;
	/**
	 * 拼团价格
	 */
	private Double collagePrice;
	
	
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	
	public Integer getActivityType() {
		return activityType;
	}
	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActivityGoodsId() {
		return activityGoodsId;
	}
	public void setActivityGoodsId(Integer activityGoodsId) {
		this.activityGoodsId = activityGoodsId;
	}
	public Integer getCollageNum() {
		return collageNum;
	}
	public void setCollageNum(Integer collageNum) {
		this.collageNum = collageNum;
	}
	public Integer getCollageTime() {
		return collageTime;
	}
	public void setCollageTime(Integer collageTime) {
		this.collageTime = collageTime;
	}
	public Double getCollagePrice() {
		return collagePrice;
	}
	public void setCollagePrice(Double collagePrice) {
		this.collagePrice = collagePrice;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastModifiedTime() {
		return LastModifiedTime;
	}
	public void setLastModifiedTime(String lastModifiedTime) {
		LastModifiedTime = lastModifiedTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
