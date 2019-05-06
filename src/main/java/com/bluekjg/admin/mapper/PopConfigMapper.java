package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.PopConfig;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 首页弹窗配置表 Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2018-10-24
 */
public interface PopConfigMapper extends BaseMapper<PopConfig> {

	List<PopConfig> selectDataGrid(Page<PopConfig> page, PopConfig bean);
	boolean insertPopConfig(PopConfig bean);
	boolean updatePopConfig(PopConfig bean);
	boolean updateStatus(PopConfig bean);
	PopConfig selectPopConfigById(@Param("id") Integer id);
}