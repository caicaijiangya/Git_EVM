package com.bluekjg.admin.sjljj.utils;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bluekjg.admin.sjljj.ActivityInfo;
import com.bluekjg.admin.sjljj.BasicInfo;
import com.bluekjg.admin.sjljj.CardInfoList;
import com.bluekjg.admin.sjljj.CouponInfo;
import com.bluekjg.admin.sjljj.CustomInfo;
import com.bluekjg.admin.sjljj.Info;
import com.bluekjg.core.wx.HttpClientWX;
import com.bluekjg.wxapp.utils.WxappConfigUtil;
import net.sf.json.JSONObject;

/**
 * 优惠码
 * @author tim
 *
 */
public class CouponUtil {
	
	private static Logger logger = LoggerFactory.getLogger(CouponUtil.class);
	//领取立减金活动接口
	private static final String RECEIVE_COUPON_URL = "https://api.weixin.qq.com/card/mkt/activity/create?access_token=ACCESS_TOKEN ";
	//创建优惠券接口
	private static final String CREATE_COUPON_URL = "https://api.weixin.qq.com/card/create?access_token=ACCESS_TOKEN";
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/**
	 * 创建优惠券
	 * @param coupon
	 * @return
	 */
	public static JSONObject createCoupon(CouponInfo coupon){
		
		JSONObject jsonObject = null;
		try {
			String requestUrlToken = access_token_url.replace("APPID", WxappConfigUtil.WX_MP_APPID).replace("APPSECRET", WxappConfigUtil.WX_MP_SECRET);
			JSONObject jsonObjectToken = HttpClientWX.httpRequest(requestUrlToken, "GET", null);
			System.out.println(jsonObjectToken);
			// 如果请求成功
			if (null != jsonObjectToken) {
				logger.info(jsonObjectToken.toString());
				if(jsonObjectToken.get("access_token") != null) {
					String access_token = jsonObjectToken.getString("access_token");
					//调用微信接口
					JSONObject jsonStr =  JSONObject.fromObject(coupon);
					String requestUrl = CREATE_COUPON_URL.replace("ACCESS_TOKEN",access_token);
					System.out.println(jsonStr.toString());
					jsonObject = HttpClientWX.httpRequest(requestUrl, "POST",jsonStr.toString());
				}
			}
		} catch (Exception e) {
			logger.info("创建优惠码失败");
			logger.error(e.getMessage(),e);
		}
		return  jsonObject;
	}
	
	/**
	 * 领取优惠券
	 * @param activityInfo
	 * @return
	 */
	public static JSONObject receiveCoupon(ActivityInfo activityInfo){
		JSONObject jsonObject = null;
		try {
			String requestUrlToken = access_token_url.replace("APPID", WxappConfigUtil.WX_MP_APPID).replace("APPSECRET", WxappConfigUtil.WX_MP_SECRET);
			JSONObject jsonObjectToken = HttpClientWX.httpRequest(requestUrlToken, "GET", null);
			// 如果请求成功
			if (null != jsonObjectToken) {
				String access_token = jsonObjectToken.getString("access_token");
				//调用微信接口
				JSONObject jsonStr =  JSONObject.fromObject(activityInfo);
				String requestUrl = RECEIVE_COUPON_URL.replace("ACCESS_TOKEN",access_token);
				System.out.println(jsonStr.toString());
				jsonObject = HttpClientWX.httpRequest(requestUrl, "POST",jsonStr.toString());
			}
		} catch (Exception e) {
			logger.info("领取优惠码失败");
			logger.error(e.getMessage(),e);
		}
		return  jsonObject;
	}

	
	public static void main(String[] args) {
		WxappConfigUtil.WX_CODING_FORMAT = "UTF-8";
		WxappConfigUtil.WX_MP_APPID = "wxa0e6d57953e42b52";
		WxappConfigUtil.WX_MP_SECRET = "213bfd1ad86e058979dbc48c88944ac1";
		WxappConfigUtil.WX_MERCHANTS_APPID = "1500047572";
		WxappConfigUtil.WX_MERCHANTS_SECRET = "EVMevmnidejifuguanlidashi2018321";
		CustomInfo customInfo  = new CustomInfo();
		customInfo.setType("AFTER_PAY_PACKAGE");
		
		CardInfoList cardInfoList = new CardInfoList();
		cardInfoList.setCard_id("pbZ990b58oxKYQGZ6dWzgKfG2rq8");
		cardInfoList.setTotal_user(true);
		cardInfoList.setMin_amt(200);
		
		BasicInfo basicInfo= new BasicInfo();
		basicInfo.setActivity_bg_color("Color010");
		basicInfo.setActivity_tinyappid("wxe0439672492bd6ec");
		basicInfo.setBegin_time((int)(System.currentTimeMillis()/1000));
		basicInfo.setEnd_time((int)(System.currentTimeMillis()/1000)+64000);
		basicInfo.setGift_num(6);
		basicInfo.setMax_partic_times_act(1);
		basicInfo.setMax_partic_times_one_day(1);
		basicInfo.setMch_code("1500047572");
		
		Info info = new Info();
		info.setBasic_info(basicInfo);
		info.setCustom_info(customInfo);
		info.setCard_info_list(Arrays.asList(cardInfoList));
		ActivityInfo activityInfo = new ActivityInfo();
		activityInfo.setInfo(info);
		JSONObject jsonObject  = receiveCoupon(activityInfo);
		System.out.println(jsonObject);
	}
}
