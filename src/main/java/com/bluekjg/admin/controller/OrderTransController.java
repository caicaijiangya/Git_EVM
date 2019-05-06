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

import com.bluekjg.admin.model.OrderTrans;
import com.bluekjg.admin.service.IOrderTransService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * 交易记录管理表 前端控制器
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
@Controller
@RequestMapping("/orderTrans")
public class OrderTransController extends BaseController {

@Autowired private IOrderTransService orderTransService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/orderTrans/orderTransList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(OrderTrans orderTrans, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	orderTransService.selectDataGrid(pageInfo,orderTrans);
        return pageInfo;
    }
    
   
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
        OrderTrans orderTrans = new OrderTrans();
        orderTrans.setId(id);
        orderTrans.setLastModifiedTime(new Date());
        orderTrans.setIsDel(1);
        boolean b = orderTransService.updateById(orderTrans);
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
    public String editPage(Model model, Long id) {
    	OrderTrans orderTrans = orderTransService.selectById(id);
        model.addAttribute("orderTrans", orderTrans);
        return "admin/orderTrans/orderTransEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid OrderTrans orderTrans) {
    	orderTrans.setLastModifiedTime(new Date());
        boolean b = orderTransService.updateById(orderTrans);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
