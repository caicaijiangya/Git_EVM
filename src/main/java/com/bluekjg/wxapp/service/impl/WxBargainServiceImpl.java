package com.bluekjg.wxapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxBargainMapper;
import com.bluekjg.wxapp.mapper.WxCouponMapper;
import com.bluekjg.wxapp.mapper.WxIntegralMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxBargainGoods;
import com.bluekjg.wxapp.model.WxBargainGoodsDetail;
import com.bluekjg.wxapp.model.WxBargainGoodsUserLog;
import com.bluekjg.wxapp.model.WxCoupon;
import com.bluekjg.wxapp.service.IWxBargainService;
import com.bluekjg.wxapp.service.IWxCouponService;

/**
 * @description：砍价信息
 * @author：pincui.Tom 
 * @date：2018/12/11 14:51
 */
@Service
@Transactional
public class WxBargainServiceImpl extends ServiceImpl<WxBargainMapper, WxBargainGoods>implements IWxBargainService {

	protected Logger logger = LoggerFactory.getLogger(WxBargainServiceImpl.class);

	@Autowired
	private WxBargainMapper bargainMapper;
	@Autowired 
	private WxIntegralMapper integralMapper;
	@Autowired
	private IWxCouponService couponService;

	@Override
	public WxBargainGoods queryBargainById(PagingDto page) {
		// TODO Auto-generated method stub
		return bargainMapper.queryBargainById(page);
	}
	@Override
	public Integer queryBargainStatusById(PagingDto page) {
		// TODO Auto-generated method stub
		return bargainMapper.queryBargainStatusById(page);
	}
	@Override
	public Integer queryBargainStatus(PagingDto page) {
		// TODO Auto-generated method stub
		return bargainMapper.queryBargainStatus(page);
	}

	@Override
	public WxActivityGoods selectActivityById(Integer storeId,Integer id, Integer goodsId) {
		// TODO Auto-generated method stub
		return bargainMapper.selectActivityById(storeId,id, goodsId);
	}

	@Override
	@Transactional
	public void insertBargainGoods(WxBargainGoods bargain) {
		// TODO Auto-generated method stub
		bargainMapper.insertBargainGoods(bargain);
		WxBargainGoodsDetail bargainDetail = new WxBargainGoodsDetail();
		bargainDetail.setBargainId(bargain.getId());
		bargainDetail.setOpenId(bargain.getOpenId());
		bargainDetail.setPrice(bargain.getJoinPrice());
		bargainDetail.setIsBargain(0);
		bargainMapper.insertBargainGoodsDetail(bargainDetail);
	}

	@Override
	@Transactional
	public void insertBargainGoodsDetail(WxBargainGoodsDetail bargainDetail) {
		// TODO Auto-generated method stub
		bargainMapper.insertBargainGoodsDetail(bargainDetail);
		WxBargainGoods bargain = new WxBargainGoods();
		bargain.setId(bargainDetail.getBargainId());
		bargain.setJoinPrice(bargainDetail.getPrice());
		bargain.setStatus(bargainDetail.getStatus());
		bargainMapper.updateBargainGoods(bargain);
	}
	@Override
	public Integer queryBargainGoodsDetailCount(PagingDto page) {
		// TODO Auto-generated method stub
		return bargainMapper.queryBargainGoodsDetailCount(page);
	}
	@Override
	public WxBargainGoods queryBargainGoodsId(PagingDto page) {
		// TODO Auto-generated method stub
		return bargainMapper.queryBargainGoodsId(page);
	}
	@Override
	public Integer queryBargainGoodsCount(PagingDto page) {
		// TODO Auto-generated method stub
		return bargainMapper.queryBargainGoodsCount(page);
	}
	@Override
	public void updateBargainGoods(WxBargainGoods bargain) {
		// TODO Auto-generated method stub
		bargainMapper.updateBargainGoods(bargain);
	}
	@Override
	public Integer queryBargainGoodsUserLogCount(PagingDto page) {
		// TODO Auto-generated method stub
		return bargainMapper.queryBargainGoodsUserLogCount(page);
	}
	@Override
	@Transactional
	public void insertBargainGoodsUserLog(WxBargainGoodsUserLog log) {
		bargainMapper.insertBargainGoodsUserLog(log);
		if(log.getType() == 1) {
			//积分
			UserBean user = new UserBean();
    		user.setOpenId(log.getOpenId());
    		user.setIntegral(log.getValue());
    		integralMapper.updateUserIntegral(user);
		}else if(log.getType() == 2) {
			//优惠券
			couponService.insertUserCoupon(log.getOpenId(),log.getValue(),1,log.getActivityId());
		}
	}
	@Override
	public Integer queryBargainGoodsAmountCount(PagingDto page) {
		// TODO Auto-generated method stub
		return bargainMapper.queryBargainGoodsAmountCount(page);
	}
}