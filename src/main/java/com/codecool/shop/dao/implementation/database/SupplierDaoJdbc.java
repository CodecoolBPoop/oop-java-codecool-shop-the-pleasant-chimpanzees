package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.config.DBUtil;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                supplier.setId(rs.getInt("id"));
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
                supplier.setId(rs.getInt("id"));
                return supplier;
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve user from db by name: " + e);
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "REMOVE FROM supplier WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to remove supplier from db: " + e);
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> allSuppliers = new ArrayList<>();
        String query = "SELECT * FROM supplier";
        Supplier supplier;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                supplier = new Supplier(rs.getString("name"), rs.getString("description"));
                supplier.setId(rs.getInt("id"));
                allSuppliers.add(supplier);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get all suppliers from db: " + e);
        }
        return allSuppliers;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
