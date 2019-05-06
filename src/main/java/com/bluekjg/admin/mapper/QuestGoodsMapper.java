package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.QuestGoods;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 问卷测试推荐商品表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-10-12
 */
public interface QuestGoodsMapper extends BaseMapper<QuestGoods> {

	List<QuestGoods> selectDataGrid(Page<QuestGoods> page, QuestGoods questGoods);

	List<Goods> queryGoodsByClassifyId(QuestGoods questGoods);

	List<Goods> queryAllGoods();

}