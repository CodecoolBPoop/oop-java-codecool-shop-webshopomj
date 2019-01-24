package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.ShoppingCart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ShoppingCartDaoJdbc extends DaoJdbc implements ShoppingCartDao {
    private ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
    private SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();

    @Override
    public void add(Product product) {
        if (isProductInCart(product.getId())) {
            String query = "UPDATE shopping_cart SET amount = amount +1 WHERE product_id = '" + product.getId() + "';";
            executeQuery(query);
        } else {
            String query = "INSERT INTO shopping_cart (product_id, amount, price)" +
                    "VALUES ('" + product.getId() + "', " + 1 + ", " + product.getPrice() + ");";
            executeQuery(query);
        }
    }

    @Override
    public void remove(int id) {
        String query = "UPDATE shopping_cart SET amount = amount - 1 WHERE product_id = '" + id + "'; DELETE FROM shopping_cart WHERE amount <= 0";
        executeQuery(query);
    }



    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product find(int productId) {
        return null;
    }

    public Boolean isProductInCart(int productID) {
        String query = "SELECT * FROM shopping_cart WHERE product_id = '" + productID + "';";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public List<ShoppingCart> getShoppingCart() {
        String query = "SELECT * FROM shopping_cart;";

        List<ShoppingCart> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                ShoppingCart result = new ShoppingCart(
                        resultSet.getInt("product_id"),
                        resultSet.getInt("amount"),
                        resultSet.getFloat("price")
                );
                resultList.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

}
