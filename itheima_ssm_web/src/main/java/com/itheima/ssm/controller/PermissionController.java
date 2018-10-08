package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mav= new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mav.addObject("permissionList",permissionList);
        mav.setViewName("permission-list");
        return mav;
    }
    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.save(permission);

        return "redirect:findAll.do";
    }

}
