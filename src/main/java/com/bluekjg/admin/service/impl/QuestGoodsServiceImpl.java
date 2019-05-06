package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.QuestGoods;
import com.bluekjg.admin.mapper.QuestGoodsMapper;
import com.bluekjg.admin.service.IQuestGoodsService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问卷测试推荐商品表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-10-12
 */
@Service
public class QuestGoodsServiceImpl extends ServiceImpl<QuestGoodsMapper, QuestGoods> implements IQuestGoodsService {
	
	@Autowired private QuestGoodsMapper questGoodsMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, QuestGoods questGoods) {
		Page<QuestGoods> page = new Page<QuestGoods>(pageInfo.getNowpage(),pageInfo.getSize());
		List<QuestGoods> list = questGoodsMapper.selectDataGrid(page,questGoods);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public List<Goods> queryGoodsByClassifyId(QuestGoods questGoods) {
		return questGoodsMapper.queryGoodsByClassifyId(questGoods);
	}

	@Override
	public List<Goods> queryAllGoods() {
		return questGoodsMapper.queryAllGoods();
	}
	
}
