package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Message;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 门店信息表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-09-30
 */
public interface IMessageService extends IService<Message> {

	void selectDataGrid(PageInfo pageInfo, Message message);

	Integer updateMessageById(Message message);

	//查看消息通知详情
	void queryMessageDetail(PageInfo pageInfo, Message message);

	
}
