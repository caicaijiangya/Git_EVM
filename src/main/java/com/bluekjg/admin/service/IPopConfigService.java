package com.bluekjg.admin.service;

import com.bluekjg.admin.model.PopConfig;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 首页弹窗配置表 服务类
 * </p>
 *
 * @author Tom
 * @since 2018-10-24
 */
public interface IPopConfigService extends IService<PopConfig> {

	void selectDataGrid(PageInfo pageInfo, PopConfig g);
	void insertPopConfig(PopConfig bean);
	void updatePopConfig(PopConfig bean);
	void updateStatus(PopConfig bean);
	PopConfig selectPopConfigById(Integer id);
}
