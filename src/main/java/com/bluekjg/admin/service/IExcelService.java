package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Coupon;
import com.bluekjg.admin.model.ExportDto;
import com.bluekjg.admin.model.UserIssueCoupon;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * Excel 服务类
 * </p>
 *
 * @author Tom
 * @since 2018-11-9
 */
public interface IExcelService extends IService<ExportDto> {

	/**
	 * 查询订单详情
	 * @param dto
	 * @return
	 */
	List<Map<String, Object>> queryOrderGuanyi(ExportDto dto);
	
	
	/**
	 * 查询订单详情
	 * @param dto
	 * @return
	 */
	List<Map<String, Object>> queryOrderDetailJd(ExportDto dto);
	/**
	 * 批量发货
	 * @param list
	 */
	boolean updateOrderExpressNo(List<List<String>> list);
	
	
	/**
	 * 批量失效积分
	 */
	boolean editIntegralClear(List<List<String>> list);
	
	/**
	 * 优惠券查询导出
	 * @param coupon
	 * @return
	 */
	List<Map<String,Object>> downLoadQueryCoupon(Coupon coupon);
	/**
	 * 优惠券人工发放导出
	 * @param dto
	 * @return
	 */
	List<Map<String,Object>> downLoadUserIssueCoupon(UserIssueCoupon userIssueCoupon);
}
