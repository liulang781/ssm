package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.RoleDao;
import com.itheima.ssm.dao.UserInfoDao;
import com.itheima.ssm.domain.BCryptPasswordEncoderUtils;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userInfoDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建spring-security框架中的User对象
        User user = new User(userInfo.getUsername(),userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    @Override
    public UserInfo findById(String id) {
        return userInfoDao.findById(id);
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(BCryptPasswordEncoderUtils.getPasswordEncoder(userInfo.getPassword()));
        userInfoDao.save(userInfo);
    }

    @Override
    public UserInfo findUserByIdAndAllRole(String id) {
        UserInfo userInfo = userInfoDao.findById(id);
        return userInfo;
    }


    @Override
    public void delRoleById(String userId) {
        userInfoDao.delRoleById(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) {
        for (String roleId : ids) {
            userInfoDao.addRoleToUser(userId,roleId);
        }
    }
}
