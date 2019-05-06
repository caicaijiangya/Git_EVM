package com.bluekjg.core.custom;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.bluekjg.core.commons.report.excel.ExcelField;
import com.bluekjg.core.commons.report.excel.ExcelFiledInfo;
import com.bluekjg.core.commons.utils.StringUtils;

/**	
 *
 * 快速简单操作Excel的工具
 *
 * 参考：http://git.oschina.net/pyinjava/fastexcel
 * 
 * @author L.cm
 */
public final class ReportExcel implements Closeable {
	private static final Logger logger = LogManager.getLogger(ReportExcel.class);
	
	// 缓存类信息，提高性能
	private static final ConcurrentMap<String, List<ExcelFiledInfo>> filedInfo = new ConcurrentHashMap<String, List<ExcelFiledInfo>>();
	// 表达式处理
	private static final ExpressionParser elParser = new SpelExpressionParser();
	private static final TemplateParserContext elContext = new TemplateParserContext();
	// 默认的格式化
	public static final String DEFAULT_NUM_FORMAT = "#.##";
	public static final String DEFAULT_DATE_FORMAT = "hh:mm:ss";
	public static final String DEFAULT_BOOL_FORMAT = "true:false";
	
	private final Workbook workbook;
	
	public ReportExcel() throws IOException, InvalidFormatException {
		this.workbook = new HSSFWorkbook();
	}
	
	/**
	 * 写入1个sheet
	 *
	 * @param list 数据列表
	 * @param sheet sheet名字
	 * @param exportDate 生成文件时追加的导出日期,网页上选择的那个参数
	 * @return 写入结果
	 */
	public <W> boolean createExcel(HttpServletResponse response,List<W> list,String sheet,String exportDate) {
		if (null == list || list.isEmpty()) {
			return false;
		}

		// 获取execl的配置信息
		W excel = list.get(0);
		List<ExcelFiledInfo> infoList = getFiledInfo(excel.getClass());
		// 排序
		Collections.sort(infoList);
		
		FileOutputStream fileOutputStream = null;
		try {
			
			Sheet workSheet = workbook.createSheet(sheet);
			setSheet(list, infoList, workSheet);
			
	    	response.setHeader("content-disposition", "attachement;filename="+ new String((sheet+"报表_"+exportDate+".xls").getBytes("gb2312"), "ISO8859-1" ));
			response.setContentType("application/octet-stream;charset=UTF-8");
	    	OutputStream out = response.getOutputStream();
	    	workbook.write(out);
	    	out.flush();
			out.close();
			
		} catch (IOException e) {
			logger.error("流异常", e);
		} catch (IllegalAccessException e) {
			logger.error("反射异常", e);
		} catch (Exception e) {
			logger.error("其他异常", e);
		} finally {
			IOUtils.closeQuietly(fileOutputStream);
		}
		return true;
	}
	
	/**
	 * 写入2个sheet
	 *
	 * @param sheet1List 第一个sheet的数据
	 * @param sheet1  第一个sheet的名字
	 * @param sheet2List 第二个sheet的数据
	 * @param sheet2  第二个sheet的名字
	 * @param exportDate 生成文件时追加的导出日期,网页上选择的那个参数
	 * @return 写入结果
	 */
	public <W,L> boolean createExcel(HttpServletResponse response,List<W> sheet1List,String sheet1,List<L> sheet2List,String sheet2,String exportDate) {
		if (null == sheet1List || sheet1List.isEmpty()) {
			return false;
		}
		if(null == sheet2List || sheet2List.isEmpty()) {
			return false;
		}

		W sheet1Excel = sheet1List.get(0);
		List<ExcelFiledInfo> sheet1InfoList = getFiledInfo(sheet1Excel.getClass());
		// 排序
		Collections.sort(sheet1InfoList);
		
		L sheet2Execl = sheet2List.get(0);
		List<ExcelFiledInfo> sheet2InfoList = getFiledInfo(sheet2Execl.getClass());
		// 排序
		Collections.sort(sheet2InfoList);

		FileOutputStream fileOutputStream = null;
		try {
			
			Sheet workSheet = workbook.createSheet(sheet1);
			setSheet(sheet1List, sheet1InfoList, workSheet);
			
			Sheet leaveSheet = workbook.createSheet(sheet2);
			setSheet(sheet2List, sheet2InfoList, leaveSheet);
			//默认使用 sheet1 的名字
	    	response.setHeader("content-disposition", "attachement;filename="+ new String((sheet1+"报表_"+exportDate+".xls").getBytes("gb2312"), "ISO8859-1" ));
			response.setContentType("application/octet-stream;charset=UTF-8");
	    	OutputStream out = response.getOutputStream();
	    	workbook.write(out);
	    	out.flush();
			out.close();
			
		} catch (IOException e) {
			logger.error("流异常", e);
		} catch (IllegalAccessException e) {
			logger.error("反射异常", e);
		} catch (Exception e) {
			logger.error("其他异常", e);
		} finally {
			IOUtils.closeQuietly(fileOutputStream);
		}
		return true;
	}

	private <T> void setSheet(List<T> list, List<ExcelFiledInfo> infoList, Sheet sheet) throws IllegalAccessException {
		// 生成标题行
		Row titleRow = sheet.createRow(0);
		int fieldSize = infoList.size();
		
		CellStyle headCellStyle = workbook.createCellStyle();
		headCellStyle = workbook.createCellStyle();
		headCellStyle.setAlignment(HorizontalAlignment.CENTER);//居中
		Font font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		headCellStyle.setFont(font);
		
		for (int i = 0; i < fieldSize; i++) {
			Cell cell = titleRow.createCell(i);
			ExcelFiledInfo info = infoList.get(i);
			String cellName = info.getCellName();
			if (StringUtils.isBlank(cellName)) {
				cellName = info.getField().getName();
			}
			cell.setCellValue(cellName);
			cell.setCellStyle(headCellStyle);
		}
		
		CellStyle numberCellStyle = workbook.createCellStyle();
		numberCellStyle = workbook.createCellStyle();
		numberCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DEFAULT_NUM_FORMAT));
		
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle = workbook.createCellStyle();
		DataFormat dateFormat = workbook.createDataFormat();
		dateCellStyle.setDataFormat(dateFormat.getFormat(DEFAULT_DATE_FORMAT));
		
		// 生成数据行
		for (int i = 0, length = list.size(); i < length; i++) {
			Row row = sheet.createRow(i + 1);
			T t = list.get(i);
			for (int j = 0; j < fieldSize; j++) {
				Cell cell = row.createCell(j);
				ExcelFiledInfo info = infoList.get(j);
				String format = info.getFormat();
				Field field   = info.getField();
				String el     = info.getEl();
				Object object;
				// 如果存在el表达式，计算他
				if (StringUtils.isNotBlank(el)) {
					Expression expression = elParser.parseExpression(el, elContext);
					object = expression.getValue(t, field.getType());
				} else {
					object = field.get(t);
				}

				switch (info.getType()) {
				case TEXT:
					cell.setCellType(CellType.STRING);
					if(object!=null) {
						cell.setCellValue(object.toString());
					}else {
						cell.setCellValue("");
					}
					break;
				case NUMBER:
					cell.setCellType(CellType.NUMERIC);
					cell.setCellStyle(numberCellStyle);
					if (object instanceof Integer) {
						cell.setCellValue((Integer) object);
					} else if (object instanceof Short) {
						cell.setCellValue((Short) object);
					} else if (object instanceof Float) {
						cell.setCellValue((Float) object);
					} else if (object instanceof Byte) {
						cell.setCellValue((Byte) object);
					} else if (object instanceof Double) {
						cell.setCellValue((Double) object);
					} else {
						cell.setCellValue((String) object);
					}
					break;
				case DATE:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(dateCellStyle);
					if(object!=null) {
						cell.setCellValue((Date) object);
					}else {
						cell.setCellValue("");
					}
					break;
				case BOOL:
					cell.setCellType(CellType.STRING);
					format = StringUtils.isBlank(format) ? DEFAULT_BOOL_FORMAT : format;
					String[] boolValues = format.split(":|:");
					cell.setCellValue(object == null ? boolValues[0] : (Boolean) object ? boolValues[0] : boolValues[1]);
					break;
				}
			}
		}
	}
	
	@Override
	public void close() throws IOException {
		this.workbook.close();
	}
	
	/**
	 * 获取类注解信息
	 * @param clazz
	 * @return ExcelFiledInfo集合
	 */
	private List<ExcelFiledInfo> getFiledInfo(Class<?> clazz) {
		String name = clazz.getName();
		if (filedInfo.containsKey(name)) {
			return filedInfo.get(name);
		} else {
			List<ExcelFiledInfo> infoList = loadFiledInfo(clazz);
			filedInfo.put(name, infoList);
			return infoList;
		}
	}
	
	/**
	 * 获取类注解信息
	 * @param clazz
	 * @return ExcelFiledInfo集合
	 */
	private List<ExcelFiledInfo> loadFiledInfo(Class<?> clazz) {
		List<ExcelFiledInfo> infoList = new ArrayList<ExcelFiledInfo>();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(ExcelField.class)) {
				ExcelField excelField = field.getAnnotation(ExcelField.class);
				field.setAccessible(true);
				infoList.add(new ExcelFiledInfo(field, excelField, i));
			}
		}
		return infoList;
	}
	
}
