package com.bluekjg.wxapp.controller;

import java.util.Date;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.wxapp.utils.CommonUtil;
import com.bluekjg.wxapp.utils.PostObjectSample;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

/**
 * @description：文件上传
 * @author：pincui.tom
 * @date：2018/3/23 14:51
 */
@Controller
@RequestMapping("/xcx/upload")
public class WxUploadController extends BaseController {
    
    /**
     * （阿里云库）
     * @param uploadFile
     * @return
     */
    @RequestMapping(value = "/ali/image", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	@ResponseBody
	public String upload(@RequestParam(value="uploadFile", required=false) MultipartFile uploadFile) {
    	String msg = "";
		try {
			// 生成一个新的文件名
			// 取原始文件名
			String oldName = uploadFile.getOriginalFilename();
			
			// 生成新文件名
			// UUID.randomUUID();
			String newName = UUID.randomUUID().toString().replaceAll("-", "");
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			// 文件上传
			String imagePath = DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD);
			Integer result = PostObjectSample.PostObject(imagePath + "/" + newName, uploadFile.getContentType(),
					uploadFile.getInputStream());
			// 返回结果
			if (result > 0) {
				msg = WxappConfigUtil.ALICLOUD_IMAGE_BASE_URL + imagePath + "/" + newName;
			}
			System.out.println("===>>>"+WxappConfigUtil.ALICLOUD_IMAGE_BASE_URL + imagePath + "/" + newName);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return msg;
	}
    
    
    /**
     * 图片上传本地服务器
     * @param uploadFile
     * @return
     */
    @RequestMapping(value = "/file/image", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	@ResponseBody
	public Object fileUpload(@RequestParam(value="uploadFile", required=false) MultipartFile uploadFile) {
    	String msg = "";
		try {
			// 生成一个新的文件名
			// 取原始文件名
			String oldName = uploadFile.getOriginalFilename();
			// 生成新文件名
			// UUID.randomUUID();
			String newName = UUID.randomUUID().toString().replaceAll("-", "");
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			String imagePath = DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD);
			// 文件上传
			String url = CommonUtil.saveOutputStream(newName,imagePath, uploadFile.getInputStream());
			msg = url+"&tempPath="+imagePath+"&tempName="+newName;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return msg;
	}
}
