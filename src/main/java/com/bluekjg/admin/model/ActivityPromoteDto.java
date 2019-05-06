package com.bluekjg.admin.model;

/**
 * <p>
 * 	活动推广条件实体类
 * </p>
 *
 * @author Tom
 * @since 2019-03-04
 */
public class ActivityPromoteDto {
	private static final long serialVersionUID = 1L;
	
	private String startTime;
	private String endTime;
	private Integer type;
	private String title;
	
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
	
	
}
