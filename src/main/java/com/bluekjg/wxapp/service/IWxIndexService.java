package com.bluekjg.wxapp.service;

import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxGoodsIndex;
import com.bluekjg.wxapp.model.WxPopConfig;
import com.bluekjg.wxapp.model.WxQuestResult;
import com.bluekjg.wxapp.model.wxApp20.WxAITestResult;
import com.bluekjg.wxapp.model.wxApp20.WxGoodsJCTJ;


/**
 * @description：商品信息
 * @author：pincui.Tom
 * @date：2018/9/27 14:51
 */
public interface IWxIndexService extends IService<WxGoodsIndex> {
	/**
	 * 查询品牌列表
	 * @param page
	 * @return
	 */
	List<WxGoodsIndex> queryBrandList(PagingDto page);
	/**
	 * 查询分类列表
	 * @param page
	 * @return
	 */
	List<WxGoodsIndex> queryClassifyList(PagingDto page);
	/**
	 * 查询商品列表
	 * @param page
	 * @return
	 */
	List<WxGoodsIndex> queryGoodsList(PagingDto page);
	/**
	 * 查询活动列表
	 * @param page
	 * @return
	 */
	List<WxGoodsIndex> queryActivityList(PagingDto page);
	/**
	 * 查询热门商品
	 * @param page
	 * @return
	 */
	List<WxGoodsIndex> queryGoodsHotList(PagingDto page);
	/**
	 * 查询最后一次测试结果
	 * @param page
	 * @return
	 */
	WxQuestResult queryQuestResultId(PagingDto page);
	/**
	 * 查看默认弹窗
	 * @return
	 */
	WxPopConfig queryPopConfig(PagingDto page);
	
	/**
	 * 查询满减满赠活动信息
	 * @return
	 */
	List<WxGoodsIndex> queryActivityFull();
	/**
	 * 查询活动商品列表
	 * @param activityId
	 * @return
	 */
	List<WxGoodsIndex> queryActivityDetail(PagingDto page);

	/**
	 * 查询AI检测结果
	 * @param
	 * @return
	 */
	WxAITestResult queryAITestResultByOpenId(PagingDto page);
	/**
	 * 通过商品ID查询推荐商品
	 */
	WxGoodsJCTJ queryTJGoods(Integer goodsId);
	/**
	 * 根据openID查询推荐商品的商品Id
	 */
	Integer queryTJGoodsIDByOpenId(PagingDto page);
	/**
	 * new 通过openID查询历史订单数量
	 */
	Integer queryOrderCountByOpenId(PagingDto page);
	/**
	 * new 通过openID查询此人历史订单中的商品ID
	 */
	List<Integer> queryGoodsByOpenId(PagingDto page);

	//通过openID查询用户表里是否有
	Integer queryCountFromUser(PagingDto page);
	//手机号
	Integer queryMobileFromUser(PagingDto page);
}