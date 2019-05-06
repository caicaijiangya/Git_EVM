package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Banner;
import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.admin.mapper.BannerMapper;
import com.bluekjg.admin.mapper.StaticFilesMapper;
import com.bluekjg.admin.service.IBannerService;
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
 * 模块管理表 服务实现类
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {

	@Autowired private BannerMapper bannerMapper;
	@Autowired private StaticFilesMapper staticFilesMapper;

	@Override
	public void selectDataGrid(PageInfo pageInfo, Banner banner) {
		Page<Banner> page = new Page<Banner>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Banner> list = bannerMapper.selectDataGrid(page,banner);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public boolean insertBanner(Banner banner) {
		Integer result = bannerMapper.insert(banner);
		List<StaticFiles> sfList = new ArrayList<StaticFiles>();
		if (result>0) {
			//获取图片
			String filePath = banner.getFilePath();
			String fileSeq = banner.getFileSeq();
			if (!StringUtils.isEmpty(filePath)) {
				String [] imageArr = filePath.split(",");
				String [] imageSeq = fileSeq.split(",");
				for(int i=0;i<imageArr.length;i++){
					StaticFiles sf = new StaticFiles();
					sf.setFilePath(imageArr[i]);
					sf.setFileType(0);
					sf.setBigType(5);
					sf.setSmallType(0);
					sf.setRelationId(banner.getId());
					sf.setSeq(Integer.valueOf(imageSeq[i]));
					sfList.add(sf);
				}
			}
			if (sfList.size()>0) {
				//保存图片
				Integer n = staticFilesMapper.saveImgList(sfList);
				if (n!=sfList.size()) {
					return false;
				}else {
					return true;
				}
			}
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<StaticFiles> queryImgList(Integer id) {
		return bannerMapper.queryImgList(id);
	}

	@Override
	public void modifyImgInfo(Banner banner) {
		bannerMapper.deleteImg(banner);
		List<StaticFiles> sfList = new ArrayList<StaticFiles>();
		//获取轮播图
		String filePath = banner.getFilePath();
		String fileSeq = banner.getFileSeq();
		if(!StringUtils.isEmpty(filePath)){
			String [] imageArr = filePath.split(",");
			String [] imageSeq = fileSeq.split(",");
			for(int i=0;i<imageArr.length;i++){
				if(StringUtils.isEmpty(imageArr[i])){
					continue;
				}
				StaticFiles sf = new StaticFiles();
				sf.setFilePath(imageArr[i]);
				sf.setFileType(0);
				sf.setBigType(5);
				sf.setSmallType(0);
				sf.setRelationId(banner.getId());
				sf.setSeq(Integer.valueOf(imageSeq[i]));
				sfList.add(sf);
			}
		}
		//保存商品图片
		staticFilesMapper.saveImgList(sfList);
	}

	@Override
	public void searchBannerImg(PageInfo pageInfo, Banner banner) {
		Page<Banner> page = new Page<Banner>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Banner> list = bannerMapper.searchBannerImg(page,banner);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	
}
