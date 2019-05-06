package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.mapper.PopConfigMapper;
import com.bluekjg.admin.mapper.StaticFilesMapper;
import com.bluekjg.admin.model.PopConfig;
import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.admin.service.IPopConfigService;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 首页弹窗配置表 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2018-10-24
 */
@Service
public class PopConfigServiceImpl extends ServiceImpl<PopConfigMapper, PopConfig> implements IPopConfigService {
	
	@Autowired 
    private PopConfigMapper popConfigMapper;
	@Autowired 
	StaticFilesMapper staticFilesMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, PopConfig g) {
		Page<PopConfig> page = new Page<PopConfig>(pageInfo.getNowpage(), pageInfo.getSize());
		List<PopConfig> list = popConfigMapper.selectDataGrid(page, g);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	@Override
	public PopConfig selectPopConfigById(Integer id) {
		// TODO Auto-generated method stub
		return popConfigMapper.selectPopConfigById(id);
	}
	@Override
	@Transactional
	public void insertPopConfig(PopConfig bean) {
		// TODO Auto-generated method stub
		popConfigMapper.insertPopConfig(bean);
		//图片一
		if(bean.getImage1() != null && bean.getImage1().length() > 0) {
			StaticFiles files = new StaticFiles();
			files.setRelationId(bean.getId());
			files.setFilePath(bean.getImage1());
			files.setFileType(0);
			files.setBigType(7);
			files.setSmallType(0);
			staticFilesMapper.insertFiles(files);
		}
		//图片二
		if(bean.getImage2() != null && bean.getImage2().length() > 0) {
			StaticFiles files = new StaticFiles();
			files.setRelationId(bean.getId());
			files.setFilePath(bean.getImage2());
			files.setFileType(0);
			files.setBigType(7);
			files.setSmallType(1);
			staticFilesMapper.insertFiles(files);
		}
	}
	@Override
	public void updatePopConfig(PopConfig bean) {
		// TODO Auto-generated method stub
		popConfigMapper.updatePopConfig(bean);
		StaticFiles files = new StaticFiles();
		files.setRelationId(bean.getId());
		//图片一
		files.setFilePath(bean.getImage1());
		files.setFileType(0);
		files.setBigType(7);
		files.setSmallType(0);
		staticFilesMapper.deleteFilesRelation(files);
		if(bean.getImage1() != null && bean.getImage1().length() > 0) {
			staticFilesMapper.insertFiles(files);
		}
		//图片二
		files.setFilePath(bean.getImage2());
		files.setFileType(0);
		files.setBigType(7);
		files.setSmallType(1);
		staticFilesMapper.deleteFilesRelation(files);
		if(bean.getImage2() != null && bean.getImage2().length() > 0) {
			staticFilesMapper.insertFiles(files);
		}
	}
	@Override
	public void updateStatus(PopConfig bean) {
		// TODO Auto-generated method stub
		popConfigMapper.updateStatus(bean);
	}

	
}
