package com.bluekjg.wxapp.service.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bluekjg.core.commons.utils.ConstantUtils;
import com.bluekjg.wxapp.mapper.WxDictMapper;
import com.bluekjg.wxapp.mapper.WxIntegralMapper;
import com.bluekjg.wxapp.mapper.WxKeeperOrderMapper;
import com.bluekjg.wxapp.mapper.WxUserMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxDict;
import com.bluekjg.wxapp.model.WxIntegralActivity;
import com.bluekjg.wxapp.model.WxIntegralLog;
import com.bluekjg.wxapp.model.wx.DataModel;
import com.bluekjg.wxapp.service.IWxOrderService;
import com.bluekjg.wxapp.service.IWxUserService;
import com.bluekjg.wxapp.utils.DictUtil;
import net.sf.json.util.JSONStringer;


/**
 * @description：名片表数据服务层接口实现类
 * @author：pincui.tom
 * @date：2018/3/27 14:51
 */
@Service
@Transactional
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, UserBean> implements IWxUserService {
	protected Logger logger = LogManager.getLogger(getClass());
    @Autowired private WxUserMapper userMapper;
    @Autowired private WxKeeperOrderMapper keeperOrderMapper;
    @Autowired private WxIntegralMapper integralMapper;
    @Autowired private WxDictMapper dictMapper;
    @Autowired
	private IWxOrderService orderService;
    
	@Override
	public UserBean selectByOpenId(String openId) {
		return userMapper.selectByOpenId(openId);
	}
	@Override
	public boolean insertUserInfo(UserBean user) {
		return userMapper.insertUserInfo(user);
	}
	@Override
	@Transactional
	public boolean updateUserInfo(UserBean user) {
		userMapper.updateUserInfo(user);
		boolean bool = true;
		return bool;
	}
	@Override
	public UserBean selectTempUserByMobile(String mobileNo) {
		List<UserBean> list = userMapper.selectTempUserByMobile(mobileNo);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	//查询所有店员信息
	@Override
	public List<UserBean> getAssistantnList(PagingDto page) {
		return userMapper.getAssistantnList(page);
	}
	
	//删除店员
	@Override
	@Transactional
	public void deleteAssistantn(UserBean user) {
		userMapper.deleteAssistantn(user);
		userMapper.deleteModelUserByOpenId(user);
	}
	
	//核销订单
	@Override
	public void writeOffData(DataModel dataModel) {
		if(dataModel.getCodeType() == 0){  //订单核销
			orderService.succeeOrder(dataModel.getDataId(), 1, dataModel.getOpenId());
		}
	}
	
	//查询登录用户信息
	@Override
	public String queryUserInfo(DataModel dataModel) {
		JSONStringer json = new JSONStringer();
		json.object();
		UserBean userBean = queryWxUserInfo(dataModel.getOpenId());
		if(userBean!=null){
			json.key("status").value(ConstantUtils.SUCCESS);
			json.key("userName").value(userBean.getUserName());
			json.key("nickName").value(userBean.getNickName());
			json.key("storeId").value(userBean.getStoreId());
			json.key("headImgUrl").value(userBean.getHeadImgUrl());
			json.key("userType").value(userBean.getUserType());
			json.key("mobileNo").value(userBean.getMobileNo());
			json.key("isAuth").value(userBean.getIsAuth());
		}else{
			json.key("status").value(ConstantUtils.NO_DATA);
			json.key("msg").value("获取信息失败!");
		}
		json.endObject();
		return json.toString();
	}
	private UserBean queryWxUserInfo(String openId) {
		UserBean userBean = userMapper.queryWxUserInfo(openId);
		return userBean;
	}
	
	//修改个人信息--pjf
	@Override
	public Integer editUserInfo(UserBean user) {
		return userMapper.editUserInfo(user);
	}
	@Override
	public List<WxIntegralLog> selectIntegralLog(PagingDto page) {
		return userMapper.selectIntegralLog(page);
	}
	@Override
	@Transactional
	public Integer insertIntegralLog(String openId,String shareOpenId,int type,int integral) {
		UserBean user = new UserBean();
		//type 类型（0签到，1兑换，2会员注册，3个人资料完善，4消费积分，5分享积分）
		String code = "";
    	String title = null;
    	if(type == 0) {
    		title = "签到获得";
    		code = DictUtil.CHECK_CODE;
    	}else if(type == 1) {
    		title = "积分兑换商品";
    	}else if(type == 2) {
    		title = "会员注册获得";
    		code = DictUtil.USER_REGISTER;
    	}else if(type == 3) {
    		title = "完善个人资料获得";
    		code = DictUtil.USER_PERFECT;
    	}else if(type == 4) {
    		title = "商城消费获得";
    		WxDict dict_mall = dictMapper.queryDictByCode(DictUtil.MALL_CONSUMPTION);
    		if(dict_mall != null) {
    			integral = integral * dict_mall.getIndexs();
    		}
    	}else if(type == 5) {
    		title = "分享获得";
    		code = DictUtil.USER_SHARE;
    		//当天分享获得积分
    		Integer shareIntegral = userMapper.selectShareIntegralDay(openId,null);
    		Integer isShareIntegral = userMapper.selectShareIntegralDay(openId,shareOpenId);
    		//同一个人当天不可重复点击，当天获得积分不得大于10
    		if((isShareIntegral != null && isShareIntegral > 0) || (shareIntegral != null &&shareIntegral >= 10)) {
    			title = null;
    		}
    	}else if(type == 6) {
    		title = "邀请好友获得";
    	}else if(type == 7) {
    		title = "积分兑换订单取消";
    	}else if(type == 10) {
    		title = "系统赠送";
    	}else {
    		title = "退货扣减";
    		user.setIsAuth(0);
    	}
    	if(title != null) {
    		WxDict dict = dictMapper.queryDictByCode(code);
        	if(dict != null && dict.getIndexs() != null) {integral = dict.getIndexs();}
        	if(integral > 0 || type == 1) {
        		WxIntegralLog log = new WxIntegralLog();
        		log.setOpenId(openId);
        		log.setIntegral(Double.valueOf(integral));
        		log.setTitle(title);
        		log.setType(type);
        		log.setNote(shareOpenId);
        		userMapper.insertIntegralLog(log);
        		
        		user.setOpenId(openId);
        		user.setIntegral(integral);
        		integralMapper.updateUserIntegral(user);
        	}
    	}
    	
		return integral;
	}
	@Override
	public Integer selectUserCouponCount(PagingDto page) {
		return userMapper.selectUserCouponCount(page);
	}
	@Override
	public List<WxIntegralLog> selectMyCheckin(PagingDto page) {
		return userMapper.selectMyCheckin(page);
	}
	@Override
	public Integer writeOffOrderStatus(Integer orderId) {
		// TODO Auto-generated method stub
		return keeperOrderMapper.queryOrderStatus(orderId);
	}
	@Override
	public List<UserBean> queryWxUserInfoByStoreId(Integer storeId) {
		// TODO Auto-generated method stub
		return userMapper.queryWxUserInfoByStoreId(storeId);
	}
	@Override
	public Integer selectUserIntegralLogCount(PagingDto page) {
		// TODO Auto-generated method stub
		return userMapper.selectUserIntegralLogCount(page);
	}
	@Override
	public WxIntegralActivity selectIntegralActivity(Integer type) {
		// TODO Auto-generated method stub
		return userMapper.selectIntegralActivity(type);
	}
	@Override
	public Integer selectUserIsNew(String openId) {
		// TODO Auto-generated method stub
		return userMapper.selectUserIsNew(openId);
	}
	@Override
	public Integer updateUserInvityOpenId(String openId, String invityOpenId) {
		// TODO Auto-generated method stub
		return userMapper.updateUserInvityOpenId(openId, invityOpenId);
	}
	@Override
	public boolean updateUserStore(UserBean user) {
		// TODO Auto-generated method stub
		return userMapper.updateUserStore(user);
	}
	@Override
	public UserBean queryBlacklist(String openId) {
		// TODO Auto-generated method stub
		return userMapper.queryBlacklist(openId);
	}
}