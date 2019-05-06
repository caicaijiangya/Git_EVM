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
import com.bluekjg.admin.model.QuestGoods;
import com.bluekjg.admin.service.IGoodsOtherService;
import com.bluekjg.admin.service.IQuestGoodsService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * 推荐商品管理表 前端控制器
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
@Controller
@RequestMapping("/questGoods")
public class QuestGoodsController extends BaseController {

@Autowired private IQuestGoodsService questGoodsService;
@Autowired private IGoodsOtherService goodsOtherService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/questGoods/questGoodsList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(QuestGoods questGoods, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        questGoodsService.selectDataGrid(pageInfo,questGoods);
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
        return "admin/questGoods/questGoodsAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid QuestGoods questGoods) {
        questGoods.setLastModifiedTime(new Date());
        boolean b = questGoodsService.insert(questGoods);
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
        QuestGoods questGoods = new QuestGoods();
        questGoods.setId(id);
        questGoods.setLastModifiedTime(new Date());
        questGoods.setIsDel(1);
        boolean b = questGoodsService.updateById(questGoods);
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
    public String editPage(Model model,QuestGoods questGoods, Integer id,HttpServletRequest request) {
    	List<GoodsClassify> classifyList = goodsOtherService.queryAllClassify();
    	request.setAttribute("classifyList", classifyList);
    	List<Goods> goodsList = questGoodsService.queryGoodsByClassifyId(questGoods);
    	request.setAttribute("goodsList", goodsList);
    	QuestGoods qGoods = questGoodsService.selectById(id);
        model.addAttribute("questGoods", qGoods);
        return "admin/questGoods/questGoodsEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid QuestGoods questGoods) {
        questGoods.setLastModifiedTime(new Date());
        boolean b = questGoodsService.updateById(questGoods);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
  //根据分类查询商品信息
    @PostMapping("/queryGoodsByClassifyId")
    @ResponseBody
    public List<Goods> queryGoodsInfo(QuestGoods questGoods){
    	return questGoodsService.queryGoodsByClassifyId(questGoods);
    }
}
