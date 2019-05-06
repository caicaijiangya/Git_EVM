package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.StoreArea;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 门店地市表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-10-02
 */
public interface StoreAreaMapper extends BaseMapper<StoreArea> {

	List<StoreArea> selectDataGrid(Page<StoreArea> page, StoreArea storeArea);

}