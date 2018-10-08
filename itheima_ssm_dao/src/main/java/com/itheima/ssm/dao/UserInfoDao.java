package com.itheima.ssm.dao;


import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UserInfoDao {
    /**
     * 根据用户名查询信息
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.RoleDao.findByUserId")),
    })
    UserInfo  findByUsername(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Select("select * from users where id = #{id}")
    @ResultMap("userMap")
    UserInfo findById(String id);

    @Insert("insert into users(email,username,password,phoneNum,status)values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id in (select userId from users_role where roleId=#{roleId})")
    List<UserInfo> findByRoleId(String roleId);

    @Delete("delete from users_role where userId = #{id}")
    void delRoleById(String id);

   @Insert("insert into users_role(userId,roleId)values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
