package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {

    void addToShoppingCart(Product product);
    Product find(int id);
    void removeItemFromShoppingCart(int id);

    List<Product> getAllProductsInShoppingCart();
}
