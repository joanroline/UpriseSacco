package org.uprise.systems.models;

public class Deposit {
    private double depositAmount;
    private String dateDeposited;
    private String receiptNumber;

    public Deposit(double depositAmount, String dateDeposited, String receiptNumber) {
        this.depositAmount = depositAmount;
        this.dateDeposited = dateDeposited;
        this.receiptNumber = receiptNumber;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public String getDateDeposited() {
        return dateDeposited;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setDepositAmount() {
        this.depositAmount = depositAmount;
    }

    public void setDateDeposited() {
        this.dateDeposited = dateDeposited;
    }

    public void setReceiptNumber() {
        this.receiptNumber = receiptNumber;
    }
}