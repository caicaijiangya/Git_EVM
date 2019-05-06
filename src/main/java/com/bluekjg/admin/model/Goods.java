package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品主表
 * </p>
 *	SMALL_TYPE=0商品列表图，1商品轮播图，2商品详情图
 * @author Tom
 * @since 2018-09-25
 */
@TableName("t_evm_goods")
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
	private Integer userId;
	private String userName;
	private String goodsCode;
	private String storeFormat;
	private String newStoreId;
	private String[] newStoreIds;
    /**
     * 商品名称
     */
	@TableField(exist=false)
	private String goodsName;
	/**
	 * 商品图片
	 */
	@TableField(exist=false)
	private String goodsImage;
	

	/**
	 * 商品描述
	 */
	@TableField(exist=false)
	private String goodsDesc;
	/**
	 * 商品单价
	 */
	@TableField(exist=false)
	private Double goodsPrice;
	/**
	 * 市场价
	 */
	@TableField(exist=false)
	private Double marketPrice;
	/**
	 * 商品销量
	 */
	@TableField(exist=false)
	private Integer goodsSales;
	/**
	 * 商品总销量
	 */
	@TableField(exist=false)
	private Integer goodsTotalSales;
	/**
	 * 商品可用库存
	 */
	@TableField(exist=false)
	private Integer goodsAmount;
	/**
	 * 商品总剩余库存(累计库存)
	 */
	@TableField(exist=false)
	private Integer goodsRemAmount;
	/**
	 * 商品总库存（不做加减处理）
	 */
	@TableField(exist=false)
	private Integer goodsTotalAmount;
	/**
	 * 品牌
	 */
	@TableField(exist=false)
	private Integer brandId;
	@TableField(exist=false)
	private String brandName;
	/**
	 * 分类
	 */
	@TableField(exist=false)
	private Integer classifyId;
	@TableField(exist=false)
	private String classifyName;
	/**
	 * 状态（0待上架，1已上架）
	 */
	@TableField(exist=false)
	private Integer status;
	/**
	 * 商品永久二维码
	 */
	@TableField(exist=false)
	private String qrCode;
	@TableField(exist=false)
	private Integer isShow;
    /**
     * 是否删除（0否，1是）
     */
	@TableField(exist=false)
	private Integer isDel;
    /**
     * 创建日期
     */
	@TableField(exist=false)
	private String createdTime;
    /**
     * 修改日期
     */
	@TableField(exist=false)
	private String lastModifiedTime;
    /**
     * 备注
     */
	@TableField(exist=false)
	private String note;
	/**
	 * 增加库存
	 */
	@TableField(exist=false)
	private Integer addAmount;
	private String labels;
	private String classifys;
	private String specName;
	private String detailImg;
	private String advertImg;
	private String goodsVideo;
	//活动ID
	private Integer activityId;
	//选中的商品ids
	private String goodsIds;
	private Integer type;
	private Integer seq;
	private Integer storeId;
	private String storeName;
	private Integer size;
	private Integer integralGoodsAmount;
	private String sku; //商品编码
	private Integer clickNum; //点击次数
	private Integer clickManNum; //点击人数
	private String clickAverage; //人均点击次数
	private Integer collectNum; //收藏量
	private Integer cartGoodsNum; //加购数量
	private Integer saleNum;	//销售数量
	private Integer buyManNum;	//购买人数
	private String 	buyAverage;	//人均购买件数
	private Double salePrice;	//销售金额
	private String startTime;
	private String endTime;
	private Integer specId;	//商品规格Id
	private Integer storeSaleNum; //门店销售数量
	private Double	storeSalePrice;	//门店销售金额
	private String proportion;	//门店销售金额占比
	private Integer goodsId;	//商品ID
	private String format;
	private String gdId;
	private String[] gdIds;
	private String dateTime;
	private String[] goodsSpecIds;
	private String goodsSpecId;
	private Integer funsNum;
	private String clickPer;
	private String buyPer;
	private String dataParam;
	
	public String getDataParam() {
		return dataParam;
	}

	public void setDataParam(String dataParam) {
		this.dataParam = dataParam;
	}

	public String getClickPer() {
		return clickPer;
	}

	public void setClickPer(String clickPer) {
		this.clickPer = clickPer;
	}

	public String getBuyPer() {
		return buyPer;
	}

	public void setBuyPer(String buyPer) {
		this.buyPer = buyPer;
	}

	public Integer getFunsNum() {
		return funsNum;
	}

	public void setFunsNum(Integer funsNum) {
		this.funsNum = funsNum;
	}

	public String getStoreFormat() {
		return storeFormat;
	}

	public void setStoreFormat(String storeFormat) {
		this.storeFormat = storeFormat;
	}

	public String getNewStoreId() {
		return newStoreId;
	}

	public void setNewStoreId(String newStoreId) {
		this.newStoreId = newStoreId;
	}


	public String[] getNewStoreIds() {
		return newStoreIds;
	}

	public void setNewStoreIds(String[] newStoreIds) {
		this.newStoreIds = newStoreIds;
	}

	public String[] getGoodsSpecIds() {
		return goodsSpecIds;
	}

	public void setGoodsSpecIds(String[] goodsSpecIds) {
		this.goodsSpecIds = goodsSpecIds;
	}

	public String getGoodsSpecId() {
		return goodsSpecId;
	}

	public void setGoodsSpecId(String goodsSpecId) {
		this.goodsSpecId = goodsSpecId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String[] getGdIds() {
		return gdIds;
	}

	public void setGdIds(String[] gdIds) {
		this.gdIds = gdIds;
	}

	public String getGdId() {
		return gdId;
	}

	public void setGdId(String gdId) {
		this.gdId = gdId;
	}


	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public Integer getStoreSaleNum() {
		return storeSaleNum;
	}

	public void setStoreSaleNum(Integer storeSaleNum) {
		this.storeSaleNum = storeSaleNum;
	}

	public Double getStoreSalePrice() {
		return storeSalePrice;
	}

	public void setStoreSalePrice(Double storeSalePrice) {
		this.storeSalePrice = storeSalePrice;
	}

	public Integer getSpecId() {
		return specId;
	}

	public void setSpecId(Integer specId) {
		this.specId = specId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public String getBuyAverage() {
		return buyAverage;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getClickManNum() {
		return clickManNum;
	}

	public void setClickManNum(Integer clickManNum) {
		this.clickManNum = clickManNum;
	}

	public String getClickAverage() {
		return clickAverage;
	}

	public void setClickAverage(String clickAverage) {
		this.clickAverage = clickAverage;
	}

	public void setBuyAverage(String buyAverage) {
		this.buyAverage = buyAverage;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Integer getCartGoodsNum() {
		return cartGoodsNum;
	}

	public void setCartGoodsNum(Integer cartGoodsNum) {
		this.cartGoodsNum = cartGoodsNum;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Integer getBuyManNum() {
		return buyManNum;
	}

	public void setBuyManNum(Integer buyManNum) {
		this.buyManNum = buyManNum;
	}

	public String getClassifys() {
		return classifys;
	}

	public void setClassifys(String classifys) {
		this.classifys = classifys;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public Integer getIntegralGoodsAmount() {
		return integralGoodsAmount;
	}

	public void setIntegralGoodsAmount(Integer integralGoodsAmount) {
		this.integralGoodsAmount = integralGoodsAmount;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAddAmount() {
		return addAmount;
	}

	public void setAddAmount(Integer addAmount) {
		this.addAmount = addAmount;
	}

	public String getGoodsVideo() {
		return goodsVideo;
	}

	public void setGoodsVideo(String goodsVideo) {
		this.goodsVideo = goodsVideo;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}
	
	public String getDetailImg() {
		return detailImg;
	}

	public void setDetailImg(String detailImg) {
		this.detailImg = detailImg;
	}

	public String getAdvertImg() {
		return advertImg;
	}

	public void setAdvertImg(String advertImg) {
		this.advertImg = advertImg;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
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

	public Integer getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public Integer getGoodsRemAmount() {
		return goodsRemAmount;
	}

	public void setGoodsRemAmount(Integer goodsRemAmount) {
		this.goodsRemAmount = goodsRemAmount;
	}

	public Integer getGoodsTotalAmount() {
		return goodsTotalAmount;
	}

	public void setGoodsTotalAmount(Integer goodsTotalAmount) {
		this.goodsTotalAmount = goodsTotalAmount;
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

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	
}
