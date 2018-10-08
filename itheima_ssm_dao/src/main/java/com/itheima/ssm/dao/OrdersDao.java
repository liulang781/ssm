package com.itheima.ssm.dao;


import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {

    /**
     * 查询所有订单
     *
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.itheima.ssm.dao.ProductDao.findById")),
    })
    List<Orders> findAllOrders();


    /**
     * 通过id查询订单详情
     *
     * @param ordersId
     * @return
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", javaType = Product.class, one = @One(select = "com.itheima.ssm.dao.ProductDao.findById")),
            @Result(column = "memberId", property = "member", javaType = Member.class, one = @One(select = "com.itheima.ssm.dao.MemberDao.findById")),
            @Result(column = "id", property = "travellers", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.TravellerDao.findByOrderId"))


    })
    Orders findById(String ordersId);
}
