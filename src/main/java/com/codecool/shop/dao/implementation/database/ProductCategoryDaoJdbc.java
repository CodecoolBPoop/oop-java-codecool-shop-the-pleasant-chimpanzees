package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.config.DBUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private Connection conn;
    private static ProductCategoryDaoJdbc instance = null;

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    private ProductCategoryDaoJdbc() {
        conn = DBUtil.getInstance().getProductionConnection();
    }

    @Override
    public void add(ProductCategory category) {
        String query = "INSERT INTO product_category (name, description, department) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setString(3, category.getDepartment());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to add category to db: " + e);
        }

    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM product_category WHERE id = ?";
        ProductCategory pc;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                pc = new ProductCategory(rs.getString("name"), rs.getString("department"), rs.getString("description"));
                pc.setId(rs.getInt("id"));
                return pc;
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve product category from db by id: " + e);
        }
        return null;
    }

    @Override
    public ProductCategory find(String name) {
        String query = "SELECT * FROM product_category WHERE name = ?";
        ProductCategory pc;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                pc = new ProductCategory(rs.getString("name"), rs.getString("department"), rs.getString("description"));
                pc.setId(rs.getInt("id"));
                return pc;
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve product category from db by name: " + e);
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM product_category WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to remove product category from db: " + e);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> allProductCategory = new ArrayList<>();
        String query = "SELECT * FROM product_category";
        ProductCategory pr;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                pr = new ProductCategory(rs.getString("name"), rs.getString("department"), rs.getString("description"));
                pr.setId(rs.getInt("id"));
                allProductCategory.add(pr);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get all product category from db: " + e);
        }
        return allProductCategory;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
