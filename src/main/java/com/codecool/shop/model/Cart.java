package com.codecool.shop.model;


public class Cart {

    private int id;

    private int userId;

    public Cart(User user, int cartId){
        this.userId = user.getId();
        setId(cartId);
    }

    public Cart(){}

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

}
