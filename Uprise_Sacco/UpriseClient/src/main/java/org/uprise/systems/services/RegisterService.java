package org.uprise.systems.services;

import org.uprise.systems.models.User;
import org.uprise.systems.DatabaseConnection.DatabaseCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterService {
    private Connection connection;

    public RegisterService() {
        // Get the database connection using the DatabaseCon class
        connection = DatabaseCon.getConnection();
    }

    public boolean registerUser(User newUser) {
        // Check if the username or email already exists in the database
        if (checkUsernameExists(newUser.getUsername()) || checkEmailExists(newUser.getEmail())) {
            return false; // Registration failed, username or email already exists
        }

        String insertQuery = "INSERT INTO users (username, email, dateOfBirth, telephoneNumber, password) " +
                             "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, newUser.getUsername());
            preparedStatement.setString(2, newUser.getEmail());
            preparedStatement.setString(3, newUser.getDateOfBirth());
            preparedStatement.setString(4, newUser.getTelephoneNumber());
            preparedStatement.setString(5, newUser.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper methods to check if username or email exists in the database
    private boolean checkUsernameExists(String username) {
        String selectQuery = "SELECT * FROM users WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // If the result set has any rows, it means the username exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkEmailExists(String email) {
        String selectQuery = "SELECT * FROM users WHERE email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // If the result set has any rows, it means the email exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
