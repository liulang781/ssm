package com.itheima.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")

public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
//    @RolesAllowed("ROLE_ADMIN")
    public ModelAndView FindAll(@RequestParam(name ="page",required = true,defaultValue = "1")int page,@RequestParam(name ="size",required = true,defaultValue = "4")int size) {
        List<Product> products = productService.findAllProduct(page,size);
        PageInfo pageInfo= new PageInfo(products);
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageInfo", pageInfo);
        mav.setViewName("product-list");
        return mav;
    }

    @RequestMapping("/save.do")
//    @Secured("ROLE_ADMIN")
    public String save(Product product) {
        productService.saveProduct(product);
        //重定向
        return "redirect:findAll.do";
    }

}
