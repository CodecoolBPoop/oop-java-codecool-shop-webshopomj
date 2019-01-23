package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends BaseModel {
    public static List<Product> productInShoppingCart = new ArrayList<>();
    public static int numOfItemsInShoppingCart = 0;

    private int productId;
    private int amount;
    private float price;


    public ShoppingCart() {
    }

    public ShoppingCart(int productId, int amount, float price) {
        this.productId = productId;
        this.amount = amount;
        this.price = price;
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
                "productId=" + productId +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
