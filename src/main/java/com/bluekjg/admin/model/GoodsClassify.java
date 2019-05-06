package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品分类主表
 * </p>
 * @author Tom
 * @since 2018-09-25
 */
@TableName("t_evm_goods_classify")
public class GoodsClassify extends Model<GoodsClassify> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
	private Integer pid;
	private Integer goodsId;
	private Integer seq;
	private Integer type;
	private Integer size;
	private String code;
	private String goodsName;
	
    public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
     * 商品名称
     */
	@TableField(exist=false)
	private String name;
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

	//品牌名
	@TableField(exist=false)
	private String brandName;
	//品牌排序
	@TableField(exist=false)
	private Integer brandSeq;
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getBrandSeq() {
		return brandSeq;
	}

	public void setBrandSeq(Integer brandSeq) {
		this.brandSeq = brandSeq;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
