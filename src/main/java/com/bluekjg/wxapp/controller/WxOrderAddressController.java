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

import com.bluekjg.admin.model.Area;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxAddress;
import com.bluekjg.wxapp.model.WxArea;
import com.bluekjg.wxapp.model.WxStore;
import com.bluekjg.wxapp.service.IWxAddressService;
import com.bluekjg.wxapp.service.IWxAreaService;
import com.bluekjg.wxapp.service.IWxStoreService;

@Controller
@RequestMapping("/xcx/orderAddress")
public class WxOrderAddressController extends BaseController{
	@Autowired
	private IWxStoreService storeService;
	@Autowired
	private IWxAreaService areaService;
	@Autowired
	private IWxAddressService addressService;
	
	/**
     * 地市查询
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryArea")
    @ResponseBody
    public Object queryArea(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	WxArea area = areaService.queryAreaByNo(page.getCode());
        	List<WxArea> areaList = areaService.queryStoreArea();
        	map.put("area", area);
        	map.put("areaList", areaList);
        	obj = renderSuccess(map);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 门店列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryStore")
    @ResponseBody
    public Object queryStore(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxStore> storeList = storeService.queryStoreByArea(page);
        	obj = renderSuccess(storeList);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    /**
     * 我的收货地址
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryAddressMy")
    @ResponseBody
    public Object queryAddressMy(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxAddress> addressList = addressService.queryAddressMy(page);
        	obj = renderSuccess(addressList);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 我的收货地址------pjf
     *
     * @param resource
     * @return
     */
    @RequestMapping("/selectMyAddress")
    @ResponseBody
    public Object selectMyAddress(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<WxAddress> addressList = addressService.selectMyAddress(page);
        	obj = renderSuccess(addressList);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 删除我的收货地址------pjf
     *
     * @param resource
     * @return
     */
    @RequestMapping("/delMyAddress")
    @ResponseBody
    public Object delMyAddress(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Integer b = addressService.delMyAddress(page);
        	obj = renderSuccess(b);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 获取省份数据------pjf
     *
     * @param resource
     * @return
     */
    @RequestMapping("/getProvincesList")
    @ResponseBody
    public Object getProvincesList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<Area>  proList= addressService.getProvincesList(page);
        	obj = renderSuccess(proList);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 获取地市数据------pjf
     *
     * @param resource
     * @return
     */
    @RequestMapping("/getCitysList")
    @ResponseBody
    public Object getCitysList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<Area>  cityList= addressService.getCitysList(page);
        	obj = renderSuccess(cityList);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 获取区县数据------pjf
     *
     * @param resource
     * @return
     */
    @RequestMapping("/getAreasList")
    @ResponseBody
    public Object getAreasList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	List<Area>  areaList= addressService.getAreasList(page);
        	obj = renderSuccess(areaList);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 添加我的收货地址------pjf
     *
     * @param resource
     * @return
     */
    @RequestMapping("/addMyAddress")
    @ResponseBody
    public Object addMyAddress(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Integer b = addressService.addMyAddress(page);
        	obj = renderSuccess(b);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 修改默认收货地址,默认唯一------pjf
     *
     * @param resource
     * @return
     */
    @RequestMapping("/editDefultAddress")
    @ResponseBody
    public Object editDefultAddress(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Integer b = addressService.editDefultAddress(page);
        	obj = renderSuccess(b);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 修改我的收获地址------pjf
     *
     * @param resource
     * @return
     */
    @RequestMapping("/editMyAddress")
    @ResponseBody
    public Object editMyAddress(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	Integer b = addressService.editMyAddress(page);
        	obj = renderSuccess(b);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 根据ID获取地址详情------pjf
     *
     * @param resource
     * @return
     */
    @RequestMapping("/getAddressById")
    @ResponseBody
    public Object getAddressById(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxAddress wxAddress = addressService.getAddressById(page);
        	obj = renderSuccess(wxAddress);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
}
