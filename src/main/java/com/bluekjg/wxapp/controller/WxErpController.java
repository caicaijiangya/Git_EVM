package com.bluekjg.wxapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.service.IWxErpService;

@Controller
@RequestMapping("/xcx/api/erp")
public class WxErpController extends BaseController{
	@Autowired
	private IWxErpService erpService;
	/**
     * 查询商品分类
     *
     * @param resource
     * @return
     */
    @RequestMapping("/order")
    @ResponseBody
    public Object queryClassify(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	erpService.pushOrderReturnErp(page.getId());
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
	
}
