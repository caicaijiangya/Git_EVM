package com.bluekjg.core.commons.report.excel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtil {

	public static HSSFWorkbook writeToExcelByMap(List<String[]> metadata,
			List<Map<String, Object>> data, WriteExcelInterface wel) {
		int length = metadata.size();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		for (int i = 0; i < length; i++) {
			sheet.setColumnWidth(i, 20 * 256);
		}
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < length; i++) {
			String[] str = metadata.get(i);
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(str[0]);
			cell.setCellStyle(getOrderExcelTitleStyle(workbook));
		}

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFDataFormat format = workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("@"));

		for (int i = 0; i < data.size(); i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < length; j++) {
				HSSFCell cell = row.createCell(j);
				String[] str = metadata.get(j);
				cell.setCellStyle(cellStyle);
				Object obj = data.get(i).get(str[1]);
				if (obj != null) {
					String value = obj.toString();
					SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
					if (obj instanceof Date) {
						Date date = (Date) obj;
						value = simpleDate.format( date);
					} else if (obj instanceof Timestamp) {
						Timestamp timestamp = (Timestamp) obj;
						value = simpleDate.format(
								timestamp);
					}
					cell.setCellValue(wel.processData(str[1], value));
				}
			}

		}
		return workbook;
	}

	/**
	 * 标题样式
	 * */
	private static CellStyle getOrderExcelTitleStyle(Workbook workBook) {
		CellStyle titleStyle = workBook.createCellStyle();
		Font headerFont = workBook.createFont();
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setFontName("微软雅黑");
		titleStyle.setFont(headerFont);
		titleStyle.setWrapText(true);
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 填充的背景颜色
		HSSFPalette palette = ((HSSFWorkbook) workBook).getCustomPalette();
		palette.setColorAtIndex(HSSFColor.LIME.index, (byte) 147, (byte) 205,
				(byte) 221);
		titleStyle.setFillForegroundColor(HSSFColor.LIME.index);// 前景色
		titleStyle.setFillBackgroundColor(HSSFCellStyle.THICK_FORWARD_DIAG);

		return titleStyle;

	}

	/**
	 * 普通单元格样式
	 * */
	@SuppressWarnings("unused")
	private static CellStyle getOrderExcelCellStyle(Workbook workBook,
			short fontAlign) {
		CellStyle cellStyle = workBook.createCellStyle();
		cellStyle.setWrapText(true);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setAlignment(fontAlign);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFPalette cellPalette = ((HSSFWorkbook) workBook).getCustomPalette();
		cellPalette.setColorAtIndex(HSSFColor.GREEN.index, (byte) 116,
				(byte) 210, (byte) 129);
		cellStyle.setLeftBorderColor(HSSFColor.GREEN.index);
		cellStyle.setRightBorderColor(HSSFColor.GREEN.index);
		cellStyle.setTopBorderColor(HSSFColor.GREEN.index);
		cellStyle.setBottomBorderColor(HSSFColor.GREEN.index);
		return cellStyle;
	}

}
