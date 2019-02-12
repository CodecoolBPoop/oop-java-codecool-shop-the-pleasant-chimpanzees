package com.codecool.shop.config;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DBUtilTest {

    private Connection connection;

    @BeforeAll
    void init() throws FileNotFoundException {

        DBUtil.ConnectionData data = new DBUtil.ConnectionData(
                "src/Data/prod_config.txt",
                "src/Data/test_config.txt");

        DBUtil.getInstance().configure(data);
        connection = DBUtil.getInstance().getTestConnection();

        executeQueryFromFile("src/Data/init_db.sql", connection);
        executeQueryFromFile("src/Data/test_data.sql", connection);
    }

    private void executeQueryFromFile(String path, Connection connection) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while(scanner.hasNextLine()){
            sb.append(scanner.nextLine());
        }

        try {
            Statement st = connection.createStatement();
            st.executeUpdate(sb.toString());
            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testGetContext(){
        List<String> expected = Arrays.asList(
                "bob@gmail.com", "marti@gmail.com", "helen@gmail.com");

        assertEquals(expected, getQueryResults(connection, "select * from _user", "email"));
    }

    private static List<String> getQueryResults(Connection connection, String query, String rowName){
        List<String> result = new ArrayList<>();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                result.add(
                        rs.getString(rowName)
                );
            }
            rs.close();
            st.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}