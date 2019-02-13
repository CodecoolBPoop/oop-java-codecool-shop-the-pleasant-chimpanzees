package com.codecool.shop.model;

public class User {

    public int id;
    public String email;
    public String address;
    public String city;
    public String state;
    public int zip;

    public User(int id, String email, String address, String city, String state, int zip) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public User(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
