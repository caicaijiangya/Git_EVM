package com.bluekjg.wxapp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.DictData;
import com.bluekjg.admin.service.IDictService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxActivityFull;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxActivityGoodsCollage;
import com.bluekjg.wxapp.model.WxAddress;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.model.WxCoupon;
import com.bluekjg.wxapp.model.WxGoods;
import com.bluekjg.wxapp.model.WxGoodsFreight;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderAddress;
import com.bluekjg.wxapp.model.WxOrderDetail;
import com.bluekjg.wxapp.model.WxOrderRefund;
import com.bluekjg.wxapp.model.WxStore;
import com.bluekjg.wxapp.service.IWxAddressService;
import com.bluekjg.wxapp.service.IWxCollageService;
import com.bluekjg.wxapp.service.IWxCouponService;
import com.bluekjg.wxapp.service.IWxErpService;
import com.bluekjg.wxapp.service.IWxGoodsService;
import com.bluekjg.wxapp.service.IWxOrderService;
import com.bluekjg.wxapp.service.IWxStoreService;
import com.bluekjg.wxapp.service.IWxUserService;
import com.bluekjg.wxapp.utils.DictUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/xcx/order")
public class WxOrderController extends BaseController{
	@Autowired
	private IWxGoodsService goodsService;
	@Autowired
	private IWxStoreService storeService;
	@Autowired
	private IWxAddressService addressService;
	@Autowired
	private IWxCouponService couponService;
	@Autowired
	private IWxOrderService orderService;
	@Autowired
	private IWxCollageService collageService;
	@Autowired
	private IDictService dictService;
	@Autowired
	private IWxUserService userService;
	@Autowired
	private IWxErpService erpService;
	 /**
     * 查询我的订单列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryMyOrder")
    @ResponseBody
    public Object queryMyOrder(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxOrder> list = orderService.queryOrderList(page);
        	obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询我的退款订单列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryMyRefundOrder")
    @ResponseBody
    public Object queryMyRefundOrder(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxOrder> list = orderService.queryOrderRefundList(page);
        	obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 查询我的默认收货地址
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryMyAddress")
    @ResponseBody
    public Object queryMyAddress(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxAddress address = addressService.queryAddressByOpenId(page.getOpenId());
        	obj = renderSuccess(address);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询收货地址
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryAddress")
    @ResponseBody
    public Object queryAddress(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
    	try {
    		WxAddress address = addressService.queryAddressById(page.getAddressId());
    		obj = renderSuccess(address);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 查询订单详情
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryOrderDetail")
    @ResponseBody
    public Object queryOrderDetail(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxOrder order = orderService.queryOrderById(page.getId());
        	obj = renderSuccess(order);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
	
	/**
	 * 我的支付记录
	 * @param orderId
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryTrans")
	@ResponseBody
	public Object queryTrans(@Valid Integer orderId,HttpServletRequest request) {
		Object obj = renderError("支付失败");
		try {
			WxOrder order = orderService.queryOrderById(orderId);
			if(order != null) {
				order.setCollageId(collageService.queryCollageGoodsId(order.getId()));
			}
			obj = renderSuccess(order);
		}  catch (Exception e) {
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
    @RequestMapping("/orderGoods")
    @ResponseBody
    public Object orderGoods(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	JSONArray array = JSONArray.fromObject(request.getParameter("goodsJson"));
        	List<Map<String, Object>> jsonList= JSONArray.toList(array, new HashMap<String, Object>(), new JsonConfig());
        	List<WxGoods> goodsList = null;
        	WxStore store = storeService.queryStoreById(page.getStoreId());
        	WxAddress address = addressService.queryAddressById(page.getAddressId());
        	if(page.getStoreId() != null && page.getStoreId() > 0) {
        		goodsList = goodsService.queryStoreGoods(String.valueOf(page.getStoreId()), jsonList);
        	}else {
        		goodsList = goodsService.queryOrderGoods(jsonList);
        	}
        	if(goodsList != null && goodsList.size() > 0) {
        		for(int i=0;i<goodsList.size();i++) {
        			for(int j=0;j<jsonList.size();j++) {
    					if(goodsList.get(i).getSpecId().intValue() == Integer.valueOf(jsonList.get(j).get("specId").toString()).intValue()) {
    						goodsList.get(i).setGoodsNum(Integer.valueOf(jsonList.get(j).get("goodsNum").toString()));
    					}
        			}
        			WxActivityGoods activity = goodsService.queryActivityGoods(page.getStoreId(), goodsList.get(i).getSpecId(),null);
        			WxActivityGoods activityLadder = goodsService.queryActivityGoodsLadder(page.getStoreId(),goodsList.get(i).getSpecId(), goodsList.get(i).getGoodsNum());
        			if(activity != null && activity.getCollages() != null && activity.getCollages().size() > 0) {
        				for(WxActivityGoodsCollage collage:activity.getCollages()) {
        					if(collage.getId() == page.getCollageId()) {
        						activity.setActivityPrice(collage.getCollagePrice());
        					}
        				}
        			}
        			goodsList.get(i).setActivity(activity);
        			goodsList.get(i).setActivityLadder(activityLadder);
        			if(goodsList.get(i).getActivity() != null) {
        				PagingDto page1 = new PagingDto();
        				page1.setActivityId(goodsList.get(i).getActivity().getId());
        				page1.setGoodsId(goodsList.get(i).getActivity().getGoodsId());
        				page1.setOpenId(page.getOpenId());
        				page1.setStoreId(page.getStoreId());
        				Integer buyUserNum = orderService.queryActivityOrderCount(page1);
        	        	Integer collUserNum = collageService.queryCollageByIsUser(page1);
        	        	goodsList.get(i).getActivity().setBuyUserNum(buyUserNum);
        	        	goodsList.get(i).getActivity().setCollUserNum(collUserNum);
        			}
        		}
        		
        	}
        	//设置补充商品为 普通商品
        	if(goodsList == null) {goodsList = new ArrayList<WxGoods>();}
        	List<Map<String, Object>> jsonList1 = new ArrayList<Map<String, Object>>();
    		for(int i=0;i<jsonList.size();i++) {
    			boolean bool = true;
    			if(goodsList != null && goodsList.size() > 0) {
    				for(int j=0;j<goodsList.size();j++) {
    					if(goodsList.get(j).getSpecId().intValue() == Integer.valueOf(jsonList.get(i).get("specId").toString()).intValue()) {
    						bool = false;
    					}
        			}
    			}
    			if(bool) {jsonList1.add(jsonList.get(i));}
			}
    		if(jsonList1 != null && jsonList1.size() > 0) {
    			List<WxGoods> goodsListBc = goodsService.queryOrderGoods(jsonList1);
        		if(page.getStoreId() != null && page.getStoreId() > 0 && goodsListBc != null && goodsListBc.size() > 0) {
        			for(int i=0;i<goodsListBc.size();i++) {
        				goodsListBc.get(i).setActivity(null);
        				goodsListBc.get(i).setActivityLadder(null);
        				goodsListBc.get(i).setIsDel(1);
        			}
        		}
        		goodsList.addAll(goodsListBc);
    		}
    		
        	map.put("store", store);
        	map.put("address", address);
        	map.put("goodsList", goodsList);
        	obj = renderSuccess(map);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 满减满赠打折查询
     *
     * @param resource
     * @return
     */
    @RequestMapping("/orderFull")
    @ResponseBody
    public Object orderFull(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	JSONArray array = JSONArray.fromObject(request.getParameter("goodsJson"));
        	List<Map<String, Object>> jsonList= JSONArray.toList(array, new HashMap<String, Object>(), new JsonConfig());
        	List<WxActivityFull> preList = null;
        	List<WxActivityFull> giftList = null;
        	List<WxActivityFull> discountList = null;
        	if(jsonList != null && jsonList.size() > 0) {
        		preList = goodsService.queryActivityFullPre(String.valueOf(page.getStoreId()), jsonList);
            	giftList = goodsService.queryActivityFullGift(String.valueOf(page.getStoreId()), jsonList);
            	discountList = goodsService.queryActivityFullDiscount(String.valueOf(page.getStoreId()), jsonList);
        	}
        	map.put("preList", preList);
        	map.put("giftList", giftList);
        	map.put("discountList", discountList);
        	obj = renderSuccess(map);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询运费
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryFreight")
    @ResponseBody
    public Object queryFreight(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	if(page.getProvinceId() != null && page.getProvinceId() > 0) {
        		WxGoodsFreight freight = goodsService.queryGoodsFreight(page.getProvinceId());
	        	if(freight != null) {
	        		obj = renderSuccess(freight);
	        	}
        	}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询优惠券信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryCoupon")
    @ResponseBody
    public Object queryCoupon(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<Map<String,Object>> itemList = new ArrayList<Map<String,Object>>();
        	JSONArray array = JSONArray.fromObject(request.getParameter("couponGoods"));
        	for(int i=0;i<array.size();i++) {
        		JSONArray array1 = JSONArray.fromObject(array.get(i));
        		Map<String,Object> map = new HashMap<String,Object>();
        		map.put("goodsId", array1.get(0));
        		map.put("goodsPrice", array1.get(1));
        		map.put("isOriginal", array1.get(2));
        		map.put("isActivity", array1.get(3));
        		itemList.add(map);
        	}
        	page.setItemList(itemList);
        	List<WxCoupon> couponList = couponService.queryOrderCoupon(page);
        	obj = renderSuccess(couponList);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    /**
     * 生成订单
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addOrder")
    @ResponseBody
    public Object addOrder(@Valid WxOrder order,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("订单错误");
        try {
        	/*
        	 * 验证是否黑名单用户
        	 */
        	UserBean user = userService.queryBlacklist(order.getOpenId());
        	if(user != null) {
    			obj = renderError("不允许购买");
    			return obj;
    		}
        	String json = request.getParameter("json");
        	String isInBuyStr = request.getParameter("isInBuy");
        	Integer isInBuy = 0;//是否单独购买（0否，1是）
        	if(isInBuyStr != null && isInBuyStr.length() > 0) {
        		isInBuy = Integer.valueOf(isInBuyStr);
        	}
        	if(order.getActivityId() == null) {order.setActivityId(0);}
        	if(isInBuy == 1) {
        		//取消活动
        		order.setActivityId(0);
        		order.setCollageId(0);
        	}
        	/*
    		 * 解析商品JSON转WxOrderDetail实体
    		 */
    		List<WxOrderDetail> details = new ArrayList<WxOrderDetail>();
    		JSONArray array = JSONArray.fromObject(json);
    		List<Map<String, Object>> jsonList= JSONArray.toList(array, new HashMap<String, Object>(), new JsonConfig());
    		for(int i=0;i<jsonList.size();i++) {
    			WxOrderDetail detail = new WxOrderDetail();
    			Map<String, Object> goodsJson = jsonList.get(i);
    			detail.setActivityGoodsId(Integer.valueOf(goodsJson.get("activityGoodsId").toString()));
    			detail.setGoodsId(Integer.valueOf(goodsJson.get("goodsId").toString()));
    			detail.setGoodsNum(Integer.valueOf(goodsJson.get("goodsNum").toString()));
    			detail.setGoodsPrice(Double.valueOf(goodsJson.get("goodsPrice").toString()));
    			detail.setSpecId(Integer.valueOf(goodsJson.get("specId").toString()));
    			detail.setOriginalPrice(Double.valueOf(goodsJson.get("originalPrice").toString()));
    			detail.setGoodsSpec(orderService.selectgoodsSpecById(Integer.valueOf(goodsJson.get("specId").toString())));
    			details.add(detail);
    		}
    		if(details != null && details.size() == 0) {
    			obj = renderError("无订单商品");
    			return obj;
    		}
    		WxCollageGoods collage = null;
    		
    		double freeFreightBalances = 0.0;//免运费商品金额
    		List<WxGoods> goodsList = null;
			if(order.getStoreId() != null && order.getStoreId() > 0) {
        		goodsList = goodsService.queryStoreGoods(String.valueOf(order.getStoreId()), jsonList);
        	}else {
        		goodsList = goodsService.queryOrderGoods(jsonList);
        	}
			if(goodsList != null && goodsList.size() > 0) {
				for(int i=0;i<goodsList.size();i++) {
					int num = 1;
					WxOrderDetail orderGoods = null;
					for(int j=0;j<details.size();j++) {
    					if(goodsList.get(i).getSpecId().equals(details.get(j).getSpecId())) {
    						orderGoods = details.get(j);
    						num = details.get(j).getGoodsNum();
    					}
    				}
					Integer orderType = 0;
					Integer smallType= 3;
					WxActivityGoods activity = goodsService.queryActivityGoods(order.getStoreId(), goodsList.get(i).getSpecId(),null);
        			WxActivityGoods activityLadder = goodsService.queryActivityGoodsLadder(order.getStoreId(),goodsList.get(i).getSpecId(), num);
        			if(activity == null && activityLadder != null) {activity = activityLadder;orderType = 0;}
        			goodsList.get(i).setActivity(activity);
        			
        			if(order.getActivityId() == 0 && goodsList.get(i).getActivity() != null) {
						if (goodsList.get(i).getActivity().getActivityType() == 1 || goodsList.get(i).getActivity().getActivityType() == 2 || goodsList.get(i).getActivity().getActivityType() == 3 || goodsList.get(i).getActivity().getActivityType() == 4){
							goodsList.get(i).setActivity(null);
					    }
					}
        			//满减满赠去除活动
        			if(activity != null && (activity.getActivityType() == 5 || activity.getActivityType() == 6 || activity.getActivityType() == 7)) {
        				goodsList.get(i).setActivity(null);
        			}
					//验证活动
					WxActivityGoods activityType = goodsList.get(i).getActivity();
		        	if(activityType != null) {
		        		if(order.getActivityId() == 0) {
		        			order.setActivityId(activityType.getId());
		        		}
		        		if(activityType.getIsFreeFreight() == 1) {
		        			//计算免运费的金额
		        			freeFreightBalances += orderGoods.getGoodsPrice();
		        		}
		    			if(activityType.getActivityType() >= 4) {
		    				order.setOrderType(activityType.getActivityType() + 1);
		    			}else {
		    				order.setOrderType(activityType.getActivityType());
		    			}
		    			//order.setCollageNum(activityType.getCollageNum());
		    			//order.setCollageTime(activityType.getCollageTime());
		    			//验证拼团
		    			if(activityType.getActivityType() == 2 && order.getCollageId() != null && order.getCollageId() > 0) {
		    				collage = orderService.selectCollageGoodsObj(order.getCollageId());
		    				if(collage == null || collage.getStatus() != 0 || collage.getCollageNum() <= collage.getJoinNum()) {
		    					obj = renderError("拼团已结束");
		            			return obj;
		    				}
		    			}
		    		}
		        	
		        	
					if(order.getOrderType() == 1) {
						orderType = 2;
					}else if(order.getOrderType() == 2) {
						orderType = 4;
					}else if(order.getOrderType() == 3) {
						orderType = 3;
					}else if(order.getOrderType() == 4) {
						orderType = 6;
					}else if(order.getOrderType() == 5) {
						orderType = 9;
					}else if(order.getOrderType() == 6) {
						orderType = 10;
					}else if(order.getOrderType() == 7) {
						orderType = 11;
					}else if(order.getOrderType() == 8) {
						orderType = 12;
					}
					int goodsId = goodsList.get(i).getSpecId();
					//秒杀/拼团/特价/砍价
					if(order.getOrderType() == 1 || order.getOrderType() == 2 || order.getOrderType() == 3 || order.getOrderType() == 5) {
						smallType = 0;
					}
		        	///添加商品图片
		        	for(int j=0;j<details.size();j++) {
		        		if(details.get(j).getGoodsId().equals(goodsList.get(i).getId())) {
		        			details.get(j).setGoodsImages(goodsService.queryGoodsImages(goodsId, orderType,smallType));
		        		}
		        	}
		        	
		        	if(goodsList.get(i).getActivity() == null && goodsList.get(i).getGoodsAmount() < orderGoods.getGoodsNum()) {
    					obj = renderError("商品库存不足");
    					return obj;
					}else if(goodsList.get(i).getActivity() != null && goodsList.get(i).getActivity().getActivityAmount() < orderGoods.getGoodsNum()) {
    					obj = renderError("商品库存不足");
    					return obj;
					}
	    		}
			}else {
				obj = renderError("无商品");
    			return obj;
			}
			/*
    		 * 验证收货地址
    		 */
    		WxOrderAddress orderAddress = null;
    		if(order.getAddressId() != null && order.getAddressId() > 0) {
    			WxAddress address = addressService.queryAddressById(order.getAddressId());
    			if(address != null) {
    				orderAddress = new WxOrderAddress();
    				orderAddress.setProvince(address.getProvince());
    				orderAddress.setCity(address.getCity());
    				orderAddress.setArea(address.getArea());
    				orderAddress.setAddressDetail(address.getAddressDetail());
    				orderAddress.setMobileNo(address.getMobileNo());
    				orderAddress.setName(address.getName());
    				orderAddress.setFreight(0.0);
    				double totalBalances = order.getTotalBalances() - freeFreightBalances;
    				//配送费用
    				WxGoodsFreight freight = goodsService.queryGoodsFreight(orderAddress.getProvince());
        			if(freight != null) {
        				if(totalBalances != 0 && totalBalances < freight.getFullMoney()) {
        					orderAddress.setFreight(freight.getMoney());
        				}
        			}
    			}else {
    				obj = renderError("收货地址错误");
    				return obj;
    			}
    		}
			//生成订单号
			order.setOrderNo(orderService.createOrderNo());
			order.setStatus(0);
			orderService.insertOrder(order,details,orderAddress,collage);
			obj = renderSuccess(order.getId());
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 确认收货
     *
     * @param resource
     * @return
     */
    @RequestMapping("/confirmOrder")
    @ResponseBody
    public Object confirmOrder(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("操作失败");
        try {
        	WxOrder order = orderService.queryOrderById(page.getId());
        	if(order.getStatus() == 2) {
        		orderService.succeeOrder(order.getId(),order.getStatus(),null);
        		obj = renderSuccess("ok");
        	}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 取消订单
     *
     * @param resource
     * @return
     */
    @RequestMapping("/cancelOrder")
    @ResponseBody
    public Object cancelOrder(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("操作失败");
        try {
        	WxOrder order = orderService.queryOrderById(page.getId());
        	if(order.getStatus() == 0 || order.getStatus() == 1 || order.getStatus() == 2 || order.getStatus() == 6) {
        		orderService.cancelOrder(order);
        		obj = renderSuccess("ok");
        	}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 后台退款操作
     *
     * @param resource
     * @return
     */
    @RequestMapping("/refund")
    @ResponseBody
    public Object refund(@Valid String refundNo,Boolean towards,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("操作失败");
        try {
        	WxOrderRefund refund = orderService.selectOrderTransByRefundNo(refundNo);
        	orderService.processOrder(request,refund,towards);
    		obj = renderSuccess("ok");
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 退货退款商品
     *
     * @param resource
     * @return
     */
    @RequestMapping("/refundGoods")
    @ResponseBody
    public Object refundGoods(@Valid PagingDto page,Boolean towards,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	List<WxOrderDetail> detailList = orderService.queryOrderRefundGoodsList(page.getId());
        	List<WxOrderDetail> giftList = orderService.queryOrderRefundGiftList(page.getId());
        	List<DictData> dictList = dictService.queryDictByCodeList(DictUtil.ORDER_REFUND_DESC);
        	map.put("detailList", detailList);
        	map.put("giftList", giftList);
        	map.put("dictList", dictList);
        	obj = renderSuccess(map);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 退货退款地址
     *
     * @param resource
     * @return
     */
    @RequestMapping("/refundAddress")
    @ResponseBody
    public Object refundAddress(@Valid Integer refundId,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	WxAddress address = orderService.queryOrderRefundAddress(refundId);
        	obj = renderSuccess(address);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 退货寄件运单号
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addExpressNo")
    @ResponseBody
    public Object addExpressNo(@Valid WxAddress address,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("编辑失败");
        try {
        	orderService.updateRefundExpressNo(address);
        	obj = renderSuccess("ok");
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 添加退货退款申请
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addRefund")
    @ResponseBody
    public Object addRefund(@Valid WxOrderRefund refund,Boolean towards,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("操作失败");
        try {
        	WxOrder order = orderService.queryOrderById(refund.getOrderId());
        	//退货
        	refund.setStatus(1);
        	refund.setType(2);
        	if(order.getStatus() == 0 || order.getStatus() == 1) {
        		//退款
        		refund.setType(1);
        		refund.setStatus(3);
        	}else {
        		//退货减运费
        		if(order.getAddress() != null && order.getAddress().getFreight() != null) {
        			BigDecimal freight = BigDecimal.valueOf(order.getAddress().getFreight());
            		order.getTrans().setBalances(order.getTrans().getBalances().subtract(freight));
            		if(order.getTrans().getBalances().doubleValue() < 0) {order.getTrans().setBalances(new BigDecimal(0.0));}
        		}
        	}
        	List<WxOrderRefund> refundDetail = new ArrayList<WxOrderRefund>();
        	List<WxOrderRefund> refundGift = new ArrayList<WxOrderRefund>();
        	double goodsPrice = 0.0;
        	String json = request.getParameter("json");
        	String giftJson = request.getParameter("giftJson");
        	JSONArray array = JSONArray.fromObject(json);
        	JSONArray arrayGift = JSONArray.fromObject(giftJson);
    		for(int i=0;i<array.size();i++) {
    			WxOrderRefund detail = new WxOrderRefund();
    			JSONObject goodsJson = array.getJSONObject(i);
    			detail.setGoodsId(goodsJson.getInt("goodsId"));
    			detail.setGoodsNum(goodsJson.getInt("goodsNum"));
    			detail.setSpecId(goodsJson.getInt("specId"));
    			refundDetail.add(detail);
    			goodsPrice += (goodsJson.getDouble("goodsPrice")*goodsJson.getInt("goodsNum"));
    		}
    		for(int i=0;i<arrayGift.size();i++) {
    			WxOrderRefund gift = new WxOrderRefund();
    			JSONObject goodsJson = arrayGift.getJSONObject(i);
    			gift.setGoodsId(goodsJson.getInt("goodsId"));
    			gift.setGoodsNum(goodsJson.getInt("goodsNum"));
    			gift.setActivityId(goodsJson.getInt("activityId"));
    			refundGift.add(gift);
    		}
    		double bl = goodsPrice/order.getTotalBalances();
    		refund.setBalances(order.getTrans().getBalances().multiply(BigDecimal.valueOf(bl)));
    		refund.setRefundNo(order.getOrderNo()+ (int)((Math.random()*9+1)*1000));
    		String title = "";
    		if(order.getActivityId() != null && order.getActivityId() > 0) {
    			if(order.getOrderType() == 1) {
    				title = "秒杀";
    			}else if(order.getOrderType() == 2) {
    				title = "拼团";
    			}else if(order.getOrderType() == 3) {
    				title = "特价";
    			}else if(order.getOrderType() == 5) {
    				title = "砍价";
    			}
    		}else {
    			refund.setRefundName(title+"商品退款产生记录");
    		}
    		refund.setDetails(refundDetail);
    		refund.setGifts(refundGift);;
    		order.setRefund(refund);
    		refund.setStoreId(order.getStoreId());
    		orderService.processOrder(request,refund,towards);
    		obj = renderSuccess("操作成功");
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    /**
     * 添加订单二维码
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addQrCode")
    @ResponseBody
    public Object addQrCode(@Valid WxOrder order,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	orderService.updateOrderQrCode(order);
        	obj = renderSuccess("ok");
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询退款明细
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryRefundDetail")
    @ResponseBody
    public Object queryRefundDetail(@Valid String refundNo,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxOrderRefund refund = orderService.selectOrderTransByRefundNo(refundNo);
        	if(refund != null) {
        		WxAddress address = orderService.queryOrderRefundAddress(refund.getId());
        		refund.setAddress(address);
        	}
        	obj = renderSuccess(refund);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
}
