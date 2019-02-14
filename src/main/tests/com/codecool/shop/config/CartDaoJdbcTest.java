package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.database.CartDaoJdbc;
import com.codecool.shop.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartDaoJdbcTest {

    Connection testConnection;
    DBUtil.ConnectionData data;

    @BeforeAll
    void init() {
        data = new DBUtil.ConnectionData(
                "src/Data/prod_config.txt",
                "src/Data/test_config.txt");

        DBUtil.getInstance().configure(data);
        testConnection = DBUtil.getInstance().getTestConnection();
    }

    @Test
    void addToCartTest(){
        User user = new User(2);
        Cart cart = new Cart(user,2);
        CartDaoJdbc cartDaoJdbc = CartDaoJdbc.getInstance();
        cartDaoJdbc.addToCart(2, 2, 2);

        Assertions.assertEquals(user.getId(), cartDaoJdbc.findCart(2).getUserId());
        Assertions.assertEquals(cart.getId(), cartDaoJdbc.findCart(2).getId());
    }

    @Test
    void removeFromCartTest(){
        CartDaoJdbc cartDaoJdbc = CartDaoJdbc.getInstance();
        cartDaoJdbc.removeFromCart(6,2);

    }
}
