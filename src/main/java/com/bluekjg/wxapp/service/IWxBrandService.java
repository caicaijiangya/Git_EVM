package com.bluekjg.wxapp.service;

import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxBanner;
import com.bluekjg.wxapp.model.WxBrandStory;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxStaticFile;


/**
 * @description：BRANDSTORY 信息
 * @author：pincui.Tom
 * @date：2018/9/27 14:51
 */
public interface IWxBrandService extends IService<WxBrandStory> {

	/**
	 * 获取品牌故事列表
	 * @param page
	 * @return
	 */
	List<WxBrandStory> queryBrandList(PagingDto page);

	
	/**
	 * 获取品牌故事详情
	 * @param id
	 * @return
	 */
	WxBrandStory queryBrandDetail(PagingDto dto);


	/**
	 * 点赞操作
	 * @param page
	 * @return
	 */
	Integer brandZan(PagingDto page);
	
}