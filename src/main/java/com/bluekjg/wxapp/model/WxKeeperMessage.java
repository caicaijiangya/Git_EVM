package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：用户信息表实体类
 * @author：pincui.tom
 * @date：2018/3/26 14:51
 */
@TableName("t_evm_message")
public class WxKeeperMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer rid;
	//门店ID
	private Integer storeId;
	//发布人Id
	private String openId;
	//消息标题
	private String title;
	//消息内容
	private String contents;
	//订单类型
	private Integer type;
	//浏览量
	private Integer nums;
	//跳转地址
	private String pageUrl;
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	//创建日期
	private String createdTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	//是否已读
	private Integer isRead;
	//订单类型
	private Integer orderType;
	//取货方式
	private Integer takeStyle;
	//订单Id
	private Integer orderId;
	//订单编号
	private String orderNo;
	//订单总额
	private double totalBalances;
	//优惠金额
	private double discountPrice;
	//图片地址
	private String filePath;
	//商品名称
	private String goodsName;
	//退款申请时间
	private String applyTime;
	//接受者类型：1-店主 2-店长3-店员 4-消费者
	private Integer sendType;
	//接受者openId
	private String sendOpenId;
	private List<WxKeeperGoods> orderDetails;
	
	public List<WxKeeperGoods> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<WxKeeperGoods> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getSendOpenId() {
		return sendOpenId;
	}
	public void setSendOpenId(String sendOpenId) {
		this.sendOpenId = sendOpenId;
	}
	public Integer getSendType() {
		return sendType;
	}
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public double getTotalBalances() {
		return totalBalances;
	}
	public void setTotalBalances(double totalBalances) {
		this.totalBalances = totalBalances;
	}
	public double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getTakeStyle() {
		return takeStyle;
	}
	public void setTakeStyle(Integer takeStyle) {
		this.takeStyle = takeStyle;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getNums() {
		return nums;
	}
	public void setNums(Integer nums) {
		this.nums = nums;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
