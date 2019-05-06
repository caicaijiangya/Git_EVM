package com.bluekjg.wxapp.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.wxapp.mapper.WxAiTestMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.wxApp20.WxAITestResult;
import com.bluekjg.wxapp.model.wxApp20.WxAITestResultDetail;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDetailDto;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDto;
import com.bluekjg.wxapp.service.IWxAiTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description：问卷测试题库
 * @author：pincui.Tom 
 * @date：2018/9/12 14:51
 */
@Service
public class WxAiTestServiceImpl extends ServiceImpl<WxAiTestMapper, WxAITestResult>implements IWxAiTestService {

	protected Logger logger = LoggerFactory.getLogger(WxAiTestServiceImpl.class);

	@Autowired
	private WxAiTestMapper aiTestMapper;

	public List<WxQuestionDetailDto> queryQuestDetailByProblemId(Integer problemId){

		return aiTestMapper.queryAiTestDetailByProblemId(problemId);
	}

	public List<WxQuestionDetailDto> queryQuestDetail(){

		return aiTestMapper.queryAiTestDetail();
	}
	@Override
	public String queryQuestName(Integer seq) {
		return aiTestMapper.queryQuestName(seq);
	}

	public List<WxQuestionDto> queryQuestResultAndScore(PagingDto page){

		return aiTestMapper.queryQuestResultAndScore(page);
	}

	public Integer queryCountByOpenId(PagingDto page){

		return aiTestMapper.queryCountByOpenId(page);
	}

	public boolean insertANullResult(PagingDto page){

		return aiTestMapper.insertANullResult(page);
	}

	public Integer queryTestIdByOpenId(PagingDto page){

		return aiTestMapper.queryTestIdByOpenId(page);
	}


	public boolean insertAiTestResultNew(WxAITestResult bean){
		return aiTestMapper.insertAiTestResultNew(bean);
	}

	public boolean insertAiTestResultDetailNew(WxAITestResultDetail bean){
		return aiTestMapper.insertAiTestResultDetailNew(bean);
	}







}