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
    ProductCategory productCategory = new ProductCategory("superhero","comic book","???");
    Supplier supplier = new Supplier("marvel", "comoc book");

    @BeforeAll
    void init() {
        DBUtil.ConnectionData data = new DBUtil.ConnectionData(
                "src/Data/prod_config.txt",
                "src/Data/test_config.txt");

        DBUtil.getInstance().configure(data);
        testConnection = DBUtil.getInstance().getTestConnection();
    }

    @Test
    void addToCartTest(){
        User user = new User(8);
        Cart cart = new Cart(user,4);
        CartDaoJdbc cartDaoJdbc = CartDaoJdbc.getInstance();
        cartDaoJdbc.addToCart(4, 2, 3);


        Assertions.assertEquals(user.getId(), cartDaoJdbc.findCart(4).getUserId());
        //Assertions.assertEquals(cart.getId(), cartDaoJdbc.findCart(4).getId());
    }
}
