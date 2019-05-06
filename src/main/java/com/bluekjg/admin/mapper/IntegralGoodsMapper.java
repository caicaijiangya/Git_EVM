package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.IntegralGoods;
import com.bluekjg.admin.model.StaticFiles;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 积分商品表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-10-07
 */
public interface IntegralGoodsMapper extends BaseMapper<IntegralGoods> {

	List<IntegralGoods> selectDataGrid(Page<IntegralGoods> page, IntegralGoods integralGoods);
	
	IntegralGoods selectIntegralGoodsObj(@Param("id") Integer id);
	
	void updateGoodsAmount(IntegralGoods integralGoods);

	List<StaticFiles> queryGoodsImgList(IntegralGoods iGoods);

	Integer deleteGoodsImg(IntegralGoods integralGoods);

	Integer selectIntegralGoodsByGoodsId(IntegralGoods integralGoods);

	void editGoodsStock(IntegralGoods integralGoods);

	IntegralGoods queryGoodsAmount(IntegralGoods integralGoods);


}