package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Activity;
import com.bluekjg.admin.model.ActivityFull;
import com.bluekjg.admin.model.ActivityGoods;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.core.commons.result.PageInfo;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 活动主表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-09-28
 */
public interface IActivityService extends IService<Activity> {

	void selectDataGrid(PageInfo pageInfo, Activity activity);

	//查看可以指派活动的商品
	void queryGoodsList(PageInfo pageInfo, Activity activity);
	//查询活动门店列表
	void selectStoreDataGrid(PageInfo pageInfo, Activity activity);
	//指派商品 , 添加商品到关联表
	boolean appointGoods(Goods goods);

	//查看已指派的商品
	void queryActivityGoodsList(PageInfo pageInfo, Activity activity);
	
	//批量取消指派
	boolean updateActivityGoods(ActivityGoods activityGoods);

	//查询一个时间段内是否存在同类型活动, 避免重复添加
	Integer queryActivityList(Activity activity);

	//删除活动恢复库存
	Integer editActivityGoods(Integer id);
	void deleteActivityStore(Integer id);
	//添加活动门店
	void insertActivityStore(Activity activity);
	
	List<Map<String,Object>> selectActivityStore(Integer id);
	
	void fullDataGrid(PageInfo pageInfo, Integer id);
	void discountDataGrid(PageInfo pageInfo, Integer id);
	//编辑优惠条件
	void editActivityFull(ActivityFull full);
	//编辑优惠折扣
	void editActivityDiscount(ActivityFull full);
	
	
	public void selectActivityLabelDataGrid(PageInfo pageInfo, Integer id);
	//删除活动标签
	void deleteActivityLabel(Integer id);
	//添加活动标签
	void insertActivityLabel(String id,String name,String seq);
}
