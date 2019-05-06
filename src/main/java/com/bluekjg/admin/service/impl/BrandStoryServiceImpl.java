package com.bluekjg.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.admin.mapper.BrandStoryMapper;
import com.bluekjg.admin.model.BrandStory;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.service.IBrandStoryService;
import com.bluekjg.core.commons.result.PageInfo;
/**
 * <p>
 *   服务实现类
 * </p>
 * @author Tim
 * @since 2018-04-12
 */
@Service
public class BrandStoryServiceImpl extends ServiceImpl<BrandStoryMapper, BrandStory> implements IBrandStoryService {
   
	@Autowired 
    private BrandStoryMapper brandStoryMapper;

	@Override
	public void queryBrandStoryList(PageInfo pageInfo, BrandStory brandStory) {
		Page<BrandStory> page = new Page<BrandStory>(pageInfo.getNowpage(), pageInfo.getSize());
		List<BrandStory> list = brandStoryMapper.queryBrandStoryList(page, brandStory);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	//获取商品列表
	@Override
	public List<Goods> queryGoodsList() {
		return brandStoryMapper.queryGoodsList();
	}
    
    
}
