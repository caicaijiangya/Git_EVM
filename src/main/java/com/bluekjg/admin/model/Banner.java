package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * banner管理表
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
@TableName("t_evm_banner")
public class Banner extends Model<Banner> {

    private static final long serialVersionUID = 1L;

	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 标题
     */
	@TableField("TITLE")
	private String title;
    /**
     * 跳转地址	
     */
	@TableField("URL")
	private String url;
    /**
     * 图片宽度
     */
	@TableField("WIDTH")
	private Integer width;
    /**
     * 图片高度
     */
	@TableField("HEIGHT")
	private Integer height;
    /**
     * 类型（0首页，1优惠券，2活动专区）
     */
	@TableField("TYPE")
	private Integer type;
    /**
     * 创建时间
     */
	@TableField("CREATED_TIME")
	private Date createdTime;
    /**
     * 是否删除 : 0-否 , 1-是
     */
	@TableField("IS_DEL")
	private Integer isDel;
    /**
     * 最后更改时间
     */
	@TableField("LAST_MODIFIED_TIME")
	private Date lastModifiedTime;
    /**
     * 备注
     */
	@TableField("NOTE")
	private String note;
	//图片
	@TableField(exist=false)
	private String filePath;
	//图片序号
	@TableField(exist=false)
	private String fileSeq;
	//图片类型
	@TableField(exist=false)
	private Integer fileType;
	//图片分类
	@TableField(exist=false)
	private Integer bigType;
	//图片分类
	@TableField(exist=false)
	private Integer smallType;

	
	
	public String getFileSeq() {
		return fileSeq;
	}

	public void setFileSeq(String fileSeq) {
		this.fileSeq = fileSeq;
	}

	public Integer getSmallType() {
		return smallType;
	}

	public void setSmallType(Integer smallType) {
		this.smallType = smallType;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
