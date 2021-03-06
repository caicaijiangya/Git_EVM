package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 地区运费参照表
 * </p>
 *
 * @author Tom
 * @since 2018-11-8
 */
@TableName("t_evm_city_freight")
public class CityFreight extends Model<CityFreight> {

    private static final long serialVersionUID = 1L;
    
    @TableId(value="ID", type= IdType.AUTO)
	private Long id;

    /**
     * 省份
     */
    @TableField("PROVINCE")
	private Integer province;
    /**
     * 省份名称
     */
    @TableField(exist=false)
    private String provinceName;
    /**
     * 运费
     */
    @TableField("MONEY")
	private Double money;
    /**
     * 超重运费
     */
    @TableField("EXCEED_MONEY")
	private Double exceedMoney;
    /**
     * 是否删除（0未删除，1已删除）
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
	private String note;
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Integer getProvince() {
		return province;
	}



	public void setProvince(Integer province) {
		this.province = province;
	}



	public String getProvinceName() {
		return provinceName;
	}



	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}



	public Double getMoney() {
		return money;
	}



	public void setMoney(Double money) {
		this.money = money;
	}



	public Double getExceedMoney() {
		return exceedMoney;
	}



	public void setExceedMoney(Double exceedMoney) {
		this.exceedMoney = exceedMoney;
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
