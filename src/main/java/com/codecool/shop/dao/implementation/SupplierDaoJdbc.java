package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private static final String DATABASE = System.getenv("DATABASE");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO suppliers (name, description)" +
                "VALUES ('" + supplier.getName() + "', " + supplier.getDescription() + "');";
        executeQuery(query);
    }

    public static Supplier find(int id) {
        String query = "SELECT * FROM suppliers WHERE id = '" + id + "';";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            if (resultSet.next()) {
                Supplier result = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
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
        String query = "DELETE FROM suppliers WHERE id = '" + id + "';";
        executeQuery(query);
    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM suppliers;";

        List<Supplier> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            if (resultSet.next()) {
                Supplier result = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
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

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
    }
}
