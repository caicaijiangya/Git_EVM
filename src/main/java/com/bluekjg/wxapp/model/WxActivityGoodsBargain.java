package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：活动商品砍价表实体类
 * @author：pincui.tom
 * @date：2018/12/10 14:51
 */
@TableName("t_evm_activity_goods_bargain")
public class WxActivityGoodsBargain implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 活动商品ID
	 */
	private Integer activityGoodsId;
	/**
	 * 每人发起助力砍价次数（0不限制）
	 */
	private Integer bargainNum;
	/**
	 * 每人允许帮砍次数（0不限制）
	 */
	private Integer helpBargainNum;
	/**
	 * 最大赠送次数（0不限制）
	 */
	private Integer givingNum;
	/**
	 * 每次砍价实际优惠价格
	 */
	private Double price;
	/**
	 * 优惠券
	 */
	private Integer couponId;
	private String couponName;
	private Double couponMoney;
	/**
	 * 积分
	 */
	private Integer integral;
	
	/**
	 * 赠送类型（0不赠送，1积分，2优惠券）
	 */
	private Integer type;
	/**
	 * 砍价时长（默认24小时）
	 */
	private Integer time;
	
	
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	
	private String image0;
	private String image1;
	private String image2;
	
	private List<WxActivityGoodsBargainLadder> ladderList;
	private List<WxActivityGoodsBargainPrice> priceList;
	private Integer bargainType;
	
	public Integer getBargainType() {
		return bargainType;
	}
	public void setBargainType(Integer bargainType) {
		this.bargainType = bargainType;
	}
	public List<WxActivityGoodsBargainLadder> getLadderList() {
		return ladderList;
	}
	public void setLadderList(List<WxActivityGoodsBargainLadder> ladderList) {
		this.ladderList = ladderList;
	}
	public List<WxActivityGoodsBargainPrice> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<WxActivityGoodsBargainPrice> priceList) {
		this.priceList = priceList;
	}
	public String getImage0() {
		return image0;
	}
	public void setImage0(String image0) {
		this.image0 = image0;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActivityGoodsId() {
		return activityGoodsId;
	}
	public void setActivityGoodsId(Integer activityGoodsId) {
		this.activityGoodsId = activityGoodsId;
	}
	public Integer getBargainNum() {
		return bargainNum;
	}
	public void setBargainNum(Integer bargainNum) {
		this.bargainNum = bargainNum;
	}
	public Integer getHelpBargainNum() {
		return helpBargainNum;
	}
	public void setHelpBargainNum(Integer helpBargainNum) {
		this.helpBargainNum = helpBargainNum;
	}
	public Integer getGivingNum() {
		return givingNum;
	}
	public void setGivingNum(Integer givingNum) {
		this.givingNum = givingNum;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
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
	public Double getCouponMoney() {
		return couponMoney;
	}
	public void setCouponMoney(Double couponMoney) {
		this.couponMoney = couponMoney;
	}
	
	
	
}
