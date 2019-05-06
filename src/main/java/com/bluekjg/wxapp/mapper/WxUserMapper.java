package com.bluekjg.wxapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.model.WxIntegralActivity;
import com.bluekjg.wxapp.model.WxIntegralLog;

/**
 * @description：用户信息表数据库控制层接口
 * @author：pincui.tom
 * @date：2018/3/27 14:51
 */
public interface WxUserMapper extends BaseMapper<UserBean> {
	
	/**
	 * 根据OpenId查询用户信息
	 * @param openId
	 * @return
	 */
	UserBean selectByOpenId(String openId);
	/**
	 * 获取门店店员信息
	 * @param storeId
	 * @return
	 */
	List<UserBean> queryWxUserInfoByStoreId(@Param("storeId") Integer storeId);
	
	/**
	 * 根据手机号查询用户信息
	 * @param mobileNo
	 * @return
	 */
	List<UserBean> selectTempUserByMobile(@Param("mobileNo") String mobileNo);
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
     * 修改业务员信息
     * @param user
     * @return
     */
    boolean updateDsr(UserBean user);
    /**
     * 修改美顾信息
     * @param user
     * @return
     */
    boolean updateBa(UserBean user);
    /**
     * 修改高管信息
     * @param user
     * @return
     */
    boolean updateLeader(UserBean user);
    /**
     * 更新用户所属门店
     * @param user
     * @return
     */
    boolean updateUserStore(UserBean user);

    //查询所有店员信息
	List<UserBean> getAssistantnList(PagingDto page);

	//删除店员
	boolean deleteAssistantn(UserBean user);
	boolean deleteModelUserByOpenId(UserBean user);

	UserBean queryWxUserInfo(@Param("openId") String openId);

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
	 * @param bean
	 * @return
	 */
	boolean insertIntegralLog(WxIntegralLog bean);
	/**
	 * 查询我的优惠券数量
	 * @param page
	 * @return
	 */
	Integer selectUserCouponCount(PagingDto page);
	/**
	 * 查询当天分享获取积分情况
	 * @param openId
	 * @param note
	 * @return
	 */
	Integer selectShareIntegralDay(@Param("openId") String openId,@Param("note") String note);
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
	WxIntegralActivity selectIntegralActivity(@Param("type") Integer type);
	/**
	 * 查询是否为新用户
	 * @param openId
	 * @return
	 */
	Integer selectUserIsNew(@Param("openId") String openId);
	Integer updateUserInvityOpenId(@Param("openId") String openId,@Param("invityOpenId") String invityOpenId);
	/**
	 * 查询是否黑名单用户
	 * @param openId
	 * @return
	 */
	UserBean queryBlacklist(@Param("openId") String openId);
}