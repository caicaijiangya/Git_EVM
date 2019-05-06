package com.bluekjg.wxapp.model;

import java.io.Serializable;

/**
 * <p>
 * 问卷测试题库详情表
 * </p>
 *
 * @author Tom
 * @since 2018-09-12
 */
public class WxQuestProblemDetail implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private Integer id;
    /**
     * 答案
     */
	private String title;
	/**
	 * 分数
	 */
	private Integer score;
	/**
	 * 排序（A,B,C,D,E）
	 */
	private String seq;
	
    /**
     * 创建时间
     */
	private String createdTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
}
