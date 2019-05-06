package com.bluekjg.wxapp.erp;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Client {

	public static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	public static void main(String[] args) throws Exception {
		// getSessionKey();//
		 //getShops(); // 店铺查询
		// getWarehouses(); //仓库查询
		// getAccoutInfo(); //收付款账户查询
		// getSupplier();//供应商查询
		// getGoods(); // 商品查询
		// addGoods(); // 商品新增
		// addSysSubject(); //系统费用科目增加
		 //addSku(); //商品规格增加
		// addItemBarcode(); //商品条码增加
		// addPurchaseArrive(); // 采购入库单增加
		// addPurchase();//采购订单增加
		// approvePurchase();//采购订单审核
		// addPurchaseArriveNew(); // 采购入库单增加（未完成）
//		 addPurchaseReturns();//增加采购退货单
		 //addOrder(); // 订单新增
		// addCompletedOrder(); // 增加销售订单
		// addTradeReturnCompleted(); // 旧退货单增加
		 addTradeReturn(); //新退货单增加
		// addTradeRefund();// 增加退款单
		// addAdjust();//增加调整单
		// addTransfer();//增加调拨单
		// addInventory(); // 增加盘点单
		// addUniqueCode();//增加唯一码对照
		// deleteGoods(); // 商品删除
		// deleteSku(); //商品规格删除
		// updateGoods(); //商品修改
		// updateSku(); //商品规格修改
		// updatePurchaseArrives();//采购入库单修改
		// approvePurchaseArriveNew();//采购入库单审核
		// updateOrderMemo(); // 订单备注修改
		// updateOrderTag(); // 订单标记修改
		// updateOrderRefund(); //订单退款状态修改
		// updateDelivery(); //发货单修改
		// approveTradeReturn(); //退换货单入库
		// updateInventory();// 盘点单修改
		// updateInvoice(); //发货单发票信息修改
		// getVip(); //会员查询
		// getGoodsUnit();//商品单位查询
		// getItemBarcode(); //商品/条码对照查询
		// getPurchase(); // 采购订单查询
		// getPurchaseArrives(); // 采购入库单查询
		// getPurchaseReturns(); //采购退货单查询
//		 getTradeOrders(); // 销售订单查询
		// getHistoryTradeOrders(); // 历史销售订单查询
		 getDelivery(); // 发货单查询
		// getHistoryDelivery(); // 历史发货单查询
//		getTradeReturns(); // 退货单查询
		// getTradeRefund();//退款单查询
		// getInvoice(); // 发票信息查询
		// getStockTransfers(); // 调拨单查询
		// getInventory();//盘点单查询
		// getStocks(); //旧库存查询
		// getNewStock(); // 新库存查询
		// getStockCountOrder(); // 查询盘点单
		// getStockCountOrderBreakeven();//查询盘点单盈亏明细
		// getItemStockCRM(); // 商品进销存查询
		// getSupplierbill(); //供应商流水账
		// getAdjustHeader();//调整单查询
		// getAdjustDetail();//调整单明细查询
		 addVip(); //增加会员
		// updateVip(); //修改会员
		// getAlipayaccount(); // 平台账务流水
		// getCustomerpayment(); //销售收款单查询
		// getPurchaseprepay(); //采购预付款单
		// getPurchasepayment(); //采购应收应付单
		// getPayment();//支付方式查询
		// getPlatformsubject(); //平台费用科目查询
		// getArea(); //省市区信息查询
		// getSyssubject(); //系统费用科目查询
		// addTenant();
		// outTransfer(); // 调拨单出库
		// inTransfer(); //调拨单入库
		// getItemLocationInfo();
		// createItemLocationInfo();
		// deleteItemLocationInfo();
		// setDefaultItemLocation();
		// getVersion();
		// getWeightConfig();
		// getPDAConfig();
		// getWaveDeliveryOrder();
		// getWavedeliveryDetails();
		// getWavePickDetails();
		// UpdateWave();
	}

	public static void UpdateWave() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.wave.update");
		// map.put("wave_code", "BC2032002478");
		// map.put("status", "2");
		// map.put("operator", "admin");
		List<Map<String, Object>> details = new ArrayList<Map<String, Object>>();

		// 第一个detail
		Map<String, Object> detail = new LinkedHashMap<String, Object>();
		detail.put("delivery_code", "SDO2032002440");
		detail.put("scan", "1");
		detail.put("print_express", "1");
		detail.put("print_delivery", "1");

		// 第一个detail中的item_details
		List<Map<String, Object>> item_details = new ArrayList<Map<String, Object>>();

		// 第一个detail中的item_details的第一个item_detail
		Map<String, Object> item_detail = new LinkedHashMap<String, Object>();
		item_detail.put("barcode", "1001");
		item_detail.put("pick_qty", "1");
		item_details.add(item_detail);
		// //第一个detail中的item_details的第二个item_detail
		item_detail = new LinkedHashMap<String, Object>();
		item_detail.put("barcode", "1002");
		item_detail.put("pick_qty", "1");
		item_details.add(item_detail);

		detail.put("item_details", item_details);
		details.add(detail);
		//
		// 第二个detail
		detail = new LinkedHashMap<String, Object>();
		detail.put("delivery_code", "SDO2030037117");
		detail.put("scan", "0");
		detail.put("print_express", "1");
		detail.put("print_delivery", "0");

		// 第二个detail中的item_details
		List<Map<String, Object>> item_details2 = new ArrayList<Map<String, Object>>();

		// 第二个detail中的item_details的第一个item_detail
		Map<String, Object> item_detail2 = new LinkedHashMap<String, Object>();
		item_detail2.put("barcode", "1001");
		item_detail2.put("pick_qty", "2");
		item_details2.add(item_detail2);
		// 第一个detail中的item_details的第二个item_detail
		item_detail2 = new LinkedHashMap<String, Object>();
		item_detail2.put("barcode", "1002");
		item_detail2.put("pick_qty", "5");
		item_details2.add(item_detail2);

		detail.put("item_details", item_details2);
		details.add(detail);

		// 第三个detail
		detail = new LinkedHashMap<String, Object>();
		detail.put("delivery_code", "SDO2032002398");
		detail.put("scan", "0");
		detail.put("print_express", "1");
		detail.put("print_delivery", "0");

		// 第三个detail中的item_details
		List<Map<String, Object>> item_details3 = new ArrayList<Map<String, Object>>();

		// 第三个detail中的item_details的第一个item_detail
		Map<String, Object> item_detail3 = new LinkedHashMap<String, Object>();
		item_detail3.put("barcode", "1003");
		item_detail3.put("pick_qty", "1");
		item_details3.add(item_detail3);
		// 第三个detail中的item_details的第二个item_detail
		item_detail3 = new LinkedHashMap<String, Object>();
		item_detail3.put("barcode", "2001");
		item_detail3.put("pick_qty", "1");
		item_details3.add(item_detail3);

		detail.put("item_details", item_details3);
		details.add(detail);

		// 第四个detail
		detail = new LinkedHashMap<String, Object>();
		detail.put("delivery_code", "SDO2030037093");
		detail.put("scan", "0");
		detail.put("print_express", "1");
		detail.put("print_delivery", "0");

		// 第四个detail中的item_details
		List<Map<String, Object>> item_details4 = new ArrayList<Map<String, Object>>();

		// 第四个detail中的item_details的第一个item_detail
		Map<String, Object> item_detail4 = new LinkedHashMap<String, Object>();
		item_detail4.put("barcode", "1003");
		item_detail4.put("pick_qty", "2");
		item_details4.add(item_detail4);
		// 第四个detail中的item_details的第二个item_detail
		item_detail4 = new LinkedHashMap<String, Object>();
		item_detail4.put("barcode", "2001");
		item_detail4.put("pick_qty", "4");
		item_details4.add(item_detail4);

		detail.put("item_details", item_details4);
		details.add(detail);

		map.put("details", details);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getWavePickDetails() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.wave.pick.details.get");
		map.put("wave_code", "BC2004126618");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getWavedeliveryDetails() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.wave.delivery.details.get");
		map.put("wave_code", "BC2034867064");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getWaveDeliveryOrder() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.wave.delivery.order.get");
		map.put("code", "BC2034867076");
		// map.put("mail_no","70");
		// map.put("status", "0");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getPDAConfig() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.pda.system.config.get");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getVersion() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.system.version.get");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getWeightConfig() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.weight.config.get");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getItemLocationInfo() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.item.location.get");
		map.put("page_no", "1");
		map.put("page_size", "100");
		// map.put("cancel", "0");
		map.put("barcode", "170601w02b");
		// map.put("warehouse_code", "0001");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void createItemLocationInfo() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.item.location.add");
		map.put("warehouse_code", "CK001");// demo:CK001;
		map.put("location_code", "K1");// demo:k3
		map.put("barcode", "1.01.000.00002");// demo:SP0001
		map.put("default_item_location", "1");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void deleteItemLocationInfo() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.item.location.delete");
		map.put("warehouse_code", "CKDM001");
		map.put("location_code", "KW003");
		map.put("barcode", "20170518010");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void setDefaultItemLocation() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.default.itemlocation.set");
		map.put("warehouse_code", "CK001");
		map.put("location_code", "k1");
		map.put("barcode", "SP0001");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addTenant() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.could.order.download.syn.add");
		map.put("user_type", "2"); // 1:新客户 2：老客户
		map.put("tenant_code", "43455er242425242t44");
		map.put("uid", "2078712113441311");
		map.put("account", "13614141414");
		map.put("company_name", "上海管易云计算软件有限公司");
		map.put("soft_version", 2); // 1：标准版本 2：专业版本 3：企业版本
		map.put("end_date", "2019-11-21 20:00:00");
		map.put("purchaser", "张三");
		map.put("purchaser_phone", "15827710064");
		map.put("pay_amount", "28000");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addVip() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.vip.add");
		map.put("code", "45dsf222sf+2331");
		map.put("name", "王维奇");
		map.put("shop_code", "20170314002");
		// map.put("level_code", "004");
		// map.put("business_man_code", "260600110@qq.com");
		map.put("sex", 1);
		map.put("birthday", "2016-12-17");
		map.put("email", "test@qq.com");
		map.put("qq", "qqqqqqqqqqq");
		map.put("ww", "wwww");
		map.put("real_name", "MMMM");
		map.put("id_card", "4123123123123");
		map.put("agent", 0);
		List<Map<String, Object>> receive_infos = new ArrayList<Map<String, Object>>();
		Map<String, Object> address = new LinkedHashMap<String, Object>();
		address.put("name", "测试测试浿");
		address.put("receiver", "小维");
		address.put("telephone", "020-127777777");
		address.put("mobile", "13632131231");
		address.put("zip", "213124");
		address.put("area", "北京-北京市-东城区");
		address.put("address", "test item...");
		receive_infos.add(address);

		map.put("receive_infos", receive_infos);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void updateVip() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.vip.update");
		map.put("id", "1459290240");
		// map.put("code", "tfff222234");
		// map.put("shop_code", "000001");
		map.put("new_shop_code", "000001");
		map.put("level_code", "003");
		map.put("business_man_code", "260600110@qq.com");
		map.put("sex", 1);
		map.put("birthday", "2016-12-16");
		map.put("email", "test@qq.comN");
		map.put("qq", "qqqqqqqqqqqN");
		map.put("ww", "wwwwN");
		map.put("real_name", "MMMMB");
		map.put("id_card", "412312312312B3");
		map.put("agent", 1);
		List<Map<String, Object>> receive_infos = new ArrayList<Map<String, Object>>();
		Map<String, Object> address = new LinkedHashMap<String, Object>();
		address.put("name", "测试测试浿B");
		address.put("receiver", "小维B");
		address.put("telephone", "020-12314123B");
		address.put("mobile", "1367776666");
		address.put("zip", "213124N");
		address.put("area", "北京-北京市-东城区");
		address.put("address", "test itemN...");
		receive_infos.add(address);

		map.put("receive_infos", receive_infos);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getItemStockCRM() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.report.itemstockcrm.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		map.put("start_date", "2016-10-15 09:11:11");
		map.put("end_date", "2016-10-21 11:11:11");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getSupplierbill() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.supplierbill.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		// map.put("start_date", "2016-10-15 09:11:11");
		// map.put("end_date", "2016-10-21 11:11:11");
		map.put("supplier_code", "11111");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getAccoutInfo() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.account.info.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		// map.put("start_date", "2016-09-21 09:11:11");
		// map.put("end_date", "2016-10-21 11:11:11");
		// map.put("code", "AC1393359981");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getStockCountOrder() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.count.order.get");
		map.put("page_no", "1");
		map.put("page_size", "10");
		map.put("date_type", "4");
		// map.put("code", "WCO40330963294");
		map.put("start_date", "2017-03-29 00:00:01");
		map.put("end_date", "2017-03-29 23:59:59");
		// map.put("approve_status","2");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getStockCountOrderBreakeven() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.count.order.breakeven.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		map.put("code", "WCO1447034626");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getSyssubject() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.syssubject.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getPlatformsubject() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.tenant.platform.subject.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getArea() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.area.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getAdjustHeader() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.adjustheader.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		// map.put("start_date", "2016-10-21 09:11:11");
		// map.put("end_date", "2016-10-21 11:11:11");
		// map.put("approve", 0);
		map.put("code", "WAO63583317032");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getAdjustDetail() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.adjustdetail.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		map.put("code", "WAO1452830295");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getAlipayaccount() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.alipay.account.order.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		map.put("finance_code", "17231413162");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getPurchaseprepay() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.purchase.prepay.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		map.put("code", "1453271573");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getPurchasepayment() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.purchase.payment.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		map.put("code", "1453448778");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getPayment() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.payment.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		map.put("code", "zhifubao");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void approvePurchaseArriveNew() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.new.purchase.arrive.approve");
		map.put("code", "PAO63582543797");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void approvePurchase() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.purchase.approve");
		map.put("code", "PO45984064077");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addTradeRefund() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.refund.add");
		map.put("refund_code", System.currentTimeMillis());
		map.put("refund_type", "0");// 0-仅退款;1-退货退款
		map.put("refund_reason", "lalallala");
		map.put("trade_code", "SO1804813088");
		map.put("shop_code", "tb001");
		map.put("vip_code", "test201705281925");
		map.put("payment_type_code", "zhifubao");
		map.put("amount", "200");
		map.put("note", "退款单备注。。");
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		// Map<String, Object> item = new LinkedHashMap<String, Object>();
		// item.put("barcode", "SP00000011101");
		// item.put("qty", "10");
		// item.put("price", "20");
		// item.put("note", "test item...");
		// detailList.add(item);
		// item = new LinkedHashMap<String, Object>();
		// item.put("barcode", "SP00000011102");
		// item.put("price", "15");
		// item.put("qty", "20");
		// item.put("note", "test item...");
		// detailList.add(item);
		map.put("item_detail", detailList);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getGoodsUnit() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.items.unit.get");
		// map.put("code", "001");
		map.put("start_date", "1998-10-21 09:11:11");
		map.put("end_date", "2017-12-05 23:59:59");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addPurchaseArriveNew() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.new.purchase.arrive.add");
		map.put("warehouse_code", "TQC");
		map.put("supplier_code", "对接供应商");
		// map.put("order_type", "ctcg001");
		map.put("purchase_code", "PO63581541809");
		map.put("note", "test..");
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new LinkedHashMap<String, Object>();
		item.put("barcode", "201704111020");
		item.put("qty", "100");
		item.put("price", "1000");
		item.put("note", "test item...");
		detailList.add(item);
		// item = new LinkedHashMap<String, Object>();
		// item.put("barcode", "201704071806");
		// item.put("qty", "10000");
		// item.put("note", "test item...");
		// detailList.add(item);
		map.put("detail_list", detailList);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void updateInventory() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.count.update");
		map.put("code", "WCO81769739540");
		// map.put("type_code", "001");
		// map.put("warehouse_code", "CK01");
		// map.put("note", "update");
		List<Map<String, Object>> item_detail = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new LinkedHashMap<String, Object>();
		item.put("barcode", "这是条码1");
		item.put("qty", 15);
		item.put("note", "detail add");
		item_detail.add(item);

		item = new LinkedHashMap<String, Object>();
		item.put("barcode", "11001121");
		item.put("qty", 3);
		item.put("note", "detail add");
		item_detail.add(item);

		item = new LinkedHashMap<String, Object>();
		item.put("barcode", "41ffffff");
		item.put("qty", 5);
		item.put("note", "detail add");
		item_detail.add(item);

		item = new LinkedHashMap<String, Object>();
		item.put("barcode", "lxd20170724005");
		item.put("qty", 6);
		item.put("note", "detail add");
		item_detail.add(item);

		item = new LinkedHashMap<String, Object>();
		item.put("barcode", "赠品D002");
		item.put("qty", 7);
		item.put("note", "detail add");
		item_detail.add(item);

		map.put("item_detail", item_detail);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getInventory() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.count.get");
		// map.put("code", "WCO8797488229");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getNewStock() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.new.stock.get");
		map.put("page_no", "1");
		map.put("page_size", "10");
		// map.put("start_date", "2016-07-21 19:55:00");
		// map.put("end_date","2016-07-21 23:59:59");
		// map.put("barcode", "20171129005");
		map.put("item_code", "L0012-100");
		// map.put("item_sku_code", "01030300123-500");
		// map.put("cancel", 0);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getItemBarcode() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.item.barcode.get");
		map.put("page_no", "1");
		map.put("page_size", "100");
		// map.put("date_type", "1");
		// map.put("start_date", "2017-06-15 09:30:00");
		// map.put("end_date", "2017-06-28 23:00:00");
		// map.put("barcode", "1.01.000.0000075071");
		// map.put("item_code", "1.01.000.00000");
		// map.put("sku_code", "7507");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addItemBarcode() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.item.barcode.add");
		map.put("item_code", "SP000000111");
		map.put("sku_code", "SP00000011101");
		map.put("barcode", "513145131313141133");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getInvoice() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.invoice.get");
		map.put("page", "1");
		map.put("rows", "1");
		map.put("invoicebegintime", "2018-01-01 00:00:01");
		map.put("invoiceendtime", "2018-03-20 23:59:59");
		// map.put("warehousecode", "01");
		// map.put("code", "SDO61699856882");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void updateDelivery() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.deliverys.update");
		map.put("code", "SDO63532968652");
		// map.put("express_code", "STO");
		// map.put("mail_no", "213141");
		// map.put("weight_qty", "109.09");
		List<Map<String, Object>> deliverys_state_paramlist = new ArrayList<Map<String, Object>>();
		Map<String, Object> action = new LinkedHashMap<String, Object>();
		action.put("area_id", "2");
		action.put("operator", "mgw");
		action.put("operator_date", "2016-05-09 11:11:11");
		deliverys_state_paramlist.add(action);
		action = new LinkedHashMap<String, Object>();
		action.put("area_id", "3");
		// action.put("operator", "mgw");
		// action.put("operator_date", "2016-05-09 11:11:11");
		deliverys_state_paramlist.add(action);
		// action = new LinkedHashMap<String, Object>();
		// action.put("area_id", "1");
		// action.put("operator", "test1");
		// action.put("operator_date", "2015-01-09 12:12:12");
		// deliverys_state_paramlist.add(action);
		// action = new LinkedHashMap<String, Object>();
		// action.put("area_id", "2");
		// action.put("operator", "test2");
		// action.put("operator_date", "2015-01-09 13:13:13");
		// deliverys_state_paramlist.add(action);
		map.put("deliverys_state_paramlist", deliverys_state_paramlist);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void updateInvoice() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.invoice.update");
		map.put("pid", "SDO10010817811");
		map.put("invoiceno", "14141414");
		map.put("billamount", "1304");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getShops() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.shop.get");
		map.put("page_no", "1");
		map.put("page_size", "10");
		// map.put("code", "12345465654");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getSupplier() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.supplier.get");
		map.put("page_no", "4");
		map.put("page_size", "10");
		// map.put("start_date", "2016-10-21 09:00:00");
		// map.put("end_date", "2016-10-21 23:59:59");
		// map.put("code", "001");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void approveTradeReturn() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.return.approve");
		map.put("code", "RGO6999332255");
		map.put("warehouse_code", "CK02");
		map.put("express_code", "STO");
		map.put("express_no", "1115001002754");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getVip() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.vip.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		// map.put("start_created", "2015-06-01 08:00:00");
		// map.put("end_created", "2015-06-30 23:59:59");
		// map.put("level_code", "02");
		// map.put("shop_code", "feiniu");
		// map.put("code", "Kevin998");
		// map.put("name", "名称");
		// map.put("mobile", "11111111");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getWarehouses() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.warehouse.get");
		map.put("page_no", "1");
		map.put("page_size", "50");
		// map.put("code", "99999");
		map.put("start_date", "2018-05-01 00:00:00");
		map.put("end_date", "2018-05-10 17:24:08");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getGoods() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.items.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		map.put("code", "HKF01080-M");
		// map.put("test", "12434");
		// map.put("start_date", "2016-07-18 09:00:00");
		// map.put("end_date", "2016-07-18 23:59:59");
		// map.put("status", 1);
		// map.put("code", "test31589211089");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getStocks() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.get");
		map.put("page_no", "1");
		map.put("page_size", "101");
		// map.put("item_code", "test");
		// map.put("sku_code", "R1504TSARM01OS");
		map.put("warehouse_code", "01“");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getDelivery() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.deliverys.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		// map.put("outer_code", "1");
		// map.put("warehouse_code", "002");
		// map.put("shop_code", "006");
		map.put("code", "SDO99140182407"); // SDO1054847573
		// map.put("mail_no","1115001002705");
		// map.put("start_create", "2016-02-15 18:00:00");
		// map.put("end_create", "2016-09-28 15:18:11");
		// map.put("start_delivery_date", "2016-07-29 21:21:00");
		// map.put("end_delivery_date", "2016-07-29 21:22:00");
		// map.put("delivery", "1");
		// map.put("wms", "1");
		// map.put("del", 1);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getHistoryDelivery() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.deliverys.history.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		// map.put("start_delivery_date", "2017-11-10 00:00:01");
		// map.put("end_delivery_date", "2017-12-05 23:59:59");
		map.put("code", "SDO82746267985");
		// map.put("shop_code", "001");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getTradeOrders() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.get");
		map.put("page_no", "1");
		map.put("page_size", "10");
		// map.put("start_date", "2017-03-13 15:30:01");
		// map.put("end_date", "2017-03-13 23:59:59");
		// map.put("date_type", 3);
		// map.put("order_state", "0");
		// map.put("warehouse_code", "ding1");
		// map.put("shop_code", "005");
		// map.put("vip_name", "王媛媛1");
		// map.put("platform_code", "AOC2016070280419");
		// map.put("receiver_mobile", "13600100100");
		map.put("code", "SO92021687175");
		// map.put("abc","abc——+——！~");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getSessionKey() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.could.tenant.app.get");
		map.put("code", "ceshi000");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getHistoryTradeOrders() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.history.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		// map.put("start_date", "2016-08-21 09:00:01");
		// map.put("end_date", "2016-08-25 23:59:59");
		// map.put("date_type", 3);
		// map.put("order_state", "0");
		// map.put("warehouse_code", "ding1");
		// map.put("shop_code", "005");
		// map.put("vip_name", "王媛媛1");
		// map.put("platform_code", "1465876047789");
		// map.put("receiver_mobile", "13600100100");
		map.put("code", "SO88887306545");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getTradeReturns() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.return.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		map.put("code", "RGO95788513167");
		// map.put("code", "RGO49674106470");
		// map.put("warehousein_code", "001");
		// map.put("shop_code", "111");
		// map.put("in_begin_time", "2015-02-06 00:00:00");
		// map.put("receive", "0");
		// map.put("agree", "1");
		// map.put("cancel", "0") ;
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getTradeRefund() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.refund.get");
		map.put("page_no", "1");
		map.put("page_size", "10");
		map.put("code", "RMO68486783886");
		// map.put("return_type", "2");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getPurchaseArrives() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.purchase.arrive.get");
		map.put("page_no", "1");
		map.put("page_size", "10");
		// map.put("start_approve", "2016-10-21 12:48:00");
		// map.put("end_approve", "2016-10-21 23:59:59");
		map.put("code", "PAO85168116599");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void updatePurchaseArrives() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.purchase.arrive.update");
		map.put("code", "PAO979995924");
		// map.put("type_code", "");
		// map.put("warehouse_code", "");
		map.put("note", "单据备注");
		List<Map<String, Object>> item_detail = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new LinkedHashMap<String, Object>();
		item.put("barcode", "AH025");
		item.put("qty", "17");
		item.put("origin_price", "15");
		item.put("price", "15");
		item.put("note", "商品备注");
		item_detail.add(item);
		map.put("item_detail", item_detail);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getPurchaseReturns() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.purchase.return.get");
		map.put("page_no", "12");
		map.put("page_size", "1");
		// map.put("start_approve", "2014-04-08 00:00:01 ");
		// map.put("end_approve", "2015-05-20 00:00:01 ");
		// map.put("code", "PRO1405758519");
		map.put("status", "0");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addPurchaseReturns() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.purchase.return.add");
		map.put("warehouse_code", "STORE_232228");
		map.put("supplier_code", "001");
//		map.put("type_code", "DSCXSS");
		map.put("ex_cost", "100");
		map.put("note", "test..");
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new LinkedHashMap<String, Object>();
		item.put("item_code", "SP000000111");
		item.put("sku_code", "SP00000011101");
		item.put("qty", "10");
		item.put("price", "20");
		item.put("note", "test item...");
		detailList.add(item);
		item = new LinkedHashMap<String, Object>();
		item.put("item_code", "SP000000111");
		item.put("sku_code", "SP00000011102");
		item.put("qty", "20");
		item.put("note", "test item...");
		detailList.add(item);
		map.put("detail_list", detailList);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getPurchase() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.purchase.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		// map.put("code", "PO2016243269");
		// map.put("status", "0");
		// map.put("start_create_date", "2015-07-03 15:51:01");
		// map.put("end_create_date", "2015-07-16 13:25:01");
		// map.put("start_approve_date", "2015-07-03 15:51:32");
		// map.put("end_approve_date", "2015-07-16 17:52:05");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addLogistics() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.logistics.add");
		map.put("code", "2891047338");
		map.put("express_no", "87173819313");
		map.put("express_code", "STO");
		// map.put("status", "2");
		List<Map<String, Object>> steps = new ArrayList<Map<String, Object>>();
		Map<String, Object> step = new LinkedHashMap<String, Object>();
		step.put("status_time", "2014-11-11 12:18:00");
		step.put("status_desc", "1111111111111111");
		steps.add(step);
		map.put("steps", steps);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addUniqueCode() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.items.uniquecode.add");
		map.put("item_code", "marytest_0088");
		map.put("sku_code", "marytest_001");
		map.put("uniqueCode", "201609231042XX22133XXNXN12N");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getStockTransfers() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.transfer.get");
		map.put("page_no", "1");
		map.put("page_size", "10");
		// map.put("code", "WTO67042286450");
		map.put("start_date", "2017-01-01 00:00:00");
		map.put("end_date", "2017-06-06 00:00:00");
		// map.put("start_operation","2017-01-01 00:00:00");
		// map.put("end_operation","2017-06-06 00:00:00");
		map.put("status_in", "1");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void getCustomerpayment() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.customerpayment.get");
		map.put("page_no", "1");
		map.put("page_size", "1");
		map.put("code", "1458622073");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}
	public static void addOrder() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.add");
		map.put("order_type_code", "Sales");
		map.put("warehouse_code", "036");//036物流仓，037门店自提仓
		map.put("shop_code", "tmall_nacola");
		map.put("cod_fee", "0.00");
		map.put("cod", "false");
		map.put("platform_code", "VR20181003316557");
		map.put("deal_datetime", "2018-10-03 16:52:13");
		map.put("pay_datetime", "2018-11-01 16:37:18");
		map.put("express_code", "YUNDA");
		map.put("vip_code", "test201705281925");
		map.put("extend_memo", "");
		List<Map<String, Object>> details = new ArrayList<Map<String, Object>>();
		Map<String, Object> detail = new LinkedHashMap<String, Object>();
		detail.put("oid", "VR20181003316557");
		detail.put("item_code", "12312");
		detail.put("price", "0.01");
		detail.put("qty", "1");
		details.add(detail);
		map.put("details", details);
		List<Map<String, Object>> payments = new ArrayList<Map<String, Object>>();
		Map<String, Object> payment = new LinkedHashMap<String, Object>();
		payment.put("pay_type_code", "weixin");
		payment.put("payment", "0.01");
		payment.put("pay_code", "VR201810033165576626");
		payment.put("paytime", Long.valueOf("1541378686101"));
		payments.add(payment);
		map.put("payments", payments);
		map.put("receiver_name", "糖中央");
		map.put("receiver_mobile", "15240035248");
		map.put("receiver_address", "我耳机蓉儿我就认为");
		map.put("receiver_province", "安徽省");
		map.put("receiver_city", "合肥市");
		map.put("receiver_district", "蜀山区");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		System.out.println(requestContent);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addCompletedOrder() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.addCompleted");
		map.put("order_type_code", "Sales");
		map.put("warehouse_code", "001");
		map.put("shop_code", "001");
		map.put("cod", false);
		map.put("platform_code", System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("deal_datetime", sdf.format(System.currentTimeMillis()));
		map.put("pay_datetime", sdf.format(System.currentTimeMillis()));
		map.put("post_fee", "20");
		map.put("express_code", "STO");
		map.put("vip_code", "test201607212032");
		map.put("buyer_memo", "buyer_memo");
		// map.put("seller_memo", "seller_memo+%$#@!~^'late");
		// map.put("seller_memo_late", "");
		map.put("receiver_name", "张三");
		map.put("receiver_phone", "021-8989898989");
		map.put("receiver_mobile", "13112141211");
		map.put("receiver_address", "上海 上海市 宝山区 富联路187号");
		List<Map<String, Object>> details = new ArrayList<Map<String, Object>>();
		Map<String, Object> detail = new LinkedHashMap<String, Object>();
		detail.put("oid", System.currentTimeMillis() + "01");
		detail.put("item_code", "ECS000032");
		// detail.put("sku_code", "3");
		detail.put("price", "360");
		detail.put("qty", "1");
		detail.put("refund", 0);
		details.add(detail);
		detail = new LinkedHashMap<String, Object>();
		detail.put("oid", System.currentTimeMillis() + "02");
		detail.put("item_code", "227659910520");
		// detail.put("sku_code", "4");
		detail.put("price", "121");
		detail.put("qty", "3");
		detail.put("refund", 0);
		details.add(detail);
		map.put("details", details);
		List<Map<String, Object>> payments = new ArrayList<Map<String, Object>>();
		Map<String, Object> payment = new LinkedHashMap<String, Object>();
		payment.put("pay_type_code", "zhifubao");
		payment.put("payment", "33.00");
		payment.put("account", "");
		payment.put("pay_code", "2015012171620152458");
		payments.add(payment);
		map.put("payments", payments);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addGoods() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.item.add");
		long code_temp = System.currentTimeMillis();
		map.put("code", code_temp);
		map.put("name", code_temp + "_mc");
		map.put("simple_name", code_temp + "_jc");
		// map.put("category_code", "001");
		// map.put("supplier_code", "001");
		// map.put("stock_status_code", "01");
		// map.put("weight", "1");
		map.put("unit_code", "UOM034");
		// map.put("sales_point", "10.00");
		// map.put("package_point", "15.00");
		// map.put("purchase_price", "10.00");
		map.put("sales_price", 0);
		// map.put("agent_price", "12.00");
		map.put("cost_price", 0);
		// map.put("supplier_outer_id", "123141413413");
		// map.put("note", "thisisnote");
		List<Map<String, Object>> skus = new ArrayList<Map<String, Object>>();
		// Map<String, Object> sku = new LinkedHashMap<String, Object>();
		// sku.put("sku_code", code_temp + "001");
		// sku.put("sku_name", "规格1");
		// sku.put("sku_weight", "500");
		// sku.put("sku_purchase_price", "10.00");
		// sku.put("sku_sales_price", "11.00");
		// sku.put("sku_agent_price", "12.00");
		// sku.put("sku_cost_price", "13.00");
		// sku.put("sku_package_point", "1.00");
		// sku.put("sku_sales_point", "2.00");
		// sku.put("sku_note", "thisisskunote");
		// skus.add(sku);
		// sku = new LinkedHashMap<String, Object>();
		// sku.put("sku_code", code_temp + "002");
		// sku.put("sku_name", "规格2");
		// sku.put("sku_weight", "1000");
		// sku.put("sku_purchase_price", "110.00");
		// sku.put("sku_sales_price", "111.00");
		// sku.put("sku_agent_price", "112.00");
		// sku.put("sku_cost_price", "113.00");
		// sku.put("sku_package_point", "11.00");
		// sku.put("sku_sales_point", "12.00");
		// sku.put("sku_note", "thisisskunote");
		// skus.add(sku);
		// sku = new LinkedHashMap<String, Object>();
		// sku.put("sku_code", code_temp + "002");
		// sku.put("sku_name", "规格3");
		// sku.put("sku_weight", "1000");
		// sku.put("sku_purchase_price", "110.00");
		// sku.put("sku_sales_price", "111.00");
		// sku.put("sku_agent_price", "112.00");
		// sku.put("sku_cost_price", "113.00");
		// sku.put("sku_package_point", "11.00");
		// sku.put("sku_sales_point", "12.00");
		// sku.put("sku_note", "thisisskunote");
		// skus.add(sku);
		map.put("skus", skus);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addTradeReturnCompleted() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.return.addCompleted");
		map.put("code", "SO3291760");
		map.put("vip_code", "Today");
		map.put("shop_code", "001");
		// map.put("platform_code", "1111111113131131");
		map.put("warehouse_in_code", "001");
		// map.put("warehouse_out_code", "002");
		map.put("traderefund_type_code", "001");
		// map.put("refundId", "78987713134413");
		// map.put("express_id", "YTO"); // 有问题
		// map.put("express_code", "13141231313"); // 有问题
		map.put("note", "thisistrade_return");
		map.put("receiver_name", "张三");
		map.put("receiver_address", "北京 北京市 东城区 ABCDFGEE");
		map.put("receiver_mobile", "18121189999");
		map.put("receiver_phone", "021-121313131");
		map.put("receiver_zip", "223300");
		List<Map<String, Object>> skus = new ArrayList<Map<String, Object>>();
		Map<String, Object> sku = new LinkedHashMap<String, Object>();
		sku.put("item_code", "AL00111111111111111111103");
		sku.put("sku_code", "123211");
		sku.put("qty", 10);
		sku.put("price", 12.05);
		sku.put("note", "good1");
		skus.add(sku);
		// sku = new LinkedHashMap<String, Object>();
		// sku.put("item_code", "1434445157919");
		// sku.put("sku_code", "143444515791901");
		// sku.put("qty", 5);
		// sku.put("price", 12.05);
		// sku.put("note", "good1");
		// skus.add(sku);
		map.put("trade_return_order_detail", skus);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addTradeReturn() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.return.add");
		map.put("type_code", "001");
		map.put("vip_code", "1231223424242");
		map.put("shop_code", "奇思种子");
		map.put("note", "thisistrade_return");
		map.put("trade_code", "SO67704329316");
		map.put("trade_platform_code", "20151009012113411");
		List<Map<String, Object>> item_detail = new ArrayList<Map<String, Object>>();
		Map<String, Object> sku = new LinkedHashMap<String, Object>();
		// sku.put("barcode", "6921314113412");
		sku.put("item_code", "20170522008");
		// sku.put("sku_code", "DDDDDDDDDDDD1");
		sku.put("qty", 25);
		sku.put("originPrice", "21.00");
		sku.put("price", "120.00");
		sku.put("note", "good1");
		item_detail.add(sku);
		// sku = new LinkedHashMap<String, Object>();
		// sku.put("item_code", "1434445157919");
		// sku.put("sku_code", "143444515791901");
		// sku.put("qty", 5);
		// sku.put("price", 12.05);
		// sku.put("note", "good1");
		// skus.add(sku);
		map.put("item_detail", item_detail);

		List<Map<String, Object>> refund_detail = new ArrayList<Map<String, Object>>();

		// Map<String, Object> detail1 = new LinkedHashMap<String, Object>();
		// detail1.put("pay_type_code", "zhifubao");
		// detail1.put("payment", "55");
		// detail1.put("pay_time", "2017-05-24 11:22:00");
		// detail1.put("account", "sf123141414144");
		// refund_detail.add(detail1);
		//
		// detail1 = new LinkedHashMap<String, Object>();
		// detail1.put("pay_type_code", "weixinn");
		// detail1.put("payment", "55");
		// detail1.put("pay_time", "2017-05-24 11:22:00");
		// detail1.put("account", "sf123141414144");
		// refund_detail.add(detail1);

		map.put("refund_detail", refund_detail);

		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addAdjust() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.adjust.add");
		map.put("warehouse_code", "0001");
		map.put("sourceOrderCode", "201607121101191210611");
		// map.put("order_type", "006");
		map.put("note", "001");
		map.put("operator", "xiaozhang");
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Map<String, Object> goods = new LinkedHashMap<String, Object>();
		goods.put("item_code", "4976652004917");
		// goods.put("sku_code", "SP00000011101");
		goods.put("qty", 131);
		goods.put("note", "sku note");
		detailList.add(goods);
		goods = new LinkedHashMap<String, Object>();
		goods.put("item_code", "1.03.0001");
		goods.put("sku_code", "1");
		goods.put("qty", 55);
		goods.put("note", "sku note");
		detailList.add(goods);
		map.put("detail_list", detailList);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addTransfer() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.transfer.add");
		map.put("warehouse_out_code", "CK003");// demo:CK003;
		map.put("warehouse_in_code", "CK001");// demo:CK001;
		// map.put("order_type", "002");//demo:001|002;
		map.put("note", "调拨单备注");
		// map.put("operator", "MMMM");
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Map<String, Object> goods = new LinkedHashMap<String, Object>();
		goods.put("item_code", "20170528002");// demo:20170528002;
		// goods.put("sku_code", "VOP709709221114");
		goods.put("qty", 10);
		goods.put("note", "sku note");
		detailList.add(goods);
		// goods = new LinkedHashMap<String, Object>();
		// goods.put("item_code", "201704071806");
		// // goods.put("sku_code", "VOP709709221113");
		// goods.put("qty", 60);
		// goods.put("note", "sku note");
		// detailList.add(goods);
		map.put("detail_list", detailList);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void outTransfer() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.transfer.out");
		map.put("code", "WTO68035311956");
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Map<String, Object> goods = new LinkedHashMap<String, Object>();
		goods.put("item_code", "X4JZR3130S0302");
		// goods.put("sku_code", "abc123");
		goods.put("qty", 8);
		goods.put("note", "sku note");
		detailList.add(goods);
		// goods = new LinkedHashMap<String, Object>();
		// goods.put("item_code", "VOP709709221113");
		// goods.put("sku_code", "VOP709709221113");
		// goods.put("qty", 60);
		// goods.put("note", "sku note");
		// detailList.add(goods);
		map.put("details", detailList);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void inTransfer() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.transfer.in");
		map.put("code", "WTO63585384532");
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Map<String, Object> goods = new LinkedHashMap<String, Object>();
		goods.put("item_code", "201704111047");
		// goods.put("sku_code", "01");
		goods.put("qty", 100);
		goods.put("note", "sku note");
		detailList.add(goods);
		// goods = new LinkedHashMap<String, Object>();
		// goods.put("item_code", "VOP709709221113");
		// goods.put("sku_code", "VOP709709221113");
		// goods.put("qty", 80);
		// goods.put("note", "sku note");
		// detailList.add(goods);
		map.put("details", detailList);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void deleteGoods() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.item.delete");
		// map.put("id", "17391916228");
		map.put("code", "DMD230312");
		map.put("operater", "MM");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void deleteSku() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.item.sku.delete");
		map.put("item_id", "13297280021");
		// map.put("id", "1739213845111");
		map.put("code", "XXXXXXXXXX01");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addInventory() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.stock.count.add");
		// map.put("type_code", "01"); // demo:111
		map.put("warehouse_code", "00006"); // v1:00007,demo:CK003;
		// map.put("note", "test inventory..");
		List<Map<String, Object>> details = new ArrayList<Map<String, Object>>();

		Map<String, Object> item = new LinkedHashMap<String, Object>();
		item.put("item_code", "2017080218001"); // demo:20170528002;
		item.put("qty", "24");
		// item.put("uniqueCode", "3512413141");
		item.put("batchNumber", "20170802191701");
		item.put("manufacturingDate", "2017-03-15 00:00:00");
		item.put("shelfLife", 0);
		item.put("stockDate", "2017-03-15 00:00:00");
		item.put("note", "test item...");
		details.add(item);

		item = new LinkedHashMap<String, Object>();
		item.put("item_code", "lxd20170052"); // demo:20170528002;
		item.put("qty", "50");
		// item.put("uniqueCode", "3512413141");
		// item.put("batchNumber", "liubing1234561");
		// item.put("manufacturingDate", "2016-06-20 10:15:00");
		// item.put("shelfLife", 11);
		// item.put("stockDate", "2016-08-12 10:15:00");
		// item.put("note", "test item...");
		details.add(item);

		item = new LinkedHashMap<String, Object>();
		item.put("item_code", "lxd20170052"); // demo:20170528002;
		item.put("qty", "35");
		// item.put("uniqueCode", "3512413141");
		// item.put("batchNumber", "liubing1234561");
		// item.put("manufacturingDate", "2016-06-20 10:15:00");
		// item.put("shelfLife", 11);
		// item.put("stockDate", "2016-08-12 10:15:00");
		// item.put("note", "test item...");
		details.add(item);

		// item = new LinkedHashMap<String, Object>();
		// item.put("item_code", "PC2222222222");
		// item.put("sku_code", "PC222222222202");
		// item.put("qty", "11");
		// item.put("batchNumber", "20160913151101");
		// item.put("manufacturingDate", "2015-12-11:00:00");
		// item.put("stockDate", "2015-12-11:00:00");
		// item.put("note", "test item...");
		// details.add(item);

		map.put("details", details);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addPurchase() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.purchase.add");
		map.put("warehouse_code", "001");
		map.put("supplier_code", "001");
		// map.put("order_type", "002");
		// map.put("plan_date", "2016-08-10 12:00:00");
		map.put("note", "test..");
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new LinkedHashMap<String, Object>();
		item.put("barcode", "878712347");
		item.put("qty", "55");
		item.put("price", "50000");
		item.put("note", "test item...");
		detailList.add(item);
		item = new LinkedHashMap<String, Object>();
		// item.put("barcode", "yidao4gg001");
		item.put("barcode", "sp002gg003");
		// item.put("sku_code", "SP0002011");
		item.put("qty", "20");
		item.put("price", "201");
		item.put("note", "test item...");
		detailList.add(item);
		map.put("detail_list", detailList);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addPurchaseArrive() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.purchase.arrive.add");
		map.put("warehouse_code", "zbsc001");
		map.put("supplier_code", "123");
		// map.put("order_type", "002");
		map.put("purchase_code", "PO61652649738");
		map.put("note", "test..");
		List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new LinkedHashMap<String, Object>();
		item.put("item_code", "201703201008");
		// item.put("sku_code", "201703011030");
		item.put("qty", "15");
		item.put("price", "500");
		item.put("note", "test item...");
		detailList.add(item);
		// item = new LinkedHashMap<String, Object>();
		// item.put("item_code", "SP0002");
		// item.put("sku_code", "SP0002011");
		// item.put("qty", "20");
		// item.put("note", "test item...");
		// detailList.add(item);
		map.put("detail_list", detailList);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void updateGoods() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.item.update");
		// map.put("id", "64747241694");
		map.put("code", "11300060");
		// map.put("name", "change_");
		// map.put("simple_name", "change_...");
		// map.put("category_code", "02002");
		// map.put("supplier_code", "002");
		// map.put("stock_status_code", "02");
		// map.put("weight", "6000");
		// map.put("unit_code", "002");
		// map.put("sales_point", "60");
		// map.put("package_point", "61");
		// map.put("purchase_price", "160");
		// map.put("sales_price", "161");
		// map.put("agent_price", "162");
		// map.put("cost_price", "163");
		// map.put("note", "test...");
		List<Map<String, Object>> skus = new ArrayList<Map<String, Object>>();
		Map<String, Object> sku = new LinkedHashMap<String, Object>();
		// sku.put("sku_id", "64747241695");
		sku.put("sku_code", "6954833158381");
		sku.put("sku_name", "265_38");
		// sku.put("sku_weight", "70800");
		// sku.put("sku_purchase_price", "71");
		// sku.put("sku_sales_price", "72");
		// sku.put("sku_agent_price", "73");
		// sku.put("sku_cost_price", "74");
		// sku.put("sku_package_point", "7");
		// sku.put("sku_sales_point", "8");
		sku.put("sku_note", "haha");
		skus.add(sku);
		map.put("skus", skus);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addSysSubject() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.tenant.sys.subject.add");
		map.put("code", "XXXXXXXXXX012111");
		map.put("name", "XXXXXXXXXX0121");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void addSku() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.item.sku.add");
		map.put("item_id", "13297280021");
		map.put("code", "XXXXXXXXXX01");
		map.put("name", "XXXXXXXXXX01");
		// map.put("stock_status_code", "01");
		map.put("weight", "1000");
		map.put("sales_point", "10");
		map.put("package_point", "11");
		map.put("purchase_price", "160");
		map.put("sales_price", "161");
		map.put("agent_price", "162");
		map.put("cost_price", "163");
		map.put("note", "1...");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void updateSku() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.item.sku.update");
		map.put("item_id", "17391916228");
		// map.put("id", "17392138451");
		map.put("code", "8988776666666");
		map.put("name", "update1111111..");
		map.put("stock_status_code", "02");
		map.put("weight", "7000");
		map.put("sales_point", "70");
		map.put("package_point", "71");
		map.put("purchase_price", "760");
		map.put("sales_price", "761");
		map.put("agent_price", "762");
		map.put("cost_price", "763");
		map.put("note", "7test...");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void updateOrderMemo() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.memo.update");
		map.put("tid", "1433831802270");
		map.put("memo", "12222222111111111111");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void updateOrderTag() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.tag.update");
		map.put("code", "SO63530365698");
		// map.put("tag_code", "0001");
		map.put("tag_name", "请求");
		// map.put("operater", "MM");
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

	public static void updateOrderRefund() throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("appkey", ConfigInfo.APPKEY);
		map.put("sessionkey", ConfigInfo.SESSIONKEY);
		map.put("method", "gy.erp.trade.refund.update");
		map.put("tid", "1500902228675");
		map.put("oid", "150090222870801");
		map.put("refund_state", 1);
		String sign = SignUtil.sign(gson.toJson(map), ConfigInfo.SECRET);
		map.put("sign", sign);
		String requestContent = gson.toJson(map);
		HttpUtil.sendPost(ConfigInfo.URL, requestContent);
	}

}