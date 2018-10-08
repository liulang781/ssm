package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;

import java.util.List;

public interface RoleService {
    /**
     * 查询所有的角色
     * @return
     */
    List<Role> findAll(int page,int size);

    /**
     * 通过id查询角色
     * @param id
     * @return
     */
    Role findById(String id);

    void save(Role role);

    Role findRoleByIdAndAllPermission(String id);

    void delPermissionById(String roleId);

    void addPermissionToRole(String roleId, String[] ids);
}
