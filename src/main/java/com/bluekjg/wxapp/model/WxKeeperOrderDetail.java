package com.bluekjg.wxapp.model;

import java.util.Date;

public class WxKeeperOrderDetail{

    /**
     * 主键ID
     */
	private Integer id;
    /**
     * 订单ID
     */
	private Integer orderId;
    /**
     * 商品ID
     */
	private Integer goodsId;
    /**
     * 商品数
     */
	private Integer goodsNums;
    /**
     * 商品价格
     */
	private Float goodsPrice;
    /**
     * 创建时间
     */
	private Date createdTime;
    /**
     * 是否删除 : 0-否, 1-是
     */
	private Integer isDel;
    /**
     * 最后一次修改时间
     */
	private Date lastModifiedTime;
    /**
     * 备注
     */
	private String note;
	

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

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getGoodsNums() {
		return goodsNums;
	}

	public void setGoodsNums(Integer goodsNums) {
		this.goodsNums = goodsNums;
	}

	public Float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Float goodsPrice) {
		this.goodsPrice = goodsPrice;
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

}
