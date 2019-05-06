package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Store;
import com.bluekjg.admin.model.StoreGoods;
import com.bluekjg.admin.mapper.GoodsMapper;
import com.bluekjg.admin.mapper.StoreGoodsMapper;
import com.bluekjg.admin.mapper.StoreMapper;
import com.bluekjg.admin.service.IStoreService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 门店信息表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-09-30
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements IStoreService {

	@Autowired private StoreMapper storeMapper;
	@Autowired private StoreGoodsMapper storeGoodsMapper;
	@Autowired GoodsMapper goodsMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, Store store) {
		Page<Store> page = new Page<Store>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Store> list = storeMapper.selectDataGrid(page,store);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public Integer updateStoreById(Store store) {
		List<StoreGoods> list = storeGoodsMapper.queryStoreGoodsList(store.getId());
		if(list != null && list.size() > 0) {
			for(StoreGoods sg : list) {
				if(sg.getGoodsAmount() != null && sg.getGoodsAmount() > 0)
				goodsMapper.updateAmount(sg.getGoodsId(), sg.getGoodsAmount() * -1);
				sg.setGoodsAmount(sg.getGoodsAmount()*-1);
				storeGoodsMapper.updateStoreGoods(sg);
			}
		}
		Integer n  =storeMapper.updateStoreById(store); 
		return n;
	}

	@Override
	public Integer insertStore(Store store) {
		Integer n = storeMapper.insertStore(store);
		return n;
	}

	@Override
	public Store selectStoreById(Integer id) {
		Store store = storeMapper.selectStoreById(id);
		return store;
	}

	@Override
	public Integer updateInfoById(Store store) {
		Integer n = storeMapper.updateInfoById(store);
		return n;
	}

	@Override
	public List<Store> queryStoreList() {
		return storeMapper.queryStoreList();
	}

	@Override
	public void updateStoreQrCode(Store store) {
		// TODO Auto-generated method stub
		storeMapper.updateStoreQrCode(store);
	}

	@Override
	public void queryStoreDataGrid(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		List<Store> list = storeMapper.queryStoreList();
		Store store = new Store();
		store.setId(0);
		store.setStoreName("小程序");
		list.add(0,store);
		pageInfo.setRows(list);
	}
	
}
