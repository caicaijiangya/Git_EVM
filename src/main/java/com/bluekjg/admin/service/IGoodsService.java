package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.GoodsClassify;
import com.bluekjg.core.commons.result.PageInfo;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品主表 服务类
 * </p>
 *
 * @author Tom
 * @since 2018-09-25
 */
public interface IGoodsService extends IService<Goods> {

	void selectDataGrid(PageInfo pageInfo, Goods g);
	void selectSpecDataGrid(PageInfo pageInfo, Goods g);
	List<GoodsClassify> selectClassifyList();
	List<GoodsClassify> selectBrandList();
	Goods selectGoodsObj(Integer id);
	Goods selectGoodsSpecObj(Integer id);
	void insertGoods(Goods g);
	void updateGoods(Goods g);
	void updateGoodsQrCode(Goods g);
	void deleteGoods(Goods g);
	void insertGoodsSpec(Goods g);
	void updateGoodsSpec(Goods g);
	//查询所有商品---pjf
	List queryAllGoods();
	
	//导出订单
	void downLoadOrder(Goods g, HttpServletResponse response);
	void selectGoodsInfo(PageInfo pageInfo);
}
