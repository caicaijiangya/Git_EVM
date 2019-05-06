package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Goods;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 数据分析-渠道 Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2019-03-01
 */
public interface GoodsFluxMapper extends BaseMapper<Goods> {

	//商品流量分析-渠道列表
	List<Goods> selectDataGrid(Page<Goods> page, Goods goods);
	List<Goods> selectDataGrid(Goods goods);

	//商品流量分析-门店列表
	List<Goods> selectStoreFluxData(Page<Goods> page, Goods goods);

	List<Map<String, Object>> selectMoreDataGrid(Page<Goods> page, Goods goods);

	List<Map<String, Object>> selectMoreRateDataGrid(Goods goods);

	List<Map<String, Object>> selectStoreChannelData(Goods goods);

}