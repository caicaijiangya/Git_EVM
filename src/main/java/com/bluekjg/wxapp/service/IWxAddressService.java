package com.bluekjg.wxapp.service;

import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.admin.model.Area;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxAddress;


/**
 * @description：收货地址信息
 * @author：pincui.Tom
 * @date：2018/7/27 14:51
 */
public interface IWxAddressService extends IService<WxAddress> {

	/**
	 * 根据ID查询收货地址详情
	 * @param id
	 * @return
	 */
	WxAddress queryAddressById(Integer id);
	/**
	 * 查询我的默认收货地址
	 * @param page
	 * @return
	 */
	WxAddress queryAddressByOpenId(String openId);
	/**
	 * 查询我的收货地址
	 * @param dto
	 * @return
	 */
	List<WxAddress> queryAddressMy(PagingDto dto);
	
	//查询我的收货地址--pjf
	List<WxAddress> selectMyAddress(PagingDto page);
	
	//删除我的地址---PJF
	Integer delMyAddress(PagingDto page);
	
	//获取省份数据---pjf
	List<Area> getProvincesList(PagingDto page);
	
	//获取地市数据---pjf
	List<Area> getCitysList(PagingDto page);
	
	//获取区县数据---pjf
	List<Area> getAreasList(PagingDto page);
	
	//添加我的收获地址---pjf
	Integer addMyAddress(PagingDto page);
	
	//修改默认收货地址唯一---pjf
	Integer editDefultAddress(PagingDto page);
	
	//根据ID获取收货地址
	WxAddress getAddressById(PagingDto page);
	Integer editMyAddress(PagingDto page);
}