package com.bluekjg.wxapp.model;

import java.util.List;

import com.bluekjg.core.commons.invoice.FPKJXX_DDXX;
import com.bluekjg.core.commons.invoice.FPKJXX_FPTXX;
import com.bluekjg.core.commons.invoice.FPKJXX_XMXX;
import com.bluekjg.core.commons.invoice.GlobalInfo;
import com.bluekjg.core.commons.invoice.REQUEST_FPXXXZ_NEW;

/**
 * <p>
 * 	电子发票实体类
 * </p>
 *
 * @author Tom
 * @since 2019-04-04
 */
public class WxInvoice {
	private static final long serialVersionUID = 1L;
	private Integer orderId;//订单号
	private String ghfqylx;//01：企业  02：机关事业单位 03：个人  04：其它
	private String ghfmc;//发票抬头
	private String ghfNsrsbh;//纳税人识别号
	private String fpqqlsh;//流水号
	private String fileUrl;//发票地址
	private String sendMessage;//发送报文
	private String sendReturnMessage;//发送返回报文
	private String downloadMessage;//下载报文
	private String downloadReturnMessage;//下载返回报文
	private Integer status;//发票开具状态（1开票处理中，2开票完成）
	private GlobalInfo globalInfo;
	private FPKJXX_FPTXX fptxx;
	private FPKJXX_DDXX ddxx;
	private List<FPKJXX_XMXX> xmxxs;
	private REQUEST_FPXXXZ_NEW fpxxxzNew;
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getGhfqylx() {
		return ghfqylx;
	}
	public void setGhfqylx(String ghfqylx) {
		this.ghfqylx = ghfqylx;
	}
	public String getGhfmc() {
		return ghfmc;
	}
	public void setGhfmc(String ghfmc) {
		this.ghfmc = ghfmc;
	}
	public String getGhfNsrsbh() {
		return ghfNsrsbh;
	}
	public void setGhfNsrsbh(String ghfNsrsbh) {
		this.ghfNsrsbh = ghfNsrsbh;
	}
	public String getFpqqlsh() {
		return fpqqlsh;
	}
	public void setFpqqlsh(String fpqqlsh) {
		this.fpqqlsh = fpqqlsh;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getSendMessage() {
		return sendMessage;
	}
	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}
	public String getSendReturnMessage() {
		return sendReturnMessage;
	}
	public void setSendReturnMessage(String sendReturnMessage) {
		this.sendReturnMessage = sendReturnMessage;
	}
	public String getDownloadMessage() {
		return downloadMessage;
	}
	public void setDownloadMessage(String downloadMessage) {
		this.downloadMessage = downloadMessage;
	}
	public String getDownloadReturnMessage() {
		return downloadReturnMessage;
	}
	public void setDownloadReturnMessage(String downloadReturnMessage) {
		this.downloadReturnMessage = downloadReturnMessage;
	}
	public FPKJXX_FPTXX getFptxx() {
		return fptxx;
	}
	public void setFptxx(FPKJXX_FPTXX fptxx) {
		this.fptxx = fptxx;
	}
	public FPKJXX_DDXX getDdxx() {
		return ddxx;
	}
	public void setDdxx(FPKJXX_DDXX ddxx) {
		this.ddxx = ddxx;
	}
	public List<FPKJXX_XMXX> getXmxxs() {
		return xmxxs;
	}
	public void setXmxxs(List<FPKJXX_XMXX> xmxxs) {
		this.xmxxs = xmxxs;
	}
	public REQUEST_FPXXXZ_NEW getFpxxxzNew() {
		return fpxxxzNew;
	}
	public void setFpxxxzNew(REQUEST_FPXXXZ_NEW fpxxxzNew) {
		this.fpxxxzNew = fpxxxzNew;
	}
	public GlobalInfo getGlobalInfo() {
		return globalInfo;
	}
	public void setGlobalInfo(GlobalInfo globalInfo) {
		this.globalInfo = globalInfo;
	}
	
}
