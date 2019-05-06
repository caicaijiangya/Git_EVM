package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bluekjg.core.commons.utils.JsonUtils;

import java.io.Serializable;

/**
 *
 * 角色资源
 *
 */
@TableName("t_evm_role_resource")
public class RoleResource implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键id */
	private Long id;

	/** 角色id */
	@TableField("role_id")
	private Long roleId;

	/** 资源id */
	@TableField("resource_id")
	private Long resourceId;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
