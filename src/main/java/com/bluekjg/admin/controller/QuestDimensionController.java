package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.GoodsClassify;
import com.bluekjg.admin.model.QuestDimension;
import com.bluekjg.admin.model.QuestGoods;
import com.bluekjg.admin.service.IGoodsOtherService;
import com.bluekjg.admin.service.IQuestDimensionService;
import com.bluekjg.admin.service.IQuestGoodsService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * 肌肤检测维度表 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2018-11-7
 */
@Controller
@RequestMapping("/questDimension")
public class QuestDimensionController extends BaseController {

	@Autowired private IQuestGoodsService questGoodsService;
	@Autowired private IGoodsOtherService goodsOtherService;
	@Autowired private IQuestDimensionService questDimensionService;
    @GetMapping("/manager")
    public String manager() {
        return "admin/questDimension/questDimensionList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(QuestDimension questDimension, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	questDimensionService.selectDataGrid(pageInfo,questDimension);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(HttpServletRequest request) {
    	List<GoodsClassify> classifyList = goodsOtherService.queryAllClassify();
    	request.setAttribute("classifyList", classifyList);
        return "admin/questDimension/questDimensionAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid QuestDimension questDimension) {
    	questDimension.setLastModifiedTime(new Date());
        boolean b = questDimensionService.insert(questDimension);
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
        boolean b = questDimensionService.deleteById(id);
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
    public String editPage(Model model,Integer id,HttpServletRequest request) {
    	List<GoodsClassify> classifyList = goodsOtherService.queryAllClassify();
    	request.setAttribute("classifyList", classifyList);
    	QuestDimension questDimension = questDimensionService.selectById(id);
        model.addAttribute("questDimension", questDimension);
        return "admin/questDimension/questDimensionEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid QuestDimension questDimension) {
    	questDimension.setLastModifiedTime(new Date());
        boolean b = questDimensionService.updateById(questDimension);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
