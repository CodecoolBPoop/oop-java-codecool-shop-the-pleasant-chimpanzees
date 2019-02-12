package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.config.DBUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private Connection connection;

    public ProductDaoJdbc() {
        this.connection = DBUtil.getInstance().getConnection();
    }

    public ProductDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Product product) {
        try {
            Statement statement = connection.createStatement();
            String sqlQuery = "insert into product (name, description, price) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getDefaultPrice());
            preparedStatement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.printf("I couldn't add product named %s to the database for some reason.", product.getName());
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
