package com.bluekjg.wxapp.model;

import java.io.Serializable;

/**
 * <p>
 * 问卷测试维度表
 * </p>
 *
 * @author Tom
 * @since 2018-09-12
 */
public class WxQuestDimension implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private Integer id;
	/**
	 * 分类ID
	 */
	private Integer classifyId;
    /**
     * 英文标识（A,B,C,D,E,F,G,H）
     */
	private String key;
	/**
     * 名称
     */
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
