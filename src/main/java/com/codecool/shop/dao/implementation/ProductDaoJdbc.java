package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private static final String DATABASE = System.getenv("DATABASE");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    @Override
    public void add(Product product) {
        String query = "INSERT INTO products " +
                "(name, default_price, currency, description, product_category_id, supplier_id)" +
                "VALUES ('"
                + product.getName() + "', '"
                + product.getDefaultPrice() + "', '"
                + product.getDefaultCurrency() + "', '"
                + product.getDescription() + "', '"
                + 1 + "', '"
                + 1 + "');";
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
                        ProductCategoryDaoJdbc.find(resultSet.getInt("product_category_id")),
                        SupplierDaoJdbc.find(resultSet.getInt("supplier_id")));
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
        String query = "SELCET * FROM products;";

        List<Product> resultList = new ArrayList<>();

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
                        ProductCategoryDaoJdbc.find(resultSet.getInt("product_category_id")),
                        SupplierDaoJdbc.find(resultSet.getInt("supplier_id")));
                resultList.add(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM products WHERE supplier_id = '" + supplier.getId() + "';";

        List<Product> resultList = new ArrayList<>();

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
                        ProductCategoryDaoJdbc.find(resultSet.getInt("product_category_id")),
                        SupplierDaoJdbc.find(resultSet.getInt("supplier_id")));
                resultList.add(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM products WHERE product_category_id = '" + productCategory.getId() + "';";

        List<Product> resultList = new ArrayList<>();

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
                        ProductCategoryDaoJdbc.find(resultSet.getInt("product_category_id")),
                        SupplierDaoJdbc.find(resultSet.getInt("supplier_id")));
                resultList.add(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
    }

}
