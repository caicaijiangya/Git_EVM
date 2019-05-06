package com.bluekjg.wxapp.model;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

public class WxKeeperOrder{

	private static final long serialVersionUID = 5812022024136666172L;
	 /**
	 * 主键ID
	 */
	
	public WxKeeperOrder() {
	}
	
	public WxKeeperOrder(Integer id, Integer collageStatus) {
		super();
		this.id = id;
	}


	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
	private Integer rid;
	private String openId;
	private String orderNo;
	private Double totalBalances;
	/**
	 * 付款方式:0微信支付,1到店支付
	 */
	private Integer paymoneyStyle;
	private Integer takeStyle;
	private Integer status;
	private Integer orderType;
	private Integer activityId;
	private Date createdTime;
	private Integer isDel;
	private String lastModifiedTime;
	private String writeOffCode; //核销码
	private String note;

	private String goodsImage;
	private Double goodsPrice;
	private Integer goodsNums;
	private String goodsName;
	private Integer goodsId;
    private Integer dataId;  //关联ID
    private String title;
    private Date endTime;
    private String condition;
    private String startDate;
    private String endDate;
    private Double totalMoney;
    private Integer totalOrder;
    private Integer totalWth;
    private String nickName;
    private String mobileNo;
    private double discountPrice;
    private	Date applyTime;
    private WxOrderTrans orderTrans;
    private List<WxOrderGift> giftList;
    private List<WxOrderPre> preList;
    
	public List<WxOrderPre> getPreList() {
		return preList;
	}

	public void setPreList(List<WxOrderPre> preList) {
		this.preList = preList;
	}

	public List<WxOrderGift> getGiftList() {
		return giftList;
	}

	public void setGiftList(List<WxOrderGift> giftList) {
		this.giftList = giftList;
	}

	public WxOrderTrans getOrderTrans() {
		return orderTrans;
	}

	public void setOrderTrans(WxOrderTrans orderTrans) {
		this.orderTrans = orderTrans;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getWriteOffCode() {
		return writeOffCode;
	}

	public void setWriteOffCode(String writeOffCode) {
		this.writeOffCode = writeOffCode;
	}

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(Integer totalOrder) {
		this.totalOrder = totalOrder;
	}

	public Integer getTotalWth() {
		return totalWth;
	}

	public void setTotalWth(Integer totalWth) {
		this.totalWth = totalWth;
	}


	private List<WxKeeperGoods> orderDetails;
    
	public List<WxKeeperGoods> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<WxKeeperGoods> orderDetails) {
		this.orderDetails = orderDetails;
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}


	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getGoodsNums() {
		return goodsNums;
	}

	public void setGoodsNums(Integer goodsNums) {
		this.goodsNums = goodsNums;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public Double getTotalBalances() {
		return totalBalances;
	}

	public void setTotalBalances(Double totalBalances) {
		this.totalBalances = totalBalances;
	}

	public Integer getPaymoneyStyleId() {
		return paymoneyStyle;
	}

	public void setPaymoneyStyleId(Integer paymoneyStyleId) {
		this.paymoneyStyle = paymoneyStyleId;
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

	public String getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}


	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
