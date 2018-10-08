package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {

    /**
     * travellers表和orders表示多对多的关系需要建立第三张表来关联两张表
     * 第三张表的两个字段分别是该两张表的Id,所以通过ordersId 可以查询到travellers表的数据
     * @param orderId
     * @return
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{orderId})")
    List<Traveller> findByOrderId(String orderId);
}
