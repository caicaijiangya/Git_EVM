package com.bluekjg.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.admin.model.Analytics;
import com.bluekjg.admin.model.UserPortrait;

public interface UserPortraitMapper extends BaseMapper<UserPortrait> {

	void insertBatch(List<UserPortrait> list);

	List<UserPortrait> selectTop5(Analytics analytics);

	List<Integer> selectNumByCity(Analytics analytics);

}
