package com.bluekjg.admin.mapper;

import com.bluekjg.admin.model.StaticFiles;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 静态资源文件表 Mapper 接口
 * </p>
 *
 * @author Tom
 * @since 2018-09-25
 */
public interface StaticFilesMapper extends BaseMapper<StaticFiles> {

	List<StaticFiles> selectFileList(StaticFiles files);
	
	boolean insertFiles(StaticFiles files);
	
	boolean deleteFilesId(@Param("id") Integer id);
	
	boolean deleteFilesRelation(StaticFiles files);
	
	Integer saveImgList(@Param("list") List<StaticFiles> sfList);

	Integer addFilePath(StaticFiles staticFiles);

	Integer saveActivityGoodsImg(List<StaticFiles> sfList);

	Integer saveIntegrlGoodsImgList(List<StaticFiles> sfList);

}