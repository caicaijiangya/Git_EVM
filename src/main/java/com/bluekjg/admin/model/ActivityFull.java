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
 * 满减活动配置表
 * </p>
 *
 * @author Tom
 * @since 2018-12-7
 */
@TableName("t_evm_activity_full")
public class ActivityFull extends Model<ActivityFull> {

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
     * 条件金额
     */
	@TableField("FULL_PRICE")
	private Double fullPrice;
    /**
     * 优惠金额
     */
	@TableField("PRE_PRICE")
	private Double prePrice;
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
	private String fullPrices;
	@TableField(exist=false)
	private String prePrices;
	
	
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


	public Double getFullPrice() {
		return fullPrice;
	}


	public void setFullPrice(Double fullPrice) {
		this.fullPrice = fullPrice;
	}


	public Double getPrePrice() {
		return prePrice;
	}


	public void setPrePrice(Double prePrice) {
		this.prePrice = prePrice;
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


	public String getFullPrices() {
		return fullPrices;
	}


	public void setFullPrices(String fullPrices) {
		this.fullPrices = fullPrices;
	}


	public String getPrePrices() {
		return prePrices;
	}


	public void setPrePrices(String prePrices) {
		this.prePrices = prePrices;
	}


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
