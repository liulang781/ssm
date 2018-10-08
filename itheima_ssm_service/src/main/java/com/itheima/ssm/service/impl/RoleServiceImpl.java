package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
        import com.itheima.ssm.dao.RoleDao;
        import com.itheima.ssm.domain.Role;
        import com.itheima.ssm.service.RoleService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findRoleByIdAndAllPermission(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void delPermissionById(String roleId) {
        roleDao.delPermissionById(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String permissionId : ids) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
