package com.sap.superchargersrl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConnection {
    private static final String PROPERTIES_FILE = "database.properties";
    private static Properties props = new Properties();

    static {
        try (InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }
}