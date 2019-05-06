package com.bluekjg.wxapp.pay;

/**
 * 支付参数
 * @author max
 *
 */
public class MdlPayUtil {
	
	//微信号:AppId
	public static final String appId = System.getProperty("APPID_XYBC");
	//商户id:PartnerId
	public static final String partnerId = System.getProperty("PARTNERLD_XYBC");
	//商户密钥APIKey
	public static final String partnerKey = System.getProperty("APIKEY_XYBC");
	//获取access_token
	public static final String accessToken = "EcacWs_vdvOqSykS-mkq4FEr7tA1RcK2QssDsbI-6qIu6XrVvOJHTSswaLOxo6jOsFLgKW6qw8O3jYXj9LspmYKmDeovnNdpKOJAZc3J9AlXv7KciwexFGPCxftE8bl1DAFjACAGDI";
	//微信支付证书存放路径地址
	public final static String CERT_PATH = "/WEB-INF/cer/apiclient_cert.p12";
	
	public final static String notifyUrl = "https://wxapp.myevm.cn/xcx/pay/notice";
	public final static String refundNotice = "https://wxapp.myevm.cn/xcx/pay/refundNotice";

	/**
	 * 微信支付接口地址
	 */
	//微信支付统一接口(POST)
	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//订单查询接口(POST)
	public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	//微信退款接口(POST)
	public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//关闭订单接口(POST)
	public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	//退款查询接口(POST)
	public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	//对账单接口(POST)
	public final static String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	//短链接转换接口(POST)
	public final static String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	//接口调用上报接口(POST)
	public final static String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
	//微信企业付款接口(POST)
	public static final String TRANS_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
}
