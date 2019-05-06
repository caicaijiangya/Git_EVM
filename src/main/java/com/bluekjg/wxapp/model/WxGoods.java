package com.bluekjg.wxapp.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @description：商品表实体类
 * @author：pincui.tom
 * @date：2018/3/26 14:51
 */
@TableName("t_evm_goods")
public class WxGoods implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer rid;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品描述
	 */
	private String goodsDesc;
	/**
	 * 商品缩略图
	 */
	private String goodsImages;
	/**
	 * 商品视频
	 */
	private String goodsVideo;
	/**
	 * 商品价格
	 */
	private Double goodsPrice;
	/**
	 * 商品正价、原始价
	 */
	private Double originalPrice;
	/**
	 * 商品销量
	 */
	private Integer goodsSales;
	/**
	 * 商品总销量
	 */
	private Integer goodsTotalSales;
	/**
	 * 商品库存
	 */
	private Integer goodsAmount;
	/**
	 * 状态（0待上架，1已上架）
	 */
	private Integer status;
	/**
	 * 品牌
	 */
	private Integer brandId;
	private String brandName;
	/**
	 * 分类
	 */
	private Integer classifyId;
	private String classifyName;
	//是否删除（0未删除，1已删除）
	private Integer isDel;
	//创建日期
	private String createTime;
	//修改日期
	private String LastModifiedTime;
	//备注
	private String note;
	//优惠券数量
	private Integer isCoupons;
	//商品详情图
	private List<WxStaticFile> detailImgs;
	//商品轮播图
	private List<WxStaticFile> advertImgs;
	//商品标签
	private List<WxGoodsLabel> labels;
	//商品规格
	private List<WxGoodsSpec> specs;
	//商品活动
	private WxActivityGoods activity;
	private WxActivityGoods activityLadder;
	private Integer goodsNum;
	private Integer specId;
	private String specName;
	private boolean isBuy;//是否允许购买
	private String openId;
	
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Integer getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	public WxActivityGoods getActivityLadder() {
		return activityLadder;
	}
	public void setActivityLadder(WxActivityGoods activityLadder) {
		this.activityLadder = activityLadder;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public boolean isBuy() {
		return isBuy;
	}
	public void setBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getGoodsImages() {
		return goodsImages;
	}
	public void setGoodsImages(String goodsImages) {
		this.goodsImages = goodsImages;
	}
	public String getGoodsVideo() {
		return goodsVideo;
	}
	public void setGoodsVideo(String goodsVideo) {
		this.goodsVideo = goodsVideo;
	}
	public Integer getGoodsSales() {
		return goodsSales;
	}
	public void setGoodsSales(Integer goodsSales) {
		this.goodsSales = goodsSales;
	}
	public Integer getGoodsTotalSales() {
		return goodsTotalSales;
	}
	public void setGoodsTotalSales(Integer goodsTotalSales) {
		this.goodsTotalSales = goodsTotalSales;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Integer getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
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
	public List<WxStaticFile> getDetailImgs() {
		return detailImgs;
	}
	public void setDetailImgs(List<WxStaticFile> detailImgs) {
		this.detailImgs = detailImgs;
	}
	public List<WxStaticFile> getAdvertImgs() {
		return advertImgs;
	}
	public void setAdvertImgs(List<WxStaticFile> advertImgs) {
		this.advertImgs = advertImgs;
	}
	public List<WxGoodsLabel> getLabels() {
		return labels;
	}
	public void setLabels(List<WxGoodsLabel> labels) {
		this.labels = labels;
	}
	public List<WxGoodsSpec> getSpecs() {
		return specs;
	}
	public void setSpecs(List<WxGoodsSpec> specs) {
		this.specs = specs;
	}
	public WxActivityGoods getActivity() {
		return activity;
	}
	public void setActivity(WxActivityGoods activity) {
		this.activity = activity;
	}
	public Integer getIsCoupons() {
		return isCoupons;
	}
	public void setIsCoupons(Integer isCoupons) {
		this.isCoupons = isCoupons;
	}
	
	
}
