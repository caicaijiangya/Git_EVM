package com.bluekjg.admin.controller;

import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.DictData;
import com.bluekjg.admin.model.ParameterData;
import com.bluekjg.admin.service.IDictService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.ConstantUtils;
import com.bluekjg.wxapp.utils.DictUtil;

/**
 * <p>
 * 数据字典表 前端控制器
 * </p>
 *
 * @author Max
 * @since 2018-03-30
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

    @Autowired private IDictService wxappDictService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/dict/dictlist";
    }
    
    @GetMapping("/managerParam")
    public String managerParam() {
        return "admin/param/paramlist";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(DictData dict, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	wxappDictService.queryDictList(pageInfo, dict);
        return pageInfo;
    }
    
    @PostMapping("/dataGridParam")
    @ResponseBody
    public PageInfo dataGridParam(ParameterData param, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	wxappDictService.queryParameterList(pageInfo, param);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/dict/dictadd";
    }
    @GetMapping("/addParamPage")
    public String addParamPage() {
        return "admin/param/paramadd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid DictData wxappDict) {
    	String dictCode = wxappDict.getDictCode();
    	Integer n = wxappDictService.countDictNum(wxappDict);
    	if (n>0) {
    		return renderError("该编码已存在,请重新输入！");
		}else {
			boolean b = wxappDictService.insert(wxappDict);
	        if (b) {
	            return renderSuccess("添加成功！");
	        } else {
	            return renderError("添加失败！");
	        }
		}
        
    }
    @PostMapping("/addParam")
    @ResponseBody
    public Object addParam(@Valid ParameterData param) {
        boolean b = wxappDictService.addParam(param);
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
    public Object delete(DictData wxappDict) {
    	wxappDict.setId(wxappDict.getId());
    	wxappDict.setLastModifiedTime(new Date());
    	wxappDict.setIsDel(1);
        boolean b = wxappDictService.updateById(wxappDict);
        if (b) {
            return renderSuccess("删除成功！");
        } else {
            return renderError("删除失败！");
        }
    }
    @PostMapping("/deleteParam")
    @ResponseBody
    public Object deleteParam(ParameterData param) {
    	param.setIsDel(1);
        boolean b = wxappDictService.updateParam(param);
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
        DictData wxappDict = wxappDictService.selectById(id);
        model.addAttribute("wxappDict", wxappDict);
        return "admin/dict/dictedit";
    }
    @GetMapping("/editParamPage")
    public String editParamPage(Model model, Integer id) {
    	ParameterData params = wxappDictService.queryParameterById(id);
        model.addAttribute("params", params);
        return "admin/param/paramedit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid DictData dict) {
    	if (dict.getOldCode().equals(dict.getDictCode())) {
    		boolean b = wxappDictService.updateById(dict);
            if (b) {
                return renderSuccess("编辑成功！");
            } else {
                return renderError("编辑失败！");
            }
		}else {
			Integer n  = wxappDictService.countDictNum(dict);
			if (n>0) {
				return renderError("已存在的编码,请重新修改！");
			}else {
				boolean b = wxappDictService.updateById(dict);
		        if (b) {
		            return renderSuccess("编辑成功！");
		        } else {
		            return renderError("编辑失败！");
		        }
			}
		}
        
    }
    @PostMapping("/editParam")
    @ResponseBody
    public Object editParam(@Valid ParameterData param) {
        boolean b = wxappDictService.updateParam(param);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    /**
     * 查询所在大区数据
     *
     * @return
     */
    @PostMapping(value = "/allAreaTree")
    @ResponseBody
    public Object allAreaTree() {
        return wxappDictService.selectTree(ConstantUtils.STORE_AREA);
    }
    
    /**
     * 查询所在班次数据
     *
     * @return
     */
    @PostMapping(value = "/allBanciTree")
    @ResponseBody
    public Object allBanciTree() {
        return wxappDictService.selectTree(ConstantUtils.BANCI_TYPE);
    }
    
    /**
     * 查询所在支付类型数据
     *
     * @return
     */
    @PostMapping(value = "/allPayTypeTree")
    @ResponseBody
    public Object allPayTypeTree() {
        return wxappDictService.selectTree(ConstantUtils.PAY_TYPE);
    }
    
    
    /**
     * 规则页面
     * @return
     */
    @GetMapping("/rules")
    public String rules(Model model) {
    	DictData dict0 = wxappDictService.queryDictByCode(DictUtil.RULES_RETURN_SALES);
    	DictData dict1 =wxappDictService.queryDictByCode(DictUtil.RULES_INTEGRAL);
    	DictData dict2 =wxappDictService.queryDictByCode(DictUtil.INDEX_ACTIVITY_IMAGE);
    	DictData dict3 =wxappDictService.queryDictByCode(DictUtil.RULES_BARGAIN);
    	model.addAttribute("dict0", dict0);
    	model.addAttribute("dict1", dict1);
    	model.addAttribute("dict2", dict2);
    	model.addAttribute("dict3", dict3);
        return "admin/dict/rules";
    }
}
