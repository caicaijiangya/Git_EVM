package com.bluekjg.core.commons.scan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bluekjg.admin.mapper.AreaMapper;
import com.bluekjg.core.commons.result.City;
import com.bluekjg.wxapp.utils.RedisUtil;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

@Component
public class CityUtils {
	
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private RedisUtil redisUtil;
	private static final String CITY_DATA = "evm_admin_city_data";
	
	public String getCityLevel() {
		if(redisUtil.exists(CITY_DATA)) {
			return (String) redisUtil.get(CITY_DATA);
		}
		List<Map<String,Object>> result = areaMapper.getCityLevel(0);//查层级
		for(int i=0;i<result.size();i++) {//第二层只能有一个元素,否则有问题
			City one = (City) result.get(i);
			List<City> oneSub = one.getSub();
			for(City two: oneSub) {
				List<City> twoSub = two.getSub();
				List<City> tempList = new ArrayList<>();
				City city = new City();//直辖市
				city.setCode(two.getCode());
				city.setName("市辖区");
				tempList.add(city);//将加入的元素置顶添加
				tempList.addAll(twoSub);
				two.setSub(tempList);
			}
		}
/*		for(Map<String, Object> second: result) {
			List<Map<String,Object>> subSecond = (List<Map<String, Object>>) second.get("sub");
			String code = (String) second.get("code");
			for(Map<String,Object> three: subSecond) {
				List<Map<String,Object>> subThree = (List<Map<String, Object>>) three.get("sub");
				List<Map<String,Object>> tempList= new ArrayList<>();
				Map<String,Object> first = new HashMap<>();//直辖市
				first.put("code", code);
				first.put("name", "直辖市");
				tempList.add(first);
				tempList.addAll(subThree);
				second.put("sub", tempList);
			}
		}*/
    	JsonConfig cfg = new JsonConfig();
    	PropertyFilter filter = new PropertyFilter() {
			@Override
			public boolean apply(Object obj, String fieldName, Object fieldValue) {
				if(fieldValue==null) {
					return true;
				}
				if(fieldValue instanceof List) {
					List<Object> list = (List<Object>) fieldValue;
					if(list.size()==0) {
						return true;
					}
				}
				return false;
			}
		};
		cfg.setJsonPropertyFilter(filter);
    	cfg.setExcludes(new String[] {"id","pid"});
    	String json =JSONArray.fromObject(result,cfg).toString();
		redisUtil.set(CITY_DATA, json);
    	return json;
	}
	
/**
  	public String getCityLevel() {
		List<City> result = areaMapper.getCity();//查平层
		JSONArray json = getCityTree(result,0);
		return json.toString();
	}
	
    public JSONArray getCityTree(List<City> list,Integer pid) throws JSONException{
    	JSONArray json = new JSONArray();
		for (City city : list) {
			JSONObject child = new JSONObject();
			if(pid.equals(city.getPid())){
				child.put("name", city.getName());
				child.put("code",city.getCode());
				child.put("sub",getCityTree(list,city.getId()));
			}
			json.add(child);
		}
		return json;
	}	
*/
}
