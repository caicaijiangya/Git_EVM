package com.bluekjg.admin.service;

import com.bluekjg.admin.model.IntegralActivity;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 活动规则表 服务类
 * </p>
 *
 * @author Tom
 * @since 2019-01-16
 */
public interface IIntegralActivityService extends IService<IntegralActivity> {

	void selectDataGrid(PageInfo pageInfo, IntegralActivity activity);
	IntegralActivity selectIntegralActivityById(Integer id);
	void insertIntegralActivity(IntegralActivity activity);
	void updateIntegralActivity(IntegralActivity activity);
}
