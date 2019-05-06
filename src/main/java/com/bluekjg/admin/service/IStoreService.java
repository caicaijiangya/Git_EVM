package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Store;
import com.bluekjg.core.commons.result.PageInfo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 门店信息表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-09-30
 */
public interface IStoreService extends IService<Store> {

	void selectDataGrid(PageInfo pageInfo, Store store);

	Integer updateStoreById(Store store);

	Integer insertStore(Store store);

	Store selectStoreById(Integer id);

	Integer updateInfoById(Store store);
	void updateStoreQrCode(Store store);
	List<Store> queryStoreList();
	
	void queryStoreDataGrid(PageInfo pageInfo);
}
