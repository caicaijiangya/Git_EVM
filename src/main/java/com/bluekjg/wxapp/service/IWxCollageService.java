package com.bluekjg.wxapp.service;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxCollageGoods;


/**
 * @description：拼团信息
 * @author：pincui.Tom
 * @date：2018/9/27 14:51
 */
public interface IWxCollageService extends IService<WxCollageGoods> {
	/**
	 * 查询拼团活动Id
	 * @param orderId
	 * @return
	 */
	Integer queryCollageGoodsId(Integer orderId);
	/**
	 * 查询未结束我的开团
	 * @param bean
	 * @return
	 */
	Integer queryCollageByIsUser(PagingDto dto);
	/**
	 * 查询拼团详情
	 * @param dto
	 * @return
	 */
	WxCollageGoods queryCollageObj(PagingDto dto);
	/**
	 * 拼团状态
	 * @param bean
	 * @return
	 */
	WxCollageGoods queryCollageJoinNum(WxCollageGoods bean);
	/**
	 * 参团数量+1
	 * @param bean
	 */
	void updateCollageNum(WxCollageGoods bean);
	void updateCollageDetailStatus(WxCollageGoods bean);
}