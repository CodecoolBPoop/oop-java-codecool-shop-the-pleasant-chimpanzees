package com.codecool.shop.model;

public class User {

    public int id;
    public String email;
    public String address;
    public String city;
    public String state;
    public int zip;


    public User(){}

    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
