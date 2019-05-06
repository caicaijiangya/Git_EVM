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
 * 积分商品表
 * </p>
 *
 * @author Tim
 * @since 2018-10-07
 */
@TableName("t_evm_integral_goods")
public class IntegralGoods extends Model<IntegralGoods> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 根据类型判断是商品ID还是优惠券ID
     */
	@TableField("GOODS_ID")
	private Integer goodsId;
	@TableField(exist=false)
	private Integer couponId;
    /**
     * 商品总库存（不进行加减）
     */
	@TableField("GOODS_TOTAL_AMOUNT")
	private Integer goodsTotalAmount;
    /**
     * 积分价格
     */
	@TableField("GOODS_PRICE")
	private Double goodsPrice;
    /**
     * 商品库存
     */
	@TableField("GOODS_AMOUNT")
	private Integer goodsAmount;
	 /**
     * 每人限购数量（0不限购）
     */
	@TableField("BUY_NUM")
	private Integer buyNum;
    /**
     * 类型（0商品，1优惠券）
     */
	@TableField("TYPE")
	private Integer type;
    /**
     * 开始时间
     */
	@TableField("START_TIME")
	private Date startTime;
    /**
     * 结束时间
     */
	@TableField("END_TIME")
	private Date endTime;
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
	
	@TableField(exist=false)
	private String goodsName;
	@TableField(exist=false)
	private String filePath;
	@TableField(exist=false)
	private Integer amount;
	@TableField(exist=false)
	private String detailImg;
	@TableField(exist=false)
	private String advertImg;
	@TableField(exist=false)
	private String image;
	//商品可分配库存,商品可用库存
	@TableField(exist=false)
	private Integer goodsStock;
	@TableField(exist=false)
	private Integer oldGoodsId;
	

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Integer getOldGoodsId() {
		return oldGoodsId;
	}

	public void setOldGoodsId(Integer oldGoodsId) {
		this.oldGoodsId = oldGoodsId;
	}

	public Integer getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public Integer getGoodsTotalAmount() {
		return goodsTotalAmount;
	}

	public void setGoodsTotalAmount(Integer goodsTotalAmount) {
		this.goodsTotalAmount = goodsTotalAmount;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
