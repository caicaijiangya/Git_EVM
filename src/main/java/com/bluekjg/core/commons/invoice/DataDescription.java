package com.bluekjg.core.commons.invoice;

import java.util.Map;
import com.bluekjg.core.commons.utils.MapUtils;

/**
 * 上海电子发票平台
 * 数据描述
 * @author Tom
 *
 */
public class DataDescription{	
	private String zipCode = "0";			//0 不压缩 1 压缩  数据包大于 10k 要求自动压缩. 平台返回时压缩标志为1时企业需要自行解 压缩，为0时不需要解压缩。
	private String encryptCode = "0";		//0:不加密  1: 3DES 加密 2:CA 
	private String codeType = "0";			//0 、3DES加密 、CA加密 
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getEncryptCode() {
		return encryptCode;
	}
	public void setEncryptCode(String encryptCode) {
		this.encryptCode = encryptCode;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
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
