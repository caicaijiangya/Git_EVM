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
 * 订单主表
 * </p>
 *
 * @author Tim
 * @since 2018-09-18
 */
@TableName("t_evm_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 下单人
     */
	@TableField("OPEN_ID")
	private String openId;
    /**
     * 订单号
     */
	@TableField("ORDER_NO")
	private String orderNo;
    /**
     * 取货门店（TAKE_STYLE=1 ）时有效
     */
	@TableField("STORE_ID")
	private Long storeId;
    /**
     * 订单总额
     */
	@TableField("TOTAL_BALANCES")
	private Double totalBalances;
    /**
     * 付款方式（0微信支付，1到店付款）
     */
	@TableField("PAYMONEY_STYLE")
	private Integer paymoneyStyle;
    /**
     * 取货方式（0物流配送，1到店取货）
     */
	@TableField("TAKE_STYLE")
	private Integer takeStyle;
    /**
     * 订单状态（0待付款，1待取货，2订单完成，3订单取消）
     */
	@TableField("STATUS")
	private Integer status;
    /**
     * 订单类型（0-普通，1-秒杀，2-拼团，3-特价，4-积分兑换）
     */
	@TableField("ORDER_TYPE")
	private Integer orderType;
    /**
     * 活动ID
     */
	@TableField("ACTIVITY_ID")
	private Long activityId;
    /**
     * 是否删除（0否，1是）
     */
	@TableField("IS_DEL")
	private Integer isDel;
    /**
     * 创建日期
     */
	@TableField("CREATED_TIME")
	private Date createdTime;
    /**
     * 修改日期
     */
	@TableField("LAST_MODIFIED_TIME")
	private Date lastModifiedTime;
    /**
     * 备注
     */
	@TableField("NOTE")
	private String note;
	/**
     * 核销码
     */
	@TableField("WRITE_OFF_CODE")
	private String writeOffCode;
	/**
     * 核销人openID
     */
	@TableField("WRITE_OFF_OPEN_ID")
	private String writeOffOpenId;
	/**
     * 优惠金额
     */
	@TableField("DISCOUNT_PRICE")
	private double discountPrice;
	
	//下单人真实姓名
	@TableField(exist=false)
	private String userName;
	//下单人微信昵称
	@TableField(exist=false)
	private String nickName;
	//门店名称
	@TableField(exist=false)
	private String storeName;
	//核销人真实姓名
	@TableField(exist=false)
	private String userName2;
	//核销人昵称
	@TableField(exist=false)
	private String nickName2;
	//开始时间
	@TableField(exist=false)
	private String dateStart;
	//结束时间
	@TableField(exist=false)
	private String dateEnd;
	//订单日期-天
	@TableField(exist=false)
	private String days;
	//订单实际销售额
	@TableField(exist=false)
	private double realPrice;
	//销量
	@TableField(exist=false)
	private Integer orderQuantity;
	//订单日期-月
	@TableField(exist=false)
	private String months;
	//订单号
	@TableField(exist=false)
	private String expressNo;
	@TableField(exist=false)
	private String detailAddress;
	@TableField(exist=false)
	private String provinceName;
	@TableField(exist=false)
	private String cityName;
	@TableField(exist=false)
	private String areaName;
	@TableField(exist=false)
	private String startTime;
	@TableField(exist=false)
	private String endTime;
	@TableField(exist=false)
	private Integer goodsNum;
	@TableField(exist=false)
	private Double goodsPrice;
	@TableField(exist=false)
	private Double payBalances;
	@TableField(exist=false)
	private Double refundBalances;
	@TableField(exist=false)
	private Double freight;
	@TableField(exist=false)
	private Double goodsAver;
	@TableField(exist=false)
	private String transNo;
	@TableField(exist=false)
	private String selectedStoreId;
	@TableField(exist=false)
	private String[] selectedStoreIds;
	@TableField(exist=false)
	private String isCollage;
	@TableField(exist=false)
	private String metadata;
	
	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public String getSelectedStoreId() {
		return selectedStoreId;
	}

	public void setSelectedStoreId(String selectedStoreId) {
		this.selectedStoreId = selectedStoreId;
	}

	public String[] getSelectedStoreIds() {
		return selectedStoreIds;
	}

	public void setSelectedStoreIds(String[] selectedStoreIds) {
		this.selectedStoreIds = selectedStoreIds;
	}

	public String getIsCollage() {
		return isCollage;
	}

	public void setIsCollage(String isCollage) {
		this.isCollage = isCollage;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public Double getGoodsAver() {
		return goodsAver;
	}

	public void setGoodsAver(Double goodsAver) {
		this.goodsAver = goodsAver;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Double getPayBalances() {
		return payBalances;
	}

	public void setPayBalances(Double payBalances) {
		this.payBalances = payBalances;
	}

	public Double getRefundBalances() {
		return refundBalances;
	}

	public void setRefundBalances(Double refundBalances) {
		this.refundBalances = refundBalances;
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

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public double getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(double realPrice) {
		this.realPrice = realPrice;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
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

	public String getUserName2() {
		return userName2;
	}

	public void setUserName2(String userName2) {
		this.userName2 = userName2;
	}

	public String getNickName2() {
		return nickName2;
	}

	public void setNickName2(String nickName2) {
		this.nickName2 = nickName2;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Double getTotalBalances() {
		return totalBalances;
	}

	public void setTotalBalances(Double totalBalances) {
		this.totalBalances = totalBalances;
	}

	public Integer getPaymoneyStyle() {
		return paymoneyStyle;
	}

	public void setPaymoneyStyle(Integer paymoneyStyle) {
		this.paymoneyStyle = paymoneyStyle;
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

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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
