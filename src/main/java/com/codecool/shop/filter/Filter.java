package com.codecool.shop.filter;

import com.codecool.shop.builder.ProductBuilder;
import com.codecool.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class Filter {

    private ProductBuilder productBuilder = new ProductBuilder();

    public List<Product> filterRequest(HttpServletRequest req) {

        List<Product> products = new ArrayList<>();

        if (!req.getParameterNames().hasMoreElements() ||
                (isParameterValueEqualsToAll(req, "category") && isParameterValueEqualsToAll(req, "supplier"))) {
            products.addAll(productBuilder.getAllProducts());
        } else if (isParameterValueEqualsToAll(req, "supplier")) {
            if (isParameterValueEqualsTo(req, "category", "Tablet")) {
                products.addAll(productBuilder.getProductsByCategory("Tablet"));
            } else if (isParameterValueEqualsTo(req, "category", "Phone")) {
                products.addAll(productBuilder.getProductsByCategory("Phone"));
            }
        } else if (isParameterValueEqualsToAll(req, "category")) {
            if (isParameterValueEqualsTo(req, "supplier", "Amazon")) {
                products.addAll(productBuilder.getProductsBySupplier("Amazon"));
            } else if (isParameterValueEqualsTo(req, "supplier", "Lenovo")) {
                products.addAll(productBuilder.getProductsBySupplier("Lenovo"));
            } else if (isParameterValueEqualsTo(req, "supplier", "Samsung")) {
                products.addAll(productBuilder.getProductsBySupplier("Samsung"));
            }
        } else if (isParameterValueEqualsTo(req, "category", "Tablet")) {
            if (isParameterValueEqualsTo(req, "supplier", "Amazon")) {
                products.addAll(productBuilder.getProductByCategoryAndSupplier("Tablet", "Amazon"));
            } else if (isParameterValueEqualsTo(req, "supplier", "Lenovo")) {
                products.addAll(productBuilder.getProductByCategoryAndSupplier("Tablet", "Lenovo"));
            } else if (isParameterValueEqualsTo(req, "supplier", "Samsung")) {
                products.addAll(productBuilder.getProductByCategoryAndSupplier("Tablet", "Samsung"));
            }
        } else if (isParameterValueEqualsTo(req, "category", "Phone")) {
            if (isParameterValueEqualsTo(req, "supplier", "Amazon")) {
                products.addAll(productBuilder.getProductByCategoryAndSupplier("Phone", "Amazon"));
            } else if (isParameterValueEqualsTo(req, "supplier", "Lenovo")) {
                products.addAll(productBuilder.getProductByCategoryAndSupplier("Phone", "Lenovo"));
            } else if (isParameterValueEqualsTo(req, "supplier", "Samsung")) {
                products.addAll(productBuilder.getProductByCategoryAndSupplier("Phone", "Samsung"));
            }
        }

        return products;
    }

    private boolean isParameterValueEqualsTo(HttpServletRequest req, String name, String valueToCheck) {
        return req.getParameter(name).equals(valueToCheck);
    }

    private boolean isParameterValueEqualsToAll(HttpServletRequest req, String name) {
        return req.getParameter(name).equals("All");
    }
}
