package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：积分商品表实体类
 * @author：pincui.tom
 * @date：2018/3/26 14:51
 */
@TableName("t_evm_integral_goods")
public class WxIntegralGoods implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer rid;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品描述
	 */
	private String goodsDesc;
	/**
	 * 商品缩略图
	 */
	private String goodsImages;
	/**
	 * 商品价格
	 */
	private Double goodsPrice;
	/**
	 * 商品价格
	 */
	private Double goodsPrice1;
	/**
	 * 商品可用库存
	 */
	private Integer goodsAmount;
	/**
	 * 限购数量
	 */
	private Integer buyNum;
	/**
	 * 类型（0商品，1优惠券）
	 */
	private Integer type;
	private Integer addressId;
	private String openId;
	private String orderDesc;
	private Integer specId;
	private Integer orderNum;
	private Integer couponNum;
	private Double freight;
	private List<WxStaticFile> advertImgs;
	
	public Double getFreight() {
		return freight;
	}
	public void setFreight(Double freight) {
		this.freight = freight;
	}
	public Integer getCouponNum() {
		return couponNum;
	}
	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	public List<WxStaticFile> getAdvertImgs() {
		return advertImgs;
	}
	public void setAdvertImgs(List<WxStaticFile> advertImgs) {
		this.advertImgs = advertImgs;
	}
	public Double getGoodsPrice1() {
		return goodsPrice1;
	}
	public void setGoodsPrice1(Double goodsPrice1) {
		this.goodsPrice1 = goodsPrice1;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
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
	public Integer getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
