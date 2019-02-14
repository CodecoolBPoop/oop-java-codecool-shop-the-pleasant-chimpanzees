package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.database.ProductCategoryDaoJdbc;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductCategoryDaoJdbcTest {

    Connection testConnection;
    DBUtil.ConnectionData data;

    @BeforeAll
    void init() {
        data = new DBUtil.ConnectionData(
                "src/Data/prod_config.txt",
                "src/Data/test_config.txt");

        DBUtil.getInstance().configure(data);
        testConnection = DBUtil.getInstance().getTestConnection();
    }

    @BeforeEach
    void clearDB() {



        String[] command = {"psql", "-U", data.getTestUser(), "-d", data.getTestDatabaseName(), "-h", "localhost", "-f", "src/Data/init_db.sql"};
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
        ProductCategoryDaoJdbc pcdj = ProductCategoryDaoJdbc.getInstance();
        pcdj.setConn(testConnection);

        ProductCategory testCategory = new ProductCategory("Test category", "Test category", "Test category");

        pcdj.add(testCategory);

        Assertions.assertEquals(1, pcdj.getAll().size());
    }

    @Test
    void testFindById() {
        ProductCategoryDaoJdbc pcdj = ProductCategoryDaoJdbc.getInstance();
        pcdj.setConn(testConnection);

        ProductCategory testCategory = new ProductCategory("Test category", "Test category", "Test category");
        testCategory.setId(1);

        pcdj.add(testCategory);

        ProductCategory dbCategory = pcdj.find(1);

        Assertions.assertEquals(testCategory.getId(), dbCategory.getId());
        Assertions.assertEquals(testCategory.getName(), dbCategory.getName());
        Assertions.assertEquals(testCategory.getDepartment(), dbCategory.getDepartment());
        Assertions.assertEquals(testCategory.getDescription(), dbCategory.getDescription());
    }

    @Test
    void testFindByName() {
        ProductCategoryDaoJdbc pcdj = ProductCategoryDaoJdbc.getInstance();
        pcdj.setConn(testConnection);

        ProductCategory testCategory = new ProductCategory("Test category", "Test category", "Test category");
        testCategory.setId(1);

        pcdj.add(testCategory);

        ProductCategory dbCategory = pcdj.find("Test category");

        Assertions.assertEquals(testCategory.getId(), dbCategory.getId());
        Assertions.assertEquals(testCategory.getName(), dbCategory.getName());
        Assertions.assertEquals(testCategory.getDescription(), dbCategory.getDescription());
        Assertions.assertEquals(testCategory.getDepartment(), dbCategory.getDepartment());
    }

    @Test
    void testRemove() {
        ProductCategoryDaoJdbc pcdj = ProductCategoryDaoJdbc.getInstance();
        pcdj.setConn(testConnection);

        pcdj.remove(1);

        Assertions.assertEquals(new ArrayList<ProductCategory>(), pcdj.getAll());
    }

    @Test
    void testGetAll() {
        ProductCategoryDaoJdbc pcdj = ProductCategoryDaoJdbc.getInstance();
        pcdj.setConn(testConnection);

        ProductCategory pc1 = new ProductCategory("Superhero", "???", "About Superheroes");
        ProductCategory pc2 = new ProductCategory("Humor", "???", "About Humor");
        ProductCategory pc3 = new ProductCategory("Horror", "???", "About Horror");

        pcdj.add(pc1);
        pcdj.add(pc2);
        pcdj.add(pc3);

        List<ProductCategory> testCategory = new ArrayList<>();
        testCategory.add(pc1);
        testCategory.add(pc2);
        testCategory.add(pc3);

        for (int i = 0; i < testCategory.size(); i++) {
            testCategory.get(i).setId(i + 1);
        }

        Assertions.assertEquals(testCategory.size(), pcdj.getAll().size());
        Assertions.assertEquals(testCategory.get(0).getName(), pcdj.getAll().get(0).getName());
        Assertions.assertEquals(testCategory.get(1).getName(), pcdj.getAll().get(1).getName());
        Assertions.assertEquals(testCategory.get(2).getName(), pcdj.getAll().get(2).getName());
    }
}
