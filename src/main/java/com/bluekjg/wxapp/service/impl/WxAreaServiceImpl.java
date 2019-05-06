package com.bluekjg.wxapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxAreaMapper;
import com.bluekjg.wxapp.model.WxArea;
import com.bluekjg.wxapp.service.IWxAreaService;

/**
 * @description：地市服务实体类
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
public class WxAreaServiceImpl extends ServiceImpl<WxAreaMapper, WxArea>implements IWxAreaService {

	protected Logger logger = LoggerFactory.getLogger(WxAreaServiceImpl.class);

	@Autowired
	private WxAreaMapper areaMapper;

	@Override
	public WxArea queryAreaByNo(String areaNo) {
		// TODO Auto-generated method stub
		return areaMapper.queryAreaByNo(areaNo);
	}

	@Override
	public List<WxArea> queryStoreArea() {
		// TODO Auto-generated method stub
		return areaMapper.queryStoreArea();
	}
}