package org.uprise.systems;

import org.uprise.systems.models.User;
import org.uprise.systems.services.DepositService;
import org.uprise.systems.services.LoginService;
import org.uprise.systems.services.RegisterService;
import org.uprise.systems.views.DepositView;
import org.uprise.systems.views.LoginView;
import org.uprise.systems.views.RegisterView;

import java.util.Scanner;

public class App {
    public static String password;
    public static String username;

    public static void main(String[] args) {
        

        
        Scanner scanner = new Scanner(System.in);
        RegisterService registerService = new RegisterService();
        LoginService loginService = new LoginService(registerService);
        DepositService depositService = new DepositService();
        LoginView loginView = new LoginView();
        DepositView depositView = new DepositView();
        RegisterView registerView = new RegisterView(registerService);

        boolean loggedIn = false;
        User authenticatedUser = null;

        while (!loggedIn) {
            System.out.println("=== Sacco Performance Measurement and Monitoring ===");
            System.out.println("Enter command: 'login username password'");
            System.out.println("Enter command: 'register' to register a new user");
            System.out.println("Enter command: 'exit' to exit the Sacco system");

            System.out.print("Enter your command: ");
            String command = scanner.nextLine();

            String[] commandParts = command.split(" ");
            String action = commandParts[0].toLowerCase();

            switch (action) {
                case "login":
                    if (commandParts.length != 3) {
                        System.out.println("Invalid login command format. Please use 'login username password'.");
                        break;
                    }

                    username = commandParts[1];
                    password = commandParts[2];

                    authenticatedUser = loginService.authenticate(username, password);
                    if (authenticatedUser != null) {
                        System.out.println("Login successful!");
                        loggedIn = true;
                    } else {
                        System.out.println("Login failed. Incorrect username or password.");
                    }
                    break;
                case "register":
                    registerView.registerUser();
                    break;
                case "exit":
                    System.out.println("Exiting the Sacco system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Unrecognized command. Please use valid commands.");
            }
        }

        // Authenticated user menu
        while (loggedIn) {
            System.out.println("=== Sacco Performance Measurement and Monitoring ===");
            System.out.println("Enter command: 'deposit' to deposit funds");
            System.out.println("Enter command: 'CheckStatement' to check for statements");
            System.out.println("Enter command: 'RequestLoan' to request for a loan");
            System.out.println("Enter command: 'LoanRequestStatus' to request for loan status");
            System.out.println("Enter command: 'Withdraw' to withdraw funds");
            System.out.println("Enter command: 'Logout' to logout of the system");

            System.out.print("Enter your command: ");
            String command = scanner.nextLine();

            String[] commandParts = command.split(" ");
            String action = commandParts[0].toLowerCase();

            switch (action) {
                case "deposit":
                    // if (commandParts.length != 4) {
                    //     System.out.println("Invalid deposit command format. Please use 'deposit amount date_deposited receipt_number'.");
                    //     break;
                    // }
                    depositView.handleDeposit();
                    break;
                case "checkstatement":
                    // if (commandParts.length != 3) {
                    //     System.out.println("Invalid CheckStatement command format. Please use 'CheckStatement dateFrom DateTo'.");
                    //     break;
                    // }
                    // TODO: Implement check statement functionality
                    break;
                case "requestloan":
                    // if (commandParts.length != 3) {
                    //     System.out.println("Invalid requestLoan command format. Please use 'requestLoan amount paymentPeriod_in_months'.");
                    //     break;
                    // }
                    // TODO: Implement request loan functionality
                    break;
                case "loanrequeststatus":
                    // if (commandParts.length != 2) {
                    //     System.out.println("Invalid LoanRequestStatus command format. Please use 'LoanRequestStatus loan_application_number'.");
                    //     break;
                    // }
                    // TODO: Implement check loan request status functionality
                    break;
                case "withdraw":
                    // if (commandParts.length != 2) {
                    //     System.out.println("Invalid Withdraw command format. Please use 'Withdraw withdraw_amount'.");
                    //     break;
                    // }
                    // TODO: Implement withdraw functionality
                    break;
                case "logout":
                    System.out.println("Logging out. Goodbye!");
                    loggedIn = false;
                    authenticatedUser = null;
                    break;
                default:
                    System.out.println("Unrecognized command. Please use valid commands.");
            }
        }
    }
}