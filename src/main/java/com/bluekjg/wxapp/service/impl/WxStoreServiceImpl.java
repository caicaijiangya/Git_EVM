package com.bluekjg.wxapp.service.impl;

import java.util.List;

import com.bluekjg.wxapp.model.wxApp20.StoreDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxStoreMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxStore;
import com.bluekjg.wxapp.model.wx.DataModel;
import com.bluekjg.wxapp.service.IWxStoreService;

import net.sf.json.util.JSONStringer;

/**
 * @description：门店数据
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
public class WxStoreServiceImpl extends ServiceImpl<WxStoreMapper, WxStore>implements IWxStoreService {

	protected Logger logger = LoggerFactory.getLogger(WxStoreServiceImpl.class);

	@Autowired
	private WxStoreMapper storeMapper;

	@Override
	public WxStore queryStoreById(Integer id) {
		return storeMapper.queryStoreById(id);
	}

	@Override
	public List<WxStore> queryStoreByArea(PagingDto dto) {
		// TODO Auto-generated method stub
		return storeMapper.queryStoreByArea(dto);
	}


	@Override
	public List<WxStore> queryAllStore() {
		return storeMapper.queryAllStore();
	}


	@Override
	public WxStore queryStoreByOpenId(String openId) {
		// TODO Auto-generated method stub
		return storeMapper.queryStoreByOpenId(openId);
	}
	@Override
	public List<StoreDto> queryStoreByAreaId(PagingDto dto) {
		// TODO Auto-generated method stub
		return storeMapper.queryStoreByAreaId(dto);
	}

	public List<WxStore> queryStoreListByAreaId(Integer areaId){

		return storeMapper.queryStoreListByAreaId(areaId);
	}
	public String queryCodeByAreaId(Integer areaId){

		return storeMapper.queryCodeByAreaId(areaId);
	}
	public String queryNameByAreaId(Integer areaId){

		return storeMapper.queryNameByAreaId(areaId);
	}


}