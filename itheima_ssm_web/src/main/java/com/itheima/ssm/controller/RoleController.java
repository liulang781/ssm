package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.PermissionService;
import com.itheima.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAllRole(@RequestParam(name = "page",required = true,defaultValue = "1")int page,@RequestParam(name = "size",required = true,defaultValue = "4")int size){
        ModelAndView mav= new ModelAndView();
        List<Role> roleList = roleService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(roleList);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("role-list");
        return mav;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mav= new ModelAndView();
        Role role=roleService.findById(id);
        mav.addObject("role",role);
        mav.setViewName("role-detail");
        return mav;
    }

    @RequestMapping("/save.do")
    public String saveRole(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        ModelAndView mav = new ModelAndView();
        Role role=roleService.findRoleByIdAndAllPermission(id);
        List<Permission> permissions = permissionService.findAll();
        mav.addObject("role",role);
        mav.addObject("permissionList",permissions);
        mav.setViewName("role-permission-add");
        return mav;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId") String roleId,@RequestParam(name = "ids")String[] ids){
//        ModelAndView mav = new ModelAndView();
        roleService.delPermissionById(roleId);
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll.do";
    }



}
