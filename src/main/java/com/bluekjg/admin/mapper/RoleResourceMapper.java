package com.bluekjg.admin.mapper;

import java.io.Serializable;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bluekjg.admin.model.RoleResource;

/**
 *
 * RoleResource 表数据库控制层接口
 *
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

    @Select("SELECT e.id AS id FROM wxapp_role r LEFT JOIN wxapp_role_resource e ON r.id = e.role_id WHERE r.id = #{id}")
    Long selectIdListByRoleId(@Param("id") Long id);

    @Delete("DELETE FROM wxapp_role_resource WHERE resource_id = #{resourceId}")
    int deleteByResourceId(@Param("resourceId") Serializable resourceId);

}