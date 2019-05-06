package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Store;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 门店信息表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-09-30
 */
public interface StoreMapper extends BaseMapper<Store> {

	List<Store> selectDataGrid(Page<Store> page, Store store);

	Integer updateStoreById(Store store);

	Integer insertStore(Store store);

	Store selectStoreById(Integer id);

	Integer updateInfoById(Store store);
	Integer updateStoreQrCode(Store store);
	List<Store> queryStoreList();

}