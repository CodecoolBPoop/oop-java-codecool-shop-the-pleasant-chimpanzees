package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import java.util.List;

public interface CartDao{

    void addToCart(Product product, User user);
    void removeFromCart(int id);
    void emptyCart();
    Product find(int id);

    List<Product> getAll();


}
