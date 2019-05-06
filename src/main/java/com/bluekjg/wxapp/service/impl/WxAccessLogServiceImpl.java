package com.bluekjg.wxapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxAccessLogMapper;
import com.bluekjg.wxapp.model.AccessLog;
import com.bluekjg.wxapp.service.IWxAccessLogService;

/**
 * @description：用户访问日志数据
 * @author：pincui.Tom 
 * @date：2019/03/05 14:51
 */
@Service
public class WxAccessLogServiceImpl extends ServiceImpl<WxAccessLogMapper, AccessLog>implements IWxAccessLogService {

	protected Logger logger = LoggerFactory.getLogger(WxAccessLogServiceImpl.class);

	@Autowired
	private WxAccessLogMapper accessLogMapper;

	@Override
	public void insertGoodsAccessLog(AccessLog log) {
		// TODO Auto-generated method stub
		accessLogMapper.insertGoodsAccessLog(log);
	}

	@Override
	public void insertActivityAccessLog(AccessLog log) {
		// TODO Auto-generated method stub
		accessLogMapper.insertActivityAccessLog(log);
	}

	@Override
	public Integer queryUserNewOld(String openId) {
		// TODO Auto-generated method stub
		return accessLogMapper.queryUserNewOld(openId);
	}

	
}