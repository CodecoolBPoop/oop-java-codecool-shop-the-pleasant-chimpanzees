package com.codecool.shop.model;

import java.util.ArrayList;

public class UserCart {

    ArrayList<Product> userCart;

    public UserCart() {
        userCart = new ArrayList<>();
    }

    public void addToCart(Product product) {
        userCart.add(product);
    }
}
