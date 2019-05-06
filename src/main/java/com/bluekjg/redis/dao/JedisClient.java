package com.bluekjg.redis.dao;

public interface JedisClient {
	String get(String key);		//读取数据
	String set(String key, String value);	//向redis中写入数据
	String set(String key, String value,String nxxx);	//获取存储结构是hashMap类型的操作
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);
	long incr(String key);
	long expire(String key, int second);	//设置缓存生存时间
	long ttl(String key);
	long del(String key);	//删除数据
	long hdel(String hkey, String key);
	boolean exists(String key);
}
