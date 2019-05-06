package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.Store;
import com.bluekjg.admin.model.StoreGoods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 商品主表 Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2018-09-25
 */
public interface StoreGoodsMapper extends BaseMapper<Goods> {
	List<Goods> selectDataGrid(Page<Goods> page,Goods g);
	List<Store> selectStoreList();
	StoreGoods selectStoreGoodsObj(@Param("id") Integer id);
	Integer selectGoodsAmount(@Param("id") Integer id);
	Integer selectStoreGoodsAmount(@Param("storeId") Integer storeId,@Param("goodsId") Integer goodsId);
	boolean insertStoreGoods(StoreGoods bean);
	boolean updateStoreGoods(StoreGoods bean);
	boolean deleteStoreGoods(@Param("id") Integer id);
	List<StoreGoods> queryStoreGoodsList(@Param("storeId") Integer storeId);
	List<Map<String,Object>> downLoadStoreGoods(Goods g);
}