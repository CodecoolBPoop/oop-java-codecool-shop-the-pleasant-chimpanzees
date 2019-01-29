package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.List;

public interface CartDao {

    void addToCart(Product product);
    void removeFromCart(int id);
    void emptyCart();
    Product find(int id);

    List<Product> getAll();
}
