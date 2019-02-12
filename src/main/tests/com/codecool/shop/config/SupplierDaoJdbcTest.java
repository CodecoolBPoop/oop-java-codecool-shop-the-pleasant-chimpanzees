package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.database.SupplierDaoJdbc;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SupplierDaoJdbcTest {

    Connection testConnection;

    @BeforeAll
    void init() {
        DBUtil.ConnectionData data = new DBUtil.ConnectionData(
                "src/Data/prod_config.txt",
                "src/Data/test_config.txt");

        DBUtil.getInstance().configure(data);
        testConnection = DBUtil.getInstance().getTestConnection();
    }

    @Test
    void testAdd() {
        Supplier supp = new Supplier("Test company", "Test company");
        SupplierDaoJdbc sdj = SupplierDaoJdbc.getInstance();
        sdj.setConn(testConnection);

        sdj.add(supp);
    }

    @Test
    void testFind() {
        Supplier supp = new Supplier("Test company", "Test company");
        SupplierDaoJdbc sdj = SupplierDaoJdbc.getInstance();
        sdj.setConn(testConnection);

        Assertions.assertEquals(supp.getName(), sdj.find(5).getName());
        Assertions.assertEquals(supp.getDescription(), sdj.find(5).getDescription());
    }
}
