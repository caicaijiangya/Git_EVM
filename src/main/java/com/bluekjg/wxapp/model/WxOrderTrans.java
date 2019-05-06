package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：订单支付表实体类
 * @author：pincui.tom
 * @date：2018/9/26 14:51
 */
@TableName("t_evm_order_trans")
public class WxOrderTrans implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/**
	 * 支付人
	 */
	private String openId;
	/**
	 * 订单ID
	 */
	private Integer orderId;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 交易单号
	 */
	private String transNo;
	/**
	 * 退款单号
	 */
	private String refundNo;
	/**
	 * 交易名称
	 */
	private String transName;
	/**
	 * 退款名称
	 */
	private String refundName;
	/**
	 * 交易金额
	 */
	private BigDecimal balances;
	/**
	 * 已退金额
	 */
	private BigDecimal refundBalances;
	/**
	 * 交易优惠金额
	 */
	private BigDecimal couponFee;
	/**
	 * 运费
	 */
	private BigDecimal freight;
	/**
	 * 交易状态 : 0未支付 , 1支付成功，2支付失败
	 */
	private Integer status;
	
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	private Integer storeId;
	private Integer orderStatus;
	private Double totalBalances;
	
	public Double getTotalBalances() {
		return totalBalances;
	}
	public void setTotalBalances(Double totalBalances) {
		this.totalBalances = totalBalances;
	}
	public BigDecimal getRefundBalances() {
		return refundBalances;
	}
	public void setRefundBalances(BigDecimal refundBalances) {
		this.refundBalances = refundBalances;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
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
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	public String getTransName() {
		return transName;
	}
	public void setTransName(String transName) {
		this.transName = transName;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public BigDecimal getCouponFee() {
		return couponFee;
	}
	public void setCouponFee(BigDecimal couponFee) {
		this.couponFee = couponFee;
	}
	
}
