package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 小程序用户授权后用户信息表
 * </p>
 *
 * @author Max
 * @since 2018-03-30
 */
@TableName("t_evm_wx_user")
public class WxUserInfo extends Model<WxUserInfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="ID", type= IdType.AUTO)
	private Long id;
    /**
     * 分类名称
     */
	@TableField("OPEN_ID")
	private String openId;
	/**
     * 用户编号
     */
	@TableField("CODE")
	private String code;
    /**
     * 微信手机号
     */
	@TableField("MOBILE_NO")
	private String mobileNo;
    /**
     * 真实姓名
     */
	@TableField("USER_NAME")
	private String userName;
    /**
     * 微信名称
     */
	@TableField("NICK_NAME")
	private String nickName;
    /**
     * 微信头像
     */
	@TableField("HEAD_IMG_URL")
	private String headImgUrl;
    /**
     * 账户金额
     */
	@TableField("MONEY")
	private Float money;
    /**
     * 账户积分
     */
	@TableField("INTEGRAL")
	private Double integral;
	//当前积分
	@TableField(exist=false)
	private Double currentIntegral;
	//总积分
	@TableField(exist=false)
	private Double totalIntegral;
	/**
     * 已消费积分
     */
	@TableField("CON_INTEGRAL")
	private Double conIntegral;
	/**
     * 失效积分
     */
	@TableField(exist=false)
	private Double failIntegral;
	/**
     * 是否禁用积分（0否，1是）
     */
	@TableField("IS_INTEGRAL")
	private Integer isIntegral;
    /**
     * 性别0,未知 1,男 2,女
     */
	@TableField("SEX")
	private Integer sex;
    /**
     * 国家
     */
	@TableField("COUNTRY")
	private String country;
    /**
     * 省份
     */
	@TableField("PROVINCE")
	private String province;
    /**
     * 城市
     */
	@TableField("CITY")
	private String city;
    /**
     * 用户类型
     */
	@TableField("USER_TYPE")
	private Integer userType;
    /**
     * 创建日期
     */
	@TableField("CREATED_TIME")
	private Date createdTime;
    /**
     * 是否删除（0未删除，1已删除）
     */
	@TableField("IS_DEL")
	private Integer isDel;
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
    /**
     * UNIONID
     */
	@TableField("UNION_ID")
	private String unionId;
	//门店ID
	@TableField("STORE_ID")
	private Integer storeId;
	//是否授权
	@TableField("IS_AUTH")
	private Integer isAuth;
	//门店名称
	@TableField(exist=false)
	private String storeName;
	//权限模块名称
	@TableField(exist=false)
	private String modelName;
	@TableField(exist=false)
	private Integer status;
	@TableField(exist=false)
	private Integer type;
	@TableField(exist=false)
	private String title;
	
	@TableField(exist=false)
	private String startTime;
	@TableField(exist=false)
	private String endTime;
	
	
	public Double getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(Double totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	public Double getCurrentIntegral() {
		return currentIntegral;
	}

	public void setCurrentIntegral(Double currentIntegral) {
		this.currentIntegral = currentIntegral;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Double getFailIntegral() {
		return failIntegral;
	}

	public void setFailIntegral(Double failIntegral) {
		this.failIntegral = failIntegral;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getConIntegral() {
		return conIntegral;
	}

	public void setConIntegral(Double conIntegral) {
		this.conIntegral = conIntegral;
	}

	public Integer getIsIntegral() {
		return isIntegral;
	}

	public void setIsIntegral(Integer isIntegral) {
		this.isIntegral = isIntegral;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Double getIntegral() {
		return integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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
	
	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
