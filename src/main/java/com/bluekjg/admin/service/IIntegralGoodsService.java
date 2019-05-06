package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.IntegralGoods;
import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.core.commons.result.PageInfo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 积分商品表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-10-07
 */
public interface IIntegralGoodsService extends IService<IntegralGoods> {

	void selectDataGrid(PageInfo pageInfo, IntegralGoods integralGoods);
	
	IntegralGoods selectIntegralGoodsObj(Integer id);

	void updateGoodsAmount(IntegralGoods integralGoods);

	void insertIntegralGoods(IntegralGoods integralGoods);

	List<StaticFiles> queryGoodsImgList(IntegralGoods iGoods);

	boolean modifyGoodsImageInfo(IntegralGoods integralGoods);

	Integer selectIntegralGoodsByGoodsId(IntegralGoods integralGoods);

	void editGoodsStock(IntegralGoods integralGoods);

	IntegralGoods queryGoodsAmount(IntegralGoods integralGoods);
	
}
