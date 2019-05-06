package com.bluekjg.wxapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxBannerMapper;
import com.bluekjg.wxapp.model.WxBanner;
import com.bluekjg.wxapp.model.WxStaticFile;
import com.bluekjg.wxapp.service.IWxBannerService;

/**
 * @description：BANNER数据
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
@Transactional
public class WxBannerServiceImpl extends ServiceImpl<WxBannerMapper, WxBanner>implements IWxBannerService {

	protected Logger logger = LoggerFactory.getLogger(WxBannerServiceImpl.class);

	@Autowired
	private WxBannerMapper bannerMapper;

	@Override
	public List<WxBanner> queryBannerList(Integer type) {
		// TODO Auto-generated method stub
		return bannerMapper.queryBannerList(type);
	}

	@Override
	public List<WxStaticFile> queryFiles(Integer id, Integer fileType, Integer bigType, Integer smallType) {
		// TODO Auto-generated method stub
		return bannerMapper.queryFiles(id, fileType, bigType, smallType);
	}
	
}