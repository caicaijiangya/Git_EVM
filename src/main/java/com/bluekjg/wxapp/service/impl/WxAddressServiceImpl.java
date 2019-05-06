package com.bluekjg.wxapp.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.admin.model.Area;
import com.bluekjg.wxapp.mapper.WxAddressMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxAddress;
import com.bluekjg.wxapp.service.IWxAddressService;

/**
 * @description：收货地址数据
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
public class WxAddressServiceImpl extends ServiceImpl<WxAddressMapper, WxAddress>implements IWxAddressService {

	protected Logger logger = LoggerFactory.getLogger(WxAddressServiceImpl.class);

	@Autowired
	private WxAddressMapper addressMapper;

	@Override
	public WxAddress queryAddressById(Integer id) {
		// TODO Auto-generated method stub
		return addressMapper.queryAddressById(id);
	}
	@Override
	public WxAddress queryAddressByOpenId(String openId) {
		// TODO Auto-generated method stub
		return addressMapper.queryAddressByOpenId(openId);
	}

	@Override
	public List<WxAddress> queryAddressMy(PagingDto dto) {
		// TODO Auto-generated method stub
		return addressMapper.queryAddressMy(dto);
	}

	@Override
	public List<WxAddress> selectMyAddress(PagingDto page) {
		return addressMapper.selectMyAddress(page);
	}

	@Override
	public Integer delMyAddress(PagingDto page) {
		return addressMapper.delMyAddress(page);
	}

	@Override
	public List<Area> getProvincesList(PagingDto page) {
		return addressMapper.getProvincesList(page);
	}

	@Override
	public List<Area> getCitysList(PagingDto page) {
		return addressMapper.getCitysList(page);
	}

	@Override
	public List<Area> getAreasList(PagingDto page) {
		return addressMapper.getAreasList(page);
	}

	@Override
	public Integer addMyAddress(PagingDto page) {
		return addressMapper.addMyAddress(page);
	}

	@Override
	public Integer editDefultAddress(PagingDto page) {
		return addressMapper.editDefultAddress(page);
	}

	@Override
	public WxAddress getAddressById(PagingDto page) {
		return addressMapper.getAddressById(page);
	}

	@Override
	public Integer editMyAddress(PagingDto page) {
		return addressMapper.editMyAddress(page);
	}
}