package com.bluekjg.core.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

import java.util.Properties;

/**
 * @description：读取配置文件properties
 * @author：pincui.tom
 * @date：2018/3/26 14:51
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer  {
	private Properties props;       // 存取properties配置文件key-value结果
	
	 @Override
	    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
	                            throws BeansException {
	        super.processProperties(beanFactoryToProcess, props);
	        this.props = props;
	        WxappConfigUtil.WX_JSCODE_URL = props.getProperty("WX_JSCODE_URL");
	        WxappConfigUtil.WX_TOKEN_URL = props.getProperty("WX_TOKEN_URL");
	        WxappConfigUtil.WX_QRCODE_URL = props.getProperty("WX_QRCODE_URL");
	        WxappConfigUtil.WX_APPID = props.getProperty("WX_APPID");
	        WxappConfigUtil.WX_SECRET = props.getProperty("WX_SECRET");
	        WxappConfigUtil.WX_MP_APPID = props.getProperty("WX_MP_APPID");
	        WxappConfigUtil.WX_MP_SECRET = props.getProperty("WX_MP_SECRET");
	        WxappConfigUtil.WX_MERCHANTS_APPID = props.getProperty("WX_MERCHANTS_APPID");
	        WxappConfigUtil.WX_MERCHANTS_SECRET = props.getProperty("WX_MERCHANTS_SECRET");
	        WxappConfigUtil.WX_GRANTTYPE = props.getProperty("WX_GRANTTYPE");
	        WxappConfigUtil.WX_CODING_FORMAT = props.getProperty("WX_CODING_FORMAT");
	        WxappConfigUtil.WX_FACTORY_FILL = props.getProperty("WX_FACTORY_FILL");
	        WxappConfigUtil.WX_FACTORY_KEY = props.getProperty("WX_FACTORY_KEY");
	        //阿里云
	        WxappConfigUtil.ALICLOUD_IMAGE_BASE_URL = props.getProperty("ALICLOUD_IMAGE_BASE_URL");
	        WxappConfigUtil.ALICLOUD_ENDPOINT = props.getProperty("ALICLOUD_ENDPOINT");
	        WxappConfigUtil.ALICLOUD_ACCESSKEYID = props.getProperty("ALICLOUD_ACCESSKEYID");
	        WxappConfigUtil.ALICLOUD_ACCESSKEYSECRET = props.getProperty("ALICLOUD_ACCESSKEYSECRET");
	        WxappConfigUtil.ALICLOUD_BUCKETNAME = props.getProperty("ALICLOUD_BUCKETNAME");
	        WxappConfigUtil.ALICLOUD_MAX_KEY = props.getProperty("ALICLOUD_MAX_KEY");
	        //服务器配置
	        WxappConfigUtil.SERVICE_PAGE = props.getProperty("SERVICE_PAGE");
	        
	        WxappConfigUtil.WX_ENCRYPT_KEY = props.getProperty("WX_ENCRYPT_KEY");
	        WxappConfigUtil.WX_ENCRYPT_IV = props.getProperty("WX_ENCRYPT_IV");
	        
	        WxappConfigUtil.SERVICE_FFMPEG_PATH = props.getProperty("SERVICE_FFMPEG_PATH");
	        
	        //微信数据分析
	        WxappConfigUtil.DAILY_SUMMARY_URL = props.getProperty("DAILY_SUMMARY_URL");
	        WxappConfigUtil.DAILY_VISIT_TREND_URL = props.getProperty("DAILY_VISIT_TREND_URL");
	        WxappConfigUtil.MONTHLY_VISIT_TREND_URL = props.getProperty("MONTHLY_VISIT_TREND_URL");
	        WxappConfigUtil.WEEKLY_VISIT_TREND_URL = props.getProperty("WEEKLY_VISIT_TREND_URL");
	        WxappConfigUtil.USER_PORTRAIT_URL = props.getProperty("USER_PORTRAIT_URL");
	        WxappConfigUtil.VISIT_DISTRIBUTION_URL = props.getProperty("VISIT_DISTRIBUTION_URL");
	        WxappConfigUtil.VISIT_PAGE_URL = props.getProperty("VISIT_PAGE_URL");
	        WxappConfigUtil.DAILY_RETAIN_URL = props.getProperty("DAILY_RETAIN_URL");
	        WxappConfigUtil.MONTHLY_RETAIN_URL = props.getProperty("MONTHLY_RETAIN_URL");
	        WxappConfigUtil.WEEKLY_RETAIN_URL = props.getProperty("WEEKLY_RETAIN_URL");
	        //电子发票
	        WxappConfigUtil.SHDZFP_URL = props.getProperty("SHDZFP_URL");
	        WxappConfigUtil.SHDZFP_APPID = props.getProperty("SHDZFP_APPID");
	        WxappConfigUtil.SHDZFP_VERSION = props.getProperty("SHDZFP_VERSION");
	        WxappConfigUtil.SHDZFP_BMB_BBH = props.getProperty("SHDZFP_BMB_BBH");
	        WxappConfigUtil.SHDZFP_REQUEST_CODE = props.getProperty("SHDZFP_REQUEST_CODE");
	        WxappConfigUtil.SHDZFP_TAXPAYER_ID = props.getProperty("SHDZFP_TAXPAYER_ID");
	        WxappConfigUtil.SHDZFP_AUTHORIZATION_CODE = props.getProperty("SHDZFP_AUTHORIZATION_CODE");
	        WxappConfigUtil.SHDZFP_RESPONSE_CODE = props.getProperty("SHDZFP_RESPONSE_CODE");
	        WxappConfigUtil.SHDZFP_SL = props.getProperty("SHDZFP_SL");
	        WxappConfigUtil.SHDZFP_SWBM = props.getProperty("SHDZFP_SWBM");
	        WxappConfigUtil.SHDZFP_KPY = props.getProperty("SHDZFP_KPY");
	        WxappConfigUtil.SHDZFP_KPXM = props.getProperty("SHDZFP_KPXM");
	        WxappConfigUtil.SHDZFP_TAXPAYER_NSRSBH1 = props.getProperty("SHDZFP_TAXPAYER_NSRSBH1");
	        WxappConfigUtil.SHDZFP_TAXPAYER_NAME1 = props.getProperty("SHDZFP_TAXPAYER_NAME1");
	        WxappConfigUtil.SHDZFP_TAXPAYER_DZ1 = props.getProperty("SHDZFP_TAXPAYER_DZ1");
	        WxappConfigUtil.SHDZFP_TAXPAYER_DH1 = props.getProperty("SHDZFP_TAXPAYER_DH1");
	        WxappConfigUtil.SHDZFP_TAXPAYER_YHZH1 = props.getProperty("SHDZFP_TAXPAYER_YHZH1");
	        WxappConfigUtil.SHDZFP_TAXPAYER_NSRSBH2 = props.getProperty("SHDZFP_TAXPAYER_NSRSBH2");
	        WxappConfigUtil.SHDZFP_TAXPAYER_NAME2 = props.getProperty("SHDZFP_TAXPAYER_NAME2");
	        WxappConfigUtil.SHDZFP_TAXPAYER_DZ2 = props.getProperty("SHDZFP_TAXPAYER_DZ2");
	        WxappConfigUtil.SHDZFP_TAXPAYER_DH2 = props.getProperty("SHDZFP_TAXPAYER_DH2");
	        WxappConfigUtil.SHDZFP_TAXPAYER_YHZH2 = props.getProperty("SHDZFP_TAXPAYER_YHZH2");
	    }

	    public String getProperty(String key){
	        return this.props.getProperty(key);
	    }

	    public String getProperty(String key, String defaultValue) {
	        return this.props.getProperty(key, defaultValue);
	    }

	    public Object setProperty(String key, String value) {
	        return this.props.setProperty(key, value);
	    }
}
