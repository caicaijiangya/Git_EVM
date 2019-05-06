package com.bluekjg.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.Store;
import com.bluekjg.admin.model.UserIssueCoupon;
import com.bluekjg.admin.model.WxUserInfo;
import com.bluekjg.admin.service.IStoreService;
import com.bluekjg.admin.service.IUserIssueCouponService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.wxapp.service.IWxCouponService;

/**
 * <p>
 * 优惠券人工发券 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2019-02-27
 */
@Controller
@RequestMapping("/userIssueCoupon")
public class UserIssueCouponController extends BaseController {

    @Autowired 
    private IUserIssueCouponService userIssueCouponService;
    @Autowired 
    private IStoreService storeService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/userIssueCoupon/userIssueCouponList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(UserIssueCoupon userIssueCoupon, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	userIssueCouponService.selectDataGrid(pageInfo,userIssueCoupon);
        return pageInfo;
    }
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Model model) {
    	List<Store> storeList = storeService.queryStoreList();
    	model.addAttribute("storeList", storeList);
        return "admin/userIssueCoupon/userIssueCouponAdd";
    }
    
    @PostMapping("/userDataGrid")
    @ResponseBody
    public PageInfo userDataGrid(WxUserInfo userInfo, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	userIssueCouponService.selectUserDataGrid(pageInfo,userInfo);
        return pageInfo;
    }
    
    /**
     * 发放优惠券
     * @param 
     * @return
     */
    @PostMapping("/submit")
    @ResponseBody
    public Object submit(@Valid WxUserInfo userInfo,Integer userNum,Integer couponId,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("添加失败！");
    	List<String> openIdList = userIssueCouponService.selectUserOpenId(userInfo);
    	Long userId = getUserId();
    	userIssueCouponService.insertUserIssueCoupon(openIdList,userNum,couponId,userId);
    	obj = renderSuccess("添加成功！");
    	return obj;
    }
}
