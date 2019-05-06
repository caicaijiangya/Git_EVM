package com.bluekjg.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.ActivityPromoteDto;
import com.bluekjg.admin.service.IActivityPromoteService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * 活动推广 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2019-03-04
 */
@Controller
@RequestMapping("/activityPromote")
public class ActivityPromoteController extends BaseController {

    @Autowired 
    private IActivityPromoteService activityPromoteService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/activityPromote/list";
    }
    
    @PostMapping("/activityDataGrid")
    @ResponseBody
    public PageInfo activityDataGrid(ActivityPromoteDto activity, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	if(activity.getType() == null) { activity.setType(0); }
    	if(activity.getType() == 0) {
    		activityPromoteService.selectCouponDataGrid(pageInfo,activity);
    	}else {
    		activityPromoteService.selectActivityDataGrid(pageInfo, activity);
    	}
    	
        return pageInfo;
    }
    
}
