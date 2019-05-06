package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.StoreArea;
import com.bluekjg.admin.mapper.StoreAreaMapper;
import com.bluekjg.admin.service.IStoreAreaService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 门店地市表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-10-02
 */
@Service
public class StoreAreaServiceImpl extends ServiceImpl<StoreAreaMapper, StoreArea> implements IStoreAreaService {

	@Autowired private StoreAreaMapper storeAreaMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, StoreArea storeArea) {
		Page<StoreArea> page = new Page<StoreArea>(pageInfo.getNowpage(),pageInfo.getSize());
		List<StoreArea> list = storeAreaMapper.selectDataGrid(page,storeArea);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	
}
