package com.bluekjg.wxapp.mapper;

import java.util.List;

import com.bluekjg.wxapp.model.wxApp20.StoreDto;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxStore;

/**
 * @description：门店信息表数据库控制层接口
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
public interface WxStoreMapper extends BaseMapper<WxStore> {
	
	/**
	 * 根据用户查询门店信息
	 * @param openId
	 * @return
	 */
	WxStore queryStoreByOpenId(@Param("openId") String openId);
	/**
	 * 根据门店ID查询门店信息
	 * @param id
	 */
	WxStore queryStoreById(@Param("id") Integer id);
	
	/**
	 * 根据地市查询门店列表
	 * @param city
	 * @return
	 */
	List<WxStore> queryStoreByArea(PagingDto dto);
	List<WxStore> queryAllStore();

	List<StoreDto> queryStoreByAreaId(PagingDto dto);

	/**
	 * 通用接口：查询门店信息

	 */
	List<WxStore> queryStoreListByAreaId(Integer areaId);
	String queryCodeByAreaId(Integer areaId);
	String queryNameByAreaId(Integer areaId);

}