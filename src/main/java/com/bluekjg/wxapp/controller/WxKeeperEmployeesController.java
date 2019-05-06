package com.bluekjg.wxapp.controller;

import java.net.URLDecoder;

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
import com.bluekjg.wxapp.model.wx.UserInfo;
import com.bluekjg.wxapp.service.IWxKeeperEmployeesService;
import com.bluekjg.wxapp.service.IWxUserService;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

/**
 * @description：员工控制层
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
@Controller
@RequestMapping("/xcx/employees")
public class WxKeeperEmployeesController extends BaseController {
	@Autowired
	private IWxKeeperEmployeesService employeesService;
	@Autowired
	private IWxUserService wxUserBeanService;
	
    
    
	/**
     * 查询用户二维码信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryQrcode")
    @ResponseBody
    public Object queryQrcode(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	String qrCode = employeesService.queryQrcode(page);
        	obj = renderSuccess(qrCode);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 新增用户二维码信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/insertQrcode")
    @ResponseBody
    public Object insertQrcode(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	employeesService.insertQrcode(page);
        	obj = renderSuccess("ok");
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
   
    /**
     * 新增用户信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/insertUser")
    @ResponseBody
    public Object insertUser(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	UserInfo userInfo = null;
        	//添加员工
        	employeesService.insertUserEmp(page);
        	userInfo = new UserInfo();
        	userInfo.setStoreId(page.getStoreId());
       		userInfo.setUserType(4);
       		userInfo.setOpenId(page.getScanOpenId());
        	obj = renderSuccess(userInfo);
       		
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 更新用户信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public Object updateUser(@Valid UserBean user,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	if(user.getNickName() != null && user.getNickName().length() > 0) {
        		user.setNickName(URLDecoder.decode(user.getNickName(), WxappConfigUtil.WX_CODING_FORMAT));
        	}
        	boolean result = employeesService.updateUser(user);
        	if(result) {
        		obj = renderSuccess("ok");
        	}
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 更新用户信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/editUserType")
    @ResponseBody
    public Object editUserType(@Valid UserBean user,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Integer b = employeesService.editUserType(user);
        	obj = renderSuccess(b);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
}
}

