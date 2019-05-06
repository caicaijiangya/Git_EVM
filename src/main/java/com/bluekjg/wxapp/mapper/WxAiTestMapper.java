package com.bluekjg.wxapp.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxQuestResultDetail;
import com.bluekjg.wxapp.model.wxApp20.*;

import java.util.List;

/**
 * @description：问卷测试题库 数据库控制层接口
 * @author：pincui.tom
 * @date：2018/9/12 14:51
 */
public interface WxAiTestMapper extends BaseMapper<WxAITestResult> {

	/**
	 * new 通过问题ID查询本题具体选项和名称
	 *
	 */
	List<WxQuestionDetailDto> queryAiTestDetailByProblemId(Integer problemId);

	/**
	 * new 查询本题具体选项和名称
	 *
	 */
	List<WxQuestionDetailDto> queryAiTestDetail();
	//查询题目名称
	String queryQuestName(Integer seq);

	/**
	 * new 通过openID查询测试的题目题号和选择的那一项
	 */
	List<WxQuestionDto> queryQuestResultAndScore(PagingDto page);
	List<WxQuestionDto> queryQuestResultAndScore1(PagingDto page);

	/**
	 * new 通过openID查询历史测试数量
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
	boolean updateAiTestResultDetail(WxAITestResultDetail bean);
	Integer queryOptionCount(WxAITestResultDetail bean);

	//查询结论Id
	Integer queryConclusionId(String conclusionName);

	//问卷自测结果（最后）
	List<LastResult> queryLastResult(PagingDto page);

}