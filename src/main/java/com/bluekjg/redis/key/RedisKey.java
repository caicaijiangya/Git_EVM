package com.bluekjg.redis.key;

/**
 * 保存key值
 * @author tim
 *
 */
public class RedisKey {
	
	public static final Integer SECOND = 86400;
	
	//根节点名称
	public static final String NEW_RETAIL = "NEW:RETAIL:";
	
	//保存微信 Token对象
	public static final String PARAMETER_TOKEN_WX_STOREID = NEW_RETAIL +"PARAMETER:TOKEN:WX:APPID:";

	//保存小程序 Token对象
	public static final String PARAMETER_TOKEN_XCX_STOREID = NEW_RETAIL +"PARAMETER:TOKEN:XCX:APPID:";
	
	//保存小程序Key值
	public static final String NEW_RETAIL_PARAMETER_APPID = NEW_RETAIL +"PARAMETER:APPID:";
	
	//保存轮播图
	public static final String INDEX_ADVERT_STOREID = NEW_RETAIL + "INDEX:ADVERT:STOREID:";
	
	//保存首页版块
	public static final String INDEX_MODEL_STOREID = NEW_RETAIL + "INDEX:MODEL:STOREID:";
	
	//轮播信息
	public static final String INDEX_MSG_STOREID = NEW_RETAIL + "INDEX:MSG:STOREID:";
	
	//门店信息
	public static final String INDEX_STORE_STOREID = NEW_RETAIL + "INDEX:STORE:STOREID:";
	
	//预售卡购买记录 每人限购一次
	public static final String PRE_CARD_SALE_LOG = NEW_RETAIL +"PRECARD:CARDID:OPENID:";
	
	//预售卡库存
	public static final String PRE_CARD_SALE_STOCK = NEW_RETAIL +"PRECARD:STOCK:CARDID:";
	
	//秒杀商品购买记录 每人限购一次
	public static final String SECOND_KILL_SALE_LOG = NEW_RETAIL +"SECOND:KILL:GOODS:OPENID:";
		
	//秒杀商品库存
	public static final String SECOND_KILL_SALE_STOCK = NEW_RETAIL +"SECOND:KILL:STOCK:GOODSID:";
	
	//砍价成功用户
	public static final String BARGAIN_CHENGONG_PROMPT = NEW_RETAIL +"BARGAIN:PROMPT:";


}
