package com.bluekjg.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluekjg.admin.model.Area;
import com.bluekjg.admin.model.CityFreight;
import com.bluekjg.admin.model.GoodsClassify;
import com.bluekjg.admin.model.QuestGoods;
import com.bluekjg.admin.service.IAreaService;
import com.bluekjg.admin.service.ICityFreightService;
import com.bluekjg.admin.service.IDictService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * 地区运费管理表 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2018-11-8
 */
@Controller
@RequestMapping("/cityFreight")
public class CityFreightController extends BaseController {

	@Autowired 
	private ICityFreightService cityFreightService;
	@Autowired
	private IAreaService areaService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/cityFreight/cityFreightList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(CityFreight freight, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	cityFreightService.selectDataGrid(pageInfo,freight);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Model model) {
    	List<Area> areaList = areaService.queryProvinceList();
    	model.addAttribute("areaList", areaList);
        return "admin/cityFreight/cityFreightAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid CityFreight freight) {
        boolean b = cityFreightService.insert(freight);
        if (b) {
            return renderSuccess("添加成功！");
        } else {
            return renderError("添加失败！");
        }
    }
    
    
    /**
     * 编辑页面
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model,Integer id) {
    	List<Area> areaList = areaService.queryProvinceList();
    	CityFreight freight = cityFreightService.selectById(id);
    	model.addAttribute("areaList", areaList);
    	model.addAttribute("freight", freight);
        return "admin/cityFreight/cityFreightEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid CityFreight freight) {
        boolean b = cityFreightService.updateById(freight);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    /**
     * 删除
     * @param 
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
        boolean b = cityFreightService.deleteById(id);
        if (b) {
            return renderSuccess("删除成功！");
        } else {
            return renderError("删除失败！");
        }
    }
}
