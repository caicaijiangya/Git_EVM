package com.bluekjg.wxapp.controller;

import java.net.URLDecoder;
import java.util.Date;
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
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.utils.ConstantUtils;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.core.commons.utils.StringUtils;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxCoupon;
import com.bluekjg.wxapp.model.WxGoods;
import com.bluekjg.wxapp.model.WxIntegralActivity;
import com.bluekjg.wxapp.model.WxIntegralLog;
import com.bluekjg.wxapp.model.wx.DataModel;
import com.bluekjg.wxapp.service.IWxCouponService;
import com.bluekjg.wxapp.service.IWxErpService;
import com.bluekjg.wxapp.service.IWxGoodsService;
import com.bluekjg.wxapp.service.IWxUserService;
import com.bluekjg.wxapp.utils.CommonUtil;

/**
 * @description：用户信息
 * @author：pincui.tom
 * @date：2018/3/27 14:51
 */
@Controller
@RequestMapping("/xcx/user")
public class WxUserController extends BaseController {
	@Autowired
	private IWxUserService userService;
	@Autowired
	private IWxGoodsService goodsService;
	@Autowired
	private IWxCouponService couponService;
	@Autowired
	private IWxErpService erpService;
	//获取登录用户信息
		@RequestMapping("/queryUserInfo")
		@ResponseBody
		public String queryUserInfo(DataModel dataModel) {
			logger.info("进入获取登录用户信息");
			String res = "";
			if(StringUtils.isEmpty(dataModel.getOpenId())){
				res = "{\"status\":"+ConstantUtils.FAIL+",\"msg\":\"" + ConstantUtils.NO_PARAM + "\"}";
				return res;
			}
			res = userService.queryUserInfo(dataModel);
			logger.info("离开获取登录用户信息");
			return res;
		}
	
	/**
     * 验证用户
     *
     * @param resource
     * @return
     */
    @RequestMapping("/verify")
    @ResponseBody
    public Object verify(@Valid UserBean user) {
    	Object obj = renderError("The request failed");
        try {
        	UserBean bean = userService.selectByOpenId(user.getOpenId());
        	if(bean != null) {
        		obj = renderSuccess(bean);
        		if(bean.getUserType() == null || bean.getUserType() == 0){
        			logger.error("用户验证verify-->openId==>"+user.getOpenId()+"-->userType==>"+bean.getUserType());
        		}
        	}else{
        		logger.error("用户验证verify-->openId==>"+user.getOpenId());
        	}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }

    /**
     * 用户注册
     *
     * @param resource
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public Object register(@Valid UserBean user,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	if(user != null && user.getNickName() != null) {
        		user.setUserName(CommonUtil.filterEmoji(URLDecoder.decode(user.getNickName(), "UTF-8")));
        		user.setNickName(CommonUtil.filterEmoji(URLDecoder.decode(user.getNickName(), "UTF-8")));
        	}
        	if(user != null && user.getHeadImgUrl() != null) {
        		user.setHeadImgUrl(URLDecoder.decode(user.getHeadImgUrl(), "UTF-8"));
        	}
        	user.setMoney(0.0);
        	user.setIntegral(0);
        	//user.setCode(user.getOpenId());
        	userService.insertUserInfo(user);
        	//新用户注册活动赠送优惠券
        	//绑定
        	WxCoupon coupon = couponService.queryCouponActivityById(null, 1,0);
        	if(coupon != null) {
        		Integer userNum = couponService.queryUserCouponActivityCount(user.getOpenId(),coupon.getId());
            	Integer userDay = couponService.queryUserCouponActivityDayCount(user.getOpenId(),coupon.getId());
        		if(coupon.getCouponNum() > 0 || coupon.getIsCouponNum() == 0) {
        			if(userNum < coupon.getUserNum() && userDay < coupon.getUserDayNum()) {
	        			couponService.insertUserCoupon(user.getOpenId(), coupon.getCouponId(), 4, coupon.getId());
	        			if(coupon.getIsCouponNum() == 1) {
	    	        		//活动优惠券库存-1
	    	        		couponService.updateCouponActivityCouponNum(coupon.getId());
	    	        	}
        			}
        		}
        	}
        	//erpService.pushVipErp(user.getOpenId());
        	obj = renderSuccess(user);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 设置手机号
     *
     * @param resource
     * @return
     */
    @RequestMapping("/editUser")
    @ResponseBody
    public Object editUser(@Valid UserBean user,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	if(user != null && user.getNickName() != null) {
    			user.setUserName(CommonUtil.filterEmoji(URLDecoder.decode(user.getNickName(), "UTF-8")));
        		user.setNickName(CommonUtil.filterEmoji(URLDecoder.decode(user.getNickName(), "UTF-8")));
        	}
        	if(user != null && user.getHeadImgUrl() != null) {
        		user.setHeadImgUrl(URLDecoder.decode(user.getHeadImgUrl(), "UTF-8"));
        	}
        	user.setIsAuth(1);
    		userService.updateUserInfo(user);
    		userService.insertIntegralLog(user.getOpenId(),null,2,0);
    		//新用户注册活动赠送优惠券
        	//授权
    		WxCoupon coupon = couponService.queryCouponActivityById(null, 1,1);
        	if(coupon != null) {
        		Integer userNum = couponService.queryUserCouponActivityCount(user.getOpenId(),coupon.getId());
            	Integer userDay = couponService.queryUserCouponActivityDayCount(user.getOpenId(),coupon.getId());
        		if(coupon.getCouponNum() > 0 || coupon.getIsCouponNum() == 0) {
        			if(userNum < coupon.getUserNum() && userDay < coupon.getUserDayNum()) {
	        			couponService.insertUserCoupon(user.getOpenId(), coupon.getCouponId(), 4, coupon.getId());
	        			if(coupon.getIsCouponNum() == 1) {
	    	        		//活动优惠券库存-1
	    	        		couponService.updateCouponActivityCouponNum(coupon.getId());
	    	        	}
        			}
        		}
        	}
    		UserBean bean = userService.selectByOpenId(user.getOpenId());
    		obj = renderSuccess(bean);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 设置手机号
     *
     * @param resource
     * @return
     */
    @RequestMapping("/phoneNumber")
    @ResponseBody
    public Object phoneNumber(@Valid UserBean user,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	boolean bool = userService.updateUserInfo(user);
    		if(bool) {
    			obj = renderSuccess(user.getMobileNo());
    		}
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 查询用户信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Object getUserInfo(@Valid UserBean user,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	UserBean bean = userService.selectByOpenId(user.getOpenId());
        	if(bean != null) {
        		obj = renderSuccess(bean);
        	}
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 查询所有店员信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/getAssistantnList")
    @ResponseBody
    public Object getAssistantnList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<UserBean> list = userService.getAssistantnList(page);
        	obj = renderSuccess(list);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    /**
     * 删除店员信息-修改店员ISDEL状态
     *
     * @param resource
     * @return
     */
    @RequestMapping("/deleteAssistantn")
    @ResponseBody
    public Object deleteAssistantn(@Valid UserBean user,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	userService.deleteAssistantn(user);
        	obj = renderSuccess("ok");
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 修改个人信息---pjf
     *
     * @param resource
     * @return
     */
    @RequestMapping("/editUserInfo")
    @ResponseBody
    public Object editUserInfo(@Valid UserBean user,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Integer b = userService.editUserInfo(user);
        	//新用户注册活动赠送优惠券
        	//完善资料
        	WxCoupon coupon = couponService.queryCouponActivityById(null, 1,2);
        	if(coupon != null) {
        		Integer userNum = couponService.queryUserCouponActivityCount(user.getOpenId(),coupon.getId());
            	Integer userDay = couponService.queryUserCouponActivityDayCount(user.getOpenId(),coupon.getId());
        		if(coupon.getCouponNum() > 0 || coupon.getIsCouponNum() == 0) {
        			if(userNum < coupon.getUserNum() && userDay < coupon.getUserDayNum()) {
	        			couponService.insertUserCoupon(user.getOpenId(), coupon.getCouponId(), 4, coupon.getId());
	        			if(coupon.getIsCouponNum() == 1) {
	    	        		//活动优惠券库存-1
	    	        		couponService.updateCouponActivityCouponNum(coupon.getId());
	    	        	}
        			}
        		}
        	}
        	PagingDto page = new PagingDto();
        	page.setOpenId(user.getOpenId());
        	page.setType(3);
        	Integer count = userService.selectUserIntegralLogCount(page);
        	if(b > 0 && count == 0) {
        		userService.insertIntegralLog(user.getOpenId(),null,3,0);
        	}
        	UserBean userBean= userService.selectByOpenId(page.getOpenId());
        	if(userBean.getStoreId() == null) {userBean.setStoreId(0);}
        	//分享绑定积分
        	WxIntegralActivity integralActivity = userService.selectIntegralActivity(2);
			if(integralActivity != null && userBean.getInviteOpenId() != null && userBean.getInviteOpenId().length() > 0) {
        		if(integralActivity.getStoreList() != null && integralActivity.getStoreList().size() > 0) {
        			boolean bool = false;
        			for(Integer storeId:integralActivity.getStoreList()) {
        				if(storeId.intValue() == userBean.getStoreId().intValue()) {
        					bool = true;
        				}
        			}
        			if(bool) {userService.insertIntegralLog(userBean.getInviteOpenId(),null,6,integralActivity.getIntegral());}
        		}
				
			}
        	obj = renderSuccess(b);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    /**
     * 查询我的积分明细
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryIntegralLog")
    @ResponseBody
    public Object queryIntegralLog(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxIntegralLog> list = userService.selectIntegralLog(page);
        	obj = renderSuccess(list);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    /**
     * 查询我的收藏
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryCollect")
    @ResponseBody
    public Object queryCollect(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxGoods> list = goodsService.selectCollectGoods(page);
        	obj = renderSuccess(list);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 查询我的优惠券数量
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryCouponCount")
    @ResponseBody
    public Object queryCouponCount(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Integer count = userService.selectUserCouponCount(page);
        	obj = renderSuccess(count);
        	
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 查询我的签到记录
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryMyCheckin")
    @ResponseBody
    public Object queryMyCheckin(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	List<WxIntegralLog> list = userService.selectMyCheckin(page);
        	map.put("list", list);
        	map.put("date", DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
        	map.put("dateView", DateUtil.parseDateToStr(new Date(), "yyyy年MM月"));
        	obj = renderSuccess(map);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 签到
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addCheckin")
    @ResponseBody
    public Object addCheckin(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
    		Integer integral = userService.insertIntegralLog(page.getOpenId(),null,0,0);
    		obj = renderSuccess(integral);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 分享回调
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addShare")
    @ResponseBody
    public Object addShare(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	//新用户邀请人
        	int isNew = userService.selectUserIsNew(page.getScanOpenId());
        	if(isNew > 0) {
        		userService.updateUserInvityOpenId(page.getScanOpenId(), page.getOpenId());
        	}
        	//分享积分
    		Integer integral = userService.insertIntegralLog(page.getOpenId(),page.getScanOpenId(),5,0);
    		obj = renderSuccess(integral);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 更新用户所属门店
     *
     * @param resource
     * @return
     */
    @RequestMapping("/editUserStore")
    @ResponseBody
    public Object editUserStore(@Valid UserBean user,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	int isNew = userService.selectUserIsNew(user.getOpenId());
        	if(isNew > 0) {
        		userService.updateUserStore(user);
        	}
    		obj = renderSuccess("ok");
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
}
