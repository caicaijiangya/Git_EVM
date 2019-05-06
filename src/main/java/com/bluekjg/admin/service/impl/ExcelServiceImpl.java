package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Coupon;
import com.bluekjg.admin.model.ExportDto;
import com.bluekjg.admin.model.UserIssueCoupon;
import com.bluekjg.admin.model.WxUserInfo;
import com.bluekjg.admin.mapper.ExcelMapper;
import com.bluekjg.admin.service.IExcelService;
import com.bluekjg.core.commons.utils.MapTrunPojo;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxOrderDetail;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * <p>
 * Excel 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2018-11-9
 */
@Service
@Transactional
public class ExcelServiceImpl extends ServiceImpl<ExcelMapper, ExportDto> implements IExcelService {

	@Autowired ExcelMapper excelMapper;

	@Override
	public List<Map<String, Object>> queryOrderGuanyi(ExportDto dto) {
		return excelMapper.queryOrderGuanyi(dto);
	}

	@Override
	public List<Map<String, Object>> queryOrderDetailJd(ExportDto dto) {
		List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		List<WxOrder> orderList = excelMapper.queryOrderDetail(dto);
		if(orderList != null && orderList.size() > 0) {
			for(WxOrder order:orderList) {
				if(order.getDetails() != null && order.getDetails().size() > 0) {
					for(int i=0;i<order.getDetails().size();i++) {
						WxOrderDetail detail=order.getDetails().get(i);
						if(order.getAddress() != null) {
							String addressDetail = order.getAddress().getProvinceName()+order.getAddress().getCityName()+order.getAddress().getAreaName();
							order.getAddress().setAddressDetail(addressDetail+order.getAddress().getAddressDetail());
						}
						Map<String, Object> map = MapTrunPojo.object2Map(detail);
						if(i == 0) {
							Map<String, Object> address = MapTrunPojo.object2Map(order.getAddress());
							Map<String, Object> store = MapTrunPojo.object2Map(order.getStore());
							Map<String, Object> trans = MapTrunPojo.object2Map(order.getTrans());
							MapTrunPojo.mapCopy(address, map);
							MapTrunPojo.mapCopy(store, map);
							MapTrunPojo.mapCopy(trans, map);
							MapTrunPojo.mapCopy(MapTrunPojo.object2Map(order), map);
						}else {
							map.put("LastModifiedTime", order.getLastModifiedTime());
						}
						
						list.add(map);
					}
				}
			}
		}
		return list;
	}

	@Override
	@Transactional
	public boolean updateOrderExpressNo(List<List<String>> list) {
		if(list != null && list.size() > 0) {
			for(int i=1;i<list.size();i++) {
				String expressNo = list.get(i).get(2);
				String expressName = list.get(i).get(6);
				String orderNo = list.get(i).get(22);
				if(expressNo != null && expressNo.length() > 0) {
					if(expressNo != null && expressNo.length() > 0 && orderNo != null && orderNo.length() > 0) {
						excelMapper.updateOrderExpressNo(orderNo, expressNo,expressName);
						excelMapper.updateOrderStatus(orderNo);
					}else {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //事务回滚
						return false;
					}
				}
			}
		}else {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean editIntegralClear(List<List<String>> list) {
		if(list != null && list.size() > 0) {
			String openId = "";
			double toIntegral = 0.0;
			for(int i=1;i<list.size();i++) {
				String id = list.get(i).get(0);//id
				if(list.get(i).get(1) != null && list.get(i).get(1).length() > 0) {
					openId = list.get(i).get(1);//openID
				}
				String integral = list.get(i).get(12);//处理积分
				String status = list.get(i).get(16);//状态（1失效，2异常）
				if(id != null && id.length() > 0 && integral != null && integral.length() > 0 && Double.valueOf(integral) > 0) {
					if(openId != null && openId.length() > 0  && status != null && status.length() > 0) {
						Integer ret = excelMapper.updateIntegralLogStatus(Integer.valueOf(id), Integer.valueOf(status));
						if(ret != null && ret > 0) {
							toIntegral += Double.valueOf(integral);
						}
					}else {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //事务回滚
						return false;
					}
				}
			}
			WxUserInfo userInfo = new WxUserInfo();
			userInfo.setOpenId(openId);
			userInfo.setIntegral(toIntegral);
			excelMapper.updateUserIntegralFail(userInfo);
		}else {
			return false;
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> downLoadQueryCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		return excelMapper.downLoadQueryCoupon(coupon);
	}

	@Override
	public List<Map<String, Object>> downLoadUserIssueCoupon(UserIssueCoupon userIssueCoupon) {
		// TODO Auto-generated method stub
		return excelMapper.downLoadUserIssueCoupon(userIssueCoupon);
	}

}
