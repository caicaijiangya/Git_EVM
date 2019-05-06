package com.bluekjg.wxapp.mapper;


import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxKeeperMessage;
import com.bluekjg.wxapp.model.WxStore;
import com.bluekjg.wxapp.model.wx.DataModel;

/**
 * @description：店主店员消息通知 数据库控制层接口
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
public interface WxKeeperMessageMapper extends BaseMapper<WxStore> {

	//查询个人消息通知
	List<WxKeeperMessage> queryMessage(PagingDto page);

	//查看退款详情
	WxKeeperMessage queryRefundDetail(DataModel dataModel);

	//修改消息是否已读状态
	Integer editIsRead(WxKeeperMessage wxMessage);

	//消费者个人消息通知
	List<WxKeeperMessage> queryCustomerMessage(PagingDto page);

	//添加通知消息
	boolean insertMessage(WxKeeperMessage wxMessage);
	//添加通知消息详情
	boolean insertMessageReveice(WxKeeperMessage wxMessage);
}