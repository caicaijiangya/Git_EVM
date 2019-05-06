package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：订单退货退款表实体类
 * @author：pincui.tom
 * @date：2018/9/26 14:51
 */
@TableName("t_evm_order_refund")
public class WxOrderRefund implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 订单Id
	 */
	private Integer orderId;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 退款ID
	 */
	private Integer refundId;
	/**
	 * 支付单号
	 */
	private String transNo;
	/**
	 * 退款号
	 */
	private String refundNo;
	/**
	 * 退款名称
	 */
	private String refundName;
	/**
	 * 退款金额
	 */
	private BigDecimal balances;
	/**
	 * 已退金额
	 */
	private BigDecimal refundBalances;
	/**
	 * 支付总金额
	 */
	private BigDecimal totalBalances;
	/**
	 * 退款状态 : 1退货申请，2待收货，3待退款，4已退款
	 */
	private Integer status;
	/**
	 * 退货类型（1退款，2退货退款）
	 */
	private Integer type;
	/**
	 * 优惠券ID
	 */
	private Integer couponId;
	private Integer goodsId;
	private Integer specId;
	private Integer goodsNum;
	private String goodsName;
	private String goodsImages;
	private String desc;
	private String note;
	private String userName;
	//门店ID
	private Integer storeId;
	private Integer activityId;
	private Integer orderType;
	private List<WxOrderRefund> details;
	private List<WxOrderRefund> gifts;
	private WxAddress address;
	private String refundDesc;
	private String auditDesc;
	private Double goodsPrice;
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getAuditDesc() {
		return auditDesc;
	}
	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}
	public WxAddress getAddress() {
		return address;
	}
	public void setAddress(WxAddress address) {
		this.address = address;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRefundDesc() {
		return refundDesc;
	}
	public void setRefundDesc(String refundDesc) {
		this.refundDesc = refundDesc;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<WxOrderRefund> getGifts() {
		return gifts;
	}
	public void setGifts(List<WxOrderRefund> gifts) {
		this.gifts = gifts;
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
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public BigDecimal getRefundBalances() {
		return refundBalances;
	}
	public void setRefundBalances(BigDecimal refundBalances) {
		this.refundBalances = refundBalances;
	}
	public BigDecimal getTotalBalances() {
		return totalBalances;
	}
	public void setTotalBalances(BigDecimal totalBalances) {
		this.totalBalances = totalBalances;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public List<WxOrderRefund> getDetails() {
		return details;
	}
	public void setDetails(List<WxOrderRefund> details) {
		this.details = details;
	}
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getRefundId() {
		return refundId;
	}
	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	public String getRefundName() {
		return refundName;
	}
	public void setRefundName(String refundName) {
		this.refundName = refundName;
	}
	public BigDecimal getBalances() {
		return balances;
	}
	public void setBalances(BigDecimal balances) {
		this.balances = balances;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	
}
