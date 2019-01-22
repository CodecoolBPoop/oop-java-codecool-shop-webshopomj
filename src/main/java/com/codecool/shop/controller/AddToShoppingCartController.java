package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/cart"})
public class AddToShoppingCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ShoppingCartDao cartDataStore = ShoppingCartDaoMem.getInstance();

        String indexOfItem = req.getParameter("incoming");
        int intIndex = Integer.parseInt(indexOfItem) -1;

        // new object attributes
        int orderId = ShoppingCart.numOfItemsInShoppingCart += 1;
        int id = productDataStore.getAll().get(intIndex).getId();
        String name = productDataStore.getAll().get(intIndex).getName();
        float price = productDataStore.getAll().get(intIndex).getPr();
        String description = productDataStore.getAll().get(intIndex).getDescription();
        String curr = productDataStore.getAll().get(intIndex).curr();
        ProductCategory prcat = productDataStore.getAll().get(intIndex).getProductCategory();
        Supplier supp = productDataStore.getAll().get(intIndex).getSupplier();

        // construct new Product object to add to Shopping Cart
        Product itemIntoShoppingCart = new Product(orderId, name, price, curr, description, prcat, supp);

        ((ShoppingCartDaoMem) cartDataStore).addToShoppingCart(itemIntoShoppingCart, id);

        /*
        System.out.println("    id to add:     :" + id);
        System.out.println("    NEW ITEM TO CART    :" + itemIntoShoppingCart);
        System.out.println("    THIS MANY ITEMS IN CART     :" + ShoppingCart.numOfItemsInShoppingCart);
        System.out.println("    THESE ARE IN CART     :" + cartDataStore.getAll() + "\n");
        */
        resp.sendRedirect("index.html");

    }
}
