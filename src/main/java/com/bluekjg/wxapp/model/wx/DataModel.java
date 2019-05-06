package com.bluekjg.wxapp.model.wx;

import com.bluekjg.core.utils.StringUtil;

/**
 * 数据传递
 * @author tim
 *
 */
public class DataModel {
	
	//用户标识
	private String openId;
	
	//门店ID
	private Integer storeId;
	
	//小程序appId
	private String appId;
	
	//数据查询标识
	private Integer dataId;
	
	//经度
	private Double longitude;  
	
	//纬度
    private Double latitude;
    
    //分页
    private Integer currPage;
    
    //活动ID
    private Integer activityId;
    
    //商品ID
    private Integer goodsId;
    
    //商品数量
    private Integer goodsNums;
    
    //订单ID
    private Integer orderId;
   //用户门店类型
    private Integer userType;
    
    private String code;
    
    private String iv;
    
    private String signature;
    
    private String encryptedData;
    
    private Integer status;
    
    private Integer codeType;  //核销订单数据
    
    private String startDate;
    
    private Integer buyType;
    
    private String endDate;
    
    private Integer id;
    
    private String condition;
    
    public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public DataModel() {
	}

	public DataModel(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
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

	public Integer getCurrPage() {
		return StringUtil.isEmpty(currPage)?1:currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getGoodsNums() {
		return goodsNums;
	}

	public void setGoodsNums(Integer goodsNums) {
		this.goodsNums = goodsNums;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCodeType() {
		return codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
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

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}
}
