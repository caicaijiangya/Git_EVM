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
 * 积分规则门店对照表
 * </p>
 *
 * @author Tom
 * @since 2019-01-16
 */
@TableName("t_evm_integral_activity_store")
public class IntegralActivityStore extends Model<IntegralActivityStore> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 积分规则Id
     */
	@TableField("INTEGRAL_ACTIVITY_ID")
	private Integer integralActivityId;
    /**
     * 门店Id
     */
	@TableField("STORE_ID")
	private Integer storeId;
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
	
	//积分规则标题
	@TableField(exist=false)
	private String integralActivityTitle;
	//门店名称
	@TableField(exist=false)
	private String storeName;
	
	
	public String getIntegralActivityTitle() {
		return integralActivityTitle;
	}


	public void setIntegralActivityTitle(String integralActivityTitle) {
		this.integralActivityTitle = integralActivityTitle;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getIntegralActivityId() {
		return integralActivityId;
	}


	public void setIntegralActivityId(Integer integralActivityId) {
		this.integralActivityId = integralActivityId;
	}


	public Integer getStoreId() {
		return storeId;
	}


	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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
