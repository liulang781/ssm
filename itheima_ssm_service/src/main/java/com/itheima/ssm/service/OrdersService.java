package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;

import java.util.List;

public interface OrdersService {

    /**
     * 查询所有订单
     * @return
     */
    List<Orders> findAllOrders(Integer page, Integer size);

    /**
     * 通过id查询订单
     * @param id
     * @return
     */
    Orders findById(String id);

}
