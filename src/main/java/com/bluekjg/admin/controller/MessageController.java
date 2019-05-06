package com.bluekjg.admin.controller;

import javax.validation.Valid;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluekjg.admin.model.Message;
import com.bluekjg.admin.service.IMessageService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * 消息通知表 前端控制器
 * </p>
 *
 * @author Tim
 * @since 2018-09-30
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired private IMessageService messageService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/message/messageList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Message message, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        messageService.selectDataGrid(pageInfo,message);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/message/messageAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Message message) {
        message.setLastModifiedTime(new Date());
        boolean b = messageService.insert(message);
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
        Message message = new Message();
        message.setId(id);
        message.setLastModifiedTime(new Date());
        message.setIsDel(1);
        Integer b = messageService.updateMessageById(message);
        if (b>0) {
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
    public String editPage(Model model, Integer id) {
        Message message = messageService.selectById(id);
        model.addAttribute("message", message);
        return "admin/message/messageEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Message message) {
        message.setLastModifiedTime(new Date());
        boolean b = messageService.updateById(message);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    //消息通知详情页
    @GetMapping("/messageDetail")
    public String messageDetail(Model model,Integer id) {
    	model.addAttribute("id", id);
        return "admin/message/messageDetail";
    }
    
    @PostMapping("/queryMessageDetail")
    @ResponseBody
    public PageInfo queryMessageDetail(Message message, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        messageService.queryMessageDetail(pageInfo,message);
        return pageInfo;
    }
}
