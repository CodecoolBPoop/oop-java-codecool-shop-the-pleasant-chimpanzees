package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.config.DBUtil;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private Connection conn;
    private static SupplierDaoJdbc instance = null;

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    private SupplierDaoJdbc() {
        conn = DBUtil.getInstance().getProductionConnection();
    }

    private SupplierDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO supplier (name, description) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to add supplier to the db: " + e);
        }
    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM supplier WHERE id = ?";
        Supplier supplier;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                supplier = new Supplier(rs.getString("name"), rs.getString("description"));
                return supplier;
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve user from db by id: " + e);
        }
        return null;
    }

    @Override
    public Supplier find(String name) {
        String query = "SELECT * FROM supplier WHERE name = ?";
        Supplier supplier;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                supplier = new Supplier(rs.getString("name"), rs.getString("description"));
                return supplier;
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve user from db by name: " + e);
        }
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
