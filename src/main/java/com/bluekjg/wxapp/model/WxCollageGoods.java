package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：拼团表实体类
 * @author：pincui.tom
 * @date：2018/9/26 14:51
 */
@TableName("t_evm_collage_goods")
public class WxCollageGoods implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 活动ID
	 */
	private Integer activityId;
	/**
	 * 商品ID
	 */
	private Integer goodsId;
	private Integer specId;
	/**
	 * 拼团ID
	 */
	private Integer collageId;
	/**
	 * 已参团人数
	 */
	private Integer joinNum;
	/**
	 * 总拼团人数
	 */
	private Integer collageNum;
	private Integer collageTime;
	/**
	 * 剩余时间
	 */
	private String time;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 状态（0开启中，1拼团成功，2拼团失败）
	 */
	private Integer status;
	/**
	 * 团长姓名
	 */
	private String userName;
	/**
	 * 团长头像
	 */
	private String headImgUrl;
	
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	private Integer isBuy;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	private Double goodsPrice;
	private Double activityPrice;
	private String goodsName;
	private String goodsDesc;
	private String goodsImages;
	private Integer orderNum;
	private String openId;
	private Integer orderId;
	private Integer storeId;
	private Integer goodsCollageId;
	private List<WxCollageGoodsDetail> details;
	
	
	public Integer getGoodsCollageId() {
		return goodsCollageId;
	}
	public void setGoodsCollageId(Integer goodsCollageId) {
		this.goodsCollageId = goodsCollageId;
	}
	public Integer getCollageTime() {
		return collageTime;
	}
	public void setCollageTime(Integer collageTime) {
		this.collageTime = collageTime;
	}
	public Integer getCollageId() {
		return collageId;
	}
	public void setCollageId(Integer collageId) {
		this.collageId = collageId;
	}
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getIsBuy() {
		return isBuy;
	}
	public void setIsBuy(Integer isBuy) {
		this.isBuy = isBuy;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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
	public String getGoodsImages() {
		return goodsImages;
	}
	public void setGoodsImages(String goodsImages) {
		this.goodsImages = goodsImages;
	}
	public List<WxCollageGoodsDetail> getDetails() {
		return details;
	}
	public void setDetails(List<WxCollageGoodsDetail> details) {
		this.details = details;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getJoinNum() {
		return joinNum;
	}
	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
	}
	public Integer getCollageNum() {
		return collageNum;
	}
	public void setCollageNum(Integer collageNum) {
		this.collageNum = collageNum;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
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
