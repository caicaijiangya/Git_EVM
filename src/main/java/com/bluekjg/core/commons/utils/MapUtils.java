package com.bluekjg.core.commons.utils;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.xml.sax.InputSource;

public class MapUtils {
	public static Logger logger = LogManager.getLogger(MapUtils.class.getName());
	/**
	 * 实体对象转成Map
	 *
	 * @param obj 实体对象
	 * @return
	 */
	public static Map<String, Object> object2Map(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
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
	 *
	 * @param map   map实体对象包含属性
	 * @param clazz 实体对象类型
	 * @return
	 */
	public static <T> T map2Object(Map<String, Object> map, Class<T> clazz) {
		if (map == null) {
			return null;
		}
		T obj = null;
		try {
			obj = clazz.newInstance();

			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				String filedTypeName = field.getType().getName();
				if (filedTypeName.equalsIgnoreCase("java.util.date")) {
					String datetimestamp = String.valueOf(map.get(field.getName()));
					if (datetimestamp.equalsIgnoreCase("null")) {
						field.set(obj, null);
					} else {
						field.set(obj, new Date(Long.parseLong(datetimestamp)));
					}
				} else {
					field.set(obj, map.get(field.getName()));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return obj;
	}

	/**
	 * 将Document对象转为Map（String→Document→Map）
	 * 
	 * @param Document
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> xmlStr2Map(String xmlStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Document doc = DocumentHelper.parseText(xmlStr);
			if (doc == null) {
				return map;
			}
			Element root = doc.getRootElement();
			for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
				Element e = (Element) iterator.next();
				// System.out.println(e.getName());
				List list = e.elements();
				if (list.size() > 0) {
					map.put(e.getName(), Ele2Map(e));
				} else {
					map.put(e.getName(), e.getText());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return map;
	}

	/**
	 * 将Element对象转为Map（String→Document→Element→Map）
	 * 
	 * @param Element
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map Ele2Map(Element e) {
		Map map = new HashMap();
		List list = e.elements();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List mapList = new ArrayList();
				if (iter.elements().size() > 0) {
					Map m = Ele2Map(iter);
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					} else {
						map.put(iter.getName(), m);
					}
				} else {
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if (obj.getClass().getName().equals("java.util.ArrayList")) {
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					} else {
						map.put(iter.getName(), iter.getText());// 公共map resultCode=0
					}
				}
			}
		} else {
			map.put(e.getName(), e.getText());
		}
		return map;
	}

}
