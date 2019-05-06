package com.bluekjg.wxapp.service;

import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.WxBanner;
import com.bluekjg.wxapp.model.WxStaticFile;


/**
 * @description：BANNER信息
 * @author：pincui.Tom
 * @date：2018/9/27 14:51
 */
public interface IWxBannerService extends IService<WxBanner> {
	/**
	 * 查询BANNER列表
	 * @param page
	 * @return
	 */
	List<WxBanner> queryBannerList(Integer type);
	
	/**
	 * 查询静态文件
	 * @param id
	 * @param fileType
	 * @param bigType
	 * @param smallType
	 * @return
	 */
	List<WxStaticFile> queryFiles(Integer id,Integer fileType,Integer bigType,Integer smallType);
}