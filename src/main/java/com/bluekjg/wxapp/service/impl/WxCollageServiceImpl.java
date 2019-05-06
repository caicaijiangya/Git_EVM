package com.bluekjg.wxapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxCollageMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.model.WxCollageGoodsDetail;
import com.bluekjg.wxapp.service.IWxCollageService;
import com.bluekjg.wxapp.service.IWxErpService;

/**
 * @description：拼团数据
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
public class WxCollageServiceImpl extends ServiceImpl<WxCollageMapper, WxCollageGoods>implements IWxCollageService {

	protected Logger logger = LoggerFactory.getLogger(WxCollageServiceImpl.class);

	@Autowired
	private WxCollageMapper collageMapper;
	@Autowired
	private IWxErpService erpService;

	@Override
	public WxCollageGoods queryCollageObj(PagingDto dto) {
		// TODO Auto-generated method stub
		return collageMapper.queryCollageObj(dto);
	}

	@Override
	public void updateCollageNum(WxCollageGoods bean) {
		// TODO Auto-generated method stub
		WxCollageGoods collage = collageMapper.queryCollageJoinNum(bean);
		if(collage != null) {
			bean.setId(collage.getId());
			bean.setJoinNum(1);
			bean.setStatus(1);
			collageMapper.updateCollageNum(bean);
			collageMapper.updateCollageDetailStatus(bean);
			if(collage.getCollageNum() == collage.getJoinNum()+1) {
				collageMapper.updateCollageStatus(bean);
				List<WxCollageGoodsDetail> details = collageMapper.queryCollageDetail(collage.getId());
				if(details != null && details.size() > 0) {
					for(WxCollageGoodsDetail detail:details) {
						erpService.pushOrderErp(detail.getOrderId());
					}
				}
			}
		}
	}

	@Override
	public WxCollageGoods queryCollageJoinNum(WxCollageGoods bean) {
		// TODO Auto-generated method stub
		return collageMapper.queryCollageJoinNum(bean);
	}

	@Override
	public void updateCollageDetailStatus(WxCollageGoods bean) {
		// TODO Auto-generated method stub
		collageMapper.updateCollageDetailStatus(bean);
	}

	@Override
	public Integer queryCollageByIsUser(PagingDto dto) {
		// TODO Auto-generated method stub
		return collageMapper.queryCollageByIsUser(dto);
	}

	@Override
	public Integer queryCollageGoodsId(Integer orderId) {
		// TODO Auto-generated method stub
		return collageMapper.queryCollageGoodsId(orderId);
	}
}