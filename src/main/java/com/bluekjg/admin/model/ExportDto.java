package com.bluekjg.admin.model;


/**
 * <p>
 * 导出参数实体
 * </p>
 *
 * @author Tom
 * @since 2018-10-29
 */
public class ExportDto{
	/**
	 * 开始日期
	 */
	private String startDate;
	/**
	 * 结束日期
	 */
	private String endDate;
	/**
	 * 取货方式（0物流配送，1到店取货）
	 */
	private Integer takeStyle;
	/**
	 * 状态
	 */
	private Integer status;
	private Integer orderType;
	private String orderNo;
	private Integer id;
	private Integer type;
	private String metadata;
	
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTakeStyle() {
		return takeStyle;
	}
	public void setTakeStyle(Integer takeStyle) {
		this.takeStyle = takeStyle;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
