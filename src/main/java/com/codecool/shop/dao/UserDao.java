package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.User;

public interface UserDao {

    void add(User user);
    User findByEmail(String email);
    User findByPw(String pw);

}
