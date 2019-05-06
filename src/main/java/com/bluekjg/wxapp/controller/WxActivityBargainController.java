package com.bluekjg.wxapp.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.redis.key.RedisKey;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxActivityGoodsBargainPrice;
import com.bluekjg.wxapp.model.WxBargainGoods;
import com.bluekjg.wxapp.model.WxBargainGoodsDetail;
import com.bluekjg.wxapp.model.WxBargainGoodsUserLog;
import com.bluekjg.wxapp.model.WxDict;
import com.bluekjg.wxapp.model.WxGoods;
import com.bluekjg.wxapp.model.WxGoodsIndex;
import com.bluekjg.wxapp.service.IWxBargainService;
import com.bluekjg.wxapp.service.IWxDictService;
import com.bluekjg.wxapp.service.IWxGoodsService;
import com.bluekjg.wxapp.service.IWxIndexService;
import com.bluekjg.wxapp.service.IWxUserService;
import com.bluekjg.wxapp.utils.CommonUtil;
import com.bluekjg.wxapp.utils.DictUtil;
import com.bluekjg.wxapp.utils.PostObjectSample;
import com.bluekjg.wxapp.utils.RedisUtil;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

@Controller
@RequestMapping("/xcx/activityBargain")
public class WxActivityBargainController extends BaseController{
	@Autowired
	private IWxBargainService bargainService;
	@Autowired
	private IWxGoodsService goodsService;
	@Autowired
	private IWxIndexService indexService;
	@Autowired
	private IWxDictService dictService;
	@Autowired
	private IWxUserService userService;
	@Autowired 
	private RedisUtil redisUtil;
	
	/**
     * 查询最新成功砍价
     * 五秒内
     * @param resource
     * @return
     */
    @RequestMapping("/queryNew")
    @ResponseBody
    public Object queryNew(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxBargainGoods bg = null;
        	Date date = new Date();
        	for(int i=1;i<=6;i++) {
        		Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.SECOND, (i*-1));
                String dateStr = DateUtil.parseDateToStr(calendar.getTime(), DateUtil.DATE_TIME_FORMAT_YYYYMMDDHHMISS);
            	Object bargain = redisUtil.get(RedisKey.BARGAIN_CHENGONG_PROMPT+dateStr);
                if(bargain != null) {
                	bg = (WxBargainGoods) bargain;
                	break;
                }
        	}
        	obj = renderSuccess(bg);
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
	/**
     * 查询砍价详情
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryBargain")
    @ResponseBody
    public Object queryBargain(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	WxBargainGoods bargain = bargainService.queryBargainById(page);
        	int count = bargainService.queryBargainGoodsDetailCount(page);
        	WxDict dict = dictService.queryDictByCode(DictUtil.RULES_BARGAIN);
        	UserBean userInfo = userService.selectByOpenId(page.getOpenId());
        	if(bargain != null) {
        		bargain.setTime(goodsService.activityDate(bargain.getStartTime(), bargain.getEndTime()));
        		WxActivityGoods activity = bargainService.selectActivityById(bargain.getStoreId(),bargain.getActivityId(),bargain.getSpecId());
        		map.put("activity", activity);
        	}
        	map.put("dict", dict);
        	map.put("count", count);
        	map.put("bargain", bargain);
        	map.put("userInfo", userInfo);
        	obj = renderSuccess(map);
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 生成二维码
     * @param id
     * @return
     */
    public void updateQrCode(Integer id) {
    	String requestUrl = WxappConfigUtil.WX_TOKEN_URL.replace("APPID", WxappConfigUtil.WX_APPID).
                replace("SECRET", WxappConfigUtil.WX_SECRET);
        // 发起GET请求获取凭证
		Map<String,String>  jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (jsonObject != null && jsonObject.get("access_token") != null) {
        	String accessToken = jsonObject.get("access_token");
       		if(accessToken != null) {
       			String qrcodeUrl = WxappConfigUtil.WX_QRCODE_URL.replace("ACCESS_TOKEN", accessToken);
       			Map<String, Object> param = new HashMap<String, Object>();
       			param.put("page", "page/customer/pages/activity/bargain/detail");
    			param.put("width", 430);
    			param.put("scene", id);
    			param.put("auto_color", false);
    			Map<String, Object> line_color = new HashMap<>();
    			line_color.put("r", 255);
    			line_color.put("g", 255);
    			line_color.put("b", 255);
    			param.put("line_color", line_color);
    			logger.info("调用生成微信URL接口传参:" + param);
    			InputStream inputStream = CommonUtil.httpsPostParame(qrcodeUrl, param);
    			if(inputStream != null) {
					// 生成新文件名
					String newName = UUID.randomUUID().toString().replaceAll("-", "");
					newName = newName + ".png";
					// 文件上传
					String imagePath = DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD);
					// 文件上传
					Integer result = PostObjectSample.PostObject(imagePath + "/" + newName, "image/png",inputStream);
					// 返回结果
					if (result > 0) {
						String qrCode = WxappConfigUtil.ALICLOUD_IMAGE_BASE_URL + imagePath + "/" + newName;
						WxBargainGoods bargain = new WxBargainGoods();
						bargain.setQrCode(qrCode);
						bargain.setJoinPrice(0.0);
						bargain.setId(id);
						bargainService.updateBargainGoods(bargain);
					}
    			}
       		}
        }
    }
	/**
     * 发起助力
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addBargain")
    @ResponseBody
    public Object addBargain(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	/*
        	 * 验证是否黑名单用户
        	 */
        	UserBean user = userService.queryBlacklist(page.getOpenId());
        	if(user != null) {
    			obj = renderError("不允许购买");
    			return obj;
    		}
        	Map<String,Object> map = new HashMap<String,Object>();
        	//验证活动
    		WxActivityGoods activity = bargainService.selectActivityById(null,page.getActivityId(),page.getGoodsId());
    		page.setStatus(0);
        	Integer id0 = bargainService.queryBargainStatusById(page);
        	page.setStatus(1);
        	
        	if(activity != null && activity.getBargain() != null) {
        		page.setStoreId(activity.getStoreId());
        		Integer id1 = bargainService.queryBargainStatusById(page);
            	Integer count = bargainService.queryBargainStatus(page);
            	Integer amount = bargainService.queryBargainGoodsAmountCount(page);
        		if(id0 == null) {
					if(id1 == null) {
						if(activity.getBargain().getBargainNum() == 0 || activity.getBargain().getBargainNum() > count) {
							if(activity.getActivityAmount() > 0 && activity.getActivityAmount() > amount) {
    							WxBargainGoods bargain = new WxBargainGoods();
        						bargain.setStatus(0);
        						double price = activity.getBargain().getPrice();
        						if(price == 0.0) {
        							price = 10.0;
        							if(activity.getBargain().getPriceList() != null && activity.getBargain().getPriceList().size() > 0) {
        								price = activity.getBargain().getPriceList().get(activity.getBargain().getPriceList().size() - 1).getPrice();
        							}
                				}
        						if((activity.getGoodsPrice() - price) < activity.getActivityPrice()) {
        							price = activity.getGoodsPrice() - activity.getActivityPrice();
            						bargain.setStatus(1);
        						}
        						//阶梯砍价
        						if(activity.getBargain().getBargainType() == 1) {
        							price = 0.0;
            						bargain.setStatus(0);
        						}
        						bargain.setActivityId(page.getActivityId());
        						bargain.setGoodsId(page.getGoodsId());
        						bargain.setJoinPrice(price);
        						bargain.setTimeNum(activity.getBargain().getTime());
        						bargain.setOpenId(page.getOpenId());
        						bargain.setStoreId(activity.getStoreId());
        						bargainService.insertBargainGoods(bargain);
        						updateQrCode(bargain.getId());//更新二维码
        						map.put("status", 0);
        						map.put("id", bargain.getId());
        						if(activity.getBargain().getBargainType() == 0) {
        							map.put("msg", "你为自己成功砍掉了"+bargain.getJoinPrice()+"元，快去邀请更多好友来助力吧！");
        						}else if(activity.getBargain().getBargainType() == 1) {
        							map.put("msg", "你已成功发起助力，快去邀请更多好友来助力吧！");
        						}
        						
        						obj = renderSuccess(map);
    						}else {
    		    				obj = renderError("库存不足");
    		    			}
						}else {
		    				obj = renderError("超出限制");
		    			}
						
					}else {
						map.put("status", 1);
						map.put("id", id1);
						map.put("msg", "当前商品已经助力成功");
						obj = renderSuccess(map);
					}
				}else {
					map.put("status", 0);
					map.put("id", id0);
					map.put("msg", "当前商品正在助力中，快去邀请更多好友来助力吧");
					obj = renderSuccess(map);
    			}
    		}else {
    			obj = renderError("活动已结束");
    		}
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
	
    /**
     * 助力砍价
     *
     * @param resource
     * @return
     */
    @RequestMapping("/helpBargain")
    @ResponseBody
    public Object helpBargain(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	WxBargainGoods bargain = bargainService.queryBargainGoodsId(page);
        	if(bargain != null) {
        		WxActivityGoods activity = bargainService.selectActivityById(bargain.getStoreId(),bargain.getActivityId(),bargain.getGoodsId());
            	if(activity != null && activity.getBargain() != null) {
            		page.setActivityId(bargain.getActivityId());
        			page.setGoodsId(bargain.getGoodsId());
            		//是否助砍过
                	int count = bargainService.queryBargainGoodsDetailCount(page);
                	//已助力次数
                	int isCount = bargainService.queryBargainGoodsCount(page);
                	if(count == 0) {
                		if(activity.getBargain().getHelpBargainNum() > isCount) {
                			if(activity.getBargain().getBargainType() == 1 || (activity.getGoodsPrice() - bargain.getJoinPrice()) > activity.getActivityPrice()) {
                				WxBargainGoodsDetail bargainDetail = new WxBargainGoodsDetail();
                				bargainDetail.setStatus(0);
                				double price = activity.getBargain().getPrice();
                				if(price == 0.0) {
                					//计算未使用价格的次数
                					List<Double> randomPriceList = new ArrayList<Double>();
                					if(activity.getBargain().getPriceList() != null && activity.getBargain().getPriceList().size() > 0) {
                						for(WxActivityGoodsBargainPrice bprice:activity.getBargain().getPriceList()) {
                							int calculate = 0;//单价格次数累计
                							if(bargain.getDetails() != null && bargain.getDetails().size() > 0) {
                								for(WxBargainGoodsDetail detail:bargain.getDetails()) {
                									if(detail.getPrice().equals(bprice.getPrice())) {
                										calculate ++;
                									}
                								}
                							}
                							if(bprice.getUseNum() != null) {
                								//将价格存入集合中
                    							for(int i=0;i<bprice.getUseNum() - calculate;i++) {
                    								randomPriceList.add(bprice.getPrice());
                    							}
                							}
                						}
                					}
                					//随机使用一个价格
                					if(randomPriceList != null && randomPriceList.size() > 0) {
                						int random = (int)(Math.random()*randomPriceList.size());
                						if(random >= randomPriceList.size() ) {random = randomPriceList.size() - 1;}
                						price = randomPriceList.get(random);
                					}else {
                						price = randomPrice((int)(Math.random()*1000));
                					}
                				}
                    			if((activity.getGoodsPrice() - (bargain.getJoinPrice() + price)) <= activity.getActivityPrice()) {
                    				price = activity.getGoodsPrice() - (activity.getActivityPrice() + bargain.getJoinPrice());
                    				bargainDetail.setStatus(1);
                    			}
                    			//阶梯砍价
                    			if(activity.getBargain().getBargainType() == 1) {
                    				price = 0.0;
                    				int fullNum = activity.getBargain().getLadderList().get(activity.getBargain().getLadderList().size()-1).getFullNum();
                    				int detail_count = bargain.getDetails().size();
                    				if((fullNum-1) == detail_count) {
                    					bargainDetail.setStatus(1);
                    				}else {
                    					bargainDetail.setStatus(0);
                    				}
                    			}
                    			bargainDetail.setBargainId(page.getId());
                    			bargainDetail.setOpenId(page.getOpenId());
                    			bargainDetail.setPrice(price);
                    			bargainDetail.setIsBargain(1);
                    			bargainService.insertBargainGoodsDetail(bargainDetail);
                    			
                    			//助砍赠送
                    			int logCount = bargainService.queryBargainGoodsUserLogCount(page);
                    			if(activity.getBargain().getType() > 0 && logCount < activity.getBargain().getGivingNum()) {
                    				WxBargainGoodsUserLog log = new WxBargainGoodsUserLog();
                    				log.setActivityId(bargain.getActivityId());
                    				log.setGoodsId(bargain.getGoodsId());
                    				log.setOpenId(page.getOpenId());
                    				log.setType(activity.getBargain().getType());
                    				log.setValue(0);
                    				map.put("msg", "为好友助力成功！");
                    				if(activity.getBargain().getType() == 1) {
                    					log.setValue(activity.getBargain().getIntegral());
                    					map.put("msg", "为好友助力成功！获得"+activity.getBargain().getIntegral()+"点积分");
                    				}else if(activity.getBargain().getType() == 2) {
                    					log.setValue(activity.getBargain().getCouponId());
                    					map.put("msg", "为好友助力成功！获得"+activity.getBargain().getCouponMoney()+"元优惠券");
                    				}
                    				bargainService.insertBargainGoodsUserLog(log);
                    				map.put("status", 3);
            						obj = renderSuccess(map);
                    			}else {
                    				map.put("status", 2);
            						map.put("msg", "为好友助力成功！");
            						obj = renderSuccess(map);
                    			}
                			}else {
                				WxBargainGoods bargain_ = new WxBargainGoods();
                				bargain_.setJoinPrice(0.0);
                				bargain_.setId(page.getId());
                				bargain_.setStatus(1);
                				bargainService.updateBargainGoods(bargain_);
                				obj = renderError("助力已结束");
                			}
                			
                		}else {
                			obj = renderError("已达帮砍上限");
                		}
                	}else {
                		obj = renderError("已经砍过了");
                	}
            	}else {
        			obj = renderError("活动已结束");
        		}
        	}else {
    			obj = renderError("助力已结束");
    		}
        	
        	
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    
    
    
    /**
     * 查询为你推荐
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryGoods")
    @ResponseBody
    public Object queryGoods(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	int num = 4;
        	List<Object> list = new ArrayList<Object>();
        	if(page.getClassifyId() != null && page.getClassifyId().length() > 0) {
        		page.setItems(page.getClassifyId().split(","));
        		List<Integer> idList = new ArrayList<Integer>();
            	List<WxGoods> goodsList = goodsService.queryGoodsClassifyList(page);
            	if(goodsList == null || goodsList.size() < num) {
            		if(goodsList != null) {
            			for(WxGoods goods:goodsList) {
            				list.add(goods);
            				idList.add(goods.getId());
            			}
            		}
            		List<WxGoodsIndex> goodsIndexList = indexService.queryGoodsHotList(page);
            		if(goodsIndexList != null && goodsIndexList.size() > 0) {
            			for(int i=0;i< goodsIndexList.size();i++) {
            				if(list.size() < num) {
            					boolean bool = true;
            					for(Integer id:idList) {
            						if(goodsIndexList.get(i).getId().intValue() == id.intValue()) {
            							bool = false;
            						}
            					}
            					if(bool) {
            						list.add(goodsIndexList.get(i));
            					}
            				}
                		}
            		}
            	}else {
            		for(int i=0;i<goodsList.size();i++) {
            			if(i < num) {
            				list.add(goodsList.get(i));
            			}
            		}
            	}
        	}
        	
        	obj = renderSuccess(list);
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    public double randomPrice(int random) {
    	double pro10 = 0.00, 
    			pro09 = 0.00,
    			pro08 = 0.00, 
    			pro07 = 0.00,
    			pro06 = 0.01,
    			pro05 = 0.02,
    			pro04 = 0.05,
    			pro03 = 0.12,
    			pro02 = 0.30,
    			pro01 = 0.50;
    	//System.out.println(pro10+pro09+pro08+pro07+pro06+pro05+pro04+pro03+pro02+pro01);
    	Double prices[] = new Double[1000];
    	for(int i=0;i<1000;i++) {
    		if(i < ((int)(pro10*1000))) {
    			prices[i] = 10.0;
    		}else if(i < ((int)((pro10+pro09)*1000))) {
    			prices[i] = 9.0;
    		}else if(i < ((int)((pro10+pro09+pro08)*1000))) {
    			prices[i] = 8.0;
    		}else if(i < ((int)((pro10+pro09+pro08+pro07)*1000))) {
    			prices[i] = 7.0;
    		}else if(i < ((int)((pro10+pro09+pro08+pro07+pro06)*1000))) {
    			prices[i] = 6.0;
    		}else if(i < ((int)((pro10+pro09+pro08+pro07+pro06+pro05)*1000))) {
    			prices[i] = 5.0;
    		}else if(i < ((int)((pro10+pro09+pro08+pro07+pro06+pro05+pro04)*1000))) {
    			prices[i] = 4.0;
    		}else if(i < ((int)((pro10+pro09+pro08+pro07+pro06+pro05+pro04+pro03)*1000))) {
    			prices[i] = 3.0;
    		}else if(i < ((int)((pro10+pro09+pro08+pro07+pro06+pro05+pro04+pro03+pro02)*1000))) {
    			prices[i] = 2.0;
    		}else if(i < ((int)((pro10+pro09+pro08+pro07+pro06+pro05+pro04+pro03+pro02+pro01)*1000))) {
    			prices[i] = 1.0;
    		}
    	}
    	if(random >=1000 ) {random = 999;}
    	return prices[random];
    }
    
    public static void main(String[] arge) {
    	WxActivityBargainController xx = new WxActivityBargainController();
    	Map<Integer,Integer> map = new HashMap<Integer, Integer>();
    	for(int i=0;i<100;i++) {
    		int price = (int) xx.randomPrice((int)(Math.random()*1000));
    		int count = 1;
    		if(map.get(price) != null) {
    			count = map.get(price) + 1;
    		}
    		map.put(price, count);
    	}
    	for (Integer key : map.keySet()) { 
    		System.out.println(key+":"+map.get(key)); 
    	} 
    }
}
