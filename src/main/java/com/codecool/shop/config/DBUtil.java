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

    private String CONFIG_FILE_PATH = "/Users/adamgonda/IdeaProjects/current/oop-java-codecool-shop-the-pleasant-chimpanzees/src/Data/db_config.txt";

    private static DBUtil instance;
    private Map<String, String> CONFIG_DATA = new HashMap<>();
    private Connection CONNECTION;

    public DBUtil() {
        initConfigData();
        initializeConnection();
    }

    public static DBUtil getInstance(){
        if(instance == null){
            instance = new DBUtil();
        }
        return instance;
    }

    private Map<String, String> initConfigData() {
        File file =
                new File(CONFIG_FILE_PATH);
        Scanner sc = null;

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            putInConfigData(sc.nextLine());
        }
        return CONFIG_DATA;
    }

    private void initializeConnection(){

        try {
            Class.forName(getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        String url = getUrl();
        Properties props = new Properties();
        props.setProperty("user", getUser());
        props.setProperty("password", getPassword());

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CONNECTION = conn;
    }

    private void putInConfigData(String nextLine) {
        String[] keyAndValue = nextLine.split("=");
        String key = keyAndValue[0].trim();
        String value = keyAndValue[1].trim();
        CONFIG_DATA.put(key, value);
    }

    private String getDriver() {
        return CONFIG_DATA.get("driver");
    }

    private String getUrl() {
        return CONFIG_DATA.get("dbUrl");
    }

    private String getUser() {
        return CONFIG_DATA.get("user");
    }

    private String getPassword() {
        return CONFIG_DATA.get("dbPwd");
    }

    public Connection getConnection(){
        return CONNECTION;
    }
}


