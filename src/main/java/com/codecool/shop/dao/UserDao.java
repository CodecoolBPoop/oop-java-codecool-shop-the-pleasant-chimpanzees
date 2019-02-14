package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {

    void add(User user);
    User find(String email);
    User findByEmail(String email);
    User findByPw(String pw);

}
