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

        int id = Integer.parseInt(req.getParameter("incoming"));

        Product tofind = productDataStore.find(id);
        System.out.println(tofind.hashCode());

        cartDataStore.add(tofind);

        List<Product> productsincart = cartDataStore.getAll();

        Map<Integer, Integer> map = new HashMap<>();

        for (Product student : productsincart) {
            Integer key  = student.getId();
            if(map.containsKey(key)){
                Integer idd = map.get(key);
                map.put(key, idd +1);

            }else{
                Integer amount = 1;
                map.put(key, amount);
            }
        }

        System.out.println(map);

        resp.sendRedirect("index.html");
    }
}
