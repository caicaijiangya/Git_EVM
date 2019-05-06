package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：商品表实体类
 * @author：pincui.tom
 * @date：2018/3/26 14:51
 */
@TableName("t_evm_goods")
public class WxGoodsIndex implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer rid;
	/**
	 * 规格ID
	 */
	private Integer specId;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品描述
	 */
	private String goodsDesc;
	/**
	 * 商品价格
	 */
	private Double goodsPrice;
	/**
	 * 活动ID
	 */
	private Integer activityId;
	/**
	 * 活动价格
	 */
	private Double activityPrice;
	/**
	 * 活动库存
	 */
	private Integer activityAmount;
	/**
	 * 活动类型：1秒杀，2拼团，3特价
	 */
	private Integer activityType;
	/**
	 * 商品图片
	 */
	private String goodsImages;
	/**
	 * 商品总销量
	 */
	private String activityImages;
	/**
	 * 尺寸
	 */
	private Integer size;
	private String activityTime;
	private String activitySTime;
	private String activityStartTime;
	private String activityEndTime;
	//活动是否开始（0已开始，1未开始）
	private Integer isStart;
	
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	public String getActivitySTime() {
		return activitySTime;
	}
	public void setActivitySTime(String activitySTime) {
		this.activitySTime = activitySTime;
	}
	public Integer getIsStart() {
		return isStart;
	}
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}
	public String getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}
	public String getActivityStartTime() {
		return activityStartTime;
	}
	public void setActivityStartTime(String activityStartTime) {
		this.activityStartTime = activityStartTime;
	}
	public String getActivityEndTime() {
		return activityEndTime;
	}
	public void setActivityEndTime(String activityEndTime) {
		this.activityEndTime = activityEndTime;
	}
	public Integer getActivityType() {
		return activityType;
	}
	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
	public Integer getActivityAmount() {
		return activityAmount;
	}
	public void setActivityAmount(Integer activityAmount) {
		this.activityAmount = activityAmount;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Double getActivityPrice() {
		return activityPrice;
	}
	public void setActivityPrice(Double activityPrice) {
		this.activityPrice = activityPrice;
	}
	public String getGoodsImages() {
		return goodsImages;
	}
	public void setGoodsImages(String goodsImages) {
		this.goodsImages = goodsImages;
	}
	public String getActivityImages() {
		return activityImages;
	}
	public void setActivityImages(String activityImages) {
		this.activityImages = activityImages;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
	
}
