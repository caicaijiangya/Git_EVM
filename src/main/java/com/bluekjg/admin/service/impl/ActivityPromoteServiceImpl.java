package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.ActivityPromoteDto;
import com.bluekjg.admin.mapper.ActivityPromoteMapper;
import com.bluekjg.admin.service.IActivityPromoteService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动推广 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2019-03-04
 */
@Service
public class ActivityPromoteServiceImpl extends ServiceImpl<ActivityPromoteMapper, ActivityPromoteDto> implements IActivityPromoteService {

	@Autowired private ActivityPromoteMapper activityPromoteMapper;

	@Override
	public void selectCouponDataGrid(PageInfo pageInfo, ActivityPromoteDto activity) {
		Page<ActivityPromoteDto> page = new Page<ActivityPromoteDto>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Map<String,Object>> list = activityPromoteMapper.selectCouponDataGrid(page,activity);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void selectActivityDataGrid(PageInfo pageInfo, ActivityPromoteDto activity) {
		Page<ActivityPromoteDto> page = new Page<ActivityPromoteDto>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Map<String,Object>> list = activityPromoteMapper.selectActivityDataGrid(page,activity);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
}
