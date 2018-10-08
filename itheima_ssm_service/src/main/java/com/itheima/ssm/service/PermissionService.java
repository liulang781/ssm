package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {

    /**
     * 查询所有的权限
     * @return
     */
    List<Permission> findAll();

    void save(Permission permission);
}
