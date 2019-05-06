package com.bluekjg.wxapp.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.WxKeeperIndexModel;
import com.bluekjg.wxapp.model.WxModelUser;
import com.bluekjg.wxapp.model.WxStore;

/**
 * @description：店主/店员权限模块
 * @author：pincui.Tom
 * @date：2018/7/27 14:51
 */
public interface IWxKeeperIndexModelService extends IService<WxStore> {
	//查询店主丶店员的权限信息
	List<WxKeeperIndexModel> queryIndexModel(String openId);

	//添加店员权限
	Integer addUserAuth(WxModelUser modelUser);

	//取消店员权限
	Integer delUserAuth(WxModelUser modelUser);

	
}