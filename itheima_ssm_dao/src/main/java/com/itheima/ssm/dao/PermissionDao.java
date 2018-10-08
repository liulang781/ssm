package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findByRoleId(String roleId);



    @Select("select * from permission")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "permissionName", property = "permissionName"),
            @Result(column = "url", property = "url"),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "com.itheima.ssm.dao.RoleDao.findByPermissionId"))
    })
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url)values(#{permissionName},#{url})")
    void save(Permission permission);
}
