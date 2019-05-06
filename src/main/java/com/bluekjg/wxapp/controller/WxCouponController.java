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
import com.bluekjg.wxapp.model.WxCoupon;
import com.bluekjg.wxapp.service.IWxCouponService;
import com.bluekjg.wxapp.service.IWxUserService;

@Controller
@RequestMapping("/xcx/coupon")
public class WxCouponController extends BaseController{
	@Autowired
	private IWxCouponService couponService;
	@Autowired
	private IWxUserService userService;
	
	/**
     * 查询优惠券列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxCoupon> list = couponService.queryCouponList(page);
        	obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
	/**
     * 查询我的优惠券
     *
     * @param resource
     * @return
     */
    @RequestMapping("/myList")
    @ResponseBody
    public Object myList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxCoupon> list = couponService.queryCouponMy(page);
        	obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询活动优惠券
     *
     * @param resource
     * @return
     */
    @RequestMapping("/activityCoupon")
    @ResponseBody
    public Object activityCoupon(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxCoupon coupon = couponService.queryCouponActivityById(page.getId(),null,null);
        	obj = renderSuccess(coupon);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 领取优惠券
     *
     * @param resource
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("领取失败");
        try {
        	if(page.getType() == null) {page.setType(0);}
        	if(page.getSourceId() == null) {page.setSourceId(0);}
        	WxCoupon coupon = couponService.queryCouponById(page.getId());
        	Integer userNum = couponService.queryUserCouponActivityCount(page.getOpenId(),page.getId());
        	Integer userDay = couponService.queryUserCouponActivityDayCount(page.getOpenId(),page.getId());
        	if(coupon != null) {
        		if(coupon.getCouponNum() > 0 || coupon.getIsCouponNum() == 0) {
        			if(userNum < coupon.getUserNum() && userDay < coupon.getUserDayNum()) {
	        			couponService.insertUserCoupon(page.getOpenId(), page.getId(), page.getType(), page.getSourceId());
	        			if(coupon.getIsCouponNum() == 1) {
        	        		//优惠券库存-1
        	        		couponService.updateCouponCouponNum(page.getId());
        	        	}
	        			obj = renderSuccess("领取成功");
	        		}else {
	        			obj = renderError("领取超限");
	        		}
        		}else {
        			obj = renderError("优惠券已领完");
        		}
        	}else {
        		obj = renderError("优惠券已结束");
        	}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 领取活动优惠券
     *
     * @param resource
     * @return
     */
    @RequestMapping("/receiveCoupons")
    @ResponseBody
    public Object receiveCoupons(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("领取失败");
        try {
        	//保存手机号
        	if(page.getMobileNo() != null && page.getMobileNo().length() > 0) {
        		UserBean user = new UserBean();
        		user.setOpenId(page.getOpenId());
        		user.setMobileNo(page.getMobileNo());
        		userService.updateUserInfo(user);
        	}
        	WxCoupon coupon = couponService.queryCouponActivityById(page.getSourceId(),null,null);
        	Integer userNum = couponService.queryUserCouponActivityCount(page.getOpenId(),page.getId());
        	Integer userDay = couponService.queryUserCouponActivityDayCount(page.getOpenId(),page.getId());
        	if(coupon != null) {
        		if(coupon.getCouponNum() == null) {coupon.setCouponNum(0);}
        		if(coupon.getCouponNum() > 0 || coupon.getIsCouponNum() == 0) {
        			if(userNum < coupon.getUserNum() && userDay < coupon.getUserDayNum()) {
        				couponService.insertUserCoupon(page.getOpenId(), page.getId(), page.getType(), page.getSourceId());
        	        	if(coupon.getIsCouponNum() == 1) {
        	        		//活动优惠券库存-1
        	        		couponService.updateCouponActivityCouponNum(page.getSourceId());
        	        	}
        				obj = renderSuccess("领取成功");
        			}else {
        				obj = renderError("领取超出限制");
        			}
        		}else {
        			obj = renderError("优惠券已领完");
        		}
        	}else {
        		obj = renderError("优惠券活动已结束");
        	}
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
}
