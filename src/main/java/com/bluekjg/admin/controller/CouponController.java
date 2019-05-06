package com.bluekjg.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.Coupon;
import com.bluekjg.admin.model.Store;
import com.bluekjg.admin.service.ICouponService;
import com.bluekjg.admin.service.IStoreService;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.utils.WebUtils;

import net.sf.json.JSONArray;

/**
 * <p>
 * 优惠券 前端控制器
 * </p>
 *
 * @author Tim
 * @since 2018-09-29
 */
@Controller
@RequestMapping("/coupon")
public class CouponController extends BaseController {

    @Autowired 
    private ICouponService couponService;
    @Autowired 
    private IStoreService storeService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/coupon/couponList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Coupon coupon, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	couponService.selectDataGrid(pageInfo,coupon);
        return pageInfo;
    }
    
    @PostMapping("/combogridDataGrid")
    @ResponseBody
    public PageInfo combogridDataGrid(Integer type, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo();
    	couponService.selectCombogridDataGrid(pageInfo, type);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/coupon/couponAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Coupon coupon,HttpServletRequest request,HttpServletResponse response) {
    	coupon.setStatus(1);
    	String dueTime0 = request.getParameter("dueTime0");
    	String dueTime1 = request.getParameter("dueTime1");
    	if(coupon.getDueType() == 0) {
    		coupon.setDueTime(dueTime0);
    	}else {
    		coupon.setDueTime(dueTime1);
    	}
        coupon.setCouponNo(WebUtils.getItemID(14));
    	boolean b = couponService.insertCoupon(coupon);
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
        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setIsDel(1);
        boolean b = couponService.updateById(coupon);
        if (b) {
            return renderSuccess("删除成功！");
        } else {
            return renderError("删除失败！");
        }
    }
    
    /**
     * 修改状态
     * @param id
     * @return
     */
    @PostMapping("/editStatus")
    @ResponseBody
    public Object editStatus(Integer id,Integer status) {
    	Object obj = renderError("操作失败");
        try {
        	Coupon coupon = new Coupon();
            coupon.setId(id);
            coupon.setStatus(status);
            couponService.updateById(coupon);
            obj = renderSuccess("操作成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Integer id) {
        Coupon coupon = couponService.selectCouponById(id);
        model.addAttribute("coupon", coupon);
        return "admin/coupon/couponEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Coupon coupon,HttpServletRequest request,HttpServletResponse response) {
        String dueTime0 = request.getParameter("dueTime0");
    	String dueTime1 = request.getParameter("dueTime1");
    	if(coupon.getDueType() == 0) {
    		coupon.setDueTime(dueTime0);
    	}else {
    		coupon.setDueTime(dueTime1);
    	}
        boolean b = couponService.updateCouponById(coupon);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    //优惠券领取详情页
    @GetMapping("/userCoupon")
    public String userCoupon(Model model,Integer id) {
    	model.addAttribute("id", id); 	//优惠券ID
        return "admin/coupon/userCouponList";
    }
    
    @PostMapping("/userCouponDataGrid")
    @ResponseBody
    public PageInfo userCouponDataGrid(Coupon coupon, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	couponService.queryUserCoupon(pageInfo,coupon);
        return pageInfo;
    }
    
  	/**
  	 * 优惠券门店
  	 * @param model
  	 * @param id
  	 * @return
  	 */
    @GetMapping("/couponStore")
    public String couponStore(Model model,Integer id) {
    	List<Integer> list = couponService.selectCouponStore(id);
    	model.addAttribute("id", id); 	//优惠券ID
    	model.addAttribute("list", JSONArray.fromObject(list).toString());
        return "admin/coupon/couponStore";
    }
    
    @PostMapping("/editCouponStore")
    @ResponseBody
    public Object editCouponStore(@Valid Integer id,String storeId) {
    	Object obj = renderError("编辑失败");
    	try {
    		if(storeId != null && storeId.length() > 0) {
    			couponService.deleteCouponStore(id);
				String[] storeIds = storeId.split(",");
				for(String sid:storeIds) {
					couponService.insertCouponStore(id, Integer.valueOf(sid));
				}
			}
    		obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    /**
  	 * 优惠券商品
  	 * @param model
  	 * @param id
  	 * @return
  	 */
    @GetMapping("/couponGoods")
    public String couponGoods(Model model,Integer id) {
    	List<Integer> list = couponService.selectCouponGoods(id);
    	model.addAttribute("id", id); 	//优惠券ID
    	model.addAttribute("list", JSONArray.fromObject(list).toString());
        return "admin/coupon/couponGoods";
    }
    @PostMapping("/editCouponGoods")
    @ResponseBody
    public Object editCouponGoods(@Valid Integer id,String goodsId) {
    	Object obj = renderError("编辑失败");
    	try {
    		if(goodsId != null && goodsId.length() > 0) {
    			couponService.deleteCouponGoods(id);
				String[] goodsIds = goodsId.split(",");
				for(String gid:goodsIds) {
					couponService.insertCouponGoods(id, Integer.valueOf(gid));
				}
			}
    		obj = renderSuccess("编辑成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
    
    
    
    
    /**
     * 优惠券查询
     * @return
     */
    @GetMapping("/queryManager")
    public String queryManager(Model model) {
    	List<Store> storeList = storeService.queryStoreList();
    	model.addAttribute("storeList", storeList);
        return "admin/coupon/couponQuery";
    }
    
    @PostMapping("/queryDataGrid")
    @ResponseBody
    public PageInfo queryDataGrid(Coupon coupon, Integer page, Integer rows) {
    	PageInfo pageInfo = new PageInfo(page, rows);
    	couponService.selectUserCouponDataGrid(pageInfo,coupon);
        return pageInfo;
    }
}
