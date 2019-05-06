package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.Coupon;
import com.bluekjg.admin.model.Goods;
import com.bluekjg.admin.model.IntegralGoods;
import com.bluekjg.admin.model.StaticFiles;
import com.bluekjg.admin.service.ICouponService;
import com.bluekjg.admin.service.IGoodsService;
import com.bluekjg.admin.service.IIntegralGoodsService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.wxapp.service.IWxOrderService;

/**
 * <p>
 * 积分商品管理表 前端控制器
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
@Controller
@RequestMapping("/integralGoods")
public class IntegralGoodsController extends BaseController {

@Autowired private IIntegralGoodsService integralGoodsService;
@Autowired private IGoodsService goodsService;
@Autowired private ICouponService couponService;
@Autowired private IWxOrderService orderService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/integralGoods/integralGoodsList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(IntegralGoods integralGoods, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        integralGoodsService.selectDataGrid(pageInfo,integralGoods);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(HttpServletRequest request,HttpServletResponse response) {
    	List<Goods> goodsList = goodsService.queryAllGoods();
    	request.setAttribute("goodsList", goodsList);
    	request.setAttribute("brands", goodsService.selectBrandList());
    	request.setAttribute("couponList", couponService.queryAllCoupon(new Coupon()));
        return "admin/integralGoods/integralGoodsAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid IntegralGoods integralGoods) {
        integralGoods.setLastModifiedTime(new Date());
        Object obj = renderError("操作失败");
    	try {
    		if(integralGoods.getAmount() == null) {
    			integralGoods.setAmount(0);
    		}
    		Integer row = integralGoodsService.selectIntegralGoodsByGoodsId(integralGoods);
    		if (row>0) {
    			obj = renderError("已存在的商品,请勿重复添加！");
			}else {
				if(integralGoods.getType() == 1) {
					integralGoods.setGoodsId(integralGoods.getCouponId());
				}
				IntegralGoods iGoods = integralGoodsService.queryGoodsAmount(integralGoods);
				if (iGoods!=null && iGoods.getGoodsStock()!=null) {
					if (integralGoods.getType() == 0 && integralGoods.getAmount()>iGoods.getGoodsStock()) {
						obj = renderError("库存不足");
					}else {
						integralGoodsService.insertIntegralGoods(integralGoods);
			    		obj = renderSuccess("修改成功！");
					}
				}else{
					integralGoodsService.insertIntegralGoods(integralGoods);
		    		obj = renderSuccess("修改成功！");
				}
			}
    		
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Integer id,Integer goodsId) {
        IntegralGoods integralGoods = new IntegralGoods();
        integralGoods.setId(id);
        integralGoods.setLastModifiedTime(new Date());
        integralGoods.setIsDel(1);
        integralGoods.setGoodsId(goodsId);
        integralGoods.setGoodsAmount(0);
        IntegralGoods iGoods = integralGoodsService.selectIntegralGoodsObj(id);
        if(iGoods != null && iGoods.getType() == 0 && iGoods.getGoodsAmount() != null && iGoods.getGoodsAmount() > 0) {
        	orderService.updateGoodsAmount(goodsId, iGoods.getGoodsAmount());
        }
        boolean b = integralGoodsService.updateById(integralGoods);
        if (b) {
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
    public String editPage(Model model,IntegralGoods iGoods, Integer id,HttpServletRequest request,HttpServletResponse response) {
    	
        IntegralGoods integralGoods = integralGoodsService.selectIntegralGoodsObj(id);
        model.addAttribute("integralGoods", integralGoods);
        model.addAttribute("id", id);
      //处理商品详情图和轮播图
    	List<StaticFiles> sfList = integralGoodsService.queryGoodsImgList(iGoods);
    	List<String> detailImgList = new ArrayList<String>();
        List<String> advertImgList = new ArrayList<String>();
        List<String> imageList = new ArrayList<String>();
        String detailImgArr = "";
        String advertImgArr = "";
        String imageArr="";
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
				}{
					
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
        return "admin/integralGoods/integralGoodsEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid IntegralGoods integralGoods) {
    	try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	IntegralGoods iGoods = integralGoodsService.queryGoodsAmount(integralGoods);
		if (integralGoods.getType() == 0 && integralGoods.getAmount()>iGoods.getGoodsStock()) {
			return renderError("库存不足！");
		}else {
			integralGoods.setLastModifiedTime(new Date());
			boolean b = integralGoodsService.updateById(integralGoods);
	        if (b) {
	        	//修改积分商品库存
	        	integralGoodsService.updateGoodsAmount(integralGoods);
	        	if(integralGoods.getType() == 0) {
		        	//修改可分配
		        	integralGoodsService.editGoodsStock(integralGoods);
	        	}
	        	//修改图片
	        	boolean res = integralGoodsService.modifyGoodsImageInfo(integralGoods);
	        	if(res){
	            	//编辑成功
	            	return renderSuccess("编辑成功！");
	            }else {
	                return renderError("编辑失败！");
	            }
	        } else {
	            return renderError("编辑失败！");
	        }
		}
        
    }
}
