package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.core.commons.result.PageInfo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 数据分析-渠道 服务类
 * </p>
 *
 * @author Tom
 * @since 2019-03-01
 */
public interface IGoodsFluxService extends IService<Goods> {

	//查询商品渠道(总)数据
	void selectDataGrid(PageInfo pageInfo, Goods goods);
	//查询商品渠道(门店)数据
	void selectStoreFluxData(PageInfo pageInfo, Goods goods);
	List<Map<String, Object>> selectMoreGraphics(Goods goods);
	List<Map<String, Object>> selectMoreRateDataGrid(Goods goods);
	void dataProcessing(List<Map<String, Object>> list, List<Map<String, Object>> dataList,List<Map<String, Object>> storeInfoList);
	void downLoadMoreChannel(Goods goods, HttpServletResponse response);

}
