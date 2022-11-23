package com.examprojectsummer2021.utilities;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Carsten
 */

public class DatabaseConnectionUtility {

    private static String url;
    private static String user;
    private static String password;
    private static Connection conn;

    private static DatabaseConnectionUtility databaseConnectionUtilityInstance = null;

    //Constructor
    private DatabaseConnectionUtility() {
    }

    public static DatabaseConnectionUtility getInstance(){
        if (databaseConnectionUtilityInstance == null){
            databaseConnectionUtilityInstance = new DatabaseConnectionUtility();
        }
        return databaseConnectionUtilityInstance;
    }

    //loads login info from file
    private void setLogin() {
        Properties prop = new Properties();
        try {
            prop.load(new ClassPathResource("application.properties").getInputStream());
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            url = prop.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {

        setLogin();

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return conn;
    }
}
