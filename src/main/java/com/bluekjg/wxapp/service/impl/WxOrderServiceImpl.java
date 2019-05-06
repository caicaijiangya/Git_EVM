package com.bluekjg.wxapp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.redis.key.RedisKey;
import com.bluekjg.wxapp.mapper.WxBargainMapper;
import com.bluekjg.wxapp.mapper.WxCouponMapper;
import com.bluekjg.wxapp.mapper.WxGoodsMapper;
import com.bluekjg.wxapp.mapper.WxIntegralMapper;
import com.bluekjg.wxapp.mapper.WxKeeperOrderMapper;
import com.bluekjg.wxapp.mapper.WxOrderMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.PayDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxActivityFull;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxActivityGoodsCollage;
import com.bluekjg.wxapp.model.WxAddress;
import com.bluekjg.wxapp.model.WxBargainGoods;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.model.WxCollageGoodsDetail;
import com.bluekjg.wxapp.model.WxCoupon;
import com.bluekjg.wxapp.model.WxDict;
import com.bluekjg.wxapp.model.WxGoods;
import com.bluekjg.wxapp.model.WxIntegralActivity;
import com.bluekjg.wxapp.model.WxKeeperMessage;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderAddress;
import com.bluekjg.wxapp.model.WxOrderDetail;
import com.bluekjg.wxapp.model.WxOrderRefund;
import com.bluekjg.wxapp.model.WxOrderTrans;
import com.bluekjg.wxapp.model.wx.DataModel;
import com.bluekjg.wxapp.service.IWxDictService;
import com.bluekjg.wxapp.service.IWxErpService;
import com.bluekjg.wxapp.service.IWxGoodsService;
import com.bluekjg.wxapp.service.IWxKeeperMessageService;
import com.bluekjg.wxapp.service.IWxOrderService;
import com.bluekjg.wxapp.service.IWxPayService;
import com.bluekjg.wxapp.service.IWxUserService;
import com.bluekjg.wxapp.utils.DictUtil;
import com.bluekjg.wxapp.utils.RedisUtil;

/**
 * @description：订单数据
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
@Transactional
public class WxOrderServiceImpl extends ServiceImpl<WxOrderMapper, WxOrder>implements IWxOrderService {

	protected Logger logger = LoggerFactory.getLogger(WxOrderServiceImpl.class);

	@Autowired
	private WxOrderMapper orderMapper;
	@Autowired
	private IWxPayService payService;
	@Autowired
	private IWxKeeperMessageService keeperMessageService;
	@Autowired 
	private WxKeeperOrderMapper keeperOrderMapper;
	@Autowired
	private IWxUserService userService;
	@Autowired
	private WxGoodsMapper goodsMapper;
	@Autowired
	private WxBargainMapper bargainMapper;
	@Autowired
	private IWxErpService erpService;
	@Autowired 
	private RedisUtil redisUtil;
	@Autowired
	private IWxGoodsService goodsService;
	@Autowired
	private IWxDictService dictService;
	@Autowired
	private WxIntegralMapper integralMapper;
	@Autowired
	private WxCouponMapper couponMapper;

	@Override
	@Transactional
	public void insertOrder(WxOrder order,List<WxOrderDetail> details,WxOrderAddress address,WxCollageGoods collage) {
		if(order.getCouponId() != null && order.getCouponId() > 0) {
			WxCoupon coupon = couponMapper.queryUserCouponById(order.getCouponId());
			if(coupon != null) {
				//折扣券
				if(coupon.getCouponType() == 1) {
					order.setDisountPrice(order.getTotalBalances() - (order.getTotalBalances() * order.getDisountPrice()));
				}
			} else {
				order.setCouponId(0);
				order.setDisountPrice(0.0);
			}
		}
		orderMapper.insertOrder(order);
		List<Map<String,Object>> goodsJson = new ArrayList<Map<String,Object>>();
		for(int i=0;i<details.size();i++) {
			WxOrderDetail detail = details.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("goodsId", detail.getGoodsId());
			map.put("goodsNum", detail.getGoodsNum());
			map.put("specId", detail.getSpecId());
			goodsJson.add(map);
		}
		//满减、满赠、打折
		List<WxActivityFull> preList = goodsService.queryActivityFullPre(String.valueOf(order.getStoreId()), goodsJson);
    	List<WxActivityFull> giftList = goodsService.queryActivityFullGift(String.valueOf(order.getStoreId()), goodsJson);
    	List<WxActivityFull> discountList = goodsService.queryActivityFullDiscount(String.valueOf(order.getStoreId()), goodsJson);
    	//使用优惠券，清除满减满赠活动
    	if(order.getCouponId() != null && order.getCouponId() > 0 && order.getIsActivityShared() == 0) {
    		preList = null;
    		giftList = null;
    		discountList = null;
    	}
    	double pre = 0;
    	if(preList != null && preList.size() > 0) {
    		for(WxActivityFull full:preList) {
    			pre += full.getPrePrice();
    			full.setOrderId(order.getId());
    			orderMapper.insertOrderPre(full);
    		}
    	}
    	if(giftList != null && giftList.size() > 0) {
    		for(WxActivityFull full:giftList) {
    			full.setOrderId(order.getId());
    			if(full.getAmount() > 0) {
    				orderMapper.updateGoodsGiftAmount(order.getStoreId(),full.getId(), full.getGoodsId(),full.getGoodsNum(), -1);
    				orderMapper.insertOrderGift(full);
    			}
    		}
    	}
    	double discountPre = 0;
    	if(discountList != null && discountList.size() > 0) {
    		for(WxActivityFull full:discountList) {
    			full.setOrderId(order.getId());
    			double price = 0;
    			for(WxOrderDetail detail:details) {
    				if(full.getGoodsId().intValue() == detail.getGoodsId().intValue()) {
    					double goodsPrice = detail.getOriginalPrice() * detail.getGoodsNum();
    					goodsPrice = goodsPrice - (goodsPrice * full.getPrePrice());
    					price += goodsPrice;
    				}
    			}
    			discountPre += price;
    			full.setPrice(price);
    			orderMapper.insertOrderDiscount(full);
    		}
    	}
		//订单支付
		WxOrderTrans trans = new WxOrderTrans();
		trans.setOpenId(order.getOpenId());
		trans.setOrderId(order.getId());
		trans.setTransNo(order.getOrderNo()+ (int)((Math.random()*9+1)*1000));
		trans.setRefundNo(order.getOrderNo()+ (int)((Math.random()*9+1)*1000));
		Double balances = order.getTotalBalances() - order.getDisountPrice() - pre - discountPre;
		if(address != null) { balances = balances + address.getFreight(); }
		if(balances < 0) {balances = 0.0;}
		trans.setBalances(BigDecimal.valueOf(balances));
		if(order.getActivityId() != null && order.getActivityId() > 0) {
			if(order.getOrderType() == 1) {
				trans.setTransName("购买秒杀商品产生记录");
				trans.setRefundName("秒杀商品退款产生记录");
			}else if(order.getOrderType() == 2) {
				trans.setTransName("购买拼团商品产生记录");
				trans.setRefundName("拼团商品退款产生记录");
			}else if(order.getOrderType() == 3) {
				trans.setTransName("购买特价商品产生记录");
				trans.setRefundName("特价商品退款产生记录");
			}else if(order.getOrderType() == 5) {
				trans.setTransName("购买砍价商品产生记录");
				trans.setRefundName("砍价商品退款产生记录");
			}
		}else {
			trans.setTransName("购买商品产生记录");
			trans.setRefundName("商品退款产生记录");
		}
		//添加支付记录
		orderMapper.insertOrderTrans(trans);
		if(address != null) {
			address.setOrderId(order.getId());
			orderMapper.insertOrderAddress(address);
		}
		for(int i=0;i<details.size();i++) {
			WxOrderDetail detail = details.get(i);
			//删除购物车商品
			WxGoods wxGoods = new WxGoods();
			wxGoods.setOpenId(order.getOpenId());
			wxGoods.setId(detail.getGoodsId());
			wxGoods.setSpecId(detail.getSpecId());
			goodsMapper.deleteShoppingcart(wxGoods);
			/*
			 * 商品减库存
			 */
			Integer goodsNum = detail.getGoodsNum() * -1;
			if(order.getActivityId() != null && order.getActivityId() > 0) {
				//活动商品库存
				orderMapper.updateActivityGoodsAmount(order.getStoreId(),order.getActivityId(),detail.getActivityGoodsId(), goodsNum);
			}else {
				if(order.getStoreId() != null && order.getStoreId() > 0) {
					//门店商品库存
					orderMapper.updateStoreGoodsAmount(order.getStoreId(), detail.getSpecId(), goodsNum);
				}else {
					//总店商品库存
					orderMapper.updateGoodsAmount(detail.getSpecId(), goodsNum);
				}
				orderMapper.updateGoodsRemAmount(detail.getSpecId(), goodsNum);
				orderMapper.updateGoodsSales(detail.getGoodsId(), detail.getGoodsNum());//加销量
			}
			orderMapper.updateGoodsTotalSales(detail.getGoodsId(), detail.getGoodsNum());//加销量（商品+活动）
			detail.setOrderId(order.getId());
			orderMapper.insertOrderDetail(detail);
		}
		
		//修改优惠券为已使用
		if(order.getCouponId() != null && order.getCouponId() > 0) {
			orderMapper.updateCouponStatus(order.getCouponId(), 1);
		}
		if(order.getOrderType() == 2) {
			//拼团订单
			if(collage != null) {
				WxCollageGoodsDetail collageDetail = new WxCollageGoodsDetail();
				collageDetail.setCollageId(collage.getId());
				collageDetail.setOpenId(order.getOpenId());
				collageDetail.setIsCollage(1);
				collageDetail.setStatus(0);
				collageDetail.setOrderId(order.getId());
				orderMapper.insertCollageGoodsDetail(collageDetail);
			}else {
				//添加新团
				WxActivityGoodsCollage activityType = orderMapper.selectActivityByType(order.getStoreId(),order.getActivityId(),details.get(0).getActivityGoodsId(),order.getGoodsCollageId());
				if(activityType != null) {
					Date date = new Date();
					String startTime = DateUtil.parseDateToStr(date, DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
					date.setHours(date.getHours()+activityType.getCollageTime());
					String endTime = DateUtil.parseDateToStr(date, DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
					collage = new WxCollageGoods();
					collage.setActivityId(order.getActivityId());
					collage.setGoodsId(details.get(0).getSpecId());
					collage.setJoinNum(0);
					collage.setStartTime(startTime);
					collage.setEndTime(endTime);
					collage.setStoreId(order.getStoreId());
					collage.setCollageId(order.getGoodsCollageId());
					orderMapper.insertCollageGoods(collage);
					//新增团长
					WxCollageGoodsDetail collageDetail = new WxCollageGoodsDetail();
					collageDetail.setCollageId(collage.getId());
					collageDetail.setOpenId(order.getOpenId());
					collageDetail.setIsCollage(0);
					collageDetail.setStatus(0);
					collageDetail.setOrderId(order.getId());
					orderMapper.insertCollageGoodsDetail(collageDetail);
				}
			}
		}else if(order.getOrderType() == 5) {
			//砍价订单
			WxBargainGoods b = bargainMapper.queryBargainGoodsById(order.getBargainId());
			if(b != null) {
				b.setCreateTime(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYYMMDDHHMISS));
			}
			redisUtil.set(RedisKey.BARGAIN_CHENGONG_PROMPT+b.getCreateTime(), b);
			WxBargainGoods bargain = new WxBargainGoods();
			bargain.setJoinPrice(0.0);
			bargain.setStatus(2);
			bargain.setId(order.getBargainId());
			bargain.setOrderId(order.getId());
			bargainMapper.updateBargainGoods(bargain);
		}
		//发送通知消息
		sendMessage(order,true);
		//支付金额为0、到店付款    不跳转支付页面
		if(balances == 0.0 || order.getPayMoneyStyle() == 1) {
			order.setId(0);
		}
		//免除支付
		if(balances == 0.0) {
			PayDto payDto = new PayDto();
			payDto.setOutTradeNo(trans.getTransNo());
			payService.paySuccesssNotice(payDto);
		}
	}

	@Override
	public String selectgoodsSpecById(Integer id) {
		// TODO Auto-generated method stub
		return orderMapper.selectgoodsSpecById(id);
	}

	@Override
	public WxCollageGoods selectCollageGoodsObj(Integer id) {
		// TODO Auto-generated method stub
		return orderMapper.selectCollageGoodsObj(id);
	}
	@Override
	public WxOrderTrans selectOrderTrans(Integer id) {
		// TODO Auto-generated method stub
		return orderMapper.selectOrderTrans(id);
	}
	@Override
	public WxOrder queryOrderById(Integer id) {
		// TODO Auto-generated method stub
		return orderMapper.queryOrderById(id);
	}
	@Override
	public WxOrderRefund selectOrderTransByRefundNo(String refundNo) {
		// TODO Auto-generated method stub
		return orderMapper.selectOrderTransByRefundNo(refundNo);
	}
	@Override
	public Map<String, String> queryOrderExpressMap(PagingDto dto) {
		// TODO Auto-generated method stub
		return orderMapper.queryOrderExpressMap(dto);
	}
	/**
	 * 订单完成
	 */
	@Override
	@Transactional
	public void succeeOrder(Integer orderId,Integer status,String openId) {
		// TODO Auto-generated method stub
		WxOrder order = orderMapper.queryOrderById(orderId);
		WxOrderTrans trans = orderMapper.selectOrderTrans(orderId);
		int integral = 0;
		if(trans != null && trans.getBalances() != null) {
			integral = trans.getBalances().intValue();
		}
		orderMapper.updateOrderStatus(orderId, 6,status);//完成订单
		if(openId != null) {
			DataModel dataModel = new DataModel();
			dataModel.setOpenId(openId);
			dataModel.setDataId(orderId);
			keeperOrderMapper.writeOffOrderInfo(dataModel);
		}
		//添加积分
		WxDict dict = dictService.queryDictByCode(DictUtil.MALL_FULL_DISCOUNT);
		if(dict != null) {
			double totalBalances = Double.valueOf(dict.getDictValue()) * trans.getTotalBalances();
			if(totalBalances <= trans.getBalances().doubleValue()) {
				WxIntegralActivity integralActivity = userService.selectIntegralActivity(1);
				if(integralActivity != null) {
					if(order.getStoreId() == null) {order.setStoreId(0);}
					boolean bool = false;
        			for(Integer storeId:integralActivity.getStoreList()) {
        				if(storeId.intValue() == order.getStoreId().intValue()) {
        					bool = true;
        				}
        			}
        			if(bool) {integral = integral*integralActivity.getMultiple();}
				}
				orderMapper.updateOrderIntegral(orderId, integral);
				userService.insertIntegralLog(order.getOpenId(),null,4,integral);
			}
		}
		
		if(status == 1) {
			
			order.setStatus(6);
			sendMessage(order,true);
		}
	}
	/**
	 * 取消订单
	 * @param request
	 * @param order
	 * @param towards
	 */
	public void cancelOrder(WxOrder order) {
		if(order.getStatus() == 0) {
			//未支付订单 直接取消
			orderMapper.updateOrderStatus(order.getId(), 5,order.getStatus());//取消订单
			if(order.getCouponId() != null && order.getCouponId() > 0) {
				orderMapper.updateCouponStatus(order.getCouponId(), 0);//修改优惠券为可用
				order.setStatus(5);
			}
			WxOrderRefund refund = new WxOrderRefund();
			refund.setActivityId(order.getActivityId());
			refund.setOrderId(order.getId());
			refund.setStoreId(order.getStoreId());
			refund.setOrderType(order.getOrderType());
			if(order.getDetails() != null && order.getDetails().size() > 0) {
				List<WxOrderRefund> details = new ArrayList<WxOrderRefund>();
				for(WxOrderDetail detail:order.getDetails()) {
					WxOrderRefund dr = new WxOrderRefund();
					dr.setGoodsId(detail.getGoodsId());
					dr.setSpecId(detail.getSpecId());
					dr.setGoodsNum(detail.getGoodsNum());
					details.add(dr);
				}
				refund.setDetails(details);
			}
			returnGoodsAmount(refund);//返还库存
			if(order.getOrderType() == 4) {
				//返还积分
				userService.insertIntegralLog(order.getOpenId(),null,7,order.getTotalBalances().intValue());
			}
		}
	}
	/**
	 * 订单处理
	 * @param order
	 * @param towards 订单走向（false:返回上一步，true：操作下一步）
	 */
	@Override
	@Transactional
	public void processOrder(HttpServletRequest request,WxOrderRefund refund,boolean towards) {
		WxOrder order = orderMapper.queryOrderById(refund.getOrderId());
		int orderStatus = order.getStatus();
		order.setStatus(-1);
		if(towards) {
			//提交退货/退款
			if(refund.getId() == null) {
				orderMapper.insertOrderRefund(refund);
				//商品
				if(refund.getDetails() != null && refund.getDetails().size() > 0) {
					for(WxOrderRefund detail:refund.getDetails()) {
						detail.setRefundId(refund.getId());
						orderMapper.insertOrderRefundDetail(detail);
					}
				}
				//赠品
				if(refund.getGifts() != null && refund.getGifts().size() > 0) {
					for(WxOrderRefund gift:refund.getGifts()) {
						gift.setRefundId(refund.getId());
						orderMapper.insertOrderRefundGift(gift);
					}
				}
				WxOrderRefund refund1 = selectOrderTransByRefundNo(refund.getRefundNo());
				//订单商品已全退改为待退款/待退货
				if(refund1.getGoodsNum() == 0) {
					int refundStatus = refund1.getStatus();
					if(refund1.getStatus() == 1) {
						refundStatus = 7;
					}
					orderMapper.updateOrderStatus(refund.getOrderId(), refundStatus,orderStatus);
				}
				//门店订单直接退款
				if(order.getStoreId() > 0) {
					processOrder(request,refund1,towards);
				}else {
					if(refund1.getType() == 1) {
						//ERP订单退款接口推送
						erpService.pushOrderRefundErp(order.getOrderNo(), 0);
					}
				}
			}else {
				//订单退款
				if((refund.getStatus() == 3 && refund.getStoreId() > 0 ) 
						|| (refund.getStatus() == 3 && refund.getStoreId() == 0 && refund.getRefundId() != null)) {
					//退款接口
					Integer result = 1;
	        		if(refund.getBalances().doubleValue() == 0.0) {
	        			//支付金额为-0则无需退款
	        			orderMapper.updateOrderRefundStatus(refund.getId(), 4,refund.getNote());
	        		}else {
	        			result = payService.wxrefund(request,refund);
	        		}
	        		if(result > 0) {
	    				returnGoodsAmount(refund);//返还库存
	    				if(order.getStoreId() == 0 && refund.getType() == 1) {
	    					//ERP订单退款接口推送
	    					erpService.pushOrderRefundErp(refund.getOrderNo(), 1);
	    				}
	        		}
	        		order.setStatus(3); 
				}else if(refund.getStatus() == 1) {
					//退货申请
					orderMapper.updateOrderRefundStatus(refund.getId(), 2,refund.getNote());
					
					if(order.getIntegral() > 0) {
						//退还积分
						if(order.getUserIntegral().intValue() >= order.getIntegral().intValue()) {
							//扣减积分
							userService.insertIntegralLog(order.getOpenId(), null, -1, order.getIntegral().intValue() * -1);
						}else {
							return;//积分不足 不予退货
						}
					}
					if(orderStatus == 7) {
						orderMapper.updateOrderStatus(refund.getOrderId(), 8,6);
					}
				}else if(refund.getStatus() == 2) {
					order.setStatus(7); 
					//确认收货
					orderMapper.updateOrderRefundStatus(refund.getId(), 3,refund.getNote());
					if(orderStatus == 8) {
						orderMapper.updateOrderStatus(refund.getOrderId(), 3,6);
					}
				}
			}
			
		}else {
			//不通过
			int status = 11;//拒绝退货
			if(refund.getStatus() == 3) { 
				status = 12; //拒绝退款
			}
			orderMapper.updateOrderRefundStatus(refund.getId(), status,refund.getNote());
			if(orderStatus == 3) {
				orderMapper.updateOrderStatus(refund.getOrderId(), Integer.valueOf(order.getNote()),2);
				if(order.getStoreId() == 0 && refund.getType() == 1) {
					//ERP订单退款接口推送
					erpService.pushOrderRefundErp(order.getOrderNo(), 2);
				}
			}
			if(orderStatus == 7) {
				orderMapper.updateOrderStatus(refund.getOrderId(), Integer.valueOf(order.getNote()),6);
			}
		}
		//发送通知消息
		sendMessage(order,towards);
	}
	/**
	 * 返回商品库存
	 */
	@Override
	public void returnGoodsAmount(WxOrderRefund refund) {
		if(refund != null && refund.getDetails() != null && refund.getDetails().size() > 0) {
			for(WxOrderRefund detail:refund.getDetails()) {
				if(refund.getOrderType() == 4) {
					//积分商品
					orderMapper.updateIntegralGoodsAmount(detail.getSpecId(), detail.getGoodsNum());
				}else {
					orderMapper.updateGoodsTotalSales(detail.getGoodsId(), (detail.getGoodsNum() * -1));//减总销量
					if(refund.getActivityId() != null && refund.getActivityId() > 0) {
						//活动商品
						orderMapper.updateActivityGoodsAmount(refund.getStoreId(),refund.getActivityId(), detail.getSpecId(), detail.getGoodsNum());
						orderMapper.updateActivityGoodsLadderAmount(refund.getStoreId(),refund.getActivityId(), detail.getSpecId(), detail.getGoodsNum(), detail.getGoodsNum());
					}else {
						orderMapper.updateGoodsSales(detail.getGoodsId(), (detail.getGoodsNum() * -1));//减销量
						//商城商品
						orderMapper.updateGoodsRemAmount(detail.getSpecId(), detail.getGoodsNum());
						if(refund.getStoreId() != 0 && refund.getStoreId() > 0) {
							orderMapper.updateStoreGoodsAmount(refund.getStoreId(), detail.getSpecId(), detail.getGoodsNum());
						}else {
							orderMapper.updateGoodsAmount(detail.getSpecId(), detail.getGoodsNum());
						}
					}
					//赠品库存返还
					if(refund.getGifts() != null && refund.getGifts().size() > 0) {
						for(WxOrderRefund gift:refund.getGifts()) {
							orderMapper.updateGoodsGiftAmount(refund.getStoreId(),gift.getActivityId(), gift.getGoodsId(),gift.getGoodsNum(), gift.getGoodsNum());
						}
					}
				}
			}
		}
	}
	@Override
	public void sendMessage(WxOrder order,boolean towards) {
		WxKeeperMessage wxMessage = new WxKeeperMessage();
		wxMessage.setSendOpenId(order.getOpenId());
		if(towards) {
			if(order.getStoreId() != null && order.getStoreId() > 0) {
				if(order.getStatus() == 0) {
					wxMessage.setTitle("您已在商城成功生成门店订单");
					List<UserBean> userList = userService.queryWxUserInfoByStoreId(order.getStoreId());
					if(userList != null && userList.size() > 0) {
						for(UserBean user:userList) {
							WxKeeperMessage wxMessage_ = new WxKeeperMessage();
							wxMessage_.setSendOpenId(user.getOpenId());
							wxMessage_.setTitle("用户购买门店商品，订单号（"+order.getOrderNo()+"），订单时间:"+DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYY年MM月DD日));
							wxMessage_.setType(2);
							wxMessage_.setSendType(user.getUserType());
							keeperMessageService.insertMessage(wxMessage_);
						}
					}
				}else if(order.getStatus() == 3) {
					wxMessage.setTitle("消费者没有没有到门店取货，选择退款：用户购买门店商品，订单号（"+order.getOrderNo()+"），未取货，申请退款成功");
				}else if(order.getStatus() == 6) {
					wxMessage.setTitle("用户购买的门店商品，订单号（"+order.getOrderNo()+"），已经核销成功，请注意商品请详");
				}
			}else {
				if(order.getStatus() == 0) {
					if(order.getOrderType() == 0) {
						wxMessage.setTitle("您已在商城成功生成订单");
					}else if(order.getOrderType() == 1) {
						wxMessage.setTitle("您已在商城成功生成秒杀订单");
					}else if(order.getOrderType() == 2) {
						wxMessage.setTitle("您已在商城成功生成拼团订单");
					}else if(order.getOrderType() == 3) {
						wxMessage.setTitle("您已在商城成功生成特价订单");
					}else if(order.getOrderType() == 4) {
						wxMessage.setTitle("您已在商城成功兑换商品");
					}
				}else if(order.getStatus() == 3) {
					wxMessage.setTitle("您的退款申请商家已经处理");
				}else if(order.getStatus() == 7) {
					wxMessage.setTitle("您的退货申请商家已经处理");
				}
			}
		}else {
			if(order.getStatus() == 3) {
				wxMessage.setTitle("商家拒绝了您的退款申请");
			}else if(order.getStatus() == 7) {
				wxMessage.setTitle("商家拒绝了您的退货申请");
			}
		}
		
		
		wxMessage.setType(2);
		wxMessage.setSendType(4);
		if(wxMessage.getTitle() != null) {
			keeperMessageService.insertMessage(wxMessage);
		}
	}
	@Override
	public List<WxOrder> queryOrderList(PagingDto dto) {
		// TODO Auto-generated method stub
		return orderMapper.queryOrderList(dto);
	}
	@Override
	public List<WxOrder> queryOrderRefundList(PagingDto dto) {
		// TODO Auto-generated method stub
		return orderMapper.queryOrderRefundList(dto);
	}
	/**
     * 生成16位订单号
     * @return
     */
	@Override
    public String createOrderNo() {
    	String orderNo = "";
    	//两位随机大写字母
        for (int i = 0;i < 2;i++){
        	orderNo = orderNo+ (char)(Math.random()*26+'A');
        }
        //年月日
        String date = DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD);
        //生成随机6位数
        int ran = (int)((Math.random()*9+1)*100000);
        orderNo = orderNo + date + ran;
        return orderNo;
    }
	@Override
	public Integer queryActivityOrderCount(PagingDto dto) {
		// TODO Auto-generated method stub
		return orderMapper.queryActivityOrderCount(dto);
	}
	@Override
	public void updateOrderQrCode(WxOrder order) {
		// TODO Auto-generated method stub
		orderMapper.updateOrderQrCode(order);
	}
	@Override
	public void updateGoodsAmount(Integer goodsId, Integer amount) {
		//总店商品库存
		orderMapper.updateGoodsAmount(goodsId, amount);
		orderMapper.updateGoodsRemAmount(goodsId, amount);
	}
	@Override
	public String queryOrderExpressNo(PagingDto dto) {
		// TODO Auto-generated method stub
		return orderMapper.queryOrderExpressNo(dto);
	}
	@Override
	public List<WxOrder> queryOrderByExpressList() {
		// TODO Auto-generated method stub
		return orderMapper.queryOrderByExpressList();
	}

	@Override
	public List<WxOrderDetail> queryOrderRefundGoodsList(Integer id) {
		// TODO Auto-generated method stub
		List<WxOrderDetail> returnList = null;
		List<WxOrderDetail> detailList = orderMapper.queryOrderRefundGoodsList(id);
		if(detailList != null && detailList.size() > 0) {
			returnList = new ArrayList<WxOrderDetail>();
			for(int i=0;i<detailList.size();i++) {
				if(detailList.get(i).getGoodsNum() > 0) {
					detailList.get(i).setNum(0);
					returnList.add(detailList.get(i));
				}
			}
		}
		return returnList;
	}

	@Override
	public List<WxOrderDetail> queryOrderRefundGiftList(Integer id) {
		// TODO Auto-generated method stub
		return orderMapper.queryOrderRefundGiftList(id);
	}

	@Override
	public WxAddress queryOrderRefundAddress(Integer refundId) {
		// TODO Auto-generated method stub
		return orderMapper.queryOrderRefundAddress(refundId);
	}

	@Override
	public void updateRefundExpressNo(WxAddress address) {
		// TODO Auto-generated method stub
		orderMapper.updateRefundExpressNo(address);
	}

	@Override
	public void insertOrderRefund(WxOrderRefund refund) {
		// TODO Auto-generated method stub
		orderMapper.insertOrderRefund(refund);
	}
}