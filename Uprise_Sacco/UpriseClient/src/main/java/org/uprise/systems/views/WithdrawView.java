package org.uprise.systems.views;

import java.util.Scanner;

public class WithdrawView {
    private Scanner scanner;

    public WithdrawView() {
        scanner = new Scanner(System.in);
    }

    public String getCommand() {
        System.out.print("Enter command (Withdraw withdraw_amount): ");
        return scanner.nextLine();
    }
}
