package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Address;
import com.bluekjg.admin.model.OrderRefund;
import com.bluekjg.admin.mapper.OrderRefundMapper;
import com.bluekjg.admin.service.IOrderRefundService;
import com.bluekjg.admin.upload.ExcelUtil;
import com.bluekjg.admin.upload.WriteExcelInterface;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.core.commons.utils.MapUtils;
import com.mysql.fabric.xmlrpc.base.Array;
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
 * 订单退款表 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2019-01-14
 */
@Service
public class OrderRefundServiceImpl extends ServiceImpl<OrderRefundMapper, OrderRefund> implements IOrderRefundService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired OrderRefundMapper orderRefundMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, OrderRefund of) {
		Page<OrderRefund> page = new Page<OrderRefund>(pageInfo.getNowpage(),pageInfo.getSize());
		List<OrderRefund> list = orderRefundMapper.selectDataGrid(page,of);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void selectAreaDataGrid(PageInfo pageInfo,Integer id){
		List<Address> list = orderRefundMapper.selectAreasList(id);
		pageInfo.setRows(list);
	}

	@Override
	public void selectAddressDataGrid(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		List<Address> list = orderRefundMapper.selectAdminAddress();
		pageInfo.setRows(list);
	}

	@Override
	public void insertAdminAddress(Address address) {
		// TODO Auto-generated method stub
		orderRefundMapper.insertAdminAddress(address);
	}

	@Override
	public Address selectRefundAddress(Integer refundId) {
		// TODO Auto-generated method stub
		return orderRefundMapper.selectRefundAddress(refundId);
	}

	@Override
	public void insertRefundAddress(Address address) {
		// TODO Auto-generated method stub
		orderRefundMapper.insertRefundAddress(address);
	}

	@Override
	public void downLoadOrderRefund(OrderRefund or, HttpServletResponse response) {
		List<OrderRefund> listRefund = orderRefundMapper.selectDataGrid(or);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(listRefund != null) {
			for(OrderRefund refund:listRefund) {
				list.add(MapUtils.object2Map(refund));
			}
		}
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "创建日期", "createdTime" });
		metadata.add(new String[] { "订单所属门店", "storeName" });
		metadata.add(new String[] { "会员昵称", "nickName" });
		metadata.add(new String[] { "会员手机号", "mobileNo" });
		metadata.add(new String[] { "会员姓名", "userName" });
		metadata.add(new String[] { "订单号", "orderNo" });
		metadata.add(new String[] { "商户交易号", "transNo" });
		metadata.add(new String[] { "退款单号", "refundNo" });
		metadata.add(new String[] { "退款类型", "typeName" });
		metadata.add(new String[] { "退款原因", "desc" });
		metadata.add(new String[] { "退款数量", "goodsNum" });
		metadata.add(new String[] { "退款金额", "balances" });
		metadata.add(new String[] { "退款时间", "lastModifiedTime" });
		metadata.add(new String[] { "退货物流", "expressName" });
		metadata.add(new String[] { "退货单号", "expressNo" });
		metadata.add(new String[] { "审核说明", "auditDesc" });
		metadata.add(new String[] { "状态", "statusName" });
		metadata.add(new String[] { "是否入库", "isRk" });
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("订单退款");
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
