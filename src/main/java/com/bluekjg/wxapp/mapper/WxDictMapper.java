package com.bluekjg.wxapp.mapper;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.WxDict;

/**
 * @description：数据字典表数据库控制层接口
 * @author：pincui.tom
 * @date：2018/9/27 14:51
 */
public interface WxDictMapper extends BaseMapper<WxDict> {
	
	/**
	 * 根据编码查询数据字典信息
	 * @param code
	 */
	WxDict queryDictByCode(@Param("code") String code);
}