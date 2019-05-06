package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.Store;
import com.bluekjg.admin.model.StoreGoods;
import com.bluekjg.admin.mapper.GoodsMapper;
import com.bluekjg.admin.mapper.StoreGoodsMapper;
import com.bluekjg.admin.service.IStoreGoodsService;
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
public class StoreGoodsServiceImpl extends ServiceImpl<StoreGoodsMapper, Goods> implements IStoreGoodsService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired StoreGoodsMapper storeGoodsMapper;
	@Autowired GoodsMapper goodsMapper;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, Goods g) {
		Page<Goods> page = new Page<Goods>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Goods> list = storeGoodsMapper.selectDataGrid(page,g);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public List<Store> selectStoreList() {
		// TODO Auto-generated method stub
		return storeGoodsMapper.selectStoreList();
	}

	@Override
	public Integer selectGoodsAmount(Integer id) {
		// TODO Auto-generated method stub
		return storeGoodsMapper.selectGoodsAmount(id);
	}

	@Override
	@Transactional
	public void insertStoreGoods(StoreGoods bean) {
		// TODO Auto-generated method stub
		storeGoodsMapper.insertStoreGoods(bean);
		goodsMapper.updateRemAmount(bean.getSpecId(), bean.getGoodsAmount());
		//goodsMapper.updateAmount(bean.getSpecId(), bean.getGoodsAmount());
	}

	@Override
	public StoreGoods selectStoreGoodsObj(Integer id) {
		// TODO Auto-generated method stub
		return storeGoodsMapper.selectStoreGoodsObj(id);
	}

	@Override
	@Transactional
	public void updateStoreGoods(StoreGoods bean) {
		// TODO Auto-generated method stub
		storeGoodsMapper.updateStoreGoods(bean);
		goodsMapper.updateRemAmount(bean.getSpecId(), bean.getGoodsAmount());
		//goodsMapper.updateAmount(bean.getSpecId(), bean.getGoodsAmount());
	}

	@Override
	public void deleteStoreGoods(Integer id) {
		// TODO Auto-generated method stub
		StoreGoods bean = storeGoodsMapper.selectStoreGoodsObj(id);
		if(bean != null) {
			Integer amount = bean.getGoodsAmount() * -1;
			storeGoodsMapper.deleteStoreGoods(id);
			goodsMapper.updateRemAmount(bean.getSpecId(), amount);
			//goodsMapper.updateAmount(bean.getSpecId(), amount);
		}
	}

	@Override
	public void downLoadStoreGoods(Goods g, HttpServletResponse response) {
		List<Map<String, Object>> list = storeGoodsMapper.downLoadStoreGoods(g);
		
		List<String[]> metadata = new ArrayList<String[]>();
		metadata.add(new String[] { "门店", "storeName" });
		metadata.add(new String[] { "品牌", "brandName" });
		metadata.add(new String[] { "SKU", "goodsCode" });
		metadata.add(new String[] { "分类", "classifyName" });
		metadata.add(new String[] { "商品名称", "goodsName" });
		metadata.add(new String[] { "商品规格", "specName" });
		metadata.add(new String[] { "商品单价", "goodsPrice" });
		metadata.add(new String[] { "商品库存", "goodsAmount" });
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
			name.append("门店商品");
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
	public Integer selectStoreGoodsAmount(Integer storeId, Integer goodsId) {
		// TODO Auto-generated method stub
		return storeGoodsMapper.selectStoreGoodsAmount(storeId, goodsId);
	}

}
