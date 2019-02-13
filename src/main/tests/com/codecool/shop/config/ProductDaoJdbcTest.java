package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.database.ProductDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ProductDaoJdbcTest {
    Connection connection = DBUtil.getInstance().getTestConnection();
    ProductDaoJdbc dbProduct = new ProductDaoJdbc(connection);


    @Test
    void productWithSomeFieldsCanBeAdded() {
        Product product = new Product("testProduct", 1, "HUF", "test", new ProductCategory("one", "HR", "two"), new Supplier("three", "four"));
        dbProduct.add(product);
    }

    @Test
    void findAnExistingProduct() {
        dbProduct.find(3);
    }

    @Test
    void removeProduct() {

    }





}
