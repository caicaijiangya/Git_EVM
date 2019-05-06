package com.bluekjg.wxapp.model;

import java.io.Serializable;

/**
 * <p>
 * 问卷测试结果详情表
 * </p>
 *
 * @author Tom
 * @since 2018-09-12
 */
public class WxQuestResultDetail implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 结果Id
	 */
	private Integer resultId;
	/**
	 * 维度ID
	 */
	private Integer dimensionId;
	private String dimensionName;
	/**
	 * 问题ID
	 */
	private Integer problemId;
	/**
	 * 分数
	 */
	private Double score;
	/**
	 * 创建时间
	 */
	private String createdTime;
	private String note;
	private Integer size;


	//新加
	private Integer page;
	private String value;
	private String quest_id;

	public String getQuest_id() {
		return quest_id;
	}

	public void setQuest_id(String quest_id) {
		this.quest_id = quest_id;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDimensionName() {
		return dimensionName;
	}
	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getResultId() {
		return resultId;
	}
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
	public Integer getDimensionId() {
		return dimensionId;
	}
	public void setDimensionId(Integer dimensionId) {
		this.dimensionId = dimensionId;
	}
	public Integer getProblemId() {
		return problemId;
	}
	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}


}
