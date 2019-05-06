package com.bluekjg.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.admin.model.Analytics;
import com.bluekjg.admin.model.UserPortrait;
import com.bluekjg.core.commons.result.PageInfo;

public interface IAnalyticsService extends IService<Analytics> {

	void analyticsDataGrid(PageInfo pageInfo, Analytics analytics);

	void exportAnalytics(HttpServletResponse response, Analytics analytics);

	List<Analytics> loadOne(Analytics analytics);

	List<UserPortrait> selectTop5(Analytics analytics);

	Analytics querySourceSum(Analytics analytics);

	List<Analytics> selectVisitUvNew(Analytics analytics);

}
