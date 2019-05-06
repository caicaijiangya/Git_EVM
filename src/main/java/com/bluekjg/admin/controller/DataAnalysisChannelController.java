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
import com.bluekjg.admin.service.IDataAnalysisChannelService;
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
@RequestMapping("/data_channel")
public class DataAnalysisChannelController extends BaseController {

    @Autowired 
    private IDataAnalysisChannelService dataAnalysisChannelService;
    
    @GetMapping("/oddManager")
    public String oddManager() {
        return "admin/dataAnalysis/channel/oddList";
    }
    
    @PostMapping("/oddDataGrid")
    @ResponseBody
    public PageInfo oddDataGrid(DataAnalysisDto channel, Integer page, Integer rows) {
    	if(channel.getFormat() == null) {channel.setFormat("%Y");}
    	if(channel.getStoreId() != null && channel.getStoreId().length() > 0) {
    		channel.setStoreIds(channel.getStoreId().split(","));
    	}
    	PageInfo pageInfo = new PageInfo(page, rows);
    	dataAnalysisChannelService.selectOddDataGrid(pageInfo,channel);
        return pageInfo;
    }
    
    /**
     * 查看单渠道图表
     * @return
     */
    @GetMapping("/ollGraphics")
    public String ollGraphics(DataAnalysisDto channel,Model model) {
    	if(channel.getFormat() != null) {
    		channel.setFormat(URLDecoder.decode(channel.getFormat()));
    	}
    	if(channel.getFormat() == null) {channel.setFormat("%Y");}
    	if(channel.getStoreId() != null && channel.getStoreId().length() > 0) {
    		channel.setStoreIds(channel.getStoreId().split(","));
    	}
    	List<Map<String,Object>> list = dataAnalysisChannelService.selectOddGraphics(channel);
    	JSONArray jsonData = JSONArray.fromObject(list);
    	model.addAttribute("jsonData",jsonData);
        return "admin/dataAnalysis/channel/oddGraphics";
    }
    
    /**
     * 导出单渠道统计
     * @param 
     * @return
     */
    @RequestMapping("/downLoadOddChannel")
    @ResponseBody
    public Object downLoadOddChannel(@Valid DataAnalysisDto channel, HttpServletResponse response) {
    	if(channel.getFormat() != null) {
    		channel.setFormat(URLDecoder.decode(channel.getFormat()));
    	}
    	if(channel.getFormat() == null) {channel.setFormat("%Y");}
    	if(channel.getStoreId() != null && channel.getStoreId().length() > 0) {
    		channel.setStoreIds(channel.getStoreId().split(","));
    	}
        dataAnalysisChannelService.downLoadOddChannel(channel, response);
        return renderSuccess("导出成功");
    }
    
    
    
    
    @GetMapping("/moreManager")
    public String moreManager() {
        return "admin/dataAnalysis/channel/moreList";
    }
    
    @PostMapping("/moreDataGrid")
    @ResponseBody
    public PageInfo moreDataGrid(DataAnalysisDto channel, Integer page, Integer rows) {
    	if(channel.getFormat() == null) {channel.setFormat("%Y");}
    	if(channel.getStoreId() != null && channel.getStoreId().length() > 0) {
    		channel.setStoreIds(channel.getStoreId().split(","));
    	}
    	PageInfo pageInfo = new PageInfo(page, rows);
    	dataAnalysisChannelService.selectMoreDataGrid(pageInfo,channel);
        return pageInfo;
    }
    /**
     * 查看多渠道图表
     * @return
     */
    @GetMapping("/moreGraphics")
    public String moreGraphics(DataAnalysisDto channel,Model model) {
    	if(channel.getFormat() != null) {
    		channel.setFormat(URLDecoder.decode(channel.getFormat()));
    	}
    	if(channel.getFormat() == null) {channel.setFormat("%Y");}
    	if(channel.getStoreId() != null && channel.getStoreId().length() > 0) {
    		channel.setStoreIds(channel.getStoreId().split(","));
    	}
    	List<Map<String,Object>> list = dataAnalysisChannelService.selectMoreGraphics(channel);
    	List<Map<String,Object>> rateList = dataAnalysisChannelService.selectMoreRateDataGrid(channel);
    	List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
    	if(list != null && list.size() > 0) {
    		dataAnalysisChannelService.dataProcessing(list, dataList);
    	}
    	
    	JSONArray jsonData = JSONArray.fromObject(dataList);
    	JSONArray jsonRate = JSONArray.fromObject(rateList);
    	model.addAttribute("jsonData",jsonData);
    	model.addAttribute("jsonRate", jsonRate);
        return "admin/dataAnalysis/channel/moreGraphics";
    }
    /**
     * 导出多渠道统计
     * @param 
     * @return
     */
    @RequestMapping("/downLoadMoreChannel")
    @ResponseBody
    public Object downLoadMoreChannel(@Valid DataAnalysisDto channel, HttpServletResponse response) {
    	if(channel.getFormat() != null) {
    		channel.setFormat(URLDecoder.decode(channel.getFormat()));
    	}
    	if(channel.getFormat() == null) {channel.setFormat("%Y");}
    	if(channel.getStoreId() != null && channel.getStoreId().length() > 0) {
    		channel.setStoreIds(channel.getStoreId().split(","));
    	}
        dataAnalysisChannelService.downLoadMoreChannel(channel, response);
        return renderSuccess("导出成功");
    }
}
