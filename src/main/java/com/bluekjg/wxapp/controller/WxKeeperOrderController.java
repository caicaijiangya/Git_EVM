package com.bluekjg.wxapp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.print.Printer;
import com.bluekjg.core.commons.print.printerUtil;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxActivityFull;
import com.bluekjg.wxapp.model.WxKeeperOrder;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderDetail;
import com.bluekjg.wxapp.model.wx.DataModel;
import com.bluekjg.wxapp.service.IWxKeeperOrderService;
import com.bluekjg.wxapp.service.IWxOrderService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/xcx/store/order")
public class WxKeeperOrderController extends BaseController{

	@Autowired
	private IWxKeeperOrderService service;
	@Autowired
	private IWxOrderService orderService;
	
	/**
     * 查询所有订单
     *
     * @param resource
     * @return
     */
    @RequestMapping("/orderList")
    @ResponseBody
    public Object orderList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxKeeperOrder> list = service.queryOrderList(page);
        	obj = renderSuccess(list);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }

	//查询订单量,订单总额,未取货
	@PostMapping("/queryOrderCount")
	@ResponseBody
	public String queryOrderCount(WxKeeperOrder wxOrder) {
		return service.queryOrderCount(wxOrder);
	}
	
	/**
     * 查询已核销订单
     *
     * @param resource
     * @return
     */
    @RequestMapping("/recodeList")
    @ResponseBody
    public Object recodeList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxKeeperOrder> list = service.queryRecodeList(page);
        	obj = renderSuccess(list);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 店主/店员订单详情
     *
     * @param resource
     * @return
     */
    @RequestMapping("/orderDetail")
    @ResponseBody
    public Object orderDetail(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxKeeperOrder wxOrder = service.selectOrderDetail(page);
        	obj = renderSuccess(wxOrder);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    /**
     * 确认收款
     *
     * @param resource
     * @return
     */
    @RequestMapping("/confirmReceipt")
    @ResponseBody
    public Object confirmReceipt(@Valid DataModel dataModel,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("收款失败");
        try {
        	service.confirmReceipt(dataModel);
        	obj = renderSuccess("收款成功");
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 打印小票
     *
     * @param resource
     * @return
     */
    @RequestMapping("/printReceipts")
    @ResponseBody
    public Object printReceipts(@Valid DataModel dataModel,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("打印失败");
        try {
        	WxOrder order = orderService.queryOrderById(dataModel.getId());
        	if(order.getStore().getDeviceId() == null || order.getStore().getDeviceId().length() == 0) {
        		return obj = renderError("未找到设备");
        	}
        	String url = "http://api.zhongwuyun.com";
    		Printer printer = new Printer();
    		int time =Math.round(new Date().getTime()/1000);//时间戳
    		StringBuffer sb = new StringBuffer();
    		sb.append("<S1><C>EVM肌肤管理大师</C></S1><RN>");
    		sb.append("<C>"+order.getStore().getStoreName()+"</C><RN>");
    		sb.append("<C>"+order.getStore().getAddressDetail()+"</C><RN>");
    		sb.append("<C>Tel: "+order.getStore().getMobileNo()+"</C><RN>");
    		sb.append("<C>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</C><RN>");
    		sb.append("销售日期："+order.getCreateTime()+"<RN>");
    		sb.append("订单编号："+order.getOrderNo()+"<RN>");
    		if(order.getWriteOffOpenId() != null) {
    			sb.append("美容顾问："+order.getWriteOffOpenId()+"<RN>");
    		}
    		sb.append("会员卡号：<RN><RN>");
    		sb.append("<TR><TD>产品</TD><TD>单价</TD><TD>数量</TD></TR>");
    		sb.append("<C>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</C>");
    		int totalNum = 0;
    		double giftPrice = 0.0;
    		double goodsPrice = 0.0;
    		for(WxOrderDetail detail:order.getDetails()) {
    			totalNum += detail.getGoodsNum();
    			goodsPrice += (detail.getGoodsPrice() * detail.getGoodsNum());
    			sb.append("<TR><TD>"+detail.getGoodsName()+"</TD><TD>"+detail.getGoodsPrice()+"</TD><TD>"+detail.getGoodsNum()+"</TD></TR>");
    		}
    		if(order.getGiftList() != null && order.getGiftList().size() > 0) {
    			sb.append("<TR><TD>满"+order.getGiftList().get(0).getFullPrice()+"元赠</TD><TD></TD><TD></TD></TR>");
    			for(WxActivityFull full:order.getGiftList()) {
    				totalNum += full.getGoodsNum();
    				giftPrice += (full.getGoodsPrice() * full.getGoodsNum());
    				sb.append("<TR><TD>"+full.getGoodsName()+"</TD><TD>"+full.getGoodsPrice()+"</TD><TD>"+full.getGoodsNum()+"</TD></TR>");
    				
    			}
    		}
    		sb.append("<TR><TD>Total：</TD><TD>"+(goodsPrice + giftPrice)+"</TD><TD>"+totalNum+"</TD></TR>");
    		sb.append("<C>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</C>");
    		sb.append("<TR><TD>合计数量：</TD><TD></TD><TD>"+totalNum+"</TD></TR>");
    		sb.append("<TR><TD>合计金额：</TD><TD></TD><TD>"+(goodsPrice + giftPrice)+"</TD></TR>");
    		double per = 0.0;
    		if(order.getPreList() != null && order.getPreList().size() > 0) {
    			for(WxActivityFull full:order.getPreList()) {
    				per += full.getPrePrice();
    			}
    		}
    		double discount = 0.0;
    		if(order.getDiscountList() != null && order.getDiscountList().size() > 0) {
    			for(WxActivityFull full:order.getDiscountList()) {
    				per += full.getPrice();
    			}
    		}
    		sb.append("<TR><TD>优惠金额：</TD><TD></TD><TD>"+(order.getDisountPrice() + per + discount + giftPrice)+"</TD></TR>");
    		sb.append("<TR><TD>折扣金额：</TD><TD></TD><TD>"+discount+"</TD></TR>");
    		sb.append("<TR><TD>优惠券：</TD><TD></TD><TD></TD></TR>");
    		if(order.getDisountPrice() > 0) {
    			sb.append("<TR><TD>"+order.getCouponName()+"</TD><TD></TD><TD>"+order.getDisountPrice()+"</TD></TR><RN>");
    		}
    		sb.append("<TR><TD>实付金额：</TD><TD></TD><TD>"+order.getTrans().getBalances()+"</TD></TR>");
    		sb.append("<C>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</C>");
    		sb.append("<TR><TD>支付方式：</TD><TD></TD><TD></TD></TR>");
    		if(order.getPayMoneyStyle() == 0) {
    			sb.append("<TR><TD>微信支付</TD><TD></TD><TD>"+order.getTrans().getBalances()+"</TD></TR><RN>");
    		}else {
    			sb.append("<TR><TD>到店付款</TD><TD></TD><TD>"+order.getTrans().getBalances()+"</TD></TR><RN>");
    		}
    		sb.append("<C>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~</C>");
    		sb.append("<B1>退换货规定：</B1><RN>");
    		sb.append("1.自购买之日起7天内，提供无理由退换货服务（退换货要求：产品未开封，外包装完好不影响二次销售的情况），7日后产品无质量问题不可退货；<RN>");
    		sb.append("2.自购买之日起30天内，如使用产品后皮肤出现过敏症状，需提供二级甲等以上医院开具的过敏证明且产品余量不低于2/3，方可办理退换货。<RN>");
    		sb.append("<B1>退换货须知：</B1><RN>");
    		sb.append("①退换货时请提供收银凭证；<RN>");
    		sb.append("②如有赠品，退货时需将赠品一并退回包括原包装；<RN>");
    		sb.append("③如需退换货，请至原购买店铺办理。<RN>");
    		//sb.append("<QR>data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAgCAYAAAAffCjxAAAA1UlEQVRIiaXUQRXCMBCE4T9RUBEI4ICASAAHxQFScECdtJy5gAA8FAXLgfa9vpA2m81cdubyHdf1fX8EbsATOAEjhnigBRogAP3UTdB1sfdWzAMDcK7F/HS7WswvehXmo23GYsiMpSATtgYVY1tQEZaD1JgGUmFaKIuVQElMRB5AUwr9Yc65HTBYoGQsUMvvEQIgIm8glEIx8nLOHYCxBEohgek1a6FNRAtlEQ2kQnKQGtmCipA1qBhJQSYkhszIEqpCZijUIjN0qUVmqBORj4jcrQjAF+lygXlwIqSBAAAAAElFTkSuQmCC</QR>");
    		//sb.append("<C>扫码关注，了解品牌最新资讯！</C>");
    		String data = sb.toString();//打印内容
    		printer.setAppid(8000119);//appid
    		printer.setAppsecret("f118cb32e0b79bf44a2069839082a0e8");//appsecret
    		printer.setDeviceid(order.getStore().getDeviceId());//设备id
    		printer.setDevicesecret(order.getStore().getDeviceSecret());//设备秘钥
    		printer.setTimestamp(time);//时间戳
    		printer.setData(data);
    		String sign = printerUtil.getSign(printer);
    		//请求数据
    		String datas = "appid="+printer.getAppid()+"&sign="+sign+"&timestamp="+time+"&deviceid="+
    		printer.getDeviceid()+"&devicesecret="+printer.getDevicesecret()+"&printdata="+printerUtil.percentEncode(data);
    		JSONObject json = printerUtil.sendPost(url, datas);
			if(json != null) {
				int errNum = json.getInt("errNum");
				if(errNum == 0) {
					JSONObject retData = json.getJSONObject("retData");
					if(retData != null) {
						int status = retData.getInt("status");
						if(status == 1) {
							obj = renderSuccess("打印成功");
						}else if(status == 0) {
							obj = renderError("打印机掉线");
						}else if(status == 2) {
							obj = renderError("打印机缺纸");
						}
					}
				}else if(errNum == 2) {
					obj = renderError("打印超时");
				}else if(errNum == 2) {
					obj = renderError("信息有误");
				}
			}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
}
