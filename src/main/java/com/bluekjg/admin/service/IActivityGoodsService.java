package com.bluekjg.admin.service;

import com.bluekjg.admin.model.ActivityFullgift;
import com.bluekjg.admin.model.ActivityGoods;
import com.bluekjg.admin.model.ActivityGoodsBargain;
import com.bluekjg.admin.model.ActivityGoodsBargainLadder;
import com.bluekjg.admin.model.ActivityGoodsCollage;
import com.bluekjg.admin.model.ExportDto;
import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.core.commons.result.PageInfo;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 活动商品表 服务类
 * </p>
 *
 * @author Tim
 * @since 2018-10-02
 */
public interface IActivityGoodsService extends IService<ActivityGoods> {

	void updateGoods(ActivityGoods activityGoods);

	void updateActivityGoods(ActivityGoods activityGoods);

	void updateActivityAmount(ActivityGoods activityGoods);

	ActivityGoods selectActivityGoodsById(Integer id);

	//缩略图
	ActivityGoods selectImageById(ActivityGoods agoods);

	//轮播图
	ActivityGoods selectLunboImgById(ActivityGoods agoods);

	//详情图
	ActivityGoods selectDetailImgById(ActivityGoods agoods);

	boolean modifyGoodsImageInfo(ActivityGoods activityGoods);

	List<StaticFiles> selectImgById(ActivityGoods agoods);
	
	void selectCollageDataGrid(PageInfo pageInfo,Integer activityGoodsId);
	void updateGoodsCollage(ActivityGoodsCollage activityGoodsCollage);
	
	ActivityGoodsBargain selectActivityGoodsBargainById(Integer id);
	void selectBargainLadderDataGrid(PageInfo pageInfo,Integer bargainId);
	void selectBargainPriceDataGrid(PageInfo pageInfo,Integer bargainId);
	void selectFullgiftDataGrid(PageInfo pageInfo,Integer activityId,Integer storeId);
	void updateGoodsBargain(ActivityGoodsBargain activityGoodsBargain);
	
	ActivityFullgift selectActivityFullgiftById(Integer id);
	void insertActivityFullgift(ActivityFullgift activityFullgift);
	void updateActivityFullgift(ActivityFullgift activityFullgift);
	void deleteActivityFullgift(Integer id);
	void downLoadBargain(ExportDto dto,HttpServletResponse response);
}
