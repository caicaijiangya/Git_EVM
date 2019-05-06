package com.bluekjg.wxapp.service;


import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxKeeperMessage;
import com.bluekjg.wxapp.model.WxStore;
import com.bluekjg.wxapp.model.wx.DataModel;

/**
 * @description：店主/店员消息通知
 * @author：pincui.Tom
 * @date：2018/7/27 14:51
 */
public interface IWxKeeperMessageService extends IService<WxStore> {

	//查询个人消息通知
	List<WxKeeperMessage> queryMessage(PagingDto page);

	//查看退款详情
	WxKeeperMessage queryRefundDetail(DataModel dataModel);

	//修改消息是否已读状态
	Integer editIsRead(WxKeeperMessage wxMessage);

	//查询消费者个人消息通知
	List<WxKeeperMessage> queryCustomerMessage(PagingDto page);

	//添加通知消息
	void insertMessage(WxKeeperMessage wxMessage);
}