package com.codecool.shop.model;

public class User {

    private int id;
    private String email;
    private String address;
    private String city;
    private String state;
    private Integer zip;
    private String password;

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

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}
