package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.config.DBUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private Connection connection;
    private static ProductDaoJdbc instance = null;


    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    public ProductDaoJdbc() {
        this.connection = DBUtil.getInstance().getProductionConnection();
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
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from product join supplier on product.supplier_id = supplier.id join product_category on product.category_id = product_category.id where product.id = ?";
            PreparedStatement preparedSt = connection.prepareStatement(sql);
            preparedSt.setInt(1, id);
            ResultSet results = preparedSt.executeQuery();
            Product searched = new Product();
            getOneProductData(results, searched);
            statement.close();
            if (searched.getName().equals("")) {
                throw new NullPointerException("No stuff by this id exists.");
            }
            return searched;

        } catch (SQLException e) {
            System.out.printf("I couldn't find product of id %s%n", id);
            e.printStackTrace();
        }
        return null;
    }

    public Product findByName(String productName) {
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from product join supplier on product.supplier_id = supplier.id join product_category on product.category_id = product_category.id where product.name = ?";
            PreparedStatement preparedSt = connection.prepareStatement(sql);
            preparedSt.setString(1, productName);
            ResultSet results = preparedSt.executeQuery();
            Product searched = new Product();
            getOneProductData(results, searched);
            statement.close();
            if (searched.getName().equals("")) {
                throw new NullPointerException("No stuff by this id exists.");
            }
            return searched;

        } catch (SQLException e) {
            System.out.printf("I couldn't find product of id %s%n", productName);
            e.printStackTrace();
        }
        return null;
    }

    private void getOneProductData(ResultSet results, Product searched) throws SQLException {
        while (results.next()) {
            searched.setId(results.getInt("id"));
            searched.setName(results.getString("name"));
            searched.setDescription(results.getString("description"));
            searched.setPrice(results.getFloat("price"), "USD");
            searched.setSupplier(new Supplier(results.getString(9), results.getString(10)));
            searched.setProductCategory(new ProductCategory(results.getString(11), results.getString(12), results.getString(13)));
        }
    }

    @Override
    public void remove(int id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "delete from product where id = ?";
            PreparedStatement preppedSt = connection.prepareStatement(sql);
            preppedSt.setInt(1, id);
            preppedSt.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.printf("Cant find product by id %s%n", id);
            e.printStackTrace();
        }

    }

    @Override
    public List<Product> getAll() {
        ArrayList<Product> everyProduct = new ArrayList<>();

        try {
            String sql = "select * from product";
            PreparedStatement preppedStmnt = connection.prepareStatement(sql);
            ResultSet results = preppedStmnt.executeQuery();
            everyProduct = collectProductsToList(results);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return everyProduct;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        int supplierId = supplier.getId();
        ArrayList<Product> everyProduct = new ArrayList<>();

        try {
            String sql = "select * from product where product.supplier_id = ?";
            PreparedStatement preppedStmnt = connection.prepareStatement(sql);
            preppedStmnt.setInt(1, supplierId);
            ResultSet results = preppedStmnt.executeQuery();
            everyProduct = collectProductsToList(results);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return everyProduct;

    }

    private ArrayList<Product> collectProductsToList(ResultSet results) throws SQLException {
        ArrayList<Product> everyProduct = new ArrayList<>();
        while (results.next()) {
            Product current = new Product();
            current.setId(results.getInt("id"));
            current.setName(results.getString("name"));
            current.setDescription(results.getString("description"));
            current.setPrice(results.getFloat("price"), "USD");
            everyProduct.add(current);
        }
        return everyProduct;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        int categoryId = productCategory.getId();
        ArrayList<Product> everyProduct = new ArrayList<>();

        try {
            String sql = "select * from product where product.category_id = ?";
            PreparedStatement preppedStmnt = connection.prepareStatement(sql);
            preppedStmnt.setInt(1, categoryId);
            ResultSet results = preppedStmnt.executeQuery();
            everyProduct = collectProductsToList(results);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return everyProduct;
    }



}
