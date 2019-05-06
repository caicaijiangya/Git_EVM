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
 * 优惠券活动
 * </p>
 *
 * @author Tom
 * @since 2019-02-26
 */
@TableName("t_evm_coupon_activity")
public class CouponActivity extends Model<CouponActivity> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
	@TableField("COUPON_ID")
	private Integer couponId;
	@TableField("LIKE_URL")
	private String likeUrl;
	@TableField("USER_NUM")
	private Integer userNum;
	@TableField("USER_DAY_NUM")
	private Integer userDayNum;
	@TableField("COUPON_NUM")
	private Integer couponNum;
	@TableField("IS_COUPON_NUM")
	private Integer isCouponNum;
	@TableField("START_TIME")
	private String startTime;
	@TableField("END_TIME")
	private String endTime;
	@TableField("TYPE")
	private Integer type;
	@TableField("FORM_TYPE")
	private Integer formType;
	@TableField("STATUS")
	private Integer status;
	@TableField(exist=false)
	private String couponNo;
	@TableField(exist=false)
	private String couponName;
	@TableField("TITLE")
	private String title;
    /**
     * 是否删除（0否，1是）
     */
	@TableField("IS_DEL")
	private Integer isDel;
    /**
     * 创建日期
     */
	@TableField("CREATED_TIME")
	private Date createdTime;
    /**
     * 修改日期
     */
	@TableField("LAST_MODIFIED_TIME")
	private Date lastModifiedTime;
    /**
     * 备注
     */
	@TableField("NOTE")
	private String note;
	

	public Integer getFormType() {
		return formType;
	}

	public void setFormType(Integer formType) {
		this.formType = formType;
	}

	public Integer getUserDayNum() {
		return userDayNum;
	}

	public void setUserDayNum(Integer userDayNum) {
		this.userDayNum = userDayNum;
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

	public Integer getIsCouponNum() {
		return isCouponNum;
	}

	public void setIsCouponNum(Integer isCouponNum) {
		this.isCouponNum = isCouponNum;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getLikeUrl() {
		return likeUrl;
	}

	public void setLikeUrl(String likeUrl) {
		this.likeUrl = likeUrl;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public Integer getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
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

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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
