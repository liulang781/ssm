package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;



public interface ProductDao {
    /**
     * 查询所有商品
     * @return
     */
    @Select("select * from product")
    List<Product> findAllProduct();

    /**
     * 添加产品信息
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void saveProduct(Product product);

    /**
     * 通过id查询产品
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    Product findById(String id);
}
