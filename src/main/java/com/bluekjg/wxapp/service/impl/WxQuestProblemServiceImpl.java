package com.bluekjg.wxapp.service.impl;

import java.util.List;

import com.bluekjg.wxapp.model.wxApp20.LastResult;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDetailDto;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxQuestProblemMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxKeeperGoods;
import com.bluekjg.wxapp.model.WxQuestDimension;
import com.bluekjg.wxapp.model.WxQuestProblem;
import com.bluekjg.wxapp.model.WxQuestResult;
import com.bluekjg.wxapp.model.WxQuestResultDetail;
import com.bluekjg.wxapp.service.IWxQuestProblemService;

/**
 * @description：问卷测试题库
 * @author：pincui.Tom 
 * @date：2018/9/12 14:51
 */
@Service
public class WxQuestProblemServiceImpl extends ServiceImpl<WxQuestProblemMapper, WxQuestProblem>implements IWxQuestProblemService {

	protected Logger logger = LoggerFactory.getLogger(WxQuestProblemServiceImpl.class);

	@Autowired
	private WxQuestProblemMapper questProblemMapper;

	@Override
	public List<WxQuestProblem> queryQuestProblemList(PagingDto page) {
		// TODO Auto-generated method stub
		return questProblemMapper.queryQuestProblemList(page);
	}

	@Override
	public Integer queryQuestConclusionByKey(String[] keys) {
		// TODO Auto-generated method stub
		return questProblemMapper.queryQuestConclusionByKey(keys);
	}

	@Override
	public void insertQuestResult(WxQuestResult bean) {
		// TODO Auto-generated method stub
		questProblemMapper.insertQuestResult(bean);
		if(bean.getDetails() != null && bean.getDetails().size() > 0) {
			for(WxQuestResultDetail detail:bean.getDetails()) {
				detail.setResultId(bean.getId());
				questProblemMapper.insertQuestResultDetail(detail);
			}
		}
	}

	@Override
	public WxQuestResult queryQuestResultObj(PagingDto page) {
		// TODO Auto-generated method stub
		return questProblemMapper.queryQuestResultObj(page);
	}

	@Override
	public List<WxKeeperGoods> queryQuestGoodsList(PagingDto page) {
		// TODO Auto-generated method stub
		return questProblemMapper.queryQuestGoodsList(page);
	}

	@Override
	public Integer queryQuestResultCount(String openId,String key) {
		// TODO Auto-generated method stub
		return questProblemMapper.queryQuestResultCount(openId,key);
	}

	@Override
	public Integer queryQuestConclusionByKey1(String key) {
		// TODO Auto-generated method stub
		return questProblemMapper.queryQuestConclusionByKey1(key);
	}

	@Override
	public List<WxKeeperGoods> queryGoodsList(String[] ids) {
		// TODO Auto-generated method stub
		return questProblemMapper.queryGoodsList(ids);
	}

	@Override
	public List<WxQuestResult> queryQuestResultList(PagingDto page) {
		// TODO Auto-generated method stub
		return questProblemMapper.queryQuestResultList(page);
	}

	@Override
	public Integer queryQuestProblemSum() {
		// TODO Auto-generated method stub
		return questProblemMapper.queryQuestProblemSum();
	}

	@Override
	public List<WxQuestDimension> queryQuestDimensionList(PagingDto page) {
		// TODO Auto-generated method stub
		return questProblemMapper.queryQuestDimensionList(page);
	}

	@Override
	public Integer[] queryCurveData(PagingDto page) {
		Integer[] datas = new Integer[5];
		List<WxQuestResult> results = questProblemMapper.queryCurveData(page);
		for(int i=1;i<=datas.length;i++) {
			boolean isr = false;
			if(results != null && results.size() > 0) {
				for(WxQuestResult result:results) {
					if(result.getScore().intValue() == i) {
						isr = true;
						datas[(i - 1)] = result.getRealScore();
					}
				}
			}
			if(isr == false) {
				datas[(i - 1)] = 0;
			}
		}
		return datas;
	}

	public List<WxQuestionDetailDto> queryQuestDetailByProblemId(Integer problemId){

		return questProblemMapper.queryQuestDetailByProblemId(problemId);
	}

	public List<WxQuestionDetailDto> queryQuestDetail(){

		return questProblemMapper.queryQuestDetail();
	}
	@Override
	public String queryQuestName(Integer seq) {
		return questProblemMapper.queryQuestName(seq);
	}

	public List<WxQuestionDto> queryQuestResultAndScore(PagingDto page){

		return questProblemMapper.queryQuestResultAndScore(page);
	}
	public List<WxQuestionDto> queryQuestResultAndScore1(PagingDto page){

		return questProblemMapper.queryQuestResultAndScore1(page);
	}

	public Integer queryCountByOpenId(PagingDto page){

		return questProblemMapper.queryCountByOpenId(page);
	}

	public boolean insertANullResult(PagingDto page){

		return questProblemMapper.insertANullResult(page);
	}

	public Integer queryTestIdByOpenId(PagingDto page){

		return questProblemMapper.queryTestIdByOpenId(page);
	}


	public boolean insertQueseResultNew(WxQuestResult bean){
		return questProblemMapper.insertQueseResultNew(bean);
	}

	public boolean insertQuestResultDetailNew(WxQuestResultDetail bean){
		return questProblemMapper.insertQuestResultDetailNew(bean);
	}
	public boolean updateQuestResultDetail(WxQuestResultDetail bean){
		return questProblemMapper.updateQuestResultDetail(bean);
	}
	public Integer queryOptionCount(WxQuestResultDetail bean){
		return questProblemMapper.queryOptionCount(bean);
	}

	@Override
	public Integer queryConclusionId(String conclusionName) {
		return questProblemMapper.queryConclusionId(conclusionName);
	}

	@Override
	public List<LastResult> queryLastResult(PagingDto page) {
		return questProblemMapper.queryLastResult(page);
	}
}