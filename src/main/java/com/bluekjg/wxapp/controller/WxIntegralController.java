package com.bluekjg.wxapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxAddress;
import com.bluekjg.wxapp.model.WxCoupon;
import com.bluekjg.wxapp.model.WxIntegralGoods;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderAddress;
import com.bluekjg.wxapp.model.WxOrderDetail;
import com.bluekjg.wxapp.service.IWxAddressService;
import com.bluekjg.wxapp.service.IWxIntegralService;
import com.bluekjg.wxapp.service.IWxOrderService;
import com.bluekjg.wxapp.service.IWxUserService;

@Controller
@RequestMapping("/xcx/integral")
public class WxIntegralController extends BaseController{
	@Autowired
	private IWxIntegralService integralService;
	@Autowired
	private IWxAddressService addressService;
	@Autowired
	private IWxOrderService orderService;
	@Autowired
	private IWxUserService userService;
	/**
     * 查询积分商品列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/goodsList")
    @ResponseBody
    public Object goodsList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	if(page.getType() == 0) {
        		List<WxIntegralGoods> list = integralService.queryGoodsList(page);
        		obj = renderSuccess(list);
        	}else if(page.getType() == 1) {
        		List<WxCoupon> list = integralService.queryCouponList(page);
        		obj = renderSuccess(list);
        	}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
	 /**
     * 查询我的积分
     *
     * @param resource
     * @return
     */
    @RequestMapping("/integral")
    @ResponseBody
    public Object integral(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Integer integral = integralService.queryUserIntegral(page.getOpenId());
        	obj = renderSuccess(integral);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
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
        	WxIntegralGoods goods = integralService.queryGoodsDetail(page);
        	obj = renderSuccess(goods);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 查询优惠券详情
     *
     * @param resource
     * @return
     */
    @RequestMapping("/coupon")
    @ResponseBody
    public Object coupon(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxCoupon goods = integralService.queryCouponDetail(page);
        	obj = renderSuccess(goods);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 兑换优惠券
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addCoupon")
    @ResponseBody
    public Object addCoupon(@Valid WxCoupon coupon,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	/*
        	 * 验证是否黑名单用户
        	 */
        	UserBean user = userService.queryBlacklist(coupon.getOpenId());
        	if(user != null) {
    			obj = renderError("不允许购买");
    			return obj;
    		}
        	/*
    		 * 验证用户积分
    		 */
    		Integer integral = integralService.queryUserIntegral(coupon.getOpenId());
    		if(integral < coupon.getGoodsPrice()) {
    			obj = renderError("积分不足");
				return obj;
    		}
    		/*
    		 * 验证商品库存
    		 */
    		WxIntegralGoods integralGoods = integralService.queryGoodsAmount(coupon.getOpenId(),coupon.getId().toString(), "1");
    		if(integralGoods == null) {
    			obj = renderError("商品已下架");
				return obj;
    		}
    		if(integralGoods.getGoodsAmount() <= 0) {
    			obj = renderError("库存不足");
				return obj;
    		}
    		if(integralGoods.getBuyNum() != 0 && integralGoods.getBuyNum() <= integralGoods.getCouponNum()) {
    			obj = renderError("超出限购");
				return obj;
    		}
    		coupon.setStatus(0);
    		integralService.addCoupon(coupon);
        	obj = renderSuccess("ok");
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
    public Object addOrder(@Valid WxIntegralGoods goods,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("订单错误");
        try {
        	/*
        	 * 验证是否黑名单用户
        	 */
        	UserBean user = userService.queryBlacklist(goods.getOpenId());
        	if(user != null) {
    			obj = renderError("不允许购买");
    			return obj;
    		}
        	WxOrderDetail detail = new WxOrderDetail();
        	detail.setGoodsId(goods.getId());
        	detail.setSpecId(goods.getSpecId());
        	detail.setGoodsPrice(goods.getGoodsPrice());
        	detail.setGoodsSpec("");
        	detail.setGoodsNum(1);
        	detail.setGoodsImages(goods.getGoodsImages());
    		/*
    		 * 验证收货地址
    		 */
    		WxOrderAddress orderAddress = null;
    		if(goods.getAddressId() != null && goods.getAddressId() > 0) {
    			WxAddress address = addressService.queryAddressById(goods.getAddressId());
    			if(address != null) {
    				orderAddress = new WxOrderAddress();
    				orderAddress.setFreight(goods.getFreight());
    				orderAddress.setProvince(address.getProvince());
    				orderAddress.setCity(address.getCity());
    				orderAddress.setArea(address.getArea());
    				orderAddress.setAddressDetail(address.getAddressDetail());
    				orderAddress.setMobileNo(address.getMobileNo());
    				orderAddress.setName(address.getName());
    			} else {
    				obj = renderError("收货地址错误");
    				return obj;
    			}
    		}
    		/*
    		 * 验证用户积分
    		 */
    		Integer integral = integralService.queryUserIntegral(goods.getOpenId());
    		if(integral < goods.getGoodsPrice()) {
    			obj = renderError("积分不足");
				return obj;
    		}
    		/*
    		 * 验证商品库存
    		 */
    		WxIntegralGoods integralGoods = integralService.queryGoodsAmount(goods.getOpenId(),goods.getSpecId().toString(), "0");
    		if(integralGoods == null) {
    			obj = renderError("商品已下架");
				return obj;
    		}
    		if(integralGoods.getGoodsAmount() <= 0) {
    			obj = renderError("库存不足");
				return obj;
    		}
    		if(integralGoods.getBuyNum() != 0 && integralGoods.getBuyNum() <= integralGoods.getOrderNum()) {
    			obj = renderError("超出限购");
				return obj;
    		}
    		WxOrder order =new WxOrder();
    		order.setOpenId(goods.getOpenId());
    		order.setOrderDesc(goods.getOrderDesc());
    		order.setTotalBalances(goods.getGoodsPrice());
    		order.setPayMoneyStyle(0);
    		order.setTakeStyle(0);
    		order.setOrderType(4);//积分订单
    		//生成订单号
			order.setOrderNo(orderService.createOrderNo());
			order.setStatus(0);
			order.setStoreId(0);
			order.setActivityId(0);
			order.setDisountPrice(0.0);
			order.setCouponId(0);
			integralService.insertOrder(order, detail, orderAddress);
			obj = renderSuccess(order.getId());
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
}
