package com.bluekjg.wxapp.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author Tim
 * @since 2018-10-09
 */
public class WxBrandStoryDetail  implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 主图
     */
    private String introImg;
    /**
     * 是否显示0 显示 1 不显示
     */
    private Integer isShow;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 描述
     */
    private String brandDetail;
    /**
     * 备注
     */
    private String note;
    /**
     * 是否删除 0 否 1是
     */
    private Integer isDel;
    /**
     * 创建时间
     */
    private java.util.Date createdTime;
    /**
     * 最后修改时间
     */
    private java.util.Date lastModifiedTime;

    /**
     * 最后修改时间
     */
    private Integer brandStoryId;

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

    public String getIntroImg() {
        return introImg;
    }

    public void setIntroImg(String introImg) {
        this.introImg = introImg;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getBrandDetail() {
        return brandDetail;
    }

    public void setBrandDetail(String brandDetail) {
        this.brandDetail = brandDetail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public java.util.Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(java.util.Date createdTime) {
        this.createdTime = createdTime;
    }

    public java.util.Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(java.util.Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }


	public Integer getBrandStoryId() {
		return brandStoryId;
	}

	public void setBrandStoryId(Integer brandStoryId) {
		this.brandStoryId = brandStoryId;
	}

}
