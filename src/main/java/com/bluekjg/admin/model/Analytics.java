package com.bluekjg.admin.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("t_evm_analytics")
public class Analytics extends Model<Analytics>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4961379510203265545L;
	
	/**
	 * getAnalysisDailyVisitTrend
	 */
	@JsonProperty("ref_date")
	@TableId(value="ref_date",type=IdType.INPUT)
	private String refDate;//日期，格式为 yyyymmdd
	@JsonProperty("session_cnt")
	@TableField("session_cnt")
	private Integer sessionCnt;//打开次数
	@JsonProperty("visit_pv")
	@TableField("visit_pv")
	private Integer visitPv;//访问次数
	@JsonProperty("visit_uv")
	@TableField("visit_uv")
	private Integer visitUv;//访问人数
	@JsonProperty("visit_uv_new")
	@TableField("visit_uv_new")
	private Integer visitUvNew;//新用户数
	@JsonProperty("stay_time_uv")
	@TableField("stay_time_uv")
	private Double stayTimeUv;//人均停留时长 (浮点型，单位：秒)
	@JsonProperty("stay_time_session")
	@TableField("stay_time_session")
	private Double stayTimeSession;//次均停留时长 (浮点型，单位：秒)
	@JsonProperty("visit_depth")
	@TableField("visit_depth")
	private Double visitDepth;//平均访问深度 (浮点型)
	
	/**
	 * getAnalysisDailySummary
	 */
	@TableField("visit_total")
	@JsonProperty("visit_total")
	private Integer visitTotal;//累计用户数
	@TableField("share_pv")
	@JsonProperty("share_pv")
	private Integer sharePv;//转发次数
	@TableField("share_uv")
	@JsonProperty("share_uv")
	private Integer shareUv;//转发人数
	
	/**
	 * getAnalysisVisitDistribution 中场景值
	 */
	private Integer search;//2.搜索(搜索引擎)
	private Integer session;//3.会话(直接访问)
	private Integer share;//14.APP分享(分享)
	private Integer other;//10.其他(其他)
	
	/**
	 * 查询字段
	 */
	@TableField(exist=false)
	private String startDate;
	@TableField(exist=false)
	private String endDate;
	@TableField(exist=false)
	private String format;
	/**
	 * UserPortrait 字段
	 */
	@TableField(exist=false)
	private String city;
	/**
	 * top 虚拟字段
	 */
	@TableField(exist=false)
	private String top0;
	@TableField(exist=false)
	private String top1;
	@TableField(exist=false)
	private String top2;
	@TableField(exist=false)
	private String top3;
	@TableField(exist=false)
	private String top4;
	
	public String getTop0() {
		return top0;
	}

	public void setTop0(String top0) {
		this.top0 = top0;
	}

	public String getTop1() {
		return top1;
	}

	public void setTop1(String top1) {
		this.top1 = top1;
	}

	public String getTop2() {
		return top2;
	}

	public void setTop2(String top2) {
		this.top2 = top2;
	}

	public String getTop3() {
		return top3;
	}

	public void setTop3(String top3) {
		this.top3 = top3;
	}

	public String getTop4() {
		return top4;
	}

	public void setTop4(String top4) {
		this.top4 = top4;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getVisitTotal() {
		return visitTotal;
	}

	public Integer getSearch() {
		return search;
	}

	public void setSearch(Integer search) {
		this.search = search;
	}

	public Integer getSession() {
		return session;
	}

	public void setSession(Integer session) {
		this.session = session;
	}

	public Integer getShare() {
		return share;
	}

	public void setShare(Integer share) {
		this.share = share;
	}

	public Integer getOther() {
		return other;
	}

	public void setOther(Integer other) {
		this.other = other;
	}

	public void setVisitTotal(Integer visitTotal) {
		this.visitTotal = visitTotal;
	}

	public Integer getSharePv() {
		return sharePv;
	}

	public void setSharePv(Integer sharePv) {
		this.sharePv = sharePv;
	}

	public Integer getShareUv() {
		return shareUv;
	}

	public void setShareUv(Integer shareUv) {
		this.shareUv = shareUv;
	}

	public String getRefDate() {
		return refDate;
	}

	public void setRefDate(String refDate) {
		this.refDate = refDate;
	}

	public Integer getSessionCnt() {
		return sessionCnt;
	}

	public void setSessionCnt(Integer sessionCnt) {
		this.sessionCnt = sessionCnt;
	}

	public Integer getVisitPv() {
		return visitPv;
	}

	public void setVisitPv(Integer visitPv) {
		this.visitPv = visitPv;
	}

	public Integer getVisitUv() {
		return visitUv;
	}

	public void setVisitUv(Integer visitUv) {
		this.visitUv = visitUv;
	}

	public Integer getVisitUvNew() {
		return visitUvNew;
	}

	public void setVisitUvNew(Integer visitUvNew) {
		this.visitUvNew = visitUvNew;
	}

	public Double getStayTimeUv() {
		return stayTimeUv;
	}

	public void setStayTimeUv(Double stayTimeUv) {
		this.stayTimeUv = stayTimeUv;
	}

	public Double getStayTimeSession() {
		return stayTimeSession;
	}

	public void setStayTimeSession(Double stayTimeSession) {
		this.stayTimeSession = stayTimeSession;
	}

	public Double getVisitDepth() {
		return visitDepth;
	}

	public void setVisitDepth(Double visitDepth) {
		this.visitDepth = visitDepth;
	}

	@Override
	protected Serializable pkVal() {
		return this.refDate;
	}
}
