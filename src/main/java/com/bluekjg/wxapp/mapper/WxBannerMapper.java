package com.bluekjg.wxapp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.WxBanner;
import com.bluekjg.wxapp.model.WxStaticFile;

/**
 * @description：BANNER 数据库控制层接口
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
public interface WxBannerMapper extends BaseMapper<WxBanner> {
	/**
	 * 查询BANNER列表
	 * @param page
	 * @return
	 */
	List<WxBanner> queryBannerList(@Param("type") Integer type);
	/**
	 * 查询静态文件
	 * @param id
	 * @param fileType
	 * @param bigType
	 * @param smallType
	 * @return
	 */
	List<WxStaticFile> queryFiles(@Param("id") Integer id,@Param("fileType") Integer fileType,@Param("bigType") Integer bigType,@Param("smallType") Integer smallType);
}