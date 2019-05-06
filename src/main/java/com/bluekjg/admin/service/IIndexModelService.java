package com.bluekjg.admin.service;

import com.bluekjg.admin.model.IndexModel;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 模块管理表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
public interface IIndexModelService extends IService<IndexModel> {

	void selectDataGrid(PageInfo pageInfo, IndexModel indexModel);
	
}
