package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.admin.mapper.StaticFilesMapper;
import com.bluekjg.admin.service.IStaticFilesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 静态资源表 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2018-09-30
 */
@Service
public class StaticFilesServiceImpl extends ServiceImpl<StaticFilesMapper, StaticFiles> implements IStaticFilesService {

	@Autowired private StaticFilesMapper staticFilesMapper;

	@Override
	public List<StaticFiles> selectFileList(StaticFiles files) {
		// TODO Auto-generated method stub
		return staticFilesMapper.selectFileList(files);
	}

	@Override
	public void insertFiles(StaticFiles files) {
		// TODO Auto-generated method stub
		staticFilesMapper.insertFiles(files);
	}

	@Override
	public void deleteFilesRelation(StaticFiles files) {
		// TODO Auto-generated method stub
		staticFilesMapper.deleteFilesRelation(files);
	}
	
	
}
