package com.bluekjg.admin.model;

/**
 * <p>
 * 	数据分析条件实体类
 * </p>
 *
 * @author Tom
 * @since 2019-03-01
 */
public class DataAnalysisDto {
	private static final long serialVersionUID = 1L;
	
	private String startTime;
	private String endTime;
	private String format;
	private String storeId;
	private String[] storeIds;
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String[] getStoreIds() {
		return storeIds;
	}
	public void setStoreIds(String[] storeIds) {
		this.storeIds = storeIds;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
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
	
	
}
