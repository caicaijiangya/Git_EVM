package com.bluekjg.admin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.wxapp.utils.PostObjectSample;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController extends BaseController{
	@RequestMapping(value = "/upload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	@ResponseBody
	public String upload(MultipartFile uploadFile) throws IllegalStateException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String msg = null;
		try {
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
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return msg;
	}
	
}
