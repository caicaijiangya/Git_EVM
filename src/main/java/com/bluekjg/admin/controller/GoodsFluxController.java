package com.bluekjg.admin.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluekjg.admin.model.DataAnalysisDto;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.service.IGoodsFluxService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

import net.sf.json.JSONArray;

/**
 * <p>
 * 数据分析-渠道 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2019-03-01
 */
@Controller
@RequestMapping("/goodsFlux")
public class GoodsFluxController extends BaseController {

    @Autowired 
    private IGoodsFluxService goodsFluxService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/dataAnalysis/goodsChannel/channelList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Goods goods, Integer page, Integer rows) {
    	if(goods.getFormat() == null) {goods.setFormat("%Y");}
    	if(goods.getGdId() != null && goods.getGdId().length() > 0) {
    		goods.setGdIds(goods.getGdId().split(","));
    	}
    	PageInfo pageInfo = new PageInfo(page, rows);
    	goodsFluxService.selectDataGrid(pageInfo,goods);
        return pageInfo;
    }

    /**
     * 查看多渠道图表
     * @return
     */
    @GetMapping("/moreGraphics")
    public String moreGraphics(Goods goods,Model model) {
    	if(goods.getFormat() != null) {
    		goods.setFormat(URLDecoder.decode(goods.getFormat()));
    	}
    	if(goods.getFormat() == null) {goods.setFormat("%Y");}
    	List<Map<String,Object>> list = goodsFluxService.selectMoreGraphics(goods);
    	
    	JSONArray jsonData = JSONArray.fromObject(list);
    	model.addAttribute("jsonData",jsonData);
        return "admin/dataAnalysis/goodsChannel/moreGraphics";
    }
    
    @GetMapping("/storeData")
    public String storeData(Model model,Goods goods) {
    	model.addAttribute("goodsId", goods.getGoodsId());
    	model.addAttribute("specId", goods.getSpecId());
    	model.addAttribute("salePrice", goods.getSalePrice());
    	if(goods.getFormat() != null) {
    		goods.setFormat(URLDecoder.decode(goods.getFormat()));
    	}
    	if(goods.getFormat() == null) {goods.setFormat("%Y");}
    	goods.setStartTime(URLDecoder.decode(goods.getStartTime()));
    	goods.setEndTime(URLDecoder.decode(goods.getEndTime()));
    	model.addAttribute("format", goods.getFormat());
    	model.addAttribute("startTime", goods.getStartTime());
    	model.addAttribute("endTime", goods.getEndTime());
        return "admin/dataAnalysis/goodsChannel/storeDataList";
    }
    
    @PostMapping("/storeFluxData")
    @ResponseBody
    public PageInfo storeFluxData(Goods goods, Integer page, Integer rows) {
    	if(goods.getFormat() != null) {
    		goods.setFormat(URLDecoder.decode(goods.getFormat()));
    	}
    	if(goods.getFormat() == null) {goods.setFormat("%Y");}
    	goods.setStartTime(URLDecoder.decode(goods.getStartTime()));
    	goods.setEndTime(URLDecoder.decode(goods.getEndTime()));
    	if(goods.getNewStoreId() != null && goods.getNewStoreId().length() > 0) {
    		goods.setNewStoreIds(goods.getNewStoreId().split(","));
    	}
    	PageInfo pageInfo = new PageInfo(page, rows);
    	goodsFluxService.selectStoreFluxData(pageInfo,goods);
        return pageInfo;
    }
   
    /**
     * 导出统计
     * @param 
     * @return
     */
    @RequestMapping("/downLoadMoreChannel")
    @ResponseBody
    public Object downLoadMoreChannel(@Valid Goods goods, HttpServletResponse response) {
    	if(goods.getFormat() != null) {
    		goods.setFormat(URLDecoder.decode(goods.getFormat()));
    	}
    	if(goods.getFormat() == null) {goods.setFormat("%Y");}
    	if(goods.getGdId() != null && goods.getGdId().length() > 0) {
    		goods.setGdIds(goods.getGdId().split(","));
    	}
        goodsFluxService.downLoadMoreChannel(goods, response);
        return renderSuccess("导出成功");
    }
}
