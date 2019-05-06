package com.bluekjg.wxapp.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.PayDto;
import com.bluekjg.wxapp.model.WxOrderRefund;
import com.bluekjg.wxapp.model.WxOrderTrans;
import com.bluekjg.wxapp.pay.IPUtils;
import com.bluekjg.wxapp.pay.MdlPayUtil;
import com.bluekjg.wxapp.pay.WXOrderQuery;
import com.bluekjg.wxapp.pay.WXPay;
import com.bluekjg.wxapp.pay.WXPrepay;
import com.bluekjg.wxapp.pay.XMLUtil;
import com.bluekjg.wxapp.service.IWxOrderService;
import com.bluekjg.wxapp.service.IWxPayService;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

@Controller
@RequestMapping("/xcx/pay")
public class WxPayController extends BaseController{
	@Autowired
	private IWxOrderService orderService;
	@Autowired
	private IWxPayService payService;
	
	/**
	 * 微信支付主方法
	 * 
	 * @param request
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/wxpay")
	@ResponseBody
	public Object pay(@Valid Integer orderId,HttpServletRequest request) {
		Object obj = renderError("支付失败");
		try {
			WxOrderTrans trans = orderService.selectOrderTrans(orderId);
			if(trans == null || trans.getStatus() == 1 || trans.getOrderStatus() != 0) {
				return renderError("支付错误");
			}
			String total_prices = String.valueOf(trans.getBalances()
					.multiply(new BigDecimal(100)).intValue());
			WXPrepay prePay = new WXPrepay();
			prePay.setAppid(WxappConfigUtil.WX_APPID);
			prePay.setPartnerKey(WxappConfigUtil.WX_MERCHANTS_SECRET);	//商户号秘钥
			prePay.setMch_id(WxappConfigUtil.WX_MERCHANTS_APPID);		//商户号
			prePay.setBody(trans.getTransName());						//订单支付描述
			prePay.setNotify_url(MdlPayUtil.notifyUrl);					//回调地址
			prePay.setOut_trade_no(trans.getTransNo());					//回调的支付编号
			prePay.setSpbill_create_ip(IPUtils.getClientAddress(request));
			prePay.setTotal_fee(total_prices); 							//订单支付价格
			prePay.setTrade_type("JSAPI");
			prePay.setOpenid(trans.getOpenId());						//支付人OpenID
			// 获取预支付订单号
			String prepayid = prePay.submitXmlGetPrepayId();
			logger.info("获取的预支付订单是：" + prepayid);
			if (prepayid != null && prepayid.length() > 10) {
				// 生成微信支付参数，符合支付调起传入格式
				SortedMap<String, String> map = WXPay.createPackageValue(WxappConfigUtil.WX_APPID,WxappConfigUtil.WX_MERCHANTS_SECRET, prepayid);
				obj = renderSuccess(map);
			}
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return obj;
	}
	
	
	/**
	 * 支付后回调
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/notice")
	public void notice(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		String result = new String(outSteam.toByteArray(), "utf-8");
		Map<String, String> map = null;
		try {
			map = XMLUtil.doXMLParse(result);
		} catch (JDOMException e) {
			logger.error(e.getMessage(),e);
		}
		// 此处调用订单查询接口验证是否交易成功
		boolean isSucc = reqOrderquery(map, MdlPayUtil.accessToken);
		// 支付成功，商户处理后同步返回给微信参数
		if (!isSucc) {
			String noticeStr = setXML(map.get("return_code"), map.get("return_msg"));
			out.print(noticeStr);
			System.out.println("===============支付失败==============>>>错误信息："+map.get("return_msg"));
		} else {
			System.out.println("==================================>>>商户订单号："+map.get("out_trade_no"));
			System.out.println("===============付款成功==============>>>返回状态码："+map.get("return_code"));
			String out_trade_no = map.get("out_trade_no");
			boolean isCoupon = map.containsKey("coupon_fee");
			String couponFee = "";
			if(isCoupon){
				couponFee = map.get("coupon_fee");
			}
			PayDto payDto = new PayDto();
			payDto.setCoupon(isCoupon);
			payDto.setOutTradeNo(out_trade_no);
			payDto.setCouponFee(couponFee);
			payService.paySuccesssNotice(payDto);
			String noticeStr = setXML(map.get("return_code"), map.get("return_msg"));
			out.print(noticeStr);
		}
	}
	public static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code
				+ "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}
	/**
	 * 请求订单查询接口
	 * 
	 * @param map
	 * @param accessToken
	 * @return
	 */
	public boolean reqOrderquery(Map<String, String> map, String accessToken) {
		WXOrderQuery orderQuery = new WXOrderQuery();
		orderQuery.setAppid(map.get("appid"));
		orderQuery.setMch_id(map.get("mch_id"));
		orderQuery.setTransaction_id(map.get("transaction_id"));
		orderQuery.setOut_trade_no(map.get("out_trade_no"));
		orderQuery.setNonce_str(map.get("nonce_str"));
		orderQuery.setPartnerKey(WxappConfigUtil.WX_MERCHANTS_SECRET);

		Map<String, String> orderMap = orderQuery.reqOrderquery();
		
		// 此处添加支付成功后，支付金额和实际订单金额是否等价，防止钓鱼
		if (orderMap.get("return_code") != null && orderMap.get("return_code").equalsIgnoreCase("SUCCESS")) {
			if (orderMap.get("trade_state") != null && orderMap.get("trade_state").equalsIgnoreCase("SUCCESS")) {
				String total_fee = map.get("total_fee");
				String order_total_fee = map.get("total_fee");
				System.out.println(order_total_fee+"=====>"+total_fee);
				if (Integer.parseInt(order_total_fee) >= Integer.parseInt(total_fee)) {
					return true;
				}
			}
		}
		return false;
	}
	
	 /**
     * 订单退款
     *
     * @param resource
     * @return
     */
    @RequestMapping("/wxrefund")
    @ResponseBody
    public Object wxrefund(@Valid String refundNo,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("退款失败");
        try {
        	
        	WxOrderRefund refund = orderService.selectOrderTransByRefundNo(refundNo);
        	if(refund == null || refund.getStatus() != 3) {
				return renderError("无退款");
			}else{
				Integer result = payService.wxrefund(request,refund);
				if(result>0){
					obj = renderSuccess("ok");
				}
				
			}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    

    /**
	 * 退款后回调
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/refundNotice")
	public void refundNotice(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		String result = new String(outSteam.toByteArray(), "utf-8");
		Map<String, String> map = null;
		try {
			map = XMLUtil.doXMLParse(result);
		} catch (JDOMException e) {
			logger.error(e.getMessage(),e);
		}
		// 此处调用订单查询接口验证是否交易成功
		boolean isSucc = reqOrderquery(map, MdlPayUtil.accessToken);
		// 支付成功，商户处理后同步返回给微信参数
		if (!isSucc) {
			String noticeStr = setXML(map.get("return_code"), map.get("return_msg"));
			out.print(noticeStr);
			System.out.println("===============退款失败==============>>>错误信息："+map.get("return_msg"));
		} else {
			System.out.println("==================================>>>商户订单号："+map.get("out_refund_no"));
			System.out.println("===============退款成功==============>>>返回状态码："+map.get("return_code"));
			String out_refund_no = map.get("out_refund_no");
			PayDto payDto = new PayDto();
			payDto.setOutRefundNo(out_refund_no);
			payService.refundSuccesssNotice(payDto);
			String noticeStr = setXML(map.get("return_code"), map.get("return_msg"));
			out.print(noticeStr);
		}
	}
}
