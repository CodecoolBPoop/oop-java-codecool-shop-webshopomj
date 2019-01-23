package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

public interface SupplierDao {

    void add(Supplier supplier);
    static Supplier find(int id) {return null;}
    void remove(int id);

    static List<Supplier> getAll() {return null;}
}
