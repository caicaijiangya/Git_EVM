package com.bluekjg.wxapp.service;


import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;

public interface IWxKeeperEmployeesService {
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
	void insertQrcode(PagingDto dto);
	
	//新增员工
	void insertUserEmp(PagingDto page);
	
	//修改用户
	boolean updateUser(UserBean user);
	
	Integer editUserType(UserBean user);
	
}
