package com.sap.superchargersrl.util.test;

import com.sap.superchargersrl.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Successfully connected to the database!");
                System.out.println("Connection details:");
                System.out.println("URL: " + conn.getMetaData().getURL());
                System.out.println("User: " + conn.getMetaData().getUserName());
                System.out.println("Database product name: " + conn.getMetaData().getDatabaseProductName());
                System.out.println("Database product version: " + conn.getMetaData().getDatabaseProductVersion());
            } else {
                System.out.println("Failed to connect to the database!");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database:");
            e.printStackTrace();
        }
    }
}