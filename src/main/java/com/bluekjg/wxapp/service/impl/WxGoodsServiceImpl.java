package com.bluekjg.wxapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.wxapp.mapper.WxDictMapper;
import com.bluekjg.wxapp.mapper.WxGoodsMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxActivityFull;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.model.WxDict;
import com.bluekjg.wxapp.model.WxGoods;
import com.bluekjg.wxapp.model.WxGoodsFreight;
import com.bluekjg.wxapp.service.IWxGoodsService;
import com.bluekjg.wxapp.utils.DictUtil;

/**
 * @description：商品数据
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
public class WxGoodsServiceImpl extends ServiceImpl<WxGoodsMapper, WxGoods>implements IWxGoodsService {

	protected Logger logger = LoggerFactory.getLogger(WxGoodsServiceImpl.class);

	@Autowired
	private WxGoodsMapper goodsMapper;
	@Autowired
	private WxDictMapper dictMapper;

	@Override
	public WxGoods queryGoodsDetail(PagingDto page) {
		// TODO Auto-generated method stub
		return goodsMapper.queryGoodsDetail(page);
	}

	@Override
	public List<WxCollageGoods> queryCollageGoodsList(Integer activityId,Integer goodsId) {
		// TODO Auto-generated method stub
		return goodsMapper.queryCollageGoodsList(activityId,goodsId);
	}

	@Override
	public List<WxGoods> queryOrderGoods(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		return goodsMapper.queryOrderGoods(list);
	}

	@Override
	public List<WxGoods> queryStoreGoods(String storeId, List<Map<String,Object>> list) {
		// TODO Auto-generated method stub
		return goodsMapper.queryStoreGoods(storeId, list);
	}

	@Override
	public Integer queryCollect(String openId, String goodsId) {
		// TODO Auto-generated method stub
		return goodsMapper.queryCollect(openId, goodsId);
	}

	@Override
	public void insertCollect(String openId, String goodsId) {
		// TODO Auto-generated method stub
		goodsMapper.insertCollect(openId, goodsId);
	}

	@Override
	public void deleteCollect(String openId, String goodsId) {
		// TODO Auto-generated method stub
		goodsMapper.deleteCollect(openId, goodsId);
	}

	@Override
	public void insertShoppingcart(WxGoods goods) {
		// TODO Auto-generated method stub
		goodsMapper.insertShoppingcart(goods);
	}

	@Override
	public Integer queryShoppingcart(String openId, String goodsId,String specId) {
		// TODO Auto-generated method stub
		return goodsMapper.queryShoppingcart(openId, goodsId,specId);
	}

	@Override
	public void updateShoppingcart(WxGoods goods) {
		// TODO Auto-generated method stub
		goodsMapper.updateShoppingcart(goods);
	}
	
	@Override
	public void deleteShoppingcart(Integer id) {
		// TODO Auto-generated method stub
		goodsMapper.deleteShoppingcartById(id);
	}

	@Override
	public List<WxGoods> queryMyCartGoodsList(PagingDto page) {
		// TODO Auto-generated method stub
		return goodsMapper.queryMyCartGoodsList(page);
	}

	@Override
	public String queryGoodsImages(Integer goodsId, Integer type,Integer smallType) {
		// TODO Auto-generated method stub
		return goodsMapper.queryGoodsImages(goodsId, type,smallType);
	}

	@Override
	public List<WxGoods> selectCollectGoods(PagingDto page) {
		// TODO Auto-generated method stub
		return goodsMapper.selectCollectGoods(page);
	}
	
	
	/**
     * 计算活动时间
     * @param startTime
     * @param endTime
     * @return
     */
	@Override
    public String activityDate(String startTime,String endTime) {
    	Date start = DateUtil.parseStrToDate(startTime, DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
    	Date end = DateUtil.parseStrToDate(endTime, DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
    	long seconds = (end.getTime() - start.getTime()) / 1000;
    	long min = 60;
    	long hours = min * 60;
    	long day = hours * 24;
    	long r_d = 0;//天
    	long r_h = 0;//时
    	long r_m = 0;//分
    	if (seconds / day >= 1) {
    		r_d = seconds / day;
    		seconds = seconds % day;
    	}
    	
    	if(seconds / hours >= 1) {
    		r_h = seconds / hours;
    		seconds = seconds % hours;
    	}
    	r_m = Math.round((Double.valueOf(seconds) / Double.valueOf(min)));
    	String r = "";
    	if(seconds > 0) {
    		if(r_d > 0) {
        		r = r + r_d+"天";
        	}
        	if(r_h > 0) {
        		r = r + r_h+"小时";
        	}
        	if(r_m > 0) {
        		r = r + r_m+"分钟";
        	}
    	}else {
    		r = r + r_m+"分钟";
    	}
    	
    	return r;
    }

	@Override
	public WxGoodsFreight queryGoodsFreight(Integer province) {
		WxGoodsFreight freight = goodsMapper.queryGoodsFreight(province);
		WxDict dict = dictMapper.queryDictByCode(DictUtil.EVM_FREIGHT);
		if(freight == null || freight.getMoney() == null || dict == null || dict.getDictValue() == null) {
			return null;
		}
		freight.setFullMoney(Double.valueOf(dict.getDictValue()));
		return freight;
	}

	@Override
	public List<WxGoods> queryGoodsClassifyList(PagingDto page) {
		// TODO Auto-generated method stub
		return goodsMapper.queryGoodsClassifyList(page);
	}

	@Override
	public WxActivityGoods queryActivityGoodsLadder(Integer storeId,Integer id, Integer num) {
		// TODO Auto-generated method stub
		return goodsMapper.queryActivityGoodsLadder(storeId,id, num);
	}

	@Override
	public WxActivityGoods queryActivityGoods(Integer storeId, Integer goodsId,Integer isType) {
		// TODO Auto-generated method stub
		return goodsMapper.queryActivityGoods(storeId, goodsId,isType);
	}

	@Override
	public List<WxActivityFull> queryActivityFullPre(String storeId, List<Map<String,Object>> jsonList) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
    	map.put("storeId", storeId);
    	map.put("list", jsonList);
    	List<WxActivityFull> fullList = goodsMapper.queryActivityFullPre(map);
		List<WxActivityFull> retList = null;
    	if(fullList != null && fullList.size() > 0 && fullList.get(0).getIsOverlay() == 0) {
    		retList = new ArrayList<WxActivityFull>();
    		for(WxActivityFull full:fullList) {
    			boolean bool = true;
    			if(retList != null && retList.size() > 0) {
    				for(WxActivityFull ret:retList) {
    					if(full.getFullPrice().intValue() != ret.getFullPrice().intValue()) {
    						bool = false;
    					}
    				}
    			}
    			if(bool) {
					retList.add(full);
				}
    		}
    		fullList = retList;
    	}
    	return fullList;
	}

	@Override
	public List<WxActivityFull> queryActivityFullGift(String storeId, List<Map<String,Object>> jsonList) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
    	map.put("storeId", storeId);
    	map.put("list", jsonList);
    	List<WxActivityFull> fullList = goodsMapper.queryActivityFullGift(map);
    	List<WxActivityFull> retList = null;
    	if(fullList != null && fullList.size() > 0 && fullList.get(0).getIsOverlay() == 0) {
    		retList = new ArrayList<WxActivityFull>();
    		for(WxActivityFull full:fullList) {
    			boolean bool = true;
    			if(retList != null && retList.size() > 0) {
    				for(WxActivityFull ret:retList) {
    					if(full.getFullPrice().intValue() != ret.getFullPrice().intValue()) {
    						bool = false;
    					}
    				}
    			}
    			if(bool) {
					retList.add(full);
				}
    		}
    		fullList = retList;
    	}
		return fullList;
	}

	@Override
	public List<WxActivityFull> queryActivityFullDiscount(String storeId, List<Map<String, Object>> jsonList) {
		Map<String,Object> map = new HashMap<String, Object>();
    	map.put("storeId", storeId);
    	map.put("list", jsonList);
    	List<WxActivityFull> fullList = goodsMapper.queryActivityFullDiscount(map);
    	List<WxActivityFull> retList = null;
    	if(fullList != null && fullList.size() > 0) {
    		retList = new ArrayList<WxActivityFull>();
    		for(WxActivityFull full:fullList) {
    			boolean bool = true;
    			if(retList != null && retList.size() > 0) {
    				for(WxActivityFull ret:retList) {
    					if(full.getGoodsId().intValue() == ret.getGoodsId().intValue() && full.getFullPrice().intValue() != ret.getFullPrice().intValue()) {
    						bool = false;
    					}
    				}
    			}
    			if(bool) {
					retList.add(full);
				}
    		}
    		fullList = retList;
    	}
		return fullList;
	}

	@Override
	public List<String> queryActivityLabelList(Integer id) {
		// TODO Auto-generated method stub
		return goodsMapper.queryActivityLabelList(id);
	}

	@Override
	public void updateShoppingcartNum(WxGoods goods) {
		// TODO Auto-generated method stub
		goodsMapper.updateShoppingcartNum(goods);
	}
	
}