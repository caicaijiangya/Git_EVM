package com.bluekjg.wxapp.service;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxBargainGoods;
import com.bluekjg.wxapp.model.WxBargainGoodsDetail;
import com.bluekjg.wxapp.model.WxBargainGoodsUserLog;


/**
 * @description：砍价信息
 * @author：pincui.Tom
 * @date：2018/12/11 14:51
 */
public interface IWxBargainService extends IService<WxBargainGoods> {
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
	WxActivityGoods selectActivityById(Integer storeId,Integer id,Integer goodsId);
	
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
	void updateBargainGoods(WxBargainGoods bargain);
	/**
	 * 查询活动ID，商品ID
	 * @param page
	 * @return
	 */
	WxBargainGoods queryBargainGoodsId(PagingDto page);
	
	void insertBargainGoods(WxBargainGoods bargain);
	void insertBargainGoodsDetail(WxBargainGoodsDetail bargainDetail);
	void insertBargainGoodsUserLog(WxBargainGoodsUserLog log);
	/**
	 * 查询已赠送次数
	 * @param page
	 * @return
	 */
	Integer queryBargainGoodsUserLogCount(PagingDto page);
	/**
	 * 查询已发起次数
	 * @param bargain
	 * @return
	 */
	Integer queryBargainGoodsAmountCount(PagingDto page);
}