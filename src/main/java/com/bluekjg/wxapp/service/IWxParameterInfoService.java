package com.bluekjg.wxapp.service;


import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.admin.model.vo.RParameterInfo;
import com.bluekjg.wxapp.model.WxParameterInfo;


/**
 * @description：美顾信息表数据服务层接口
 * @author：pincui.Tim
 * @date：2018/7/04 14:51
 */
public interface IWxParameterInfoService extends IService<WxParameterInfo> {
	
	/**
	 * 获取参数详情
	 * @param appId
	 * @return
	 */
	public RParameterInfo getParameterInfoByAppId(String appId);
	
}