package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 问卷测试结果表
 * </p>
 *
 * @author Tom
 * @since 2018-09-12
 */
public class WxQuestResult implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private Integer id;
	private Integer rid;
	/**
	 * 测试人
	 */
	private String openId;
	private String userName;
	/**
	 * 结论ID
	 */
	private Integer conclusionId;
	private String conclusionName;
	/**
	 * 总分数
	 */
	private Integer score;
	/**
	 * 真实分数
	 */
    private Integer realScore;
    /**
     * 创建时间
     */
	private String createdTime;
	
	List<WxQuestResultDetail> details;
	
	private String key;

	List<WxQuestResultDetail> quest;

	public List<WxQuestResultDetail> getQuest() {
		return quest;
	}

	public void setQuest(List<WxQuestResultDetail> quest) {
		this.quest = quest;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getConclusionName() {
		return conclusionName;
	}
	public void setConclusionName(String conclusionName) {
		this.conclusionName = conclusionName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getConclusionId() {
		return conclusionId;
	}
	public void setConclusionId(Integer conclusionId) {
		this.conclusionId = conclusionId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getRealScore() {
		return realScore;
	}
	public void setRealScore(Integer realScore) {
		this.realScore = realScore;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public List<WxQuestResultDetail> getDetails() {
		return details;
	}
	public void setDetails(List<WxQuestResultDetail> details) {
		this.details = details;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
	
}
