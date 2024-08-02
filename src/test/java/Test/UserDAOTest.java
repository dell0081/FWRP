/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.CredentialsDTO;
import Model.ConsumersDTO;
import Model.CharitableOrganizationDTO;
import DataAccessLayer.UserDAO;
import DataAccessLayer.DBConnection;

public class UserDAOTest {

    private UserDAO userDAO;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        // Initialize the connection to the test database
        connection = DBConnection.getInstance().getConnection();
        userDAO = new UserDAO();
        
        // Clean up the test database before each test
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM Users")) {
            stmt.executeUpdate();
        }
    }

    @Test
    void testAddConsumer() throws SQLException {
        // Create a test consumer
        ConsumersDTO consumer = new ConsumersDTO();
        consumer.setEmailAddress("testconsumer@gmail.com");
        consumer.setLocation("8 street");
        consumer.setPhoneNumber("123-456-7890");
        consumer.setPassword("Abc1234567890");
        consumer.setUserType("Consumer");
        consumer.setFirstName("John");
        consumer.setLastName("Doe");

        // Add the consumer using the DAO
        boolean result = userDAO.addUser(consumer);
        assertTrue(result);

        // Verify that the consumer was added to the database
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users WHERE email = ?")) {
            stmt.setString(1, consumer.getEmailAddress());
            try (ResultSet rs = stmt.executeQuery()) {
                assertTrue(rs.next());
                assertEquals("testconsumer@gmail.com", rs.getString("email"));
                assertEquals("8 street", rs.getString("address"));
                assertEquals("123-456-7890", rs.getString("phone_num"));
                assertEquals("Abc1234567890", rs.getString("password"));
                assertEquals("Consumer", rs.getString("userType"));
                assertEquals("John", rs.getString("first_name"));
                assertEquals("Doe", rs.getString("last_name"));
            }
        }
    }

    @Test
    void testAddCharitableOrganization() throws SQLException {
        // Create a test charitable organization
        CharitableOrganizationDTO charity = new CharitableOrganizationDTO();
        charity.setEmailAddress("testcharity@gmail.com");
        charity.setLocation("Charity Address");
        charity.setPhoneNumber("098-765-4321");
        charity.setPassword("password");
        charity.setUserType("Charity");
        charity.setCharitableOrgName("Good Cause");

        // Add the charitable organization using the DAO
        boolean result = userDAO.addUser(charity);
        assertTrue(result);

        // Verify that the charitable organization was added to the database
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users WHERE email = ?")) {
            stmt.setString(1, charity.getEmailAddress());
            try (ResultSet rs = stmt.executeQuery()) {
                assertTrue(rs.next());
                assertEquals("testcharity@example.com", rs.getString("email"));
                assertEquals("Charity Address", rs.getString("address"));
                assertEquals("098-765-4321", rs.getString("phone_num"));
                assertEquals("password", rs.getString("password"));
                assertEquals("Charity", rs.getString("userType"));
                assertEquals("Good Cause", rs.getString("charity_name"));
            }
        }
    }
}
