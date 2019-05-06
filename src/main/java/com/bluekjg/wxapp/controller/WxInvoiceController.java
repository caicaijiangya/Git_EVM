package com.bluekjg.wxapp.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.invoice.FPKJXX_DDXX;
import com.bluekjg.core.commons.invoice.FPKJXX_FPTXX;
import com.bluekjg.core.commons.invoice.FPKJXX_XMXX;
import com.bluekjg.core.commons.invoice.REQUEST_FPXXXZ_NEW;
import com.bluekjg.core.commons.utils.WebUtils;
import com.bluekjg.wxapp.model.WxInvoice;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderDetail;
import com.bluekjg.wxapp.service.IWxInvoiceService;
import com.bluekjg.wxapp.service.IWxOrderService;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

@Controller
@RequestMapping("/xcx/integral")
public class WxInvoiceController  extends BaseController{
	@Autowired
	private IWxInvoiceService invoiceService;
	@Autowired
	private IWxOrderService orderService;
	/**
     * 电子发票
     *
     * @param resource
     * @return
     */
    @RequestMapping("/electronicInvoice")
    @ResponseBody
    public Object electronicInvoice(@Valid WxInvoice invoice,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("开票失败");
        try {
        	WxOrder order = orderService.queryOrderById(invoice.getOrderId());
        	List<WxOrderDetail> detailList = orderService.queryOrderRefundGoodsList(invoice.getOrderId());
        	if(order != null && detailList != null && detailList.size() > 0) {
        		/*
            	 * 发票头信息
            	 */
            	FPKJXX_FPTXX fptxx = new FPKJXX_FPTXX();
        		//纳税人
        		if(order.getStoreId() != null && order.getStoreId() > 0) {
        			//小程序订单
                	fptxx.setXHFMC(WxappConfigUtil.SHDZFP_TAXPAYER_NAME2);
                	fptxx.setXHF_DZ(WxappConfigUtil.SHDZFP_TAXPAYER_DZ2);
                	fptxx.setXHF_DH(WxappConfigUtil.SHDZFP_TAXPAYER_DH2);
                	fptxx.setXHF_YHZH(WxappConfigUtil.SHDZFP_TAXPAYER_YHZH2);
        		}else {
        			//门店订单
        			fptxx.setXHFMC(WxappConfigUtil.SHDZFP_TAXPAYER_NAME1);
                	fptxx.setXHF_DZ(WxappConfigUtil.SHDZFP_TAXPAYER_DZ1);
                	fptxx.setXHF_DH(WxappConfigUtil.SHDZFP_TAXPAYER_DH1);
                	fptxx.setXHF_YHZH(WxappConfigUtil.SHDZFP_TAXPAYER_YHZH1);
        		}
            	
            	fptxx.setFPQQLSH(WebUtils.getItemID(20));//流水号
            	fptxx.setKPXM(WxappConfigUtil.SHDZFP_KPXM);
            	//发票抬头
            	fptxx.setGHFQYLX(invoice.getGhfqylx());
            	fptxx.setGHFMC(invoice.getGhfmc());
            	fptxx.setGHF_NSRSBH(invoice.getGhfNsrsbh());
            	fptxx.setKPY(WxappConfigUtil.SHDZFP_KPY);
            	fptxx.setKPHJJE(order.getTotalBalances().toString());//价税合计金额
            	fptxx.setHJBHSJE("0");//合计不含税金额
            	fptxx.setHJSE("0");//合计税额
            	/*
            	 * 项目信息（商品信息）
            	 */
            	List<FPKJXX_XMXX> xmxxs = new ArrayList<FPKJXX_XMXX>();
            	for(WxOrderDetail detail:detailList) {
            		FPKJXX_XMXX xmxx = new FPKJXX_XMXX();
                	xmxx.setXMMC(detail.getGoodsName());
                	xmxx.setXMSL(detail.getGoodsNum().toString());//项目数量
                	xmxx.setXMDJ(detail.getGoodsPrice().toString());//项目单价
                	xmxx.setXMJE(String.valueOf(detail.getGoodsPrice()*detail.getGoodsNum()));//项目总金额
                	xmxx.setKCE("0");//扣除额
                	xmxx.setSPBM(WxappConfigUtil.SHDZFP_SWBM);
                	xmxxs.add(xmxx);
            	}
            	
            	/*
            	 * 订单信息
            	 */
            	FPKJXX_DDXX ddxx = new FPKJXX_DDXX();
            	ddxx.setDDH(order.getOrderNo());
            	ddxx.setTHDH(order.getOrderNo());
            	/*
            	 * 下载电子发票
            	 */
            	REQUEST_FPXXXZ_NEW fpxxxzNew = new REQUEST_FPXXXZ_NEW();
            	fpxxxzNew.setPDF_XZFS("1");//下载
            	fpxxxzNew.setDDH(ddxx.getDDH());
            	fpxxxzNew.setFPQQLSH(fptxx.getFPQQLSH());
            	invoice.setFpqqlsh(fptxx.getFPQQLSH());
            	invoice.setFptxx(fptxx);
            	invoice.setDdxx(ddxx);
            	invoice.setFpxxxzNew(fpxxxzNew);
            	invoice.setXmxxs(xmxxs);
            	Integer status = invoiceService.openElectronicInvoice(invoice);
            	if(status == 2) {
            		obj = renderSuccess("开票成功");
            	}else if(status == 1) {
            		obj = renderSuccess("开票处理中");
            	}
        	}
        	
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /*public static void main(String arge[]) {
    	WxappConfigUtil.WX_CODING_FORMAT = "UTF-8";
    	WxappConfigUtil.WX_APPID = "wxe0439672492bd6ec";
    	WxappConfigUtil.WX_SECRET = "853c4eeb7376be55ea0d0ec639fdb7df";
    	WxappConfigUtil.WX_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?appid=APPID&secret=SECRET&grant_type=client_credential";
    	String requestUrl = WxappConfigUtil.WX_TOKEN_URL.replace("APPID", WxappConfigUtil.WX_APPID).
                replace("SECRET", WxappConfigUtil.WX_SECRET);
        // 发起GET请求获取凭证
		Map<String,String>  jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (jsonObject != null && jsonObject.get("access_token") != null) {
        	String accessToken = jsonObject.get("access_token");
        	String ticketurl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=wx_card";
        	Map<String,String>  getticketObject = CommonUtil.httpsRequest(ticketurl, "GET", null);
        	System.out.println(getticketObject);
        	String seturl = "https://api.weixin.qq.com/card/invoice/seturl?access_token="+accessToken;
        	Map<String,String>  seturlObject = CommonUtil.httpsRequest(seturl, "POST", "{}");
        	System.out.println(seturlObject);
        	if(getticketObject != null && getticketObject.get("ticket") != null && seturlObject != null && seturlObject.get("invoice_url") != null) {
        		String ticket = getticketObject.get("ticket");
        		String invoice_url = seturlObject.get("invoice_url");
        		JSONObject jo = new JSONObject();
            	jo.put("s_pappid", invoice_url.split("s_pappid")[1]);
            	jo.put("order_id", "001");
            	jo.put("money", "1");
            	jo.put("timestamp", new Date().getTime());
            	jo.put("source", "wxa");
            	jo.put("ticket", ticket);
            	jo.put("type", 1);
            	String getauthurl = "https://api.weixin.qq.com/card/invoice/getauthurl?access_token="+accessToken;
            	Map<String,String>  getauthurlObject = CommonUtil.httpsRequest(getauthurl, "POST", jo.toString());
            	System.out.println(getauthurlObject);
        	}
        	
        }
    }*/
}
