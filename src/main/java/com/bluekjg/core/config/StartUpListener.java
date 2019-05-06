package com.bluekjg.core.config;

import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bluekjg.admin.model.DictData;
//import com.bluekjg.admin.service.IWxappDictService;

public class StartUpListener implements ServletContextListener {

//	private IWxappDictService dictService;
	public static WebApplicationContext context;

	public void init() {
//		dictService = (IWxappDictService) context.getBean("wxappDictServiceImpl");
//		WxappDict dict = new WxappDict();
//		dict.setIsDel(0);
//		EntityWrapper<WxappDict> wrapper = new EntityWrapper<WxappDict>();
//		wrapper.setEntity(dict);
//		List<WxappDict> lists = dictService.selectList(wrapper);
//		for (WxappDict pv : lists) {
//			System.setProperty(pv.getCode(), pv.getName());
//		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		context =  WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		//init();
	}

}
