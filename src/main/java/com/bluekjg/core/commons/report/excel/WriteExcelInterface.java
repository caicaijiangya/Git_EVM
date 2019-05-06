package com.bluekjg.core.commons.report.excel;

/**
 * 需要自己处理的excel列
 * @Description 
 * @date 2015-11-10
 */
public interface WriteExcelInterface {
	public String processData(String field,Object value);
}
