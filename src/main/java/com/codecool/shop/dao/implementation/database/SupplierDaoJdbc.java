package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.config.DBUtil;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    Connection conn = DBUtil.getInstance().getConnection();

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO supplier VALUES (?,?)";
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
        return null;
    }

    @Override
    public Supplier find(String name) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
