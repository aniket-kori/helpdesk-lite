package com.aniket.helpdesklite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/helpdesk_db";

    public static Connection getConnection() throws SQLException {
        String username = System.getenv("MYSQL_USER");
        String password = System.getenv("MYSQL_PASSWORD");

        if (username == null || password == null) {
            throw new IllegalStateException(
                    "Set MYSQL_USER and MYSQL_PASSWORD environment variables."
            );
        }

        return DriverManager.getConnection(URL, username, password);
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            System.out.println("Connected to HelpDesk Lite database!");
        } catch (SQLException | IllegalStateException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}