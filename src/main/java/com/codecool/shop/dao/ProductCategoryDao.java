package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    void add(ProductCategory category);
    static ProductCategory find(int id) {return null;}
    void remove(int id);

    List<ProductCategory> getAll();

}
