package com.bluekjg.wxapp.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.WxAccessFlow;

/**
 * @description：用户访问日志表数据库控制层接口
 * @author：pincui.tom
 * @date：2018/11/29 14:51
 */
public interface WxAccessFlowMapper extends BaseMapper<WxAccessFlow> {
	/**
	 * 访问次数+1
	 * @param flow
	 * @return
	 */
	boolean updateAccessFlow(WxAccessFlow flow);
	/**
	 * 添加新访问记录
	 * @param flow
	 * @return
	 */
	boolean insertAccessFlow(WxAccessFlow flow);
	/**
	 * 修改用户所属门店
	 * @param flow
	 * @return
	 */
	boolean updateUserStore(WxAccessFlow flow);
}