package com.bluekjg.wxapp.model;

import java.util.List;
import java.io.Serializable;

/**
 * <p>
 * 问卷测试题库表
 * </p>
 *
 * @author Tom
 * @since 2018-09-12
 */
public class WxQuestProblem implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private Integer id;
	/**
	 * 维度ID
	 */
	private Integer dimensionId;
    /**
     * 问题
     */
	private String title;
	/**
	 * 前四道题类型（1皮肤问题，2花费，3职业，4地区，0其它）
	 */
	private Integer type;
	/**
	 * 排序
	 */
	private Integer seq;
    /**
     * 创建时间
     */
	private String createdTime;
	private String note;
	
	private List<WxQuestProblemDetail> details;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(Integer dimensionId) {
		this.dimensionId = dimensionId;
	}

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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public List<WxQuestProblemDetail> getDetails() {
		return details;
	}

	public void setDetails(List<WxQuestProblemDetail> details) {
		this.details = details;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
