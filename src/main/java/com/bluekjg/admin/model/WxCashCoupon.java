package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * @author Tim
 * @since 2018-08-03
 */
@TableName("t_evm_wx_cash_coupon")
public class WxCashCoupon extends Model<WxCashCoupon> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("card_id")
	private String cardId;
    /**
     * 卡券ID。一个卡券ID对应一类卡券，包含了相应库存数量的Code码。
     */
	@TableField("activity_id")
	private String activityId;
    /**
     * 劵面金额
     */
	private Double money;
    /**
     * 满额多少
     */
	@TableField("full_money")
	private Double fullMoney;
    /**
     * 卡券的商户logo，建议像素为300*300。
     */
	@TableField("logo_url")
	private String logoUrl;
    /**
     * 码型： "CODE_TYPE_TEXT"文 本 ； "CODE_TYPE_BARCODE"一维码 "CODE_TYPE_QRCODE"二维码 "CODE_TYPE_ONLY_QRCODE",二维码无code显示； "CODE_TYPE_ONLY_BARCODE",一维码无code显示；CODE_TYPE_NONE， 不显示code和条形码类型
     */
	@TableField("code_type")
	private String codeType;
    /**
     * 商户名字,字数上限为12个汉字
     */
	@TableField("brand_name")
	private String brandName;
    /**
     * 卡券名，字数上限为9个汉字
     */
	private String title;
    /**
     * 券颜色。按色彩规范标注填写Color010-Color100。
     */
	private String color;
    /**
     * 卡券使用提醒
     */
	private String notice;
    /**
     * 卡券使用说明，字数上限为1024个汉字。
     */
	private String description;
    /**
     * 卡券库存的数量
     */
	@TableField("quantity")
	private Integer quantity;
    /**
     * 使用时间的类型
     */
	private String type;
	
	@TableField("store_id")
	private Integer storeId;
    /**
     * 起用时间
     */
	@TableField("begin_timestamp")
	private Date beginTimestamp;
    /**
     * 结束时间 
     */
	@TableField("end_timestamp")
	private Date endTimestamp;
    /**
     * type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，不支持填写0
     */
	@TableField("fixed_term")
	private Integer fixedTerm;
    /**
     * type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效，领取后当天生效填写0。（单位为天）
     */
	@TableField("fixed_begin_term")
	private Integer fixedBeginTerm;
    /**
     * 可用于DATE_TYPE_FIX_TERM时间类型，表示卡券统一过期时间 ， 建议设置为截止日期的23:59:59过期 
     */
	@TableField("end_time_stamp")
	private Date endTimeStamp;
    /**
     * 单日最大领取次数
     */
	@TableField("get_limit")
	private Integer getLimit;
    /**
     * 最大领取次数
     */
	@TableField("use_limit")
	private Integer useLimit;
    /**
     * 卡券领取页面是否可分享。(0 否 1 是)
     */
	@TableField("can_share")
	private Integer canShare;
    /**
     * 卡券是否可转赠。(0否 1 是)
     */
	@TableField("can_give_friend")
	private Integer canGiveFriend;
	@TableField("center_app_brand_pass")
	private String centerAppBrandPass;
    /**
     * 顶部居中的url 
     */
	@TableField("center_url")
	private String centerUrl;
	@TableField("center_title")
	private String centerTitle;
    /**
     * 卡券跳转的小程序的user_name，仅可跳转该 公众号绑定的小程序 
     */
	@TableField("center_app_brand_user_name")
	private String centerAppBrandUserName;
    /**
     * 显示在入口下方的提示语 ，仅在卡券状态正常(可以核销)时显示。
     */
	@TableField("center_sub_title")
	private String centerSubTitle;
    /**
     * 是否删除(0 否 1 是)
     */
	@TableField("is_del")
	private Integer isDel;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private Date createdTime;
    /**
     * 最后修改时间
     */
	@TableField("last_modified_time")
	private Date lastModifiedTime;
    /**
     * 备注
     */
	private String note;
    /**
     * 是否激活(0否 1 是 默认0)
     */
	@TableField("is_activity")
	private Integer isActivity;
	
	@TableField(exist=false)
	private String storeName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
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

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getBeginTimestamp() {
		return beginTimestamp;
	}

	public void setBeginTimestamp(Date beginTimestamp) {
		this.beginTimestamp = beginTimestamp;
	}

	public Date getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Date endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public Integer getFixedTerm() {
		return fixedTerm;
	}

	public void setFixedTerm(Integer fixedTerm) {
		this.fixedTerm = fixedTerm;
	}

	public Integer getFixedBeginTerm() {
		return fixedBeginTerm;
	}

	public void setFixedBeginTerm(Integer fixedBeginTerm) {
		this.fixedBeginTerm = fixedBeginTerm;
	}

	public Date getEndTimeStamp() {
		return endTimeStamp;
	}

	public void setEndTimeStamp(Date endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
	}

	public Integer getGetLimit() {
		return getLimit;
	}

	public void setGetLimit(Integer getLimit) {
		this.getLimit = getLimit;
	}

	public Integer getUseLimit() {
		return useLimit;
	}

	public void setUseLimit(Integer useLimit) {
		this.useLimit = useLimit;
	}

	public Integer getCanShare() {
		return canShare;
	}

	public void setCanShare(Integer canShare) {
		this.canShare = canShare;
	}

	public Integer getCanGiveFriend() {
		return canGiveFriend;
	}

	public void setCanGiveFriend(Integer canGiveFriend) {
		this.canGiveFriend = canGiveFriend;
	}

	public String getCenterAppBrandPass() {
		return centerAppBrandPass;
	}

	public void setCenterAppBrandPass(String centerAppBrandPass) {
		this.centerAppBrandPass = centerAppBrandPass;
	}

	public String getCenterUrl() {
		return centerUrl;
	}

	public void setCenterUrl(String centerUrl) {
		this.centerUrl = centerUrl;
	}

	public String getCenterTitle() {
		return centerTitle;
	}

	public void setCenterTitle(String centerTitle) {
		this.centerTitle = centerTitle;
	}

	public String getCenterAppBrandUserName() {
		return centerAppBrandUserName;
	}

	public void setCenterAppBrandUserName(String centerAppBrandUserName) {
		this.centerAppBrandUserName = centerAppBrandUserName;
	}

	public String getCenterSubTitle() {
		return centerSubTitle;
	}

	public void setCenterSubTitle(String centerSubTitle) {
		this.centerSubTitle = centerSubTitle;
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

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getIsActivity() {
		return isActivity;
	}

	public void setIsActivity(Integer isActivity) {
		this.isActivity = isActivity;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
