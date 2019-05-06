package com.bluekjg.admin.sjljj;

public class Sku {
	
	
	//必填字段
	private Integer quantity;  //卡券库存的数量，上限为100000000

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
