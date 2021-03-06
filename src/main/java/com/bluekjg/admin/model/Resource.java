package com.bluekjg.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bluekjg.core.commons.utils.JsonUtils;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 资源
 *
 */
@TableName("t_evm_resource")
public class Resource implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private Long id;

	/** 资源名称 */
	@NotBlank
	private String name;

	/** 资源路径 */
	private String url;

	/** 打开方式 ajax,iframe */
	@TableField("open_mode")
	private String openMode;

	/** 资源介绍 */
	private String description;

	/** 资源图标 */
	@JsonProperty("iconCls")
	private String icon;

	/** 父级资源id */
	private Long pid;

	/** 排序 */
	private Integer seq;

	/** 状态 */
	private Integer status;

	/** 打开的 */
	private Integer opened;

	/** 资源类别 */
	@TableField("resource_type")
	private Integer resourceType;

	/** 创建时间 */
	@TableField("create_time")
	private Date createTime;
	
	/** 是否删除：0-否 1-是*/
	@TableField("is_del")
	private Integer isDel;

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOpenMode() {
		return openMode;
	}

	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOpened() {
		return opened;
	}

	public void setOpened(Integer opened) {
		this.opened = opened;
	}

	public Integer getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
