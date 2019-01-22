package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoMem implements ShoppingCartDao {
    private List<Product> allProductsInShoppingCart = new ArrayList<>();
    private static ShoppingCartDaoMem instance = null;
    private int numOfItemsInCart;

    public int getNumOfItemsInCart(){
        return this.numOfItemsInCart;
    }

    public void reduceNumOfItemsInCart(){
        numOfItemsInCart -=1;
    }


    private ShoppingCartDaoMem(){

    }


    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }


    @Override
    public void addToShoppingCart(Product product) {
        product.setId(allProductsInShoppingCart.size());
        allProductsInShoppingCart.add(product);
    }

    public void addToShoppingCart(Product product, int id) {
        product.setId(id);
        allProductsInShoppingCart.add(product);
        numOfItemsInCart +=1;
    }

    @Override
    public Product find(int id) {
        return allProductsInShoppingCart.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }


    @Override
    public void removeItemFromShoppingCart(int orderID) {
        for (int i = 0; i < allProductsInShoppingCart.size(); i++) {
            if (allProductsInShoppingCart.get(i).getOrderID() == orderID) {
                allProductsInShoppingCart.remove(i);
            }
        }
    }

    @Override
    public List<Product> getAllProductsInShoppingCart() {
        return allProductsInShoppingCart;
    }

    public int getItemsInCart(){
        return numOfItemsInCart;
    }
}
