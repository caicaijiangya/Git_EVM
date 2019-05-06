package com.bluekjg.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bluekjg.admin.model.BrandStory;
import com.bluekjg.admin.model.Goods;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author Tim
 * @since 2018-04-12
 */
public interface BrandStoryMapper extends BaseMapper<BrandStory> {

	/**
	 * 获取品牌有话说内容
	 * @param page
	 * @param tXyBrandStory
	 * @return
	 */
	List<BrandStory> queryBrandStoryList(Page<BrandStory> page, BrandStory brandStory);

	/**
	 * 获取跳转的商品列表
	 * @return
	 */
	List<Goods> queryGoodsList();

}