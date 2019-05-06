package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.Activity;
import com.bluekjg.admin.model.IntegralActivity;
import com.bluekjg.admin.service.IIntegralActivityService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * 积分规则表 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2019-01-16
 */
@Controller
@RequestMapping("/integralActivity")
public class IntegralActivityController extends BaseController {

    @Autowired private IIntegralActivityService integralActivityService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/integralActivity/integralActivityList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(IntegralActivity activity, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	integralActivityService.selectDataGrid(pageInfo,activity);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Model model) {
        return "admin/integralActivity/integralActivityAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid IntegralActivity activity,HttpServletRequest request) {
    	Object obj = renderError("添加失败！");
        try {
        	integralActivityService.insertIntegralActivity(activity);
        	obj = renderSuccess("添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
    	Object obj = renderError("删除失败！");
        try {
        	IntegralActivity activity = new IntegralActivity();
        	activity.setId(id);
        	activity.setIsDel(1);
        	integralActivityService.updateById(activity);
        	obj = renderSuccess("删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Integer id) {
    	IntegralActivity activity = integralActivityService.selectIntegralActivityById(id);
    	model.addAttribute("activity", activity);
        return "admin/integralActivity/integralActivityEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid IntegralActivity activity,HttpServletRequest request) {
    	Object obj = renderError("编辑失败！");
        try {
        	integralActivityService.updateIntegralActivity(activity);
        	obj = renderSuccess("编辑成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
}
