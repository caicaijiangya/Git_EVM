package com.bluekjg.wxapp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bluekjg.wxapp.mapper.WxKeeperEmployeesMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.UserBean;
import com.bluekjg.wxapp.service.IWxKeeperEmployeesService;

@Service
@Transactional
public class WxKeeperEmployeesServiceImpl implements IWxKeeperEmployeesService {
	
	@Autowired
	private WxKeeperEmployeesMapper employeesMapper;

	//查询二维码
	@Override
	public String queryQrcode(PagingDto dto) {
		return employeesMapper.queryQrcode(dto);
	}

	//新增二维码
	@Override
	public void insertQrcode(PagingDto dto) {
		employeesMapper.insertQrcode(dto);
	}

	//新增用户
	@Override
	public void insertUserEmp(PagingDto page) {
		employeesMapper.insertUserEmp(page);
	}

	//修改用户
	@Override
	public boolean updateUser(UserBean user) {
		return employeesMapper.updateUser(user);
	}

	@Override
	public Integer editUserType(UserBean user) {
		return employeesMapper.editUserType(user);
	}
	
}
