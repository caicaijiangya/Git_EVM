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
 * 砍价阶梯表
 * </p>
 *
 * @author Tom
 * @since 2018-12-7
 */
@TableName("t_evm_activity_goods_bargain_ladder")
public class ActivityGoodsBargainLadder extends Model<ActivityGoodsBargainLadder> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 砍价ID
     */
	@TableField("BARGAIN_ID")
	private Integer bargainId;
    /**
     * 商品ID
     */
	@TableField("GOODS_ID")
	private Integer goodsId;
    /**
     * 限制条件
     */
	@TableField("FULL_NUM")
	private Integer fullNum;
	/**
     * 商品名称
     */
	@TableField("GOODS_NAME")
	private String goodsName;
	/**
     * 商品数量
     */
	@TableField("GOODS_NUM")
	private Integer goodsNum;
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
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getBargainId() {
		return bargainId;
	}


	public void setBargainId(Integer bargainId) {
		this.bargainId = bargainId;
	}


	public Integer getGoodsId() {
		return goodsId;
	}


	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}


	public Integer getFullNum() {
		return fullNum;
	}


	public void setFullNum(Integer fullNum) {
		this.fullNum = fullNum;
	}


	public String getGoodsName() {
		return goodsName;
	}


	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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


	public Integer getGoodsNum() {
		return goodsNum;
	}


	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
