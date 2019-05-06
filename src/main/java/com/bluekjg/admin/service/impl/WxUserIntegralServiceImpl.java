package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.WxUserInfo;
import com.bluekjg.admin.mapper.WxUserIntegralMapper;
import com.bluekjg.admin.service.IWxUserIntegralService;
import com.bluekjg.admin.upload.ExcelUtil;
import com.bluekjg.admin.upload.WriteExcelInterface;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.DateUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户积分表 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2019-01-14
 */
@Service
public class WxUserIntegralServiceImpl extends ServiceImpl<WxUserIntegralMapper, WxUserInfo> implements IWxUserIntegralService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired 
    private WxUserIntegralMapper wxUserIntegralMapper;
	
	@Override
	public void queryUserIntegralList(PageInfo pageInfo, WxUserInfo userInfo) {
		Page<WxUserInfo> page = new Page<WxUserInfo>(pageInfo.getNowpage(), pageInfo.getSize());
		List<WxUserInfo> list = wxUserIntegralMapper.queryUserIntegralList(page, userInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	
	@Override
	public void queryUserIntegralDetailList(PageInfo pageInfo, WxUserInfo userInfo) {
		Page<WxUserInfo> page = new Page<WxUserInfo>(pageInfo.getNowpage(), pageInfo.getSize());
		List<WxUserInfo> list = wxUserIntegralMapper.queryUserIntegralDetailList(page, userInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void updateUserIntegral(WxUserInfo userInfo) {
		// TODO Auto-generated method stub
		wxUserIntegralMapper.updateUserIntegral(userInfo);
	}
	@Override
	public void downLoadIntegral(WxUserInfo userInfo, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = wxUserIntegralMapper.downLoadIntegral(userInfo);
		
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "所属门店", "storeName" });
		metadata.add(new String[] { "员工编号", "code" });
		metadata.add(new String[] { "会员昵称", "nickName" });
		metadata.add(new String[] { "会员姓名", "userName" });
		metadata.add(new String[] { "会员卡号", "" });
		metadata.add(new String[] { "会员等级", "" });
		metadata.add(new String[] { "会员城市", "country" });
		metadata.add(new String[] { "会员电话", "mobileNo" });
		metadata.add(new String[] { "状态", "isIntegral" });
		metadata.add(new String[] { "总积分", "totalIntegral" });
		metadata.add(new String[] { "总消费积分", "conIntegral" });
		metadata.add(new String[] { "有效积分", "integral" });
		metadata.add(new String[] { "截止到当年的有效积分", "dIntegral" });
		metadata.add(new String[] { "截止到次年的有效积分", "cIntegral" });
		metadata.add(new String[] { "失效积分", "failIntegral" });
		metadata.add(new String[] { "获得积分", "currentIntegral" });
		metadata.add(new String[] { "消费积分", "conIntegral1" });
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("积分");
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
	@Override
	public void downLoadIntegralDetail(WxUserInfo userInfo, HttpServletResponse response) {
		List<Map<String, Object>> list = wxUserIntegralMapper.downLoadIntegralDetail(userInfo);
		
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "编号", "id" });
		metadata.add(new String[] { "微信编码", "openId" });
		metadata.add(new String[] { "所属门店", "storeName" });
		metadata.add(new String[] { "员工编号", "code" });
		metadata.add(new String[] { "会员昵称", "nickName" });
		metadata.add(new String[] { "会员姓名", "userName" });
		metadata.add(new String[] { "会员卡号", "" });
		metadata.add(new String[] { "会员等级", "" });
		metadata.add(new String[] { "会员城市", "country" });
		metadata.add(new String[] { "会员电话", "mobileNo" });
		metadata.add(new String[] { "创建时间", "createdTime" });
		metadata.add(new String[] { "有效期截止时间", "failTime"});
		metadata.add(new String[] { "积分说明", "title" });
		metadata.add(new String[] { "积分", "integral" });
		metadata.add(new String[] { "积分类型", "type" });
		metadata.add(new String[] { "备注", "note" });
		metadata.add(new String[] { "状态", "status" });
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("积分明细");
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
