package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc extends DaoJdbc implements ProductDao {

    private ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
    private SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();

    private void getProductResult(String query, List<Product> resultList) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Product result = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategoryDaoJdbc.find(resultSet.getInt("product_category_id")),
                        supplierDaoJdbc.find(resultSet.getInt("supplier_id")));
                resultList.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Product product) {
        String query = "INSERT INTO products " +
                "(name, default_price, currency, description, product_category_id, supplier_id)" +
                "VALUES ('"
                + product.getName() + "', '"
                + product.getDefaultPrice() + "', '"
                + product.getDefaultCurrency() + "', '"
                + product.getDescription() + "', '"
                + product.getProductCategory().getId() + "', '"
                + product.getSupplier().getId() + "');";
        executeQuery(query);
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM products WHERE id = '" + id + "';";

        try (Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        ) {
            if (resultSet.next()) {
                Product result = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategoryDaoJdbc.find(resultSet.getInt("product_category_id")),
                        supplierDaoJdbc.find(resultSet.getInt("supplier_id")));
                return result;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM products WHERE id = '" + id + "';";
        executeQuery(query);
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products;";

        List<Product> resultList = new ArrayList<>();

        getProductResult(query, resultList);

        return resultList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM products WHERE supplier_id = '" + supplier.getId() + "';";

        List<Product> resultList = new ArrayList<>();

        getProductResult(query, resultList);

        return resultList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM products WHERE product_category_id = '" + productCategory.getId() + "';";

        List<Product> resultList = new ArrayList<>();

        getProductResult(query, resultList);

        return resultList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory, Supplier supplier) {
        String query = "SELECT * FROM products WHERE product_category_id = '" + productCategory.getId()
                        + "' AND supplier_id = '" + supplier.getId() + "';";

        List<Product> resultList = new ArrayList<>();

        getProductResult(query, resultList);

        return resultList;
    }
}
