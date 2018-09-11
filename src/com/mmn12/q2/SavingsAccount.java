package com.mmn12.q2;

public class SavingsAccount extends BankAccount {


    final double INTEREST = 5;
    private double interestRate = INTEREST;

    public SavingsAccount(String id, String accountId, String ownerName, double balance, double interestRate) {
        super(id, accountId, ownerName, balance);
        this.interestRate = interestRate;
    }

    public SavingsAccount(String id, String accountId, String ownerName, double balance) {
        super(id, accountId, ownerName, balance);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    private double calculateInterest() {
        return this.getBalance() * (this.interestRate / 100);
    }

    @Override
    public void monthlyManage() {
        double interest = this.calculateInterest();
        this.deposit(interest);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Interest rate: " + this.interestRate + "%";
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (!(obj instanceof SavingsAccount))
                return false;
            SavingsAccount savingsAccount = (SavingsAccount) obj;
            if (savingsAccount.interestRate == this.interestRate)
                return true;
        }
        return false;
    }
}
