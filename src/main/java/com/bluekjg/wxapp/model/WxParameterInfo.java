package com.bluekjg.wxapp.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;

/**
 * @author Tim
 * @since 2018-07-04
 */
@TableName("t_store_xs_parameter_info")
public class WxParameterInfo extends Model<WxParameterInfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 小程序AppId
     */
	@TableField("APPID")
	private String appid;
    /**
     * 小程序商户号
     */
	@TableField("PARTNERLD")
	private String partnerld;
    /**
     * 小程序密钥
     */
	@TableField("APPSECRET")
	private String appsecret;
	
	
	 /**
     * 小程序密钥
     */
	@TableField("MERCHANT_KEY")
	private String merchantKey;
	
	
    /**
     * 门店ID
     */
	@TableField("STORE_ID")
	private Integer storeId;
	 /**
     * 门店名称
     */
	@TableField(exist=false)
	private String storeName;
	
    /**
     * 创建时间
     */
	@TableField("CREATED_TIME")
	private Date createdTime;
    /**
     * 是否删除 : 0-否 ,1-是
     */
	@TableField("IS_DEL")
	private Integer isDel;
    /**
     * 最后一次修改时间
     */
	@TableField("LAST_MODIFIED_TIME")
	private Date lastModifiedTime;
    /**
     * 备注
     */
	@TableField("NOTE")
	private String note;


	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPartnerld() {
		return partnerld;
	}

	public void setPartnerld(String partnerld) {
		this.partnerld = partnerld;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	public String getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
