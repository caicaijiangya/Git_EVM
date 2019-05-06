package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.ActivityFullgift;
import com.bluekjg.admin.model.ActivityGoods;
import com.bluekjg.admin.model.ActivityGoodsBargain;
import com.bluekjg.admin.model.ActivityGoodsBargainLadder;
import com.bluekjg.admin.model.ActivityGoodsBargainPrice;
import com.bluekjg.admin.model.ActivityGoodsCollage;
import com.bluekjg.admin.model.ExportDto;
import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.admin.mapper.ActivityGoodsMapper;
import com.bluekjg.admin.mapper.StaticFilesMapper;
import com.bluekjg.admin.service.IActivityGoodsService;
import com.bluekjg.admin.upload.ExcelUtil;
import com.bluekjg.admin.upload.WriteExcelInterface;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.core.commons.utils.StringUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动商品表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-10-02
 */
@Service
public class ActivityGoodsServiceImpl extends ServiceImpl<ActivityGoodsMapper, ActivityGoods> implements IActivityGoodsService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired private ActivityGoodsMapper activityGoodsMapper;
	@Autowired private StaticFilesMapper staticFilesMapper;
	
	//修改商品的可用库存
	@Override
	public void updateGoods(ActivityGoods activityGoods) {
		if(activityGoods.getStoreId() == null || activityGoods.getStoreId() == 0) {
			activityGoodsMapper.updateGoods(activityGoods);
		}else {
			activityGoodsMapper.updateStoreGoods(activityGoods);
		}
	}

	//修改活动商品的参数
	@Override
	public void updateActivityGoods(ActivityGoods activityGoods) {
		activityGoodsMapper.updateActivityGoods(activityGoods);
	}

	//修改活动商品的库存
	@Override
	public void updateActivityAmount(ActivityGoods activityGoods) {
		activityGoodsMapper.updateActivityAmount(activityGoods);
	}

	//根据ID查询活动商品
	@Override
	public ActivityGoods selectActivityGoodsById(Integer id) {
		return activityGoodsMapper.selectActivityGoodsById(id);
	}


	//缩略图
	@Override
	public ActivityGoods selectImageById(ActivityGoods agoods) {
		return activityGoodsMapper.selectImageById(agoods);
	}

	//轮播图
	@Override
	public ActivityGoods selectLunboImgById(ActivityGoods agoods) {
		return activityGoodsMapper.selectLunboImgById(agoods);
	}

	//详情图
	@Override
	public ActivityGoods selectDetailImgById(ActivityGoods agoods) {
		return activityGoodsMapper.selectDetailImgById(agoods);
	}

	@Override
	public boolean modifyGoodsImageInfo(ActivityGoods activityGoods) {
		if (activityGoods.getActivityType()==1) {
			activityGoods.setActType(2);
		}else if (activityGoods.getActivityType()==2) {
			activityGoods.setActType(4);
		}else if (activityGoods.getActivityType()==3) {
			activityGoods.setActType(3);
		}else if (activityGoods.getActivityType()==4) {
			activityGoods.setActType(9);
		}else if (activityGoods.getActivityType()==5) {
			activityGoods.setActType(10);
		}else if (activityGoods.getActivityType()==6) {
			activityGoods.setActType(11);
		}else if (activityGoods.getActivityType()==7) {
			activityGoods.setActType(12);
		}
		Integer result = activityGoodsMapper.deleteImg(activityGoods);
		List<StaticFiles> sfList = new ArrayList<StaticFiles>();
		if(result>=0){  
			//获取商品详情图
			String detailImg = activityGoods.getDetailImg();
			if(!StringUtils.isEmpty(detailImg)){
				String [] detailImgArr = detailImg.split(",");
				for(String img : detailImgArr){
					if(StringUtils.isEmpty(img)){
						continue;
					}
					StaticFiles sf = new StaticFiles();
					sf.setFilePath(img);
					sf.setFileType(0);
					sf.setBigType(activityGoods.getActType());
					sf.setRelationId(activityGoods.getGoodsId());
					sf.setSmallType(2);
					sfList.add(sf);
				}
			}
			//获取商品轮播图
			String advertImg = activityGoods.getAdvertImg();
			if(!StringUtils.isEmpty(advertImg)){
				String [] advertImgArr = advertImg.split(",");
				for(String img : advertImgArr){
					if(StringUtils.isEmpty(img)){
						continue;
					}
					StaticFiles sf = new StaticFiles();
					sf.setFilePath(img);
					sf.setFileType(0);
					sf.setBigType(activityGoods.getActType());
					sf.setRelationId(activityGoods.getGoodsId());
					sf.setSmallType(1);
					sfList.add(sf);
				}
			}
			//获取商品缩略图
			String image = activityGoods.getImage();
			if(!StringUtils.isEmpty(image)){
				String [] imageArr = image.split(",");
				for(String img : imageArr){
					if(StringUtils.isEmpty(img)){
						continue;
					}
					StaticFiles sf = new StaticFiles();
					sf.setFilePath(img);
					sf.setFileType(0);
					sf.setBigType(activityGoods.getActType());
					sf.setRelationId(activityGoods.getGoodsId());
					sf.setSmallType(0);
					sfList.add(sf);
				}
			}
			//获取活动列表图
			String listImg = activityGoods.getListImg();
			if(!StringUtils.isEmpty(listImg)){
				StaticFiles sf = new StaticFiles();
				sf.setFilePath(listImg);
				sf.setFileType(0);
				sf.setBigType(activityGoods.getActType());
				sf.setRelationId(activityGoods.getGoodsId());
				sf.setSmallType(3);
				sfList.add(sf);
			}
			if(sfList.size()>0){
				//保存商品图片
				Integer batchResult = staticFilesMapper.saveActivityGoodsImg(sfList);
				if(batchResult!=sfList.size()){
					return false;
				}else{
					return true;
				}
			}
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<StaticFiles> selectImgById(ActivityGoods agoods) {
		return activityGoodsMapper.selectImgById(agoods);
	}
	/**
	 * ==========================拼团===========================
	 */
	@Override
	public void selectCollageDataGrid(PageInfo pageInfo, Integer activityGoodsId) {
		// TODO Auto-generated method stub
		List<ActivityGoodsCollage> list = activityGoodsMapper.selectActivityGoodsCollage(activityGoodsId);
		pageInfo.setRows(list);
	}
	
	@Override
	public void updateGoodsCollage(ActivityGoodsCollage activityGoodsCollage) {
		if(activityGoodsCollage != null) {
			//拼团阶梯
			activityGoodsMapper.deleteActivityGoodsCollage(activityGoodsCollage.getActivityGoodsId());
			if(activityGoodsCollage.getNums() != null 
					&& activityGoodsCollage.getNums().length() > 0 
					&& activityGoodsCollage.getTimes() != null 
					&& activityGoodsCollage.getTimes().length() > 0
					&& activityGoodsCollage.getPrices() != null 
					&& activityGoodsCollage.getPrices().length() > 0
					) {
				String nums[] = activityGoodsCollage.getNums().split(",");
				String times[] = activityGoodsCollage.getTimes().split(",");
				String prices[] = activityGoodsCollage.getPrices().split(",");
				if(nums.length == times.length && nums.length == prices.length) {
					for(int i=0;i<nums.length;i++) {
						ActivityGoodsCollage activityGoodsCollage_ = new ActivityGoodsCollage();
						activityGoodsCollage_.setActivityGoodsId(activityGoodsCollage.getActivityGoodsId());
						activityGoodsCollage_.setCollageNum(Integer.valueOf(nums[i]));
						activityGoodsCollage_.setCollageTime(Integer.valueOf(times[i]));
						activityGoodsCollage_.setCollagePrice(Double.valueOf(prices[i]));
						activityGoodsMapper.insertActivityGoodsCollage(activityGoodsCollage_);
					}
				}
			}
		}
	}
	/**
	 * ============================ 砍价==============================
	 */
	@Override
	public ActivityGoodsBargain selectActivityGoodsBargainById(Integer id) {
		// TODO Auto-generated method stub
		return activityGoodsMapper.selectActivityGoodsBargainById(id);
	}

	@Override
	public void updateGoodsBargain(ActivityGoodsBargain activityGoodsBargain) {
		if(activityGoodsBargain != null) {
			StaticFiles files0 = new StaticFiles();
			files0.setFilePath(activityGoodsBargain.getImage0());
			files0.setRelationId(activityGoodsBargain.getActivityGoodsId());
			files0.setFileType(0);
			files0.setBigType(8);
			files0.setSmallType(0);
			StaticFiles files1 = new StaticFiles();
			files1.setFilePath(activityGoodsBargain.getImage1());
			files1.setRelationId(activityGoodsBargain.getActivityGoodsId());
			files1.setFileType(0);
			files1.setBigType(8);
			files1.setSmallType(1);
			StaticFiles files2 = new StaticFiles();
			files2.setFilePath(activityGoodsBargain.getImage2());
			files2.setRelationId(activityGoodsBargain.getActivityGoodsId());
			files2.setFileType(0);
			files2.setBigType(8);
			files2.setSmallType(2);
			staticFilesMapper.deleteFilesRelation(files0);
			staticFilesMapper.deleteFilesRelation(files1);
			staticFilesMapper.deleteFilesRelation(files2);
			List<StaticFiles> filesList = new ArrayList<StaticFiles>();
			if(activityGoodsBargain.getImage0() != null && activityGoodsBargain.getImage0().length() > 0) {
				filesList.add(files0);
			}
			if(activityGoodsBargain.getImage1() != null && activityGoodsBargain.getImage1().length() > 0) {
				filesList.add(files1);
			}
			if(activityGoodsBargain.getImage2() != null && activityGoodsBargain.getImage2().length() > 0) {
				filesList.add(files2);
			}
			if(filesList.size() > 0)
			staticFilesMapper.saveActivityGoodsImg(filesList);
			
			boolean bool = activityGoodsMapper.updateActivityGoodsBargain(activityGoodsBargain);
			if(!bool) {
				activityGoodsMapper.insertActivityGoodsBargain(activityGoodsBargain);
			}
			//价格配置
			activityGoodsMapper.deleteActivityGoodsBargainPrice(activityGoodsBargain.getId());
			if(activityGoodsBargain.getPrices() != null
					&& activityGoodsBargain.getPrices().length() > 0
					&& activityGoodsBargain.getUseNums() != null
					&& activityGoodsBargain.getUseNums().length() > 0) {
				String prices[] = activityGoodsBargain.getPrices().split(",");
				String useNums[] = activityGoodsBargain.getUseNums().split(",");
				if(prices.length == useNums.length) {
					for(int i=0;i<prices.length;i++) {
						ActivityGoodsBargainPrice activityGoodsBargainPrice = new ActivityGoodsBargainPrice();
						activityGoodsBargainPrice.setBargainId(activityGoodsBargain.getId());
						activityGoodsBargainPrice.setPrice(Double.valueOf(prices[i]));
						activityGoodsBargainPrice.setUseNum(Integer.valueOf(useNums[i]));
						activityGoodsMapper.insertActivityGoodsBargainPrice(activityGoodsBargainPrice);
					}
				}
			}
			//砍价阶梯
			activityGoodsMapper.deleteActivityGoodsBargainLadder(activityGoodsBargain.getId());
			if(activityGoodsBargain.getBargainType() == 1) {
				if(activityGoodsBargain.getFullNums() != null 
						&& activityGoodsBargain.getFullNums().length() > 0 
						&& activityGoodsBargain.getGoodsIds() != null 
						&& activityGoodsBargain.getGoodsIds().length() > 0
						&& activityGoodsBargain.getGoodsNames() != null 
						&& activityGoodsBargain.getGoodsNames().length() > 0
						) {
					String fullNums[] = activityGoodsBargain.getFullNums().split(",");
					String goodsIds[] = activityGoodsBargain.getGoodsIds().split(",");
					String goodsNames[] = activityGoodsBargain.getGoodsNames().split(",");
					String goodsNums[] = activityGoodsBargain.getGoodsNums().split(",");
					if(fullNums.length == goodsIds.length && goodsIds.length == goodsNames.length) {
						for(int i=0;i<fullNums.length;i++) {
							ActivityGoodsBargainLadder activityGoodsBargainLadder = new ActivityGoodsBargainLadder();
							activityGoodsBargainLadder.setBargainId(activityGoodsBargain.getId());
							activityGoodsBargainLadder.setGoodsId(Integer.valueOf(goodsIds[i]));
							activityGoodsBargainLadder.setFullNum(Integer.valueOf(fullNums[i]));
							activityGoodsBargainLadder.setGoodsName(goodsNames[i]);
							activityGoodsBargainLadder.setGoodsNum(Integer.valueOf(goodsNums[i]));
							activityGoodsMapper.insertActivityGoodsBargainLadder(activityGoodsBargainLadder);
						}
					}
				}
				
				
			}
		}
	}

	@Override
	public void selectBargainLadderDataGrid(PageInfo pageInfo,Integer bargainId) {
		// TODO Auto-generated method stub
		List<ActivityGoodsBargainLadder> list = activityGoodsMapper.selectActivityGoodsBargainLadder(bargainId);
		pageInfo.setRows(list);
	}

	@Override
	public void selectBargainPriceDataGrid(PageInfo pageInfo, Integer bargainId) {
		// TODO Auto-generated method stub
		List<ActivityGoodsBargainPrice> list = activityGoodsMapper.selectActivityGoodsBargainPrice(bargainId);
		pageInfo.setRows(list);
	}
	
	@Override
	public void selectFullgiftDataGrid(PageInfo pageInfo, Integer activityId,Integer storeId) {
		Page<ActivityFullgift> page = new Page<ActivityFullgift>(pageInfo.getNowpage(),pageInfo.getSize());
		List<ActivityFullgift> list = activityGoodsMapper.selectActivityFullgift(activityId,storeId);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void downLoadBargain(ExportDto dto, HttpServletResponse response) {
		List<Map<String, Object>> list = activityGoodsMapper.downLoadBargain(dto);
		
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "会员昵称", "userName" });
		metadata.add(new String[] { "会员手机号", "mobileNo" });
		metadata.add(new String[] { "发起砍价时间", "bargainTime" });
		metadata.add(new String[] { "订单号", "orderNo" });
		metadata.add(new String[] { "订单提交时间", "orderTime" });
		metadata.add(new String[] { "支付时间", "orderTransTime" });
		metadata.add(new String[] { "商户交易号", "transNo" });
		metadata.add(new String[] { "订单状态", "orderStatus" });
		metadata.add(new String[] { "商品名称", "goodsName" });
		metadata.add(new String[] { "SKU", "goodsCode" });
		metadata.add(new String[] { "商品价格", "goodsPrice" });
		metadata.add(new String[] { "商品数量", "goodsNum" });
		metadata.add(new String[] { "活动价格", "activityPrice" });
		metadata.add(new String[] { "帮砍价格", "joinPrice" });
		metadata.add(new String[] { "实付金额", "balances" });
		metadata.add(new String[] { "助力会员昵称", "nickName" });
		metadata.add(new String[] { "助砍详情", "price" });
		metadata.add(new String[] { "新老客户","newOld"});
		
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("砍价统计");
			name.append(DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"));
			name.append(".xls");
			response.setHeader("Content-Disposition", "attachment; filename="+ new String(name.toString().getBytes(), "ISO8859-1"));
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	@Override
	public void updateActivityFullgift(ActivityFullgift activityFullgift) {
		// TODO Auto-generated method stub
		activityGoodsMapper.updateActivityFullgift(activityFullgift);
		ActivityGoods activityGoods = new ActivityGoods();
		activityGoods.setStoreId(activityFullgift.getStoreId());
		activityGoods.setGoodsId(activityFullgift.getGoodsId());
		activityGoods.setAmount(activityFullgift.getAmount() * activityFullgift.getGoodsNum());
		updateGoods(activityGoods);//修改库存
	}

	@Override
	public void insertActivityFullgift(ActivityFullgift activityFullgift) {
		// TODO Auto-generated method stub
		activityGoodsMapper.insertActivityFullgift(activityFullgift);
		ActivityGoods activityGoods = new ActivityGoods();
		activityGoods.setStoreId(activityFullgift.getStoreId());
		activityGoods.setGoodsId(activityFullgift.getGoodsId());
		activityGoods.setAmount(activityFullgift.getAmount() * activityFullgift.getGoodsNum());
		updateGoods(activityGoods);//修改库存
	}

	@Override
	public void deleteActivityFullgift(Integer id) {
		// TODO Auto-generated method stub
		ActivityFullgift fullgift = selectActivityFullgiftById(id);
		activityGoodsMapper.deleteActivityFullgift(id);
		ActivityGoods activityGoods = new ActivityGoods();
		activityGoods.setStoreId(fullgift.getStoreId());
		activityGoods.setGoodsId(fullgift.getGoodsId());
		activityGoods.setAmount((fullgift.getAmount() * fullgift.getGoodsNum())*-1);
		updateGoods(activityGoods);//修改库存
	}

	@Override
	public ActivityFullgift selectActivityFullgiftById(Integer id) {
		// TODO Auto-generated method stub
		return activityGoodsMapper.selectActivityFullgiftById(id);
	}
}
