package com.bluekjg.wxapp.service.impl;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.core.commons.invoice.DataDescription;
import com.bluekjg.core.commons.invoice.FPKJXX_XMXX;
import com.bluekjg.core.commons.invoice.GlobalInfo;
import com.bluekjg.core.commons.invoice.ReturnStateInfo;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.core.commons.utils.MapUtils;
import com.bluekjg.core.commons.utils.WebUtils;
import com.bluekjg.core.commons.utils.ZipUtils;
import com.bluekjg.wxapp.mapper.WxInvoiceMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.model.WxInvoice;
import com.bluekjg.wxapp.service.IWxInvoiceService;
import com.bluekjg.wxapp.utils.CommonUtil;
import com.bluekjg.wxapp.utils.PostObjectSample;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

import net.sf.json.JSONObject;

/**
 * @description：电子发票
 * @author：pincui.Tom 
 * @date：2019/04/03 14:51
 */
@Service
public class WxInvoiceServiceImpl extends ServiceImpl<WxInvoiceMapper, WxInvoice>implements IWxInvoiceService {

	protected Logger logger = LoggerFactory.getLogger(WxInvoiceServiceImpl.class);

	@Autowired
	private WxInvoiceMapper invoiceMapper;

	@Override
	public Integer openElectronicInvoice(WxInvoice invoice) {
		int status = 0;
		GlobalInfo globalInfo = new GlobalInfo();
		globalInfo.setInterfaceCode("ECXML.FPKJ.BC.E_INV");//开发票
		globalInfo.setRequestTime(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS));
		globalInfo.setDataExchangeId(globalInfo.getRequestCode()+globalInfo.getInterfaceCode()+DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD) + WebUtils.getItemID(9));
		invoice.setGlobalInfo(globalInfo);
		StringBuffer soapResultData = new StringBuffer(); 
		soapResultData.append(requestMessage(invoice));
    	// 发起GET请求获取凭证
    	Map<String,Object>  jsonObject = CommonUtil.httpRequest(WxappConfigUtil.SHDZFP_URL, "POST", soapResultData.toString());
    	invoice.setSendReturnMessage(jsonObject.toString());
    	if(jsonObject != null && jsonObject.containsKey("returnStateInfo")) {
    		JSONObject returnStateInfo = JSONObject.fromObject(jsonObject).getJSONObject("returnStateInfo");
    		if(returnStateInfo.getString("returnCode").equals("0000")) {
    			status = downloadElectronicInvoice(invoice);//下载发票
    			invoice.setStatus(status);
    			invoiceMapper.insertOrderInvoice(invoice);
    		}
    	}
    	return status;
	}
	@Override
	public Integer downloadElectronicInvoice(WxInvoice invoice) {
		int status = 0;
		GlobalInfo globalInfo = new GlobalInfo();
		globalInfo.setInterfaceCode("ECXML.FPXZ.CX.E_INV");//下载发票
		globalInfo.setRequestTime(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS));
		globalInfo.setDataExchangeId(invoice.getGlobalInfo().getRequestCode()+invoice.getGlobalInfo().getInterfaceCode()+DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD) + WebUtils.getItemID(9));
		invoice.setGlobalInfo(globalInfo);
		StringBuffer soapResultData = new StringBuffer(); 
		soapResultData.append(requestMessage(invoice));
    	// 发起GET请求获取凭证
    	Map<String,Object>  jsonObject = CommonUtil.httpRequest(WxappConfigUtil.SHDZFP_URL, "POST", soapResultData.toString());
    	invoice.setSendReturnMessage(jsonObject.toString());
    	if(jsonObject != null && jsonObject.containsKey("returnStateInfo")) {
    		JSONObject returnStateInfo = JSONObject.fromObject(jsonObject).getJSONObject("returnStateInfo");
    		if(returnStateInfo.getString("returnCode").equals("0000")) {
    			JSONObject dataJson = JSONObject.fromObject(jsonObject).getJSONObject("Data");
    			if(dataJson.containsKey("content") && dataJson.containsKey("dataDescription")) {
    				JSONObject dataDescription = dataJson.getJSONObject("dataDescription");
        	    	InputStream is = null;
        			try {
        				byte[] byteText = dataJson.getString("content").getBytes();
        				String encodedText = "";
        				if(dataDescription.getString("zipCode").equals("1")) {
        					//解压
        					encodedText = new String(ZipUtils.unGZip(Base64.decodeBase64(byteText)));
        				} else {
        					encodedText = new String(Base64.decodeBase64(byteText));
        				}
        		        encodedText = new String(ZipUtils.unGZip(Base64.decodeBase64(byteText)));
        		        Map<String,Object> encodedTextMap = MapUtils.xmlStr2Map(encodedText);
        		        if(encodedTextMap.containsKey("RETURNCODE") 
        		        		&& encodedTextMap.containsKey("PDF_FILE")
        		        		&& encodedTextMap.get("RETURNCODE").toString().equals("0000")) {
        		        	byte[] filebyte = Base64.decodeBase64(encodedTextMap.get("PDF_FILE").toString().getBytes()); 
            				is = new ByteArrayInputStream(filebyte);
            				String newName = globalInfo.getDataExchangeId()+".pdf";
            				// 文件上传
            				String imagePath = "INVOICE_"+DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD);
            				Integer result = PostObjectSample.PostObject(imagePath + "/" + newName, "application/pdf",is);
            				// 返回结果
            				if (result > 0) {
            					status = 2;
            					invoice.setFileUrl(WxappConfigUtil.ALICLOUD_IMAGE_BASE_URL + imagePath + "/" + newName);
            				}
        		        }
        		        
        		        
        			} catch (Exception e) {
        				logger.error(e.getMessage(),e);
        			}finally {
        				try {
        					if(is != null) {
        						is.close();
        					}
        				} catch (IOException e) {
        					logger.error(e.getMessage(),e);
        				}
        				
        			}
    			}
    		}else if(returnStateInfo.getString("returnCode").equals("9411")) {
    			//开票处理中
    			status = 1;
    		}
    	}
    	return status;
	}
	/**
	 * 请求报文
	 * @return
	 */
	public String requestMessage(WxInvoice invoice) {
    	ReturnStateInfo returnStateInfo = new ReturnStateInfo();
    	DataDescription dataDescription = new DataDescription();
    	StringBuffer soapResultData = new StringBuffer(); 
    	soapResultData.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
    	soapResultData.append("<interface xmlns=\"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.chinatax.gov.cn/tirip/dataspec/interfaces.xsd\" version=\"DZFP1.0\">");
    	/**全局信息**/
    	soapResultData.append(invoice.getGlobalInfo().toXmlString());
    	/**返回信息**/
    	soapResultData.append(returnStateInfo.toXmlString());
    	/**交换数据**/
    	soapResultData.append("<Data>");
    	soapResultData.append(dataDescription.toXmlString());
    	
    	soapResultData.append("<content>");
    	StringBuffer contentResultData = new StringBuffer();
    	if(invoice.getGlobalInfo().getInterfaceCode().equals("ECXML.FPKJ.BC.E_INV")) {
    		//开发票
    		contentResultData.append("<REQUEST_FPKJXX class=\"REQUEST_FPKJXX\"> ");
    		contentResultData.append(invoice.getFptxx().toXmlString());
    		contentResultData.append("<FPKJXX_XMXXS class=\"FPKJXX_XMXX;\" size=\""+invoice.getXmxxs().size()+"\">");
    		for(FPKJXX_XMXX xmxx:invoice.getXmxxs()) {
    			contentResultData.append(xmxx.toXmlString());
    		}
    		contentResultData.append("</FPKJXX_XMXXS>");
    		contentResultData.append(invoice.getDdxx().toXmlString());
    		contentResultData.append("</REQUEST_FPKJXX>");
    		invoice.setSendMessage(soapResultData.toString()+contentResultData.toString());
    	}else if(invoice.getGlobalInfo().getInterfaceCode().equals("ECXML.FPXZ.CX.E_INV")){
    		//下载发票
    		contentResultData.append(invoice.getFpxxxzNew().toXmlString());
    		invoice.setDownloadMessage(soapResultData.toString()+contentResultData.toString());
    	}
    	try {
			String encodedText = Base64.encodeBase64String(contentResultData.toString().getBytes("UTF-8"));
			soapResultData.append(encodedText);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
		}
    	soapResultData.append("</content>");
    	soapResultData.append("</Data>");
    	soapResultData.append("</interface>");
    	return soapResultData.toString();
	}
}