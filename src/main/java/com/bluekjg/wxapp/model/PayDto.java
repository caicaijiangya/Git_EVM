package com.bluekjg.wxapp.model;

public class PayDto {
	
	//交易订单号
	private String outTradeNo;
	
	//退款单号
	private String outRefundNo;
	
	//是否使用立减金
	private boolean isCoupon;
	
	//优惠金额
	private String couponFee;

	public PayDto() {
	}
	
	
	public PayDto(String outTradeNo) {
		super();
		this.outTradeNo = outTradeNo;
	}



	public String getOutRefundNo() {
		return outRefundNo;
	}


	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}


	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public boolean isCoupon() {
		return isCoupon;
	}

	public void setCoupon(boolean isCoupon) {
		this.isCoupon = isCoupon;
	}

	public String getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(String couponFee) {
		this.couponFee = couponFee;
	}

}
