package com.bluekjg.admin.service;

import com.bluekjg.admin.model.Banner;
import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.core.commons.result.PageInfo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * banner管理表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
public interface IBannerService extends IService<Banner> {

	void selectDataGrid(PageInfo pageInfo, Banner banner);

	boolean insertBanner(Banner banner);
	
	//查看该banner所以图片
	List<StaticFiles> queryImgList(Integer id);

	//修改banner图片
	void modifyImgInfo(Banner banner);

	//查看banner图片
	void searchBannerImg(PageInfo pageInfo, Banner banner);

	
}
