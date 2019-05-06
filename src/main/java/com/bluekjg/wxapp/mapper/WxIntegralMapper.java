package com.bluekjg.wxapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxCoupon;
import com.bluekjg.wxapp.model.WxIntegralGoods;

/**
 * @description：积分商品 数据库控制层接口
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
public interface WxIntegralMapper extends BaseMapper<WxIntegralGoods> {
	/**
	 * 查询优惠券列表
	 * @param page
	 * @return
	 */
	List<WxCoupon> queryCouponList(PagingDto page);
	/**
	 * 查询商品列表
	 * @param page
	 * @return
	 */
	List<WxIntegralGoods> queryGoodsList(PagingDto page);
	/**
	 * 查询商品详情
	 * @param page
	 * @return
	 */
	WxIntegralGoods queryGoodsDetail(PagingDto page);
	/**
	 * 查询优惠券详情
	 * @param page
	 * @return
	 */
	WxCoupon queryCouponDetail(PagingDto page);
	/**
	 * 查询积分商品库存
	 * @param goodsId
	 * @param type
	 * @return
	 */
	WxIntegralGoods queryGoodsAmount(@Param("openId") String openId,@Param("goodsId") String goodsId,@Param("type") String type);
	/**
	 * 查询余额
	 * @param openId
	 * @return
	 */
	Integer queryUserIntegral(@Param("openId") String openId);
	/**
	 * 修改库存
	 * @param id
	 * @param amount
	 * @return
	 */
	boolean updateIntegralGoodsAmount(@Param("id") Integer id,@Param("type") Integer type,@Param("amount") Integer amount);
	/**
	 * 修改积分
	 * @param bean
	 * @return
	 */
	boolean updateUserIntegral(UserBean bean);
}