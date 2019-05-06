package com.bluekjg.admin.sjljj;

/**
 * 卡券高级信息
 * @author tim
 *
 */
public class AdvancedInfo {
	
	
	private UseCondition use_condition;  //使用门槛
	//private Abstract abstractInfo;  //封面摘要结构体名称
	//private TextImageList text_image_list;  //图文列表
	//private TimeLimit time_limit;  //使用时段限制

	//arry	商家服务类型： BIZ_SERVICE_DELIVER 外卖服务； BIZ_SERVICE_FREE_PARK 停车位； BIZ_SERVICE_WITH_PET 可带宠物； BIZ_SERVICE_FREE_WIFI 免费wifi， 可多选
	//private List<String> business_service;

	public UseCondition getUse_condition() {
		return use_condition;
	}

	public void setUse_condition(UseCondition use_condition) {
		this.use_condition = use_condition;
	}

//	public Abstract getAbstractInfo() {
//		return abstractInfo;
//	}
//
//	public void setAbstractInfo(Abstract abstractInfo) {
//		this.abstractInfo = abstractInfo;
//	}
//
//	public TextImageList getText_image_list() {
//		return text_image_list;
//	}
//
//	public void setText_image_list(TextImageList text_image_list) {
//		this.text_image_list = text_image_list;
//	}
//
//	public TimeLimit getTime_limit() {
//		return time_limit;
//	}
//
//	public void setTime_limit(TimeLimit time_limit) {
//		this.time_limit = time_limit;
//	}
//
//	public List<String> getBusiness_service() {
//		return business_service;
//	}
//
//	public void setBusiness_service(List<String> business_service) {
//		this.business_service = business_service;
//	}
	
}
