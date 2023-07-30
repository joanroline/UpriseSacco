package org.uprise.systems.views;

import org.uprise.systems.models.Deposit;
import org.uprise.systems.services.DepositService;

import java.util.Scanner;

public class DepositView {
    private Scanner scanner = new Scanner(System.in);
    private DepositService depositService = new DepositService();

    public DepositView() {
    }

    public void handleDeposit() {
        System.out.println("Enter command (deposit amount date_deposited receipt_number): ");
        String command = scanner.nextLine();
        String[] commandParts = command.split(" ");

        if (commandParts.length != 4 || !commandParts[0].equalsIgnoreCase("deposit")) {
            System.out.println("Invalid deposit command format. Please use 'deposit amount date_deposited receipt_number'.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(commandParts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number for the deposit amount.");
            return;
        }

        String dateDeposited = commandParts[2];
        String receiptNumber = commandParts[3];

        Deposit deposit = new Deposit(amount, dateDeposited, receiptNumber);
        if (depositService.addDeposit(deposit)) {
    
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Deposit failed. Please try again.");
        }
    }
}