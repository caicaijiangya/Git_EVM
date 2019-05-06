package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bluekjg.core.commons.utils.JsonUtils;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 系统日志
 *
 */
@TableName("t_evm_sys_log")
public class SysLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键id */
	private Long id;

	/** 登陆名 */
	@TableField("login_name")
	private String loginName;

	/** 角色名 */
	@TableField("role_name")
	private String roleName;

	/** 内容 */
	@TableField("opt_content")
	private String optContent;

	/** 客户端ip */
	@TableField("client_ip")
	private String clientIp;

	/** 创建时间 */
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOptContent() {
		return this.optContent;
	}

	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}

	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
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
