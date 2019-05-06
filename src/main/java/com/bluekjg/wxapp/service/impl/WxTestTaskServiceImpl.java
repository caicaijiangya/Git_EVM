package com.bluekjg.wxapp.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.admin.mapper.AnalyticsMapper;
import com.bluekjg.admin.mapper.UserPortraitMapper;
import com.bluekjg.admin.model.Analytics;
import com.bluekjg.admin.model.AnalyticsPage;
import com.bluekjg.admin.model.UserPortrait;
import com.bluekjg.core.commons.utils.AnalyticsUtils;
import com.bluekjg.wxapp.mapper.WxOrderMapper;
import com.bluekjg.wxapp.mapper.WxTestTaskMapper;
import com.bluekjg.wxapp.model.WxActivityFull;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderRefund;
import com.bluekjg.wxapp.service.IWxOrderService;
import com.bluekjg.wxapp.service.IWxPayService;
import com.bluekjg.wxapp.service.IWxTestTaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;

/**
 * @description：定时任务
 * @author：pincui.Tom 
 * @date：2018/9/27 14:51
 */
@Service
@Transactional
public class WxTestTaskServiceImpl extends ServiceImpl<WxOrderMapper, WxOrder>implements IWxTestTaskService {

	protected Logger logger = LoggerFactory.getLogger(WxTestTaskServiceImpl.class);

	@Autowired
	private WxTestTaskMapper testTaskMapper;
	@Autowired
	private IWxPayService payService;
	@Autowired
	private IWxOrderService orderService;
	@Autowired
	private WxOrderMapper orderMapper;
	@Autowired 
	private AnalyticsMapper analyticsMapper;
	@Autowired
	private UserPortraitMapper userPortraitMapper;
	
	@Override
	public void testtask0() {
		List<WxActivityFull> goodsList  = testTaskMapper.selectActivityGoods();
		List<WxActivityFull> fullList  = testTaskMapper.selectActivityGift();
		// 结束超时活动
		testTaskMapper.cancelActivity();
		//商品库存返款
		if(goodsList != null && goodsList.size() > 0) {
			for(WxActivityFull full:goodsList) {
				logger.info("商品:"+full.getGoodsId()+":"+full.getAmount());
				if(full.getStoreId() != 0 && full.getStoreId() > 0) {
					orderMapper.updateStoreGoodsAmount(full.getStoreId(), full.getGoodsId(), full.getAmount());
				}else {
					orderMapper.updateGoodsAmount(full.getGoodsId(), full.getAmount());
				}
				orderMapper.updateActivityGoodsAmount(full.getStoreId(),full.getId(), full.getGoodsId(), full.getAmount()*-1);
			}
		}
		//赠品库存返款
		if(fullList != null && fullList.size() > 0) {
			for(WxActivityFull full:fullList) {
				full.setAmount(full.getAmount() * full.getGoodsNum());
				logger.info("赠品:"+full.getGoodsId()+":"+full.getAmount());
				if(full.getStoreId() != 0 && full.getStoreId() > 0) {
					orderMapper.updateStoreGoodsAmount(full.getStoreId(), full.getGoodsId(), full.getAmount());
				}else {
					orderMapper.updateGoodsAmount(full.getGoodsId(), full.getAmount());
				}
				orderMapper.updateGoodsGiftAmount(full.getStoreId(),full.getId(), full.getGoodsId(),full.getGoodsNum(), full.getAmount()*-1);
			}
		}
	}
	
	@Override
	public void testtask1() {
		//优惠券批量失效
		testTaskMapper.updateUserCouponStatus();
	}
	
	@Override
	public void testtask2() {
		//完成订单
		List<WxOrder> orderList = testTaskMapper.completeOrder();
		if(orderList != null && orderList.size() > 0) {
			for(WxOrder order:orderList) {
				orderService.succeeOrder(order.getId(),order.getStatus(),null);
			}
		}
	}

	@Override
	public void testtask_1() {
		//待退款
		List<WxOrderRefund> refundList = testTaskMapper.queryCancelCollage();
		// 结束超时拼团
		testTaskMapper.cancelCollageOrder();
		testTaskMapper.cancelCollage();
		if(refundList != null && refundList.size() > 0) {
			for(WxOrderRefund refund:refundList) {
				if(refund != null && refund.getStatus() == 1) {
					payService.wxrefund(null,refund);
					testTaskMapper.updateCancelCollage(refund);
					orderMapper.updateOrderStatus(refund.getOrderId(), 4,3);
				}
			}
		}
	}

	@Override
	public void timedtask_10i() {
		// 30分钟不付款自动取消
		List<Integer> list = testTaskMapper.queryCancelOrder();
		if(list != null && list.size() > 0) {
			for(Integer id:list) {
				WxOrder order = orderService.queryOrderById(id);
				if(order != null) {
					orderService.cancelOrder(order);
				}
			}
			
		}
	}

	/**
	 * 定期更新 t_evm_analytics
	 */
	@Override
	public void updateAnalytics() {
		String yesterday = getYesterday();
//		String yesterday = "20190304";
		insertAnalysisDailyVisitTrend(yesterday); 
		updateAnalysisDailySummary(yesterday);
		updateAnalysisVisitDistribution(yesterday);
	}

	private String getYesterday() {
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,-2);
		Date time=cal.getTime();
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(time);
		return yesterday;
	}
	
	private void insertAnalysisDailyVisitTrend(String yesterday) {
		Analytics analytics = analyticsMapper.selectById(yesterday);
		if(analytics!=null) {
			throw new RuntimeException(yesterday + "(t_evm_analytics)的数据已经存过了");
		}
		String res = AnalyticsUtils.getAnalysisDailyVisitTrend(yesterday, yesterday);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(res);
			JsonNode listJson = node.get("list");
			List<Analytics> visitTrendList = mapper.readValue(mapper.writeValueAsString(listJson)
					,new TypeReference<List<Analytics>>() { });
			for(Analytics visitTrend: visitTrendList) {//正常情况下只有一个
				analyticsMapper.insert(visitTrend);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private void updateAnalysisDailySummary(String yesterday) {
		String res = AnalyticsUtils.getAnalysisDailySummary(yesterday, yesterday);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(res);
			JsonNode listJson = node.get("list");
			List<Analytics> list = mapper.readValue(mapper.writeValueAsString(listJson)
					,new TypeReference<List<Analytics>>() { });
			for(Analytics dailySummary: list) {//正常情况下只有一个
				analyticsMapper.updateById(dailySummary);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private void updateAnalysisVisitDistribution(String yesterday) {
		String res = AnalyticsUtils.getAnalysisVisitDistribution(yesterday, yesterday);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(res);
			JsonNode listJson = node.get("list");
			CollectionLikeType type = mapper.getTypeFactory().constructCollectionLikeType(List.class, Map.class);
			List<Map<String,Object>> list = mapper.readValue(mapper.writeValueAsString(listJson),type);
			for(Map<String,Object> map:list) {
				if(map.get("index").equals("access_source_session_cnt")) {
					List<Map<String,Integer>> sub = (List<Map<String, Integer>>) map.get("item_list");//正常情况下只有一个
					Analytics analytics = new Analytics();
					analytics.setRefDate(yesterday);
					for(Map<String,Integer> m: sub) {
						//对应场景值
						switch (m.get("key")) {
							case 2: analytics.setSearch(m.get("value")); break;//搜索
							case 3: analytics.setSession(m.get("value")); break;//会话
							case 14: analytics.setShare(m.get("value")); break;//APP分享
							case 10: analytics.setOther(m.get("value")); break;//其他
						}
					}
					analyticsMapper.updateById(analytics);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void updateUserPortrait() {
		String yesterday = getYesterday();
//		String yesterday = "20190302";
		List<UserPortrait> l = userPortraitMapper.selectList(new EntityWrapper<UserPortrait>().eq("ref_date", yesterday));
		if(l.size()>0) {
			throw new RuntimeException(yesterday + "(t_evm_user_portrait)的数据已经存过了");
		}
		String res = AnalyticsUtils.getAnalysisUserPortrait(yesterday, yesterday);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node;
		List<UserPortrait> list = new ArrayList<>();
		try {
			node = mapper.readTree(res);
			JsonNode listJson = node.get("visit_uv").get("city");
			if (listJson.isArray()) {
			    for (JsonNode objNode : listJson) {
			    	Map<String,Object> map = mapper.readValue(mapper.writeValueAsString(objNode),Map.class);
		    		UserPortrait userPortrait = new UserPortrait();
		    		userPortrait.setRefDate(yesterday);
		    		userPortrait.setCity((String) map.get("name"));
		    		Integer num = (Integer) map.get("value");
		    		userPortrait.setNum(num);
		    		list.add(userPortrait);
			    }
			}
			userPortraitMapper.insertBatch(list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} 
	}

	@Override
	public void updateAnalyticsPage() {
		String yesterday = getYesterday();
//		String yesterday ="20190304";
		Integer count = analyticsMapper.selectCountByRefDate(yesterday);
		if(count>0) {
			throw new RuntimeException(yesterday + "(t_evm_analytics_page)的数据已经存过了");
		}
		String res = AnalyticsUtils.getAnalysisVisitPage(yesterday, yesterday);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(res);
			JsonNode listJson = node.get("list");
			List<AnalyticsPage> list = mapper.readValue(mapper.writeValueAsString(listJson)
					,new TypeReference<List<AnalyticsPage>>() { });
			for(AnalyticsPage ap:list) {
				ap.setRefDate(yesterday);
			}
			analyticsMapper.insertPageBatch(list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} 
	}

}