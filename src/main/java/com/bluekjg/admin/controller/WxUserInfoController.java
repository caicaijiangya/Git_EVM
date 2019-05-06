package com.bluekjg.admin.controller;

import java.util.Date;
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

import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.ConvertBean;
import com.bluekjg.redis.key.RedisKey;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.IndexModel;
import com.bluekjg.admin.model.Store;
import com.bluekjg.admin.model.WxUserInfo;
import com.bluekjg.admin.service.IStoreService;
import com.bluekjg.admin.service.IWxUserInfoService;
import com.bluekjg.core.commons.base.BaseController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Max
 * @since 2018-03-30
 */
@Controller
@RequestMapping("/wxUserInfo")
public class WxUserInfoController extends BaseController {

    @Autowired 
    private IWxUserInfoService userInfoService;
    @Autowired 
    private IStoreService storeService;
    
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/wxuser/wxUserList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(WxUserInfo userInfo, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	userInfoService.queryUserInfoList(pageInfo, userInfo);
        return pageInfo;
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        WxUserInfo wxUser = new WxUserInfo();
        wxUser.setId(id);
        wxUser.setIsDel(1);
        wxUser.setLastModifiedTime(new Date());
        boolean b = userInfoService.updateById(wxUser);
        if (b) {
            return renderSuccess("删除成功");
        } else {
            return renderError("删除失败");
        }
    }
   
    /**
     * 编辑
     * @param userInfo
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Long id,HttpServletRequest request,HttpServletResponse response) {
    	List<Store> storeList = storeService.queryStoreList();
    	request.setAttribute("storeList", storeList);
        WxUserInfo wxUserInfo = userInfoService.selectById(id);
        model.addAttribute("wxUserInfo", wxUserInfo);
        return "admin/wxuser/wxUserEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid WxUserInfo wxUserInfo,HttpServletRequest request,HttpServletResponse response) {
        wxUserInfo.setLastModifiedTime(new Date());
        boolean b = userInfoService.updateById(wxUserInfo);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    /**
     * 添加权限页面
     * @return
     */
    @GetMapping("/addAuth")
    public String addAuth(Model model,Integer id,String openId) {
    	model.addAttribute("id", id); //用户ID
    	model.addAttribute("openId", openId); //用户openId
        return "admin/wxuser/authList";
    }
    
    @PostMapping("/dataGridAdd")
    @ResponseBody
    public PageInfo dataGridAdd(WxUserInfo wxUserInfo, Integer page, Integer rows){
    	PageInfo pageInfo = new PageInfo(page,rows);
    	userInfoService.queryModelList(pageInfo,wxUserInfo);
    	return pageInfo;
    }
    
    /**
     * 确认赋予权限
     * @param 
     * @return
     */
    @PostMapping("/appoint")
    @ResponseBody
    public Object appoint(IndexModel indexModel){
    	boolean b = userInfoService.addModel(indexModel);
    	 if (b) {
             return renderSuccess("指派成功");
         } else {
             return renderError("指派失败");
         }
    }
    
    /**
     * 查看已赋予的权限页面
     * @return
     */
    @GetMapping("/queryAuth")
    public String queryAuth(Model model,Integer id,String openId) {
    	model.addAttribute("id", id); //用户ID
    	model.addAttribute("openId", openId); //用户openId
        return "admin/wxuser/queryAuthList";
    }
    
    @PostMapping("/dataGridDel")
    @ResponseBody
    public PageInfo dataGridDel(WxUserInfo wxUserInfo, Integer page, Integer rows){
    	PageInfo pageInfo = new PageInfo(page,rows);
    	userInfoService.queryModelUserList(pageInfo,wxUserInfo);
    	return pageInfo;
    }
    
    /**
     * 批量取消权限
     * @param id
     * @return
     */
    @PostMapping("/deleteList")
    @ResponseBody
    public Object deleteList(IndexModel indexModel) {
        boolean b = userInfoService.deleteByIds(indexModel);
        if (b) {
            return renderSuccess("取消成功！");
        } else {
            return renderError("取消失败！");
        }
    }
    
    
    /**
     * 导出用户
     * @param 
     * @return
     */
    @RequestMapping("/downLoadUserInfo")
    @ResponseBody
    public Object downLoadGoods(@Valid WxUserInfo wxUserInfo, HttpServletResponse response) {
    	wxUserInfo.setNickName(ConvertBean.twoTimesDecode(wxUserInfo.getNickName()));
    	userInfoService.downLoadUserInfo(wxUserInfo, response);
        return renderSuccess("导出成功");
    }
    
    
    
    /**
     * 黑名单
     * @return
     */
    @GetMapping("/blacklistManager")
    public String blacklistManager() {
        return "admin/wxuser/wxUserBlacklistList";
    }
    
    @PostMapping("/blacklistDataGrid")
    @ResponseBody
    public PageInfo blacklistDataGrid(WxUserInfo userInfo, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	userInfoService.queryUserBlacklistDataGrid(pageInfo, userInfo);
        return pageInfo;
    }
    
    /**
     * 加入黑名单
     * @param userInfo
     * @return
     */
    @PostMapping("/addBlacklist")
    @ResponseBody
    public Object addBlacklist(WxUserInfo userInfo) {
    	Object obj = renderError("加入失败");
    	try {
    		Integer count = userInfoService.queryUserBlacklistById(userInfo.getOpenId());
    		if(count != null && count > 0) {
    			obj = renderError("已在黑名单中");
    		}else {
    			userInfoService.insertUserBlacklist(userInfo);
        		obj = renderSuccess("加入成功");
    		}
    		
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 取消黑名单
     * @param openId
     * @return
     */
    @PostMapping("/deleteBlacklist")
    @ResponseBody
    public Object deleteBlacklist(String openId) {
    	Object obj = renderError("移除失败");
    	try {
    		userInfoService.deleteUserBlacklist(openId);
    		obj = renderSuccess("移除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
}
