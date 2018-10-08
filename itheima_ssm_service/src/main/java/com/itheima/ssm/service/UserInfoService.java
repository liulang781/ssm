package com.itheima.ssm.service;



import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserInfoService extends UserDetailsService {


    /**
     * 查询所有的用户
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 根据id查询user
     * @param id
     * @return
     */
    UserInfo findById(String id);

    /**
     * 添加用户
     * @param userInfo
     */
    void save(UserInfo userInfo);


    /**
     * 通过userId查询user信息
     * 并查询所有角色信息封装到userInfo中
     * @param id
     * @return
     */
    UserInfo findUserByIdAndAllRole(String id);


    /**
     * 删除userId下的role
     * @param userId
     */
    void delRoleById(String userId);

    void addRoleToUser(String userId, String[] ids);
}
