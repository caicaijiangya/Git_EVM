package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.ActivityFullgift;
import com.bluekjg.admin.model.ActivityGoods;
import com.bluekjg.admin.model.ActivityGoodsBargain;
import com.bluekjg.admin.model.ActivityGoodsBargainLadder;
import com.bluekjg.admin.model.ActivityGoodsBargainPrice;
import com.bluekjg.admin.model.ActivityGoodsCollage;
import com.bluekjg.admin.model.ExportDto;
import com.bluekjg.admin.model.StaticFiles;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 活动商品表 Mapper 接口
 * </p>
 *
 * @author Tim
 * @since 2018-10-02
 */
public interface ActivityGoodsMapper extends BaseMapper<ActivityGoods> {

	void updateGoods(ActivityGoods activityGoods);
	
	void updateStoreGoods(ActivityGoods activityGoods);
	
	void updateActivityGoods(ActivityGoods activityGoods);

	void updateActivityAmount(ActivityGoods activityGoods);

	ActivityGoods selectActivityGoodsById(Integer id);

	ActivityGoods selectActivityGoodsImgById(ActivityGoods aGoods);

	ActivityGoods selectImageById(ActivityGoods agoods);

	ActivityGoods selectLunboImgById(ActivityGoods agoods);

	ActivityGoods selectDetailImgById(ActivityGoods agoods);

	List<StaticFiles> selectImgById(ActivityGoods agoods);

	Integer deleteImg(ActivityGoods activityGoods);
	
	List<ActivityGoodsCollage> selectActivityGoodsCollage(@Param("activityGoodsId") Integer activityGoodsId);
	
	ActivityGoodsBargain selectActivityGoodsBargainById(@Param("id") Integer id);
	List<ActivityGoodsBargainLadder> selectActivityGoodsBargainLadder(@Param("bargainId") Integer bargainId);
	List<ActivityGoodsBargainPrice> selectActivityGoodsBargainPrice(@Param("bargainId") Integer bargainId);
	
	List<ActivityFullgift> selectActivityFullgift(@Param("activityId") Integer activityId,@Param("storeId") Integer storeId);
	
	boolean deleteActivityGoodsCollage(@Param("activityGoodsId") Integer activityGoodsId);
	boolean insertActivityGoodsCollage(ActivityGoodsCollage activityGoodsCollage);
	
	boolean updateActivityGoodsBargain(ActivityGoodsBargain activityGoodsBargain);
	boolean insertActivityGoodsBargain(ActivityGoodsBargain activityGoodsBargain);
	
	boolean deleteActivityGoodsBargainLadder(@Param("bargainId") Integer bargainId);
	boolean insertActivityGoodsBargainLadder(ActivityGoodsBargainLadder activityGoodsBargainLadder);
	
	boolean deleteActivityGoodsBargainPrice(@Param("bargainId") Integer bargainId);
	boolean insertActivityGoodsBargainPrice(ActivityGoodsBargainPrice activityGoodsBargainPrice);
	
	ActivityFullgift selectActivityFullgiftById(@Param("id") Integer id);
	boolean deleteActivityFullgift(@Param("id") Integer id);
	boolean insertActivityFullgift(ActivityFullgift activityFullgift);
	boolean updateActivityFullgift(ActivityFullgift activityFullgift);
	List<Map<String,Object>> downLoadBargain(ExportDto dto);
}