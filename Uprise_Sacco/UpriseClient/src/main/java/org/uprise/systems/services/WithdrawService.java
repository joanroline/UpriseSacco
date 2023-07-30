package org.uprise.systems.services;

import org.uprise.systems.models.Deposit;

import java.util.List;

public class WithdrawService {
    private List<Deposit> deposits;

    public WithdrawService(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public boolean withdraw(double amount) {
        double totalBalance = getTotalBalance();
        if (amount <= totalBalance) {
            // Perform the withdrawal here, e.g., update the balance or create a withdrawal record
            // For this example, we assume the withdrawal is successful if the amount is less than or equal to the total balance
            return true;
        } else {
            return false;
        }
    }

    private double getTotalBalance() {
        // Calculate the total balance based on the deposits
        double totalBalance = 0.0;
        for (Deposit deposit : deposits) {
            totalBalance += deposit.getDepositAmount();
        }
        return totalBalance;
    }
}
