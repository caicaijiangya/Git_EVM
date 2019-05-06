package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 参数表
 * </p>
 *
 * @author Max
 * @since 2018-03-30
 */
@TableName("t_evm_parameter")
public class ParameterData extends Model<ParameterData> {

    private static final long serialVersionUID = 1L;
    
    @TableId(value="ID", type= IdType.AUTO)
	private Long id;

    /**
     * 参数名称
     */
    @TableField("PARAMETER_NAME")
	private String parameterName;
    /**
     * 参数值
     */
    @TableField("PARAMETER_VALUE")
	private String parameterValue;
    /**
     * 是否删除（0未删除，1已删除）
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
	private String note;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
