package com.bluekjg.wxapp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxAddress;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderAddress;
import com.bluekjg.wxapp.model.WxOrderDetail;
import com.bluekjg.wxapp.model.WxOrderRefund;
import com.bluekjg.wxapp.model.WxOrderTrans;


/**
 * @description：订单信息
 * @author：pincui.Tom
 * @date：2018/9/27 14:51
 */
public interface IWxOrderService extends IService<WxOrder> {
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
	WxOrder queryOrderById(Integer id);
	/**
	 * 查询支付记录
	 * @param id
	 */
	WxOrderTrans selectOrderTrans(Integer id);
	/**
	 * 查询退款记录
	 * @param refundNo
	 * @return
	 */
	WxOrderRefund selectOrderTransByRefundNo(String refundNo);
	/**
	 * 添加订单
	 * @param bean
	 * @return
	 */
	void insertOrder(WxOrder order,List<WxOrderDetail> details,WxOrderAddress address,WxCollageGoods collage);
	/**
	 * 完成订单
	 * @param order
	 */
	void succeeOrder(Integer orderId,Integer status,String openId);
	/**
	 * 取消订单
	 * @param request
	 * @param order
	 * @param towards
	 */
	void cancelOrder(WxOrder order) ;
	/**
	 * 订单处理
	 * @param refund
	 * @param towards 订单走向（false:返回上一步，true：操作下一步）
	 */
	void processOrder(HttpServletRequest request,WxOrderRefund refund,boolean towards);
	/**
	 * 查询拼团详情
	 * @param id
	 * @return
	 */
	WxCollageGoods selectCollageGoodsObj(Integer id);
	/**
	 * 查询商品规格
	 * @param id
	 * @return
	 */
	String selectgoodsSpecById(Integer id);
	/**
	 * 返还商品库存
	 * @param orderId
	 */
	void returnGoodsAmount(WxOrderRefund refund);
	/**
	 * 发送通知
	 * @param order
	 * @param towards 订单走向（false:返回上一步，true：操作下一步）
	 */
	public void sendMessage(WxOrder order,boolean towards);
	
	/**
     * 生成16位订单号
     * @return
     */
    public String createOrderNo();
    
    
    /**
	 * 订单二维码
	 * @param order
	 * @return
	 */
	void updateOrderQrCode(WxOrder order);
	
	/**
	 * 商品库存  加减
	 * @param goodsId
	 * @param amount
	 * @return
	 */
	void updateGoodsAmount(Integer goodsId,Integer amount);
	
	
	/**
	 * 查询退货商品列表
	 * @param id
	 * @return
	 */
	List<WxOrderDetail> queryOrderRefundGoodsList(Integer id);
	/**
	 * 退货赠品列表
	 * @param id
	 * @return
	 */
	List<WxOrderDetail> queryOrderRefundGiftList(Integer id);
	WxAddress queryOrderRefundAddress(Integer refundId);
	/**
	 * 修改运单号
	 * @param address
	 * @return
	 */
	void updateRefundExpressNo(WxAddress address);
	
	void insertOrderRefund(WxOrderRefund refund);
}