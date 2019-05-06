package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Tim
 * @since 2018-10-09
 */
@TableName("t_evm_order_trans")
public class OrderTrans extends Model<OrderTrans> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 下单人
     */
	@TableField("OPEN_ID")
	private String openId;
    /**
     * 订单ID
     */
	@TableField("ORDER_ID")
	private Integer orderId;
    /**
     * 交易单号
     */
	@TableField("TRANS_NO")
	private String transNo;
    /**
     * 退款单号
     */
	@TableField("REFUND_NO")
	private String refundNo;
    /**
     * 交易名称
     */
	@TableField("TRANS_NAME")
	private String transName;
    /**
     * 退款名称
     */
	@TableField("REFUND_NAME")
	private String refundName;
    /**
     * 交易金额
     */
	@TableField("BALANCES")
	private Double balances;
    /**
     * 支付优惠金额
     */
	@TableField("COUPON_FEE")
	private Double couponFee;
    /**
     * 交易状态 : 0未支付 , 1支付成功，2支付失败，3退款成功，4退款失败
     */
	@TableField("STATUS")
	private Integer status;
    /**
     * 创建时间
     */
	@TableField("CREATED_TIME")
	private Date createdTime;
    /**
     * 是否删除 : 0-否 ,1-是
     */
	@TableField("IS_DEL")
	private Integer isDel;
    /**
     * 最后一次修改时间
     */
	@TableField("LAST_MODIFIED_TIME")
	private Date lastModifiedTime;
    /**
     * 备注
     */
	@TableField("NOTE")
	private String note;

	@TableField(exist=false)
	private String orderNo;
	@TableField(exist=false)
	private String nickName;

	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	public String getRefundName() {
		return refundName;
	}

	public void setRefundName(String refundName) {
		this.refundName = refundName;
	}

	public Double getBalances() {
		return balances;
	}

	public void setBalances(Double balances) {
		this.balances = balances;
	}

	public Double getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(Double couponFee) {
		this.couponFee = couponFee;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
