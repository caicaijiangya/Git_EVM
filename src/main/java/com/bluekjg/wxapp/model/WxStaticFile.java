package com.bluekjg.wxapp.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：静态资源文件表实体类
 * @author：pincui.tom
 * @date：2018/9/26 14:51
 */
@TableName("t_evm_static_files")
public class WxStaticFile implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer relationId;
	/**
	 * 文件完整http路径
	 */
	private String filePath;
	/**
	 * 文件类型（0图片，1视频，2文档）
	 */
	private Integer fileType;
	/**
	 * 大分类（0商品，1优惠券，2秒杀商品，3特价商品，4团购商品，5banner）
	 */
	private Integer bigType;
	/**
	 * 小分类
	 */
	private Integer smallType;
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
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public Integer getBigType() {
		return bigType;
	}
	public void setBigType(Integer bigType) {
		this.bigType = bigType;
	}
	public Integer getSmallType() {
		return smallType;
	}
	public void setSmallType(Integer smallType) {
		this.smallType = smallType;
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
