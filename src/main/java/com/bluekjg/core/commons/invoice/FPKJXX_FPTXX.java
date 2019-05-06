package com.bluekjg.core.commons.invoice;

import java.util.Map;
import com.bluekjg.core.commons.utils.MapUtils;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

/**
 * 上海电子发票平台
 * 发票头信息
 * @author Tom
 *
 */
public class FPKJXX_FPTXX{
	private String FPQQLSH;				//发票请求唯一流 水号 
	private String DSPTBM = WxappConfigUtil.SHDZFP_REQUEST_CODE;				//平台编码
	private String NSRSBH = WxappConfigUtil.SHDZFP_TAXPAYER_ID;				//开票方识别号
	private String NSRMC;				//开票方名称
	private String FJH;					//分机号
	private String NSRDZDAH;			//开票方电子档案 号
	private String SWJG_DM;				//税务机构代码
	private String DKBZ = "0";				//代开标志1、 自开(0)  2、 代开(1)默认为自开
	private String SGBZ;				//收购标志1、收购票（Y）代开标志为1 时，不能填Y 2、非收购票： 此字段为空 3、收购票扣除 额必须为空 4、收购票税率 必须为0 5、成品油发票 必须为C（大 写） 6.成品油票不 支持代开
	private String PYDM;				//票样代码
	private String KPXM;				//主要开票商品，或 者第一条商品，取 项目信息中第一条 数据的项目名称 （或传递大类例 如：办公用品）
	private String BMB_BBH = WxappConfigUtil.SHDZFP_BMB_BBH;				//编码表版本号，保 持最新的版本 号，目前26.0 
	private String XHF_NSRSBH = WxappConfigUtil.SHDZFP_TAXPAYER_ID;			//必填，如果是企业 自营开具发票，填 写第3项中的开票 方识别号，如果是 商家驻店开具发 票，填写商家的纳 税人识别
	private String XHFMC;				//销方名称 必填，纳税人名称
	private String XHF_DZ;				//销方地址
	private String XHF_DH;				//销方电话
	private String XHF_YHZH;			//销方银行、账号
	private String GHFMC;				//购货方名称，即发 票抬头
	private String GHF_NSRSBH;			//购货方识别号 
	private String GHF_DZ;				//购货方地址
	private String GHF_SF;				//购货方省份
	private String GHF_GDDH;			//购货方固定电话
	private String GHF_SJ;				//购货方手机
	private String GHF_EMAIL;			//购货方邮箱
	private String GHFQYLX;				//购货方企业类型01：企业  02：机关事业单位 03：个人  04：其它
	private String GHF_YHZH;			//购货方银行、账号
	private String HY_DM;				//行业代码
	private String HY_MC;				//行业名称
	private String KPY;					//开票员
	private String SKY;					//收款员
	private String FHR;					//复核人
	private String KPRQ;				//开票日期
	private String KPLX = "1";				//开票类型1 正票 2红票
	private String YFP_DM;				//原发票代码 如果CZDM不是10 或KPLX为红票时候 都是必录
	private String YFP_HM;				//原发票号码 如果CZDM不是10 或KPLX为红票时候 都是必录
	private String TSCHBZ;				//特殊冲红标志 0正常冲红(电子发 票) 1特殊冲红(冲红 纸质等) 
	private String CZDM = "10";				//操作代码 10正票正常开具 20退货折让红票 
	private String QD_BZ = "0";				//清单标志 
	private String QDXMMC;				//清单发票项目名称
	private String CHYY;				//冲红原因 
	private String KPHJJE;				//价税合计金额 小数点后 2 位，以 元为单位精确到分 
	private String HJBHSJE;				//合计不含税金额。 所有商品行不含 税金额之和 小数点后2位，以元 为单位精确到分 （单行商品金额之 和）。平台处理价 税分离，此值传0 
	private String HJSE;				//合计税额。所有商 品行税额之和  小数点后2位，以元 为单位精确到分(单 行商品税额之和)， 平台处理价税分 离，此值传0 
	private String BZ;					//备注
	public String getFPQQLSH() {
		return FPQQLSH;
	}
	public void setFPQQLSH(String fPQQLSH) {
		FPQQLSH = fPQQLSH;
	}
	public String getDSPTBM() {
		return DSPTBM;
	}
	public void setDSPTBM(String dSPTBM) {
		DSPTBM = dSPTBM;
	}
	public String getNSRSBH() {
		return NSRSBH;
	}
	public void setNSRSBH(String nSRSBH) {
		NSRSBH = nSRSBH;
	}
	public String getNSRMC() {
		return NSRMC;
	}
	public void setNSRMC(String nSRMC) {
		NSRMC = nSRMC;
	}
	public String getFJH() {
		return FJH;
	}
	public void setFJH(String fJH) {
		FJH = fJH;
	}
	public String getNSRDZDAH() {
		return NSRDZDAH;
	}
	public void setNSRDZDAH(String nSRDZDAH) {
		NSRDZDAH = nSRDZDAH;
	}
	public String getSWJG_DM() {
		return SWJG_DM;
	}
	public void setSWJG_DM(String sWJG_DM) {
		SWJG_DM = sWJG_DM;
	}
	public String getDKBZ() {
		return DKBZ;
	}
	public void setDKBZ(String dKBZ) {
		DKBZ = dKBZ;
	}
	public String getSGBZ() {
		return SGBZ;
	}
	public void setSGBZ(String sGBZ) {
		SGBZ = sGBZ;
	}
	public String getPYDM() {
		return PYDM;
	}
	public void setPYDM(String pYDM) {
		PYDM = pYDM;
	}
	public String getKPXM() {
		return KPXM;
	}
	public void setKPXM(String kPXM) {
		KPXM = kPXM;
	}
	public String getBMB_BBH() {
		return BMB_BBH;
	}
	public void setBMB_BBH(String bMB_BBH) {
		BMB_BBH = bMB_BBH;
	}
	public String getXHF_NSRSBH() {
		return XHF_NSRSBH;
	}
	public void setXHF_NSRSBH(String xHF_NSRSBH) {
		XHF_NSRSBH = xHF_NSRSBH;
	}
	public String getXHFMC() {
		return XHFMC;
	}
	public void setXHFMC(String xHFMC) {
		XHFMC = xHFMC;
	}
	public String getXHF_DZ() {
		return XHF_DZ;
	}
	public void setXHF_DZ(String xHF_DZ) {
		XHF_DZ = xHF_DZ;
	}
	public String getXHF_DH() {
		return XHF_DH;
	}
	public void setXHF_DH(String xHF_DH) {
		XHF_DH = xHF_DH;
	}
	public String getXHF_YHZH() {
		return XHF_YHZH;
	}
	public void setXHF_YHZH(String xHF_YHZH) {
		XHF_YHZH = xHF_YHZH;
	}
	public String getGHFMC() {
		return GHFMC;
	}
	public void setGHFMC(String gHFMC) {
		GHFMC = gHFMC;
	}
	public String getGHF_NSRSBH() {
		return GHF_NSRSBH;
	}
	public void setGHF_NSRSBH(String gHF_NSRSBH) {
		GHF_NSRSBH = gHF_NSRSBH;
	}
	public String getGHF_DZ() {
		return GHF_DZ;
	}
	public void setGHF_DZ(String gHF_DZ) {
		GHF_DZ = gHF_DZ;
	}
	public String getGHF_SF() {
		return GHF_SF;
	}
	public void setGHF_SF(String gHF_SF) {
		GHF_SF = gHF_SF;
	}
	public String getGHF_GDDH() {
		return GHF_GDDH;
	}
	public void setGHF_GDDH(String gHF_GDDH) {
		GHF_GDDH = gHF_GDDH;
	}
	public String getGHF_SJ() {
		return GHF_SJ;
	}
	public void setGHF_SJ(String gHF_SJ) {
		GHF_SJ = gHF_SJ;
	}
	public String getGHF_EMAIL() {
		return GHF_EMAIL;
	}
	public void setGHF_EMAIL(String gHF_EMAIL) {
		GHF_EMAIL = gHF_EMAIL;
	}
	public String getGHFQYLX() {
		return GHFQYLX;
	}
	public void setGHFQYLX(String gHFQYLX) {
		GHFQYLX = gHFQYLX;
	}
	public String getGHF_YHZH() {
		return GHF_YHZH;
	}
	public void setGHF_YHZH(String gHF_YHZH) {
		GHF_YHZH = gHF_YHZH;
	}
	public String getHY_DM() {
		return HY_DM;
	}
	public void setHY_DM(String hY_DM) {
		HY_DM = hY_DM;
	}
	public String getHY_MC() {
		return HY_MC;
	}
	public void setHY_MC(String hY_MC) {
		HY_MC = hY_MC;
	}
	public String getKPY() {
		return KPY;
	}
	public void setKPY(String kPY) {
		KPY = kPY;
	}
	public String getSKY() {
		return SKY;
	}
	public void setSKY(String sKY) {
		SKY = sKY;
	}
	public String getFHR() {
		return FHR;
	}
	public void setFHR(String fHR) {
		FHR = fHR;
	}
	public String getKPRQ() {
		return KPRQ;
	}
	public void setKPRQ(String kPRQ) {
		KPRQ = kPRQ;
	}
	public String getKPLX() {
		return KPLX;
	}
	public void setKPLX(String kPLX) {
		KPLX = kPLX;
	}
	public String getYFP_DM() {
		return YFP_DM;
	}
	public void setYFP_DM(String yFP_DM) {
		YFP_DM = yFP_DM;
	}
	public String getYFP_HM() {
		return YFP_HM;
	}
	public void setYFP_HM(String yFP_HM) {
		YFP_HM = yFP_HM;
	}
	public String getTSCHBZ() {
		return TSCHBZ;
	}
	public void setTSCHBZ(String tSCHBZ) {
		TSCHBZ = tSCHBZ;
	}
	public String getCZDM() {
		return CZDM;
	}
	public void setCZDM(String cZDM) {
		CZDM = cZDM;
	}
	public String getQD_BZ() {
		return QD_BZ;
	}
	public void setQD_BZ(String qD_BZ) {
		QD_BZ = qD_BZ;
	}
	public String getQDXMMC() {
		return QDXMMC;
	}
	public void setQDXMMC(String qDXMMC) {
		QDXMMC = qDXMMC;
	}
	public String getCHYY() {
		return CHYY;
	}
	public void setCHYY(String cHYY) {
		CHYY = cHYY;
	}
	public String getKPHJJE() {
		return KPHJJE;
	}
	public void setKPHJJE(String kPHJJE) {
		KPHJJE = kPHJJE;
	}
	public String getHJBHSJE() {
		return HJBHSJE;
	}
	public void setHJBHSJE(String hJBHSJE) {
		HJBHSJE = hJBHSJE;
	}
	public String getHJSE() {
		return HJSE;
	}
	public void setHJSE(String hJSE) {
		HJSE = hJSE;
	}
	public String getBZ() {
		return BZ;
	}
	public void setBZ(String bZ) {
		BZ = bZ;
	}
	public String toXmlString() {
		String className = this.getClass().getSimpleName();
		Map<String, Object> resmap = MapUtils.object2Map(this);
		StringBuffer xml = new StringBuffer(); 
		xml.append("<"+className+" class=\""+className+"\">");
		xml.append(InvoiceUtil.requesttoxml(resmap,false));
		xml.append("</"+className+">");
		return xml.toString();
	}
}
