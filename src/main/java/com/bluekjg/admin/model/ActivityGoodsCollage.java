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
 * 活动商品拼团表
 * </p>
 *
 * @author Tom
 * @since 2019-03-29
 */
@TableName("t_evm_activity_goods_collage")
public class ActivityGoodsCollage extends Model<ActivityGoodsCollage> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 活动ID
     */
	@TableField("ACTIVITY_GOODS_ID")
	private Integer activityGoodsId;
    /**
     * 拼团人数
     */
	@TableField("COLLAGE_NUM")
	private Integer collageNum;
    /**
     * 拼团时长（默认24小时）
     */
	@TableField("COLLAGE_TIME")
	private Integer collageTime;
	/**
	 * 拼团价格
	 */
	@TableField("COLLAGE_PRICE")
	private Double collagePrice;
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
	private String nums;
	@TableField(exist=false)
	private String times;
	@TableField(exist=false)
	private String prices;
	@Override
	protected Serializable pkVal() {
		return this.id;
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
	public Integer getCollageNum() {
		return collageNum;
	}
	public void setCollageNum(Integer collageNum) {
		this.collageNum = collageNum;
	}
	public Integer getCollageTime() {
		return collageTime;
	}
	public void setCollageTime(Integer collageTime) {
		this.collageTime = collageTime;
	}
	public Double getCollagePrice() {
		return collagePrice;
	}
	public void setCollagePrice(Double collagePrice) {
		this.collagePrice = collagePrice;
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
	public String getNums() {
		return nums;
	}
	public void setNums(String nums) {
		this.nums = nums;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getPrices() {
		return prices;
	}
	public void setPrices(String prices) {
		this.prices = prices;
	}

}
