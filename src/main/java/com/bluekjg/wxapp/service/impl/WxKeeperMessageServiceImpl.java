package com.bluekjg.wxapp.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxKeeperMessageMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxKeeperMessage;
import com.bluekjg.wxapp.model.WxStore;
import com.bluekjg.wxapp.model.wx.DataModel;
import com.bluekjg.wxapp.service.IWxKeeperMessageService;

/**
 * @description：店主店员消息通知数据
 * @author：pincui.Tom 
 * @date：2018/7/27 14:51
 */
@Service
@Transactional
public class WxKeeperMessageServiceImpl extends ServiceImpl<WxKeeperMessageMapper, WxStore> implements IWxKeeperMessageService {
	protected Logger logger = LoggerFactory.getLogger(WxKeeperMessageServiceImpl.class);

	@Autowired WxKeeperMessageMapper messageMapper;
	
	//查看个人消息通知
	@Override
	public List<WxKeeperMessage> queryMessage(PagingDto page) {
		return messageMapper.queryMessage(page);
	}

	//查看退款详情
	@Override
	public WxKeeperMessage queryRefundDetail(DataModel dataModel) {
		return messageMapper.queryRefundDetail(dataModel);
	}

	//修改消息是否已读状态
	@Override
	public Integer editIsRead(WxKeeperMessage wxMessage) {
		return messageMapper.editIsRead(wxMessage);
	}

	//消费者个人消息通知
	@Override
	public List<WxKeeperMessage> queryCustomerMessage(PagingDto page) {
		return messageMapper.queryCustomerMessage(page);
	}

	@Override
	@Transactional
	public void insertMessage(WxKeeperMessage wxMessage) {
		// TODO Auto-generated method stub
		messageMapper.insertMessage(wxMessage);
		messageMapper.insertMessageReveice(wxMessage);
	}
}