package com.bluekjg.wxapp.service;

import com.baomidou.mybatisplus.service.IService;
import com.bluekjg.wxapp.model.WxInvoice;


/**
 * @description：电子发票
 * @author：pincui.Tom
 * @date：2019/04/03 14:51
 */
public interface IWxInvoiceService extends IService<WxInvoice> {
	/**
	 * 开具电子发票
	 * @param invoice
	 * @return 0失败，1开票处理中，2开票成功
	 */
	Integer openElectronicInvoice(WxInvoice invoice);
	/**
	 * 下载电子发票
	 * @param invoice
	 */
	Integer downloadElectronicInvoice(WxInvoice invoice);
}