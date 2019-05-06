package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Activity;
import com.bluekjg.admin.model.ActivityFull;
import com.bluekjg.admin.model.ActivityGoods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 活动主表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-09-28
 */
public interface ActivityMapper extends BaseMapper<Activity> {

	List<Activity> selectDataGrid(Page<Activity> page, Activity activity);

	//查看可以指派给活动的商品
	List<Activity> queryGoodsList(Page<Activity> page, Activity activity);
	//查询活动门店列表
	List<Activity> selectStoreDataGrid(Page<Activity> page, Activity activity);
	
	//确定指派商品 , 添加到关联表
	Integer appointGoods(List<ActivityGoods> agList);

	//查看已指派活动的商品
	List<Activity> queryActivityGoodsList(Page<Activity> page, Activity activity);

	//取消已指派的商品
	Integer updateActivityGoods(ActivityGoods activityGoods);

	Integer queryActivityList(Activity activity);
	
	//删除图片表相应图片
	void delActivityGoodsImg(List<Integer> goodsIdList, ActivityGoods activityGoods);

	//取消指派恢复库存
	Integer updateGoodsAmount(ActivityGoods activityGoods);

	List<ActivityGoods> queryActivityGoodsInfo(Integer id);

	Integer updateGoods(List<ActivityGoods> aGoodsList);
	List<ActivityGoods> queryActivityGoodsAmountList(@Param("id") Integer id);
	boolean updateActivityIsDel(@Param("id") Integer id);
	boolean deleteActivityStore(@Param("id") Integer id);
	boolean insertActivityStore(Activity activity);
	
	List<Map<String,Object>> selectActivityStore(@Param("id") Integer id);
	//查询优惠条件
	List<ActivityFull> selectActivityFull(@Param("id") Integer id);
	List<ActivityFull> selectActivityDiscount(@Param("id") Integer id);
	boolean deleteActivityFull(@Param("id") Integer id);
	boolean insertActivityFull(ActivityFull full);
	boolean deleteActivityDiscount(@Param("id") Integer id);
	boolean insertActivityDiscount(ActivityFull full);
	//活动标签
	List<Map<String,Object>> selectActivityLabel(@Param("id") Integer id);
	boolean deleteActivityLabel(@Param("id") Integer id);
	boolean insertActivityLabel(@Param("id") String id,@Param("name") String name,@Param("seq") String seq);
}