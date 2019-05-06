package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluekjg.admin.model.Area;
import com.bluekjg.admin.model.StoreArea;
import com.bluekjg.admin.service.IAreaService;
import com.bluekjg.admin.service.IStoreAreaService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * 门店地市管理表 前端控制器
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
@Controller
@RequestMapping("/storeArea")
public class StoreAreaController extends BaseController {

@Autowired private IStoreAreaService storeAreaService;
@Autowired private IAreaService areaService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/storeArea/storeAreaList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(StoreArea storeArea, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        storeAreaService.selectDataGrid(pageInfo,storeArea);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(HttpServletRequest request,HttpServletResponse response) {
    	List<Area> provinceList = areaService.queryProvinceList();
    	request.setAttribute("provinceList", provinceList);
    	return "admin/storeArea/storeAreaAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid StoreArea storeArea) {
        storeArea.setLastModifiedTime(new Date());
        boolean b = storeAreaService.insert(storeArea);
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
        StoreArea storeArea = new StoreArea();
        storeArea.setId(id);
        storeArea.setLastModifiedTime(new Date());
        storeArea.setIsDel(1);
        boolean b = storeAreaService.updateById(storeArea);
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
    public String editPage(Model model, Integer id,Integer provinceId,HttpServletRequest request,HttpServletResponse response) {
    	List<Area> cityList = areaService.queryCityList(provinceId);
    	request.setAttribute("cityList", cityList);
        StoreArea storeArea = storeAreaService.selectById(id);
        model.addAttribute("storeArea", storeArea);
        model.addAttribute("provinceId", provinceId);
        return "admin/storeArea/storeAreaEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid StoreArea storeArea) {
        storeArea.setLastModifiedTime(new Date());
        boolean b = storeAreaService.updateById(storeArea);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
  //查询市
    @PostMapping("/queryCityList")
    @ResponseBody
    public List<Area> queryCityList(Integer provinceId){
    	return areaService.queryCityList(provinceId);
    } 
}
