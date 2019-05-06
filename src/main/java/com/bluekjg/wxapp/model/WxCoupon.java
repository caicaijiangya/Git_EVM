package com.bluekjg.wxapp.model;


/**
 * <p>
 * 	优惠券实体类
 * </p>
 *
 * @author Tom
 * @since 2018-09-25
 */
public class WxCoupon {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private Integer id;
	private Integer rid;
	private Integer couponId;
	private String couponNo;
    /**
     * 优惠券标题
     */
	private String title;
	/**
	 * 优惠券图片
	 */
	private String counponImages;
    /**
     * 优惠金额
     */
	private Double money;
	/**
	 * 满足条件的金额
	 */
	private Double fullMoney;
	/**
	 * 优惠券类型(0-满减)
	 */
	private Integer couponType;
	/**
	 * 优惠券描述
	 */
	private String couponDesc;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 状态（0正常，1已使用）
	 */
	private Integer status;
	
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	private Double goodsPrice;
	private Integer goodsAmount;
	private String openId;
	/**
	 * 是否过期（0否，1是）
	 */
	private Integer isTime;
	private Integer sourceType;
	private Integer sourceId;
	private Integer userNum;
	private Integer userDayNum;
	private Integer couponNum;
	private Integer isCouponNum;
	private Integer isActivityShared;
	
	public Integer getIsActivityShared() {
		return isActivityShared;
	}
	public void setIsActivityShared(Integer isActivityShared) {
		this.isActivityShared = isActivityShared;
	}
	public String getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public Integer getUserDayNum() {
		return userDayNum;
	}
	public void setUserDayNum(Integer userDayNum) {
		this.userDayNum = userDayNum;
	}
	public Integer getIsCouponNum() {
		return isCouponNum;
	}
	public void setIsCouponNum(Integer isCouponNum) {
		this.isCouponNum = isCouponNum;
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
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public Integer getIsTime() {
		return isTime;
	}
	public void setIsTime(Integer isTime) {
		this.isTime = isTime;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Integer getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getCounponImages() {
		return counponImages;
	}
	public void setCounponImages(String counponImages) {
		this.counponImages = counponImages;
	}
	
	
}
