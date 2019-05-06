package com.bluekjg.wxapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.core.utils.StringUtil;
import com.bluekjg.wxapp.mapper.WxKeeperMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxStore;
import com.bluekjg.wxapp.service.IWxKeeperService;

/**
 * @description：卖家数据
 * @author：pincui.Tom 
 * @date：2018/7/27 14:51
 */
@Service
public class WxKeeperServiceImpl extends ServiceImpl<WxKeeperMapper, WxStore>implements IWxKeeperService {

	protected Logger logger = LoggerFactory.getLogger(WxKeeperServiceImpl.class);

	@Autowired
	private WxKeeperMapper keeperMapper;

	/**
	 * 查询到店取货的取货量
	 */
	@Override
	public Integer queryOrderDetailCount(PagingDto page) {
		return keeperMapper.queryOrderDetailCount(page);
	}


	/**
	 * 查询销售总额
	 */
	@Override
	public Double queryOrderDetailPrice(PagingDto page) {
		Double price = keeperMapper.queryOrderDetailPrice(page);
		if(StringUtil.isEmpty(price)) {
			price = 0D;
		}
		return price;
	}

	/**
	 * 查询到店取货的订单量
	 */
	@Override
	public Integer queryOrderCount(PagingDto page) {
		return keeperMapper.queryOrderCount(page);
	}


	@Override
	public Double queryDiscountPrice(PagingDto page) {
		return keeperMapper.queryDiscountPrice(page);
	}


}