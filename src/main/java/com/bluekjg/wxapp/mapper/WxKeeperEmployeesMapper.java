package com.bluekjg.wxapp.mapper;


import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;

/**
 * @description：营销数据数据库控制层接口
 * @author：pincui.Tom
 * @date：2018/8/16 14:51
 */
public interface WxKeeperEmployeesMapper {
	/**
	 * 查询用户二维码
	 * @param dto
	 * @return
	 */
	String queryQrcode(PagingDto dto);
	/**
	 * 新增用户二维码
	 * @param dto
	 * @return
	 */
	boolean insertQrcode(PagingDto dto);
	
	//新增用户
	boolean insertUserEmp(PagingDto page);
	
	//修改用户
	boolean updateUser(UserBean user);
	
	Integer editUserType(UserBean user);
}