package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface ProductDao {

    void add(Product product);
    Product find(int id);
    void remove(int id);

    static List<Product> getAll() {return null;}
    List<Product> getBy(Supplier supplier);
    List<Product> getBy(ProductCategory productCategory);

}
