package com.bluekjg.wxapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.bluekjg.wxapp.model.*;
import com.bluekjg.wxapp.model.wxApp20.*;
import com.bluekjg.wxapp.service.IWxStoreService;
import com.bluekjg.wxapp.utils.GetDistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.wxapp.service.IWxDictService;
import com.bluekjg.wxapp.service.IWxGoodsService;
import com.bluekjg.wxapp.service.IWxIndexService;
import com.bluekjg.wxapp.utils.DictUtil;

@Controller
@RequestMapping("/xcx/index")
public class WxIndexController extends BaseController{
	@Autowired
	private IWxIndexService indexService;
	@Autowired
	private IWxGoodsService goodsService;
	@Autowired
	private IWxDictService dictService;
//	@Autowired
//	private IWxAiRequestService aiRequestService;
	@Autowired
	private IWxStoreService storeService;
	
	/**
     * 查询数据字典
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryDict")
    @ResponseBody
    public Object queryDict(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	WxDict dict = dictService.queryDictByCode(page.getCode());
    		obj = renderSuccess(dict);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
	/**
     * 查询商品分类
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryClassify")
    @ResponseBody
    public Object queryClassify(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	List<WxGoodsIndex> list = null;
        	if(page.getType() == 0) {
        		list = indexService.queryBrandList(page);//品牌
        	}else if(page.getType() == 1){
        		list = indexService.queryClassifyList(page);//分类
        	}
    		obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
	/**
     * 查询商品列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/goodsList")
    @ResponseBody
    public Object goodsList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	List<WxGoodsIndex> list = indexService.queryGoodsList(page);
    		obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询活动商品列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/activityList")
    @ResponseBody
    public Object activityList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	List<WxGoodsIndex> list = indexService.queryActivityList(page);
        	if(list != null && list.size() > 0) {
        		for(WxGoodsIndex goods:list) {
        			goods.setActivityTime(goodsService.activityDate(goods.getActivityStartTime(),goods.getActivityEndTime()));
        			goods.setActivitySTime(goodsService.activityDate(goods.getActivityStartTime(),goods.getActivitySTime()));
        		}
        	}
        	if(page.getActivityId() != null && page.getMinId() == 0) {
        		List<WxGoodsIndex> fullList = indexService.queryActivityFull();
        		if(fullList != null && fullList.size() > 0)
        		list.addAll(fullList);
        	}
    		obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询活动商品列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/activityDetail")
    @ResponseBody
    public Object activityDetail(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	List<WxGoodsIndex> list = indexService.queryActivityDetail(page);
    		obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    /**
     * 查询热门商品列表
     *
     * @param resource
     * @return
     */
    @RequestMapping("/goodsHotList")
    @ResponseBody
    public Object goodsHotList(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	List<WxGoodsIndex> list = indexService.queryGoodsHotList(page);
    		obj = renderSuccess(list);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    
    /**
     * 查询最后一次测试结果-首页
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryQuestResult")
    @ResponseBody
    public Object queryQuestResult(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	WxPopConfig pop = indexService.queryPopConfig(page);
        	WxDict activityImage = dictService.queryDictByCode(DictUtil.INDEX_ACTIVITY_IMAGE);
        	WxDict yfwxsh = dictService.queryDictByCode("INDEX_YFWXSH");
        	map.put("yfwxsh", yfwxsh);
        	map.put("pop", pop);
        	map.put("activityImage", activityImage);
    		obj = renderSuccess(map);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 查询最后一次测试结果-个人中心
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryQuestResult1")
    @ResponseBody
    public Object queryQuestResult1(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	WxQuestResult result = indexService.queryQuestResultId(page);
        	WxDict yfwxsh = dictService.queryDictByCode("INDEX_YFWXSH");
        	map.put("yfwxsh", yfwxsh);
        	map.put("result", result);
    		obj = renderSuccess(map);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    
    /**
     * 测试结果
     *
     * @param resource
     * @return
     */
    @RequestMapping("/queryQuestResult2")
    @ResponseBody
    public Object queryQuestResult2(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
    	Object obj = renderError("查询失败");
        try {
        	WxQuestResult result = indexService.queryQuestResultId(page);
    		obj = renderSuccess(result);
        } catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }

	/**
	 * 首页初始化
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryNew")
	@ResponseBody
	public Object queryNew(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
		Object obj = renderError("查询失败");
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			// 查询是否做过AI测试
			WxAITestResult aiTestResult = indexService.queryAITestResultByOpenId(page);
			if(aiTestResult != null){
				// TODO 5-做过AI测试，直接显示结果(查询)
				PageDto pageDto = new PageDto();
				SkinDto skin = new SkinDto();
				WxSkinRecommend recommend = new WxSkinRecommend();
				List<WxGoodsJCTJ> listJC = new ArrayList<>();
				List<WxGoodsGXTJ> listGX = new ArrayList<>();
				List<WxGoodsJCTJ> listJCInGx = new ArrayList<>();
				WxGoodsJCTJ jctj = new WxGoodsJCTJ();
				WxGoodsJCTJ jctjInGx = new WxGoodsJCTJ();
				WxGoodsGXTJ gxtj = new WxGoodsGXTJ();

				Integer jctjGoodsId = indexService.queryTJGoodsIDByOpenId(page);
				//基础推荐
				jctj = indexService.queryTJGoods(jctjGoodsId);

				listJC.add(jctj);

				//个性推荐
//				Integer gxtjGoodsId = indexService.queryTJGoodsIDByOpenId(page);
//				jctjInGx = indexService.queryTJGoods(gxtjGoodsId);
//				listJCInGx.add(jctjInGx);
//
//				gxtj.setItem(listJCInGx);
//				gxtj.setId("");
//				gxtj.setName("第一周期");
//
//				recommend.setType("0");
//				recommend.setName("");
//				recommend.setSelected(true);
//				recommend.setItemsJC(listJC);
//				recommend.setItemsGX(listGX);
//
//				skin.setName("");
//				skin.setNote("");
//				skin.setRecommend(null);

				pageDto.setSkin(skin);
				pageDto.setStore(null);
				map.put("page",pageDto);
				map.put("page_key",5);


			} else {
				// TODO 检测所在城市有没有门店 - 根据城市id 查询该城市是否有门店
				int checkCity = 0;
				List<StoreDto> storeList = storeService.queryStoreByAreaId(page);

				if(storeList.size() != 0){
					// 所在城市有门店，列出所有门店信息
					int checkStore = 0;  // 附近门店
					for (int i = 0;i <= storeList.size();i++){
						// 是否在门店附近 - 遍历该城市所有门店经纬度，判断距离
						double distance = GetDistanceUtil.getmeter(Double.parseDouble(page.getLon()),Double.parseDouble(page.getLat()),Double.parseDouble(storeList.get(i).getLon()),Double.parseDouble(storeList.get(i).getLat())); // 距离
						if (distance <= 2000){
							// 3-附近有门店，去店里做测试 - 跳转门店地址，去门店做AI测试
							map.put("page_key",3);
							PageDto pageDto = new PageDto();
							StoreDto store = new StoreDto();
							store.setDistance(String.valueOf(distance));
							store.setName(storeList.get(i).getName());
							store.setLon(storeList.get(i).getLon());
							store.setLat(storeList.get(i).getLat());
							pageDto.setStore(store);
							pageDto.setSkin(null);
							map.put("page",pageDto);
							break;
						}else{
							// 2-附近没有门店，预约 - 跳转预约
							PageDto pageDto = new PageDto();
							StoreDto store = new StoreDto();
							store.setDistance(String.valueOf(distance));
							store.setName(storeList.get(i).getName());
							store.setLon(storeList.get(i).getLon());
							store.setLat(storeList.get(i).getLat());
							pageDto.setStore(store);
							pageDto.setSkin(null);
							map.put("page_key",2);
							map.put("page",pageDto);
							break;
						}

					}
				} else {
					// 所在城市没有门店
					// 检测是否做过问卷测试
					WxQuestResult result = indexService.queryQuestResultId(page);

					if(result != null){
						PageDto pageDto = new PageDto();
						SkinDto skin = new SkinDto();
						WxSkinRecommend recommend = new WxSkinRecommend();
//						List<WxGoodsJCTJ> listJC = new ArrayList<>();
//						List<WxGoodsGXTJ> listGX = new ArrayList<>();
//
//						WxGoodsJCTJ tjGoods = indexService.queryTJGoods(1);// TODO: 传入goodsID
//
//						recommend.setType("0");
//						recommend.setName("");
//						recommend.setSelected(true);
//						recommend.setItemsJC(listJC);
//						recommend.setItemsGX(listGX);
//
//						skin.setName("");
//						skin.setNote("");
//						skin.setRecommend(null);

						pageDto.setSkin(skin);
						map.put("page",pageDto);

						SkinDto skinDto = new SkinDto();
						skinDto.setName(result.getConclusionName());
						skinDto.setNote("测试1111111");
						skinDto.setRecommend(null);

						pageDto.setSkin(skinDto);
						pageDto.setStore(null);
						// 4-做过测试，直接显示结果
						map.put("page",pageDto);
						map.put("page_key",4);

					} else {
						// 1-没做过测试，进去测试 - 页面跳转
						PageDto pageDto = new PageDto();
						pageDto.setSkin(null);
						pageDto.setStore(null);
						map.put("page_key",1);
						map.put("page",pageDto);
					}
				}
			}
			//userkey
			Integer countUser = indexService.queryCountFromUser(page);
			Integer countMobile = indexService.queryMobileFromUser(page);
			if (countUser != 0){
				map.put("user_key",2);
			}else if (countUser == 0 && countMobile !=0){
				map.put("user_key",1);
			}else{
				map.put("user_key",0);
			}

			obj = renderSuccess(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}


	/**
	 * 通用接口，获取门店列表
	 */
	@RequestMapping("/queryStoreListByAreaId")
	@ResponseBody
	public Object queryStoreListByAreaId(@Valid PagingDto page,HttpServletRequest request,HttpServletResponse response) {
		Object obj = renderError("查询失败");
		Map<String,Object> map = new HashMap<>();
		List<WxStore> storeList = storeService.queryStoreListByAreaId(page.getAreaId());
		String code = storeService.queryCodeByAreaId(page.getAreaId());
		String name = storeService.queryNameByAreaId(page.getAreaId());

		map.put("code",code);
		map.put("name",name);
		map.put("item",storeList);

		obj = renderSuccess(map);
		return obj;
	}
}
