package com.bluekjg.wxapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.bluekjg.wxapp.model.wxApp20.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.AccessLog;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxActivityGoods;
import com.bluekjg.wxapp.model.WxCollageGoods;
import com.bluekjg.wxapp.model.WxGoods;
import com.bluekjg.wxapp.service.IWxAccessLogService;
import com.bluekjg.wxapp.service.IWxBannerService;
import com.bluekjg.wxapp.service.IWxBargainService;
import com.bluekjg.wxapp.service.IWxGoodsService;
import com.bluekjg.wxapp.service.IWxUserService;

@Controller
@RequestMapping("/xcx/goods")
public class WxGoodsController extends BaseController{
	@Autowired
	private IWxGoodsService goodsService;
	@Autowired
	private IWxBannerService bannerService;
	@Autowired
	private IWxUserService userService;
	@Autowired
	private IWxBargainService bargainService;
	@Autowired
	private IWxAccessLogService accessLogService;
	
	/**
     * 查询商品详情
     *
     * @param resource
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Object detail(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Integer isCollect = 0;
        	isCollect = goodsService.queryCollect(page.getOpenId(), String.valueOf(page.getId()));
			if(isCollect > 1) {isCollect = 0;}
        	Map<String,Object> map = new HashMap<String,Object>();
        	WxGoods goods = goodsService.queryGoodsDetail(page);
        	AccessLog goodsAccess = new AccessLog();
        	if(goods != null && goods.getSpecs() != null && goods.getSpecs().size()>0) {
        		goodsAccess.setOpenId(page.getOpenId());
        		goodsAccess.setGoodsId(page.getId());
        		goodsAccess.setSpecId(goods.getSpecs().get(0).getId());
        		accessLogService.insertGoodsAccessLog(goodsAccess);
        	}
        	UserBean userInfo = userService.selectByOpenId(page.getOpenId());
        	map.put("goods", goods);
        	map.put("userInfo", userInfo);
        	map.put("isCollect", isCollect);
        	obj = renderSuccess(map);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 查询商品活动
     *
     * @param resource
     * @return
     */
    @RequestMapping("/activity")
    @ResponseBody
    public Object activity(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
    	Map<String,Object> map = new HashMap<String,Object>();
    	WxActivityGoods activity = goodsService.queryActivityGoods(null, page.getSpecId(),null);
		try {
			if(activity == null) {
				activity = goodsService.queryActivityGoods(null, page.getId(),1);
				if(activity != null) {
					List<String> labelList = goodsService.queryActivityLabelList(activity.getId());
					map.put("labelList", labelList);
					//满减满赠， 不显示活动
					if(activity.getActivityType() == 5 || activity.getActivityType() == 6 || activity.getActivityType() == 7) {
						activity = null;
	        		}
				}
				
			}
			if(activity != null) {
				if(activity.getActivityType().intValue() == 4) {
					//砍价活动处理活动库存
					PagingDto page1 = new PagingDto();
					page1.setActivityId(activity.getId());
					page1.setStoreId(activity.getStoreId());
					page1.setGoodsId(page.getSpecId());
					Integer amount = bargainService.queryBargainGoodsAmountCount(page1);
					activity.setActivityAmount(activity.getActivityAmount() - amount);
					if(activity.getActivityAmount() < 0) {activity.setActivityAmount(0);}
				}
				activity.setActivityTime(goodsService.activityDate(activity.getActivityStartTime(),activity.getActivityEndTime()));
        		Integer bigType = 0;
        		if(activity.getActivityType() == 1) {
        			bigType = 2;
        		}else if(activity.getActivityType() == 2) {
        			bigType = 4;
        		}else if(activity.getActivityType() == 3) {
        			bigType = 3;
        		}else if(activity.getActivityType() == 4) {
        			bigType = 9;
        		}else if(activity.getActivityType() == 5) {
        			bigType = 10;
        		}else if(activity.getActivityType() == 6) {
        			bigType = 11;
        		}else if(activity.getActivityType() == 7) {
        			bigType = 12;
        		}
        		map.put("advertImgs", bannerService.queryFiles(page.getSpecId(), 0, bigType, 1));
    			map.put("detailImgs", bannerService.queryFiles(page.getSpecId(), 0, bigType, 2));
			}
			map.put("activity", activity);
			obj = renderSuccess(map);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return obj;
    }
    /**
     * 查询拼团列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/collageList")
    @ResponseBody
    public Object collageList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxCollageGoods> list = goodsService.queryCollageGoodsList(page.getActivityId(),page.getGoodsId());
        	if(list != null && list.size() > 0) {
        		for(int i=0;i<list.size();i++) {
        			list.get(i).setTime(goodsService.activityDate(list.get(i).getStartTime(),list.get(i).getEndTime()));
        		}
        	}
        	obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 加入购物车
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addShoppingcart")
    @ResponseBody
    public Object addShoppingcart(@Valid WxGoods goods,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Integer count = goodsService.queryShoppingcart(goods.getOpenId(), String.valueOf(goods.getId()),String.valueOf(goods.getSpecId()));
        	if(count == 0) {
        		goodsService.insertShoppingcart(goods);
        	}else {
        		goodsService.updateShoppingcart(goods);
        	}
        	obj = renderSuccess("ok");
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }

	/**
	 * 加入购物车（批量）
	 *
	 */
	@RequestMapping("/addShoppingcarts")
	@ResponseBody
	public Object addShoppingcarts(@Valid ShoppingCart shoppingCart, HttpServletRequest request, HttpServletResponse response) {
		Object obj = renderError("The request failed");
		try {
			for (WxGoods goods : shoppingCart.getGoodsList()) {
				Integer count = goodsService.queryShoppingcart(goods.getOpenId(), String.valueOf(goods.getId()),String.valueOf(goods.getSpecId()));
				if(count == 0) {
					goodsService.insertShoppingcart(goods);
				}else {
					goodsService.updateShoppingcart(goods);
				}
			}
			obj = renderSuccess("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
    /**
     * 删除购物车
     *
     * @param resource
     * @return
     */
    @RequestMapping("/delShoppingcart")
    @ResponseBody
    public Object delShoppingcart(@Valid Integer id,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	goodsService.deleteShoppingcart(id);
        	obj = renderSuccess("ok");
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 添加收藏
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addCollect")
    @ResponseBody
    public Object addCollect(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
    	Integer isCollect = goodsService.queryCollect(page.getOpenId(), String.valueOf(page.getGoodsId()));
        try {
        	if(isCollect == 0) {
        		goodsService.insertCollect(page.getOpenId(), String.valueOf(page.getGoodsId()));
        		isCollect = 1;
        	}else {
        		goodsService.deleteCollect(page.getOpenId(), String.valueOf(page.getGoodsId()));
        		isCollect = 0;
        	}
        	obj = renderSuccess(isCollect);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询我的购物车商品
     *
     * @param resource
     * @return
     */
    @RequestMapping("/myCartGoods")
    @ResponseBody
    public Object myCartGoods(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxGoods> list = goodsService.queryMyCartGoodsList(page);
        	obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 修改购物车商品数量
     *
     * @param resource
     * @return
     */
    @RequestMapping("/updateShoppingcartNum")
    @ResponseBody
    public Object updateShoppingcartNum(@Valid WxGoods goods,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	goodsService.updateShoppingcartNum(goods);
        	obj = renderSuccess("ok");
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
}
