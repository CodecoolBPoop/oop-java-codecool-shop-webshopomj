package com.codecool.shop.controller;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ShoppingCartDaoJdbc;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/rem_cart"})
public class RemoveCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ShoppingCartDao cartDataStore = ShoppingCartDaoMem.getInstance();
        ShoppingCartDaoJdbc shoppingCartDaoJdbc = new ShoppingCartDaoJdbc();

        String id = req.getParameter("orderID");
        //System.out.println("Removing this item: " + shoppingCartDaoJdbc.find(Integer.parseInt(id)));
        shoppingCartDaoJdbc.remove(Integer.parseInt(id));
        //System.out.println("IN CART AFTER REMOVE: " + shoppingCartDaoJdbc.getAll() + "\n");

    }
}
