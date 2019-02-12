package com.codecool.shop.config;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilTest {

    @Test
    void testGetContext(){
        Connection connection = DBUtil.getInstance().getConnection();
        List<String> expected = Arrays.asList(
                "bob@gmail.com", "marti@gmail.com", "helen@gmail.com");
        List<String> result = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from _user");
            while (rs.next()) {
                result.add(rs.getString("email"));
            }
            rs.close();
            st.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(expected, result);
    }
}