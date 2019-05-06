package com.bluekjg.admin.sjljj;

/**
 * 使用门槛
 * @author tim
 */
public class UseCondition {
	
	//非必填
	//private String accept_category;  //指定可用的商品类目，仅用于代金券类型 ，填入后将在券面拼写适用于xxx
	
	//private String reject_category;    //指定不可用的商品类目，仅用于代金券类型 ，填入后将在券面拼写不适用于xxxx
	
	//不可以与其他类型共享门槛 ，填写false时系统将在使用须知里 拼写“不可与其他优惠共享”， 填写true时系统将在使用须知里 拼写“可与其他优惠共享”， 默认为true
	//private boolean can_use_with_other_discount;  
	
	private Integer least_cost;  //满减门槛字段，可用于兑换券和代金券 ，填入后将在全面拼写消费满xx元可用
	
	//private String object_use_for;  //购买xx可用类型门槛，仅用于兑换 ，填入后自动拼写购买xxx可用。

//	public String getAccept_category() {
//		return accept_category;
//	}
//
//	public void setAccept_category(String accept_category) {
//		this.accept_category = accept_category;
//	}
//
//	public String getReject_category() {
//		return reject_category;
//	}
//
//	public void setReject_category(String reject_category) {
//		this.reject_category = reject_category;
//	}
//
//	public boolean isCan_use_with_other_discount() {
//		return can_use_with_other_discount;
//	}
//
//	public void setCan_use_with_other_discount(boolean can_use_with_other_discount) {
//		this.can_use_with_other_discount = can_use_with_other_discount;
//	}

	public Integer getLeast_cost() {
		return least_cost;
	}

	public void setLeast_cost(Integer least_cost) {
		this.least_cost = least_cost;
	}

//	public String getObject_use_for() {
//		return object_use_for;
//	}
//
//	public void setObject_use_for(String object_use_for) {
//		this.object_use_for = object_use_for;
//	}

}
