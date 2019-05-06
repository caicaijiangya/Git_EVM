package com.bluekjg.redis.key;

/**
 * redis key
 * @author tim
 *
 */
public class RedisKeyUtil {
	//商品详情图
	public final static String GOODS_DETAIL_IMAGE_KEY = "XYH:GOODS:DETAIL:IMAGE";
	//首页轮播图
	public final static String INDEX_ADVERT = "XYH:INDEX:ADVERT";
	//首页商品分类
	public final static String INDEX_GOODS_CATEGORY = "XYH:INDEX:GOODSCATEGORY";
	//首页活动轮播
	public final static String INDEX_ACTIVITY = "XYH:INDEX:ACTIVITY";
	//首页通知消息
	public final static String INDEX_ROLL_MSG = "XYH:INDEX:ROLLMSG";
    //用户分享生成图片
	public final static String USER_SHARE_IMG = "XYH:SHARE:IMG";
	
	//用户分享商品生成图片
    public final static String USER_SHARE_GOODS_IMG = "XYH:SHARE:GOODS:IMG";
    
    //保存免费试用商品列表
    public final static String USER_APPLY_GOODS_ID = "XYH:APPLY:GOODS:ID";
    
    //保存申请商品 图片列表
    public final static String USER_APPLY_GOODS_IMG = "XYH:APPLY:GOODS:IMG";
    
    //保存秒杀商品
    public final static String USER_SECOND_KILL_GOODS_INFO = "XYH:KILL:GOODS:INFO";
    //获取是否已秒杀商品
    public final static String USER_SECOND_KILL_GOODS_OPENID_GOODSID = "XYH:KILL:GOODS:OPENID";
    
    //获取爱豆人员信息
    public final static String AIDOU_INFO = "aidouInfo";
    
    //保存用户信息
    public final static String USER_INFO_SESSION_KEY = "XYH:USER:INFO:SESSIONKEY";
	
    public final static String QR_CODE_KEY = "XYH:CODE";
	
}
