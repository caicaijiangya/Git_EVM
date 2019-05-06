package com.bluekjg.wxapp.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.bluekjg.core.commons.utils.ConstantUtils;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.core.commons.utils.Util;
import com.bluekjg.wxapp.mapper.WxOrderMapper;
import com.bluekjg.wxapp.model.PayDto;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.model.WxCollageGoodsDetail;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderRefund;
import com.bluekjg.wxapp.model.WxOrderTrans;
import com.bluekjg.wxapp.pay.MD5;
import com.bluekjg.wxapp.pay.MD5Util;
import com.bluekjg.wxapp.pay.MdlPayUtil;
import com.bluekjg.wxapp.pay.ResultBean;
import com.bluekjg.wxapp.pay.TransfersUtil;
import com.bluekjg.wxapp.service.IWxCollageService;
import com.bluekjg.wxapp.service.IWxErpService;
import com.bluekjg.wxapp.service.IWxPayService;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

/**
 * @description：支付数据
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
@Transactional
public class WxPayServiceImpl extends ServiceImpl<WxOrderMapper, WxOrder>implements IWxPayService {

	protected Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);

	@Autowired
	private WxOrderMapper orderMapper;
	@Autowired
	private IWxCollageService collageService;
	@Autowired
	private IWxErpService erpService;

	@Override
	public void paySuccesssNotice(PayDto payDto) {
		try {
			WxOrderTrans trans = orderMapper.selectOrderTransByTransNo(payDto.getOutTradeNo());
			if(trans != null) {
				if(!StringUtils.isEmpty(payDto.getCouponFee())){
					double couponFee = Util.div(Double.valueOf(payDto.getCouponFee()), 100D);
					trans.setCouponFee(BigDecimal.valueOf(couponFee));
				}else{
					trans.setCouponFee(BigDecimal.valueOf(0D));
				}
				trans.setStatus(1);
				orderMapper.updateOrderStatus(trans.getOrderId(), trans.getStatus(),0);
				trans.setStatus(1);
				orderMapper.updateOrderTransStatus(trans);
				
				/*
				 * 团购订单处理
				 */
				WxOrder order = orderMapper.queryOrderById(trans.getOrderId());
				if(order.getOrderType() != 2) {
					//ERP订单接口推送
					erpService.pushOrderErp(trans.getOrderId());
				}
				if(order != null && order.getOrderType() == 2) {
					WxCollageGoods collageObj = new WxCollageGoods();
					collageObj.setActivityId(order.getActivityId());
					collageObj.setGoodsId(order.getDetails().get(0).getSpecId());
					collageObj.setOpenId(order.getOpenId());
					collageObj.setOrderId(order.getId());
					collageObj.setStoreId(order.getStoreId());
					WxCollageGoods collage = collageService.queryCollageJoinNum(collageObj);
					if(collage != null) {
						collageService.updateCollageNum(collageObj);
					}
					
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void refundSuccesssNotice(PayDto payDto) {
		WxOrderRefund refund = orderMapper.selectOrderTransByRefundNo(payDto.getOutRefundNo());
		if(refund != null) {
			orderMapper.updateOrderRefundStatus(refund.getRefundId(), 4,null);
			//订单剩余商品数量为0
			if(refund.getGoodsNum() == 0) {
				orderMapper.updateOrderStatus(refund.getOrderId(), 4,3);
				WxOrderTrans trans = new WxOrderTrans();
				trans.setOrderId(refund.getOrderId());
				trans.setStatus(3);
				trans.setRefundBalances(refund.getBalances());
				orderMapper.updateOrderTransStatus(trans);
				if(refund.getCouponId() != null && refund.getCouponId() > 0) {
					orderMapper.updateCouponStatus(refund.getCouponId(), 0);//修改优惠券为可用
				}
			}
		}
	}

	@Override
	public Integer wxrefund(HttpServletRequest request,WxOrderRefund refund) {
		Integer result  = 0;
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		Random random = new Random();
		String randomStr = MD5.GetMD5String(String.valueOf(random.nextInt(10000)));
		String nonce_str = MD5Util.MD5Encode(randomStr, "utf-8").toLowerCase();
		parameters.put("appid", WxappConfigUtil.WX_APPID);
		parameters.put("mch_id", WxappConfigUtil.WX_MERCHANTS_APPID);
//		parameters.put("sub_appid", MdlPayUtil.WX_APPID);
		parameters.put("nonce_str", nonce_str);
		parameters.put("out_trade_no", refund.getTransNo());// "微信支付订单中的out_trade_no"
		parameters.put("out_refund_no", refund.getRefundNo()); // 我们自己设定的退款申请号，约束为UK
		parameters.put("total_fee", TransfersUtil.getMoney("￥" + refund.getTotalBalances())); // 单位为分
		parameters.put("refund_fee", TransfersUtil.getMoney("￥" + refund.getBalances())); // 单位为分(不退运费)
		String sign = TransfersUtil.createSign("utf-8", parameters, WxappConfigUtil.WX_MERCHANTS_SECRET);
		parameters.put("sign", sign);
		String reuqestXml = TransfersUtil.getRequestXml(parameters);
		ResultBean resultBean =  TransfersUtil.refund(reuqestXml);
		if (resultBean != null && resultBean.getResult() == ConstantUtils.SUCCESS){//成功
			PayDto payDto = new PayDto();
			payDto.setOutRefundNo(refund.getRefundNo());
			refundSuccesssNotice(payDto);
			result = 1;
		}
		return result;
	}
}