package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Message;
import com.bluekjg.admin.mapper.MessageMapper;
import com.bluekjg.admin.service.IMessageService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息通知表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-09-30
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

	@Autowired private MessageMapper messageMapper;

	@Override
	public void selectDataGrid(PageInfo pageInfo, Message message) {
		Page<Message> page = new Page<Message>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Message> list = messageMapper.selectDataGrid(page,message);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public Integer updateMessageById(Message message) {
		return messageMapper.updateMessageById(message);
	}

	@Override
	public void queryMessageDetail(PageInfo pageInfo, Message message) {
		Page<Message> page = new Page<Message>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Message> list = messageMapper.queryMessageDetail(page,message);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	
}
