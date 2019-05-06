package com.bluekjg.wxapp.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.wxapp.erp.Client;
import com.bluekjg.wxapp.erp.ConfigInfo;
import com.bluekjg.wxapp.erp.HttpUtil;
import com.bluekjg.wxapp.erp.SignUtil;
import com.bluekjg.wxapp.mapper.WxDictMapper;
import com.bluekjg.wxapp.mapper.WxErpMapper;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxDict;
import com.bluekjg.wxapp.model.WxErpOrder;
import com.bluekjg.wxapp.model.WxErpOrderDetail;
import com.bluekjg.wxapp.service.IWxErpService;
import com.bluekjg.wxapp.utils.DictUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @description：ERP接口服务实体类
 * @author：pincui.Tom 
 * @date：2018/10/19 14:51
 */
@Service
public class WxErpServiceImpl extends ServiceImpl<WxErpMapper, WxErpOrder>implements IWxErpService {
	
	protected Logger logger = LoggerFactory.getLogger(WxErpServiceImpl.class);
	@Autowired
	private WxErpMapper erpMapper;
	@Autowired
	private WxDictMapper dictMapper;
	@Override
	public void pushVipErp(String openId) {
		/*UserBean user = erpMapper.queryUserById(openId);
		if(user != null) {
			String name = "游客";
			String shopCode = "evm_xx";
			WxDict dict = null;
			if(user.getUserName() != null && user.getUserName().length() > 0) {
				name = user.getUserName();
			}
			dict = dictMapper.queryDictByCode(DictUtil.ERP_SHOPCODE);
			if(dict != null) {shopCode = dict.getDictValue();}
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("appkey", ConfigInfo.APPKEY);
			map.put("sessionkey", ConfigInfo.SESSIONKEY);
			map.put("method", "gy.erp.vip.add");
			map.put("code", user.getCode());
			map.put("name", name);
			map.put("shop_code", shopCode);
			List<Map<String, Object>> receive_infos = new ArrayList<Map<String, Object>>();
			Map<String, Object> address = new LinkedHashMap<String, Object>();
			address.put("name", "小程序");
			address.put("receiver", "小程序");
			address.put("mobile", "15800000000");
			address.put("area", "北京-北京市-东城区");
			address.put("address", "备注地址");
			receive_infos.add(address);

			map.put("receive_infos", receive_infos);
			String sign = SignUtil.sign(Client.gson.toJson(map), ConfigInfo.SECRET);
			map.put("sign", sign);
			String requestContent = Client.gson.toJson(map);
			String response = HttpUtil.sendPost(ConfigInfo.URL, requestContent);
			if(response != null && response.length() > 0) {
				JSONObject obj = JSONObject.fromObject(response);
				if(obj.get("success") != null && Boolean.valueOf(obj.get("success").toString()) == true) {
					WxErpOrder order = new WxErpOrder();
					String erpOrderId = obj.get("id").toString();
					String erpOrderCode = obj.get("code").toString();
					order.setErpId(erpOrderId);
					order.setErpCode(erpOrderCode);
					order.setId(user.getId());
					erpMapper.updateErpVip(order);
				}
			}
		}*/
	}
	@Override
	public void pushOrderErp(Integer id) {
		/*WxErpOrder order = erpMapper.queryErpOrderById(id);
		if(order != null && order.getStoreId() == 0) {
			String warehouseCode = "036";
			String expressCode = "YUNDA";
			String shopCode = "evm_xx";
			WxDict dict = null;
			dict = dictMapper.queryDictByCode(DictUtil.ERP_WAREHOUSECODE);
			if(dict != null) {warehouseCode = dict.getDictValue();}
			dict = dictMapper.queryDictByCode(DictUtil.ERP_EXPRESSCODE);
			if(dict != null) {expressCode = dict.getDictValue();}
			dict = dictMapper.queryDictByCode(DictUtil.ERP_SHOPCODE);
			if(dict != null) {shopCode = dict.getDictValue();}
			order.setWarehouseCode(warehouseCode);//快递仓库
			order.setExpressCode(expressCode);//默认物流公司
			order.setShopCode(shopCode);
			
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("appkey", ConfigInfo.APPKEY);
			map.put("sessionkey", ConfigInfo.SESSIONKEY);
			map.put("method", "gy.erp.trade.add");
			map.put("order_type_code", "Sales");
			map.put("warehouse_code", order.getWarehouseCode());//036物流仓，037门店自提仓
			map.put("shop_code", order.getShopCode());
			map.put("cod_fee", "0.00");
			map.put("cod", "false");
			map.put("platform_code", order.getPlatformCode());
			map.put("deal_datetime", order.getDealDatetime());
			map.put("pay_datetime", order.getPayDatetime());
			map.put("express_code", order.getExpressCode());
			map.put("vip_code", order.getVipCode());
			map.put("extend_memo", "");
			List<Map<String, Object>> details = new ArrayList<Map<String, Object>>();
			if(order.getDetails() != null && order.getDetails().size() > 0) {
				for(WxErpOrderDetail detail:order.getDetails()) {
					Map<String, Object> detail_ = new LinkedHashMap<String, Object>();
					detail_.put("oid", order.getPlatformCode());
					detail_.put("item_code", detail.getItemCode());
					if(detail.getSkuCode() != null && detail.getSkuCode().length() > 0) {
						detail_.put("sku_code", detail.getSkuCode());
					}
					detail_.put("price", detail.getPrice());
					detail_.put("qty", detail.getQty());
					details.add(detail_);
				}
			}
			map.put("details", details);
			List<Map<String, Object>> payments = new ArrayList<Map<String, Object>>();
			Map<String, Object> payment = new LinkedHashMap<String, Object>();
			payment.put("pay_type_code", "weixin");
			payment.put("payment", order.getPayment());
			payment.put("pay_code", order.getPayCode());
			if(order.getPayDatetime() != null)
			payment.put("paytime", DateUtil.parseStrToDate(order.getPayDatetime(), DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS).getTime());
			payments.add(payment);
			map.put("payments", payments);
			map.put("receiver_name", order.getReceiverName());
			map.put("receiver_mobile", order.getReceiverMobile());
			map.put("receiver_address", order.getReceiverAddress());
			map.put("receiver_province", order.getReceiverProvince());
			map.put("receiver_city", order.getReceiverCity());
			map.put("receiver_district", order.getReceiverDistrict());
			String sign = SignUtil.sign(Client.gson.toJson(map), ConfigInfo.SECRET);
			map.put("sign", sign);
			String requestContent = Client.gson.toJson(map);
			String response = HttpUtil.sendPost(ConfigInfo.URL, requestContent);
			if(response != null && response.length() > 0) {
				JSONObject obj = JSONObject.fromObject(response);
				if(obj.get("success") != null && Boolean.valueOf(obj.get("success").toString()) == true) {
					String erpOrderId = obj.get("id").toString();
					String erpOrderCode = obj.get("code").toString();
					order.setErpId(erpOrderId);
					order.setErpCode(erpOrderCode);
					erpMapper.updateErpOrder(order);
				}
			}
			
		}*/
		
	}
	@Override
	public void pushOrderRefundErp(String orderNo, Integer status) {
		/*Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.refund.update");
		map.put("tid", orderNo);
		map.put("oid", orderNo);
		map.put("refund_state", status);
		String sign = SignUtil.sign(Client.gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = Client.gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);*/
	}
	@Override
	public void pushDeliveryErp(String orderNo) {
		/*Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.deliverys.get");
		map.put("outer_code", orderNo);
		String sign = SignUtil.sign(Client.gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = Client.gson.toJson(map);
		String response = HttpUtil.sendPost(ConfigInfo.URL, requestContent);
		
		if(response != null && response.length() > 0) {
			JSONObject obj = JSONObject.fromObject(response);
			if(obj.get("success") != null && Boolean.valueOf(obj.get("success").toString()) == true) {
				
				if(obj.get("deliverys") != null) {
					String deliverys = obj.get("deliverys").toString();
					if(deliverys != null && deliverys.length() > 0) {
						JSONArray array = JSONArray.fromObject(deliverys);
						if(array.size() > 0) {
							JSONObject json = array.getJSONObject(0);
							if(json != null && json.get("express_no") != null && json.get("express_no").toString().length() > 4) {
								String expressNo =  json.get("express_no").toString();
								String express_name = json.getString("express_name");
								WxErpOrder order = new WxErpOrder();
								order.setErpId(orderNo);
								order.setErpCode(expressNo);
								order.setExpressCode(express_name);
								erpMapper.updateOrderAddress(order);
								erpMapper.updateOrderStatus(orderNo);
							}
						}
					}
					
				}
			}
		}*/
	}
	@Override
	public void pushOrderReturnErp(Integer id) {
		/*WxErpOrder order = erpMapper.queryErpOrderRefundById(id);
		if(order != null) {
			String shopCode = "tmall_nacola";
			WxDict dict = null;
			dict = dictMapper.queryDictByCode(DictUtil.ERP_SHOPCODE);
			if(dict != null) {shopCode = dict.getDictValue();}
			order.setShopCode(shopCode);
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("appkey", ConfigInfo.APPKEY);
			map.put("sessionkey", ConfigInfo.SESSIONKEY);
			map.put("method", "gy.erp.trade.return.add");
			map.put("type_code", "001");
			map.put("vip_code", order.getVipCode());
			map.put("shop_code", order.getShopCode());
			map.put("trade_platform_code", order.getPlatformCode());
			List<Map<String, Object>> details = new ArrayList<Map<String, Object>>();
			if(order.getDetails() != null && order.getDetails().size() > 0) {
				for(WxErpOrderDetail detail:order.getDetails()) {
					Map<String, Object> detail_ = new LinkedHashMap<String, Object>();
					detail_.put("item_code", detail.getItemCode());
					if(detail.getSkuCode() != null && detail.getSkuCode().length() > 0) {
						detail_.put("sku_code", detail.getSkuCode());
					}
					detail_.put("price", detail.getPrice());
					detail_.put("qty", detail.getQty());
					details.add(detail_);
				}
			}
			map.put("item_detail", details);
			String sign = SignUtil.sign(Client.gson.toJson(map), ConfigInfo.SECRET);
			map.put("sign", sign);
			String requestContent = Client.gson.toJson(map);
			HttpUtil.sendPost(ConfigInfo.URL, requestContent);
		}*/
	}

	
}