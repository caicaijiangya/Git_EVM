package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.wxapp.service.IWxUserService;
import com.bluekjg.admin.model.WxUserInfo;
import com.bluekjg.admin.service.IWxUserIntegralService;
import com.bluekjg.core.commons.base.BaseController;

/**
 * <p>
 * 用户积分表 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2019-01-14
 */
@Controller
@RequestMapping("/wxUserIntegral")
public class WxUserIntegralController extends BaseController {

    @Autowired 
    private IWxUserIntegralService userIntegralService;
    @Autowired
    private IWxUserService userService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/wxuser/userIntegralList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(WxUserInfo userInfo, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	userIntegralService.queryUserIntegralList(pageInfo, userInfo);
        return pageInfo;
    }
    /**
     * 积分明细
     * @return
     */
    @GetMapping("/detailManager")
    public String detailManager(Model model,String openId) {
    	model.addAttribute("openId", openId);
        return "admin/wxuser/userIntegralDetail";
    }
    
    @PostMapping("/detailDataGrid")
    @ResponseBody
    public PageInfo detailDataGrid(WxUserInfo userInfo, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	userIntegralService.queryUserIntegralDetailList(pageInfo, userInfo);
        return pageInfo;
    }
    /**
     * 禁用、启用
     * @param id
     * @return
     */
    @PostMapping("/disable")
    @ResponseBody
    public Object disable(WxUserInfo userInfo) {
    	Object obj = renderError("操作失败");
    	try {
    		userIntegralService.updateUserIntegral(userInfo);
    		obj = renderSuccess("操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 增加积分页面
     * @return
     */
    @GetMapping("/addIntegralPage")
    public String addIntegralPage(Model model,String openId,Double integral) {
    	model.addAttribute("openId", openId);
    	model.addAttribute("integral", integral);
        return "admin/wxuser/userIntegralAdd";
    }
    /**
     * 增加积分
     * @param id
     * @return
     */
    @PostMapping("/addIntegral")
    @ResponseBody
    public Object addIntegral(WxUserInfo userInfo) {
    	Object obj = renderError("操作失败");
    	try {
    		userService.insertIntegralLog(userInfo.getOpenId(),userInfo.getNote(),10,userInfo.getIntegral().intValue());
    		obj = renderSuccess("操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 导出积分
     * @param 
     * @return
     */
    @RequestMapping("/downLoadIntegral")
    @ResponseBody
    public Object downLoadIntegral(@Valid WxUserInfo userInfo, HttpServletResponse response) {
    	userIntegralService.downLoadIntegral(userInfo, response);
        return renderSuccess("导出成功");
    }
    
    /**
     * 导出积分明细
     * @param 
     * @return
     */
    @RequestMapping("/downLoadIntegralDetail")
    @ResponseBody
    public Object downLoadIntegralDetail(@Valid WxUserInfo userInfo, HttpServletResponse response) {
    	userIntegralService.downLoadIntegralDetail(userInfo, response);
        return renderSuccess("导出成功");
    }
}
