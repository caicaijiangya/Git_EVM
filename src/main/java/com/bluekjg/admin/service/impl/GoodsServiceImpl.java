package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.GoodsClassify;
import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.admin.model.Store;
import com.bluekjg.admin.mapper.GoodsMapper;
import com.bluekjg.admin.mapper.StaticFilesMapper;
import com.bluekjg.admin.service.IGoodsService;
import com.bluekjg.admin.upload.ExcelUtil;
import com.bluekjg.admin.upload.WriteExcelInterface;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.DateUtil;
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
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品主表 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2018-09-25
 */
@Service
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired GoodsMapper goodsMapper;
	@Autowired StaticFilesMapper staticFilesMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, Goods g) {
		Page<Goods> page = new Page<Goods>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Goods> list = goodsMapper.selectDataGrid(page,g);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	@Override
	public List<GoodsClassify> selectClassifyList() {
		// TODO Auto-generated method stub
		return goodsMapper.selectClassifyList();
	}
	@Override
	public List<GoodsClassify> selectBrandList() {
		return goodsMapper.selectBrandList();
	}

	@Override
	@Transactional
	public void insertGoods(Goods g) {
		String[] labels = null;
		String[] classifys = null;
		String[] detailImg = null;
		String[] advertImg = null;
		if(g.getLabels() != null && g.getLabels().length() > 0) {
			labels = g.getLabels().split(",");
		}
		if(g.getClassifys() != null && g.getClassifys().length() > 0) {
			classifys = g.getClassifys().split(",");
		}
		if(g.getDetailImg() != null && g.getDetailImg().length() > 0) {
			detailImg = g.getDetailImg().split(",");
		}
		if(g.getAdvertImg() != null && g.getAdvertImg().length() > 0) {
			advertImg = g.getAdvertImg().split(",");
		}
		//商品
		goodsMapper.insertGoods(g);
		//商品标签
		if(labels != null) {
			for(int i=0;i<labels.length ;i++) {
				GoodsClassify bean = new GoodsClassify();
				bean.setSeq(i);
				bean.setName(labels[i]);
				bean.setGoodsId(g.getId());
				goodsMapper.insertGoodsLabel(bean);
			}
		}
		//商品分类
		if(classifys != null) {
			for(int i=0;i<classifys.length;i++) {
				goodsMapper.insertGoodsClassifyDz(g.getId(), Integer.valueOf(classifys[i]));
			}
		}
		//商品视频
		if(g.getGoodsVideo() != null && g.getGoodsVideo().length() > 0) {
			StaticFiles files = new StaticFiles();
			files.setRelationId(g.getId());
			files.setFilePath(g.getGoodsVideo());
			files.setFileType(1);
			files.setBigType(0);
			files.setSmallType(0);
			staticFilesMapper.insertFiles(files);
		}
		//商品缩略图
		if(g.getGoodsImage() != null && g.getGoodsImage().length() > 0) {
			StaticFiles files = new StaticFiles();
			files.setRelationId(g.getId());
			files.setFilePath(g.getGoodsImage());
			files.setFileType(0);
			files.setBigType(0);
			files.setSmallType(0);
			staticFilesMapper.insertFiles(files);
		}
		//商品轮播图
		if(advertImg != null) {
			for(int i=0;i<advertImg.length;i++) {
				StaticFiles files = new StaticFiles();
				files.setRelationId(g.getId());
				files.setFilePath(advertImg[i]);
				files.setFileType(0);
				files.setBigType(0);
				files.setSmallType(1);
				files.setSeq(i);
				staticFilesMapper.insertFiles(files);
			}
		}
		//商品详情图
		if(detailImg != null) {
			for(int i=0;i<detailImg.length;i++) {
				StaticFiles files = new StaticFiles();
				files.setRelationId(g.getId());
				files.setFilePath(detailImg[i]);
				files.setFileType(0);
				files.setBigType(0);
				files.setSmallType(2);
				files.setSeq(i);
				staticFilesMapper.insertFiles(files);
			}
		}
	}

	@Override
	public Goods selectGoodsObj(Integer id) {
		Goods goods = goodsMapper.selectGoodsObj(id);
		if(goods != null) {
			StaticFiles sf = new StaticFiles();
			sf.setRelationId(goods.getId());
			sf.setFileType(0);
			sf.setBigType(0);
			sf.setSmallType(2);
			List<StaticFiles> sfList = staticFilesMapper.selectFileList(sf);
			if(sfList != null&& sfList.size() > 0) {
				String detailImg = "";
				for(int i=0;i<sfList.size();i++) {
					detailImg += sfList.get(i).getFilePath()+",";
				}
				if(detailImg.length() > 0) {
					detailImg = detailImg.substring(0, detailImg.length()-1);
				}
				goods.setDetailImg(detailImg);
			}
		}
		return goodsMapper.selectGoodsObj(id);
	}

	@Override
	@Transactional
	public void updateGoods(Goods g) {
		String[] labels = null;
		String[] classifys = null;
		String[] detailImg = null;
		String[] advertImg = null;
		if(g.getLabels() != null && g.getLabels().length() > 0) {
			labels = g.getLabels().split(",");
		}
		if(g.getClassifys() != null && g.getClassifys().length() > 0) {
			classifys = g.getClassifys().split(",");
		}
		if(g.getDetailImg() != null && g.getDetailImg().length() > 0) {
			detailImg = g.getDetailImg().split(",");
		}
		if(g.getAdvertImg() != null && g.getAdvertImg().length() > 0) {
			advertImg = g.getAdvertImg().split(",");
		}
		//商品
		goodsMapper.updateGoods(g);
		//商品标签
		goodsMapper.deleteGoodsLabel(g.getId());
		if(labels != null) {
			for(int i=0;i<labels.length ;i++) {
				GoodsClassify bean = new GoodsClassify();
				bean.setSeq(i);
				bean.setName(labels[i]);
				bean.setGoodsId(g.getId());
				goodsMapper.insertGoodsLabel(bean);
			}
		}
		//商品分类
		goodsMapper.deleteGoodsClassifyDz(g.getId());
		if(classifys != null) {
			for(int i=0;i<classifys.length;i++) {
				goodsMapper.insertGoodsClassifyDz(g.getId(), Integer.valueOf(classifys[i]));
			}
		}
		StaticFiles files = new StaticFiles();
		files.setRelationId(g.getId());
		//商品视频
		files.setFilePath(g.getGoodsVideo());
		files.setFileType(1);
		files.setBigType(0);
		files.setSmallType(0);
		staticFilesMapper.deleteFilesRelation(files);
		if(g.getGoodsVideo() != null && g.getGoodsVideo().length() > 0) {
			
			staticFilesMapper.insertFiles(files);
		}
		//商品缩略图
		files.setFilePath(g.getGoodsImage());
		files.setFileType(0);
		files.setBigType(0);
		files.setSmallType(0);
		staticFilesMapper.deleteFilesRelation(files);
		if(g.getGoodsImage() != null && g.getGoodsImage().length() > 0) {
			staticFilesMapper.insertFiles(files);
		}
		//商品轮播图
		files.setFileType(0);
		files.setBigType(0);
		files.setSmallType(1);
		staticFilesMapper.deleteFilesRelation(files);
		if(advertImg != null) {
			for(int i=0;i<advertImg.length;i++) {
				files.setFilePath(advertImg[i]);
				files.setSeq(i);
				staticFilesMapper.insertFiles(files);
			}
		}
		//商品详情图
		files.setFileType(0);
		files.setBigType(0);
		files.setSmallType(2);
		staticFilesMapper.deleteFilesRelation(files);
		if(detailImg != null) {
			for(int i=0;i<detailImg.length;i++) {
				files.setFilePath(detailImg[i]);
				files.setSeq(i);
				staticFilesMapper.insertFiles(files);
			}
		}
	}

	@Override
	public void deleteGoods(Goods g) {
		goodsMapper.deleteGoods(g);
	}

	@Override
	public List queryAllGoods() {
		return goodsMapper.queryAllGoods();
	}

	@Override
	public void updateGoodsQrCode(Goods g) {
		// TODO Auto-generated method stub
		goodsMapper.updateGoodsQrCode(g);
	}

	@Override
	public void downLoadOrder(Goods g, HttpServletResponse response) {
		List<Map<String, Object>> list = goodsMapper.downLoadGoods(g);
		
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "品牌", "brandName" });
		metadata.add(new String[] { "SKU", "goodsCode" });
		metadata.add(new String[] { "分类", "classifyName" });
		metadata.add(new String[] { "商品名称", "goodsName" });
		metadata.add(new String[] { "商品规格", "specName" });
		metadata.add(new String[] { "商品单价", "goodsPrice" });
		metadata.add(new String[] { "商品物流库存", "goodsAmount" });
		metadata.add(new String[] { "商品剩余总库存", "goodsRemAmount" });
		metadata.add(new String[] { "排序", "seq" });
		metadata.add(new String[] { "状态", "status" });
		metadata.add(new String[] { "上架时间", "createTime" });
		metadata.add(new String[] { "下架时间", "lastModifiedTime" });
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, list,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		try {
			StringBuffer name = new StringBuffer();
			name.append("商品");
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
	public void selectSpecDataGrid(PageInfo pageInfo, Goods g) {
		// TODO Auto-generated method stub
		Page<Goods> page = new Page<Goods>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Goods> list = goodsMapper.selectSpecDataGrid(page,g);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	
	@Override
	public Goods selectGoodsSpecObj(Integer id) {
		// TODO Auto-generated method stub
		return goodsMapper.selectGoodsSpecObj(id);
	}

	@Override
	@Transactional
	public void insertGoodsSpec(Goods g) {
		// TODO Auto-generated method stub
		goodsMapper.insertGoodsSpec(g);
		//规格图
		if(g.getGoodsImage() != null && g.getGoodsImage().length() > 0) {
			StaticFiles files = new StaticFiles();
			files.setRelationId(g.getId());
			files.setFilePath(g.getGoodsImage());
			files.setFileType(0);
			files.setBigType(0);
			files.setSmallType(3);
			staticFilesMapper.insertFiles(files);
		}
	}

	@Override
	@Transactional
	public void updateGoodsSpec(Goods g) {
		// TODO Auto-generated method stub
		goodsMapper.updateGoodsSpec(g);
		StaticFiles files = new StaticFiles();
		//规格图
		files.setRelationId(g.getId());
		files.setFilePath(g.getGoodsImage());
		files.setFileType(0);
		files.setBigType(0);
		files.setSmallType(3);
		staticFilesMapper.deleteFilesRelation(files);
		if(g.getGoodsImage() != null && g.getGoodsImage().length() > 0) {
			staticFilesMapper.insertFiles(files);
		}
	}
	@Override
	public void selectGoodsInfo(PageInfo pageInfo) {
		List<Store> list = goodsMapper.selectGoodsInfo();
		pageInfo.setRows(list);
	}
}
