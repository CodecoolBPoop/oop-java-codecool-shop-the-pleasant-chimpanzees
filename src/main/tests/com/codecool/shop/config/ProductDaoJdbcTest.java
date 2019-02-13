package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.database.ProductDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.lang.reflect.Executable;
import java.sql.Connection;
import java.sql.SQLException;

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
        Product found = dbProduct.find(3);
        String expectedName = "Green Lantern: Agent Orange";
        int expectedId = 3;
        assertEquals(expectedName, found.getName());
        assertEquals(expectedId, found.getId());
    }

    @Test
    void findNonExistingProduct() {
        assertThrows(NullPointerException.class, () -> {
            dbProduct.find(46454553);
        });
    }

    @Test
    void findExistingByName() {
        Product found = dbProduct.findByName("Alf");
        String expectedName = "Alf";
        int expectedId = 6;
        assertEquals(expectedName, found.getName());
        assertEquals(expectedId, found.getId());
    }

    @Test
    void removeProduct() {
        dbProduct.remove(20);
    }





}
