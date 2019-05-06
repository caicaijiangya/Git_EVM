package com.bluekjg.wxapp.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：砍价价格配置表实体类
 * @author：pincui.tom
 * @date：2018/12/10 14:51
 */
@TableName("t_evm_activity_goods_bargain_price")
public class WxActivityGoodsBargainPrice implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 砍价ID
	 */
	private Integer bargainId;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 使用次数
	 */
	private Integer useNum;
	
	
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
	public Integer getBargainId() {
		return bargainId;
	}
	public void setBargainId(Integer bargainId) {
		this.bargainId = bargainId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getUseNum() {
		return useNum;
	}
	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
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
