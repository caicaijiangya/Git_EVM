package com.bluekjg.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluekjg.admin.model.BrandStory;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.service.IBrandStoryService;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.result.Result;
import com.bluekjg.core.utils.StringUtil;

/**
 * <p>
 *   前端控制器
 * </p>
 * @author Tim
 * @since 2018-04-12
 */
@Controller
@RequestMapping("/brandStory")
public class BrandStoryController {
    
    @Autowired 
    private IBrandStoryService brandStoryService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/brand/brandStory";
    }


    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(BrandStory brandStory, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	brandStoryService.queryBrandStoryList(pageInfo, brandStory);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(HttpServletRequest request) {
    	//获取要跳转的商品
    	List<Goods> goodsList = brandStoryService.queryGoodsList();
    	request.setAttribute("goodsList", goodsList);
        return "admin/brand/brandStoryAdd";
    }

    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(BrandStory brandStory) {
    	brandStory.setCreatedTime(new Date());
    	brandStory.setLastModifiedTime(new Date());
        List<String> brandDescList = brandStory.getStoreDesc();
        if(brandDescList!=null && brandDescList.size()>0){
        	String brandDesc = StringUtil.listConvertToString(brandDescList);
        	brandStory.setBrandDesc(brandDesc);
        }
        boolean b = brandStoryService.insert(brandStory);
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
        BrandStory brandStory = new BrandStory();
        brandStory.setId(id);
        brandStory.setLastModifiedTime(new Date());
        brandStory.setIsDel(1);
        boolean b = brandStoryService.updateById(brandStory);
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
    public String editPage(Model model, Long id,HttpServletRequest request) {
    	//获取要跳转的商品
    	List<Goods> goodsList = brandStoryService.queryGoodsList();
    	request.setAttribute("goodsList", goodsList);
        BrandStory tXyBrandStory = brandStoryService.selectById(id);
        String brandDesc = tXyBrandStory.getBrandDesc();
        if(!StringUtil.isEmpty(brandDesc)){
        	List<String> listDesc =  StringUtil.stringConvertList(brandDesc,"~");
        	request.setAttribute("listDesc", listDesc);
        }else{
        	request.setAttribute("listDesc", new ArrayList<String>());
        }
        model.addAttribute("tXyBrandStory", tXyBrandStory);
        return "admin/brand/brandStoryEdit";
    }

    /**
     * 编辑
     * @param 
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(BrandStory brandStory) {
    	brandStory.setLastModifiedTime(new Date());
        List<String> brandDescList = brandStory.getStoreDesc();
        if(brandDescList!=null && brandDescList.size()>0){
        	String brandDesc = StringUtil.listConvertToString(brandDescList);
        	brandStory.setBrandDesc(brandDesc);
        }
        boolean b = brandStoryService.updateById(brandStory);
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
