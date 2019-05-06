package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 活动商品表
 * </p>
 *
 * @author Tom
 * @since 2018-12-7
 */
@TableName("t_evm_activity_goods_bargain")
public class ActivityGoodsBargain extends Model<ActivityGoodsBargain> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 活动商品ID
     */
	@TableField("ACTIVITY_GOODS_ID")
	private Integer activityGoodsId;
    /**
     * 每人发起助力砍价次数（0不限制）
     */
	@TableField("BARGAIN_NUM")
	private Integer bargainNum;
    /**
     * 每人允许帮砍次数（0不限制）
     */
	@TableField("HELP_BARGAIN_NUM")
	private Integer helpBargainNum;
	/**
     * 最大赠送次数（0不限制）
     */
	@TableField("GIVING_NUM")
	private Integer givingNum;
    /**
     * 每次砍价实际优惠价格
     */
	@TableField("PRICE")
	private Double price;
    /**
     * 优惠券ID
     */
	@TableField("COUPON_ID")
	private Integer couponId;
    /**
     * 积分
     */
	@TableField("INTEGRAL")
	private Integer integral;
    /**
     * 赠送类型（0不赠送，1积分，2优惠券）
     */
	@TableField("TYPE")
	private Integer type;
	/**
     * 砍价类型（0普通砍价，1阶梯砍价）
     */
	@TableField("BARGAIN_TYPE")
	private Integer bargainType;
	/**
     * 砍价时长（默认24小时）
     */
	@TableField("TIME")
	private Integer time;
    /**
     * 是否删除（0否，1是）
     */
	@TableField("IS_DEL")
	private Integer isDel;
    /**
     * 创建日期
     */
	@TableField("CREATED_TIME")
	private Date createdTime;
    /**
     * 修改日期
     */
	@TableField("LAST_MODIFIED_TIME")
	private Date lastModifiedTime;
    /**
     * 备注
     */
	@TableField("NOTE")
	private String note;
	@TableField(exist=false)
	private String image0;
	@TableField(exist=false)
	private String image1;
	@TableField(exist=false)
	private String image2;
	@TableField(exist=false)
	private String fullNums;
	@TableField(exist=false)
	private String goodsIds;
	@TableField(exist=false)
	private String goodsNames;
	@TableField(exist=false)
	private String goodsNums;
	@TableField(exist=false)
	private String prices;
	@TableField(exist=false)
	private String useNums;
	
	
	public String getGoodsNums() {
		return goodsNums;
	}


	public void setGoodsNums(String goodsNums) {
		this.goodsNums = goodsNums;
	}


	public String getPrices() {
		return prices;
	}


	public void setPrices(String prices) {
		this.prices = prices;
	}


	public String getUseNums() {
		return useNums;
	}


	public void setUseNums(String useNums) {
		this.useNums = useNums;
	}


	public String getGoodsNames() {
		return goodsNames;
	}


	public void setGoodsNames(String goodsNames) {
		this.goodsNames = goodsNames;
	}


	public Integer getBargainType() {
		return bargainType;
	}


	public void setBargainType(Integer bargainType) {
		this.bargainType = bargainType;
	}


	public String getFullNums() {
		return fullNums;
	}


	public void setFullNums(String fullNums) {
		this.fullNums = fullNums;
	}


	public String getGoodsIds() {
		return goodsIds;
	}


	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}


	public Integer getTime() {
		return time;
	}


	public void setTime(Integer time) {
		this.time = time;
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


	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public Integer getGivingNum() {
		return givingNum;
	}


	public void setGivingNum(Integer givingNum) {
		this.givingNum = givingNum;
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


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
