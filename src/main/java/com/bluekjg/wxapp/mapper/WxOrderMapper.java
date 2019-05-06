package com.bluekjg.wxapp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxActivityFull;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxActivityGoodsCollage;
import com.bluekjg.wxapp.model.WxAddress;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.model.WxCollageGoodsDetail;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderAddress;
import com.bluekjg.wxapp.model.WxOrderDetail;
import com.bluekjg.wxapp.model.WxOrderRefund;
import com.bluekjg.wxapp.model.WxOrderTrans;
import com.bluekjg.wxapp.model.WxStore;

/**
 * @description：订单表数据库控制层接口
 * @author：pincui.tom
 * @date：2018/9/27 14:51
 */
public interface WxOrderMapper extends BaseMapper<WxOrder> {
	/**
	 * 查询订单运单号
	 * @param dto
	 * @return
	 */
	String queryOrderExpressNo(PagingDto dto);
	Map<String,String> queryOrderExpressMap(PagingDto dto);
	/**
	 * 查询所有代发货
	 * @return
	 */
	List<WxOrder> queryOrderByExpressList();
	/**
	 * 查询活动订单数量
	 * @param dto
	 * @return
	 */
	Integer queryActivityOrderCount(PagingDto dto);
	/**
	 * 查询订单列表
	 * @param dto
	 * @return
	 */
	List<WxOrder> queryOrderList(PagingDto dto);
	/**
	 * 查询退款订单列表
	 * @param dto
	 * @return
	 */
	List<WxOrder> queryOrderRefundList(PagingDto dto);
	/**
	 * 查询我的订单详情
	 * @param id
	 * @return
	 */
	WxOrder queryOrderById(@Param("id") Integer id);
	/**
	 * 查询支付记录
	 * @param id
	 */
	WxOrderTrans selectOrderTrans(@Param("id") Integer id);
	WxOrderTrans selectOrderTransByTransNo(@Param("transNo") String transNo);
	WxOrderRefund selectOrderTransByRefundNo(@Param("refundNo") String refundNo);
	/**
	 * 查询活动类型
	 * @return
	 */
	WxActivityGoodsCollage selectActivityByType(@Param("storeId") Integer storeId,@Param("id") Integer id,@Param("goodsId") Integer goodsId,@Param("goodsCollageId") Integer goodsCollageId);
	/**
	 * 查询拼团详情
	 * @param id
	 * @return
	 */
	WxCollageGoods selectCollageGoodsObj(@Param("id") Integer id);
	/**
	 * 查询商品规格
	 * @param id
	 * @return
	 */
	String selectgoodsSpecById(@Param("id") Integer id);
	/**
	 * 添加订单
	 * @param bean
	 * @return
	 */
	boolean insertOrder(WxOrder bean);
	/**
	 * 添加支付记录
	 * @param bean
	 * @return
	 */
	boolean insertOrderTrans(WxOrderTrans bean);
	/**
	 * 添加订单详情
	 * @param bean
	 * @return
	 */
	boolean insertOrderDetail(WxOrderDetail bean);
	/**
	 * 添加订单收货地址
	 * @param bean
	 * @return
	 */
	boolean insertOrderAddress(WxOrderAddress bean);
	/**
	 * 添加拼团
	 * @param bean
	 * @return
	 */
	boolean insertCollageGoods(WxCollageGoods bean);
	boolean insertCollageGoodsDetail(WxCollageGoodsDetail bean);
	/**
	 * 拼团人数增加
	 * @param id
	 * @return
	 */
	boolean updateCollageGoodsNum(@Param("id") Integer id);
	/**
	 * 商品库存  加减
	 * @param id
	 * @param amount
	 * @return
	 */
	boolean updateGoodsAmount(@Param("id") Integer id,@Param("amount") Integer amount);
	boolean updateGoodsRemAmount(@Param("id") Integer id,@Param("amount") Integer amount);
	/**
	 * 门店商品库存  加减
	 * @param storeId
	 * @param goodsId
	 * @param amount
	 * @return
	 */
	boolean updateStoreGoodsAmount(@Param("storeId") Integer storeId,@Param("goodsId") Integer goodsId,@Param("amount") Integer amount);
	/**
	 * 活动商品库存  加减
	 * @param activityId
	 * @param goodsId
	 * @param amount
	 * @return
	 */
	boolean updateActivityGoodsAmount(@Param("storeId") Integer storeId,@Param("activityId") Integer activityId,@Param("goodsId") Integer goodsId,@Param("amount") Integer amount);
	boolean updateActivityGoodsLadderAmount(@Param("storeId") Integer storeId,@Param("activityId") Integer activityId,@Param("goodsId") Integer goodsId,@Param("goodsNum") Integer goodsNum,@Param("amount") Integer amount);
	/**
	 * 积分商品库存  加减
	 * @param id
	 * @param amount
	 * @return
	 */
	boolean updateIntegralGoodsAmount(@Param("id") Integer id,@Param("amount") Integer amount);
	/**
	 * 赠品库存 加减
	 * @param activityId
	 * @param goodsId
	 * @param amount
	 * @return
	 */
	boolean updateGoodsGiftAmount(@Param("storeId") Integer storeId,@Param("activityId") Integer activityId,@Param("goodsId") Integer goodsId,@Param("goodsNum") Integer goodsNum,@Param("amount") Integer amount);
	/**
	 * 商品销量 加减
	 * @param id
	 * @param sales
	 * @return
	 */
	boolean updateGoodsSales(@Param("id") Integer id,@Param("sales") Integer sales);
	/**
	 * 商品总销量 加减
	 * @param id
	 * @param sales
	 * @return
	 */
	boolean updateGoodsTotalSales(@Param("id") Integer id,@Param("sales") Integer sales);
	/**
	 * 修改订单状态
	 * @param id
	 * @return
	 */
	boolean updateOrderStatus(@Param("id") Integer id,@Param("status") Integer status,@Param("upStatus") Integer upStatus);
	/**
	 * 修改订单支付状态
	 * @param bean
	 * @return
	 */
	boolean updateOrderTransStatus(WxOrderTrans bean);
	/**
	 * 修改订单退款状态
	 * @param id
	 * @param status
	 * @return
	 */
	boolean updateOrderRefundStatus(@Param("id") Integer id,@Param("status") Integer status,@Param("note") String note);
	/**
	 * 修改优惠券状态
	 * @param id
	 * @param status
	 * @return
	 */
	boolean updateCouponStatus(@Param("id") Integer id,@Param("status") Integer status);
	
	/**
	 * 订单二维码
	 * @param order
	 * @return
	 */
	boolean updateOrderQrCode(WxOrder order);
	/**
	 * 修改订单积分
	 * @param id
	 * @param integral
	 * @return
	 */
	boolean updateOrderIntegral(@Param("id") Integer id,@Param("integral") Integer integral);
	
	
	
	WxCollageGoods selectOrderCollageGoods(@Param("id") Integer id);
	WxOrderAddress queryOrderAddress(@Param("id") Integer id);
	WxStore queryOrderStore(@Param("storeId") Integer storeId);
	List<WxOrderDetail> queryOrderDetailList(@Param("id") Integer id);
	
	boolean insertOrderPre(WxActivityFull activityFull);
	boolean insertOrderGift(WxActivityFull activityFull);
	boolean insertOrderDiscount(WxActivityFull activityFull);
	
	/**
	 * 查询退货商品列表
	 * @param id
	 * @return
	 */
	List<WxOrderDetail> queryOrderRefundGoodsList(@Param("id") Integer id);
	List<WxOrderDetail> queryOrderRefundGiftList(@Param("id") Integer id);
	WxAddress queryOrderRefundAddress(@Param("refundId") Integer refundId);
	/**
	 * 新增退货
	 * @param refund
	 * @return
	 */
	boolean insertOrderRefund(WxOrderRefund refund);
	boolean insertOrderRefundDetail(WxOrderRefund refund);
	boolean insertOrderRefundGift(WxOrderRefund refund);
	boolean deleteOrderRefund(@Param("id") Integer id);
	boolean updateOrderDetailGoodsNum(@Param("orderId") Integer orderId,@Param("specId") Integer specId,@Param("goodsNum") Integer goodsNum);
	boolean updateOrderGiftGoodsNum(@Param("orderId") Integer orderId,@Param("activityId") Integer activityId,@Param("goodsId") Integer goodsId,@Param("goodsNum") Integer goodsNum);
	/**
	 * 修改订单已退金额
	 * @param trans
	 * @return
	 */
	boolean updateOrderTransRefund(WxOrderTrans trans);
	/**
	 * 修改运单号
	 * @param address
	 * @return
	 */
	boolean updateRefundExpressNo(WxAddress address);
}