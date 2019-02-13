package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.config.DBUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDaoJdbc implements CartDao {

    Connection connection = DBUtil.getInstance().getConnection();

    public void addToCart(int cartId, int productId) {
        String queryPIC = "INSERT INTO products_in_carts (cart_id, product_id) VALUES(?, ?)";
        try{
            PreparedStatement preparedStatement1 = connection.prepareStatement(queryPIC);
            preparedStatement1.setInt(1, cartId);
            preparedStatement1.setInt(2, productId);
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(findCart(cartId) == null) {
            String queryC = "INSERT INTO cart(user_id) VALUES (?)";
            try {
                PreparedStatement preparedStatement2 = connection.prepareStatement(queryC);
                preparedStatement2.setInt(1, user.getId());
                preparedStatement2.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void removeFromCart(int cartId, int productId) {
        String query = "DELETE FROM products_in_carts\n" +
                "WHERE id in (SELECT id FROM products_in_carts WHERE cart_id = ? AND product_id = ? LIMIT 1)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,cartId);
            preparedStatement.setInt(2,productId);
            preparedStatement.execute();


        } catch (SQLException e) {
            System.out.printf("I could not find this product in the cart.");
            e.printStackTrace();
        }

    }

    @Override
    public void emptyCart(int cartId) {
        String query = "DELETE FROM products_in_carts WHERE cart_id = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,cartId);
            preparedStatement.execute();


        } catch (SQLException e) {
            System.out.printf("I could not find any products in the cart.");
            e.printStackTrace();
        }

    }

    public Cart findCart(int cartId){
        String query = "SELECT * FROM cart\n" +
                "  JOIN _user ON  cart.user_id = _user.id\n" +
                "  JOIN billing_info ON _user.id = billing_info.user_id\n" +
                "  WHERE cart.id = 1";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cartId);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()){
                User user = new User(result.getInt(1), result.getString(3), result.getString(7), result.getString(8), result.getString(9),result.getInt(10));
                Cart cart = new Cart(user);
                return cart;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}
