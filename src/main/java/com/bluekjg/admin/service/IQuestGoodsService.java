package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.QuestGoods;
import com.bluekjg.core.commons.result.PageInfo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 问卷测试推荐商品表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-10-12
 */
public interface IQuestGoodsService extends IService<QuestGoods> {

	void selectDataGrid(PageInfo pageInfo, QuestGoods questGoods);

	List<Goods> queryGoodsByClassifyId(QuestGoods questGoods);

	List<Goods> queryAllGoods();
	
}
