package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.GoodsClassify;
import com.bluekjg.admin.model.Store;

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
public interface GoodsMapper extends BaseMapper<Goods> {

	List<Goods> selectDataGrid(Page<Goods> page, Goods g);
	List<Goods> selectSpecDataGrid(Page<Goods> page, Goods g);
	List<GoodsClassify> selectClassifyList();
	List<GoodsClassify> selectBrandList();
	Goods selectGoodsObj(@Param("id") Integer id);
	Goods selectGoodsSpecObj(@Param("id") Integer id);
	boolean insertGoods(Goods g);
	boolean updateGoods(Goods g);
	boolean updateGoodsQrCode(Goods g);
	boolean insertGoodsLabel(GoodsClassify bean);
	boolean deleteGoodsLabel(@Param("goodsId") Integer goodsId);
	boolean deleteGoods(Goods g);
	boolean updateRemAmount(@Param("id") Integer id,@Param("amount") Integer amount);
	boolean updateAmount(@Param("id") Integer id,@Param("amount") Integer amount);
	//商品规格
	boolean insertGoodsSpec(Goods g);
	boolean updateGoodsSpec(Goods g);
	//商品分类
	boolean insertGoodsClassifyDz(@Param("goodsId") Integer goodsId,@Param("classifyId") Integer classifyId);
	boolean deleteGoodsClassifyDz(@Param("goodsId") Integer goodsId);
	List queryAllGoods();
	
	/**
	 * 下载商品
	 * @param g
	 * @return
	 */
	List<Map<String,Object>> downLoadGoods(Goods g);
	List<Store> selectGoodsInfo();
}