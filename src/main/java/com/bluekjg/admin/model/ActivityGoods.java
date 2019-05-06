package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 活动商品表
 * </p>
 *
 * @author Tim
 * @since 2018-09-29
 */
@TableName("t_evm_activity_goods")
public class ActivityGoods extends Model<ActivityGoods> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 活动ID
     */
	@TableField("ACTIVITY_ID")
	private Integer activityId;
    /**
     * 商品ID
     */
	@TableField("GOODS_ID")
	private Integer goodsId;
    /**
     * 活动商品库存，不大于商品库存
     */
	@TableField("ACTIVITY_AMOUNT")
	private Integer activityAmount;
    /**
     * 活动价格
     */
	@TableField("ACTIVITY_PRICE")
	private Double activityPrice;
    /**
     * 限购数量（0不限）
     */
	@TableField("BUY_NUM")
	private Integer buyNum;
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

	//选中的ID
	@TableField(exist=false)
	private String ids;
	//增减库存
	@TableField(exist=false)
	private Integer amount;
	//商品可用库存
	@TableField(exist=false)
	private Integer goodsAmount;
	//商品总剩余库存
	@TableField(exist=false)
	private Integer goodsRemAmount;
	//活动类型
	@TableField(exist=false)
	private Integer activityType;
	//商品详情图
	@TableField(exist=false)
	private String detailImg;
	//商品轮播图
	@TableField(exist=false)
	private String advertImg;
	//商品缩略图
	@TableField(exist=false)
	private String image;
	@TableField(exist=false)
	private String listImg;
	//根据活动类型得到的活动商品图片类型
	@TableField(exist=false)
	private Integer actType;
	
	@TableField(exist=false)
	private String filePath;
	@TableField(exist=false)
	private Integer fileType;
	@TableField(exist=false)
	private Integer bigType;
	@TableField(exist=false)
	private String smallType;
	@TableField(exist=false)
	private String activityAmounts;
	@TableField(exist=false)
	private List<Integer> amountList;
	@TableField(exist=false)
	private List<Integer> idList;
	@TableField(exist=false)
	private Integer storeId;
	@TableField(exist=false)
	private Integer isFreeFreight; 
	
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

	public List<Integer> getAmountList() {
		return amountList;
	}

	public void setAmountList(List<Integer> amountList) {
		this.amountList = amountList;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public String getActivityAmounts() {
		return activityAmounts;
	}

	public void setActivityAmounts(String activityAmounts) {
		this.activityAmounts = activityAmounts;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Integer getBigType() {
		return bigType;
	}

	public void setBigType(Integer bigType) {
		this.bigType = bigType;
	}

	public String getSmallType() {
		return smallType;
	}

	public void setSmallType(String smallType) {
		this.smallType = smallType;
	}

	public Integer getActType() {
		return actType;
	}

	public void setActType(Integer actType) {
		this.actType = actType;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Integer getGoodsRemAmount() {
		return goodsRemAmount;
	}

	public void setGoodsRemAmount(Integer goodsRemAmount) {
		this.goodsRemAmount = goodsRemAmount;
	}

	public Integer getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getListImg() {
		return listImg;
	}

	public void setListImg(String listImg) {
		this.listImg = listImg;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
