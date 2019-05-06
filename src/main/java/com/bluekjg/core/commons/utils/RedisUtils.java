package com.bluekjg.core.commons.utils;

public class RedisUtils {
	//云名片
	public final static String CLOUDCARD_KEY = "CLOUDCARD:";
	
	//名片KEY
	public final static String CARD_KEY = CLOUDCARD_KEY + "CARD:LIST:";
	
	//待删除名片KEY
	public final static String DELETE_COLLECT_KEY = CLOUDCARD_KEY + "COLLECT:DELETE:";
	
	//用户信息KEY
	public final static String CLOUDCARD_USER_KEY = CLOUDCARD_KEY + "USER:";
	//名片模板KEY
	public final static String CARD_MODEL_KEY = CLOUDCARD_KEY + "CARD_MODEL:";
	//名片模板KEY
	public final static String CARD_DEFAULT_PER_KEY = CARD_MODEL_KEY + "PER:";
	public final static String CARD_DEFAULT_RANK_KEY = CARD_MODEL_KEY + "RANK:";
	public final static String CARD_DEFAULT_RANKTYPE_KEY = CARD_MODEL_KEY + "RANKTYPE:";
	//public final static String CARD_DEFAULT_TEMP_KEY = CARD_MODEL_KEY + "TEMP:";
	//我的名片KEY
	public final static String CARD_MY_KEY = CLOUDCARD_KEY + "CARD:MY:";
	//我的收藏KEY
	public final static String CARD_COLLECT_KEY = CLOUDCARD_KEY + "CARD:COLLECT:";
	public final static String CARD_COLLECT_RANK_KEY = CLOUDCARD_KEY + "CARD:COLLECT:RANK:";
	
	//名片实际大小
	public final static String CLOUDCARD_TEMPIMAGE_KEY = CLOUDCARD_KEY + "TEMPIMAGE:";
	//转发标题集合
	public final static String CLOUDCARD_SHARE_TITLES_KEY = CLOUDCARD_KEY + "SHARETITLES";
	//模板分类集合
	public final static String CLOUDCARD_MODELTYPES_KEY = CLOUDCARD_KEY + "MODELTYPES";
	//名片转发集合
	public final static String CLOUDCARD_CARD_SEND_KEY = CLOUDCARD_KEY + "SEND:";
	//名片转发标题集合
	public final static String CLOUDCARD_CARD_SEND_TITLE_KEY = CLOUDCARD_KEY + "SENDTITLE:";
}
