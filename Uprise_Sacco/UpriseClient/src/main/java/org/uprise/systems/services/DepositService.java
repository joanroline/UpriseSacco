package org.uprise.systems.services;

import org.uprise.systems.DatabaseConnection.DatabaseCon;
import org.uprise.systems.models.Deposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DepositService {
    private Connection connection;

    private Scanner scanner;

    public DepositService() {
        connection = DatabaseCon.getConnection();
        scanner = new Scanner(System.in);
    }

    public boolean addDeposit(Deposit deposit) {
        System.out.println("Enter username again to complete the deposit:");
        String username = scanner.nextLine();
        int userId = getUserIdByUsername(username);

        String query = "INSERT INTO deposits (userId, depositAmount, dateDeposited, receiptNumber) " + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, userId);
            preparedStatement.setDouble(2, deposit.getDepositAmount());
            preparedStatement.setString(3, deposit.getDateDeposited()); // Store date as a string
            preparedStatement.setString(4, deposit.getReceiptNumber());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        }

        private int getUserIdByUsername(String username) {
        int userId = -1; // Initialize the userId to -1 as a default value (assuming userIds are positive values)

         // Prepare the SQL query to retrieve the userId based on the provided username
        String selectQuery = "SELECT userId FROM users WHERE username = ?";

        try {
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, username); // Set the username as a parameter in the query

        // Execute the query
        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if the query returned a result
        if (resultSet.next()) {
            userId = resultSet.getInt("userId"); // Retrieve the userId from the query result
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return userId;
}
    }

    