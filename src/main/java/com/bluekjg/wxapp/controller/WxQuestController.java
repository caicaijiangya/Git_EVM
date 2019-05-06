package com.bluekjg.wxapp.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.bluekjg.wxapp.model.wxApp20.LastResult;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDetailDto;
import com.bluekjg.wxapp.model.wxApp20.WxQuestionDto;
import com.bluekjg.wxapp.utils.GetChosenByScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxKeeperGoods;
import com.bluekjg.wxapp.model.WxQuestDimension;
import com.bluekjg.wxapp.model.WxQuestProblem;
import com.bluekjg.wxapp.model.WxQuestResult;
import com.bluekjg.wxapp.model.WxQuestResultDetail;
import com.bluekjg.wxapp.service.IWxIndexService;
import com.bluekjg.wxapp.service.IWxQuestProblemService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @description：问卷测试
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
@Controller
@RequestMapping("/xcx/quest")
public class WxQuestController extends BaseController {
	@Autowired
	private IWxQuestProblemService questProblemService;
	@Autowired
	private IWxIndexService indexService;
	
	/**
     * 查询题目信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryProblem")
    @ResponseBody
    public Object queryProblem(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxQuestProblem> list = questProblemService.queryQuestProblemList(page);
        	obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询商品列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryGoodsList")
    @ResponseBody
    public Object queryGoodsList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	String[] ids = request.getParameter("ids").split(",");
        	List<WxKeeperGoods> list = questProblemService.queryGoodsList(ids);
        	obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询推荐商品
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryGoods")
    @ResponseBody
    public Object queryGoods(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	char codes[] = page.getCode().toCharArray();
        	Map<String,Object> map = new HashMap<String,Object>();
        	/*
        	 * 个性化定制
        	 */
        	page.setCode(null);
        	page.setType(0);
        	List<WxKeeperGoods> goods1 = new ArrayList<WxKeeperGoods>();
        	List<WxKeeperGoods> goods2 = new ArrayList<WxKeeperGoods>();
        	List<WxKeeperGoods> kgList = questProblemService.queryQuestGoodsList(page);
        	if(kgList != null && kgList.size() > 0) {
        		goods1.add(kgList.get(0));
        		goods2.add(kgList.get(0));
        	}
        	
        	//两个周期
        	for(int i=0;i< 2 ;i++) {
        		page.setType((i+1));
        		//code  AB
        		for(int j=0;j<codes.length;j++) {
        			page.setCode(String.valueOf(codes[j]));
                	List<WxKeeperGoods> goods_ = questProblemService.queryQuestGoodsList(page);
                	if(goods_ != null && goods_.size() > 0) {
                		if(i == 0) {
                			goods1.add(goods_.get(0));
                		}else if(i == 1){
                			goods2.add(goods_.get(0));
                		}
                	}
        		}
        	}
        	/*
        	 * 基础定制
        	 */
        	List<WxKeeperGoods> goods = new ArrayList<WxKeeperGoods>();
        	page.setType(3);
        	page.setBrandId(36);
        	//EVM A商品，B商品
    		for(int j=0;j<codes.length;j++) {
    			page.setCode(String.valueOf(codes[j]));
    			List<WxKeeperGoods> jcList = questProblemService.queryQuestGoodsList(page);
    			if(jcList != null && jcList.size() > 0) {
            		goods.add(jcList.get(0));
            	}
    		}
    		//自然之名  一款商品
    		page.setCode(null);
    		page.setBrandId(37);
    		page.setItems(new String[] {String.valueOf(codes[0]),String.valueOf(codes[1])});
    		List<WxKeeperGoods> jcList = questProblemService.queryQuestGoodsList(page);
    		if(jcList != null && jcList.size() > 0) {
        		goods.add(jcList.get(0));
        	}
    		map.put("goods", goods);
        	map.put("goods1", goods1);
        	map.put("goods2", goods2);
        	obj = renderSuccess(map);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    public static void main(String[] arge) {
    	int i = 10;
    	int j = 0;
    	while( i!=j) {
    		  System.out.println(j);
    		  j++;
    	}
    }
    /**
     * 查询我的测试结果
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryResultList")
    @ResponseBody
    public Object queryResultList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxQuestResult> list = questProblemService.queryQuestResultList(page);
        	obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 查询维度列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryDimension")
    @ResponseBody
    public Object queryDimension(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxQuestDimension> dimensionList = questProblemService.queryQuestDimensionList(page);
        	obj = renderSuccess(dimensionList);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询曲线图数据
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryCurve")
    @ResponseBody
    public Object queryCurve(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	Date date = new Date();
        	String dates[] = new String[5];
        	int count = 0;
        	for(int i = dates.length -1;i >= 0;i--) {
        		int day = i * 7 * -1;
            	Date date_ = DateUtil.addDate(date, 0, 0, day, 0, 0, 0, 0);
            	//dates[i] = DateUtil.parseDateToStr(date_, "dd/MM");
            	int month_ = date_.getMonth() + 1;
            	int day_ = date_.getDate();
            	dates[count] = day_+"/"+month_;
            	count ++;
        	}
        	Integer[] wj_data = questProblemService.queryCurveData(page);
        	map.put("dates", dates);
        	map.put("wj_data", wj_data);
        	obj = renderSuccess(map);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询我的测试报告
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryReport")
    @ResponseBody
    public Object queryReport(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	WxQuestResult result = indexService.queryQuestResultId(page);
        	map.put("result", result);
        	obj = renderSuccess(map);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询测试结果
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryResult")
    @ResponseBody
    public Object queryResult(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxQuestResult bean = questProblemService.queryQuestResultObj(page);
        	obj = renderSuccess(bean);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 添加测试结果
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addResult")
    @ResponseBody
    public Object addResult(@Valid WxQuestResult bean,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	String results = request.getParameter("results");
        	JSONArray array = JSONArray.fromObject(results);
        	List<WxQuestResultDetail> details = null;
        	List<WxQuestResultDetail> dimensions = new ArrayList<WxQuestResultDetail>();
        	int problemId = 0;
        	int score = 0;
    		if(array != null && array.size() > 0) {
    			details = new ArrayList<WxQuestResultDetail>();
    			for(int i=0;i<array.size();i++) {
    				JSONObject jobj = array.getJSONObject(i);
    				WxQuestResultDetail detail = new WxQuestResultDetail();
    				detail.setDimensionId(jobj.getInt("dimensionId"));
    				detail.setProblemId(jobj.getInt("problemId"));
    				detail.setScore(0.0);
    				if(jobj.getInt("type") == 0) {
    					detail.setScore(jobj.getDouble("score"));
    					score = score + jobj.getInt("score");
    				}
    				//同维度分数相加
    				WxQuestResultDetail detail1 = null;
    				for(int j=0;j<dimensions.size();j++) {
    					if(dimensions.get(j).getDimensionId().intValue() == jobj.getInt("dimensionId")) {
    						detail1 = dimensions.get(j);
    						dimensions.get(j).setScore(dimensions.get(j).getScore() + jobj.getInt("score"));
    						dimensions.get(j).setSize(dimensions.get(j).getSize() + 1);
    					}
    				}
    				if(detail1 == null && jobj.getInt("type") == 0) {
    					detail1 = new WxQuestResultDetail();
    					detail1.setDimensionId(jobj.getInt("dimensionId"));
    					detail1.setNote(jobj.getString("key"));
    					detail1.setScore(jobj.getDouble("score"));
    					detail1.setSize(1);
    					dimensions.add(detail1);
    				}
    				//当前选择的年龄段
    				if(jobj.getInt("type") == 5) {
    					problemId = jobj.getInt("problemDetailId");
    				}
    				details.add(detail);
    			}
    			bean.setScore(score);
    			bean.setDetails(details);
        		String[] keys = sortingResult(dimensions,problemId);
        		bean.setConclusionId(questProblemService.queryQuestConclusionByKey(keys));
        		if(bean.getConclusionId() == null) {
        			//避免未评测到结果
        			char[] key = keys[0].toCharArray();
        			bean.setConclusionId(questProblemService.queryQuestConclusionByKey1(String.valueOf(key[0])));
        		}
        		//计算真实分数（百分制） 
        		//满分/得分*100
        		Integer sum = questProblemService.queryQuestProblemSum();
        		if(sum != null) {
        			double suml = (Double.valueOf(80)-Double.valueOf(bean.getScore()))/Double.valueOf(64);
        			Long realScore = Math.round(suml*101);
        			bean.setRealScore(Integer.valueOf(realScore.toString()));
        		}else {
        			bean.setRealScore(bean.getScore());
        		}
            	questProblemService.insertQuestResult(bean);
            	obj = renderSuccess(bean.getId());
    		}
    		
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 计算评测结果
     * @param dimensions
     * @param problemId 年龄段Id
     * @return
     */
    public String[] sortingResult(List<WxQuestResultDetail> dimensions,int problemId) {
    	
    	if(dimensions != null && dimensions.size() > 0) {
    		List<Map<String,Double>> qzs = new ArrayList<Map<String,Double>>();
    		//权重
    		Map<String,Double> qzs_1 = new HashMap<String,Double>();
    		qzs_1.put("A", 0.98);qzs_1.put("B", 0.96);qzs_1.put("C", 0.93);qzs_1.put("D", 1.00);qzs_1.put("E", 0.97);qzs_1.put("F", 0.91);qzs_1.put("G", 1.02);qzs_1.put("H", 1.04);
    		Map<String,Double> qzs_2 = new HashMap<String,Double>();
    		qzs_2.put("A", 1.0);qzs_2.put("B", 0.95);qzs_2.put("C", 0.92);qzs_2.put("D", 1.01);qzs_2.put("E", 1.05);qzs_2.put("F", 0.9);qzs_2.put("G", 0.94);qzs_2.put("H", 1.02);
    		Map<String,Double> qzs_3 = new HashMap<String,Double>();
    		qzs_3.put("A", 1.02);qzs_3.put("B", 1.05);qzs_3.put("C", 0.91);qzs_3.put("D", 1.02);qzs_3.put("E", 0.98);qzs_3.put("F", 0.89);qzs_3.put("G", 0.90);qzs_3.put("H", 1.00);
    		Map<String,Double> qzs_4 = new HashMap<String,Double>();
    		qzs_4.put("A", 1.04);qzs_4.put("B", 1.11);qzs_4.put("C", 0.90);qzs_4.put("D", 1.03);qzs_4.put("E", 1.00);qzs_4.put("F", 0.88);qzs_4.put("G", 0.89);qzs_4.put("H", 0.98);
    		qzs.add(qzs_1);qzs.add(qzs_2);qzs.add(qzs_3);qzs.add(qzs_4);
    		Map<String,Double> qz = null;
        	/*qzs[0] = new Double[] {1.01,1.02,1.3,0.97,0.92,1.03,1.0,0.95};
        	qzs[1] = new Double[] {1.0,1.03,1.3,0.95,0.96,1.04,1.05,1.01};
        	qzs[2] = new Double[] {0.99,1.04,1.3,0.93,1.0,1.05,1.1,1.07};
        	qzs[3] = new Double[] {0.98,1.05,1.3,0.91,1.04,1.06,1.15,0.13};*/
        	if(problemId == 23) {
        		qz = qzs.get(0);//25岁及以下
        	}else if(problemId == 24) {
        		qz = qzs.get(1);//26-30岁
        	}else if(problemId == 25) {
        		qz = qzs.get(2);//31-40岁
        	}else if(problemId == 26) {
        		qz = qzs.get(3);//40岁及以上
        	}
    		//平均分
    		for(int i = 0;i < dimensions.size();i++) {
    			//dimensions.get(i).setScore(dimensions.get(i).getScore() / dimensions.get(i).getSize());
    			if(qz.get(dimensions.get(i).getNote()) != null) {
    				dimensions.get(i).setScore(dimensions.get(i).getScore() * qz.get(dimensions.get(i).getNote()));
    			}
    			
    		}
    		//冒泡排序
    		for (int i = 0;i < dimensions.size() - 1;i++) {
    	        for (int j = 0; j < (dimensions.size() - 1 - 1 - i); j++) {
	    	        if (dimensions.get(j).getScore() < dimensions.get(j + 1).getScore())
	    	        {
	    	        	WxQuestResultDetail temp = dimensions.get(j);
	    	        	dimensions.set(j, dimensions.get(j + 1));
	    	            dimensions.set(j + 1, temp);
	    	        }
    	        }
    		}
    		/*
    		 * 获得分数最高两个组合的KEY
    		 * 如：AC，CA
    		 */
    		String n1 = dimensions.get(0).getNote();
			String n2 = dimensions.get(1).getNote();
			return new String[]{n1+n2};
    	}
    	return null;
    }
	/**
	 * new 查看问卷测试结果
	 */
	@RequestMapping("/queryTestResult")
	@ResponseBody
	public Object queryTestResult(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
		Object obj = renderError("The request failed");
		Map<String,Object> map = new HashMap<String,Object>();

		Integer count = questProblemService.queryCountByOpenId(page);
		if (count == 0){
			map.put("isFirst",true);

		}else {
			map.put("isFirst",false);

		}
		//空的问题list
		List<WxQuestionDto> resultList = questProblemService.queryQuestResultAndScore(page);
		//最后一次答的问卷的list（可能没答完）
		List<WxQuestionDto> resultList1 = questProblemService.queryQuestResultAndScore1(page);

		if (resultList1.size()<resultList.size()){
			for (int i = resultList1.size(); i<resultList.size();i++){
				resultList1.add(i,resultList.get(i));
			}
		}


		GetChosenByScoreUtil optionUtil = new GetChosenByScoreUtil();
		for (WxQuestionDto wxquestion:resultList1) {
			wxquestion.setTitle(wxquestion.getTitle());
			wxquestion.setWarn(true);
			wxquestion.setType(wxquestion.getSort());
			wxquestion.setIcon(wxquestion.getIcon());
			if (wxquestion.getPage() ==0 || wxquestion.getPage() ==1 || wxquestion.getPage() == 2 ||wxquestion.getPage() == 3){
				wxquestion.setValue(wxquestion.getValue());
			}else {
				wxquestion.setValue(optionUtil.getOptionByScore(wxquestion.getScore()));
			}
			List<WxQuestionDetailDto> detail = questProblemService.queryQuestDetailByProblemId(wxquestion.getPage()+1);
			for(int i=0;i<detail.size();i++){
				String opt = optionUtil.getOptionBySeq(Integer.valueOf(detail.get(i).getValue()));
				detail.get(i).setValue(opt);
			}
			wxquestion.setDetailList(detail);
		}

		Integer questId = questProblemService.queryTestIdByOpenId(page);
		if (count == 0){
			map.put("quest_id",null);
		}else {
			map.put("quest_id",questId);
		}
		map.put("quest_list",resultList1);

		obj = renderSuccess(map);
		return obj;
	}


	/**
	 * new 点击开始测试，新建一条空的测试结果，并查询这条测试结果的ID和问题列表
	 */
	@RequestMapping("/insertAndQueryResultId")
	@ResponseBody
	public Object insertAndQueryResultId(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
		Object obj = renderError("The request failed");
		Map<String,Object> map = new HashMap<String,Object>();
		questProblemService.insertANullResult(page);

		Integer resultId = questProblemService.queryTestIdByOpenId(page);

		List<WxQuestionDto> resultList = questProblemService.queryQuestResultAndScore(page);
		for (int i = 0;i<12;i++) {
			WxQuestionDto wxquestion = new WxQuestionDto();
			wxquestion.setWarn(true);
			wxquestion.setPage(i);
			wxquestion.setType(resultList.get(i).getSort());
			wxquestion.setValue(null);
			wxquestion.setTitle(resultList.get(i).getTitle());
			wxquestion.setName(resultList.get(i).getName());
			wxquestion.setIcon(resultList.get(i).getIcon());
			List<WxQuestionDetailDto> detail = questProblemService.queryQuestDetailByProblemId(i+1);
			wxquestion.setName(questProblemService.queryQuestName(Integer.valueOf(detail.get(detail.size()-1).getPage())));
			wxquestion.setItem(detail);
			resultList.set(i,wxquestion);
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
	public Object commitResult(@Valid WxQuestResultDetail bean,HttpServletRequest request,HttpServletResponse response) {
		Object obj = renderError("The request failed");

		Integer page = bean.getPage();
		String value = bean.getValue();

		GetChosenByScoreUtil scoreUtil = new GetChosenByScoreUtil();
		Integer score = scoreUtil.getScoreByOption(value);

		if (page ==0 || page ==1 || page == 2 ||page == 3){
			bean.setScore(Double.valueOf(0));
		}else {
			bean.setScore(Double.valueOf(score));
		}
		//problemId 做处理。。。。后面需要写个工具方法
		bean.setProblemId(page+47);

		Integer optCount = questProblemService.queryOptionCount(bean);
		if (optCount == 0){
			questProblemService.insertQuestResultDetailNew(bean);
		}else {
			questProblemService.updateQuestResultDetail(bean);
		}

		obj = renderSuccess("The request successed");
		return obj;
	}


	/**
	 * TODO 提交整个问卷答案
	 */
	@RequestMapping("/commitQuest")
	@ResponseBody
	public Object commitQuest(@Valid WxQuestResult bean,HttpServletRequest request,HttpServletResponse response) {
		Object obj = renderError("The request failed");
		Map<String,Object> map = new HashMap<String,Object>();
		GetChosenByScoreUtil util = new GetChosenByScoreUtil();
		List<WxQuestResultDetail> details = bean.getDetails();
		Map<String,Object> valueMap = new HashMap<>();
		for (WxQuestResultDetail detail:details) {
			String value = detail.getValue();
			valueMap.put(String.valueOf(detail.getSize()),value);
		}
		Double score = getScoreWithResult(bean);
		Double realScore = getRealScoreWithResult(bean);
		int scoreInt = score.intValue();
		int realScoreInt = realScore.intValue();
		//加权过的分数
		bean.setScore(Integer.valueOf(scoreInt));
		//真实的分数
		bean.setRealScore(Integer.valueOf(realScoreInt));

		//冒泡排序
		for (int i = 4;i < 11;i++) {
			for (int j = 4; j < (11 - 1 - i); j++) {
				if (details.get(j).getScore() < details.get(j + 1).getScore())
				{
					WxQuestResultDetail temp = details.get(j);
					details.set(j, details.get(j + 1));
					details.set(j + 1, temp);
				}
			}
		}
    		/*
    		 * 获得分数最高两个组合的KEY
    		 * 如：AC，CA
    		 */
		String n1 = details.get(0).getNote();
		String n2 = details.get(1).getNote();
		String conclusion = n1+n2;

		bean.setConclusionName(conclusion);
		bean.setConclusionId(questProblemService.queryConclusionId(conclusion));

		questProblemService.insertQuestResult(bean);


		obj = renderSuccess(map);
		return obj;
	}

	/**
	 * TODO 查询问卷自测结果，带上推荐
	 */
	@RequestMapping("/queryQuestResult")
	@ResponseBody
	public Object queryQuestResult(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
		Object obj = renderError("The request failed");
		Map<String,Object> map = new HashMap<String,Object>();
		List<LastResult> list = questProblemService.queryLastResult(page);

		map.put("result",list);
		map.put("skin",null);

		obj = renderSuccess(map);
		return obj;
	}


	/**
	 * 算分(8道题的平均分)realScore
	 *
	 */
	public double getRealScoreWithResult(WxQuestResult bean){

		List<WxQuestResultDetail> details = bean.getDetails();
		GetChosenByScoreUtil util = new GetChosenByScoreUtil();
		double sumRealScore = 0;
		for (int i = 4; i<12;i++) {
			double score = util.getScoreByOption(details.get(i).getValue());
			sumRealScore += score;
		}
		sumRealScore = sumRealScore/8;
		return sumRealScore;
	}

	/**
	 * 算分（加权之后的平均分）score
	 */
	public double getScoreWithResult(WxQuestResult bean){
		List<Map<String,Double>> qzs = new ArrayList<Map<String,Double>>();
		//权重
		Map<String,Double> qzs_1 = new HashMap<String,Double>();//25岁以下
		qzs_1.put("A", 0.98);qzs_1.put("B", 0.96);qzs_1.put("C", 0.93);qzs_1.put("D", 1.00);
		qzs_1.put("E", 0.97);qzs_1.put("F", 0.91);qzs_1.put("G", 1.02);qzs_1.put("H", 1.04);
		Map<String,Double> qzs_2 = new HashMap<String,Double>();//26-30岁
		qzs_2.put("A", 1.0);qzs_2.put("B", 0.95);qzs_2.put("C", 0.92);qzs_2.put("D", 1.01);
		qzs_2.put("E", 1.05);qzs_2.put("F", 0.9);qzs_2.put("G", 0.94);qzs_2.put("H", 1.02);
		Map<String,Double> qzs_3 = new HashMap<String,Double>();//31-40岁
		qzs_3.put("A", 1.02);qzs_3.put("B", 1.05);qzs_3.put("C", 0.91);qzs_3.put("D", 1.02);
		qzs_3.put("E", 0.98);qzs_3.put("F", 0.89);qzs_3.put("G", 0.90);qzs_3.put("H", 1.00);
		Map<String,Double> qzs_4 = new HashMap<String,Double>();//40岁以上
		qzs_4.put("A", 1.04);qzs_4.put("B", 1.11);qzs_4.put("C", 0.90);qzs_4.put("D", 1.03);
		qzs_4.put("E", 1.00);qzs_4.put("F", 0.88);qzs_4.put("G", 0.89);qzs_4.put("H", 0.98);
		qzs.add(qzs_1);qzs.add(qzs_2);qzs.add(qzs_3);qzs.add(qzs_4);
		//获取生日
		String birthday = bean.getDetails().get(2).getValue();
		String year = birthday.substring(0,3);
		Calendar date = Calendar.getInstance();
		String sysYear = String.valueOf(date.get(Calendar.YEAR));
		int age = Integer.valueOf(sysYear) - Integer.valueOf(year);//年龄
		//拿到8道题的得分
		List<Double> scoreList = new ArrayList<>();
		GetChosenByScoreUtil util = new GetChosenByScoreUtil();
		for (int i = 4; i<12;i++) {
			double score = util.getScoreByOption(bean.getDetails().get(i).getValue());
			scoreList.add(score);
		}
		//根据年龄段加权
		if (age<=25){
			scoreList.set(0,scoreList.get(0) * qzs_1.get("A"));scoreList.set(1,scoreList.get(1) * qzs_1.get("B"));
			scoreList.set(2,scoreList.get(2) * qzs_1.get("C"));scoreList.set(3,scoreList.get(3) * qzs_1.get("D"));
			scoreList.set(4,scoreList.get(4) * qzs_1.get("E"));scoreList.set(5,scoreList.get(5) * qzs_1.get("F"));
			scoreList.set(6,scoreList.get(6) * qzs_1.get("G"));scoreList.set(7,scoreList.get(7) * qzs_1.get("H"));
		}else if (age > 25 && age <=30){
			scoreList.set(0,scoreList.get(0) * qzs_2.get("A"));scoreList.set(1,scoreList.get(1) * qzs_2.get("B"));
			scoreList.set(2,scoreList.get(2) * qzs_2.get("C"));scoreList.set(3,scoreList.get(3) * qzs_2.get("D"));
			scoreList.set(4,scoreList.get(4) * qzs_2.get("E"));scoreList.set(5,scoreList.get(5) * qzs_2.get("F"));
			scoreList.set(6,scoreList.get(6) * qzs_2.get("G"));scoreList.set(7,scoreList.get(7) * qzs_2.get("H"));
		}else if (age > 30  && age <=40){
			scoreList.set(0,scoreList.get(0) * qzs_3.get("A"));scoreList.set(1,scoreList.get(1) * qzs_3.get("B"));
			scoreList.set(2,scoreList.get(2) * qzs_3.get("C"));scoreList.set(3,scoreList.get(3) * qzs_3.get("D"));
			scoreList.set(4,scoreList.get(4) * qzs_3.get("E"));scoreList.set(5,scoreList.get(5) * qzs_3.get("F"));
			scoreList.set(6,scoreList.get(6) * qzs_3.get("G"));scoreList.set(7,scoreList.get(7) * qzs_3.get("H"));
		}else if (age > 40){
			scoreList.set(0,scoreList.get(0) * qzs_4.get("A"));scoreList.set(1,scoreList.get(1) * qzs_4.get("B"));
			scoreList.set(2,scoreList.get(2) * qzs_4.get("C"));scoreList.set(3,scoreList.get(3) * qzs_4.get("D"));
			scoreList.set(4,scoreList.get(4) * qzs_4.get("E"));scoreList.set(5,scoreList.get(5) * qzs_4.get("F"));
			scoreList.set(6,scoreList.get(6) * qzs_4.get("G"));scoreList.set(7,scoreList.get(7) * qzs_4.get("H"));
		}
		double sumScore = 0;
		for (int i = 0;i<8;i++){
			sumScore  += scoreList.get(i);
		}
		sumScore = sumScore/8;
		return sumScore;
	}

}

