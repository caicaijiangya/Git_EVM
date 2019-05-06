package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 门店商品主表
 * </p>
 * @author Tom
 * @since 2018-09-25
 */
@TableName("t_evm_store_goods")
public class StoreGoods extends Model<StoreGoods> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 门店ID
     */
	@TableField(exist=false)
	private Integer storeId;
	/**
     * 门店名称
     */
	@TableField(exist=false)
	private String storeName;
	/**
     * 商品ID
     */
	@TableField(exist=false)
	private Integer goodsId;
	@TableField(exist=false)
	private Integer specId;
	/**
	 * 商品名称
	 */
	@TableField(exist=false)
	private String goodsName;
	
	/**
	 * 门店商品库存
	 */
	@TableField(exist=false)
	private Integer goodsAmount;
	@TableField(exist=false)
	private Integer goodsTotalAmount;
	
    /**
     * 是否删除（0否，1是）
     */
	@TableField(exist=false)
	private Integer isDel;
    /**
     * 创建日期
     */
	@TableField(exist=false)
	private String createdTime;
    /**
     * 修改日期
     */
	@TableField(exist=false)
	private String lastModifiedTime;
    /**
     * 备注
     */
	@TableField(exist=false)
	private String note;
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer getSpecId() {
		return specId;
	}

	public void setSpecId(Integer specId) {
		this.specId = specId;
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
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
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
	public Integer getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public Integer getGoodsTotalAmount() {
		return goodsTotalAmount;
	}
	public void setGoodsTotalAmount(Integer goodsTotalAmount) {
		this.goodsTotalAmount = goodsTotalAmount;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
