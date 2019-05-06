package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Coupon;
import com.bluekjg.admin.model.ExportDto;
import com.bluekjg.admin.model.UserIssueCoupon;
import com.bluekjg.admin.model.WxUserInfo;
import com.bluekjg.wxapp.model.WxOrder;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Excel Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2018-11-9
 */
public interface ExcelMapper extends BaseMapper<ExportDto> {
	/**
	 * 查询物流订单列表
	 * @param dto
	 * @return
	 */
	List<Map<String,Object>> queryOrderGuanyi(ExportDto dto);
	/**
	 * 查询订单详情
	 * @param dto
	 * @return
	 */
	List<WxOrder> queryOrderDetail(ExportDto dto);
	/**
	 * 修改订单状态
	 * @param orderNo
	 * @return
	 */
	boolean updateOrderStatus(@Param("orderNo") String orderNo);
	/**
	 * 修改运单号
	 * @param orderNo
	 * @param expressNo
	 * @return
	 */
	boolean updateOrderExpressNo(@Param("orderNo") String orderNo,@Param("expressNo") String expressNo,@Param("expressName") String expressName);
	
	
	/**
	 * 修改积分明细状态
	 * @param id
	 * @param status
	 * @return
	 */
	Integer updateIntegralLogStatus(@Param("id") Integer id,@Param("status") Integer status);
	/**
	 * 积分失效
	 * @param userInfo
	 * @return
	 */
	Integer updateUserIntegralFail(WxUserInfo userInfo);
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