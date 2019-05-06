package com.bluekjg.wxapp.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：BANNER表实体类
 * @author：pincui.tom
 * @date：2018/11/8 14:51
 */
@TableName("t_evm_goods_freight")
public class WxGoodsFreight implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/**
	 * 商品ID
	 */
	private Integer goodsId;
	/**
	 * 省份
	 */
	private Integer province;
	/**
	 * 运费
	 */
	private Double money;
	/**
	 * 超重运费
	 */
	private Double exceedMoney;
	/**
	 * 满足条件金额
	 */
	private Double fullMoney;
	
	
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getExceedMoney() {
		return exceedMoney;
	}
	public void setExceedMoney(Double exceedMoney) {
		this.exceedMoney = exceedMoney;
	}
	public Double getFullMoney() {
		return fullMoney;
	}
	public void setFullMoney(Double fullMoney) {
		this.fullMoney = fullMoney;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastModifiedTime() {
		return LastModifiedTime;
	}
	public void setLastModifiedTime(String lastModifiedTime) {
		LastModifiedTime = lastModifiedTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}
