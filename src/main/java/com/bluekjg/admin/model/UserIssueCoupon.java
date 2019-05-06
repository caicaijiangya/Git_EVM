package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 优惠券人工发放
 * </p>
 *
 * @author Tom
 * @since 2019-02-27
 */
@TableName("t_evm_user_issue_coupon")
public class UserIssueCoupon extends Model<UserIssueCoupon> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
	@TableField("OPEN_ID")
	private String openId;
	@TableField("COUPON_ID")
	private Integer couponId;
	@TableField("USER_NUM")
	private Integer userNum;
	@TableField("SUCCESS_NUM")
	private Integer successNum;
	@TableField("ERROR_NUM")
	private Integer errorNum;
	@TableField("USER_ID")
	private Integer userId;
	
	@TableField(exist=false)
	private String couponNo;
	@TableField(exist=false)
	private String title;
	@TableField(exist=false)
	private String userName;
	@TableField(exist=false)
	private String nickName;
	@TableField(exist=false)
	private String mobileNo;
	@TableField(exist=false)
	private String name;
	@TableField(exist=false)
	private Integer type;
	@TableField(exist=false)
	private String newOld;
	@TableField(exist=false)
	private String storeName;
	@TableField(exist=false)
	private String userType;
	
    /**
     * 是否删除（0否，1是）
     */
	@TableField("IS_DEL")
	private Integer isDel;
    /**
     * 创建日期
     */
	@TableField("CREATED_TIME")
	private String createdTime;
    /**
     * 修改日期
     */
	@TableField("LAST_MODIFIED_TIME")
	private String lastModifiedTime;
    /**
     * 备注
     */
	@TableField("NOTE")
	private String note;
	
	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public String getNewOld() {
		return newOld;
	}


	public void setNewOld(String newOld) {
		this.newOld = newOld;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getOpenId() {
		return openId;
	}


	public void setOpenId(String openId) {
		this.openId = openId;
	}


	public Integer getCouponId() {
		return couponId;
	}


	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}


	public Integer getUserNum() {
		return userNum;
	}


	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}


	public Integer getSuccessNum() {
		return successNum;
	}


	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}


	public Integer getErrorNum() {
		return errorNum;
	}


	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
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


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
