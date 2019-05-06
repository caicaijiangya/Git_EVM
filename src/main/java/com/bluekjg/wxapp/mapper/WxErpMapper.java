package com.bluekjg.wxapp.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxErpOrder;

/**
 * @description：ERP接口 数据库控制层接口
 * @author：pincui.tom
 * @date：2018/10/19 14:51
 */
public interface WxErpMapper extends BaseMapper<WxErpOrder> {
	/**
	 * 查询用户信息
	 * @param openId
	 * @return
	 */
	UserBean queryUserById(@Param("openId") String openId);
	/**
	 * 查询我的订单详情
	 * @param id
	 * @return
	 */
	WxErpOrder queryErpOrderById(@Param("id") Integer id);
	/**
	 * 查询我的订单退款详情
	 * @param id
	 * @return
	 */
	WxErpOrder queryErpOrderRefundById(@Param("id") Integer id);
	/**
	 * 修改订单信息
	 * @param order
	 * @return
	 */
	boolean updateErpOrder(WxErpOrder order);
	/**
	 * 修改会员信息
	 * @param order
	 * @return
	 */
	boolean updateErpVip(WxErpOrder order);
	/**
	 * 修改运单号
	 * @param order
	 * @return
	 */
	boolean updateOrderAddress(WxErpOrder order);
	/**
	 * 修改订单状态
	 * @param orderNo
	 * @return
	 */
	boolean updateOrderStatus(@Param("orderNo") String orderNo);
}