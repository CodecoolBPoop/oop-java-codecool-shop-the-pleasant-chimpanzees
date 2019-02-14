package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import java.util.List;

public interface CartDao{

    void addToCart(int cartId, int productId);
    void add(int userId);
    void removeFromCart(int id);
    void removeFromCart(int cartId, int productId);
    void emptyCart(int cartId);
    Cart findCart(int cartId);
    Product find(int id);
    int getCartIdByEmail(String email);

    List<Product> getAll();
}
