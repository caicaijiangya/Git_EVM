package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluekjg.admin.model.IndexModel;
import com.bluekjg.admin.service.IIndexModelService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.StringUtils;

/**
 * <p>
 * 模块管理表 前端控制器
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
@Controller
@RequestMapping("/indexModel")
public class IndexModelController extends BaseController {

@Autowired private IIndexModelService indexModelService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/indexModel/indexModelList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(IndexModel indexModel, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        indexModelService.selectDataGrid(pageInfo,indexModel);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/indexModel/indexModelAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid IndexModel indexModel,HttpServletRequest request,HttpServletResponse response) {
    	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultiValueMap<String, MultipartFile> multiMap = multiRequest.getMultiFileMap();
		if (multiMap != null){
			for (String key : multiMap.keySet()) {
				List<MultipartFile> fileList = multiMap.get(key);
				for (MultipartFile file : fileList) {
					try {
						if (!file.isEmpty()) {
							if("uploadImage".equals(file.getName())){
								indexModel.setModelImg(StringUtils.uploadImage(file));
							}
						}
					} catch (Exception e) {
						logger.error(e.getMessage(),e);
					}
				}
			}
		}
        indexModel.setLastModifiedTime(new Date());
        boolean b = indexModelService.insert(indexModel);
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
        IndexModel indexModel = new IndexModel();
        indexModel.setId(id);
        indexModel.setLastModifiedTime(new Date());
        indexModel.setIsDel(1);
        boolean b = indexModelService.updateById(indexModel);
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
        IndexModel indexModel = indexModelService.selectById(id);
        model.addAttribute("indexModel", indexModel);
        return "admin/indexModel/indexModelEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid IndexModel indexModel,HttpServletRequest request,HttpServletResponse response) {
    	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultiValueMap<String, MultipartFile> multiMap = multiRequest.getMultiFileMap();
		if (multiMap != null){
			for (String key : multiMap.keySet()) {
				List<MultipartFile> fileList = multiMap.get(key);
				for (MultipartFile file : fileList) {
					try {
						if (!file.isEmpty()) {
							if("uploadImage".equals(file.getName())){
								indexModel.setModelImg(StringUtils.uploadImage(file));
							}
						}
						
					} catch (Exception e) {
						logger.error(e.getMessage(),e);
					}
				}
			}
		}
        indexModel.setLastModifiedTime(new Date());
        boolean b = indexModelService.updateById(indexModel);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
