package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/cart"})
public class AddCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ShoppingCartDao cartDataStore = ShoppingCartDaoMem.getInstance();

        int idOfProd = Integer.parseInt(req.getParameter("incoming"));
        Product itemOfProdStore = productDataStore.find(idOfProd);

        cartDataStore.add(itemOfProdStore);
        ((ShoppingCartDaoMem) cartDataStore).updateNumOfItemsInCart();

        List<Product> productsInCart = cartDataStore.getAll();

        Map<Integer, Integer> itemFreq = new HashMap<>();
        for (Product student : productsInCart) {
            Integer key  = student.getId();
            if(itemFreq.containsKey(key)){
                Integer amount = itemFreq.get(key);
                itemFreq.put(key, amount +1);
            }else{
                Integer amount = 1;
                itemFreq.put(key, amount);
            }
        }

        System.out.println(itemFreq);
        System.out.println(((ShoppingCartDaoMem) cartDataStore).getItemsInCart()  + " item/s in Shopping Cart\n");

        resp.sendRedirect("index.html");
    }
}
