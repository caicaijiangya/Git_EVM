package com.bluekjg.wxapp.service;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.WxDict;


/**
 * @description：数据字典服务
 * @author：pincui.Tom
 * @date：2018/9/27 14:51
 */
public interface IWxDictService extends IService<WxDict> {

	/**
	 * 根据编码查询数据字典信息
	 * @param code
	 */
	WxDict queryDictByCode(String code);
}