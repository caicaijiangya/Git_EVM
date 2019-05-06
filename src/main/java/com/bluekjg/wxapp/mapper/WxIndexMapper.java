package com.bluekjg.wxapp.mapper;

import java.util.List;

import com.bluekjg.wxapp.model.wxApp20.WxAITestResult;
import com.bluekjg.wxapp.model.wxApp20.WxGoodsJCTJ;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxGoodsIndex;
import com.bluekjg.wxapp.model.WxPopConfig;
import com.bluekjg.wxapp.model.WxQuestResult;

/**
 * @description：商品 数据库控制层接口
 * @author：pincui.tom
 * @date：2018/9/27 14:51
 */
public interface WxIndexMapper extends BaseMapper<WxGoodsIndex> {
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
	 * 查询活动商品列表
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
	 *
	 * new 查询检测结果推荐商品信息
	 */
	WxAITestResult queryAITestResultByOpenId(PagingDto page);
	/**
	 * new 根据商品Id查询推荐商品信息
	 */
	WxGoodsJCTJ queryTJGoods(Integer goodsId);

	/**
	 * new 根据openID查询推荐商品的商品Id
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