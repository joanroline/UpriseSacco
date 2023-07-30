package org.uprise.systems.views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Scanner;

import org.uprise.systems.models.User;
import org.uprise.systems.services.RegisterService;

public class RegisterView {

    private RegisterService registerService;

    public RegisterView(RegisterService registerService) {
        this.registerService = registerService;
    }

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.print("Enter date of birth (DD/MM/YYYY): ");
        String dateOfBirth = scanner.nextLine();

        System.out.println("Enter telephone number:");
        String telephoneNumber = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();


        User newUser = new User(username, email, dateOfBirth, telephoneNumber, password);

        boolean isRegistered = registerService.registerUser(newUser);

        if (isRegistered) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Failed to register user. Username or email already exists.");
        }
    }
}
