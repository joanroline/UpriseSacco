package org.uprise.systems.services;

import org.uprise.systems.models.User;
import org.uprise.systems.DatabaseConnection.DatabaseCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.util.List;

public class LoginService {
    // private List<User> users;
    private Connection connection;

    public LoginService(RegisterService registerService) {
        // this.users = registerService.getUserList();
        this.connection = DatabaseCon.getConnection();
        
    }

    public User authenticate(String username, String password) {
        String selectQuery = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("userId");
                    String email = resultSet.getString("email");
                    String dateOfBirth = resultSet.getString("dateOfBirth");
                    String telephoneNumber = resultSet.getString("telephoneNumber");

                    return new User(username, email, dateOfBirth, telephoneNumber, password);
                } else {
                    // If no rows are returned, it means the authentication failed
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
