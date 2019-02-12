package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.config.DBUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import com.codecool.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CartDaoJdbc implements CartDao {

    Connection connection = DBUtil.getInstance().getConnection();

    public void addToCart(Product product, User user) {
        String queryPIC = "INSERT INTO products_in_carts (cart_id, product_id) VALUES(?, ?)";
        try{
            PreparedStatement preparedStatement1 = connection.prepareStatement(queryPIC);
            preparedStatement1.setInt(1, user.getId());
            preparedStatement1.setInt(2, product.getId());
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String queryC = "INSERT INTO cart(user_id) VALUES (?)";
        try {
            PreparedStatement preparedStatement2 = connection.prepareStatement(queryC);
            preparedStatement2.setInt(1, user.getId());
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addToCart(Product product) {

    }

    @Override
    public void removeFromCart(int id) {

    }

    @Override
    public void emptyCart() {

    }

    public Product findProduct(int id) {
    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}
