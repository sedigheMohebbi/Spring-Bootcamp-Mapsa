package com.mapsa.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author esmaeil
 * @date 01.11.20
 */
public class DBConnection {

    private DBConnection() {
    }

    private static DBConnection dbConnection = null;

    public Connection connection;

    public Connection getConnection() throws IOException {
        InputStream input = getClass().getClassLoader().
                getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(input);
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            synchronized (DBConnection.class) {
                dbConnection = new DBConnection();
            }
        }
        return dbConnection;
    }

}