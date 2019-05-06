package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 活动主表
 * </p>
 *
 * @author Tim
 * @since 2018-09-28
 */
@TableName("t_evm_activity")
public class Activity extends Model<Activity> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 活动标题
     */
	@TableField("TITLE")
	private String title;
    /**
     * 活动类型：0拼团，1秒杀，2特价
     */
	@TableField("ACTIVITY_TYPE")
	private Integer activityType;
    /**
     * 活动开始时间
     */
	@TableField("ACTIVITY_START_TIME")
	private Date activityStartTime;
    /**
     * 活动结束时间
     */
	@TableField("ACTIVITY_END_TIME")
	private Date activityEndTime;
	/**
     * 是否叠加（0不叠加，1叠加）
     */
	@TableField("IS_OVERLAY")
	private Integer isOverlay;
    /**
     * 状态（0待审核，1已开始，2已结束）
     */
	@TableField("STATUS")
	private Integer status;
    /**
     * 是否删除（0否，1是）
     */
	@TableField("IS_DEL")
	private Integer isDel;
    /**
     * 创建日期
     */
	@TableField("CREATED_TIME")
	private Date createdTime;
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
	
	//开始日期
	@TableField(exist=false)
	private String dateStart;
	//结束日期
	@TableField(exist=false)
	private String dateEnd;
	//商品名
	@TableField(exist=false)
	private String goodsName;
	//品牌名
	@TableField(exist=false)
	private String name;
	//商品图片
	@TableField(exist=false)
	private String filePath;
	//商品活动库存
	@TableField(exist=false)
	private Integer activityAmount;
	//商品活动价格
	@TableField(exist=false)
	private Double activityPrice;
	//商品限购数量
	@TableField(exist=false)
	private Integer buyNum;
	//商品可用库存
	@TableField(exist=false)
	private Integer goodsAmount;
	//增减库存
	@TableField(exist=false)
	private Integer amount;
	//商品Id
	@TableField(exist=false)
	private Integer goodsId;
	@TableField(exist=false)
	private Integer actType;
	@TableField(exist=false)
	private String storeId;
	@TableField(exist=false)
	private String specName;
	@TableField(exist=false)
	private Integer brandId;
	
	public Integer getIsOverlay() {
		return isOverlay;
	}

	public void setIsOverlay(Integer isOverlay) {
		this.isOverlay = isOverlay;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Integer getActType() {
		return actType;
	}

	public void setActType(Integer actType) {
		this.actType = actType;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public Integer getActivityAmount() {
		return activityAmount;
	}

	public void setActivityAmount(Integer activityAmount) {
		this.activityAmount = activityAmount;
	}

	public Double getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(Double activityPrice) {
		this.activityPrice = activityPrice;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Date getActivityStartTime() {
		return activityStartTime;
	}

	public void setActivityStartTime(Date activityStartTime) {
		this.activityStartTime = activityStartTime;
	}

	public Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
