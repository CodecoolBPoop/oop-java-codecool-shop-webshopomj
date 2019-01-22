package com.codecool.shop.controller;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/removeItemFromShoppingCart"})
public class RemoveItemFromShoppingCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingCartDao cartDataStore = ShoppingCartDaoMem.getInstance();

        int orderIdOfRemovableItem = Integer.parseInt(request.getParameter("orderIDOfChosenItemOfShoppingCArt"));

        cartDataStore.removeItemFromShoppingCart(orderIdOfRemovableItem);
        ((ShoppingCartDaoMem) cartDataStore).reduceNumOfItemsInCart();

        response.sendRedirect("shoppingcart");
    }
}
