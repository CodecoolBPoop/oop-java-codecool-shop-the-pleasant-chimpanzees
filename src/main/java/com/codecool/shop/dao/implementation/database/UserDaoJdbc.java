package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.config.DBUtil;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.sql.*;

public class UserDaoJdbc implements UserDao {

    private Connection connection;
    private static UserDaoJdbc instance = null;

    public static UserDaoJdbc getInstance() {
        if (instance == null) {
            instance = new UserDaoJdbc();
        }
        return instance;
    }

    UserDaoJdbc() {
        connection = DBUtil.getInstance().getProductionConnection();
    }

    @Override
    public void add(User user) {
        String query = "INSERT INTO _user(email,password) VALUES (?,?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
