package com.bluekjg.core.commons.invoice;

import java.util.Map;
import com.bluekjg.core.commons.utils.MapUtils;

/**
 * 上海电子发票平台
 * 发票订单信息
 * @author Tom
 *
 */
public class FPKJXX_DDXX{
	private String DDH;				//订单号
	private String THDH;			//退货单号 在开具红字发票退 货、折让的时候必 须填写
	private String DDDATE;			//订单时间
	public String getDDH() {
		return DDH;
	}
	public void setDDH(String dDH) {
		DDH = dDH;
	}
	public String getTHDH() {
		return THDH;
	}
	public void setTHDH(String tHDH) {
		THDH = tHDH;
	}
	public String getDDDATE() {
		return DDDATE;
	}
	public void setDDDATE(String dDDATE) {
		DDDATE = dDDATE;
	}
	public String toXmlString() {
		String className = this.getClass().getSimpleName();
		Map<String, Object> resmap = MapUtils.object2Map(this);
		StringBuffer xml = new StringBuffer(); 
		xml.append("<"+className+" class=\""+className+"\">");
		xml.append(InvoiceUtil.requesttoxml(resmap,true));
		xml.append("</"+className+">");
		return xml.toString();
	}
}
