package com.bluekjg.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.admin.mapper.BrandStoryDetailMapper;
import com.bluekjg.admin.model.BrandStoryDetail;
import com.bluekjg.admin.service.IBrandStoryDetailService;
import com.bluekjg.core.commons.result.PageInfo;
/**
 * <p>
 *   服务实现类
 * </p>
 * @author Tim
 * @since 2018-04-13
 */
@Service
public class BrandStoryDetailServiceImpl extends ServiceImpl<BrandStoryDetailMapper, BrandStoryDetail> implements IBrandStoryDetailService {
    
	@Autowired 
    private BrandStoryDetailMapper brandStoryDetailMapper;

	@Override
	public void queryBrandStoryDetailList(PageInfo pageInfo, BrandStoryDetail brandStoryDetail) {
		Page<BrandStoryDetail> page = new Page<BrandStoryDetail>(pageInfo.getNowpage(), pageInfo.getSize());
		List<BrandStoryDetail> list = brandStoryDetailMapper.queryBrandStoryDetailList(page, brandStoryDetail);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
    
    
}
