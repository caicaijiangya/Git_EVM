package com.bluekjg.admin.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("t_evm_analytics_page")
public class AnalyticsPage extends Model<AnalyticsPage> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8895364504653258442L;
	
	@TableId(type=IdType.AUTO)
	private Integer id;
	@JsonProperty("ref_date")
	@TableField("ref_date")
	private String refDate;
	@JsonProperty("page_path")
	@TableField("page_path")
	private String pagePath;
	@JsonProperty("page_visit_pv")
	@TableField("page_visit_pv")
	private Integer pageVisitPv;
	@JsonProperty("page_visit_uv")
	@TableField("page_visit_uv")
	private Integer pageVisitUv;
	@JsonProperty("page_staytime_pv")
	@TableField("page_staytime_pv")
	private Integer pageStaytimePv;
	@JsonProperty("entrypage_pv")
	@TableField("entrypage_pv")
	private Integer entrypagePv;
	@JsonProperty("exitpage_pv")
	@TableField("exitpage_pv")
	private Integer exitpagePv;
	@JsonProperty("page_share_pv")
	@TableField("page_share_pv")
	private Integer pageSharePv;
	@JsonProperty("page_share_uv")
	@TableField("page_share_uv")
	private Integer pageShareUv;
	
	public Integer getPageVisitUv() {
		return pageVisitUv;
	}

	public void setPageVisitUv(Integer pageVisitUv) {
		this.pageVisitUv = pageVisitUv;
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

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public Integer getPageVisitPv() {
		return pageVisitPv;
	}

	public void setPageVisitPv(Integer pageVisitPv) {
		this.pageVisitPv = pageVisitPv;
	}

	public Integer getPageStaytimePv() {
		return pageStaytimePv;
	}

	public void setPageStaytimePv(Integer pageStaytimePv) {
		this.pageStaytimePv = pageStaytimePv;
	}

	public Integer getEntrypagePv() {
		return entrypagePv;
	}

	public void setEntrypagePv(Integer entrypagePv) {
		this.entrypagePv = entrypagePv;
	}

	public Integer getExitpagePv() {
		return exitpagePv;
	}

	public void setExitpagePv(Integer exitpagePv) {
		this.exitpagePv = exitpagePv;
	}

	public Integer getPageSharePv() {
		return pageSharePv;
	}

	public void setPageSharePv(Integer pageSharePv) {
		this.pageSharePv = pageSharePv;
	}

	public Integer getPageShareUv() {
		return pageShareUv;
	}

	public void setPageShareUv(Integer pageShareUv) {
		this.pageShareUv = pageShareUv;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
