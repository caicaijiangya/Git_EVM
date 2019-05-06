package com.bluekjg.wxapp.service;

import java.util.List;
import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxIntegralActivity;
import com.bluekjg.wxapp.model.WxIntegralLog;
import com.bluekjg.wxapp.model.wx.DataModel;

/**
 * @description：用户信息表数据服务层接口
 * @author：pincui.tom
 * @date：2018/3/27 14:51
 */
public interface IWxUserService extends IService<UserBean> {
	/**
	 * 订单状态
	 * @param id
	 * @return
	 */
	Integer writeOffOrderStatus(Integer orderId);
	/**
	 * 获取门店店员信息
	 * @param storeId
	 * @return
	 */
	List<UserBean> queryWxUserInfoByStoreId(Integer storeId);
	/**
	 * 根据OpenId查询用户信息
	 * @param openId
	 * @return
	 */
	UserBean selectByOpenId(String openId);
	
	/**
	 * 根据手机号查询用户信息
	 * @param mobileNo
	 * @return
	 */
	UserBean selectTempUserByMobile(String mobileNo);
    /**
     * 新增用户
     * @param user
     * @return
     */
    boolean insertUserInfo(UserBean user);
    /**
     * 修改用户信息
     * @param user
     * @return
     */
    boolean updateUserInfo(UserBean user);
    /**
     * 更新用户所属门店
     * @param user
     * @return
     */
    boolean updateUserStore(UserBean user);

    //查询所有店员信息
	List<UserBean> getAssistantnList(PagingDto page);

	//删除店员
	void deleteAssistantn(UserBean user);

	//核销订单
	void writeOffData(DataModel dataModel);

	//获取登录用户信息
	String queryUserInfo(DataModel dataModel);

	//修改个人信息---pjf
	Integer editUserInfo(UserBean user);


	/**
	 * 查询积分明细
	 * @param openId
	 * @return
	 */
	List<WxIntegralLog> selectIntegralLog(PagingDto page);
	/**
	 * 查询当月签到记录
	 * @param page
	 * @return
	 */
	List<WxIntegralLog> selectMyCheckin(PagingDto page);
	/**
	 * 新增积分
	 * @param openId 
	 * @param shareOpenId 分享点击人
	 * @param type 类型（0签到，1兑换，2会员注册，3个人资料完善，4消费积分，5分享积分）
	 * @param integral 积分
	 */
	Integer insertIntegralLog(String openId,String shareOpenId,int type,int integral);
	/**
	 * 查询我的优惠券数量
	 * @param page
	 * @return
	 */
	Integer selectUserCouponCount(PagingDto page);
	
	/**
	 * 查询积分记录
	 * @param page
	 * @return
	 */
	Integer selectUserIntegralLogCount(PagingDto page);
	/**
	 * 查询积分规则
	 * @param type
	 */
	WxIntegralActivity selectIntegralActivity(Integer type);
	
	/**
	 * 查询是否为新用户
	 * @param openId
	 * @return
	 */
	Integer selectUserIsNew(String openId);
	Integer updateUserInvityOpenId(String openId,String invityOpenId);
	/**
	 * 查询是否黑名单用户
	 * @param openId
	 * @return
	 */
	UserBean queryBlacklist(String openId);
}