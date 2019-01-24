package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ShoppingCartDaoJdbc;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartDao cartDataStore = ShoppingCartDaoMem.getInstance();
        ShoppingCartDaoJdbc shoppingCartDaoJdbc = new ShoppingCartDaoJdbc();

        TemplateEngine engine3 = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context3 = new WebContext(req, resp, req.getServletContext());
        context3.setVariable("checkout", shoppingCartDaoJdbc.getShoppingCart());

        engine3.process("checkout.html", context3, resp.getWriter());

    }
}
