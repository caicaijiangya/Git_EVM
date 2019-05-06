package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：活动商品表实体类
 * @author：pincui.tom
 * @date：2018/9/26 14:51
 */
@TableName("t_evm_activity_goods")
public class WxActivityGoods implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 活动ID
	 */
	private Integer id;
	/**
	 * 商品ID
	 */
	private Integer goodsId;
	/**
	 * 活动商品ID
	 */
	private Integer activityGoodsId;
	/**
	 * 活动商品图片
	 */
	private String goodsImages;
	/**
	 * 活动标题
	 */
	private String title;
	/**
	 * 活动类型：0拼团，1秒杀，2特价
	 */
	private Integer activityType;
	/**
	 * 活动剩余时间
	 */
	private String activityTime;
	/**
	 * 活动开始时间
	 */
	private String activityStartTime;
	/**
	 * 活动结束时间
	 */
	private String activityEndTime;
	
	/**
	 * 活动库存
	 */
	private Integer activityAmount;
	/**
	 * 活动价格
	 */
	private Double activityPrice;
	/**
	 * 限购数量（0不限）
	 */
	private Integer buyNum;
	/**
	 * 状态（0待审核，1已开始，2已结束）
	 */
	private Integer status;
	
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	
	//砍价信息
	private WxActivityGoodsBargain bargain;
	//拼团信息
	private List<WxActivityGoodsCollage> collages;
	
	private Double goodsPrice;
	//是否免运费（0否，1是）
	private Integer isFreeFreight;
	private Integer storeId;
	private String storeName;
	
	private Integer buyUserNum;//已购数量
	private Integer collUserNum;//已发团数量
	
	public List<WxActivityGoodsCollage> getCollages() {
		return collages;
	}
	public void setCollages(List<WxActivityGoodsCollage> collages) {
		this.collages = collages;
	}
	public Integer getIsFreeFreight() {
		return isFreeFreight;
	}
	public void setIsFreeFreight(Integer isFreeFreight) {
		this.isFreeFreight = isFreeFreight;
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
	public Integer getBuyUserNum() {
		return buyUserNum;
	}
	public void setBuyUserNum(Integer buyUserNum) {
		this.buyUserNum = buyUserNum;
	}
	public Integer getCollUserNum() {
		return collUserNum;
	}
	public void setCollUserNum(Integer collUserNum) {
		this.collUserNum = collUserNum;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public WxActivityGoodsBargain getBargain() {
		return bargain;
	}
	public void setBargain(WxActivityGoodsBargain bargain) {
		this.bargain = bargain;
	}
	public String getGoodsImages() {
		return goodsImages;
	}
	public void setGoodsImages(String goodsImages) {
		this.goodsImages = goodsImages;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getActivityGoodsId() {
		return activityGoodsId;
	}
	public void setActivityGoodsId(Integer activityGoodsId) {
		this.activityGoodsId = activityGoodsId;
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
	
	public String getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}
	public String getActivityStartTime() {
		return activityStartTime;
	}
	public void setActivityStartTime(String activityStartTime) {
		this.activityStartTime = activityStartTime;
	}
	public String getActivityEndTime() {
		return activityEndTime;
	}
	public void setActivityEndTime(String activityEndTime) {
		this.activityEndTime = activityEndTime;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
