package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 静态资源文件表
 * </p>
 * @author Tom
 * @since 2018-09-25
 */
@TableName("t_evm_static_files")
public class StaticFiles extends Model<StaticFiles> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
	/**
	 * 关联ID
	 */
	@TableField(exist=false)
	private Integer relationId;
    /**
     * 文件完整http路径
     */
	@TableField(exist=false)
	private String filePath;
	/**
	 * 文件类型（0图片，1视频，2文档）
	 */
	@TableField(exist=false)
	private Integer fileType;
	/**
	 * 大分类（0商品，1优惠券，2秒杀商品，3特价商品，4团购商品，5banner）
	 */
	@TableField(exist=false)
	private Integer bigType;
	/**
	 * 小分类
	 */
	@TableField(exist=false)
	private Integer smallType;
	/**
	 * 排序
	 */
	@TableField(exist=false)
	private Integer seq;
    /**
     * 是否删除（0否，1是）
     */
	@TableField(exist=false)
	private Integer isDel;
    /**
     * 创建日期
     */
	@TableField(exist=false)
	private String createdTime;
    /**
     * 修改日期
     */
	@TableField(exist=false)
	private String lastModifiedTime;
    /**
     * 备注
     */
	@TableField(exist=false)
	private String note;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

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

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
}
