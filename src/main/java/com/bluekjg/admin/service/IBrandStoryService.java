package com.bluekjg.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.admin.model.BrandStory;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 *   服务类
 * </p>
 *
 * @author Tim
 * @since 2018-04-12
 */
public interface IBrandStoryService extends IService<BrandStory> {

	void queryBrandStoryList(PageInfo pageInfo, BrandStory brandStory);

	//获取商品
	List<Goods> queryGoodsList();

}
