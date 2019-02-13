package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.database.SupplierDaoJdbc;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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

    @BeforeEach
    void clearDB() {

        String[] command = {"psql", "-U", "postgres", "-d", "codecoolshoptester", "-h", "localhost", "-f", "src/Data/init_db.sql"};
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command(command);

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAdd() {
        SupplierDaoJdbc sdj = SupplierDaoJdbc.getInstance();
        sdj.setConn(testConnection);

        Supplier supp = new Supplier("Test supplier", "Test supplier");

        sdj.add(supp);

        Assertions.assertEquals(1, sdj.getAll().size());
    }

    @Test
    void testFindById() {
        SupplierDaoJdbc sdj = SupplierDaoJdbc.getInstance();
        sdj.setConn(testConnection);

        Supplier supp = new Supplier("Test supplier", "Test supplier");
        supp.setId(1);

        sdj.add(supp);

        Supplier dbSupp = sdj.find(1);

        Assertions.assertEquals(supp.getName(), dbSupp.getName());
        Assertions.assertEquals(supp.getDescription(), dbSupp.getDescription());
        Assertions.assertEquals(supp.getId(), dbSupp.getId());
    }

    @Test
    void testFindByName() {
        SupplierDaoJdbc sdj = SupplierDaoJdbc.getInstance();
        sdj.setConn(testConnection);

        Supplier supp = new Supplier("Test supplier", "Test supplier");
        supp.setId(1);

        sdj.add(supp);

        Supplier dbSupp = sdj.find("Test supplier");

        Assertions.assertEquals(supp.getName(), dbSupp.getName());
        Assertions.assertEquals(supp.getDescription(), dbSupp.getDescription());
        Assertions.assertEquals(supp.getId(), dbSupp.getId());
    }

    @Test
    void testRemove() {
        SupplierDaoJdbc sdj = SupplierDaoJdbc.getInstance();
        sdj.setConn(testConnection);

        sdj.remove(1);

        Assertions.assertEquals(new ArrayList<Supplier>(), sdj.getAll());
    }

    @Test
    void testGetAll() {
        SupplierDaoJdbc sdj = SupplierDaoJdbc.getInstance();
        sdj.setConn(testConnection);

        Supplier sp1 = new Supplier("Marvel", "Marvel comic books");
        Supplier sp2 = new Supplier("DC", "DC comic books");
        Supplier sp3 = new Supplier("Netflix", "Netflix comic books");

        sdj.add(sp1);
        sdj.add(sp2);
        sdj.add(sp3);

        List<Supplier> testSuppliers = new ArrayList<>();
        testSuppliers.add(sp1);
        testSuppliers.add(sp2);
        testSuppliers.add(sp3);

        for (int i = 0; i < testSuppliers.size(); i++) {
            testSuppliers.get(i).setId(i + 1);
        }

        Assertions.assertEquals(testSuppliers.size(), sdj.getAll().size());
        Assertions.assertEquals(testSuppliers.get(0).getName(), sdj.getAll().get(0).getName());
        Assertions.assertEquals(testSuppliers.get(1).getName(), sdj.getAll().get(1).getName());
        Assertions.assertEquals(testSuppliers.get(2).getName(), sdj.getAll().get(2).getName());
    }
}
