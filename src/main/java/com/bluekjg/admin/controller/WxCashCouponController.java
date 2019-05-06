package com.bluekjg.admin.controller;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluekjg.admin.model.WxCashCoupon;
import com.bluekjg.admin.service.IWxCashCouponService;
import com.bluekjg.admin.sjljj.ActivityInfo;
import com.bluekjg.admin.sjljj.AdvancedInfo;
import com.bluekjg.admin.sjljj.BaseInfo;
import com.bluekjg.admin.sjljj.BasicInfo;
import com.bluekjg.admin.sjljj.Card;
import com.bluekjg.admin.sjljj.CardInfoList;
import com.bluekjg.admin.sjljj.Cash;
import com.bluekjg.admin.sjljj.CouponInfo;
import com.bluekjg.admin.sjljj.CustomInfo;
import com.bluekjg.admin.sjljj.DateInfo;
import com.bluekjg.admin.sjljj.Info;
import com.bluekjg.admin.sjljj.PayInfo;
import com.bluekjg.admin.sjljj.Sku;
import com.bluekjg.admin.sjljj.SwipeCard;
import com.bluekjg.admin.sjljj.UseCondition;
import com.bluekjg.admin.sjljj.utils.CouponUtil;
import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.wxapp.model.WxDict;
import com.bluekjg.wxapp.service.IWxDictService;
import com.bluekjg.wxapp.utils.DictUtil;
import com.bluekjg.wxapp.utils.ParameterInfoUtil;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

import net.sf.json.JSONObject;

/**
 * <p>
 * 社交立减金主表 前端控制器
 * </p>
 *
 * @author Tom
 * @since 2018-10-20
 */
@Controller
@RequestMapping("/wxCashCoupon")
public class WxCashCouponController extends BaseController {

    @Autowired private IWxCashCouponService cashCouponService;
    @Autowired private IWxDictService dictService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/wxCashCoupon/wxCashCouponList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(WxCashCoupon cashCoupon, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	cashCouponService.selectDataGrid(pageInfo,cashCoupon);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/wxCashCoupon/wxCashCouponAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid WxCashCoupon wxCashCoupon) {
    	WxDict dict = dictService.queryDictByCode(DictUtil.SJLJJ_LOGO);
    	WxDict dictBrandName = dictService.queryDictByCode(DictUtil.SJLJJ_BRAND_USER_NAME);
    	WxDict dictBrandPass = dictService.queryDictByCode(DictUtil.SJLJJ_BRAND_USER_PASS);
    	if(dict != null) {
    		wxCashCoupon.setLogoUrl(dict.getDictValue());
    	}
    	wxCashCoupon.setCreatedTime(new Date());
    	wxCashCoupon.setLastModifiedTime(new Date());
        BaseInfo baseInfo = new BaseInfo();
		baseInfo.setLogo_url(wxCashCoupon.getLogoUrl());
		baseInfo.setCode_type("CODE_TYPE_QRCODE");
		baseInfo.setBrand_name(wxCashCoupon.getBrandName());
		baseInfo.setTitle(wxCashCoupon.getTitle());
		baseInfo.setColor("Color010");
		baseInfo.setGet_limit(wxCashCoupon.getGetLimit());
		baseInfo.setDescription(wxCashCoupon.getDescription());
		Sku sku = new Sku();
		sku.setQuantity(10000);
		baseInfo.setSku(sku);
		SwipeCard swipe_card = new SwipeCard();
		swipe_card.setCreate_mid(WxappConfigUtil.WX_MERCHANTS_APPID);//商户号
		swipe_card.setUse_mid_list(Arrays.asList(WxappConfigUtil.WX_MERCHANTS_APPID));//商户号列表
		swipe_card.setIs_swipe_card(true);
		PayInfo payInfo = new PayInfo();
		payInfo.setSwipe_card(swipe_card);
		baseInfo.setPay_info(payInfo);
		DateInfo dateInfo = new DateInfo();
		dateInfo.setType("DATE_TYPE_FIX_TIME_RANGE");
		Date beginTimestamp = wxCashCoupon.getBeginTimestamp();
		if(beginTimestamp.before(new Date())){
			dateInfo.setBegin_timestamp((int)(System.currentTimeMillis()/1000)+5);
			wxCashCoupon.setBeginTimestamp(new Date());
		}else{
			dateInfo.setBegin_timestamp((int)(beginTimestamp.getTime()/1000)+5);
		}
		dateInfo.setEnd_timestamp((int)(wxCashCoupon.getEndTimestamp().getTime()/1000));
		baseInfo.setDate_info(dateInfo);
		baseInfo.setService_phone("");//服务商手机号
		baseInfo.setCenter_title("立即使用");
		baseInfo.setCenter_app_brand_user_name(dictBrandName.getDictValue());
		baseInfo.setCenter_app_brand_pass(dictBrandPass.getDictValue());
		//高级信息
		AdvancedInfo advancedInfo = new AdvancedInfo();
		UseCondition useCondition = new UseCondition();
		useCondition.setLeast_cost(new BigDecimal("100").multiply(new BigDecimal(wxCashCoupon.getFullMoney())).intValue());
		advancedInfo.setUse_condition(useCondition);
		
		Cash cash = new Cash();
		cash.setAdvanced_info(advancedInfo);
		cash.setBase_info(baseInfo);
		cash.setReduce_cost(new BigDecimal("100").multiply(new BigDecimal(wxCashCoupon.getMoney())).intValue());  //金额
		
		Card card = new Card();
		card.setCard_type("CASH");
		card.setCash(cash);
		CouponInfo coupon = new CouponInfo();
		coupon.setCard(card);
		JSONObject jsonObject = CouponUtil.createCoupon(coupon);
		System.out.println(jsonObject);
		if(jsonObject != null && jsonObject.containsKey("card_id")){
			wxCashCoupon.setIsDel(0);
			wxCashCoupon.setCardId(jsonObject.getString("card_id"));
	        boolean b = cashCouponService.insert(wxCashCoupon);
	        if (b) {
	            return renderSuccess("添加成功！");
	        } else {
	            return renderError("添加失败！");
	        }
		}else{
			return renderError("添加失败！");
		}
    }
    
    /**
     * 领取
     * @param 
     * @return
     */
    @PostMapping("/receiveCash")
    @ResponseBody
    public Object receive(@Valid WxCashCoupon wxCashCoupon) {
    	wxCashCoupon = cashCouponService.selectById(wxCashCoupon.getId());
    	CustomInfo customInfo  = new CustomInfo();
		customInfo.setType("AFTER_PAY_PACKAGE");
		
		CardInfoList cardInfoList = new CardInfoList();
		cardInfoList.setCard_id(wxCashCoupon.getCardId());
		cardInfoList.setTotal_user(true);
		cardInfoList.setMin_amt(new BigDecimal("100").multiply(new BigDecimal(wxCashCoupon.getFullMoney())).intValue());
		
		BasicInfo basicInfo= new BasicInfo();
		basicInfo.setActivity_bg_color("Color010");
		basicInfo.setActivity_tinyappid(WxappConfigUtil.WX_APPID);
		Date beginTime = wxCashCoupon.getBeginTimestamp();
		if(beginTime.before(new Date())){
			beginTime = new Date();
		}
		basicInfo.setBegin_time((int)(beginTime.getTime()/1000)+5);
		basicInfo.setEnd_time((int)(wxCashCoupon.getEndTimestamp().getTime()/1000)-7201);
		basicInfo.setGift_num(wxCashCoupon.getQuantity());
		basicInfo.setMax_partic_times_act(wxCashCoupon.getUseLimit());
		basicInfo.setMax_partic_times_one_day(wxCashCoupon.getGetLimit());
		basicInfo.setMch_code(WxappConfigUtil.WX_MERCHANTS_APPID);
		
		Info info = new Info();
		info.setBasic_info(basicInfo);
		info.setCustom_info(customInfo);
		info.setCard_info_list(Arrays.asList(cardInfoList));
		ActivityInfo activityInfo = new ActivityInfo();
		activityInfo.setInfo(info);
		JSONObject jsonObject  = CouponUtil.receiveCoupon(activityInfo);
		System.out.println(jsonObject);
		if(jsonObject.containsKey("activity_id")){
			String activityId = jsonObject.getString("activity_id");
			WxCashCoupon ccInfo = new WxCashCoupon();
			ccInfo.setActivityId(activityId);
			ccInfo.setIsActivity(1);
			ccInfo.setLastModifiedTime(new Date());
			ccInfo.setId(wxCashCoupon.getId());
			boolean result = cashCouponService.updateById(ccInfo);
			if(result){
				return renderSuccess("领取成功!请前去微信商户平台<营销中心>-><营销活动>->管理活动中去激活");
			}else{
				return renderError("数据更新失败,请联系管理员!");
			}
		}else{
			return renderError("领取失败！");
		}
    }
    
    /**
     * 删除赠品
     * @param 
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delFullgift(@Valid Integer id) {
    	Object obj = renderError("删除失败");
    	try {
    		cashCouponService.delById(id);
    		obj = renderSuccess("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
    	return obj;
    }
}
