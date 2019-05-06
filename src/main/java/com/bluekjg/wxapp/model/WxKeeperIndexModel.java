package com.bluekjg.wxapp.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：用户信息表实体类
 * @author：pincui.tom
 * @date：2018/3/26 14:51
 */
@TableName("t_evm_index_model")
public class WxKeeperIndexModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer rid;
	//权限名称
	private String modelName;
	//跳转地址
	private String pageUrl;
	//权限模块图片
	private String modelImg;
	//用户类型
	private Integer type;
	//排序
	private Integer seq;
	//跳转的类型(页面地址或者方法)
	private Integer jumpType;
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	//是否授权
	private Integer isAuth;
	
	public Integer getIsAuth() {
		return isAuth;
	}
	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelImg() {
		return modelImg;
	}
	public void setModelImg(String modelImg) {
		this.modelImg = modelImg;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public Integer getJumpType() {
		return jumpType;
	}
	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastModifiedTime() {
		return LastModifiedTime;
	}
	public void setLastModifiedTime(String lastModifiedTime) {
		LastModifiedTime = lastModifiedTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
