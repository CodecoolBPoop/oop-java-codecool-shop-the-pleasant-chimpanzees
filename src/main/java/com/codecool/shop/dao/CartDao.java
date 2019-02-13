package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import java.util.List;

public interface CartDao{

    void addToCart(int cartId, int productId);
    void removeFromCart(int cartId, int productId);
    void emptyCart(int cartId);
    Cart findCart(int cartId);

    List<Product> getAll();
}
