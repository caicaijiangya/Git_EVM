package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.Address;
import com.bluekjg.admin.model.OrderRefund;
import com.bluekjg.admin.service.IOrderRefundService;
import com.bluekjg.admin.service.IOrderService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.ConvertBean;
import com.bluekjg.wxapp.model.WxOrderRefund;
import com.bluekjg.wxapp.service.IWxOrderService;

import net.sf.json.JSONObject;

/**
 * <p>
 * 订单退款表 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2019-01-14
 */
@Controller
@RequestMapping("/orderRefund")
public class OrderRefundController extends BaseController {

    @Autowired private IOrderService orderService;
    @Autowired private IOrderRefundService orderRefundService;
    @Autowired private IWxOrderService wxOrderService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/orderRefund/orderRefundList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(OrderRefund of, Integer page, Integer rows, String sort,String order) {
    	if(of.getStoreId() != null && of.getStoreId().length() > 0) {
    		of.setStoreIds(of.getStoreId().split(","));
    	}
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	orderRefundService.selectDataGrid(pageInfo,of);
        return pageInfo;
    }
    /**
   	 * 退货详情
   	 * @param
   	 * @return
   	 */
   	@GetMapping("/detail")
   	public String detail(Model model,String refundNo) {
   		WxOrderRefund refund = wxOrderService.selectOrderTransByRefundNo(refundNo);
   		model.addAttribute("refund", refund);
   		return "admin/orderRefund/orderRefundDetail";
   	}
   	
   	/**
   	 * 退货地址
   	 * @param
   	 * @return
   	 */
   	@GetMapping("/address")
   	public String address(Model model,Integer refundId) {
   		Address address = orderRefundService.selectRefundAddress(refundId);
   		JSONObject json = JSONObject.fromObject(address);
   		model.addAttribute("address", json);
   		model.addAttribute("refundId",refundId);
   		return "admin/orderRefund/orderRefundAddress";
   	}
   	/**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Address address) {
    	Object obj = renderError("添加失败！");
    	try {
    		orderRefundService.insertRefundAddress(address);
    		obj = renderSuccess("添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
   	/**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/addAddress")
    @ResponseBody
    public Object addAddress(@Valid Address address) {
    	Object obj = renderError("添加失败！");
    	try {
    		orderRefundService.insertAdminAddress(address);
    		obj = renderSuccess("添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
   	/**
   	 * 查询退货地址
   	 * @param
   	 * @return
   	 */
   	@PostMapping("/addressDataGrid")
    @ResponseBody
    public PageInfo addressDataGrid(Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	orderRefundService.selectAddressDataGrid(pageInfo);
        return pageInfo;
    }
   	/**
   	 * 查询地市
   	 * @param
   	 * @return
   	 */
   	@PostMapping("/areaDataGrid")
    @ResponseBody
    public PageInfo areaDataGrid(Integer id, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	orderRefundService.selectAreaDataGrid(pageInfo,id);
        return pageInfo;
    }
   	
   	
   	/**
     * 导出订单
     * @param 
     * @return
     */
    @RequestMapping("/downLoadOrderRefund")
    @ResponseBody
    public Object downLoadOrderRefund(@Valid OrderRefund of, HttpServletResponse response) {
    	if(of.getStoreId() != null && of.getStoreId().length() > 0) {
    		of.setStoreIds(of.getStoreId().split(","));
    	}
    	if(of.getUserName() != null) {
    		of.setUserName(ConvertBean.twoTimesDecode(of.getUserName()));
    	}
    	if(of.getOrderNo() != null) {
    		of.setOrderNo(ConvertBean.twoTimesDecode(of.getOrderNo()));
    	}
    	orderRefundService.downLoadOrderRefund(of, response);
        return renderSuccess("导出成功");
    }
}
