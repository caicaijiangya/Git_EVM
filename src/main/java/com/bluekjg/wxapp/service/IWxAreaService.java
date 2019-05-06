package com.bluekjg.wxapp.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.WxArea;


/**
 * @description：地市服务
 * @author：pincui.Tom
 * @date：2018/9/27 14:51
 */
public interface IWxAreaService extends IService<WxArea> {

	/**
	 * 根据编码查询地市信息
	 * @param areaNo
	 */
	WxArea queryAreaByNo(String areaNo);
	/**
	 * 查询门店热门地市
	 * @param storeId
	 * @return
	 */
	List<WxArea> queryStoreArea();
}