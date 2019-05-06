package com.bluekjg.wxapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxKeeperIndexModel;
import com.bluekjg.wxapp.model.WxStore;
import com.bluekjg.wxapp.service.IWxKeeperIndexModelService;
import com.bluekjg.wxapp.service.IWxKeeperService;
import com.bluekjg.wxapp.service.IWxStoreService;

/**
 * @description：店主、店长、店员首页
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
@Controller
@RequestMapping("/xcx/keeperindex")
public class WxKeeperIndexController extends BaseController {
	@Autowired
	private IWxStoreService storeService;
	@Autowired
	private IWxKeeperService keeperService;
	@Autowired
	private IWxKeeperIndexModelService indexModelService;
	
	/**
     * 查询门店信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryStore")
    @ResponseBody
    public Object visitDate(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxStore store = storeService.queryStoreByOpenId(page.getOpenId());
        	obj = renderSuccess(store);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 查询所有门店信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryAllStore")
    @ResponseBody
    public Object queryAllStore(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxStore> store = storeService.queryAllStore();
        	obj = renderSuccess(store);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    /**
     * 查询订单量
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryOrderCount")
    @ResponseBody
    public Object queryOrderCount(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	page.setStartDate(page.getStartDate() + " :00:00:00");
        	page.setEndDate(page.getEndDate() + " 23:59:59");
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("pku", keeperService.queryOrderDetailCount(page));//取货量
        	map.put("ord", keeperService.queryOrderDetailPrice(page));//订单总金额
        	map.put("youhui", keeperService.queryDiscountPrice(page));//订单优惠金额
        	map.put("oNum", keeperService.queryOrderCount(page));//订单量
        	obj = renderSuccess(map);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 查询店主/店员首页权限模块信息
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryIndexModel")
    @ResponseBody
    public Object queryIndexModel(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxKeeperIndexModel> modelList = indexModelService.queryIndexModel(page.getOpenId());
        	obj = renderSuccess(modelList);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
}

