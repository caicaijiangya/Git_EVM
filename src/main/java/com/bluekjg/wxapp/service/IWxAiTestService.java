package com.bluekjg.wxapp.service;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.wxApp20.WxAITestResult;
import com.bluekjg.wxapp.model.wxApp20.WxAITestResultDetail;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDetailDto;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDto;

import java.util.List;

/**
 * @description：问卷测试题库
 * @author：pincui.Tom
 * @date：2018/9/12 14:51
 */
public interface IWxAiTestService extends IService<WxAITestResult> {

	/**
	 * new 通过问题ID查询本题具体选项和名称
	 */
	List<WxQuestionDetailDto> queryQuestDetailByProblemId(Integer problemId);
	List<WxQuestionDetailDto> queryQuestDetail();
	//查询题目名称
	String queryQuestName(Integer seq);
	/**
	 * 通过openID查询测试的题目题号和选择的那一项
	 */
	List<WxQuestionDto> queryQuestResultAndScore(PagingDto page);

	/**
	 * 通过openID查询历史测试数量
	 * @param page
	 * @return
	 */
	Integer queryCountByOpenId(PagingDto page);

	/**
	 * 新建一条空的测试结果
	 */
	boolean insertANullResult(PagingDto page);

	/**
	 * 通过openID查询测试结果Id
	 * @param page
	 * @return
	 */
	Integer queryTestIdByOpenId(PagingDto page);

	/**
	 * 新增测试结果
	 * @param bean
	 * @return
	 */
	boolean insertAiTestResultNew(WxAITestResult bean);
	/**
	 * 新增测试结果明细
	 * @param bean
	 * @return
	 */
	boolean insertAiTestResultDetailNew(WxAITestResultDetail bean);



}