package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：订单表实体类
 * @author：pincui.tom
 * @date：2018/9/26 14:51
 */
@TableName("t_evm_order")
public class WxOrder implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer rid;
	/**
	 * 下单人
	 */
	private String openId;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 退款单号
	 */
	private String refundNo;
	/**
	 * 门店ID
	 */
	private Integer storeId;
	/**
	 * 收货地址ID
	 */
	private Integer addressId;
	/**
	 * 订单总额
	 */
	private Double totalBalances;
	/**
	 * 付款方式（0微信支付，1到店付款）
	 */
	private Integer payMoneyStyle;
	/**
	 * 取货方式（0物流配送，1到店取货）
	 */
	private Integer takeStyle;
	/**
	 * 订单状态（0待付款，1待发货，2待取货，3待退款 , 4-已退款，5订单取消，6订单完成，7待退货 ）
	 */
	private Integer status;
	private String orderStatus;
	
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 订单类型（0-普通，1-秒杀，2-拼团，3-特价，4-积分兑换）
	 */
	private Integer orderType;
	/**
	 * 活动ID
	 */
	private Integer activityId;
	/**
	 * 拼团ID
	 */
	private Integer collageId;
	private Integer goodsCollageId;
	private Integer collageNum;
	private Integer collageTime;
	/**
	 * 核销码
	 */
	private String writeOffCode;
	/**
	 * 核销人openID
	 */
	private String writeOffOpenId;
	/**
	 * 优惠金额
	 */
	private Double disountPrice;
	/**
	 * 优惠券ID
	 */
	private Integer couponId;
	/**
	 * 优惠券名称
	 */
	private String couponName;
	/**
	 * 优惠券是否与活动共享
	 * 0不共享，1共享
	 */
	private Integer isActivityShared;
	
	/**
	 * 砍价ID
	 */
	private Integer bargainId;
	/**
	 * 订单描述
	 */
	private String orderDesc;
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	
	private WxOrderAddress address;
	private List<WxOrderDetail> details;
	private WxOrderTrans trans;
	private WxStore store;
	private WxCollageGoods collage;
	private List<WxActivityFull> preList;
	private List<WxActivityFull> giftList;
	private List<WxActivityFull> discountList;
	private WxOrderRefund refund;
	/**
	 * 是否允许退货（0允许，1不允许）
	 */
	private Integer returnGoods;
	private String userName;
	/**
	 * 订单二维码
	 */
	private String qrCode;
	//订单积分
	private Integer integral;
	//会员剩余积分
	private Integer userIntegral;
	
	public Integer getGoodsCollageId() {
		return goodsCollageId;
	}
	public void setGoodsCollageId(Integer goodsCollageId) {
		this.goodsCollageId = goodsCollageId;
	}
	public Integer getIsActivityShared() {
		return isActivityShared;
	}
	public void setIsActivityShared(Integer isActivityShared) {
		this.isActivityShared = isActivityShared;
	}
	public List<WxActivityFull> getDiscountList() {
		return discountList;
	}
	public void setDiscountList(List<WxActivityFull> discountList) {
		this.discountList = discountList;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	public WxOrderRefund getRefund() {
		return refund;
	}
	public void setRefund(WxOrderRefund refund) {
		this.refund = refund;
	}
	public Integer getUserIntegral() {
		return userIntegral;
	}
	public void setUserIntegral(Integer userIntegral) {
		this.userIntegral = userIntegral;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public List<WxActivityFull> getPreList() {
		return preList;
	}
	public void setPreList(List<WxActivityFull> preList) {
		this.preList = preList;
	}
	public List<WxActivityFull> getGiftList() {
		return giftList;
	}
	public void setGiftList(List<WxActivityFull> giftList) {
		this.giftList = giftList;
	}
	public WxCollageGoods getCollage() {
		return collage;
	}
	public void setCollage(WxCollageGoods collage) {
		this.collage = collage;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getReturnGoods() {
		return returnGoods;
	}
	public void setReturnGoods(Integer returnGoods) {
		this.returnGoods = returnGoods;
	}
	public WxStore getStore() {
		return store;
	}
	public void setStore(WxStore store) {
		this.store = store;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Double getTotalBalances() {
		return totalBalances;
	}
	public void setTotalBalances(Double totalBalances) {
		this.totalBalances = totalBalances;
	}
	public Integer getPayMoneyStyle() {
		return payMoneyStyle;
	}
	public void setPayMoneyStyle(Integer payMoneyStyle) {
		this.payMoneyStyle = payMoneyStyle;
	}
	public Integer getTakeStyle() {
		return takeStyle;
	}
	public void setTakeStyle(Integer takeStyle) {
		this.takeStyle = takeStyle;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	
	public Integer getCollageId() {
		return collageId;
	}
	public void setCollageId(Integer collageId) {
		this.collageId = collageId;
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
	public String getWriteOffCode() {
		return writeOffCode;
	}
	public void setWriteOffCode(String writeOffCode) {
		this.writeOffCode = writeOffCode;
	}
	public String getWriteOffOpenId() {
		return writeOffOpenId;
	}
	public void setWriteOffOpenId(String writeOffOpenId) {
		this.writeOffOpenId = writeOffOpenId;
	}
	public Double getDisountPrice() {
		return disountPrice;
	}
	public void setDisountPrice(Double disountPrice) {
		this.disountPrice = disountPrice;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
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
	public WxOrderAddress getAddress() {
		return address;
	}
	public void setAddress(WxOrderAddress address) {
		this.address = address;
	}
	public List<WxOrderDetail> getDetails() {
		return details;
	}
	public void setDetails(List<WxOrderDetail> details) {
		this.details = details;
	}
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public WxOrderTrans getTrans() {
		return trans;
	}
	public void setTrans(WxOrderTrans trans) {
		this.trans = trans;
	}
	public Integer getBargainId() {
		return bargainId;
	}
	public void setBargainId(Integer bargainId) {
		this.bargainId = bargainId;
	}
	
	
}
