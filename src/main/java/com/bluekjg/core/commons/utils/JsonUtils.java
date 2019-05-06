package com.bluekjg.core.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bluekjg.core.commons.scan.JacksonObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by L.cm
 * Date: 2015-25-12 17:57
 */
public final class JsonUtils {
    private JsonUtils() {}

    /**
     * 将对象序列化成json字符串
     * @param object javaBean
     * @return jsonString json字符串
     */
    public static String toJson(Object object) {
        try {
            return getInstance().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json反序列化成对象
     * @param jsonString jsonString
     * @param valueType class
     * @param <T> T 泛型标记
     * @return Bean
     */
    public static <T> T parse(String jsonString, Class<T> valueType) {
        try {
            return getInstance().readValue(jsonString, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 将json反序列化成List实体
     * @param <T>
     * @param jsonString jsonString
     * @param valueType class
     * @param <T> T 泛型标记
     * @return Bean
     */
    public static <T> List<T> parseList(String jsonString, JavaType javaType) {
        try {
            return getInstance().readValue(jsonString, javaType	);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**   
     * 获取泛型的Collection Type  
     * @param collectionClass 泛型的Collection   
    * @param elementClasses 元素类   
    * @return JavaType Java类型   
    * @since 1.0   
    */   
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
	    return getInstance().getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	} 

	private static ObjectMapper getInstance() {
        return JacksonHolder.INSTANCE;
    }

    private static class JacksonHolder {
        private static ObjectMapper INSTANCE = new JacksonObjectMapper();
    }

}
