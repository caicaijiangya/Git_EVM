package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：砍价表实体类
 * @author：pincui.tom
 * @date：2018/12/10 14:51
 */
@TableName("t_evm_bargain_goods")
public class WxBargainGoods implements Serializable{
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
	/**
	 * 已砍金额
	 */
	private Double joinPrice;
	/**
	 * 已砍人数
	 */
	private Integer joinNum;
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
	 * 状态（0开启中，1砍价结束，2已使用
	 */
	private Integer status;
	/**
	 * 帮主姓名
	 */
	private String userName;
	/**
	 * 帮主头像
	 */
	private String headImgUrl;
	
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	private Integer timeNum;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	private String qrCode;
	private Double goodsPrice;
	private Double activityPrice;
	private String goodsName;
	private String goodsImages;
	private String openId;
	private String classifyId;
	private Integer orderId;
	private Integer bargainType;
	private Integer storeId;
	private Integer specId;
	private List<WxBargainGoodsDetail> details;
	private List<WxActivityGoodsBargainLadder> ladderList;
	
	
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
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public Integer getBargainType() {
		return bargainType;
	}
	public void setBargainType(Integer bargainType) {
		this.bargainType = bargainType;
	}
	public List<WxActivityGoodsBargainLadder> getLadderList() {
		return ladderList;
	}
	public void setLadderList(List<WxActivityGoodsBargainLadder> ladderList) {
		this.ladderList = ladderList;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}
	public Integer getJoinNum() {
		return joinNum;
	}
	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
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
	public Double getJoinPrice() {
		return joinPrice;
	}
	public void setJoinPrice(Double joinPrice) {
		this.joinPrice = joinPrice;
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
	
	
	public Integer getTimeNum() {
		return timeNum;
	}
	public void setTimeNum(Integer timeNum) {
		this.timeNum = timeNum;
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
	public String getGoodsImages() {
		return goodsImages;
	}
	public void setGoodsImages(String goodsImages) {
		this.goodsImages = goodsImages;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public List<WxBargainGoodsDetail> getDetails() {
		return details;
	}
	public void setDetails(List<WxBargainGoodsDetail> details) {
		this.details = details;
	}
	
	
	
}
