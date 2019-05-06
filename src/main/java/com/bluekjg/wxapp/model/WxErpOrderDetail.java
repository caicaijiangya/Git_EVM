package com.bluekjg.wxapp.model;

import java.io.Serializable;

/**
 * @description：ERP接口实体类
 * @author：pincui.tom
 * @date：2018/10/19 14:51
 */
public class WxErpOrderDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/**
	 * 商品代码
	 */
	private String itemCode;
	/**
	 * 规格代码
	 */
	private String skuCode;
	/**
	 * 商品单价
	 */
	private String price;
	/**
	 * 商品数量
	 */
	private String qty;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	
	
}
