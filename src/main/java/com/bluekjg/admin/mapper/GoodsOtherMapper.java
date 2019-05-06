package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.GoodsClassify;
import java.util.List;

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
public interface GoodsOtherMapper extends BaseMapper<Goods> {
	
	List<Goods> selectCombogridDataGrid(Goods g);
	List<Goods> selectStoreGodsDataGrid(Goods g);
	List<GoodsClassify> selectClassifyDataGrid(Page<GoodsClassify> page);
	GoodsClassify selectClassifyObj(@Param("id") Integer id);
	boolean insertClassify(GoodsClassify bean);
	boolean updateClassify(GoodsClassify bean);
	boolean deleteClassify(@Param("id") Integer id);
	
	List<GoodsClassify> selectBrandDataGrid(Page<GoodsClassify> page);
	GoodsClassify selectBrandObj(@Param("id") Integer id);
	boolean insertBrand(GoodsClassify bean);
	boolean updateBrand(GoodsClassify bean);
	boolean deleteBrand(@Param("id") Integer id);
	
	List<Goods> selectHotDataGrid(Page<Goods> page,Goods g);
	GoodsClassify selectHotObj(@Param("id") Integer id);
	boolean insertHot(GoodsClassify bean);
	boolean updateHot(GoodsClassify bean);
	boolean deleteHot(@Param("id") Integer id);

	List<GoodsClassify> queryAllClassify();
	Integer queryGoodsIdBySpecId(@Param("id") Integer id);
}