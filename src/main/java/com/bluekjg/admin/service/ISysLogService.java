package com.bluekjg.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.admin.model.SysLog;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ISysLogService extends IService<SysLog> {

    void selectDataGrid(PageInfo pageInfo);

}