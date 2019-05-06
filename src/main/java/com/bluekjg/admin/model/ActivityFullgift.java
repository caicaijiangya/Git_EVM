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
 * 满赠活动赠品表
 * </p>
 *
 * @author Tom
 * @since 2018-12-7
 */
@TableName("t_evm_activity_fullgift")
public class ActivityFullgift extends Model<ActivityFullgift> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 活动ID
     */
	@TableField("ACTIVITY_ID")
	private Integer activityId;
    /**
     * 商品ID
     */
	@TableField("GOODS_ID")
	private Integer goodsId;
	@TableField("STORE_ID")
	private Integer storeId;
    /**
     * 商品数量
     */
	@TableField("GOODS_NUM")
	private Integer goodsNum;
	@TableField(exist=false)
	private String goodsName;
	/**
     * 条件金额
     */
	@TableField("FULL_PRICE")
	private Double fullPrice;
	/**
     * 库存
     */
	@TableField("AMOUNT")
	private Integer amount;
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
	private String goodsImage;
	@TableField(exist=false)
	private String fullPrices;
	@TableField(exist=false)
	private String goodsIds;
	@TableField(exist=false)
	private String goodsNums;
	@TableField(exist=false)
	private String amounts;
	@TableField(exist=false)
	private String storeName;
	
	
	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public Integer getStoreId() {
		return storeId;
	}


	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}


	public String getGoodsImage() {
		return goodsImage;
	}


	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}


	public String getGoodsName() {
		return goodsName;
	}


	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public String getFullPrices() {
		return fullPrices;
	}


	public void setFullPrices(String fullPrices) {
		this.fullPrices = fullPrices;
	}


	public String getGoodsIds() {
		return goodsIds;
	}


	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}


	public String getGoodsNums() {
		return goodsNums;
	}


	public void setGoodsNums(String goodsNums) {
		this.goodsNums = goodsNums;
	}


	public String getAmounts() {
		return amounts;
	}


	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getActivityId() {
		return activityId;
	}


	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}


	public Integer getGoodsId() {
		return goodsId;
	}


	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}


	public Integer getGoodsNum() {
		return goodsNum;
	}


	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
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


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
