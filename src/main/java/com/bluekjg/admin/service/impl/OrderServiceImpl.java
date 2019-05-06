package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Order;
import com.bluekjg.admin.model.OrderAddress;
import com.bluekjg.admin.model.OrderDetail;
import com.bluekjg.admin.model.execl.ExcelBean;
import com.bluekjg.admin.mapper.OrderMapper;
import com.bluekjg.admin.service.IOrderService;
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
 * 订单主表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-09-18
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired OrderMapper orderMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, Order o) {
		Page<Order> page = new Page<Order>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Order> list = orderMapper.selectDataGrid(page,o);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	//导出订单
	@Override
	public void downLoadOrder(Order order, HttpServletResponse response) {
		List<Map<String, Object>> list = orderMapper.downLoadOrder(order);
		String metadataKey[] = order.getMetadata().split(",");
		List<String[]> metadata = new ArrayList<String[]>();
		if(metadataKey != null && metadataKey.length > 0) {
			for(String key:metadataKey) {
				metadata.add(new String[] { ExcelBean.EXCEL_ORDER_MAP.get(key), key });
			}
		}
		/*metadata.add(new String[] { "品牌", "brandName" });
		metadata.add(new String[] { "订单所属门店", "storeName" });
		metadata.add(new String[] { "核销人", "writeOffName" });
		metadata.add(new String[] { "核销导购员工编码", "writeOffCode" });
		metadata.add(new String[] { "下单时间", "createTime" });
		metadata.add(new String[] { "订单类型", "orderType" });
		metadata.add(new String[] { "订单状态", "orderStatus" });
		metadata.add(new String[] { "订单号", "orderNo" });
		metadata.add(new String[] { "商户交易号", "transNo" });
		metadata.add(new String[] { "购买总数量", "goodsNum" });
		metadata.add(new String[] { "订单总标准金额", "goodsPrice" });
		metadata.add(new String[] { "订单总市场价", "totalBalances" });
		metadata.add(new String[] { "运费", "freight" });
		metadata.add(new String[] { "优惠金额", "discountPrice" });
		metadata.add(new String[] { "实付总金额", "payBalances" });
		metadata.add(new String[] { "退款金额", "refundBalances" });
		metadata.add(new String[] { "退款类型", "refundType" });
		metadata.add(new String[] { "赠品", "" });
		metadata.add(new String[] { "赠品金额", "" });
		metadata.add(new String[] { "优惠券名称", "couponName" });
		metadata.add(new String[] { "优惠券券号", "" });
		metadata.add(new String[] { "下单人", "userName" });
		metadata.add(new String[] { "新老客户", "newOld" });
		metadata.add(new String[] { "取货方式", "takeStyle" });
		metadata.add(new String[] { "支付方式", "payMoneyStyle" });
		metadata.add(new String[] { "物流公司", "expressName" });
		metadata.add(new String[] { "物流单号", "expressNo" });
		metadata.add(new String[] { "用户手机号码", "mobileNo" });
		metadata.add(new String[] { "用户收货地址", "detailAddress" });
		metadata.add(new String[] { "活动优惠金额", "discountAmount"});
		metadata.add(new String[] { "第一次购物时间", "minCreatedTime"});
		metadata.add(new String[] { "最近一次购物时间", "maxCreatedTime"});
		metadata.add(new String[] { "总的购物次数", "orderNum"});*/
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("订单");
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
	
	//导出订单详情
	@Override
	public void downLoadOrderDetail(Order order, HttpServletResponse response) {
		List<Map<String, Object>> list = orderMapper.downLoadOrderDetail(order);
		String metadataKey[] = order.getMetadata().split(",");
		List<String[]> metadata = new ArrayList<String[]>();
		if(metadataKey != null && metadataKey.length > 0) {
			for(String key:metadataKey) {
				metadata.add(new String[] { ExcelBean.EXCEL_ORDER_DETAIL_MAP.get(key), key });
			}
		}
		//metadata.add(new String[] { "序号", "i" });
		/*metadata.add(new String[] { "品牌", "brandName" });
		metadata.add(new String[] { "订单所属门店", "storeName" });
		metadata.add(new String[] { "核销人", "writeOffName" });
		metadata.add(new String[] { "核销导购员工编码", "writeOffCode" });
		metadata.add(new String[] { "下单时间", "createTime" });
		metadata.add(new String[] { "支付时间", "payTime" });
		metadata.add(new String[] { "订单状态", "orderStatus" });
		metadata.add(new String[] { "订单类型", "orderType" });
		metadata.add(new String[] { "支付方式", "payMoneyStyle" });
		metadata.add(new String[] { "订单号", "orderNo" });
		metadata.add(new String[] { "商户交易号", "transNo" });
		metadata.add(new String[] { "SKU", "sku" });
		metadata.add(new String[] { "商品名称", "goodsName" });
		metadata.add(new String[] { "是否赠品", "isGift" });
		metadata.add(new String[] { "规格", "goodsSpec" });
		metadata.add(new String[] { "数量", "goodsNum" });
		metadata.add(new String[] { "商品单价", "goodsPrice" });
		metadata.add(new String[] { "商品市场价", "goodsPriceO" });
		metadata.add(new String[] { "优惠金额", "discountPrice" });
		metadata.add(new String[] { "商品实付金额", "transPrice" });
		//metadata.add(new String[] { "商品退款金额", "refundPrice" });
		//metadata.add(new String[] { "退款类型", "refundType" });
		//metadata.add(new String[] { "退款原因", "refundName" });
		metadata.add(new String[] { "收货人", "contactName" });
		metadata.add(new String[] { "联系电话", "contactMobileNo" });
		metadata.add(new String[] { "收货地址", "detailAddress" });
		metadata.add(new String[] { "收货省份", "province" });
		metadata.add(new String[] { "收货城市", "city" });
		metadata.add(new String[] { "拼团状态", "isCollage"});
		metadata.add(new String[] { "会员昵称", "nickName" });
		metadata.add(new String[] { "会员姓名", "userName" });
		metadata.add(new String[] { "会员卡号", "" });
		metadata.add(new String[] { "会员手机号", "mobileNo" });
		metadata.add(new String[] { "新老客户", "newOld" });
		metadata.add(new String[] { "第一次购物时间", "minCreatedTime"});
		metadata.add(new String[] { "最近一次购物时间", "maxCreatedTime"});
		metadata.add(new String[] { "总的购物次数", "orderNum"});*/
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("订单明细");
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
	//导出订单
	@Override
	public void downLoadOrderGift(Order order, HttpServletResponse response) {
		List<Map<String, Object>> list = orderMapper.downLoadOrderGift(order);
		
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "订单号", "orderNo" });
		metadata.add(new String[] { "SKU", "goodsCode" });
		metadata.add(new String[] { "赠品名称", "goodsName" });
		metadata.add(new String[] { "赠品数量", "goodsNums" });
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("订单赠品");
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
	//订单详情
	@Override
	public Order queryOrderDetailById(Integer orderId) {
		Order order = orderMapper.queryOrderDetailById(orderId);
		return order;
	}

	//查询订单中的商品
	@Override
	public List<OrderDetail> queryOrderGoodsList(Integer orderId) {
		List<OrderDetail> orderDetailList = orderMapper.queryOrderGoodsList(orderId);
		return orderDetailList;
	}

	//每日订单统计
	@Override
	public void queryOrderCount(PageInfo pageInfo, Order od) {
		Page<Order> page = new Page<Order>(pageInfo.getNowpage(), pageInfo.getSize());
        List<Order> list = orderMapper.queryOrderCount(page,od);
        List<Order> footer = new ArrayList<Order>();
        if(list != null && list.size() > 0) {
        	double total = 0.0;
        	Order foot = new Order();
        	for(Order order:list) {
        		total += order.getRealPrice();
        	}
        	foot.setDays("Total:");
        	foot.setRealPrice(total);
        	footer.add(foot);
        }
        pageInfo.setRows(list);
        pageInfo.setFooter(footer);
        pageInfo.setTotal(page.getTotal());
	}

	//导出每日订单数据
	@Override
	public void exportDayOrderExcelList(Order order, HttpServletResponse response) {
		List<Map<String, Object>> list = orderMapper.exportDayOrderExcelList(order);
		
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "日期", "days" });
		metadata.add(new String[] { "销售额", "realPrice" });
		metadata.add(new String[] { "销量", "orderQuantity" });
		metadata.add(new String[] { "订单量", "goodsNum" });
		metadata.add(new String[] { "客单价", "goodsPrice" });
		metadata.add(new String[] { "客单件", "goodsAver" });
		
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("订单统计数据(天)");
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

	//每月订单数据
	@Override
	public void queryOrderCountByMonth(PageInfo pageInfo, Order od) {
		Page<Order> page = new Page<Order>(pageInfo.getNowpage(), pageInfo.getSize());
        List<Order> list = orderMapper.queryOrderCountByMonth(page,od);
        List<Order> footer = new ArrayList<Order>();
        if(list != null && list.size() > 0) {
        	double total = 0.0;
        	Order foot = new Order();
        	for(Order order:list) {
        		total += order.getRealPrice();
        	}
        	foot.setMonths("Total:");
        	foot.setRealPrice(total);
        	footer.add(foot);
        }
        pageInfo.setRows(list);
        pageInfo.setFooter(footer);
        pageInfo.setTotal(page.getTotal());
	}

	//导出每月订单数据
	@Override
	public void exportMonthOrderExcelList(Order order, HttpServletResponse response) {
		List<Map<String, Object>> list = orderMapper.exportMonthOrderExcelList(order);
		
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "日期", "months" });
		metadata.add(new String[] { "销售额", "realPrice" });
		metadata.add(new String[] { "销量", "orderQuantity" });
		metadata.add(new String[] { "订单量", "goodsNum" });
		metadata.add(new String[] { "客单价", "goodsPrice" });
		metadata.add(new String[] { "客单件", "goodsAver" });
		
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("订单统计数据(月)");
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
	public void editStatus(OrderAddress orderAddress) {
		orderMapper.editStatus(orderAddress);
	}

	
}
