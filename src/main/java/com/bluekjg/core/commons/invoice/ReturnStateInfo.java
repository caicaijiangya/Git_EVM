package com.bluekjg.core.commons.invoice;

import java.util.Map;
import com.bluekjg.core.commons.utils.MapUtils;

/**
 * 上海电子发票平台
 * 返回信息
 * @author Tom
 *
 */
public class ReturnStateInfo{	
	private String returnCode;				//返回代码
	private String returnMessage;			//返回描述
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	
	public String toXmlString() {
		String className = InvoiceUtil.lowerFirst(this.getClass().getSimpleName());
		Map<String, Object> resmap = MapUtils.object2Map(this);
		StringBuffer xml = new StringBuffer(); 
		xml.append("<"+className+">");
		xml.append(InvoiceUtil.requesttoxml(resmap,true));
		xml.append("</"+className+">");
		return xml.toString();
	}
}
