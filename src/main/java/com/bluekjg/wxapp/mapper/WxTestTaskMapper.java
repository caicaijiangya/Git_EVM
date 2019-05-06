package com.bluekjg.wxapp.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.WxActivityFull;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderRefund;
import com.bluekjg.wxapp.model.WxOrderTrans;

/**
 * @description：定时任务控制层接口
 * @author：pincui.tom
 * @date：2018/9/27 14:51
 */
public interface WxTestTaskMapper extends BaseMapper<WxOrder> {
	/**
	 * 完成订单
	 * @return
	 */
	List<WxOrder> completeOrder();
	/**
	 * 结束订单
	 * @return
	 */
	boolean cancelOrder();
	/**
	 * 结束活动
	 * @return
	 */
	boolean cancelActivity();
	/**
	 * 结束活动的商品列表
	 */
	List<WxActivityFull> selectActivityGoods();
	/**
	 * 结束活动的赠品列表
	 */
	List<WxActivityFull> selectActivityGift();
	/**
	 * 结束拼团
	 * @return
	 */
	boolean cancelCollage();
	/**
	 * 结束拼团订单
	 * @return
	 */
	boolean cancelCollageOrder();
	/**
	 * 取消未付款拼团
	 * @return
	 */
	List<WxOrderRefund> queryCancelCollage();
	List<Integer> queryCancelOrder();
	boolean updateCancelCollage(WxOrderRefund refund);
	/**
	 * 优惠券批量失效
	 * @return
	 */
	boolean updateUserCouponStatus();
}