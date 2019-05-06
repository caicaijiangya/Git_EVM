package com.bluekjg.core.commons.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 实体对象转成Map
 * @author Administrator
 *
 */
public class MapTrunPojo {
	public static Logger logger = LogManager.getLogger(MapTrunPojo.class.getName());
	/**
	     * 实体对象转成Map
	 * @param obj 实体对象
	 * @return
	 */
	public static Map<String, Object> object2Map(Object obj) {
	    Map<String, Object> map = new HashMap<>();
	    if (obj == null) {
	        return map;
	    }
	    Class clazz = obj.getClass();
	    Field[] fields = clazz.getDeclaredFields();
	    try {
	        for (Field field : fields) {
	            field.setAccessible(true);
	            map.put(field.getName(), field.get(obj));
	        }
	    } catch (Exception e) {
	        logger.error(e.getMessage(),e);
	    }
	    return map;
	}
	/**
	 * Map转成实体对象
	 * @param map map实体对象包含属性
	 * @param clazz 实体对象类型
	 * @return
	 */
	public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
	    if (map == null) {
	        return null;
	    }
	    Object obj = null;
	    try {
	        obj = clazz.newInstance();
	
	
	        Field[] fields = obj.getClass().getDeclaredFields();
	        for (Field field : fields) {
	            int mod = field.getModifiers();
	            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
	                continue;
	            }
	            field.setAccessible(true);
	            field.set(obj, map.get(field.getName()));
	        }
	    } catch (Exception e) {
	        logger.error(e.getMessage(),e);
	    } 
	    return obj;
	}
	
	
	/**
	 * 复制map对象
	 * @explain 将paramsMap中的键值对全部拷贝到resultMap中；
	 * paramsMap中的内容不会影响到resultMap（深拷贝）
	 * @param paramsMap
	 *     被拷贝对象
	 * @param resultMap
	 *     拷贝后的对象
	 */
	public static void mapCopy(Map paramsMap, Map resultMap) {
	    if (resultMap == null) resultMap = new HashMap();
	    if (paramsMap == null) return;

	    Iterator it = paramsMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry entry = (Map.Entry) it.next();
	        Object key = entry.getKey();
	        resultMap.put(key, paramsMap.get(key) != null ? paramsMap.get(key) : "");

	    }
	}
}
