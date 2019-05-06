package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletRequest;
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

import com.bluekjg.admin.model.QuestDimension;
import com.bluekjg.admin.model.QuestProblem;
import com.bluekjg.admin.service.IQuestDimensionService;
import com.bluekjg.admin.service.IQuestProblemService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * 模块管理表 前端控制器
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
@Controller
@RequestMapping("/questProblem")
public class QuestProblemController extends BaseController {

@Autowired private IQuestProblemService questProblemService;
@Autowired private IQuestDimensionService questDimensionService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/questProblem/questProblemList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(QuestProblem questProblem, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        questProblemService.selectDataGrid(pageInfo,questProblem);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(HttpServletRequest request) {
    	List<QuestDimension> questDimensionList = questDimensionService.selectDimensionList();
        request.setAttribute("questDimensionList", questDimensionList);
        return "admin/questProblem/questProblemAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid QuestProblem questProblem) {
    	questProblem.setLastModifiedTime(new Date());
        boolean b = questProblemService.insert(questProblem);
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
        QuestProblem questProblem = new QuestProblem();
        questProblem.setId(id);
        questProblem.setLastModifiedTime(new Date());
        questProblem.setIsDel(1);
        boolean b = questProblemService.updateById(questProblem);
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
    public String editPage(Model model, Integer id,HttpServletRequest request) {
    	List<QuestDimension> questDimensionList = questDimensionService.selectDimensionList();
        request.setAttribute("questDimensionList", questDimensionList);
        QuestProblem questProblem = questProblemService.selectById(id);
        model.addAttribute("questProblem", questProblem);
        return "admin/questProblem/questProblemEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid QuestProblem questProblem) {
        questProblem.setLastModifiedTime(new Date());
        boolean b = questProblemService.updateById(questProblem);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    
    //查看答案页面
    @GetMapping("/answer")
    public String answer(Model model,Integer id) {
    	model.addAttribute("id", id);
        return "admin/questProblem/answerList";
    }
    
    @PostMapping("/answerData")
    @ResponseBody
    public PageInfo answerData(QuestProblem questProblem, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        questProblemService.selectAnswerList(pageInfo,questProblem);
        return pageInfo;
    }
    
    /**
     * 删除答案
     * @param id
     * @return
     */
    @PostMapping("/deleteAnswer")
    @ResponseBody
    public Object deleteAnswer(Integer id) {
        QuestProblem questProblem = new QuestProblem();
        questProblem.setId(id);
        questProblem.setLastModifiedTime(new Date());
        questProblem.setIsDel(1);
        Integer b = questProblemService.updateAnsewrById(questProblem);
        if (b>0) {
            return renderSuccess("删除成功！");
        } else {
            return renderError("删除失败！");
        }
    }
    
    /**
     * 添加答案页面
     * @return
     */
    @GetMapping("/addAnswerPage")
    public String addAnswerPage(Model model,Integer problemId) {
    	model.addAttribute("problemId", problemId);
        return "admin/questProblem/answerAdd";
    }
    
    /**
     * 添加答案
     * @param 
     * @return
     */
    @PostMapping("/addAnswer")
    @ResponseBody
    public Object addAnswer(@Valid QuestProblem questProblem) {
    	questProblem.setLastModifiedTime(new Date());
        Integer b = questProblemService.insertAnswer(questProblem);
        if (b>0) {
            return renderSuccess("添加成功！");
        } else {
            return renderError("添加失败！");
        }
    }
    
    /**
     * 编辑答案页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editAnswerPage")
    public String editAnswerPage(Model model, Integer id) {
        QuestProblem questProblem = questProblemService.selectAnswerById(id);
        model.addAttribute("questProblem", questProblem);
        return "admin/questProblem/answerEdit";
    }
    
    /**
     * 编辑答案
     * @param 
     * @return
     */
    @PostMapping("/editAnswer")
    @ResponseBody
    public Object editAnswer(@Valid QuestProblem questProblem) {
        questProblem.setLastModifiedTime(new Date());
        Integer b = questProblemService.updateAnswerById(questProblem);
        if (b>0) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
