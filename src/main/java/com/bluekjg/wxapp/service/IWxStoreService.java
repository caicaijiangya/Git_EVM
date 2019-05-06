package com.bluekjg.wxapp.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxStore;
import com.bluekjg.wxapp.model.wx.DataModel;
import com.bluekjg.wxapp.model.wxApp20.StoreDto;


/**
 * @description：门店信息
 * @author：pincui.Tom
 * @date：2018/7/27 14:51
 */
public interface IWxStoreService extends IService<WxStore> {

	/**
	 * 根据用户查询门店信息
	 * @param openId
	 * @return
	 */
	WxStore queryStoreByOpenId(String openId);
	/**
	 * 根据门店ID查询门店信息
	 * @param id
	 */
	WxStore queryStoreById(Integer id);
	/**
	 * 根据地市查询门店列表
	 * @param city
	 * @return
	 */
	List<WxStore> queryStoreByArea(PagingDto dto);
	List<WxStore> queryAllStore();

	List<StoreDto> queryStoreByAreaId(PagingDto dto);


	/**
	 * 通用接口，获取门店列表
	 */
	List<WxStore> queryStoreListByAreaId(Integer areaId);
	String queryCodeByAreaId(Integer areaId);
	String queryNameByAreaId(Integer areaId);


}