package com.bluekjg.admin.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluekjg.admin.model.Coupon;
import com.bluekjg.admin.model.PopConfig;
import com.bluekjg.admin.model.StoreGoods;
import com.bluekjg.admin.service.ICouponService;
import com.bluekjg.admin.service.IPopConfigService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * 首页弹窗配置表 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2018-10-24
 */
@Controller
@RequestMapping("/popConfig")
public class PopConfigController extends BaseController {

	@Autowired private IPopConfigService popConfigService;
	@Autowired private ICouponService couponService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/popConfig/list";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(PopConfig bean, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	popConfigService.selectDataGrid(pageInfo,bean);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Model model) {
    	
    	try {
    		List list = couponService.queryAllCoupon(new Coupon());
    		model.addAttribute("couponList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return "admin/popConfig/add";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid PopConfig bean) {
    	Object obj = renderError("操作失败");
    	try {
    		if(bean.getType() == 0) {
    			bean.setCouponId(0);
    		}
    		popConfigService.insertPopConfig(bean);
    		obj = renderSuccess("添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    
    /**
     * 编辑页面
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model,Integer id) {
    	
    	try {
    		PopConfig popConfig = popConfigService.selectPopConfigById(id);
    		List list = couponService.queryAllCoupon(new Coupon());
    		model.addAttribute("couponList", list);
    		model.addAttribute("popConfig", popConfig);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return "admin/popConfig/edit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid PopConfig bean) {
    	Object obj = renderError("操作失败");
    	try {
    		if(bean.getType() == 0) {
    			bean.setCouponId(0);
    		}
    		popConfigService.updatePopConfig(bean);
    		obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    
    /**
     * 变更状态
     * @param 
     * @return
     */
    @PostMapping("/status")
    @ResponseBody
    public Object status(@Valid PopConfig bean) {
    	Object obj = renderError("操作失败");
    	try {
    		popConfigService.updateStatus(bean);
    		obj = renderSuccess("操作成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
}
