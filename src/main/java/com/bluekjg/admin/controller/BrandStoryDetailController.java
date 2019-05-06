package com.bluekjg.admin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluekjg.admin.model.BrandStory;
import com.bluekjg.admin.model.BrandStoryDetail;
import com.bluekjg.admin.service.IBrandStoryDetailService;
import com.bluekjg.admin.service.IBrandStoryService;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.result.Result;

/**
 * <p>
 *   前端控制器
 * </p>
 * @author Tim
 * @since 2018-04-13
 */
@Controller
@RequestMapping("/brandStoryDetail")
public class BrandStoryDetailController  {
    
    @Autowired 
    private IBrandStoryDetailService brandStoryDetailService;
    
    @Autowired 
    private IBrandStoryService brandStoryService;
    
    @GetMapping("/manager")
    public String manager(Integer brandStoryId,HttpServletRequest request) {
    	BrandStory storyInfo =  brandStoryService.selectById(brandStoryId);
    	request.setAttribute("storyInfo", storyInfo);
        return "admin/brand/brandStoryDetail";
    }


    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(BrandStoryDetail brandStoryDetail, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	brandStoryDetailService.queryBrandStoryDetailList(pageInfo, brandStoryDetail);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Integer brandStoryId,HttpServletRequest request) {
    	request.setAttribute("brandStoryId", brandStoryId);
        return "admin/brand/brandStoryDetailAdd";
    }

    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(BrandStoryDetail brandStoryDetail) {
    	brandStoryDetail.setCreatedTime(new Date());
    	brandStoryDetail.setLastModifiedTime(new Date());
        boolean b = brandStoryDetailService.insert(brandStoryDetail);
        if (b) {
            return renderSuccess("添加成功！");
        } else {
            return renderError("添加失败！");
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
        BrandStoryDetail brandStoryDetail = new BrandStoryDetail();
        brandStoryDetail.setId(id);
        brandStoryDetail.setLastModifiedTime(new Date());
        brandStoryDetail.setIsDel(1);
        boolean b = brandStoryDetailService.updateById(brandStoryDetail);
        if (b) {
            return renderSuccess("删除成功！");
        } else {
            return renderError("删除失败！");
        }
    }

    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, Long id) {
        BrandStoryDetail brandStoryDetail = brandStoryDetailService.selectById(id);
        model.addAttribute("tXyBrandStoryDetail", brandStoryDetail);
        return "admin/brand/brandStoryDetailEdit";
    }

    /**
     * 编辑
     * @param 
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(BrandStoryDetail brandStoryDetail) {
    	brandStoryDetail.setLastModifiedTime(new Date());
        boolean b = brandStoryDetailService.updateById(brandStoryDetail);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    /**
     * ajax成功
     * @param msg 消息
     * @return {Object}
     */
    public Object renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }
    

    /**
     * ajax失败
     * @param msg 失败的消息
     * @return {Object}
     */
    public Object renderError(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }
    
    
}
