package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;



public interface ProductService {

    /**
     * 查询所有商品
     * @return
     */
    List<Product> findAllProduct(int page ,int size);

    /**
     * 添加商品
     * @param product
     */
    void saveProduct(Product product);


    /**
     * 通过id查询产品
     * @param id
     * @return
     */
    Product findById(String id);
}
