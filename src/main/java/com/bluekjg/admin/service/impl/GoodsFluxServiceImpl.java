package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.mapper.GoodsFluxMapper;
import com.bluekjg.admin.service.IGoodsFluxService;
import com.bluekjg.admin.upload.ExcelUtil;
import com.bluekjg.admin.upload.WriteExcelInterface;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.core.utils.StringUtil;

import net.sf.json.JSONObject;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据分析-渠道 服务实现类
 * </p>
 *
 * @author Tom
 * @since 2019-03-01
 */
@Service
public class GoodsFluxServiceImpl extends ServiceImpl<GoodsFluxMapper, Goods> implements IGoodsFluxService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired private GoodsFluxMapper goodsFluxMapper;
	
	//商品流量分析-渠道列表
	@Override
	public void selectDataGrid(PageInfo pageInfo, Goods goods) {
		Page<Goods> page = new Page<Goods>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Goods> list = goodsFluxMapper.selectDataGrid(page,goods);
		DecimalFormat df = new DecimalFormat("0.00");
		List<Goods> dataList = new ArrayList<Goods>();
		for (int i = 0; i < list.size(); i++) {
			Goods newGoods = new Goods();
			newGoods.setId(list.get(i).getId());
			newGoods.setDateTime(list.get(i).getDateTime());
			newGoods.setSpecId(list.get(i).getSpecId());
			newGoods.setSku(list.get(i).getSku());
			newGoods.setSpecName(list.get(i).getSpecName());
			newGoods.setGoodsName(list.get(i).getGoodsName());
			newGoods.setClickNum(list.get(i).getClickNum());
			newGoods.setClickManNum(list.get(i).getClickManNum());
			newGoods.setFunsNum(list.get(i).getFunsNum());
			float ca = 0;
			if (list.get(i).getClickManNum()>0) {
				ca = (float)list.get(i).getClickNum()/(float)list.get(i).getClickManNum();
			}else {
				ca=0;
			}
			String clickAverage = df.format(ca);
			newGoods.setClickAverage(clickAverage);
			newGoods.setCollectNum(list.get(i).getCollectNum());
			newGoods.setCartGoodsNum(list.get(i).getCartGoodsNum());
			newGoods.setSaleNum(list.get(i).getSaleNum());
			newGoods.setBuyManNum(list.get(i).getBuyManNum());
			float ba = 0;
			if (list.get(i).getBuyManNum()>0) {
				ba =(float)list.get(i).getSaleNum()/(float)list.get(i).getBuyManNum();
			}else {
				ba=0;
			}
			String buyAverage = df.format(ba);
			newGoods.setBuyAverage(buyAverage);
			newGoods.setSalePrice(list.get(i).getSalePrice());
			dataList.add(newGoods);
		}
		pageInfo.setRows(dataList);
		pageInfo.setTotal(page.getTotal());
	}

	//商品流量分析-门店列表
	@Override
	public void selectStoreFluxData(PageInfo pageInfo, Goods goods) {
		Page<Goods> page = new Page<Goods>(pageInfo.getNowpage(),pageInfo.getSize());
		List<Goods> list = goodsFluxMapper.selectStoreFluxData(page,goods);
		List<Goods> dataList = new ArrayList<Goods>();
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < list.size(); i++) {
			Goods newGoods = new Goods();
			newGoods.setStoreId(list.get(i).getStoreId());
			if (list.get(i).getStoreId()==0) {
				newGoods.setStoreName("小程序");
			}else {
				newGoods.setStoreName(list.get(i).getStoreName());
			}
			newGoods.setStoreSaleNum(list.get(i).getStoreSaleNum());
			newGoods.setStoreSalePrice(list.get(i).getStoreSalePrice());
			double ca = 0;
			if (list.get(i).getStoreSalePrice()>0 && goods.getSalePrice()>0) {
				ca = ((double)list.get(i).getStoreSalePrice()/(double)goods.getSalePrice())*100;
			}else {
				ca=0;
			}
			String price = df.format(ca)+"%";
			newGoods.setProportion(price);
			dataList.add(newGoods);
		}
		pageInfo.setRows(dataList);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public List<Map<String, Object>> selectMoreGraphics(Goods goods) {
		Page<Goods> page = new Page<Goods>();
		List<Map<String,Object>> list = goodsFluxMapper.selectMoreDataGrid(page,goods);
		return list;
	}

	@Override
	public List<Map<String, Object>> selectMoreRateDataGrid(Goods goods) {
		return goodsFluxMapper.selectMoreRateDataGrid(goods);
	}

	@Override
	public void dataProcessing(List<Map<String, Object>> list, List<Map<String, Object>> dataList,List<Map<String, Object>> storeInfoList) {
		List<String> storeNames = new ArrayList<String>();
		for(Map<String,Object> map:list) {
			Map<String,Object> data = new HashMap<String,Object>();
			boolean bool = true;
			if(dataList != null && dataList.size() > 0) {
				for(Map<String,Object> map1:dataList) {
					if(map.get("dateTime").toString().equals(map1.get("dateTime").toString())
							&& map.get("specId").toString().equals(map1.get("specId").toString())) {
						bool = false;
						break;
					}
				}
			}
			if(bool == true) {
				data.put("dateTime", map.get("dateTime"));
				data.put("fluxNum", map.get("funsNum"));
				data.put("funsNum", map.get("funsNum"));
				data.put("sku", map.get("sku"));
				data.put("specId", map.get("specId"));
				data.put("goodsName", map.get("goodsName"));
				data.put("clickNum", map.get("clickNum"));
				data.put("clickManNum", map.get("clickManNum"));
				data.put("clickPer", map.get("clickPer"));
				data.put("collectNum", map.get("collectNum"));
				data.put("cartGoodsNum", map.get("cartGoodsNum"));
				data.put("saleNum", map.get("saleNum"));
				data.put("buyManNum", map.get("buyManNum"));
				data.put("buyPer", map.get("buyPer"));
				data.put("salePrice", map.get("salePrice"));
				dataList.add(data);
			}
		}
		if(storeInfoList != null && storeInfoList.size() > 0){
			for(Map<String, Object> map:storeInfoList){
				boolean bool = true;
				if(storeNames != null && storeNames.size() > 0) {
					for(String storeName:storeNames) {
						if(map.get("storeName").toString().equals(storeName)) {
							bool = false;
							break;
						}
					}
				}
				if(bool) {
					storeNames.add(map.get("storeName").toString());
				}
			}
		}
		for(Map<String,Object> map:storeInfoList) {
			if(dataList != null && dataList.size() > 0) {
				for(int i=0;i<dataList.size();i++) {
					Map<String,Object> map1 = dataList.get(i);
					if(map.get("dateTime").toString().equals(map1.get("dateTime").toString()) 
							&& map.get("specId").toString().equals(map1.get("specId").toString())) {
						if(storeNames != null && storeNames.size() > 0) {
							for(int j=1;j<storeNames.size()+1;j++) {
								int size = j * 2;
								if(map.get("storeName").toString().equals(storeNames.get((j-1)).toString())) {
									dataList.get(i).put("storeSaleNum" + (size + 2), map.get("storeSaleNum"));
									dataList.get(i).put("storeSalePrice" + (size + 3), map.get("storeSalePrice"));
									dataList.get(i).put("proportion" + (size + 4), map.get("proportion"));
								}
							}
						}
						
					}
				}
			}
		}
	}

	@Override
	public void downLoadMoreChannel(Goods goods, HttpServletResponse response) {
		List<Goods> listObj = goodsFluxMapper.selectDataGrid(goods);
		List<Map<String, Object>> storeInfoList = goodsFluxMapper.selectStoreChannelData(goods);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		DecimalFormat df = new DecimalFormat("0.00");
		List<Map<String, Integer>> dateSize = new ArrayList<Map<String, Integer>>();
		if(listObj != null && listObj.size() > 0){
			for(Goods obj:listObj){
				list.add(com.bluekjg.core.commons.utils.MapUtils.object2Map(obj));
				if (dateSize != null && dateSize.size() > 0) {
					
				}
				
			}
		}
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		List<String[]> metadata = new ArrayList<String[]>();
		List<String> storeNames = new ArrayList<String>();
		if(storeInfoList != null && storeInfoList.size() > 0) {
			for(Map<String,Object> map0:storeInfoList) {
				boolean bool = true;
				if(storeNames != null && storeNames.size() > 0) {
					for(String storeName:storeNames) {
						if(storeName.equals(map0.get("storeName").toString())) {
							bool = false;
							break;
						}
					}
					
				}
				if(bool) {
					storeNames.add(map0.get("storeName").toString());
				}
			}
			
			metadata.add(new String[] { "日期", "dateTime" });
			metadata.add(new String[] { "店铺流量", "fluxNum" });
			metadata.add(new String[] { "粉丝量", "funsNum" });
			metadata.add(new String[] { "商品编码", "sku" });
			metadata.add(new String[] { "商品名称", "goodsName" });
			metadata.add(new String[] { "渠道(总)", "clickNum" });
			metadata.add(new String[] { "", "clickManNum" });
			metadata.add(new String[] { "", "clickPer" });
			metadata.add(new String[] { "", "collectNum" });
			metadata.add(new String[] { "", "cartGoodsNum" });
			metadata.add(new String[] { "", "saleNum" });
			metadata.add(new String[] { "", "buyManNum" });
			metadata.add(new String[] { "", "buyPer" });
			metadata.add(new String[] { "", "salePrice" });
			
			Map<String,Object> data0 = new HashMap<String,Object>();
			data0.put("dateTime", "");
			data0.put("fluxNum", 0);
			data0.put("funsNum", 0);
			data0.put("sku", "");
			data0.put("goodsName", "");
			data0.put("clickNum", "点击次数");
			data0.put("clickManNum", "点击人数");
			data0.put("clickPer", "人均点击次数");
			data0.put("collectNum", "收藏数量");
			data0.put("cartGoodsNum", "加购数量");
			data0.put("saleNum", "销售数量");
			data0.put("buyManNum", "购买人数");
			data0.put("buyPer", "人均购买件数");
			data0.put("salePrice", "销售金额");
			if(storeNames != null && storeNames.size() > 0) {
				for(int i=1;i<storeNames.size()+1;i++) {
					int size = i * 2;
					data0.put("storeSaleNum"+(size + 2), "销售数量");
					data0.put("storeSalePrice"+(size + 3), "销量金额");
					data0.put("proportion"+(size+4), "销售占比");
					metadata.add(new String[] {  storeNames.get((i-1)), "storeSaleNum"+(size + 2) });
					metadata.add(new String[] { "", "storeSalePrice"+(size + 3) });
					metadata.add(new String[] { "", "proportion"+(size + 4) });
				}
			}
			dataList.add(data0);
			dataProcessing(list,dataList,storeInfoList);
		}
		HSSFWorkbook workbook = ExcelUtil.writeToExcelByCustomMap(metadata, dataList,
				new WriteExcelInterface() {
					@Override
					public String processData(String field, Object value) {
						return value.toString();
					}
		});
		HSSFSheet sheet = workbook.getSheetAt(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 4, 4));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 13));
		if (listObj!=null && listObj.size()>0) {
			//sheet.addMergedRegion(new CellRangeAddress(2, listObj.size()+1, 1, 1));
			//sheet.addMergedRegion(new CellRangeAddress(2, listObj.size()+1, 2, 2));
			
		}
		if(storeNames != null && storeNames.size() > 0) {
			int size = 13;
			for(int i=0;i<storeNames.size();i++) {
				sheet.addMergedRegion(new CellRangeAddress(0, 0, (size + 1), (size + 3)));
				size = size + 3;
			}
		}
		try {
			StringBuffer name = new StringBuffer();
			name.append("商品流量统计");
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

	
	
}
