package com.codecool.shop.builder;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.Product;

import java.util.List;

public class ProductBuilder {

    private ProductCategoryDao productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
    private SupplierDao supplierDaoJdbc = new SupplierDaoJdbc();
    private ProductDao productDaoJdbc = new ProductDaoJdbc();

    public List<Product> getAllProducts() {
        return productDaoJdbc.getAll();
    }

    public List<Product> getProductsByCategory(String categoryName) {
        return productDaoJdbc.getBy(productCategoryDaoJdbc.find(categoryName));
    }

    public List<Product> getProductsBySupplier(String supplierName) {
        return productDaoJdbc.getBy(supplierDaoJdbc.find(supplierName));
    }

    public List<Product> getProductByCategoryAndSupplier(String categoryName, String supplierName) {
        return productDaoJdbc.getBy(productCategoryDaoJdbc.find(categoryName), supplierDaoJdbc.find(supplierName));
    }
}
