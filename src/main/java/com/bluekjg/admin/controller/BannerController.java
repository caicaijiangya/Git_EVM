package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluekjg.admin.model.Banner;
import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.admin.service.IBannerService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * banner管理表 前端控制器
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
@Controller
@RequestMapping("/banner")
public class BannerController extends BaseController {

@Autowired private IBannerService bannerService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/banner/bannerList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Banner banner, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        bannerService.selectDataGrid(pageInfo,banner);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/banner/bannerAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Banner banner) {
        banner.setLastModifiedTime(new Date());
        boolean b = bannerService.insertBanner(banner);
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
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
        Banner banner = new Banner();
        banner.setId(id);
        banner.setLastModifiedTime(new Date());
        banner.setIsDel(1);
        boolean b = bannerService.updateById(banner);
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
    @GetMapping("/editPage")
    public String editPage(Model model, Integer id,HttpServletRequest request, HttpServletResponse response) {
        Banner banner = bannerService.selectById(id);
        model.addAttribute("banner", banner);
        //处理banner图
    	List<StaticFiles> sfList = bannerService.queryImgList(id);
    	List<String> imageList = new ArrayList<String>();
    	String imageArr = "";
    	String imageSeq = "";
    	if(sfList.size()>0){
        	for(StaticFiles sf : sfList){
        		imageList.add(sf.getFilePath());
        		imageArr+=","+sf.getFilePath();
        		imageSeq+=","+sf.getSeq();
        	}
        }
        if(imageArr.length()>0){
        	imageArr = imageArr.substring(1, imageArr.length());
        	imageSeq = imageSeq.substring(1, imageSeq.length());
        }
        request.setAttribute("imageSeq", imageSeq);
        request.setAttribute("imageArr", imageArr);
        request.setAttribute("imageList", imageList);
        return "admin/banner/bannerEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Banner banner) {
        banner.setLastModifiedTime(new Date());
        try {
        	bannerService.updateById(banner);
        	bannerService.modifyImgInfo(banner);
        	return renderSuccess("编辑成功！");
		} catch (Exception e) {
			return renderError("编辑失败！");
		}
    }
    
    //查看宣传广告图片
    @GetMapping("/bannerImg")
    public String bannerImg(Model model,Integer id) {
    	model.addAttribute("id", id);	//banner  id
        return "admin/banner/bannerImgList";
    }
    
    @PostMapping("/searchBannerImg")
    @ResponseBody
    public PageInfo searchBannerImg(Banner banner, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        bannerService.searchBannerImg(pageInfo,banner);
        return pageInfo;
    }
}
