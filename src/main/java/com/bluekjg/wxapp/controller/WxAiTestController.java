package com.bluekjg.wxapp.controller;

import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.wxApp20.WxAITestResult;
import com.bluekjg.wxapp.model.wxApp20.WxAITestResultDetail;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDetailDto;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDto;
import com.bluekjg.wxapp.service.IWxAiTestService;
import com.bluekjg.wxapp.service.IWxIndexService;
import com.bluekjg.wxapp.utils.GetChosenByScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description：问卷测试
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
@Controller
@RequestMapping("/xcx/aitest")
public class WxAiTestController extends BaseController {
	@Autowired
	private IWxAiTestService aiTestService;
	@Autowired
	private IWxIndexService indexService;
	

    /**
	 * new 查看测试结果
	 */
	@RequestMapping("/queryTestResult")
	@ResponseBody
	public Object queryTestResult(@Valid PagingDto page, HttpServletRequest request, HttpServletResponse response) {
		Object obj = renderError("The request failed");
		Map<String,Object> map = new HashMap<String,Object>();
		List<WxQuestionDto> resultList = aiTestService.queryQuestResultAndScore(page);
		GetChosenByScoreUtil optionUtil = new GetChosenByScoreUtil();
		for (WxQuestionDto wxquestion:resultList) {
			wxquestion.setWarn(true);
			wxquestion.setType("skin");
			wxquestion.setValue(optionUtil.getOptionByScore(1));
			List<WxQuestionDetailDto> detail = aiTestService.queryQuestDetailByProblemId(wxquestion.getPage());
			wxquestion.setDetailList(detail);
		}
		Integer count = aiTestService.queryCountByOpenId(page);
		if (count == 0){
			map.put("isFirst",true);
		}else {
			map.put("isFirst",false);
		}

		map.put("test",resultList);

		obj = renderSuccess(map);
		return obj;
	}


	/**
	 * new 点击开始测试，新建一条空的测试结果，并查询这条测试结果的ID和问题列表
	 */
	@RequestMapping("/insertAndQueryResultId")
	@ResponseBody
	public Object insertAndQueryResultId(@Valid PagingDto page, HttpServletRequest request, HttpServletResponse response) {
		Object obj = renderError("The request failed");
		Map<String,Object> map = new HashMap<String,Object>();
		aiTestService.insertANullResult(page);

		Integer resultId = aiTestService.queryTestIdByOpenId(page);

		List<WxQuestionDto> resultList = new ArrayList<>();
		for (int i = 1;i<=12;i++) {
			WxQuestionDto wxquestion = new WxQuestionDto();
			wxquestion.setWarn(true);
			wxquestion.setPage(i);
			wxquestion.setType("skin");
			wxquestion.setValue(null);
			List<WxQuestionDetailDto> detail = aiTestService.queryQuestDetailByProblemId(i);
			wxquestion.setName(aiTestService.queryQuestName(Integer.valueOf(detail.get(detail.size()-1).getPage())));
			wxquestion.setDetailList(detail);
			resultList.add(wxquestion);
		}


		map.put("quest_id",resultId);
		map.put("quest_list",resultList);

		obj = renderSuccess(map);
		return obj;
	}

	/**
	 * todo 提交单项问题答案
	 */
	@RequestMapping("/commitResult")
	@ResponseBody
	public Object commitResult(@Valid WxAITestResultDetail bean, HttpServletRequest request, HttpServletResponse response) {
		Object obj = renderError("The request failed");

		Integer page = bean.getPage();
		String value = bean.getValue();

		GetChosenByScoreUtil scoreUtil = new GetChosenByScoreUtil();
		Integer score = scoreUtil.getScoreByOption(value);

		bean.setScore(Double.valueOf(score));
		//TODO problemId 插入的时候暂时不做处理。。。。后面需要写个工具方法
		bean.setProblemId(page);
		aiTestService.insertAiTestResultDetailNew(bean);

		return obj;
	}


	/**
	 * TODO 提交整个AI答案
	 */
	@RequestMapping("/commitQuest")
	@ResponseBody
	public Object commitQuest(@Valid WxAITestResult bean, HttpServletRequest request, HttpServletResponse response) {
		Object obj = renderError("The request failed");
		Map<String,Object> map = new HashMap<String,Object>();
		GetChosenByScoreUtil util = new GetChosenByScoreUtil();
		List<WxAITestResultDetail> details = bean.getDetails();
		Map<String,Object> valueMap = new HashMap<>();
		for (WxAITestResultDetail detail:details) {
			String value = detail.getValue();
			valueMap.put(String.valueOf(detail.getSize()),value);
		}
		Integer score = 0;
		for (int i=0;i<=valueMap.size();i++){
			score += util.getScoreByOption(String.valueOf(valueMap.get(i)));
		}

		bean.setScore(score);
		//算过的分数 todo
		bean.setRealScore(23);

		aiTestService.insertAiTestResultNew(bean);


		obj = renderSuccess(map);
		return obj;
	}

	/**
	 * TODO 查询AI测结果，带上推荐
	 */
	@RequestMapping("/queryAiTestResult")
	@ResponseBody
	public Object queryQuestResult(@Valid PagingDto page, HttpServletRequest request, HttpServletResponse response) {
		Object obj = renderError("The request failed");
		Map<String,Object> map = new HashMap<String,Object>();



		obj = renderSuccess(map);
		return obj;
	}




}

