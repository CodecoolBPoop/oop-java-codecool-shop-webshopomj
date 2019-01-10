package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
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

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariables(params);
        context.setVariable("recipient", "World");
        context.setVariable("category", productCategoryDataStore.getAll());
        context.setVariable("supplier", supplierDataStore.getAll());

        if (!req.getParameterNames().hasMoreElements() || req.getParameterValues(req.getParameterNames().nextElement())[0].equals("All")) {
            context.setVariable("products", productDataStore.getAll());
        } else if (req.getParameterNames().nextElement().equals("category")) {
            if (req.getParameter("category").equals("Tablet")) { //productCategoryDataStore.getAll().get(0).getName()
                context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
            } else if (req.getParameter("category").equals("Phone")) {
                context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(2)));
            }
        } else if (req.getParameterNames().nextElement().equals("supplier")) {
            if (req.getParameter("supplier").equals("Amazon")) {
                context.setVariable("products", productDataStore.getBy(supplierDataStore.find(1)));
            } else if (req.getParameter("supplier").equals("Lenovo")) {
                context.setVariable("products", productDataStore.getBy(supplierDataStore.find(2)));
            }
        }

        engine.process("product/index.html", context, resp.getWriter());
    }

}
