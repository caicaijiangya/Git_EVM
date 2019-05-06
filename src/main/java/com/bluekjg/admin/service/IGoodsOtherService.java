package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.GoodsClassify;
import com.bluekjg.core.commons.result.PageInfo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品主表 服务类
 * </p>
 *
 * @author Tom
 * @since 2018-09-25
 */
public interface IGoodsOtherService extends IService<Goods> {
	void selectCombogridDataGrid(PageInfo pageInfo,Goods g);
	void selectStoreGodsDataGrid(PageInfo pageInfo,Goods g);
	void selectClassifyDataGrid(PageInfo pageInfo);
	void goodsClassifyGrid(PageInfo pageInfo);
	void selectStoreDataGrid(PageInfo pageInfo);
	GoodsClassify selectClassifyObj(Integer id);
	void insertClassify(GoodsClassify bean);
	void updateClassify(GoodsClassify bean);
	void deleteClassify(Integer id);
	
	void selectBrandDataGrid(PageInfo pageInfo);
	GoodsClassify selectBrandObj(Integer id);
	void insertBrand(GoodsClassify bean);
	void updateBrand(GoodsClassify bean);
	void deleteBrand(Integer id);
	
	void selectHotDataGrid(PageInfo pageInfo,Goods g);
	GoodsClassify selectHotObj(Integer id);
	void insertHot(GoodsClassify bean);
	void updateHot(GoodsClassify bean);
	void deleteHot(Integer id);

	List<GoodsClassify> queryAllClassify();
	Integer queryGoodsIdBySpecId(Integer id);
}
