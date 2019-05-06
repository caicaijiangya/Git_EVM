package com.bluekjg.wxapp.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxBargainGoods;
import com.bluekjg.wxapp.model.WxBargainGoodsDetail;
import com.bluekjg.wxapp.model.WxBargainGoodsUserLog;

/**
 * @description：砍价信息表数据库控制层接口
 * @author：pincui.tom
 * @date：2018/12/11 14:51
 */
public interface WxBargainMapper extends BaseMapper<WxBargainGoods> {
	/**
	 * 查询砍价详情
	 * @param page
	 * @return
	 */
	WxBargainGoods queryBargainById(PagingDto page);
	/**
	 * 查询已砍价数量
	 * @param page
	 * @return
	 */
	Integer queryBargainStatus(PagingDto page);
	/**
	 * 根据不同状态查询对应ID
	 * @param page
	 * @return
	 */
	Integer queryBargainStatusById(PagingDto page);
	/**
	 * 查询活动详情
	 * @param id
	 * @param goodsId
	 * @return
	 */
	WxActivityGoods selectActivityById(@Param("storeId") Integer storeId,@Param("id") Integer id,@Param("goodsId") Integer goodsId);
	/**
	 * 查询是否已助砍
	 * @param page
	 * @return
	 */
	Integer queryBargainGoodsDetailCount(PagingDto page);
	/**
	 * 查看我已助力次数
	 * @param page
	 * @return
	 */
	Integer queryBargainGoodsCount(PagingDto page);
	/**
	 * 修改助力砍价
	 * @param bargain
	 * @return
	 */
	Integer updateBargainGoods(WxBargainGoods bargain);
	/**
	 * 查询活动ID，商品ID
	 * @param page
	 * @return
	 */
	WxBargainGoods queryBargainGoodsId(PagingDto page);
	
	Integer insertBargainGoods(WxBargainGoods bargain);
	Integer insertBargainGoodsDetail(WxBargainGoodsDetail bargainDetail);
	Integer insertBargainGoodsUserLog(WxBargainGoodsUserLog log);
	/**
	 * 查询已赠送次数
	 * @param page
	 * @return
	 */
	Integer queryBargainGoodsUserLogCount(PagingDto page);
	
	WxBargainGoods queryBargainGoodsById(@Param("id") Integer id);
	
	
	Integer updateBargainGoodsOrderId(@Param("orderId") Integer orderId);
	/**
	 * 查询已发起次数
	 * @param bargain
	 * @return
	 */
	Integer queryBargainGoodsAmountCount(PagingDto page);
}