package com.bluekjg.core.commons.print;

import java.io.IOException;
import java.util.Date;
import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.json.JSONObject;

class printerUtilTest {
	public static Logger logger = LogManager.getLogger(printerUtilTest.class.getName());
	public static void main(String arge[]) {
		String url = "http://api.zhongwuyun.com";
		Printer printer = new Printer();
		int time =Math.round(new Date().getTime()/1000);//时间戳
		StringBuffer sb = new StringBuffer();
		sb.append("<S1><C>EVM肌肤管理大师</C></S1><RN>");
		sb.append("<C>月星环球港店</C><RN>");
		sb.append("<C>上海市普陀区中山北路3300号月星环球港B2105室</C><RN>");
		sb.append("<C>Tel: 021-52685878</C><RN>");
		sb.append("销售日期：2019/1/25  20:30:45<RN>");
		sb.append("订单编号：LM20190128157519<RN>");
		sb.append("美容顾问：裘婷婷<RN>");
		sb.append("会员卡号：<RN><RN>");
		sb.append("<TR><TD>产品</TD><TD>单价</TD><TD>数量</TD></TR>");
		sb.append("<TR><TD>水杨酸细致毛孔控油调理乳</TD><TD>129</TD><TD>1</TD></TR>");
		sb.append("<TR><TD>Total</TD><TD>129</TD><TD>1</TD></TR><RN>");
		sb.append("<TR><TD>合计数量：</TD><TD></TD><TD>1</TD></TR>");
		sb.append("<TR><TD>合计金额：</TD><TD></TD><TD>129</TD></TR>");
		sb.append("<TR><TD>优惠金额：</TD><TD></TD><TD>0</TD></TR>");
		sb.append("<TR><TD>优惠</TD><TD></TD><TD></TD></TR>");
		sb.append("<TR><TD>满减活动</TD><TD></TD><TD>10</TD></TR>");
		sb.append("<TR><TD>满300减100</TD><TD></TD><TD>100</TD></TR><RN>");
		sb.append("<TR><TD>实付金额</TD><TD></TD><TD>129</TD></TR>");
		sb.append("<TR><TD>支付方式：</TD><TD></TD><TD></TD></TR>");
		sb.append("<TR><TD>微信支付</TD><TD></TD><TD>129</TD></TR><RN>");
		sb.append("<QR>data:image/png;base64,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</QR>");
		sb.append("<C>扫码关注，了解品牌最新资讯！</C>");
		String data = sb.toString();//打印内容
		printer.setAppid(8000119);//appid
		printer.setAppsecret("f118cb32e0b79bf44a2069839082a0e8");//appsecret
		printer.setDeviceid("12350451");//设备id
		printer.setDevicesecret("fkec8nif");//设备秘钥
		printer.setTimestamp(time);//时间戳
		printer.setData(data);
		String sign = printerUtil.getSign(printer);
		//请求数据
		String datas = "appid="+printer.getAppid()+"&sign="+sign+"&timestamp="+time+"&deviceid="+
		printer.getDeviceid()+"&devicesecret="+printer.getDevicesecret()+"&printdata="+printerUtil.percentEncode(data);
		try {
			JSONObject json = printerUtil.sendPost(url, datas);
			if(json != null) {
				int errNum = json.getInt("errNum");
				if(errNum == 0) {
					JSONObject retData = json.getJSONObject("retData");
					if(retData != null) {
						int status = retData.getInt("status");
						if(status == 1) {
							System.out.println("打印成功");
						}else if(status == 0) {
							System.out.println("打印机掉线");
						}else if(status == 2) {
							System.out.println("打印机缺纸或装纸不正确");
						}
					}
				}else if(errNum == 2) {
					System.out.println("提交超时");
				}else if(errNum == 2) {
					System.out.println("打印机信息有误");
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}
	}

}