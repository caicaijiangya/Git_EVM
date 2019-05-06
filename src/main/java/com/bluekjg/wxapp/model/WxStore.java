package com.bluekjg.wxapp.model;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Tim
 * @since 2018-06-25
 */
public class WxStore {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private Integer id;
	private Integer rid;
    /**
     * 门店编码
     */
	private String storeNo;
    /**
     * 门店名称
     */
	private String storeName;
	/**
	 * 省级
	 */
	private Integer province;
	private String provinceName;
	/**
	 * 市级
	 */
	private Integer city;
	private String cityName;
	/**
	 * 区级
	 */
	private Integer area;
	private String areaName;
    /**
     * 详细地址
     */
	private String addressDetail;
   
    /**
     * 经度
     */
	private Double longitude;
    /**
     * 纬度
     */
	private Double latitude;
    /**
     * 创建时间
     */
	private Date createdTime;
  
	/**
	 * 距离
	 */
	private Double distance;
	
	private List<WxStore> stores;
	
	private String storeDesc;
	
	private String storeNotice;
	//店主
	private String openId;
	private String deviceId;
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getStoreDesc() {
		return storeDesc;
	}

	public void setStoreDesc(String storeDesc) {
		this.storeDesc = storeDesc;
	}

	
	/**
	 * 手机号
	 */
	private String mobileNo;



	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<WxStore> getStores() {
		return stores;
	}

	public void setStores(List<WxStore> stores) {
		this.stores = stores;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getStoreNotice() {
		return storeNotice;
	}

	public void setStoreNotice(String storeNotice) {
		this.storeNotice = storeNotice;
	}

}
