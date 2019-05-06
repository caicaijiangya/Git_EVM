package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.Activity;
import com.bluekjg.admin.model.ActivityFull;
import com.bluekjg.admin.model.ActivityFullgift;
import com.bluekjg.admin.model.ActivityGoods;
import com.bluekjg.admin.model.ActivityGoodsBargain;
import com.bluekjg.admin.model.ActivityGoodsCollage;
import com.bluekjg.admin.model.Coupon;
import com.bluekjg.admin.model.ExportDto;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.admin.model.Store;
import com.bluekjg.admin.service.IActivityGoodsService;
import com.bluekjg.admin.service.IActivityService;
import com.bluekjg.admin.service.ICouponService;
import com.bluekjg.admin.service.IGoodsService;
import com.bluekjg.admin.service.IStaticFilesService;
import com.bluekjg.admin.service.IStoreService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;

/**
 * <p>
 * 活动主表 前端控制器
 * </p>
 *
 * @author Tim
 * @since 2018-09-28
 */
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    @Autowired private IActivityService activityService;
    @Autowired private IActivityGoodsService activityGoodsService;
    @Autowired private ICouponService couponService;
    @Autowired private IStoreService storeService;
    @Autowired private IStaticFilesService staticFilesService;
    @Autowired private IGoodsService goodsService;
    @GetMapping("/manager")
    public String manager() {
        return "admin/activity/activityList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Activity activity, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        activityService.selectDataGrid(pageInfo,activity);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Model model) {
        return "admin/activity/activityAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Activity activity,HttpServletRequest request) {
    	/*Integer n = activityService.queryActivityList(activity);
    	if (n>0) {
    		 return renderError("该时间段类已存在此类活动,请勿重复添加！");
		}else {
			
			
		}*/
    	activity.setLastModifiedTime(new Date());
        boolean b = activityService.insert(activity);
		
        if (b) {
            return renderSuccess("添加成功！");
        } else {
            return renderError("添加失败！");
        }
    	
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
        Activity activity = new Activity();
        activity.setId(id);
        activity.setLastModifiedTime(new Date());
        activity.setIsDel(1);
        boolean b = activityService.updateById(activity);
        if (b) {
        	activityService.editActivityGoods(id);
            return renderSuccess("删除成功！");
        } else {
            return renderError("删除失败！");
        }
    }
    
    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Long id) {
        Activity activity = activityService.selectById(id);
        
        model.addAttribute("activity", activity);
        return "admin/activity/activityEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Activity activity,HttpServletRequest request) {
        activity.setLastModifiedTime(new Date());
        boolean b = activityService.updateById(activity);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    /**
     * 审核
     * @param id
     * @return
     */
    @PostMapping("/review")
    @ResponseBody
    public Object review(Integer id) {
        Activity activity = new Activity();
        activity.setId(id);
        activity.setLastModifiedTime(new Date());
        activity.setStatus(1);
        boolean b = activityService.updateById(activity);
        if (b) {
            return renderSuccess("审核成功！");
        } else {
            return renderError("审核失败！");
        }
    }
    
    /**
     * 指派商品页面
     * @return
     */
    @GetMapping("/appointGoods")
    public String appointGoods(Model model,Integer id,Integer storeId,Integer activityType){
    	model.addAttribute("id", id);		//活动ID
    	model.addAttribute("storeId", storeId);		//门店ID
    	model.addAttribute("activityType",activityType);//活动类型
    	model.addAttribute("brands", goodsService.selectBrandList());
    	return "admin/activity/appointGoodsList";
    }
    
    @PostMapping("/dataGridAppoint")
    @ResponseBody
    public PageInfo dataGridAppoint(Activity activity, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	activityService.queryGoodsList(pageInfo, activity);
        return pageInfo ;
    }
    
    /**
     * 确认指派
     * @param 
     * @return
     */
    @PostMapping("/appoint")
    @ResponseBody
    public Object appoint(Goods goods){
    	boolean b = activityService.appointGoods(goods);
    	if (b) {
			return renderSuccess("指派成功");
		}else {
			return renderError("指派失败");
		}
    }
    
    /**
     * 查看已指派的商品页面
     * @return
     */
    @GetMapping("/searchGoods")
    public String searchGoods(Model model , Integer id,Integer storeId,Integer activityType){
   
    	model.addAttribute("id", id);  //活动ID
    	model.addAttribute("storeId", storeId);  //门店ID
    	model.addAttribute("activityType", activityType);  //活动类型
    	return "admin/activity/searchGoodsList";
    }
    
    @PostMapping("/search")
    @ResponseBody
    public PageInfo search(Activity activity, Integer page , Integer rows){
    	if (activity.getActivityType()==1) {
			activity.setActType(2);
		}else if(activity.getActivityType()==2){
			activity.setActType(4);
		}else if (activity.getActivityType()==3) {
			activity.setActType(3);
		}else if (activity.getActivityType()==4) {
			activity.setActType(9);
		}else {
			activity.setActType(0);
		}
    	PageInfo pageInfo = new PageInfo(page,rows);
    	activityService.queryActivityGoodsList(pageInfo,activity);
    	return pageInfo;
    }
    
    /**
     * 取消指派
     * @param id
     * @return
     */
    @PostMapping("/deleteList")
    @ResponseBody
    public Object deleteList(ActivityGoods activityGoods) {
        boolean b = activityService.updateActivityGoods(activityGoods);
        if (b) {
            return renderSuccess("取消成功！");
        } else {
            return renderError("取消失败！");
        }
    }
    
    /**
     * 设置商品参数
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editParameterPage")
    public String editCollageParameter(Model model, Integer id,Integer goodsId,Integer activityType) {
    	model.addAttribute("id", id);
    	model.addAttribute("goodsId", goodsId);
    	model.addAttribute("activityType", activityType);
        ActivityGoods activityGoods = activityGoodsService.selectActivityGoodsById(id);
        model.addAttribute("activityGoods", activityGoods);
        return "admin/activity/searchGoodsEdit";
    }
    
    /**
     * 编辑活动商品参数
     * @param 
     * @return
     */
    @RequestMapping("/editParameter")
    @ResponseBody
    public Object editParameter(@Valid ActivityGoods activityGoods) {
    	activityGoods.setLastModifiedTime(new Date());
    	Object obj = renderError("操作失败");
    	try {
    		if(activityGoods.getAmount() == null) {
    			activityGoods.setAmount(0);
    		}
    		Integer n = activityGoods.getAmount()-activityGoods.getGoodsAmount();
    		if(n>0){
    			obj = renderError("库存不足");
    			}else {
    				activityGoodsService.updateGoods(activityGoods);
    	    		activityGoodsService.updateActivityGoods(activityGoods);
    	    		activityGoodsService.updateActivityAmount(activityGoods);
    	    		obj = renderSuccess("修改成功！");
				}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    /**
     * 设置拼团商品参数
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editCollageParameterPage")
    public String editCollageParameterPage(Model model, Integer id,Integer goodsId,Integer activityType) {
    	model.addAttribute("id", id);
    	model.addAttribute("goodsId", goodsId);
    	model.addAttribute("activityType", activityType);
        return "admin/activity/collageGoodsEdit";
    }
    
    /**
     * 编辑活动商品拼团参数
     * @param 
     * @return
     */
    @RequestMapping("/editCollageParameter")
    @ResponseBody
    public Object editCollageParameter(@Valid ActivityGoodsCollage activityGoodsCollage) {
    	Object obj = renderError("操作失败");
    	try {
    		activityGoodsService.updateGoodsCollage(activityGoodsCollage);
    		obj = renderSuccess("ok");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    /**
     * 设置砍价商品参数
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editBargainParameterPage")
    public String editBargainParameterPage(Model model, Integer id,Integer goodsId,Integer activityType) {
    	model.addAttribute("id", id);
    	model.addAttribute("goodsId", goodsId);
    	model.addAttribute("activityType", activityType);
    	Coupon coupon = new Coupon();
    	coupon.setIsShow(1);
    	List<Coupon> couponList = couponService.queryAllCoupon(coupon);
    	ActivityGoodsBargain activityGoodsBargain = activityGoodsService.selectActivityGoodsBargainById(id);
    	if(activityGoodsBargain == null) {
    		activityGoodsBargain = new ActivityGoodsBargain();
    		activityGoodsBargain.setBargainNum(1);
    		activityGoodsBargain.setHelpBargainNum(1);
    		activityGoodsBargain.setGivingNum(1);
    		activityGoodsBargain.setIntegral(0);
    		activityGoodsBargain.setType(0);
    		activityGoodsBargain.setTime(24);
    	}
        model.addAttribute("activityGoodsBargain", activityGoodsBargain);
        model.addAttribute("couponList", couponList);
        return "admin/activity/bargainGoodsEdit";
    }
    
    /**
     * 编辑活动商品砍价参数
     * @param 
     * @return
     */
    @RequestMapping("/editBargainParameter")
    @ResponseBody
    public Object editBargainParameter(@Valid ActivityGoodsBargain activityGoodsBargain) {
    	Object obj = renderError("操作失败");
    	try {
    		activityGoodsService.updateGoodsBargain(activityGoodsBargain);
    		obj = renderSuccess("ok");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    /**
     * 编辑活动商品图片
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editActivityGoodsImg")
    public String editActivityGoodsImg(Model model,ActivityGoods agoods, Integer id,Integer goodsId,Integer activityType,HttpServletRequest request,HttpServletResponse response) {
    	if (activityType==1) {
			agoods.setActType(2);
		}else if(activityType==2){
			agoods.setActType(4);
		}else if (activityType==3) {
			agoods.setActType(3);
		}else if (activityType==4) {
			agoods.setActType(9);
		}else if (activityType==5) {
			agoods.setActType(10);
		}else if (activityType==6) {
			agoods.setActType(11);
		}else if (activityType==7) {
			agoods.setActType(12);
		}
    	model.addAttribute("id", id);
    	model.addAttribute("goodsId", goodsId);
    	model.addAttribute("activityType", activityType);
        
      //处理商品详情图和轮播图
    	List<StaticFiles> sfList = activityGoodsService.selectImgById(agoods);
    	
        List<String> detailImgList = new ArrayList<String>();
        List<String> advertImgList = new ArrayList<String>();
        List<String> imageList = new ArrayList<String>();
        String detailImgArr = "";
        String advertImgArr = "";
        String imageArr="";
        String listImgArr = "";
        if(sfList.size()>0){
        	for(StaticFiles sf : sfList){
        		
        		if(sf.getSmallType()==1){  //轮播图
        			advertImgList.add(sf.getFilePath());
        			advertImgArr+=","+sf.getFilePath();
        		}else if(sf.getSmallType()==2){  //详情图
        			detailImgList.add(sf.getFilePath());
        			detailImgArr+=","+sf.getFilePath();
        		}else if (sf.getSmallType()==0) {	//缩略图
        			imageList.add(sf.getFilePath());
        			imageArr+=","+sf.getFilePath();
				}else if(sf.getSmallType()==3){//列表图
					listImgArr = sf.getFilePath();
				}
        	}
        }
        if(advertImgArr.length()>0){
        	advertImgArr = advertImgArr.substring(1, advertImgArr.length());
        }
        if(detailImgArr.length()>0){
        	detailImgArr = detailImgArr.substring(1, detailImgArr.length());
        }
        if (imageArr.length()>0) {
			imageArr = imageArr.substring(1, imageArr.length());
		}
        request.setAttribute("detailImgArr", detailImgArr);
        request.setAttribute("advertImgArr", advertImgArr);
        request.setAttribute("imageArr", imageArr);
        request.setAttribute("detailImgList", detailImgList);
        request.setAttribute("advertImgList", advertImgList);
        request.setAttribute("imageList", imageList);
        request.setAttribute("listImgArr", listImgArr);
        return "admin/activity/activityGoodsImgEdit";
    }
    
    /**
     * 编辑活动商品图片
     * @param 
     * @return
     */
    @RequestMapping("/editGoodsImg")
    @ResponseBody
    public Object editGoodsImg(@Valid ActivityGoods activityGoods,Model model,Integer goodsId,Integer activityType) {
    	activityGoods.setLastModifiedTime(new Date());
    	boolean b = activityGoodsService.modifyGoodsImageInfo(activityGoods);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    } 
    @PostMapping("/collageDataGrid")
    @ResponseBody
    public PageInfo collageDataGrid(Integer activityGoodsId, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	activityGoodsService.selectCollageDataGrid(pageInfo, activityGoodsId);
        return pageInfo;
    }
    @PostMapping("/ladderDataGrid")
    @ResponseBody
    public PageInfo ladderDataGrid(Integer bargainId, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	activityGoodsService.selectBargainLadderDataGrid(pageInfo,bargainId);
        return pageInfo;
    }
    @PostMapping("/priceDataGrid")
    @ResponseBody
    public PageInfo priceDataGrid(Integer bargainId, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	activityGoodsService.selectBargainPriceDataGrid(pageInfo, bargainId);
        return pageInfo;
    }
    
    /**
     * 赠品页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/fullgiftPage")
    public String fullgiftPage(Model model, Integer id,Integer storeId) {
        model.addAttribute("activityId", id);
        model.addAttribute("storeId", storeId);
        return "admin/activity/activityFullgiftList";
    }
    @PostMapping("/fullgiftDataGrid")
    @ResponseBody
    public PageInfo fullgiftDataGrid(Integer activityId,Integer storeId, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	activityGoodsService.selectFullgiftDataGrid(pageInfo, activityId,storeId);
        return pageInfo;
    }
    /**
     * 添加赠品页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/addFullgiftPage")
    public String addFullgiftPage(Model model, Integer activityId,Integer storeId) {
    	model.addAttribute("storeId", storeId);
    	model.addAttribute("activityId", activityId);
    	Store store = storeService.selectStoreById(storeId);
    	if(store == null) {
    		store = new Store();
    		store.setId(0);
    		store.setStoreName("小程序");
    	}
    	model.addAttribute("store", store);
        return "admin/activity/activityFullgiftAdd";
    }
    
    /**
     * 添加赠品
     * @param 
     * @return
     */
    @PostMapping("/addFullgift")
    @ResponseBody
    public Object addFullgift(@Valid ActivityFullgift activityFullgift) {
    	Object obj = renderError("添加失败");
    	try {
    		if(activityFullgift.getAmount() == null) {
    			activityFullgift.setAmount(0);
    		}
    		activityGoodsService.insertActivityFullgift(activityFullgift);
    		obj = renderSuccess("添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 编辑赠品页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editFullgiftPage")
    public String editFullgiftPage(Model model, Integer activityId,Integer storeId,Integer id) {
        ActivityFullgift fullgift = activityGoodsService.selectActivityFullgiftById(id);
        model.addAttribute("activityId", activityId);
        model.addAttribute("fullgift", fullgift);
        return "admin/activity/activityFullgiftEdit";
    }
    
    /**
     * 编辑赠品
     * @param 
     * @return
     */
    @PostMapping("/editFullgift")
    @ResponseBody
    public Object editFullgift(@Valid ActivityFullgift activityFullgift) {
    	Object obj = renderError("编辑失败");
    	try {
    		if(activityFullgift.getAmount() == null) {
    			activityFullgift.setAmount(0);
    		}
    		activityGoodsService.updateActivityFullgift(activityFullgift);
    		obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    /**
     * 删除赠品
     * @param 
     * @return
     */
    @PostMapping("/delFullgift")
    @ResponseBody
    public Object delFullgift(@Valid Integer id) {
    	Object obj = renderError("删除失败");
    	try {
    		activityGoodsService.deleteActivityFullgift(id);
    		obj = renderSuccess("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    
    /**
     * 添加优惠条件页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/fullPage")
    public String fullPage(Model model, Integer id) {
    	model.addAttribute("id", id);
        return "admin/activity/activityFullEdit";
    }
    @PostMapping("/fullDataGrid")
    @ResponseBody
    public PageInfo fullDataGrid(Integer id, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	activityService.fullDataGrid(pageInfo, id);
        return pageInfo;
    }
    @PostMapping("/editFull")
    @ResponseBody
    public Object editFull(@Valid ActivityFull full) {
    	Object obj = renderError("编辑失败");
    	try {
    		activityService.editActivityFull(full);
    		obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    /**
     * 添加折扣条件页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/discountPage")
    public String discountPage(Model model, Integer id) {
    	model.addAttribute("id", id);
        return "admin/activity/activityDiscountEdit";
    }
    @PostMapping("/discountDataGrid")
    @ResponseBody
    public PageInfo discountDataGrid(Integer id, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo();
    	activityService.discountDataGrid(pageInfo, id);
        return pageInfo;
    }
    @PostMapping("/editDiscount")
    @ResponseBody
    public Object editDiscount(@Valid ActivityFull full) {
    	Object obj = renderError("编辑失败");
    	try {
    		activityService.editActivityDiscount(full);
    		obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    /**
     * 编辑活动图片
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editImgPage")
    public String editImgPage(Model model, Integer id) {
    	model.addAttribute("id", id);
    	StaticFiles files = new StaticFiles();
    	files.setRelationId(id);
    	files.setFileType(0);
    	files.setBigType(110);
    	files.setSmallType(0);
    	List<StaticFiles> fileList= staticFilesService.selectFileList(files);
    	if(fileList != null && fileList.size() > 0) {
    		model.addAttribute("files", fileList.get(0));
    	}else {
    		model.addAttribute("files", null);
    	}
        return "admin/activity/activityImgEdit";
    }
    @PostMapping("/editImg")
    @ResponseBody
    public Object editImg(Integer id,String image) {
    	Object obj = renderError("编辑失败");
    	try {
    		StaticFiles files = new StaticFiles();
    		files.setFilePath(image);
        	files.setRelationId(id);
        	files.setFileType(0);
        	files.setBigType(110);
        	files.setSmallType(0);
        	staticFilesService.deleteFilesRelation(files);
        	if(image != null && image.length() > 0)
        	staticFilesService.insertFiles(files);
    		obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    @GetMapping("/storeManager")
    public String storeManager(Model model,Integer id,Integer activityType) {
    	model.addAttribute("id", id);
    	model.addAttribute("activityType", activityType);
        return "admin/activity/activityStoreList";
    }
    @PostMapping("/storeDataGrid")
    @ResponseBody
    public PageInfo storeDataGrid(Activity activity, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        activityService.selectStoreDataGrid(pageInfo,activity);
        return pageInfo;
    }
    @GetMapping("/editStorePage")
    public String editStorePage(Model model,Integer id) {
    	model.addAttribute("id", id);
    	Activity activity = activityService.selectById(id);
    	if(activity != null) {
        	List<Map<String,Object>> storeList = activityService.selectActivityStore(id);
        	String storeIds = "";
        	if(storeList != null && storeList.size() > 0) {
        		for(Map<String,Object> sid:storeList) {
        			storeIds += sid.get("id")+",";
        		}
        		storeIds = storeIds.substring(0,storeIds.length()-1);
        	}
        	activity.setStoreId(storeIds);
        }
    	model.addAttribute("activity", activity);
        return "admin/activity/activityStoreEdit";
    }
    @PostMapping("/editStore")
    @ResponseBody
    public Object editStore(@Valid Activity activity) {
    	Object obj = renderError("编辑失败");
    	try {
    		activityService.deleteActivityStore(activity.getId());
    		if(activity.getStoreId() != null && activity.getStoreId().length() > 0) {
				String[] storeIds = activity.getStoreId().split(",");
				for(String id:storeIds) {
					activity.setStoreId(id);
					activityService.insertActivityStore(activity);
				}
			}
    		obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    @GetMapping("/editLabelPage")
    public String editLabelPage(Model model,Integer id) {
    	model.addAttribute("id", id);
        return "admin/activity/activityLabelEdit";
    }
    @PostMapping("/editLabel")
    @ResponseBody
    public Object editLabel(@Valid Integer id,String names) {
    	Object obj = renderError("编辑失败");
    	try {
    		if(names != null && names.length() > 0) {
    			activityService.deleteActivityLabel(id);
    			String names_[] = names.split(",");
    			for(int i=0;i<names_.length;i++) {
    				if(names_[i] != null && names_[i].length() > 0) {
    					activityService.insertActivityLabel(String.valueOf(id), names_[i], String.valueOf(i));
    				}
    			}
    		}
    		obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    @PostMapping("/labelDataGrid")
    @ResponseBody
    public PageInfo labelDataGrid(Integer id) {
    	PageInfo pageInfo = new PageInfo();
        activityService.selectActivityLabelDataGrid(pageInfo, id);
        return pageInfo;
    }
    
    /**
     * 导出页面
     * @return
     */
    @GetMapping("/downExcel")
    public String downExcel() {
        return "admin/activity/downExcel";
    }
    /**
     * 导出砍价明细
     * @param 
     * @return
     */
    @RequestMapping("/downLoadBargain")
    @ResponseBody
    public Object downLoadBargain(@Valid ExportDto dto, HttpServletResponse response) {
    	activityGoodsService.downLoadBargain(dto, response);
        return renderSuccess("导出成功");
    }
}
