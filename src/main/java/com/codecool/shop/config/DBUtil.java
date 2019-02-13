package com.codecool.shop.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class DBUtil {

    private ConnectionData connectionData;
    private static DBUtil instance;
    private Connection testConnection;
    private Connection productionConnection;

    public static DBUtil getInstance(){
        if(instance == null){
            instance = new DBUtil();
        }
        return instance;
    }

    public void configure(ConnectionData connectionData){
        this.connectionData = connectionData;
        testConnection = initializeConnectionWith(
                this.connectionData.getTestDriver(),
                this.connectionData.getTestUrl(),
                this.connectionData.getTestUser(),
                this.connectionData.getTestPassword()
        );

        productionConnection = initializeConnectionWith(
                this.connectionData.getProductionDriver(),
                this.connectionData.getProductionUrl(),
                this.connectionData.getProductionUser(),
                this.connectionData.getProductionPassword()
        );
    }

    private Connection initializeConnectionWith(String driver, String url, String user, String password){

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public Connection getProductionConnection(){
        return productionConnection;
    }

    public Connection getTestConnection(){
        return testConnection;
    }

    public static class ConnectionData {

        private Map<String, String> PRODUCTION_CONFIG_DATA = new HashMap<>();
        private Map<String, String> TEST_CONFIG_DATA = new HashMap<>();

        public ConnectionData(String PRODUCTION_CONFIG_FILE_PATH, String TEST_CONFIG_FILE_PATH) {
            initConfigData(PRODUCTION_CONFIG_FILE_PATH, PRODUCTION_CONFIG_DATA);
            initConfigData(TEST_CONFIG_FILE_PATH, TEST_CONFIG_DATA);
        }

        private void initConfigData(String path, Map<String, String> map){
            File file = new File(path);
            Scanner sc = null;
            try {
                sc = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try{
                while (sc.hasNextLine()) {
                    String[] keyAndValue = sc.nextLine().split("=");
                    String key = keyAndValue[0].trim();
                    String value = keyAndValue[1].trim();
                    map.put(key, value);
                }
            }catch (NullPointerException e){
                e.printStackTrace();
                System.out.println("one of the config file is empty");
            }
        }

        public String getProductionDriver() {
            return PRODUCTION_CONFIG_DATA.get("driver");
        }

        public String getProductionUrl() {
            return PRODUCTION_CONFIG_DATA.get("url");
        }

        public String getProductionUser() {
            return PRODUCTION_CONFIG_DATA.get("user");
        }

        public String getProductionPassword() {
            return PRODUCTION_CONFIG_DATA.get("password");
        }

        public String getTestDriver() {
            return TEST_CONFIG_DATA.get("driver");
        }

        public String getTestUrl() {
            return TEST_CONFIG_DATA.get("url");
        }

        public String getTestUser() {
            return TEST_CONFIG_DATA.get("user");
        }

        public String getTestPassword() {
            return TEST_CONFIG_DATA.get("password");
        }

        public String getTestDatabaseName() { return TEST_CONFIG_DATA.get("url").substring(TEST_CONFIG_DATA.get("url").lastIndexOf("/")+1);}

    }
}


