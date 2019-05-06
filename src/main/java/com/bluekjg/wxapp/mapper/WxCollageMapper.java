package com.bluekjg.wxapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.model.WxCollageGoodsDetail;

/**
 * @description：拼团表数据库控制层接口
 * @author：pincui.tom
 * @date：2018/9/27 14:51
 */
public interface WxCollageMapper extends BaseMapper<WxCollageGoods> {
	/**
	 * 查询拼团活动Id
	 * @param orderId
	 * @return
	 */
	Integer queryCollageGoodsId(@Param("orderId") Integer orderId);
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
	 * 查询参团列表
	 * @param id
	 * @return
	 */
	List<WxCollageGoodsDetail> queryCollageDetail(@Param("id") Integer id);
	
	/**
	 * 拼团状态
	 * @param bean
	 * @return
	 */
	WxCollageGoods queryCollageJoinNum(WxCollageGoods bean);
	boolean updateCollageNum(WxCollageGoods bean);
	boolean updateCollageStatus(WxCollageGoods bean);
	boolean updateCollageDetailStatus(WxCollageGoods bean);
}