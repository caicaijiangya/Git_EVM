package com.bluekjg.wxapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.WxArea;

/**
 * @description：地市表数据库控制层接口
 * @author：pincui.tom
 * @date：2018/9/27 14:51
 */
public interface WxAreaMapper extends BaseMapper<WxArea> {
	
	/**
	 * 根据编码查询地市信息
	 * @param areaNo
	 */
	WxArea queryAreaByNo(@Param("areaNo") String areaNo);
	/**
	 * 查询门店热门地市
	 * @param storeId
	 * @return
	 */
	List<WxArea> queryStoreArea();
}