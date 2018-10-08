package com.itheima.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAllOrders(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) {

        ModelAndView mav = new ModelAndView();
        List<Orders> ordersList = ordersService.findAllOrders(page, size);
        //PageInfo就是一个分页Bean(PageBean)
        PageInfo pf = new PageInfo(ordersList);
        mav.addObject("pageInfo", pf);
        mav.setViewName("orders-list");
        return mav;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        Orders orders = ordersService.findById(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("orders", orders);
        mav.setViewName("order-detail");
        return mav;
    }


}
