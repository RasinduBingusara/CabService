package org.megacity.cabservice.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    final static String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    final static String DB_URL = "jdbc:mysql://localhost:3306/megacitydb";

    final static String Username = "root";
    final static String Password = "";

    public static Connection connection() {
        try {
            Class.forName(JDBC_Driver);
            Connection conn = DriverManager.getConnection(DB_URL, Username, Password);
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database Connection Failed: " + ex.getMessage());
            return null;
        }
    }
}
