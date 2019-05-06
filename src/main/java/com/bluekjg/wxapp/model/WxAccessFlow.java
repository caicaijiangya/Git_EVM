package com.bluekjg.wxapp.model;

import java.io.Serializable;

/**
 * <p>
 * 	用户访问日志实体类
 * </p>
 *
 * @author Tom
 * @since 2018-11-29
 */
public class WxAccessFlow implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private Integer id;
	/**
	 * 门店ID
	 */
	private Integer storeId;
    /**
     * openID
     */
	private String openId;
    /**
     * 地址
     */
	private String url;
	/**
	 * 参数
	 */
	private String parameter;
	/**
	 * 访问次数
	 */
	private Integer time;
	/**
	 * 类型（0小程序，1门店二维码，2商品二维码）
	 */
	private Integer type;
	
	/**
	 * 是否删除（0未删除，1已删除）
	 */
	private Integer isDel;
	/**
	 * 创建日期
	 */
	private String createTime;
	/**
	 * 修改日期
	 */
	private String LastModifiedTime;
	/**
	 * 备注
	 */
	private String note;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	
	
	
}
