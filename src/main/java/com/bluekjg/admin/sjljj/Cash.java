package com.bluekjg.admin.sjljj;

/**
 * 立减金对象
 * @author tim
 *
 */
public class Cash {
	
	private BaseInfo base_info;
	
	private AdvancedInfo advanced_info;
	
	private Integer reduce_cost;

	public BaseInfo getBase_info() {
		return base_info;
	}

	public void setBase_info(BaseInfo base_info) {
		this.base_info = base_info;
	}

	public AdvancedInfo getAdvanced_info() {
		return advanced_info;
	}

	public void setAdvanced_info(AdvancedInfo advanced_info) {
		this.advanced_info = advanced_info;
	}

	public Integer getReduce_cost() {
		return reduce_cost;
	}

	public void setReduce_cost(Integer reduce_cost) {
		this.reduce_cost = reduce_cost;
	}

}
