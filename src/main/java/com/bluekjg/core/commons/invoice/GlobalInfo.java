package com.bluekjg.core.commons.invoice;

import java.util.Map;
import com.bluekjg.core.commons.utils.MapUtils;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

/**
 * 上海电子发票平台
 * 全局信息
 * @author Tom
 *
 */
public class GlobalInfo{
	private String terminalCode = "0";							//0:B/S 请求来源 1:C/S 请求来源 
	private String appId =WxappConfigUtil.SHDZFP_APPID;						//应用标识
	private String version = WxappConfigUtil.SHDZFP_VERSION;								//API 版本 
	private String interfaceCode;								//API 编码 
	private String userName = WxappConfigUtil.SHDZFP_REQUEST_CODE;						//平台编码 
	private String passWord;									//密码 
	private String requestCode = WxappConfigUtil.SHDZFP_REQUEST_CODE;					//数据交换请求发起方代码 
	private String requestTime;									//数据交换请求发出时间
	private String taxpayerId = WxappConfigUtil.SHDZFP_TAXPAYER_ID;				//纳税人识别号
	private String authorizationCode = WxappConfigUtil.SHDZFP_AUTHORIZATION_CODE;			//纳税人授权码
	private String responseCode = WxappConfigUtil.SHDZFP_RESPONSE_CODE;						//数据交换请求接受方代码 
	private String dataExchangeId;			//数据交换流水号（唯一） requestCode+8 位日期(YYYYMMDD)+9 位序列号
	
	public String getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getInterfaceCode() {
		return interfaceCode;
	}
	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getTaxpayerId() {
		return taxpayerId;
	}
	public void setTaxpayerId(String taxpayerId) {
		this.taxpayerId = taxpayerId;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getDataExchangeId() {
		return dataExchangeId;
	}
	public void setDataExchangeId(String dataExchangeId) {
		this.dataExchangeId = dataExchangeId;
	}
	
	public String toXmlString() {
		String className = InvoiceUtil.lowerFirst(this.getClass().getSimpleName());
		Map<String, Object> resmap = MapUtils.object2Map(this);
		StringBuffer xml = new StringBuffer(); 
		xml.append("<"+className+">");
		xml.append(InvoiceUtil.requesttoxml(resmap,false));
		xml.append("</"+className+">");
		return xml.toString();
	}
}
