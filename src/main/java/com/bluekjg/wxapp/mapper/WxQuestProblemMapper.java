package com.bluekjg.wxapp.mapper;

import java.util.List;

import com.bluekjg.wxapp.model.wxApp20.LastResult;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDetailDto;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDto;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxKeeperGoods;
import com.bluekjg.wxapp.model.WxQuestDimension;
import com.bluekjg.wxapp.model.WxQuestProblem;
import com.bluekjg.wxapp.model.WxQuestResult;
import com.bluekjg.wxapp.model.WxQuestResultDetail;

/**
 * @description：问卷测试题库 数据库控制层接口
 * @author：pincui.tom
 * @date：2018/9/12 14:51
 */
public interface WxQuestProblemMapper extends BaseMapper<WxQuestProblem> {
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
	List<WxKeeperGoods> queryGoodsList(@Param(value="ids") String[] ids);
	/**
	 * 查询评测纪录
	 * @param key
	 * @return
	 */
	Integer queryQuestResultCount(@Param(value="openId") String openId,@Param(value="key") String key);
	/**
	 * 查询评测结果
	 * @param keys
	 * @return
	 */
	Integer queryQuestConclusionByKey(@Param(value="keys") String[] keys);
	Integer queryQuestConclusionByKey1(@Param(value="key") String key);
	/**
	 * 新增测试结果
	 * @param bean
	 * @return
	 */
	boolean insertQuestResult(WxQuestResult bean);
	/**
	 * 新增测试结果明细
	 * @param bean
	 * @return
	 */
	boolean insertQuestResultDetail(WxQuestResultDetail bean);
	
	/**
	 * 查询问卷测试报告-曲线图数据
	 * @param page
	 * @return
	 */
	List<WxQuestResult> queryCurveData(PagingDto page);

	/**
	 * new 通过问题ID查询本题具体选项和名称
	 *
	 */
	List<WxQuestionDetailDto> queryQuestDetailByProblemId(Integer problemId);

	/**
	 * new 查询本题具体选项和名称
	 *
	 */
	List<WxQuestionDetailDto> queryQuestDetail();
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