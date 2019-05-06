package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单退款表
 * </p>
 *
 * @author Tom
 * @since 2019-01-14
 */
@TableName("t_evm_order_refund")
public class OrderRefund extends Model<OrderRefund> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
	/**
     * 退款状态 : 1退货申请，2待收货，3待退款，4已退款
     */
	@TableField(exist=false)
	private Integer status;
    /**
     * 下单人
     */
	@TableField(exist=false)
	private String openId;
    /**
     * 订单号
     */
	@TableField(exist=false)
	private String orderNo;
	/**
     * 支付单号
     */
	@TableField(exist=false)
	private String transNo;
	/**
     * 退款单号
     */
	@TableField(exist=false)
	private String refundNo;
    /**
     * 订单总额
     */
	@TableField(exist=false)
	private Double totalBalances;
	/**
     * 支付总额
     */
	@TableField(exist=false)
	private Double transBalances;
	 /**
     * 已退金额
     */
	@TableField(exist=false)
	private Double refundBalances;
	/**
     * 退款金额
     */
	@TableField(exist=false)
	private Double balances;
	//下单人真实姓名
	@TableField(exist=false)
	private String userName;
	//下单人微信昵称
	@TableField(exist=false)
	private String nickName;
	@TableField(exist=false)
	private String mobileNo;
	//门店名称
	@TableField(exist=false)
	private String storeName;
	//退款名称
	@TableField(exist=false)
	private String refundName;
	@TableField(exist=false)
	private Integer orderType;
	@TableField(exist=false)
	private String startTime;
	@TableField(exist=false)
	private String endTime;
	@TableField(exist=false)
	private Integer type;
	@TableField(exist=false)
	private String desc;
	@TableField(exist=false)
	private Integer goodsNum;
	@TableField(exist=false)
	private String expressNo;
	@TableField(exist=false)
	private String expressName;
	@TableField(exist=false)
	private String auditDesc;
	@TableField(exist=false)
	private String isRk;
	@TableField(exist=false)
	private String createdTime;
	@TableField(exist=false)
	private String lastModifiedTime;
	@TableField(exist=false)
	private String storeId;
	@TableField(exist=false)
	private String[] storeIds;
	@TableField(exist=false)
	private String typeName;
	@TableField(exist=false)
	private String statusName;
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String[] getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(String[] storeIds) {
		this.storeIds = storeIds;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getIsRk() {
		return isRk;
	}

	public void setIsRk(String isRk) {
		this.isRk = isRk;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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


	public String getRefundName() {
		return refundName;
	}


	public void setRefundName(String refundName) {
		this.refundName = refundName;
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


	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getTransNo() {
		return transNo;
	}


	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}


	public String getRefundNo() {
		return refundNo;
	}


	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}


	public Double getTotalBalances() {
		return totalBalances;
	}


	public void setTotalBalances(Double totalBalances) {
		this.totalBalances = totalBalances;
	}


	public Double getTransBalances() {
		return transBalances;
	}


	public void setTransBalances(Double transBalances) {
		this.transBalances = transBalances;
	}


	public Double getRefundBalances() {
		return refundBalances;
	}


	public void setRefundBalances(Double refundBalances) {
		this.refundBalances = refundBalances;
	}


	public Double getBalances() {
		return balances;
	}


	public void setBalances(Double balances) {
		this.balances = balances;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
