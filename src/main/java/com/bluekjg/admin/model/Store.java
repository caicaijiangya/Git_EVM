package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 门店主表
 * </p>
 * @author Tom
 * @since 2018-09-25
 */
@TableName("t_evm_store")
public class Store extends Model<Store> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 门店编号
     */
	@TableField(exist=false)
	private String storeNo;
	/**
     * 门店名称
     */
	@TableField(exist=false)
	private String storeName;
	/**
     * 省
     */
	@TableField(exist=false)
	private Integer province;
	/**
     * 市
     */
	@TableField(exist=false)
	private Integer city;
	/**
     * 区
     */
	@TableField(exist=false)
	private Integer area;
	/**
     * 详细地址
     */
	@TableField(exist=false)
	private String addressDetail;
	/**
	 * 经度
	 */
	@TableField(exist=false)
	private Double longitude;
	/**
	 * 纬度
	 */
	@TableField(exist=false)
	private Double latitude;
	/**
	 * 手机号
	 */
	@TableField(exist=false)
	private String mobileNo;
	
    /**
     * 是否删除（0否，1是）
     */
	@TableField(exist=false)
	private Integer isDel;
    /**
     * 创建日期
     */
	@TableField(exist=false)
	private Date createdTime;
    /**
     * 修改日期
     */
	@TableField(exist=false)
	private Date lastModifiedTime;
    /**
     * 备注
     */
	@TableField(exist=false)
	private String note;
	//省名
	@TableField(exist=false)
	private String name1;
	//市名
	@TableField(exist=false)
	private String name2;
	//区名
	@TableField(exist=false)
	private String name3;
	@TableField(exist=false)
	private String qrCode;
	@TableField(exist=false)
	private String deviceId;
	@TableField(exist=false)
	private String deviceSecret;
	
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceSecret() {
		return deviceSecret;
	}
	public void setDeviceSecret(String deviceSecret) {
		this.deviceSecret = deviceSecret;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	public void setLastModifiedTime(Date date) {
		this.lastModifiedTime = date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
