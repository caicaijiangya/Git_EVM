package com.bluekjg.core.commons.invoice;

import java.util.Map;
import com.bluekjg.core.commons.utils.MapUtils;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

/**
 * 上海电子发票平台
 * 发票项目信息
 * @author Tom
 *
 */
public class FPKJXX_XMXX{
	private String XMMC;				//项目名称
	private String XMDW;				//项目单位
	private String GGXH;				//规格型号
	private String XMSL;				//项目数量 小数点后 8 位, 小 数点后都是 0 时， PDF 上只显示整数 
	private String HSBZ = "1";				//含税标志 表示项目单价和项 目金额是否含税。0 表示都不含税， 1 表 示都含税。
	private String FPHXZ = "0";				//发票行性质  0正常行 1折扣行 2被折扣行
	private String XMDJ;				//项目单价 小数点后 8 位小数 点后都是 0 时，PDF 上只显示2 位小数； 否则只显示至最后 一位不为0 的数字； （正票和红票单价 都大于‘0’） 
	private String SPBM;				//商品编码 技术人员需向企业 财务核实；不足 19 位后面补‘0’ 
	private String ZXBM;				//自行编码
	private String YHZCBS = "0";				//优惠政策标识 0：不使用，1：使 用 
	private String LSLBS;				//零税率标识 空：非零税率， 1： 免税，2：不征税， 3普通零税率 
	private String ZZSTSGL;				//增值税特殊管理 当YHZCBS为1时必 填（如：免税、不 征税） 
	private String KCE;					//扣除额  单位元，小数点 2 位小数 不能大于 不含税金额 说明 如下： 1.差额征税 的发票如果没有折 扣的话，只能允许 一条商品行。 2.具 体差额征税发票的 计算方法如下： 不 含税差额 = 不含 税金额 - 扣除额； 税额 = 不含税差 额*税率
	private String XMJE;				//项目金额  小数点后2位，以元 为单位精确到分。 等于=单价*数量， 根据含税标志，确 定此金额是否为含 税金额。 
	private String SL = WxappConfigUtil.SHDZFP_SL;					//税率 如果税率为 0，表示 免税
	private String SE;					//税额 小数点后 2 位，以 元为单位精确到分 
	public String getXMMC() {
		return XMMC;
	}
	public void setXMMC(String xMMC) {
		XMMC = xMMC;
	}
	public String getXMDW() {
		return XMDW;
	}
	public void setXMDW(String xMDW) {
		XMDW = xMDW;
	}
	public String getGGXH() {
		return GGXH;
	}
	public void setGGXH(String gGXH) {
		GGXH = gGXH;
	}
	public String getXMSL() {
		return XMSL;
	}
	public void setXMSL(String xMSL) {
		XMSL = xMSL;
	}
	public String getHSBZ() {
		return HSBZ;
	}
	public void setHSBZ(String hSBZ) {
		HSBZ = hSBZ;
	}
	public String getFPHXZ() {
		return FPHXZ;
	}
	public void setFPHXZ(String fPHXZ) {
		FPHXZ = fPHXZ;
	}
	public String getXMDJ() {
		return XMDJ;
	}
	public void setXMDJ(String xMDJ) {
		XMDJ = xMDJ;
	}
	public String getSPBM() {
		return SPBM;
	}
	public void setSPBM(String sPBM) {
		SPBM = sPBM;
	}
	public String getZXBM() {
		return ZXBM;
	}
	public void setZXBM(String zXBM) {
		ZXBM = zXBM;
	}
	public String getYHZCBS() {
		return YHZCBS;
	}
	public void setYHZCBS(String yHZCBS) {
		YHZCBS = yHZCBS;
	}
	public String getLSLBS() {
		return LSLBS;
	}
	public void setLSLBS(String lSLBS) {
		LSLBS = lSLBS;
	}
	public String getZZSTSGL() {
		return ZZSTSGL;
	}
	public void setZZSTSGL(String zZSTSGL) {
		ZZSTSGL = zZSTSGL;
	}
	public String getKCE() {
		return KCE;
	}
	public void setKCE(String kCE) {
		KCE = kCE;
	}
	public String getXMJE() {
		return XMJE;
	}
	public void setXMJE(String xMJE) {
		XMJE = xMJE;
	}
	public String getSL() {
		return SL;
	}
	public void setSL(String sL) {
		SL = sL;
	}
	public String getSE() {
		return SE;
	}
	public void setSE(String sE) {
		SE = sE;
	}
	public String toXmlString() {
		String className = this.getClass().getSimpleName();
		Map<String, Object> resmap = MapUtils.object2Map(this);
		StringBuffer xml = new StringBuffer(); 
		xml.append("<"+className+">");
		xml.append(InvoiceUtil.requesttoxml(resmap,false));
		xml.append("</"+className+">");
		return xml.toString();
	}
}
