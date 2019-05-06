package com.bluekjg.wxapp.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.WxInvoice;

/**
 * @description：电子发票数据库控制层接口
 * @author：pincui.tom
 * @date：2019/04/03 14:51
 */
public interface WxInvoiceMapper extends BaseMapper<WxInvoice> {
	/**
	 * 保存发票记录
	 * @param invoice
	 * @return
	 */
	boolean insertOrderInvoice(WxInvoice invoice);
}