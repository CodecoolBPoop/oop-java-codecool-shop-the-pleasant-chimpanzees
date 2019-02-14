package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.database.UserDaoJdbc;
import com.codecool.shop.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDaoJdbcTest {

    Connection testConnection;
    DBUtil.ConnectionData data;

    @BeforeAll
    void init() {
        data = new DBUtil.ConnectionData(
                "src/Data/prod_config.txt",
                "src/Data/test_config.txt");

        DBUtil.getInstance().connect(data);
        testConnection = DBUtil.getInstance().getTestConnection();
    }

    @Test
    void AddTest(){
        User user = new User("kiskutya", "vauvau");
        UserDaoJdbc userDaoJdbc = UserDaoJdbc.getInstance();
        userDaoJdbc.add(user);

        Assertions.assertEquals(user.getEmail(), userDaoJdbc.findByEmail("kiskutya").emailToString());
        Assertions.assertEquals(user.getPassword(), userDaoJdbc.findByPw("vauvau").pwToString());
    }

}
