package com.bluekjg.wxapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxKeeperIndexModelMapper;
import com.bluekjg.wxapp.model.WxKeeperIndexModel;
import com.bluekjg.wxapp.model.WxModelUser;
import com.bluekjg.wxapp.model.WxStore;
import com.bluekjg.wxapp.service.IWxKeeperIndexModelService;

/**
 * @description：店主店员权限数据
 * @author：pincui.Tom 
 * @date：2018/7/27 14:51
 */
@Service
public class WxKeeperIndexModelServiceImpl extends ServiceImpl<WxKeeperIndexModelMapper, WxStore> implements IWxKeeperIndexModelService {
	protected Logger logger = LoggerFactory.getLogger(WxKeeperIndexModelServiceImpl.class);
	
	@Autowired WxKeeperIndexModelMapper wxIndexModelMapper;
	
	/**
	 * 查询到店取货的取货量
	 */
	@Override
	public List<WxKeeperIndexModel> queryIndexModel(String openId) {
		return wxIndexModelMapper.queryIndexModel(openId);
	}

	//添加店员权限
	@Override
	public Integer addUserAuth(WxModelUser modelUser) {
		return wxIndexModelMapper.addUserAuth(modelUser);
	}

	//取消店员权限
	@Override
	public Integer delUserAuth(WxModelUser modelUser) {
		return wxIndexModelMapper.delUserAuth(modelUser);
	}


}