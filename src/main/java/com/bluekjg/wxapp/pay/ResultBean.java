package com.bluekjg.wxapp.pay;
/*
 * 页面返回结果
 */
public class ResultBean {
	
	// 1 页面作用 0 显示失败，显示成功
	private int result;
	// 2 页面内容
	private String info;
	// 3 该页面的标题
	private String title;
	// 4 要定向的位置
	private String url;
	//微信支付商户单号
	private String paymentNo;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	
}
