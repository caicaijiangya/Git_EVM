package com.bluekjg.admin.service;

import com.bluekjg.admin.model.ActivityPromoteDto;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 活动推广 服务类
 * </p>
 *
 * @author Tom
 * @since 2019-03-04
 */
public interface IActivityPromoteService extends IService<ActivityPromoteDto> {

	void selectCouponDataGrid(PageInfo pageInfo, ActivityPromoteDto activity);
	void selectActivityDataGrid(PageInfo pageInfo, ActivityPromoteDto activity);
}
