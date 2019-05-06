package com.bluekjg.wxapp.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：数据字典表实体类
 * @author：pincui.tom
 * @date：2018/9/26 14:51
 */
@TableName("t_evm_dict")
public class WxDict implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 唯一编号
	 */
	private String dictCode;
	/**
	 * 名称
	 */
	private String dictValue;
	/**
	 * 排序号
	 */
	private Integer indexs;
	
	
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
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	public Integer getIndexs() {
		return indexs;
	}
	public void setIndexs(Integer indexs) {
		this.indexs = indexs;
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
