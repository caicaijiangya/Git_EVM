package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：用户信息表实体类
 * @author：pincui.tom
 * @date：2018/3/26 14:51
 */
@TableName("t_evm_wx_user")
public class UserBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer rid;
	//openId
	private String openId;
	//门店ID
	private Integer storeId;
	//微信手机号
	private String mobileNo;
	//用户名称
	private String userName;
	//用户昵称
	private String nickName;
	//微信头像
	private String headImgUrl;
	//性别0,未知 1,男 2,女
	private Integer sex;
	//用户类型
	private Integer userType;
	//账户金额
	private Double money;
	//可用金额
	private Double availableMoney;
	//积分
	private Integer integral;
	//国家
	private String country;
	//省份
	private String province;
	//地市
	private String city;
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	private String code;
	//是否授权
	private Integer isAuth;
	private List<WxKeeperIndexModel> authorityList;
	private String inviteOpenId;//邀请人
	
	public String getInviteOpenId() {
		return inviteOpenId;
	}
	public void setInviteOpenId(String inviteOpenId) {
		this.inviteOpenId = inviteOpenId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<WxKeeperIndexModel> getAuthorityList() {
		return authorityList;
	}
	public void setAuthorityList(List<WxKeeperIndexModel> authorityList) {
		this.authorityList = authorityList;
	}
	public Integer getIsAuth() {
		return isAuth;
	}
	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getAvailableMoney() {
		return availableMoney;
	}
	public void setAvailableMoney(Double availableMoney) {
		this.availableMoney = availableMoney;
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
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	
}
