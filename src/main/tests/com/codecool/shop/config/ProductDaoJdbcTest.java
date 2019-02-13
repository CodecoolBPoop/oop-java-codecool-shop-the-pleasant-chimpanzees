package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.database.ProductDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductDaoJdbcTest {



    Connection connection = null;
    ProductDaoJdbc dbProduct;

    @BeforeAll
    void init() {
        DBUtil.ConnectionData data = new DBUtil.ConnectionData(
                "src/Data/prod_config.txt",
                "src/Data/test_config.txt");

        DBUtil.getInstance().configure(data);
        connection = DBUtil.getInstance().getTestConnection();
        dbProduct = new ProductDaoJdbc(connection);
    }

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
        dbProduct.remove(17);

    }





}
