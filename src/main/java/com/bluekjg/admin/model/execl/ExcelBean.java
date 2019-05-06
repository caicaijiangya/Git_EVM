package com.bluekjg.admin.model.execl;

import java.util.HashMap;
import java.util.Map;

public class ExcelBean {
	/**
	 * 订单导出字段
	 */
	@SuppressWarnings("serial")
	public static Map<String,String> EXCEL_ORDER_MAP = new HashMap<String, String>(){{  
	      put("brandName","品牌");     
	      put("storeName","订单所属门店");  
	      put("writeOffName","核销人");  
	      put("writeOffCode","核销导购员工编码");  
	      put("createTime","下单时间");  
	      put("orderType","订单类型");  
	      put("orderStatus","订单状态");  
	      put("orderNo","订单号");  
	      put("transNo","商户交易号");  
	      put("goodsNum","购买总数量");  
	      put("goodsPrice","订单总标准金额");  
	      put("totalBalances","订单总市场价");
	      put("freight","运费");
	      put("discountPrice","优惠金额");
	      put("payBalances","实付总金额");
	      put("refundBalances","退款金额");
	      put("refundType","退款类型");
	      put("zp","赠品");
	      put("zpje","赠品金额");
	      put("couponName","优惠券名称");
	      put("yhqqh","优惠券券号");
	      put("userName","下单人");
	      put("newOld","新老客户");
	      put("takeStyle","取货方式");
	      put("payMoneyStyle","支付方式");
	      put("expressName","物流公司");
	      put("expressNo","物流单号");
	      put("mobileNo","用户手机号码");
	      put("detailAddress","用户收货地址");
	      put("discountAmount","活动优惠金额");
	      put("minCreatedTime","第一次购物时间");
	      put("maxCreatedTime","最近一次购物时间");
	      put("orderNum","总的购物次数");
	}};  
	/**
	 * 订单明细导出字段
	 */
	@SuppressWarnings("serial")
	public static Map<String,String> EXCEL_ORDER_DETAIL_MAP = new HashMap<String, String>(){{
		put("brandName","品牌");
		put("storeName","订单所属门店");
		put("writeOffName","核销人");
		put("writeOffCode","核销导购员工编码");
		put("createTime","下单时间");
		put("payTime","支付时间");
		put("orderStatus","订单状态");
		put("orderType","订单类型");
		put("payMoneyStyle","支付方式");
		put("orderNo","订单号");
		put("transNo","商户交易号");
		put("sku","SKU");
		put("goodsName","商品名称");
		put("isGift","是否赠品");
		put("goodsSpec","规格");
		put("goodsNum","数量");
		put("goodsPrice","商品单价");
		put("goodsPriceO","商品市场价");
		put("discountPrice","优惠金额");
		put("transPrice","商品实付金额");
		put("contactName","收货人");
		put("contactMobileNo","联系电话");
		put("detailAddress","收货地址");
		put("province","收货省份");
		put("city","收货城市");
		put("isCollage","拼团状态");
		put("nickName","会员昵称");
		put("userName","会员姓名");
		put("hykh","会员卡号");
		put("mobileNo","会员手机号");
		put("newOld","新老客户");
		put("minCreatedTime","第一次购物时间");
	    put("maxCreatedTime","最近一次购物时间");
	    put("orderNum","总的购物次数");
	}};
	/**
	 * 物流订单导出字段
	 */
	@SuppressWarnings("serial")
	public static Map<String,String> EXCEL_GUANYI_MAP = new HashMap<String, String>(){{
		put("orderNo","订单编号");
		put("transNo","商户交易号");
		put("createTime","订单时间");
		put("orderStatus","订单状态");
		put("userName","买家会员");
		put("balances","支付金额");
		put("goodsName","商品名称");
		put("goodsCode","商品代码");
		put("isGift","是否赠品");
		put("goodsCode","规格代码");
		put("specName","规格名称");
		put("goodsNum","数量");
		put("goodsPrice","单价");
		put("spbz","商品备注");
		put("freight","运费");
		put("orderDesc","买家留言");
		put("name","收货人");
		put("mobileNo","联系电话");
		put("mobileNo","联系手机");
		put("addressDetail","收货地址");
		put("s","省");
		put("s1","市");
		put("q","区");
		put("yb","邮编");
		put("ddcjsj","订单创建时间");
		put("ddfksj","订单付款时间");
		put("fhsj","发货时间");
		put("wldh","物流单号");
		put("wlgs","物流公司");
		put("mjbz","卖家备注");
		put("fpzl","发票种类");
		put("fplx","发票类型");
		put("fptt","发票抬头");
		put("nsrsbh","纳税人识别号");
		put("khh","开户行");
		put("zh","账号");
		put("dz","地址");
		put("dh","电话");
		put("sfsjdd","是否手机订单");
		put("sfdhfk","是否到货付款");
		put("zffs","支付方式");
		put("zfjyh","支付交易号");
		put("zsxm","真实姓名");
		put("ckmc","仓库名称");
		put("yjfhsj","预计发货时间");
		put("yjsdsj","预计送达时间");
		put("ddlx","订单类型");
		put("sffxsdd","是否分销商订单");
	}};
	/**
	 * 门店订单导出字段
	 */
	@SuppressWarnings("serial")
	public static Map<String,String> EXCEL_JINDIE_MAP = new HashMap<String, String>(){{
		put("createTime","日期");
		put("ghdw","购货单位");
		put("xsfs","销售方式");
		put("orderNo","编号");
		put("bz","币种");
		put("storeName","交货地点");
		put("bm","部门");
		put("ywy","业务员");
		put("jsfs","结算方式");
		put("hl","汇率");
		put("LastModifiedTime","结算日期");
		put("goodsCode","产品代码");
		put("dw","单位");
		put("goodsNum","数量");
		put("goodsPrice","含税单价");
		put("LastModifiedTime","建议交货日期");
		put("bz","备注");
		put("sl","税率(%)");
		put("LastModifiedTime","交货日期");
	}};
}