package com.bluekjg.wxapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxAccessFlowMapper;
import com.bluekjg.wxapp.model.WxAccessFlow;
import com.bluekjg.wxapp.service.IWxAccessFlowService;

/**
 * @description：用户访问日志数据
 * @author：pincui.Tom 
 * @date：2018/11/29 14:51
 */
@Service
public class WxAccessFlowServiceImpl extends ServiceImpl<WxAccessFlowMapper, WxAccessFlow>implements IWxAccessFlowService {

	protected Logger logger = LoggerFactory.getLogger(WxAccessFlowServiceImpl.class);

	@Autowired
	private WxAccessFlowMapper accessFlowMapper;

	@Override
	public void addAccessFlow(WxAccessFlow flow) {
		if(flow.getType() != null && flow.getOpenId() != null && flow.getStoreId() != null) {
			boolean bool = accessFlowMapper.updateAccessFlow(flow);
			if(!bool) {
				accessFlowMapper.insertAccessFlow(flow);
			}
		}
		
	}

	@Override
	public void updateUserStore(WxAccessFlow flow) {
		accessFlowMapper.updateUserStore(flow);
	}
}