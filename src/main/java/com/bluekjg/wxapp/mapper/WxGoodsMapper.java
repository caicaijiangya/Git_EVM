package com.bluekjg.wxapp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxActivityFull;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.model.WxGoods;
import com.bluekjg.wxapp.model.WxGoodsFreight;

/**
 * @description：商品 数据库控制层接口
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
public interface WxGoodsMapper extends BaseMapper<WxGoods> {
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
	String queryGoodsImages(@Param("goodsId") Integer goodsId,@Param("type") Integer type,@Param("smallType") Integer smallType);
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
	List<WxGoods> queryOrderGoods(@Param("list") List<Map<String, Object>> list);
	/**
	 * 门店商品列表
	 * @param storeId
	 * @param ids
	 * @return
	 */
	List<WxGoods> queryStoreGoods(@Param("storeId") String storeId,@Param("list") List<Map<String,Object>> list);
	/**
	 * 查询商品拼团列表
	 * @param id
	 * @return
	 */
	List<WxCollageGoods> queryCollageGoodsList(@Param("activityId") Integer activityId,@Param("goodsId") Integer goodsId);
	
	/**
	 * 我的收藏
	 * @param openId
	 * @param goodsId
	 * @return
	 */
	Integer queryCollect(@Param("openId") String openId,@Param("goodsId") String goodsId);
	boolean insertCollect(@Param("openId") String openId,@Param("goodsId") String goodsId);
	boolean deleteCollect(@Param("openId") String openId,@Param("goodsId") String goodsId);
	/**
	 * 加入购物车
	 * @param goods
	 * @return
	 */
	Integer queryShoppingcart(@Param("openId") String openId,@Param("goodsId") String goodsId,@Param("specId") String specId);
	boolean insertShoppingcart(WxGoods goods);
	boolean updateShoppingcart(WxGoods goods);
	boolean updateShoppingcartNum(WxGoods goods);
	boolean deleteShoppingcart(WxGoods goods);
	boolean deleteShoppingcartById(@Param("id") Integer id);
	
	/**
	 * 商品运费查询
	 * @return
	 */
	WxGoodsFreight queryGoodsFreight(@Param("province") Integer province);
	/**
	 * 根据分类查询商品列表
	 * @param page
	 * @return
	 */
	List<WxGoods> queryGoodsClassifyList(PagingDto page);
	WxActivityGoods queryActivityGoods(@Param("storeId") Integer storeId,@Param("goodsId") Integer goodsId,@Param("isType") Integer isType);
	WxActivityGoods queryActivityGoodsLadder(@Param("storeId") Integer storeId,@Param("id") Integer id,@Param("num") Integer num);
	List<String> queryActivityLabelList(@Param("id") Integer id);
	
	List<WxActivityFull> queryActivityFullPre(Map<String,Object> map);
	List<WxActivityFull> queryActivityFullGift(Map<String,Object> map);
	List<WxActivityFull> queryActivityFullDiscount(Map<String,Object> map);
}