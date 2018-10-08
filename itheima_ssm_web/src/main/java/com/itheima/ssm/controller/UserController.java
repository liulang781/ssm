package com.itheima.ssm.controller;


import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.RoleService;
import com.itheima.ssm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAllUser(){
        ModelAndView mav= new ModelAndView();
        List<UserInfo> userList = userInfoService.findAll();
        mav.addObject("userList",userList);
        mav.setViewName("user-list");
        return mav;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mav= new ModelAndView();
        UserInfo userInfo=userInfoService.findById(id);
        mav.addObject("userInfo",userInfo);
        mav.setViewName("user-detail");
        return mav;

    }

    @RequestMapping("/save.do")
    public String saveUser(UserInfo userInfo){
        userInfoService.save(userInfo);
        return "redirect:findAll.do";

    }

    /**
     * 通过userId查询所有的角色
     * @param id
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mav = new ModelAndView();
        UserInfo userInfo = userInfoService.findUserByIdAndAllRole(id);
        List<Role> roles = roleService.findAll(1, 4);
        mav.addObject("user",userInfo);
        mav.addObject("roleList",roles);
        mav.setViewName("user-role-add");
        return mav;
    }

    /**
     * 删除userId的roleId
     * 添加新的roleId
     * @param userId
     * @param ids
     * @return
     */
    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId") String userId,@RequestParam(name = "ids")String[] ids){
//        ModelAndView mav = new ModelAndView();
        userInfoService.delRoleById(userId);
        userInfoService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }

}
