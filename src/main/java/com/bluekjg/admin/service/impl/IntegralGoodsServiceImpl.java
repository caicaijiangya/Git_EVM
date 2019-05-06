package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.IntegralGoods;
import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.admin.mapper.IntegralGoodsMapper;
import com.bluekjg.admin.mapper.StaticFilesMapper;
import com.bluekjg.admin.service.IIntegralGoodsService;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.StringUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 积分商品表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-10-07
 */
@Service
public class IntegralGoodsServiceImpl extends ServiceImpl<IntegralGoodsMapper, IntegralGoods> implements IIntegralGoodsService {

	@Autowired private IntegralGoodsMapper integralGoodsMapper;
	@Autowired private StaticFilesMapper staticFilesMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, IntegralGoods integralGoods) {
		Page<IntegralGoods> page = new Page<IntegralGoods>(pageInfo.getNowpage(),pageInfo.getSize());
		List<IntegralGoods> list = integralGoodsMapper.selectDataGrid(page,integralGoods);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	
	@Override
	public IntegralGoods selectIntegralGoodsObj(Integer id) {
		// TODO Auto-generated method stub
		return integralGoodsMapper.selectIntegralGoodsObj(id);
	}
	
	@Override
	public void updateGoodsAmount(IntegralGoods integralGoods) {
		 integralGoodsMapper.updateGoodsAmount(integralGoods);
	}

	@Override
	public void insertIntegralGoods(IntegralGoods integralGoods) {
		Integer result = integralGoodsMapper.insert(integralGoods);
		List<StaticFiles> sfList = new ArrayList<StaticFiles>();
		if (result>0) {
			integralGoodsMapper.deleteGoodsImg(integralGoods);
			integralGoodsMapper.updateGoodsAmount(integralGoods);
			if(integralGoods.getType() == 0) {
				integralGoodsMapper.editGoodsStock(integralGoods);
			}
			//获取商品详情图
			String detailImg = integralGoods.getDetailImg();
			if (!StringUtils.isEmpty(detailImg)) {
				String [] detailImgArr = detailImg.split(",");
				for(String img : detailImgArr){
					StaticFiles sf = new StaticFiles();
					sf.setFilePath(img);
					sf.setFileType(0);
					sf.setBigType(6);
					sf.setSmallType(2);
					sf.setRelationId(integralGoods.getGoodsId());
					sfList.add(sf);
				}
			}
			//获取商品轮播图
			String advertImg = integralGoods.getAdvertImg();
			if (!StringUtils.isEmpty(advertImg)) {
				String [] advertImgArr = advertImg.split(",");
				for(String img : advertImgArr){
					StaticFiles sf = new StaticFiles();
					sf.setFilePath(img);
					sf.setFileType(0);
					sf.setBigType(6);
					sf.setSmallType(1);
					sf.setRelationId(integralGoods.getGoodsId());
					sfList.add(sf);
				}
			}
			
			//获取商品缩略图
			String image = integralGoods.getImage();
			if (!StringUtils.isEmpty(image)) {
				String [] imageArr = image.split(",");
				for(String img : imageArr){
					StaticFiles sf = new StaticFiles();
					sf.setFilePath(img);
					sf.setFileType(0);
					sf.setBigType(6);
					sf.setSmallType(0);
					sf.setRelationId(integralGoods.getGoodsId());
					sfList.add(sf);
				}
			}
			if (sfList.size()>0) {
				//保存商品图片
				Integer n = staticFilesMapper.saveIntegrlGoodsImgList(sfList);
			}
		
	}
	}

	@Override
	public List<StaticFiles> queryGoodsImgList(IntegralGoods iGoods) {
		return integralGoodsMapper.queryGoodsImgList(iGoods);
	}

	@Override
	public boolean modifyGoodsImageInfo(IntegralGoods integralGoods) {
		Integer result = integralGoodsMapper.deleteGoodsImg(integralGoods);
		List<StaticFiles> sfList = new ArrayList<StaticFiles>();
		if(result>=0){  //商品添加成功
			//获取商品详情图
			String detailImg = integralGoods.getDetailImg();
			if(!StringUtils.isEmpty(detailImg)){
				String [] detailImgArr = detailImg.split(",");
				for(String img : detailImgArr){
					if(StringUtils.isEmpty(img)){
						continue;
					}
					StaticFiles sf = new StaticFiles();
					sf.setFilePath(img);
					sf.setFileType(0);
					sf.setBigType(6);
					sf.setSmallType(2);
					sf.setRelationId(integralGoods.getGoodsId());
					sfList.add(sf);
				}
			}
			//获取商品轮播图
			String advertImg = integralGoods.getAdvertImg();
			if(!StringUtils.isEmpty(advertImg)){
				String [] advertImgArr = advertImg.split(",");
				for(String img : advertImgArr){
					if(StringUtils.isEmpty(img)){
						continue;
					}
					StaticFiles sf = new StaticFiles();
					sf.setFilePath(img);
					sf.setFileType(0);
					sf.setBigType(6);
					sf.setSmallType(1);
					sf.setRelationId(integralGoods.getGoodsId());
					sfList.add(sf);
				}
			}
			//获取商品缩略图
			String image = integralGoods.getImage();
			if(!StringUtils.isEmpty(image)){
				String [] imageArr = image.split(",");
				for(String img : imageArr){
					if(StringUtils.isEmpty(img)){
						continue;
					}
					StaticFiles sf = new StaticFiles();
					sf.setFilePath(img);
					sf.setFileType(0);
					sf.setBigType(6);
					sf.setSmallType(0);
					sf.setRelationId(integralGoods.getGoodsId());
					sfList.add(sf);
				}
			}
			if(sfList.size()>0){
				//保存商品图片
				Integer batchResult = staticFilesMapper.saveIntegrlGoodsImgList(sfList);
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
	public Integer selectIntegralGoodsByGoodsId(IntegralGoods integralGoods) {
		return integralGoodsMapper.selectIntegralGoodsByGoodsId(integralGoods);
	}

	@Override
	public void editGoodsStock(IntegralGoods integralGoods) {
		integralGoodsMapper.editGoodsStock(integralGoods);
	}

	@Override
	public IntegralGoods queryGoodsAmount(IntegralGoods integralGoods) {
		return integralGoodsMapper.queryGoodsAmount(integralGoods);
	}
}
