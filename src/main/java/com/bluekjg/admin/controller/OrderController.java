package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluekjg.admin.model.Order;
import com.bluekjg.admin.model.OrderAddress;
import com.bluekjg.admin.model.execl.ExcelBean;
import com.bluekjg.admin.service.IOrderAddressService;
import com.bluekjg.admin.service.IOrderService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.ConvertBean;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderRefund;
import com.bluekjg.wxapp.service.IWxOrderService;

import net.sf.json.JSONArray;

/**
 * <p>
 * 订单主表 前端控制器
 * </p>
 *
 * @author Tim
 * @since 2018-09-18
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired private IOrderService orderService;
    @Autowired private IOrderAddressService orderAddressService;
    @Autowired private IWxOrderService wxOrderService;
    
    @GetMapping("/manager")
    public String manager(Model model) {
    	try {
			model.addAttribute("EXCEL_ORDER_MAP", URLEncoder.encode(JSONArray.fromObject(ExcelBean.EXCEL_ORDER_MAP).toString(),"utf-8"));
			model.addAttribute("EXCEL_ORDER_DETAIL_MAP", URLEncoder.encode(JSONArray.fromObject(ExcelBean.EXCEL_ORDER_DETAIL_MAP).toString(),"utf-8"));
			model.addAttribute("EXCEL_GUANYI_MAP", URLEncoder.encode(JSONArray.fromObject(ExcelBean.EXCEL_GUANYI_MAP).toString(),"utf-8"));
			model.addAttribute("EXCEL_JINDIE_MAP", URLEncoder.encode(JSONArray.fromObject(ExcelBean.EXCEL_JINDIE_MAP).toString(),"utf-8"));
    	} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return "admin/order/orderList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Order o, Integer page, Integer rows, String sort,String order) {
    	if(o.getSelectedStoreId() != null && o.getSelectedStoreId().length() > 0) {
    		o.setSelectedStoreIds(o.getSelectedStoreId().split(","));
    	}
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        orderService.selectDataGrid(pageInfo,o);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/order/orderAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Order order) {
        order.setLastModifiedTime(new Date());
        boolean b = orderService.insert(order);
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
        Order order = new Order();
        order.setId(id);
        order.setLastModifiedTime(new Date());
        order.setIsDel(1);
        boolean b = orderService.updateById(order);
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
        Order order = orderService.selectById(id);
        model.addAttribute("order", order);
        return "admin/order/orderEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Order order) {
        order.setLastModifiedTime(new Date());
        boolean b = orderService.updateById(order);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    /**
   	 * 从订单列表页跳转订单详情
   	 * @param
   	 * @return
   	 */
   	@GetMapping("/toOrderDetail")
   	public String toOrderDetail(Model model,Integer orderId) {
   		WxOrder order = wxOrderService.queryOrderById(orderId);
   		model.addAttribute("order", order);
   		return "admin/order/tLtOrderDetail";
   	}
    
    /**
     * 导出订单
     * @param 
     * @return
     */
    @RequestMapping("/downLoadOrder")
    @ResponseBody
    public Object downLoadOrder(@Valid Order order, HttpServletResponse response) {
    	if(order.getSelectedStoreId() != null && order.getSelectedStoreId().length() > 0) {
    		order.setSelectedStoreIds(order.getSelectedStoreId().split(","));
    	}
    	String orderNo = order.getOrderNo();
    	order.setOrderNo(ConvertBean.twoTimesDecode(orderNo));
        orderService.downLoadOrder(order, response);
        return renderSuccess("导出成功");
    }
    /**
     * 导出订单明细
     * @param 
     * @return
     */
    @RequestMapping("/downLoadOrderDetail")
    @ResponseBody
    public Object downLoadOrderDetail(@Valid Order order, HttpServletResponse response) {
    	if(order.getSelectedStoreId() != null && order.getSelectedStoreId().length() > 0) {
    		order.setSelectedStoreIds(order.getSelectedStoreId().split(","));
    	}
    	String orderNo = order.getOrderNo();
    	order.setOrderNo(ConvertBean.twoTimesDecode(orderNo));
        orderService.downLoadOrderDetail(order, response);
        return renderSuccess("导出成功");
    }
    
    /**
     * 导出订单赠品
     * @param 
     * @return
     */
    @RequestMapping("/downLoadOrderGift")
    @ResponseBody
    public Object downLoadOrderGift(@Valid Order order, HttpServletResponse response) {
    	String orderNo = order.getOrderNo();
    	order.setOrderNo(ConvertBean.twoTimesDecode(orderNo));
        orderService.downLoadOrderGift(order, response);
        return renderSuccess("导出成功");
    }
    
    /**
   	 * 订单统计页面--按天统计
   	 * @param
   	 * @return
   	 */
   	@GetMapping("/orderCount")
   	public String orderCount() {
   		return "admin/order/dayOrderCountList";
   	}
   	
   	@PostMapping("/countDataGrid")
    @ResponseBody
    public PageInfo orderCountDataGrid(Order od, Integer page, Integer rows, String sort,String order) {
   		if(od.getSelectedStoreId() != null && od.getSelectedStoreId().length() > 0) {
   			od.setSelectedStoreIds(od.getSelectedStoreId().split(","));
    	} 
   		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	 orderService.queryOrderCount(pageInfo,od);
         return pageInfo;
    }
   	
   	/**
     * 批量导出每日订单统计
     * @param 
     * @return
     */
    @RequestMapping("/exportDayOrderExcelList")
    @ResponseBody
    public Object exportDayOrderExcelList(Order order, HttpServletResponse response) {
    	if(order.getSelectedStoreId() != null && order.getSelectedStoreId().length() > 0) {
    		order.setSelectedStoreIds(order.getSelectedStoreId().split(","));
    	}
    	if(order.getStoreName() != null) {
    		String storeName = order.getStoreName();
        	order.setStoreName(ConvertBean.twoTimesDecode(storeName));
    	}
    	orderService.exportDayOrderExcelList(order, response);
        return renderSuccess("导出成功");
    }
    
    /**
   	 * 订单统计页面--按月统计
   	 * @param
   	 * @return
   	 */
   	@GetMapping("/orderSum")
   	public String orderSum() {
   		return "admin/order/monthOrderCountList";
   	}
   	
   	@PostMapping("/sumDataGrid")
    @ResponseBody
    public PageInfo queryOrderCountByMonth(Order od, Integer page, Integer rows, String sort,String order) {
   		if(od.getSelectedStoreId() != null && od.getSelectedStoreId().length() > 0) {
   			od.setSelectedStoreIds(od.getSelectedStoreId().split(","));
    	}
   		 PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	 orderService.queryOrderCountByMonth(pageInfo,od);
         return pageInfo;
    }
   	
   	/**
     * 批量导出每月订单统计
     * @param 
     * @return
     */
    @RequestMapping("/exportMonthOrderExcelList")
    @ResponseBody
    public Object exportMonthOrderExcelList(Order order, HttpServletResponse response) {
    	if(order.getSelectedStoreId() != null && order.getSelectedStoreId().length() > 0) {
    		order.setSelectedStoreIds(order.getSelectedStoreId().split(","));
    	}
    	if(order.getStoreName() != null) {
    		String storeName = order.getStoreName();
        	order.setStoreName(ConvertBean.twoTimesDecode(storeName));
    	}
    	orderService.exportMonthOrderExcelList(order, response);
        return renderSuccess("导出成功");
    }
    
    
    /**
     * 添加物流单号页面
     * @return
     */
    @GetMapping("/logisticsPage")
    public String logisticsPage(Model model,Integer orderId) {
    	model.addAttribute("orderId", orderId);
        return "admin/order/expresaAdd";
    }
    
    /**
     * 添加物流单号
     * @param 
     * @return
     */
    @PostMapping("/logisticsAdd")
    @ResponseBody
    public Object logisticsAdd(@Valid OrderAddress orderAddress) {
        orderAddress.setLastModifiedTime(new Date());
        Integer b = orderAddressService.insertExpress(orderAddress);
        if (b>0) {
        	orderService.editStatus(orderAddress);
            return renderSuccess("添加成功！");
        } else {
            return renderError("添加失败！");
        }
    } 
    
    
    /**
     * 订单处理
     *
     * @param resource
     * @return
     */
    @RequestMapping("/refund")
    @ResponseBody
    public Object refund(String refundNo,Boolean towards,String note,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("操作失败");
        try {
        	WxOrderRefund refund = wxOrderService.selectOrderTransByRefundNo(refundNo);
        	if(refund != null) {
        		refund.setNote(note);
        		wxOrderService.processOrder(request,refund,towards);
        		obj = renderSuccess("ok");
        	}
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
}
