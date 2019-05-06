package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.GoodsClassify;
import com.bluekjg.admin.model.Store;
import com.bluekjg.admin.mapper.GoodsMapper;
import com.bluekjg.admin.mapper.GoodsOtherMapper;
import com.bluekjg.admin.service.IGoodsOtherService;
import com.bluekjg.admin.service.IStoreService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品主表 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2018-09-25
 */
@Service
@Transactional
public class GoodsOtherServiceImpl extends ServiceImpl<GoodsOtherMapper, Goods> implements IGoodsOtherService {

	@Autowired GoodsOtherMapper goodsOtherMapper;
	@Autowired IStoreService storeService;
	@Autowired GoodsMapper goodsMapper;
	@Override
	public void selectCombogridDataGrid(PageInfo pageInfo,Goods g) {
		List<Goods> list = goodsOtherMapper.selectCombogridDataGrid(g);
		pageInfo.setRows(list);
	}
	@Override
	public void selectStoreGodsDataGrid(PageInfo pageInfo, Goods g) {
		List<Goods> list = goodsOtherMapper.selectStoreGodsDataGrid(g);
		pageInfo.setRows(list);
	}
	@Override
	public void selectClassifyDataGrid(PageInfo pageInfo) {
		Page<GoodsClassify> page = new Page<GoodsClassify>(pageInfo.getNowpage(),pageInfo.getSize());
		List<GoodsClassify> list = goodsOtherMapper.selectClassifyDataGrid(page);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	@Override
	public void goodsClassifyGrid(PageInfo pageInfo) {
		List<GoodsClassify> list = goodsMapper.selectClassifyList();
		pageInfo.setRows(list);
	}
	@Override
	public GoodsClassify selectClassifyObj(Integer id) {
		// TODO Auto-generated method stub
		return goodsOtherMapper.selectClassifyObj(id);
	}

	@Override
	public void insertClassify(GoodsClassify bean) {
		// TODO Auto-generated method stub
		goodsOtherMapper.insertClassify(bean);
	}

	@Override
	public void updateClassify(GoodsClassify bean) {
		// TODO Auto-generated method stub
		goodsOtherMapper.updateClassify(bean);
	}

	@Override
	public void deleteClassify(Integer id) {
		// TODO Auto-generated method stub
		goodsOtherMapper.deleteClassify(id);
	}

	@Override
	public void selectBrandDataGrid(PageInfo pageInfo) {
		Page<GoodsClassify> page = new Page<GoodsClassify>(pageInfo.getNowpage(),pageInfo.getSize());
		List<GoodsClassify> list = goodsOtherMapper.selectBrandDataGrid(page);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public GoodsClassify selectBrandObj(Integer id) {
		// TODO Auto-generated method stub
		return goodsOtherMapper.selectBrandObj(id);
	}

	@Override
	public void insertBrand(GoodsClassify bean) {
		// TODO Auto-generated method stub
		goodsOtherMapper.insertBrand(bean);
	}

	@Override
	public void updateBrand(GoodsClassify bean) {
		// TODO Auto-generated method stub
		goodsOtherMapper.updateBrand(bean);
	}

	@Override
	public void deleteBrand(Integer id) {
		// TODO Auto-generated method stub
		goodsOtherMapper.deleteBrand(id);
	}

	@Override
	public void selectHotDataGrid(PageInfo pageInfo,Goods g) {
		Page<Goods> page = new Page<Goods>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Goods> list = goodsOtherMapper.selectHotDataGrid(page,g);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public GoodsClassify selectHotObj(Integer id) {
		// TODO Auto-generated method stub
		return goodsOtherMapper.selectHotObj(id);
	}

	@Override
	public void insertHot(GoodsClassify bean) {
		// TODO Auto-generated method stub
		goodsOtherMapper.insertHot(bean);
	}

	@Override
	public void updateHot(GoodsClassify bean) {
		// TODO Auto-generated method stub
		goodsOtherMapper.updateHot(bean);
	}

	@Override
	public void deleteHot(Integer id) {
		// TODO Auto-generated method stub
		goodsOtherMapper.deleteHot(id);
	}
	@Override
	public List<GoodsClassify> queryAllClassify() {
		return goodsOtherMapper.queryAllClassify();
	}
	@Override
	public void selectStoreDataGrid(PageInfo pageInfo) {
		List<Store> rows = new ArrayList<Store>();
		Store store = new Store();
		store.setId(0);
		store.setStoreName("小程序");
		rows.add(store);
		List<Store> list = storeService.queryStoreList();
		rows.addAll(list);
		pageInfo.setRows(rows);
	}
	@Override
	public Integer queryGoodsIdBySpecId(Integer id) {
		// TODO Auto-generated method stub
		return goodsOtherMapper.queryGoodsIdBySpecId(id);
	}

}
