package com.bluekjg.core.commons.invoice;

import java.util.Map;

import com.bluekjg.core.commons.utils.MapUtils;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

/**
 * 上海电子发票平台
 * 发票信息获取
 * @author Tom
 *
 */
public class REQUEST_FPXXXZ_NEW {
	private String DDH;							//订单号 
	private String FPQQLSH;						//发票请求唯一流水号 
	private String DSPTBM = WxappConfigUtil.SHDZFP_REQUEST_CODE;			//平台编码
	private String NSRSBH =  WxappConfigUtil.SHDZFP_TAXPAYER_ID;	//开票方识别号
	private String PDF_XZFS;				//PDF下载方式 0 - 发票开具状态查询； 1 -  PDF文件（PDF_FILE）
	public String getDDH() {
		return DDH;
	}
	public void setDDH(String dDH) {
		DDH = dDH;
	}
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
	public String getPDF_XZFS() {
		return PDF_XZFS;
	}
	public void setPDF_XZFS(String pDF_XZFS) {
		PDF_XZFS = pDF_XZFS;
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
