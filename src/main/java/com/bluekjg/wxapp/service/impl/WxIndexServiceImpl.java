package com.bluekjg.wxapp.service.impl;

import java.util.List;

import com.bluekjg.wxapp.model.wxApp20.WxAITestResult;
import com.bluekjg.wxapp.model.wxApp20.WxGoodsJCTJ;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxIndexMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxGoodsIndex;
import com.bluekjg.wxapp.model.WxPopConfig;
import com.bluekjg.wxapp.model.WxQuestResult;
import com.bluekjg.wxapp.service.IWxIndexService;

/**
 * @description：商品数据
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
public class WxIndexServiceImpl extends ServiceImpl<WxIndexMapper, WxGoodsIndex>implements IWxIndexService {

	protected Logger logger = LoggerFactory.getLogger(WxIndexServiceImpl.class);

	@Autowired
	private WxIndexMapper indexMapper;

	@Override
	public List<WxGoodsIndex> queryGoodsList(PagingDto page) {
		// TODO Auto-generated method stub
		return indexMapper.queryGoodsList(page);
	}
	@Override
	public List<WxGoodsIndex> queryActivityList(PagingDto page) {
		// TODO Auto-generated method stub
		return indexMapper.queryActivityList(page);
	}

	@Override
	public List<WxGoodsIndex> queryGoodsHotList(PagingDto page) {
		// TODO Auto-generated method stub
		return indexMapper.queryGoodsHotList(page);
	}

	@Override
	public List<WxGoodsIndex> queryBrandList(PagingDto page) {
		// TODO Auto-generated method stub
		return indexMapper.queryBrandList(page);
	}

	@Override
	public List<WxGoodsIndex> queryClassifyList(PagingDto page) {
		// TODO Auto-generated method stub
		return indexMapper.queryClassifyList(page);
	}

	@Override
	public WxQuestResult queryQuestResultId(PagingDto page) {
		// TODO Auto-generated method stub
		return indexMapper.queryQuestResultId(page);
	}

	@Override
	public WxPopConfig queryPopConfig(PagingDto page) {
		// TODO Auto-generated method stub
		return indexMapper.queryPopConfig(page);
	}

	@Override
	public List<WxGoodsIndex> queryActivityFull() {
		// TODO Auto-generated method stub
		return indexMapper.queryActivityFull();
	}

	@Override
	public List<WxGoodsIndex> queryActivityDetail(PagingDto page) {
		// TODO Auto-generated method stub
		return indexMapper.queryActivityDetail(page);
	}
	@Override
	public WxAITestResult queryAITestResultByOpenId(PagingDto page) {
		// TODO Auto-generated method stub
		return indexMapper.queryAITestResultByOpenId(page);
	}
	@Override
	public WxGoodsJCTJ queryTJGoods(Integer goodsId){

		return indexMapper.queryTJGoods(goodsId);
	}
	@Override
	public Integer queryTJGoodsIDByOpenId(PagingDto page){

		return indexMapper.queryTJGoodsIDByOpenId(page);
	}
	@Override
	public Integer queryOrderCountByOpenId(PagingDto page){

		return indexMapper.queryOrderCountByOpenId(page);
	}
	@Override
	public List<Integer> queryGoodsByOpenId(PagingDto page){

		return indexMapper.queryGoodsByOpenId(page);
	}

	public Integer queryCountFromUser(PagingDto page){
		return indexMapper.queryCountFromUser(page);
	}
	public Integer queryMobileFromUser(PagingDto page){
		return indexMapper.queryMobileFromUser(page);
	}


}