package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 优惠券
 * </p>
 *
 * @author Tim
 * @since 2018-09-29
 */
@TableName("t_evm_coupon")
public class Coupon extends Model<Coupon> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
	@TableField("COUPON_NO")
	private String couponNo;
	@TableField("TITLE")
	private String title;
	@TableField("MONEY")
	private Double money;
	@TableField("FULL_MONEY")
	private Double fullMoney;
	@TableField("COUPON_TYPE")
	private Integer couponType;
	@TableField("COUPON_DESC")
	private String couponDesc;
	@TableField("START_TIME")
	private String startTime;
	@TableField("END_TIME")
	private String endTime;
	@TableField("IS_SHOW")
	private Integer isShow;
	@TableField("IS_ORIGINAL")
	private Integer isOriginal;
	@TableField("DUE_TYPE")
	private Integer dueType;
	@TableField("DUE_TIME")
	private String dueTime;
	@TableField("STATUS")
	private Integer status;
	@TableField("TYPE")
	private Integer type;
	@TableField("USER_NUM")
	private Integer userNum;
	@TableField("USER_DAY_NUM")
	private Integer userDayNum;
	@TableField("COUPON_NUM")
	private Integer couponNum;
	@TableField("IS_COUPON_NUM")
	private Integer isCouponNum;
	@TableField("IS_ACTIVITY_SHARED")
	private Integer isActivityShared;
	@TableField(exist=false)
	private String nickName;
	@TableField(exist=false)
	private String userName;
	@TableField(exist=false)
	private String filePath;
	@TableField(exist=false)
	private String goodsName;
	@TableField(exist=false)
	private String userType;
	@TableField(exist=false)
	private String newOld;
	@TableField(exist=false)
	private String storeName;
	@TableField(exist=false)
	private String useStoreName;
	@TableField(exist=false)
	private String issueTime;
	@TableField(exist=false)
	private String source;
	@TableField(exist=false)
	private String moneyName;
	@TableField(exist=false)
	private String statusName;
	@TableField(exist=false)
	private String orderNo;
	@TableField(exist=false)
	private String mobileNo;
	@TableField(exist=false)
	private String couponName;
	@TableField(exist=false)
	private Integer storeId;
	@TableField(exist=false)
	private Integer sourceId;
	@TableField(exist=false)
	private Integer sourceType;
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
	
	public Integer getIsActivityShared() {
		return isActivityShared;
	}

	public void setIsActivityShared(Integer isActivityShared) {
		this.isActivityShared = isActivityShared;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getNewOld() {
		return newOld;
	}

	public void setNewOld(String newOld) {
		this.newOld = newOld;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getUseStoreName() {
		return useStoreName;
	}

	public void setUseStoreName(String useStoreName) {
		this.useStoreName = useStoreName;
	}

	public String getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMoneyName() {
		return moneyName;
	}

	public void setMoneyName(String moneyName) {
		this.moneyName = moneyName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getUserDayNum() {
		return userDayNum;
	}

	public void setUserDayNum(Integer userDayNum) {
		this.userDayNum = userDayNum;
	}

	public Integer getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}

	public Integer getIsCouponNum() {
		return isCouponNum;
	}

	public void setIsCouponNum(Integer isCouponNum) {
		this.isCouponNum = isCouponNum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public Integer getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(Integer isOriginal) {
		this.isOriginal = isOriginal;
	}

	public Integer getDueType() {
		return dueType;
	}

	public void setDueType(Integer dueType) {
		this.dueType = dueType;
	}

	public String getDueTime() {
		return dueTime;
	}

	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getFullMoney() {
		return fullMoney;
	}

	public void setFullMoney(Double fullMoney) {
		this.fullMoney = fullMoney;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
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

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
