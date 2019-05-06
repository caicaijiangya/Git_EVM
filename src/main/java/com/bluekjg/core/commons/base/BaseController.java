package com.bluekjg.core.commons.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baomidou.mybatisplus.plugins.Page;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.result.Result;
import com.bluekjg.core.commons.shiro.ShiroUser;
import com.bluekjg.core.commons.utils.Charsets;
import com.bluekjg.core.commons.utils.StringEscapeEditor;
import com.bluekjg.core.commons.utils.URLUtils;

/**
 * @description：基础 controller
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public abstract class BaseController {
	
    // 控制器本来就是单例，这样似乎更加合理
    protected Logger logger = LogManager.getLogger(getClass());

    /**
     * 获取当前登录用户对象
     * @return {ShiroUser}
     */
    public ShiroUser getShiroUser() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取当前登录用户id
     * @return {Long}
     */
    public Long getUserId() {
        return this.getShiroUser().getId();
    }
    
    /**
     * 获取当前登录用户的手机号码
     * @return {Long}
     */
    public String getUserTel() {
        return this.getShiroUser().getPhone();
    }

    /**
     * 获取当前登录用户名
     * @return {String}
     */
    public String getStaffName() {
        return this.getShiroUser().getName();
    }

    /**
     * ajax失败
     * @param msg 失败的消息
     * @return {Object}
     */
    public Object renderError(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }
    
    /**
     * ajax成功
     * @return {Object}
     */
    public Object renderSuccess() {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }
    
    /**
     * ajax成功
     * @param msg 消息
     * @return {Object}
     */
    public Object renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     * @param obj 成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj) {
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(obj);
        return result;
    }
    
    public <T> Page<T> getPage(int current, int size, String sort, String order){
        Page<T> page = new Page<T>(current, size, sort);
        if ("desc".equals(order)) {
            page.setAsc(false);
        } else {
            page.setAsc(true);
        }
        return page;
    }
    
    public <T> PageInfo pageToPageInfo(Page<T> page) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRows(page.getRecords());
        pageInfo.setTotal(page.getTotal());
        pageInfo.setOrder(page.getOrderByField());
        return pageInfo;
    }


	/**
	 * redirect跳转
	 * @param url 目标url
	 */
	protected String redirect(String url) {
		return new StringBuilder("redirect:").append(url).toString();
	}
	
	/**
	 * 下载文件
	 * @param file 文件
	 */
	protected ResponseEntity<Resource> download(File file) {
		String fileName = file.getName();
		return download(file, fileName);
	}
	
	/**
	 * 下载
	 * @param file 文件
	 * @param fileName 生成的文件名
	 * @return {ResponseEntity}
	 */
	protected ResponseEntity<Resource> download(File file, String fileName) {
		Resource resource = new FileSystemResource(file);
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String header = request.getHeader("User-Agent");
		// 避免空指针
		header = header == null ? "" : header.toUpperCase();
		HttpStatus status;
		if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
			fileName = URLUtils.encodeURL(fileName, Charsets.UTF_8);
			status = HttpStatus.OK;
		} else {
			fileName = new String(fileName.getBytes(Charsets.UTF_8), Charsets.ISO_8859_1);
			status = HttpStatus.CREATED;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", fileName);
		return new ResponseEntity<Resource>(resource, headers, status);
	}
	
	
	/**
	 * 上传
	 * @param file 文件
	 * @param fileName 生成的文件名
	 * @return {ResponseEntity}
	 */
	 protected Map<String, String> upload(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException,IOException {
			String fileName = "";
			String fileRelativePath = "";
			// 上传目录路径
			String dirPath = request.getRealPath("/../weshopimg")+"/";
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultiValueMap<String, MultipartFile> multiMap = multiRequest
					.getMultiFileMap();
			if (multiMap != null) {
				// jquery upload 是一个一个上传的,所以这里实际上只有一个
				for (String key : multiMap.keySet()) {
					List<MultipartFile> fileList = multiMap.get(key);
					// 这里也只有一个
					for (MultipartFile file : fileList) {
						String filePrefix = file.getOriginalFilename();
						int lastDotIndex = filePrefix.lastIndexOf(".");
						String fileSuffix = "";
						if (lastDotIndex > -1
								&& lastDotIndex < filePrefix.length() - 1) {
							fileSuffix = filePrefix.substring(lastDotIndex + 1);
							filePrefix = filePrefix.substring(0, lastDotIndex);
						}
						String dirName = request.getParameter("dir");
						if (dirName == null) {
							dirName = "image";
						}
						dirPath += dirName + "/";
						fileRelativePath += dirName + "/";
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						String nowadays = sdf.format(new Date());
						dirPath += nowadays + "/";
						fileRelativePath += nowadays + "/";
						File dir = new File(dirPath);
						// 如果上传目录不存在则创建
						if (!dir.exists() || dir.isFile()) {
							dir.mkdirs();
						}
						// 文件名
						fileName = System.currentTimeMillis() + "." + fileSuffix;
						fileRelativePath += fileName;
						// 如果文件名不为空则上传
						if (fileName != null && !"".equals(fileName.trim())) {
							file.transferTo(new File(dirPath, fileName));
						}
					}
				}
			}
			Map<String, String> result = new HashMap<String, String>();
			result.put("fileName", fileName);
			result.put("fileRelativePath", fileRelativePath);
			return result;
		}
	 
	@InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor());
    }
}
