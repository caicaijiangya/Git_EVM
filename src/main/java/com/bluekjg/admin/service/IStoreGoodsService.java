package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.Store;
import com.bluekjg.admin.model.StoreGoods;
import com.bluekjg.core.commons.result.PageInfo;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品门店 服务类
 * </p>
 *
 * @author Tom
 * @since 2018-09-25
 */
public interface IStoreGoodsService extends IService<Goods> {
	
	void selectDataGrid(PageInfo pageInfo, Goods g);
	List<Store> selectStoreList();
	StoreGoods selectStoreGoodsObj(Integer id);
	Integer selectGoodsAmount(Integer id);
	Integer selectStoreGoodsAmount(Integer storeId,Integer goodsId);
	void insertStoreGoods(StoreGoods bean);
	void updateStoreGoods(StoreGoods bean);
	void deleteStoreGoods(Integer id);
	
	//导出门店商品
	void downLoadStoreGoods(Goods g, HttpServletResponse response);
}
