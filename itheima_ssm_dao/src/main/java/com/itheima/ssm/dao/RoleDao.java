package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface RoleDao {

    /**
     * 通过多表关联userId查询role
     * @param userId
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId} )")
    @Results( id = "roleMap",value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.PermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId);


    @Select("select * from role")
    List<Role> findAll();

    /**
     * 通过id查询role
     * @param id
     * @return
     */
    @Select("select * from  role where id =#{id}")
    @ResultMap("roleMap")
    Role findById(String id);

    @Insert("insert into role (roleName,roleDesc)values(#{roleName},#{roleDesc})")
    void save(Role role);


    @Select("select * from role where id in (select roleId from role_permission where permissionId=#{permissionId})")
    List<Role> findByPermissionId(String permissionId);

    @Delete("delete from role_permission where roleId = #{roleId}")
    void delPermissionById(String roleId);

    @Insert("insert into role_permission(permissionId,roleId)values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
