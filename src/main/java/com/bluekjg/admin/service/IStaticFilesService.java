package com.bluekjg.admin.service;

import com.bluekjg.admin.model.StaticFiles;
import java.util.List;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 静态资源表 服务类
 * </p>
 *
 * @author Tom
 * @since 2019-01-03
 */
public interface IStaticFilesService extends IService<StaticFiles> {

	List<StaticFiles> selectFileList(StaticFiles files);
	void insertFiles(StaticFiles files);
	void deleteFilesRelation(StaticFiles files);
}
