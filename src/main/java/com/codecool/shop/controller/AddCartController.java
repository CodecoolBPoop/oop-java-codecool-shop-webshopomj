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



        // From HTML
        int id = Integer.parseInt(req.getParameter("incoming"));

        // products
        Product tofind = productDataStore.find(id);
        System.out.println(tofind.hashCode());

        // add to cart
        cartDataStore.add(tofind);

        List<Product> productsincart = cartDataStore.getAll();

        Map<Integer, List<Product>> map = new HashMap<>();

        for (Product student : productsincart) {
            Integer key  = student.getId();
            if(map.containsKey(key)){
                List<Product> list = map.get(key);
                list.add(student);

            }else{
                List<Product> list = new ArrayList<Product>();
                list.add(student);
                map.put(key, list);
            }

        }

        System.out.println(map);




        resp.sendRedirect("index.html");
    }
}
