package com.bluekjg.wxapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxBrandStory;
import com.bluekjg.wxapp.model.WxOrder;
import com.bluekjg.wxapp.model.WxStore;

/**
 * @description：门店信息表数据库控制层接口
 * @author：pincui.tom
 * @date：2018/7/27 14:51
 */
public interface WxBrandMapper extends BaseMapper<WxBrandStory> {

	/**
	 * 获取品牌故事列表
	 * @param dto
	 * @return
	 */
	List<WxBrandStory> queryBrandList(PagingDto dto);

	/**
	 * 获取品牌故事详情
	 * @param id
	 * @return
	 */
	WxBrandStory queryBrandDetail(PagingDto dto);

	/**
	 * 更新浏览量
	 * @param dto
	 * @return
	 */
	Integer modifyBrandInfo(PagingDto dto);

	/**
	 * 点赞操作
	 * @param page
	 * @return
	 */
	Integer insertBrandZanInfo(PagingDto page);

	/**
	 * 取消点赞
	 * @param page
	 * @return
	 */
	Integer delBrandZanInfo(PagingDto page);
	
}