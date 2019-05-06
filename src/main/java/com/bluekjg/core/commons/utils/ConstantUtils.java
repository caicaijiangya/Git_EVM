package com.bluekjg.core.commons.utils;


/**
 * 常量
 * @author tim
 */
public class ConstantUtils {
	
public static final Integer FAIL = 0;  //失败
	
	
	public static final String NO_PARAM = "缺失参数";  //失败消息
	
	public static final Integer FIRST_LEVEL = 1;  //一级分销
	
	public static final Integer SECOND_LEVEL = 2;  //二级分销
	
	public static final Integer THRID_LEVEL = 3;  //三级分销
	
	public static final Integer COMMISSION_ID = 8;  //分佣比例
	
	public static final Integer IS_USE = 1;  //已经启用
	
	public static final Integer DEFAULT_ADDR = 1;  //默认地址
	
	public static final Integer PAGESIZE = 6;
	
	public static final Integer CODE_COUPON_TYPE = 1;  //优惠券类型
	
	public static final Integer CODE_LUCKEY_TYPE = 2; //抽奖商品类型
	
	public static final Integer CODE_ORDER_TYPE = 3; //订单类型
	
	public static final Integer CODE_PRECARD_TYPE = 4; //预售卡类型
	
	//系统初始密码 123456
		public static final String INITIAL_PWD = "e10adc3949ba59abbe56e057f20f883e";
		public static final String REQ_URL = "http://apis.map.qq.com/ws/geocoder/v1/";
		public static final String TXAPI_KEY = "2WDBZ-HL7CP-P4WDI-VHKJ5-5T34S-B7FFS";
		
		public static final String STORE_AREA = "STORE_AREA";			//大区分类
		public static final String CLCONTENT_TYPE = "CLCONTENT_TYPE";	//巡店陈列类目
		public static final String LEAVE_TYPE = "LEAVE_TYPE";			//请假类型
		public static final String BANCI_TYPE = "BA_BANCI";				//班次
		public static final String PAY_TYPE = "PAY_TYPE";				//支付类型
		
		public static final Integer IN_AUDIT = 0;  		//审核中
		public static final Integer PASS = 1;  			//审核不通过
		public static final Integer NO_PASS = 2;  		//审核通过
		
		public static final Integer NO_VERIFTY = 0;  	//认证不通过
		public static final Integer VERIFTY = 1;  	 	//认证通过
		
		public static final Integer DELETE_FLAG = 1;  	//删除标识
		
		public static final Integer SUCCESS = 1;  		//成功标识
		public static final Integer ERROR = 0;  		//失败标识
		public static final String ERROR_MSG = "网络异常!";  //失败消息
		
		public static void main(String[] args) {
			System.out.println(DigestUtils.md5Hex("123456"));
		}
		
		
		//常量：暂无数据
		public static final String NO_DATA = "请求失败,请检查网络是否正常";
		
		//常量：缺失数据
		public static final String NO_PARAMS = "缺失参数,请检查网络是否正常";
		
		//常量：处理数据
		public static final String PROCESS_PARAMS = "操作异常,请检查网络是否正常";

}
