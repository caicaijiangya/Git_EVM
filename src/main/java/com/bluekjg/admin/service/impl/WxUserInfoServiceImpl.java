package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.IndexModel;
import com.bluekjg.admin.model.WxUserInfo;
import com.bluekjg.admin.mapper.WxUserInfoMapper;
import com.bluekjg.admin.service.IWxUserInfoService;
import com.bluekjg.admin.upload.ExcelUtil;
import com.bluekjg.admin.upload.WriteExcelInterface;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.core.commons.utils.StringUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Max
 * @since 2018-03-30
 */
@Service
public class WxUserInfoServiceImpl extends ServiceImpl<WxUserInfoMapper, WxUserInfo> implements IWxUserInfoService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired 
    private WxUserInfoMapper wxUserInfoMapper;
	
	@Override
	public void queryUserInfoList(PageInfo pageInfo, WxUserInfo userInfo) {
		Page<WxUserInfo> page = new Page<WxUserInfo>(pageInfo.getNowpage(), pageInfo.getSize());
		List<WxUserInfo> list = wxUserInfoMapper.queryUserInfoList(page, userInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	//查询店主未赋予的操作权限模块
	@Override
	public void queryModelList(PageInfo pageInfo, WxUserInfo wxUserInfo) {
		Page<WxUserInfo> page = new Page<WxUserInfo>(pageInfo.getNowpage(), pageInfo.getSize());
		List<WxUserInfo> list = wxUserInfoMapper.queryModelList(page, wxUserInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	//确认赋予权限
	@Override
	public boolean addModel(IndexModel indexModel) {
		String openId = indexModel.getOpenId();
		String modelIds = indexModel.getModelIds();
		List<Integer> modelIdList = StringUtils.integerConvertToList(modelIds);
		List<IndexModel> imList = new ArrayList<IndexModel>();
		if (modelIdList!=null && modelIdList.size()>0) {
			for(int i=0;i<modelIdList.size();i++){
				IndexModel iModel = new IndexModel();
				iModel.setOpenId(openId);
				iModel.setModelId(modelIdList.get(i));
				imList.add(iModel);
			}
		}
		if (imList!=null && imList.size()>0) {
			//批量增加数据
			Integer n = wxUserInfoMapper.addModelUser(imList);
			if (n==imList.size()) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void queryModelUserList(PageInfo pageInfo, WxUserInfo wxUserInfo) {
		Page<WxUserInfo> page = new Page<WxUserInfo>(pageInfo.getNowpage(), pageInfo.getSize());
		List<WxUserInfo> list = wxUserInfoMapper.queryModelUserList(page, wxUserInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public boolean deleteByIds(IndexModel indexModel) {
		String ids=indexModel.getIds();
		List<Integer> idList =  StringUtils.integerConvertToList(ids);
		Integer n=0;
		if (idList != null && idList.size()>0) {
				n=wxUserInfoMapper.deleteByIds(idList);
		}
		if (n==idList.size()) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void downLoadUserInfo(WxUserInfo wxUserInfo, HttpServletResponse response) {
		List<Map<String, Object>> list = wxUserInfoMapper.downLoadUserInfo(wxUserInfo);
		
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "编号", "code" });
		metadata.add(new String[] { "OPENID" ,"openId" });
		metadata.add(new String[] { "所属门店", "storeName" });
		metadata.add(new String[] { "用户姓名", "userName" });
		metadata.add(new String[] { "用户昵称", "nickName" });
		metadata.add(new String[] { "手机号", "mobileNo" });
		metadata.add(new String[] { "用户积分", "integral" });
		metadata.add(new String[] { "所在地市", "country" });
		metadata.add(new String[] { "性别", "sex" });
		metadata.add(new String[] { "用户类型", "userType" });
		metadata.add(new String[] { "创建日期", "createdTime" });
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("用户");
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

	@Override
	public void queryUserBlacklistDataGrid(PageInfo pageInfo, WxUserInfo userInfo) {
		Page<WxUserInfo> page = new Page<WxUserInfo>(pageInfo.getNowpage(), pageInfo.getSize());
		List<WxUserInfo> list = wxUserInfoMapper.queryUserBlacklistDataGrid(page, userInfo);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public Integer queryUserBlacklistById(String openId) {
		// TODO Auto-generated method stub
		return wxUserInfoMapper.queryUserBlacklistById(openId);
	}

	@Override
	public void deleteUserBlacklist(String openId) {
		wxUserInfoMapper.deleteUserBlacklist(openId);
	}

	@Override
	public void insertUserBlacklist(WxUserInfo userInfo) {
		wxUserInfoMapper.insertUserBlacklist(userInfo);
	}
}
