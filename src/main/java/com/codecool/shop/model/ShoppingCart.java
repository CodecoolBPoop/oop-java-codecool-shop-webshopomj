package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends BaseModel {
    public static List<Product> productInShoppingCart = new ArrayList<>();
    public static int numOfItemsInShoppingCart = 0;


    public ShoppingCart() {
    }


    public List<Product> getProductInShoppingCart() {
        return productInShoppingCart;
    }

    public void setProductInShoppingCart(List<Product> productInShoppingCart) {
        this.productInShoppingCart = productInShoppingCart;
    }

    // adding
    public void addToShoppingCart(Product productToAdd) {
        //productToAdd.id = numOfItemsInShoppingCart;
        numOfItemsInShoppingCart += 1;
        productInShoppingCart.add(productToAdd);
    }

    // removing from shopping cart


    @Override
    public String toString() {
        return "ShoppingCart{" +
                "productInShoppingCart=" + productInShoppingCart +
                '}';
    }
}
