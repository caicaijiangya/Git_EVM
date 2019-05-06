package com.bluekjg.admin.service;

import com.bluekjg.admin.model.StoreArea;
import com.bluekjg.core.commons.result.PageInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 门店地市表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-10-02
 */
public interface IStoreAreaService extends IService<StoreArea> {

	void selectDataGrid(PageInfo pageInfo, StoreArea storeArea);
	
}
