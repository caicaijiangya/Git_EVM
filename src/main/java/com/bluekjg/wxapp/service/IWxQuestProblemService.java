package com.bluekjg.wxapp.service;

import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.*;
import com.bluekjg.wxapp.model.wxApp20.LastResult;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDetailDto;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDto;

/**
 * @description：问卷测试题库
 * @author：pincui.Tom
 * @date：2018/9/12 14:51
 */
public interface IWxQuestProblemService extends IService<WxQuestProblem> {
	/**
	 * 查询满分分数
	 * @return
	 */
	Integer queryQuestProblemSum();
	/**
	 * 查询所有维度
	 * @param page
	 * @return
	 */
	List<WxQuestDimension> queryQuestDimensionList(PagingDto page);
	/**
	 * 查询当前题目
	 * @return
	 */
	List<WxQuestProblem> queryQuestProblemList(PagingDto page);
	/**
	 * 我的评测结果
	 * @param page
	 * @return
	 */
	List<WxQuestResult> queryQuestResultList(PagingDto page);
	/**
	 * 查询测试结果
	 * @param page
	 * @return
	 */
	WxQuestResult queryQuestResultObj(PagingDto page);
	/**
	 * 查询推荐商品
	 * @param page
	 * @return
	 */
	List<WxKeeperGoods> queryQuestGoodsList(PagingDto page);
	/**
	 * 批量查询商品列表
	 * @param ids
	 * @return
	 */
	List<WxKeeperGoods> queryGoodsList(String[] ids);
	/**
	 * 查询评测纪录
	 * @param key
	 * @return
	 */
	Integer queryQuestResultCount(String openId,String key);
	/**
	 * 查询评测结果
	 * @param keys
	 * @return
	 */
	Integer queryQuestConclusionByKey(String[] keys);
	Integer queryQuestConclusionByKey1(String key);
	/**
	 * 新增测试结果
	 * @param bean
	 * @return
	 */
	void insertQuestResult(WxQuestResult bean);
	/**
	 * 查询问卷测试报告-曲线图数据
	 * @param page
	 * @return
	 */
	Integer[] queryCurveData(PagingDto page);

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
	List<WxQuestionDto> queryQuestResultAndScore1(PagingDto page);
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
	boolean insertQueseResultNew(WxQuestResult bean);
	/**
	 * 新增测试结果明细
	 * @param bean
	 * @return
	 */
	boolean insertQuestResultDetailNew(WxQuestResultDetail bean);
	boolean updateQuestResultDetail(WxQuestResultDetail bean);
	Integer queryOptionCount(WxQuestResultDetail bean);

	//查询结论Id
	Integer queryConclusionId(String conclusionName);

	//问卷自测结果（最后）
	List<LastResult> queryLastResult(PagingDto page);
}