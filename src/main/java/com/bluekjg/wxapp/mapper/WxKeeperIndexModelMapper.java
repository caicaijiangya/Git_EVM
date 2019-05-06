package com.bluekjg.wxapp.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.WxKeeperIndexModel;
import com.bluekjg.wxapp.model.WxModelUser;
import com.bluekjg.wxapp.model.WxStore;

/**
 * @description：店主店员权限 数据库控制层接口
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
public interface WxKeeperIndexModelMapper extends BaseMapper<WxStore> {
	//查询登录的店主或者店员权限模块
	List<WxKeeperIndexModel> queryIndexModel(String openId);

	//添加店员权限
	Integer addUserAuth(WxModelUser modelUser);

	//取消店员权限
	Integer delUserAuth(WxModelUser modelUser);

}