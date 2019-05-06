package com.bluekjg.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bluekjg.admin.model.BrandStoryDetail;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author Tim
 * @since 2018-04-13
 */
public interface BrandStoryDetailMapper extends BaseMapper<BrandStoryDetail> {

	/**
	 * 获取品牌故事详情
	 * @param page
	 * @param tXyBrandStoryDetail
	 * @return
	 */
	List<BrandStoryDetail> queryBrandStoryDetailList(Page<BrandStoryDetail> page,
			BrandStoryDetail brandStoryDetail);

}