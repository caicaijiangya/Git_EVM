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
 * 积分规则表
 * </p>
 *
 * @author Tom
 * @since 2019-01-16
 */
@TableName("t_evm_integral_activity")
public class IntegralActivity extends Model<IntegralActivity> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 活动标题
     */
	@TableField("TITLE")
	private String title;
    /**
     * 类型（1多倍积分，2邀请好友获得积分）
     */
	@TableField("TYPE")
	private Integer type;
    /**
     * 开始时间
     */
	@TableField("START_TIME")
	private String startTime;
    /**
     * 结束时间
     */
	@TableField("END_TIME")
	private String endTime;
    /**
     * 积分倍数
     */
	@TableField("MULTIPLE")
	private Integer multiple;
	/**
     * 赠送积分
     */
	@TableField("INTEGRAL")
	private Integer integral;
    /**
     * 是否删除（0否，1是）
     */
	@TableField("IS_DEL")
	private Integer isDel;
    /**
     * 创建日期
     */
	@TableField("CREATED_TIME")
	private String createdTime;
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
	
	//积分规则门店集合
	@TableField(exist=false)
	private String activityStoreIds;
	
	
	public String getActivityStoreIds() {
		return activityStoreIds;
	}


	public void setActivityStoreIds(String activityStoreIds) {
		this.activityStoreIds = activityStoreIds;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public Integer getMultiple() {
		return multiple;
	}


	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}


	public Integer getIntegral() {
		return integral;
	}


	public void setIntegral(Integer integral) {
		this.integral = integral;
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
