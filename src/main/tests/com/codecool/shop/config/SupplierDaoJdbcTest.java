package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.database.SupplierDaoJdbc;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;

public class SupplierDaoJdbcTest {

    Connection testConnection = DBTestUtil.getInstance().getConnection();

    @Test
    void testAdd() {
        Supplier supp = new Supplier("Test company", "Test company");
        SupplierDaoJdbc sdj = new SupplierDaoJdbc(testConnection);

        sdj.add(supp);
    }

    @Test
    void testFind() {
        Supplier supp = new Supplier("Test company", "Test company");
        SupplierDaoJdbc sdj = new SupplierDaoJdbc(testConnection);

        Assertions.assertEquals(supp.getName(), sdj.find(5).getName());
        Assertions.assertEquals(supp.getDescription(), sdj.find(5).getDescription());
    }
}
