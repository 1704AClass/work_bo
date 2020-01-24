package com.health.mapper;

import com.health.pojo.TPermission;
import com.health.pojo.TPermissionExample;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TPermissionMapper {
    int countByExample(TPermissionExample example);

    int deleteByExample(TPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    List<TPermission> selectByExample(TPermissionExample example);

    TPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TPermission record, @Param("example") TPermissionExample example);

    int updateByExample(@Param("record") TPermission record, @Param("example") TPermissionExample example);

    int updateByPrimaryKeySelective(TPermission record);

    int updateByPrimaryKey(TPermission record);

    @Select("select p.* from t_permission p,t_role_permission rp where p.id = rp.permission_id and rp.role_id = #{id}")
	Set<TPermission> findByRoleId(@Param("id")Integer id);
}