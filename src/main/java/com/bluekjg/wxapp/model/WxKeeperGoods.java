package com.bluekjg.wxapp.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * @author Tim
 * @since 2018-07-09
 */
public class WxKeeperGoods {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private Integer id;
	/**
     * 商品名称
     */
	private String goodsName;
	
    /**
     * 单价
     */
	private Double goodsPrice;
    /**
     * 销量
     */
	private Integer goodsSales;
	/**
     * 可用库存
     */
	private Integer goodsAmount;
	/**
     * 总剩余库存
     */
	private Integer goodsRemAmount;
	/**
     * 总库存
     */
	private Integer goodsTotalAmount;
    /**
     * 状态
     */
	private Integer status;
	/**
     * 商品描述
     */
	private String goodsDesc;
	/**
     * 品牌
     */
	private Integer brandId;
	/**
     * 分类
     */
	private Integer classifyId;
	/**
     * 累计销量
     */
	private Integer sales;
	/**
     * 创建时间
     */
	private Date createdTime;
	/**
     * 是否删除     0-否 , 1-是
     */
	private Integer isDel;
	/**
     * 最后修改时间
     */
	private Date lastModifiedTime;
    /**
     * 备注
     */
	private String note;
	
	//商品图片
	private String goodsImage;
	//商品购买数量
	private Integer goodsNums;
	private Integer specId;
	
	
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	public Integer getGoodsNums() {
		return goodsNums;
	}
	public void setGoodsNums(Integer goodsNums) {
		this.goodsNums = goodsNums;
	}
	public String getGoodsImage() {
		return goodsImage;
	}
	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	public Integer getGoodsSales() {
		return goodsSales;
	}
	public void setGoodsSales(Integer goodsSales) {
		this.goodsSales = goodsSales;
	}
	public Integer getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public Integer getGoodsRemAmount() {
		return goodsRemAmount;
	}
	public void setGoodsRemAmount(Integer goodsRemAmount) {
		this.goodsRemAmount = goodsRemAmount;
	}
	public Integer getGoodsTotalAmount() {
		return goodsTotalAmount;
	}
	public void setGoodsTotalAmount(Integer goodsTotalAmount) {
		this.goodsTotalAmount = goodsTotalAmount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Integer getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
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
