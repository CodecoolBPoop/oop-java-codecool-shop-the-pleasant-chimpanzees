package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.config.DBUtil;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.sql.*;

public class UserDaoJdbc implements UserDao {
    private Connection connection;

    public UserDaoJdbc() {
        this.connection = DBUtil.getInstance().getTestConnection();
    }

    @Override
    public User findByEmail(String email) {
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from _user where email = ?";
            PreparedStatement preppedStm = connection.prepareStatement(sql);
            preppedStm.setString(1, email);
            ResultSet result = preppedStm.executeQuery();
            User user = new User();
            while (result.next()) {
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
            }
            statement.close();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByPw(String pw) {
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from _user where password = ?";
            PreparedStatement preppedStm = connection.prepareStatement(sql);
            preppedStm.setString(1, pw);
            ResultSet result = preppedStm.executeQuery();
            User user = new User();
            while (result.next()) {
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
            }
            statement.close();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
