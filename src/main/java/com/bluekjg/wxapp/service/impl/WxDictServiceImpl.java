package com.bluekjg.wxapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxDictMapper;
import com.bluekjg.wxapp.model.WxDict;
import com.bluekjg.wxapp.service.IWxDictService;

/**
 * @description：数据字典服务实体类
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
public class WxDictServiceImpl extends ServiceImpl<WxDictMapper, WxDict>implements IWxDictService {

	protected Logger logger = LoggerFactory.getLogger(WxDictServiceImpl.class);

	@Autowired
	private WxDictMapper dictMapper;

	@Override
	public WxDict queryDictByCode(String code) {
		// TODO Auto-generated method stub
		return dictMapper.queryDictByCode(code);
	}
}