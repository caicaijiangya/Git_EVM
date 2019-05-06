package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * 积分规则
 * @author Tom
 *
 */
public class WxIntegralActivity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Integer id;
	private String title;
	/**
	 * 类型（1多倍积分，2邀请好友获得积分）
	 */
	private Integer type;
	/**
	 * 积分倍数
	 */
	private Integer multiple;
	/**
	 * 赠送积分
	 */
	private Integer integral;
	
	private List<Integer> storeList;
	
	public List<Integer> getStoreList() {
		return storeList;
	}
	public void setStoreList(List<Integer> storeList) {
		this.storeList = storeList;
	}
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getMultiple() {
		return multiple;
	}
	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	
	
}
