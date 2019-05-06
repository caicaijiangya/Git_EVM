package com.bluekjg.wxapp.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：商品规格表实体类
 * @author：pincui.tom
 * @date：2018/3/26 14:51
 */
@TableName("t_evm_goods_spec")
public class WxGoodsSpec implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/**
	 * 商品ID
	 */
	private Integer goodsId;
	/**
	 * 规格名称
	 */
	private String specName;
	/**
	 * SKU
	 */
	private String goodsCode;
	/**
	 * 规格图
	 */
	private String goodsImages;
	/**
	 * 商品价格
	 */
	private Double goodsPrice;
	/**
	 * 市场价
	 */
	private Double marketPrice;
	/**
	 * 商品可用库存
	 */
	private Integer goodsAmount;
	/**
	 * 商品总剩余库存（总店+门店）
	 */
	private Integer goodsRemAmount;
	/**
	 * 商品总库存（不做加减处理）
	 */
	private Integer goodsTotalAmount;
	/**
	 * 排序
	 */
	private Integer seq;
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public String getGoodsImages() {
		return goodsImages;
	}
	public void setGoodsImages(String goodsImages) {
		this.goodsImages = goodsImages;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
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
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	
	
}
