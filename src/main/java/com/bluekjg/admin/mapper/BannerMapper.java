package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.Banner;
import com.bluekjg.admin.model.StaticFiles;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * banner管理表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
public interface BannerMapper extends BaseMapper<Banner> {

	List<Banner> selectDataGrid(Page<Banner> page, Banner banner);

	//查询该Banner所有图片
	List<StaticFiles> queryImgList(Integer id);

	//删除图片
	void deleteImg(Banner banner);

	List<Banner> searchBannerImg(Page<Banner> page, Banner banner);


}