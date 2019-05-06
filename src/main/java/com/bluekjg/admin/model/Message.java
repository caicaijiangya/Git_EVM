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
 * 门店主表
 * </p>
 * @author Tom
 * @since 2018-09-25
 */
@TableName("t_evm_message")
public class Message extends Model<Message> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 门店Id
     */
	@TableField(exist=false)
	private Integer storeId;
	/**
     * 发布人openId
     */
	@TableField(exist=false)
	private String openId;
	/**
     * 消息标题
     */
	@TableField(exist=false)
	private String title;
	/**
	 * 消息内容
	 */
	@TableField(exist=false)
	private String contents;
	/**
	 * 发送类型
	 */
	@TableField(exist=false)
	private Integer type;
	/**
	 * 浏览量
	 */
	@TableField(exist=false)
	private Integer nums;
	
    /**
     * 是否删除（0否，1是）
     */
	@TableField(exist=false)
	private Integer isDel;
    /**
     * 创建日期
     */
	@TableField(exist=false)
	private Date createdTime;
    /**
     * 修改日期
     */
	@TableField(exist=false)
	private Date lastModifiedTime;
    /**
     * 备注
     */
	@TableField(exist=false)
	private String note;
	
	/**
     * 跳转地址
     */
	@TableField(exist=false)
	private String pageUrl;
	
	/**
     * 订单ID
     */
	@TableField(exist=false)
	private Integer orderId;
	
	//发布人昵称
	@TableField(exist=false)
	private String nickName;
	//订单编号
	@TableField(exist=false)
	private String orderNo;
	//门店名
	@TableField(exist=false)
	private String storeName;
	//接收人类型
	@TableField(exist=false)
	private Integer sendType;
	//是否已读
	@TableField(exist=false)
	private Integer isRead;
	//是否已读
	@TableField(exist=false)
	private Integer userType;
	
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getSendType() {
		return sendType;
	}
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public void setLastModifiedTime(Date date) {
		this.lastModifiedTime = date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
