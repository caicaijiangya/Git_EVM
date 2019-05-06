package com.bluekjg.wxapp.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：砍价赠送记录表实体类
 * @author：pincui.tom
 * @date：2018/12/10 14:51
 */
@TableName("t_evm_bargain_goods_user_log")
public class WxBargainGoodsUserLog implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 活动ID
	 */
	private Integer activityId;
	/**
	 * 商品ID
	 */
	private Integer goodsId;
	/**
	 * 微信编码
	 */
	private String openId;
	/**
	 * 赠送类型（1积分，2优惠券）
	 */
	private Integer type;
	/**
	 * 赠送值
	 */
	private Integer value;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
	
	
	
}
