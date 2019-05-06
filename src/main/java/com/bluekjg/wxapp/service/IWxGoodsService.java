package com.bluekjg.wxapp.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxActivityFull;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.model.WxGoods;
import com.bluekjg.wxapp.model.WxGoodsFreight;


/**
 * @description：商品信息
 * @author：pincui.Tom
 * @date：2018/9/27 14:51
 */
public interface IWxGoodsService extends IService<WxGoods> {
	/**
	 * 查询我的收藏商品
	 * @param page
	 * @return
	 */
	List<WxGoods> selectCollectGoods(PagingDto page);
	/**
	 * 查询商品图片
	 * @param goodsId
	 * @param type
	 * @return
	 */
	String queryGoodsImages(Integer goodsId,Integer type,Integer smallType);
	/**
	 * 查询我的购物车商品
	 * @param page
	 * @return
	 */
	List<WxGoods> queryMyCartGoodsList(PagingDto page);
	/**
	 * 查询商品详情
	 * @param page
	 * @return
	 */
	WxGoods queryGoodsDetail(PagingDto page);
	/**
	 * 订单商品列表
	 * @param ids
	 * @return
	 */
	List<WxGoods> queryOrderGoods(List<Map<String, Object>> list);
	/**
	 * 门店商品列表
	 * @param storeId
	 * @param ids
	 * @return
	 */
	List<WxGoods> queryStoreGoods(String storeId,List<Map<String,Object>> list);
	/**
	 * 查询商品拼团列表
	 * @param id
	 * @return
	 */
	List<WxCollageGoods> queryCollageGoodsList(Integer activityId,Integer goodsId);
	
	/**
	 * 我的收藏
	 * @param openId
	 * @param goodsId
	 * @return
	 */
	Integer queryCollect(String openId,String goodsId);
	void insertCollect(String openId,String goodsId);
	void deleteCollect(String openId,String goodsId);
	/**
	 * 加入购物车
	 * @param goods
	 * @return
	 */
	Integer queryShoppingcart(String openId,String goodsId,String specId);
	void insertShoppingcart(WxGoods goods);
	void updateShoppingcart(WxGoods goods);
	void updateShoppingcartNum(WxGoods goods);
	void deleteShoppingcart(Integer id);
	
	/**
     * 计算活动时间
     * @param startTime
     * @param endTime
     * @return
     */
    public String activityDate(String startTime,String endTime);
    
    
    /**
	 * 商品运费查询
	 * @return
	 */
	WxGoodsFreight queryGoodsFreight(Integer province);
	
	/**
	 * 根据分类查询商品列表
	 * @param page
	 * @return
	 */
	List<WxGoods> queryGoodsClassifyList(PagingDto page);
	WxActivityGoods queryActivityGoods(Integer storeId,Integer goodsId,Integer isType);
	WxActivityGoods queryActivityGoodsLadder(Integer storeId,Integer id,Integer num);
	List<String> queryActivityLabelList(Integer id);
	/**
	 * 满减金额
	 * @param storeId
	 * @param ids
	 * @return
	 */
	List<WxActivityFull> queryActivityFullPre(String storeId,List<Map<String,Object>> jsonList);
	/**
	 * 满赠赠品
	 * @param storeId
	 * @param ids
	 * @return
	 */
	List<WxActivityFull> queryActivityFullGift(String storeId,List<Map<String,Object>> jsonList);
	/**
	 * 打折折扣
	 * @param storeId
	 * @param ids
	 * @return
	 */
	List<WxActivityFull> queryActivityFullDiscount(String storeId,List<Map<String,Object>> jsonList);
}