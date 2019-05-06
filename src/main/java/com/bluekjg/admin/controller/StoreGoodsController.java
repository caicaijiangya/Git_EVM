package com.bluekjg.admin.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.StoreGoods;
import com.bluekjg.admin.service.IGoodsService;
import com.bluekjg.admin.service.IStoreGoodsService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.ConvertBean;

/**
 * <p>
 * 商品门店管理 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2018-09-25
 */
@Controller
@RequestMapping("/storeGoods")
public class StoreGoodsController extends BaseController {

    @Autowired private IStoreGoodsService storeGoodsService;
    @Autowired private IGoodsService goodsService;
    @GetMapping("/manager")
    public String manager(Model model) {
        return "admin/storeGoods/list";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Goods g, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	storeGoodsService.selectDataGrid(pageInfo,g);
        return pageInfo;
    }
    
    /**
     * 分类资源树
     *
     * @return
     */
    @PostMapping(value = "/allTree")
    @ResponseBody
    public Object allTree() {
        return storeGoodsService.selectStoreList();
    }
    /**
     * 查询商品库存
     * @return
     */
    @PostMapping(value = "/amount")
    @ResponseBody
    public Object amount(Integer id) {
        return storeGoodsService.selectGoodsAmount(id);
    }
    /**
     * 查询门店商品库存
     * @return
     */
    @PostMapping(value = "/storeAmount")
    @ResponseBody
    public Object storeAmount(Integer storeId,Integer id) {
        return storeGoodsService.selectStoreGoodsAmount(storeId, id);
    }
    
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Model model,Integer storeId,String storeName) {
    	
    	try {
    		model.addAttribute("storeId", storeId);
			model.addAttribute("storeName", URLDecoder.decode(storeName, "UTF-8"));
			model.addAttribute("brands", goodsService.selectBrandList());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
		}
        return "admin/storeGoods/add";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid StoreGoods g) {
    	Object obj = renderError("操作失败");
    	try {
    		storeGoodsService.insertStoreGoods(g);
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
    	StoreGoods sg = storeGoodsService.selectStoreGoodsObj(id);
    	//Integer amount = storeGoodsService.selectGoodsAmount(sg.getGoodsId());
    	model.addAttribute("obj", sg);
    	//model.addAttribute("fpa", amount);
        return "admin/storeGoods/edit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid StoreGoods g) {
    	Object obj = renderError("操作失败");
    	try {
    		storeGoodsService.updateStoreGoods(g);
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
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(@Valid Integer id) {
    	Object obj = renderError("操作失败");
    	try {
    		storeGoodsService.deleteStoreGoods(id);
    		obj = renderSuccess("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    /**
     * 导出商品
     * @param 
     * @return
     */
    @RequestMapping("/downLoadStoreGoods")
    @ResponseBody
    public Object downLoadStoreGoods(@Valid Goods g, HttpServletResponse response) {
    	g.setGoodsName(ConvertBean.twoTimesDecode(g.getGoodsName()));
    	storeGoodsService.downLoadStoreGoods(g, response);
        return renderSuccess("导出成功");
    }
}
