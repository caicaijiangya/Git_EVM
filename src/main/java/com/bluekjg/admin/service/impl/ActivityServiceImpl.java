package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Activity;
import com.bluekjg.admin.model.ActivityFull;
import com.bluekjg.admin.model.ActivityGoods;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.mapper.ActivityMapper;
import com.bluekjg.admin.service.IActivityService;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.StringUtils;
import com.bluekjg.wxapp.service.IWxOrderService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动主表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-09-28
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {
	
	@Autowired ActivityMapper activityMapper;
	@Autowired IWxOrderService orderService;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, Activity activity) {
		Page<Activity> page = new Page<Activity>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Activity> list = activityMapper.selectDataGrid(page,activity);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	//查看可以指派活动的商品
	@Override
	public void queryGoodsList(PageInfo pageInfo, Activity activity) {
		Page<Activity> page = new Page<Activity>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Activity> list = activityMapper.queryGoodsList(page,activity);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	//查询活动门店列表
	@Override
	public void selectStoreDataGrid(PageInfo pageInfo, Activity activity) {
		Page<Activity> page = new Page<Activity>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Activity> list = activityMapper.selectStoreDataGrid(page,activity);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	//确定指派商品, 添加到关联表
	@Override
	public boolean appointGoods(Goods goods) {
		Integer activityId = goods.getActivityId();
		Integer storeId = goods.getStoreId();
		String goodsIds = goods.getGoodsIds();
		List<Integer> goodsIdList = StringUtils.integerConvertToList(goodsIds);
		List<ActivityGoods> agList = new ArrayList<ActivityGoods>();
		if (goodsIdList != null && goodsIdList.size()>0) {
			for(int i=0;i<goodsIdList.size();i++){
				ActivityGoods activityGoods = new ActivityGoods();
				activityGoods.setActivityId(activityId);
				activityGoods.setGoodsId(goodsIdList.get(i));
				activityGoods.setStoreId(storeId);
				agList.add(activityGoods);
			}
		}
		if (agList != null && agList.size()>0) {
			//执行批量增加
			Integer n = activityMapper.appointGoods(agList);
			if (n==goodsIdList.size()) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	
	@Override
	public void queryActivityGoodsList(PageInfo pageInfo, Activity activity) {
		Page<Activity> page = new Page<Activity>(pageInfo.getNowpage(), pageInfo.getSize());
		List<Activity> list = activityMapper.queryActivityGoodsList(page, activity);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}


	@Override
	public Integer queryActivityList(Activity activity) {
		return activityMapper.queryActivityList(activity);
	}

	@Override
	public boolean updateActivityGoods(ActivityGoods activityGoods) {
		Integer b = activityMapper.updateActivityGoods(activityGoods);
		if (b>0) {
			if(activityGoods.getActivityAmount() == null) {
				activityGoods.setActivityAmount(0);
			}
			Integer c = activityMapper.updateGoodsAmount(activityGoods);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Integer editActivityGoods(Integer id) {
		List<ActivityGoods> aGoodsList = activityMapper.queryActivityGoodsAmountList(id);
		if (aGoodsList!=null && aGoodsList.size()>0 ) {
			for(ActivityGoods ag:aGoodsList){
				if(ag.getActivityAmount() != null && ag.getActivityAmount() > 0){
					orderService.updateGoodsAmount(ag.getGoodsId(), ag.getActivityAmount());
				}
			}
			activityMapper.updateActivityIsDel(id);
		}
		return 1;
	}

	@Override
	public void insertActivityStore(Activity activity) {
		// TODO Auto-generated method stub
		activityMapper.insertActivityStore(activity);
	}

	@Override
	public List<Map<String,Object>> selectActivityStore(Integer id) {
		// TODO Auto-generated method stub
		return activityMapper.selectActivityStore(id);
	}

	@Override
	public void deleteActivityStore(Integer id) {
		// TODO Auto-generated method stub
		activityMapper.deleteActivityStore(id);
	}

	@Override
	public void fullDataGrid(PageInfo pageInfo, Integer id) {
		List<ActivityFull> list = activityMapper.selectActivityFull(id);
		pageInfo.setRows(list);
	}
	
	@Override
	public void discountDataGrid(PageInfo pageInfo, Integer id) {
		// TODO Auto-generated method stub
		List<ActivityFull> list = activityMapper.selectActivityDiscount(id);
		pageInfo.setRows(list);
	}

	@Override
	public void editActivityFull(ActivityFull full) {
		// TODO Auto-generated method stub
		String fullPrices[] = full.getFullPrices().split(",");
		String prePrices[] = full.getPrePrices().split(",");
		activityMapper.deleteActivityFull(full.getActivityId());
		if(fullPrices.length == prePrices.length) {
			for(int i=0;i<fullPrices.length;i++) {
				if(fullPrices[i].length()>0 && prePrices[i].length()>0) {
					full.setFullPrice(Double.valueOf(fullPrices[i]));
					full.setPrePrice(Double.valueOf(prePrices[i]));
					activityMapper.insertActivityFull(full);
				}
			}
		}
	}

	@Override
	public void editActivityDiscount(ActivityFull full) {
		// TODO Auto-generated method stub
				String fullPrices[] = full.getFullPrices().split(",");
				String prePrices[] = full.getPrePrices().split(",");
				activityMapper.deleteActivityDiscount(full.getActivityId());
				if(fullPrices.length == prePrices.length) {
					for(int i=0;i<fullPrices.length;i++) {
						if(fullPrices[i].length()>0 && prePrices[i].length()>0) {
							full.setFullPrice(Double.valueOf(fullPrices[i]));
							full.setPrePrice(Double.valueOf(prePrices[i]));
							activityMapper.insertActivityDiscount(full);
						}
					}
				}
	}

	@Override
	public void deleteActivityLabel(Integer id) {
		// TODO Auto-generated method stub
		activityMapper.deleteActivityLabel(id);
	}

	@Override
	public void insertActivityLabel(String id, String name, String seq) {
		// TODO Auto-generated method stub
		activityMapper.insertActivityLabel(id, name, seq);
	}

	@Override
	public void selectActivityLabelDataGrid(PageInfo pageInfo, Integer id) {
		List<Map<String,Object>> list = activityMapper.selectActivityLabel(id);
		pageInfo.setRows(list);
	}

}
	
