package com.bluekjg.wxapp.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 首页弹窗管理表
 * </p>
 *
 * @author Tom
 * @since 2018-10-24
 */
@TableName("t_evm_pop_config")
public class WxPopConfig extends Model<WxPopConfig> {

    private static final long serialVersionUID = 1L;

	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 标题
     */
	@TableField("TITLE")
	private String title;
	/**
	 * 优惠券ID
	 */
	@TableField("COUPON_ID")
	private Integer couponId;
	/**
     * 开始时间
    */
	@TableField("START_TIME")
	private String startTime;
	/**
     * 结束时间
    */
	@TableField("END_TIME")
	private String endTime;
	/**
     * 类型（0普通弹窗，1优惠券领取弹窗）
     */
	@TableField("TYPE")
	private Integer type;
	/**
     * 状态（0正常，1关闭）
     */
	@TableField("STATUS")
	private Integer status;
    /**
     * 跳转地址	
     */
	@TableField("URL")
	private String url;
	/**
	 * 自动关闭时间（秒）
	 */
    @TableField("TIMING")
    private Integer timing;
    /**
     * 创建时间
     */
	@TableField("CREATED_TIME")
	private String createdTime;
    /**
     * 是否删除 : 0-否 , 1-是
     */
	@TableField("IS_DEL")
	private Integer isDel;
    /**
     * 最后更改时间
     */
	@TableField("LAST_MODIFIED_TIME")
	private String lastModifiedTime;
    /**
     * 备注
     */
	@TableField("NOTE")
	private String note;
	
	@TableField(exist=false)
	private String couponName;
	@TableField(exist=false)
	private String image1;
	@TableField(exist=false)
	private String image2;
	@TableField(exist=false)
	private Integer isReceive;
	
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(Integer isReceive) {
		this.isReceive = isReceive;
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

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getTiming() {
		return timing;
	}

	public void setTiming(Integer timing) {
		this.timing = timing;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	
}
