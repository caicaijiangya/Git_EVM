package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * @description：ERP接口实体类
 * @author：pincui.tom
 * @date：2018/10/19 14:51
 */
public class WxErpOrder implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/**
	 * 门店代码
	 */
	private String shopCode;
	/**
	 * 会员代码
	 */
	private String vipCode;
	/**
	 * 平台单号
	 */
	private String platformCode;
	/**
	 * 仓库代码
	 */
	private String warehouseCode;
	/**
	 * 物流公司代码
	 */
	private String expressCode;
	/**
	 * 拍单时间
	 */
	private String dealDatetime;
	/**
	 * 支付时间
	 */
	private String payDatetime;
	/**
	 * 收货人
	 */
	private String receiverName;
	/**
	 * 收货人手机号
	 */
	private String receiverMobile;
	/**
	 * 收货地址
	 */
	private String receiverAddress;
	/**
	 * 省
	 */
	private String receiverProvince;
	/**
	 * 市
	 */
	private String receiverCity;
	/**
	 * 区
	 */
	private String receiverDistrict;
	/**
	 * 支付金额
	 */
	private String payment;
	/**
	 * 交易单号
	 */
	private String payCode;
	
	/**
	 * 商品明细
	 */
	private List<WxErpOrderDetail> details;
	
	
	private String erpId;
	private String erpCode;
	private Integer storeId;
	private Integer takeStyle;
	
	public Integer getTakeStyle() {
		return takeStyle;
	}

	public void setTakeStyle(Integer takeStyle) {
		this.takeStyle = takeStyle;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getErpId() {
		return erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getErpCode() {
		return erpCode;
	}

	public void setErpCode(String erpCode) {
		this.erpCode = erpCode;
	}

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getPayDatetime() {
		return payDatetime;
	}

	public void setPayDatetime(String payDatetime) {
		this.payDatetime = payDatetime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getVipCode() {
		return vipCode;
	}

	public void setVipCode(String vipCode) {
		this.vipCode = vipCode;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getDealDatetime() {
		return dealDatetime;
	}

	public void setDealDatetime(String dealDatetime) {
		this.dealDatetime = dealDatetime;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public List<WxErpOrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<WxErpOrderDetail> details) {
		this.details = details;
	}
	
	
}
