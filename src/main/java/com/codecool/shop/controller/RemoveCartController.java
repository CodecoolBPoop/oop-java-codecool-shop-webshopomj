package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ShoppingCartDaoJdbc;

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
        ShoppingCartDaoJdbc shoppingCartDaoJdbc = new ShoppingCartDaoJdbc();

        String id = req.getParameter("id");
        shoppingCartDaoJdbc.remove(Integer.parseInt(id));
    }
}
