package com.bluekjg.admin.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("t_evm_user_portrait")
public class UserPortrait extends Model<UserPortrait> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6696156377599691894L;
	
	@TableId(type=IdType.AUTO)
	private Integer id;
	@JsonIgnore
	@TableField("ref_date")
	private String refDate;//日期，格式为 yyyymmdd
	private String city;
	private Integer num;
	@TableField(exist=false)
	private Integer sum;

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRefDate() {
		return refDate;
	}

	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
