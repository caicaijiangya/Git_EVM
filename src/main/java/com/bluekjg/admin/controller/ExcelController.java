package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bluekjg.admin.model.Coupon;
import com.bluekjg.admin.model.ExportDto;
import com.bluekjg.admin.model.UserIssueCoupon;
import com.bluekjg.admin.model.execl.ExcelBean;
import com.bluekjg.admin.service.IExcelService;
import com.bluekjg.admin.service.IUserIssueCouponService;
import com.bluekjg.admin.upload.ExcelUtil;
import com.bluekjg.admin.upload.ImportExecl;
import com.bluekjg.admin.upload.WriteExcelInterface;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.utils.ConvertBean;
import com.bluekjg.core.commons.utils.DateUtil;

/**
 * <p>
 * Excel 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2018-11-9
 */
@Controller
@RequestMapping("/excel")
public class ExcelController extends BaseController {

    @Autowired private IExcelService excelService;
    @Autowired 
    private IUserIssueCouponService userIssueCouponService;
    
    
    /**
     * 管易订单（物流配送）
     * @param 
     * @return
     */
    @GetMapping("/guanyi")
    @ResponseBody
    public void guanyi(@Valid ExportDto dto,HttpServletRequest request,HttpServletResponse response) {
    	dto.setOrderNo(ConvertBean.twoTimesDecode(dto.getOrderNo()));
    	dto.setStartDate(ConvertBean.twoTimesDecode(dto.getStartDate()));
    	dto.setEndDate(ConvertBean.twoTimesDecode(dto.getEndDate()));
    	dto.setTakeStyle(0);//物流订单
    	dto.setStatus(1);
    	List<Map<String, Object>> list = excelService.queryOrderGuanyi(dto);
    	String metadataKey[] = dto.getMetadata().split(",");
		List<String[]> metadata = new ArrayList<String[]>();
		if(metadataKey != null && metadataKey.length > 0) {
			for(String key:metadataKey) {
				metadata.add(new String[] { ExcelBean.EXCEL_GUANYI_MAP.get(key), key });
			}
		}
		/*List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "订单编号", "orderNo" });
		metadata.add(new String[] { "商户交易号", "transNo"});
		metadata.add(new String[] { "订单时间", "createTime"});
		metadata.add(new String[] { "订单状态", "orderStatus"});
		metadata.add(new String[] { "买家会员", "userName" });
		metadata.add(new String[] { "支付金额", "balances" });
		metadata.add(new String[] { "商品名称", "goodsName" });
		metadata.add(new String[] { "商品代码", "goodsCode" });
		metadata.add(new String[] { "是否赠品", "isGift" });
		metadata.add(new String[] { "规格代码", "goodsCode" });
		metadata.add(new String[] { "规格名称", "specName" });
		metadata.add(new String[] { "数量", "goodsNum" });
		metadata.add(new String[] { "单价", "goodsPrice" });
		metadata.add(new String[] { "商品备注", "" });
		metadata.add(new String[] { "运费", "freight" });
		metadata.add(new String[] { "买家留言", "orderDesc" });
		metadata.add(new String[] { "收货人", "name" });
		metadata.add(new String[] { "联系电话", "mobileNo" });
		metadata.add(new String[] { "联系手机", "mobileNo" });
		metadata.add(new String[] { "收货地址", "addressDetail" });
		metadata.add(new String[] { "省", "" });
		metadata.add(new String[] { "市", "" });
		metadata.add(new String[] { "区", "" });
		metadata.add(new String[] { "邮编", "" });
		metadata.add(new String[] { "订单创建时间", "" });
		metadata.add(new String[] { "订单付款时间", "" });
		metadata.add(new String[] { "发货时间", "" });
		metadata.add(new String[] { "物流单号", "" });
		metadata.add(new String[] { "物流公司", "" });
		metadata.add(new String[] { "卖家备注", "" });
		metadata.add(new String[] { "发票种类", "" });
		metadata.add(new String[] { "发票类型", "" });
		metadata.add(new String[] { "发票抬头", "" });
		metadata.add(new String[] { "纳税人识别号", "" });
		metadata.add(new String[] { "开户行", "" });
		metadata.add(new String[] { "账号", "" });
		metadata.add(new String[] { "地址", "" });
		metadata.add(new String[] { "电话", "" });
		metadata.add(new String[] { "是否手机订单", "" });
		metadata.add(new String[] { "是否到货付款", "" });
		metadata.add(new String[] { "支付方式", "" });
		metadata.add(new String[] { "支付交易号", "" });
		metadata.add(new String[] { "真实姓名", "" });
		metadata.add(new String[] { "身份证号", "" });
		metadata.add(new String[] { "仓库名称", "" });
		metadata.add(new String[] { "预计发货时间", "" });
		metadata.add(new String[] { "预计送达时间", "" });
		metadata.add(new String[] { "订单类型", "" });
		metadata.add(new String[] { "是否分销商订单", "" });*/
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("物流配送订单(管易)");
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
    
    /**
     * 金蝶订单（到店取货）
     * @param 
     * @return
     */
    @GetMapping("/jindie")
    @ResponseBody
    public void jindie(@Valid ExportDto dto,HttpServletRequest request,HttpServletResponse response) {
    	dto.setTakeStyle(1);//到店取货
    	dto.setStatus(6);
    	List<Map<String, Object>> list = excelService.queryOrderDetailJd(dto);
    	String metadataKey[] = dto.getMetadata().split(",");
		List<String[]> metadata = new ArrayList<String[]>();
		if(metadataKey != null && metadataKey.length > 0) {
			for(String key:metadataKey) {
				metadata.add(new String[] { ExcelBean.EXCEL_JINDIE_MAP.get(key), key });
			}
		}
		/*metadata.add(new String[] { "日期", "createTime" });
		metadata.add(new String[] { "购货单位", "" });
		metadata.add(new String[] { "销售方式", "" });
		metadata.add(new String[] { "编号", "orderNo" });
		metadata.add(new String[] { "币种", "" });
		metadata.add(new String[] { "交货地点", "storeName" });
		metadata.add(new String[] { "部门", "" });
		metadata.add(new String[] { "业务员", "" });
		metadata.add(new String[] { "结算方式", "" });
		metadata.add(new String[] { "汇率", "" });
		metadata.add(new String[] { "结算日期", "LastModifiedTime" });
		metadata.add(new String[] { "产品代码", "goodsCode" });
		metadata.add(new String[] { "单位", "" });
		metadata.add(new String[] { "数量", "goodsNum" });
		metadata.add(new String[] { "含税单价", "goodsPrice" });
		metadata.add(new String[] { "建议交货日期", "LastModifiedTime" });
		metadata.add(new String[] { "备注", "" });
		metadata.add(new String[] { "税率(%)", "" });
		metadata.add(new String[] { "交货日期", "LastModifiedTime" });*/
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("到店取货订单(金蝶)");
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
    
    
    
    
    
    /**
     * 订单批量发货
     * @param 
     * @return
     */
    @PostMapping("/orderDelivery")
    @ResponseBody
    public Object orderDelivery(@Valid MultipartFile orderExcel) {
        Object obj = renderError("批量发货失败");
    	try {
    		InputStream is = orderExcel.getInputStream();
    		Workbook wb = WorkbookFactory.create(is);
    		ImportExecl ie = new ImportExecl();
    		List<List<String>> list = ie.read(wb);
    		boolean bool = excelService.updateOrderExpressNo(list);
    		if(bool) {
    			obj = renderSuccess("批量发货成功");
    		}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    /**
     * 优惠券批量发放
     * @param 
     * @return
     */
    @PostMapping("/issueCoupon")
    @ResponseBody
    public Object issueCoupon(@Valid MultipartFile userIssueCouponExcel,Integer userNum,Integer couponId) {
        Object obj = renderError("批量发货失败");
    	try {
    		InputStream is = userIssueCouponExcel.getInputStream();
    		Workbook wb = WorkbookFactory.create(is);
    		ImportExecl ie = new ImportExecl();
    		List<List<String>> list = ie.read(wb);
    		List<String> openIdList = new ArrayList<String>();
    		if(list != null && list.size() > 0) {
    			for(int i=0;i<list.size();i++) {
    				if(i > 0) {
    					openIdList.add(list.get(i).get(0));
    				}
    			}
    		}
    		Long userId = getUserId();
        	userIssueCouponService.insertUserIssueCoupon(openIdList,userNum,couponId,userId);
        	obj = renderSuccess("批量发放成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    /**
     * 批量失效积分
     * @param 
     * @return
     */
    @PostMapping("/integralClear")
    @ResponseBody
    public Object integralClear(@Valid MultipartFile integralExcel) {
        Object obj = renderError("批量失效失败");
    	try {
    		InputStream is = integralExcel.getInputStream();
    		Workbook wb = WorkbookFactory.create(is);
    		ImportExecl ie = new ImportExecl();
    		List<List<String>> list = ie.read(wb);
    		boolean bool = excelService.editIntegralClear(list);
    		if(bool) {
    			obj = renderSuccess("批量失效成功");
    		}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    
    /**
     * 优惠券查询导出
     * @param 
     * @return
     */
    @GetMapping("/couponQuery")
    @ResponseBody
    public void couponQuery(@Valid Coupon coupon,HttpServletRequest request,HttpServletResponse response) {
    	if(coupon.getTitle() != null) {
    		coupon.setTitle(ConvertBean.twoTimesDecode(coupon.getTitle()));
    	}
    	if(coupon.getCreatedTime() != null) {
    		coupon.setCreatedTime(ConvertBean.twoTimesDecode(coupon.getCreatedTime()));
    	}
    	if(coupon.getCouponName() != null) {
    		coupon.setCouponName(ConvertBean.twoTimesDecode(coupon.getCouponName()));
    	}
    	List<Map<String, Object>> list = excelService.downLoadQueryCoupon(coupon);
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "会员昵称", "nickName" });
		metadata.add(new String[] { "会员姓名", "userName" });
		metadata.add(new String[] { "会员手机号", "mobileNo" });
		metadata.add(new String[] { "会员类型", "userType" });
		metadata.add(new String[] { "会员卡号", "" });
		metadata.add(new String[] { "新老客户", "newOld" });
		metadata.add(new String[] { "所属门店", "storeName" });
		metadata.add(new String[] { "发券时间", "issueTime" });
		metadata.add(new String[] { "券来源", "source" });
		metadata.add(new String[] { "优惠券名称", "title" });
		metadata.add(new String[] { "优惠金额", "moneyName" });
		metadata.add(new String[] { "满额条件", "fullMoney" });
		metadata.add(new String[] { "优惠券号", "couponNo" });
		metadata.add(new String[] { "优惠券状态", "couponStatus" });
		metadata.add(new String[] { "开始时间", "startTime" });
		metadata.add(new String[] { "结束时间", "endTime" });
		metadata.add(new String[] { "是否使用", "statusName" });
		metadata.add(new String[] { "使用时间", "createdTime" });
		metadata.add(new String[] { "使用门店", "useStoreName" });
		metadata.add(new String[] { "使用订单号", "orderNo" });
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("优惠券查询");
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
    
    /**
     * 优惠券领取详情导出
     * @param 
     * @return
     */
    @GetMapping("/couponDetail")
    @ResponseBody
    public void couponDetail(@Valid Coupon coupon,HttpServletRequest request,HttpServletResponse response) {
    	List<Map<String, Object>> list = excelService.downLoadQueryCoupon(coupon);
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "发券时间", "issueTime" });
		metadata.add(new String[] { "会员昵称", "nickName" });
		metadata.add(new String[] { "会员姓名", "userName" });
		metadata.add(new String[] { "会员手机号", "mobileNo" });
		metadata.add(new String[] { "会员类型", "userType" });
		metadata.add(new String[] { "会员卡号", "" });
		metadata.add(new String[] { "新老客户", "newOld" });
		metadata.add(new String[] { "所属门店", "storeName" });
		metadata.add(new String[] { "优惠券名称", "title" });
		metadata.add(new String[] { "优惠金额", "moneyName" });
		metadata.add(new String[] { "满额条件", "fullMoney" });
		metadata.add(new String[] { "优惠券号", "couponNo" });
		metadata.add(new String[] { "优惠券状态", "couponStatus" });
		metadata.add(new String[] { "开始时间", "startTime" });
		metadata.add(new String[] { "结束时间", "endTime" });
		metadata.add(new String[] { "是否使用", "statusName" });
		metadata.add(new String[] { "使用时间", "createdTime" });
		metadata.add(new String[] { "使用门店", "useStoreName" });
		metadata.add(new String[] { "使用订单号", "orderNo" });
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("优惠券领取详情");
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
    
    
    /**
     * 优惠券活动导出
     * @param 
     * @return
     */
    @GetMapping("/couponActivity")
    @ResponseBody
    public void couponActivity(@Valid Coupon coupon,HttpServletRequest request,HttpServletResponse response) {
    	coupon.setSourceType(coupon.getSourceType() + 2);
    	List<Map<String, Object>> list = excelService.downLoadQueryCoupon(coupon);
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "发券时间", "issueTime" });
		metadata.add(new String[] { "会员昵称", "nickName" });
		metadata.add(new String[] { "会员姓名", "userName" });
		metadata.add(new String[] { "会员手机号", "mobileNo" });
		metadata.add(new String[] { "会员类型", "userType" });
		metadata.add(new String[] { "会员卡号", "" });
		metadata.add(new String[] { "新老客户", "newOld" });
		metadata.add(new String[] { "所属门店", "storeName" });
		metadata.add(new String[] { "优惠券名称", "title" });
		metadata.add(new String[] { "优惠金额", "moneyName" });
		metadata.add(new String[] { "满额条件", "fullMoney" });
		metadata.add(new String[] { "优惠券号", "couponNo" });
		metadata.add(new String[] { "优惠券状态", "couponStatus" });
		metadata.add(new String[] { "开始时间", "startTime" });
		metadata.add(new String[] { "结束时间", "endTime" });
		metadata.add(new String[] { "是否使用", "statusName" });
		metadata.add(new String[] { "使用时间", "createdTime" });
		metadata.add(new String[] { "使用门店", "useStoreName" });
		metadata.add(new String[] { "使用订单号", "orderNo" });
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("优惠券活动");
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
    
    /**
     * 优惠券人工发放导出
     * @param 
     * @return
     */
    @GetMapping("/userIssueConpon")
    @ResponseBody
    public void userIssueConpon(@Valid UserIssueCoupon userIssueCoupon,HttpServletRequest request,HttpServletResponse response) {
    	if(userIssueCoupon.getTitle() != null) {
    		userIssueCoupon.setTitle(ConvertBean.twoTimesDecode(userIssueCoupon.getTitle()));
    	}
    	if(userIssueCoupon.getCreatedTime() != null) {
    		userIssueCoupon.setCreatedTime(ConvertBean.twoTimesDecode(userIssueCoupon.getCreatedTime()));
    	}
    	List<Map<String, Object>> list = excelService.downLoadUserIssueCoupon(userIssueCoupon);
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "优惠券名称", "title" });
		metadata.add(new String[] { "会员昵称", "nickName" });
		metadata.add(new String[] { "会员姓名", "userName" });
		metadata.add(new String[] { "会员手机号", "mobileNo" });
		metadata.add(new String[] { "会员类型", "userType" });
		metadata.add(new String[] { "会员卡号", "" });
		metadata.add(new String[] { "新老客户", "newOld" });
		metadata.add(new String[] { "所属门店", "storeName" });
		metadata.add(new String[] { "成功数量", "successNum" });
		metadata.add(new String[] { "失败数量", "errorNum" });
		metadata.add(new String[] { "发券总数量", "userNum" });
		metadata.add(new String[] { "创建时间", "createdTime" });
		metadata.add(new String[] { "记录创建人", "name" });
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("优惠券人工发放");
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
