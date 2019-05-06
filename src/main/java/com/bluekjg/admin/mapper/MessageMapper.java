package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Message;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 消息通知表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-09-30
 */
public interface MessageMapper extends BaseMapper<Message> {

	List<Message> selectDataGrid(Page<Message> page, Message message);

	Integer updateMessageById(Message message);

	List<Message> queryMessageDetail(Page<Message> page, Message message);


}