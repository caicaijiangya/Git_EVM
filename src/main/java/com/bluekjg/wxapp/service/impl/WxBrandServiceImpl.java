package com.bluekjg.wxapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.core.utils.StringUtil;
import com.bluekjg.wxapp.mapper.WxBrandMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxBrandStory;
import com.bluekjg.wxapp.service.IWxBrandService;

/**
 * @description：BANNER数据
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
@Transactional
public class WxBrandServiceImpl extends ServiceImpl<WxBrandMapper, WxBrandStory>implements IWxBrandService {

	protected Logger logger = LoggerFactory.getLogger(WxBrandServiceImpl.class);

	@Autowired
	private WxBrandMapper brandMapper;

	@Override
	public List<WxBrandStory> queryBrandList(PagingDto dto) {
		return brandMapper.queryBrandList(dto);
	}

	@Override
	public WxBrandStory queryBrandDetail(PagingDto dto) {
		//更新浏览量
		Integer result = brandMapper.modifyBrandInfo(dto);
		WxBrandStory storyInfo = brandMapper.queryBrandDetail(dto);
		String brandDesc = storyInfo.getBrandDesc();
		List<String> descList = StringUtil.stringConvertList(brandDesc,"~");
		if(descList!=null && descList.size()>0){
			storyInfo.setStoreDesc(descList);
		}
		return storyInfo;
	}

	
	/**
	 * 点赞操作
	 */
	@Override
	public Integer brandZan(PagingDto page) {
		Integer result = 0;
		if(page.getStatus()>0){
			result = brandMapper.insertBrandZanInfo(page);
		}else{
			result = brandMapper.delBrandZanInfo(page);
		}
		return result;
	}

	
}