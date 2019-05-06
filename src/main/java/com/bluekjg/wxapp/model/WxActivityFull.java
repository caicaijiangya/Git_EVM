package com.bluekjg.wxapp.model;

import java.io.Serializable;

/**
 * 满减满赠
 * @author Tom
 *
 */
public class WxActivityFull implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 活动ID
	 */
	private Integer id;
	private Integer orderId;
	/**
	 * 商品金额
	 */
	private Double goodsPrice;
	/**
	 * 优惠金额
	 */
	private Double prePrice;
	/**
	 * 商品ID
	 */
	private Integer goodsId;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品图片
	 */
	private String goodsImage;
	/**
	 * 商品数量
	 */
	private Integer goodsNum;
	/**
	 * 赠品库存
	 */
	private Integer amount;
	/**
	 * 条件金额
	 */
	private Double fullPrice;
	/**
	 * 实际优惠金额
	 */
	private Double price;
	
	private Integer storeId;
	
	private Integer isOverlay;//0不叠加，1叠加
	
	private String title;
	
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getIsOverlay() {
		return isOverlay;
	}
	public void setIsOverlay(Integer isOverlay) {
		this.isOverlay = isOverlay;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Double getFullPrice() {
		return fullPrice;
	}
	public void setFullPrice(Double fullPrice) {
		this.fullPrice = fullPrice;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getPrePrice() {
		return prePrice;
	}
	public void setPrePrice(Double prePrice) {
		this.prePrice = prePrice;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsImage() {
		return goodsImage;
	}
	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}
