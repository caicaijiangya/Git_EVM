package com.bluekjg.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.admin.model.BrandStoryDetail;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 *   服务类
 * </p>
 *
 * @author Tim
 * @since 2018-04-13
 */
public interface IBrandStoryDetailService extends IService<BrandStoryDetail> {

	/**
	 * 获取详情页
	 * @param pageInfo
	 * @param tXyBrandStoryDetail
	 */
	void queryBrandStoryDetailList(PageInfo pageInfo, BrandStoryDetail brandStoryDetail);

}
