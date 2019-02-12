package com.codecool.shop.model;


public class Cart {

    private int id;

    private int userId;

    public Cart(User user){
        this.userId = user.getId();
    }

    public void setId(int id){
        this.id = id;
    }
}
