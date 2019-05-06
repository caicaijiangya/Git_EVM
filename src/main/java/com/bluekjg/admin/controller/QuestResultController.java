package com.bluekjg.admin.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.ExportDto;
import com.bluekjg.admin.service.IQuestProblemService;
import com.bluekjg.admin.upload.ExcelUtil;
import com.bluekjg.admin.upload.WriteExcelInterface;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.utils.DateUtil;

/**
 * <p>
 * 测试结果 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2018-10-29
 */
@Controller
@RequestMapping("/questResult")
public class QuestResultController extends BaseController {
	@Autowired
    private IQuestProblemService questProblemService;
    @GetMapping("/manager")
    public String manager(Model model) {
    	model.addAttribute("startDate", DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
    	model.addAttribute("endDate", DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
        return "admin/questResult/questResultList";
    }
    
    /**
     * 问卷测试导出
     * @param 
     * @return
     */
    @PostMapping("/export_qr")
    @ResponseBody
    public void export_qr(@Valid ExportDto dto,HttpServletRequest request,HttpServletResponse response) {
    	List<Map<String,Object>> list = questProblemService.queryQuestExportData(dto);
    	List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "姓名", "userName" });
		metadata.add(new String[] { "测试日期", "date" });
		metadata.add(new String[] { "测试基础分数", "score" });
		metadata.add(new String[] { "测试百分制分数", "realScore" });
		metadata.add(new String[] { "结论组合", "key" });
		metadata.add(new String[] { "结论名称", "name" });
		
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("问卷测试");
			name.append(DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"));
			name.append(".xls");
			response.setHeader("Content-Disposition", "attachment; filename="+ new String(name.toString().getBytes(), "ISO8859-1"));
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    }
}
