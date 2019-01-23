package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.controller.AddCartController;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoMem implements ShoppingCartDao {
    private List<Product> data = new ArrayList<>();
    private static ShoppingCartDaoMem instance = null;
    public static int itemsInCart;

    private ShoppingCartDaoMem(){

    }


    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Product product) {
        product.setId(data.size());
        data.add(product);
    }

    public void add(Product product, int id) {
        product.setId(id);
        data.add(product);
        itemsInCart +=1;
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getOrderID() == id).findFirst().orElse(null);
    }


    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }

    public int getItemsInCart(){
        return itemsInCart;
    }
}
