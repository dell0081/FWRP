/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Single database connection.
 * @author Tom - Rustom Trayvilla
 * @since 2024/03/24
 * @author Modified by Farock Yandom Youmbi Farock
 */
public class DBConnection {

    private static DBConnection instance;
    private Connection connection;
    
    private static String serveUrl = "jdbc:mysql://127.0.0.1:3306/fwrp";
    private static String userString ="root";
    private static String passwordString = "root";
    private static String driveString = "com.mysql.cj.jdbc.Driver";
    
    private DBConnection(){
        try {
            // Register JDBC driver
            Class.forName(driveString);
            // Get connection
            connection = DriverManager.getConnection(serveUrl, userString, passwordString);
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
