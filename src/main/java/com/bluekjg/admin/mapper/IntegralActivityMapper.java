package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.IntegralActivity;
import com.bluekjg.admin.model.IntegralActivityStore;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 积分规则表 Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2019-01-16
 */
public interface IntegralActivityMapper extends BaseMapper<IntegralActivity> {

	List<IntegralActivity> selectDataGrid(Page<IntegralActivity> page, IntegralActivity activity);
	IntegralActivity selectIntegralActivityById(@Param("id") Integer id);
	boolean insertIntegralActivityStore(IntegralActivityStore activityStore);
	boolean deleteIntegralActivityStore(@Param("integralActivityId") Integer integralActivityId);
}