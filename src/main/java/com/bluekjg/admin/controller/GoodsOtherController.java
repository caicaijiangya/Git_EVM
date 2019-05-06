package com.bluekjg.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.GoodsClassify;
import com.bluekjg.admin.service.IGoodsOtherService;
import com.bluekjg.admin.service.IGoodsService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * 商品管理 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2018-09-25
 */
@Controller
@RequestMapping("/goodsOther")
public class GoodsOtherController extends BaseController {

    @Autowired private IGoodsOtherService goodsOtherService;
    @Autowired private IGoodsService goodsService;
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Goods g, String q, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	g.setDataParam(q);
    	goodsOtherService.selectCombogridDataGrid(pageInfo,g);
        return pageInfo;
    }
    
    @PostMapping("/storeDataGrid")
    @ResponseBody
    public PageInfo storeDataGrid(Goods g, String q, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	g.setDataParam(q);
    	goodsOtherService.selectStoreGodsDataGrid(pageInfo,g);
        return pageInfo;
    }
    
    @PostMapping("/storeGrid")
    @ResponseBody
    public PageInfo storeGrid(Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	goodsOtherService.selectStoreDataGrid(pageInfo);
        return pageInfo;
    }
    
    @GetMapping("/managerClassify")
    public String managerClassify(Model model) {
        return "admin/goods/classifyList";
    }
    
    @PostMapping("/dataGridClassify")
    @ResponseBody
    public PageInfo dataGridClassify(Goods g, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	goodsOtherService.selectClassifyDataGrid(pageInfo);
        return pageInfo;
    }
    @PostMapping("/goodsClassifyGrid")
    @ResponseBody
    public PageInfo goodsClassifyGrid(Goods g, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	goodsOtherService.goodsClassifyGrid(pageInfo);
        return pageInfo;
    }
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addClassifyPage")
    public String addClassifyPage(Model model) {
        return "admin/goods/classifyAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/addClassify")
    @ResponseBody
    public Object addClassify(@Valid GoodsClassify g) {
    	Object obj = renderError("操作失败");
    	try {
    		goodsOtherService.insertClassify(g);
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
    @GetMapping("/editClassifyPage")
    public String editClassifyPage(Model model,Integer id) {
    	GoodsClassify obj = goodsOtherService.selectClassifyObj(id);
    	model.addAttribute("obj",obj);
        return "admin/goods/classifyEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/editClassify")
    @ResponseBody
    public Object editClassify(@Valid GoodsClassify g) {
    	Object obj = renderError("操作失败");
    	try {
    		goodsOtherService.updateClassify(g);
    		obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 删除
     * @param 
     * @return
     */
    @PostMapping("/deleteClassify")
    @ResponseBody
    public Object deleteClassify(@Valid GoodsClassify g) {
    	Object obj = renderError("操作失败");
    	try {
    		goodsOtherService.deleteClassify(g.getId());
    		obj = renderSuccess("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    
    @GetMapping("/managerBrand")
    public String managerBrand(Model model) {
        return "admin/goods/brandList";
    }
    
    @PostMapping("/dataGridBrand")
    @ResponseBody
    public PageInfo dataGridBrand(Goods g, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	goodsOtherService.selectBrandDataGrid(pageInfo);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addBrandPage")
    public String addBrandPage(Model model) {
        return "admin/goods/brandAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/addBrand")
    @ResponseBody
    public Object addBrand(@Valid GoodsClassify g) {
    	Object obj = renderError("操作失败");
    	try {
    		goodsOtherService.insertBrand(g);
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
    @GetMapping("/editBrandPage")
    public String editBrandPage(Model model,Integer id) {
    	GoodsClassify obj = goodsOtherService.selectBrandObj(id);
    	model.addAttribute("obj",obj);
        return "admin/goods/brandEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/editBrand")
    @ResponseBody
    public Object editBrand(@Valid GoodsClassify g) {
    	Object obj = renderError("操作失败");
    	try {
    		goodsOtherService.updateBrand(g);
    		obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 删除
     * @param 
     * @return
     */
    @PostMapping("/deleteBrand")
    @ResponseBody
    public Object deleteBrand(@Valid GoodsClassify g) {
    	Object obj = renderError("操作失败");
    	try {
    		goodsOtherService.deleteBrand(g.getId());
    		obj = renderSuccess("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    @GetMapping("/managerHot")
    public String managerHot(Model model) {
        return "admin/goods/hotList";
    }
    
    @PostMapping("/dataGridHot")
    @ResponseBody
    public PageInfo dataGridHot(Goods g, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	goodsOtherService.selectHotDataGrid(pageInfo,g);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addHotPage")
    public String addHotPage(Model model) {
    	model.addAttribute("brands", goodsService.selectBrandList());
        return "admin/goods/hotAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/addHot")
    @ResponseBody
    public Object addHot(@Valid GoodsClassify g) {
    	Object obj = renderError("操作失败");
    	try {
    		goodsOtherService.insertHot(g);
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
    @GetMapping("/editHotPage")
    public String editHotPage(Model model,Integer id) {
    	GoodsClassify obj = goodsOtherService.selectHotObj(id);
    	model.addAttribute("obj",obj);
        return "admin/goods/hotEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/editHot")
    @ResponseBody
    public Object editHot(@Valid GoodsClassify g) {
    	Object obj = renderError("操作失败");
    	try {
    		goodsOtherService.updateHot(g);
    		obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 删除
     * @param 
     * @return
     */
    @PostMapping("/deleteHot")
    @ResponseBody
    public Object deleteHot(@Valid GoodsClassify g) {
    	Object obj = renderError("操作失败");
    	try {
    		goodsOtherService.deleteHot(g.getId());
    		obj = renderSuccess("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    /**
     * 根据规格ID查询商品ID
     * @param 
     * @return
     */
    @PostMapping("/queryGoodsId")
    @ResponseBody
    public Object queryGoodsId(@Valid Integer id) {
    	return goodsOtherService.queryGoodsIdBySpecId(id);
    }
}
