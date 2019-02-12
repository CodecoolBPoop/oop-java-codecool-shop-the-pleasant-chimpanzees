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
        /*String queryC = "INSERT INTO cart(user_id) VALUES (?)";
        try {
            PreparedStatement preparedStatement2 = connection.prepareStatement(queryC);
            preparedStatement2.setInt(1, user.getId());
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
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

    @Override
    public Product find(int id) {
        return null;
    }

    public Product findProduct(int id) {
        try {
            PreparedStatement statement = connection.createStatement();
            String sql = "select * from product join supplier on product.supplier_id = supplier.id join product_category on product.category_id = product_category.id where product.id = ?";
            PreparedStatement preparedSt = connection.prepareStatement(sql);
            preparedSt.setInt(1, id);
            ResultSet results = preparedSt.executeQuery();
            Product searched = new Product();
            while (results.next()) {
                searched.setId(results.getInt("id"));
                searched.setName(results.getString("name"));
                searched.setDescription(results.getString("description"));
                searched.setPrice(results.getFloat("price"), "USD");
                searched.setSupplier(new Supplier(results.getString(9), results.getString(10)));
                searched.setProductCategory(new ProductCategory(results.getString(11), results.getString(12), results.getString(13)));
            }
            statement.close();
            System.out.println(searched.toString());
            return searched;

        } catch (SQLException e) {
            System.out.printf("I couldn't find product of id %s%n", id);
            e.printStackTrace();
        }
        return null;
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
    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}
