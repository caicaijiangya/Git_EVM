package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.ActivityPromoteDto;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 活动推广 Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2019-03-04
 */
public interface ActivityPromoteMapper extends BaseMapper<ActivityPromoteDto> {

	List<Map<String,Object>> selectCouponDataGrid(Page<ActivityPromoteDto> page,ActivityPromoteDto activity);
	
	List<Map<String,Object>> selectActivityDataGrid(Page<ActivityPromoteDto> page,ActivityPromoteDto activity);
}