package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description：微信分页参数实体类
 * @author：pincui.tom
 * @date：2018/4/4 14:51
 */
public class PagingDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer rid;  //分页插件配置参数
	private Integer minId;
	private Integer pageSize = 6;//每页显示多少条记录
	private Integer pageCount;//一共有多少页
	private Integer rowCount;//一共有多少条记录
	private Integer pageNow;//当前页
	private boolean isPageNo;//分页是否结束
	private Integer status;//
	private String code;
	private String openId;
	private String scanOpenId;
	private Integer id;
	private Integer rectifyType;
	private String title;		//标题
	private Integer type;		//类型
	private Integer userId;		//用户Id
	private String date;		//日期
	private String mobileNo;	//手机号
	private String startDate;	//开始时间
	private String endDate;		//结束时间
	private Integer storeId;	//门店ID
	private Integer jumpType; //跳转的类型
	private Integer msgId; //消息ID
	private Integer areaId;	//区县ID
	private Double money;
	private Integer provinceId; //省份Id
	private Integer cityId; //地市Id
	private String name;	//名字
	private String address; //地址
	private Integer isPreferred;
	private Integer goodsId;
	private Integer activityId;
	private Integer addressId;
	private Integer orderId;
	private Integer brandId;
	private String classifyId;
	private Integer specId;
	private Integer sourceId;
	private Integer collageId;
	private String items[];
	private List<Map<String,Object>> itemList;
	private String lon;
	private String lat;

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public Integer getCollageId() {
		return collageId;
	}
	public void setCollageId(Integer collageId) {
		this.collageId = collageId;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public List<Map<String, Object>> getItemList() {
		return itemList;
	}
	public void setItemList(List<Map<String, Object>> itemList) {
		this.itemList = itemList;
	}
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	public String[] getItems() {
		return items;
	}
	public void setItems(String[] items) {
		this.items = items;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getIsPreferred() {
		return isPreferred;
	}
	public void setIsPreferred(Integer isPreferred) {
		this.isPreferred = isPreferred;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getScanOpenId() {
		return scanOpenId;
	}
	public void setScanOpenId(String scanOpenId) {
		this.scanOpenId = scanOpenId;
	}
	public Integer getJumpType() {
		return jumpType;
	}
	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isPageNo() {
		return isPageNo;
	}
	public void setPageNo(boolean isPageNo) {
		this.isPageNo = isPageNo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRectifyType() {
		return rectifyType;
	}
	public void setRectifyType(Integer rectifyType) {
		this.rectifyType = rectifyType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public Integer getPageNow() {
		return pageNow;
	}
	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}
	public Integer getMinId() {
		return minId;
	}
	public void setMinId(Integer minId) {
		this.minId = minId;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
