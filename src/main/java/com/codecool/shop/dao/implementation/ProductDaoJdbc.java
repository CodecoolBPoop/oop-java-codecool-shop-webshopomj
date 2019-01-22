package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;

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
                        (resultSet.getInt("product_category_id")),
                        resultSet.getInt("supplier_id"));
                return result;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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
