package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.IntegralActivity;
import com.bluekjg.admin.model.IntegralActivityStore;
import com.bluekjg.admin.mapper.IntegralActivityMapper;
import com.bluekjg.admin.service.IIntegralActivityService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 积分规则表 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2019-01-16
 */
@Service
@Transactional
public class IntegralActivityServiceImpl extends ServiceImpl<IntegralActivityMapper, IntegralActivity> implements IIntegralActivityService {
	
	@Autowired IntegralActivityMapper integralActivityMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, IntegralActivity activity) {
		Page<IntegralActivity> page = new Page<IntegralActivity>(pageInfo.getNowpage(),pageInfo.getSize());
		List<IntegralActivity> list = integralActivityMapper.selectDataGrid(page,activity);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public IntegralActivity selectIntegralActivityById(Integer id) {
		// TODO Auto-generated method stub
		return integralActivityMapper.selectIntegralActivityById(id);
	}

	@Override
	@Transactional
	public void insertIntegralActivity(IntegralActivity activity) {
		// TODO Auto-generated method stub
		integralActivityMapper.insert(activity);
		integralActivityMapper.deleteIntegralActivityStore(activity.getId());
		if(activity.getActivityStoreIds() != null && activity.getActivityStoreIds().length() > 0) {
			String activityStoreIds[] = activity.getActivityStoreIds().split(",");
			for(String storeId:activityStoreIds) {
				if(storeId != null && storeId.length() > 0) {
					IntegralActivityStore activityStore = new IntegralActivityStore();
					activityStore.setIntegralActivityId(activity.getId());
					activityStore.setStoreId(Integer.valueOf(storeId));
					integralActivityMapper.insertIntegralActivityStore(activityStore);
				}
			}
		}
	}

	@Override
	@Transactional
	public void updateIntegralActivity(IntegralActivity activity) {
		// TODO Auto-generated method stub
		integralActivityMapper.updateById(activity);
		integralActivityMapper.deleteIntegralActivityStore(activity.getId());
		if(activity.getActivityStoreIds() != null && activity.getActivityStoreIds().length() > 0) {
			String activityStoreIds[] = activity.getActivityStoreIds().split(",");
			for(String storeId:activityStoreIds) {
				if(storeId != null && storeId.length() > 0) {
					IntegralActivityStore activityStore = new IntegralActivityStore();
					activityStore.setIntegralActivityId(activity.getId());
					activityStore.setStoreId(Integer.valueOf(storeId));
					integralActivityMapper.insertIntegralActivityStore(activityStore);
				}
			}
		}
	}
}
	
