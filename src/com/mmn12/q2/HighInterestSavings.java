package com.mmn12.q2;

public class HighInterestSavings extends SavingsAccount {

    final double INTEREST = 10;
    final int MINIMUM_BALANCE = 100;
    private int minBalance = MINIMUM_BALANCE;

    public HighInterestSavings(String id, String accountId, String ownerName, double balance, double interestRate, int minBalance) {
        super(id, accountId, ownerName, balance, interestRate);
        this.minBalance = minBalance;
    }

    public HighInterestSavings(String id, String accountId, String ownerName, double balance, double interestRate) {
        this(id, accountId, ownerName, balance);
        this.setInterestRate(interestRate);
    }

    public HighInterestSavings(String id, String accountId, String ownerName, double balance, int minBalance) {
        this(id, accountId, ownerName, balance);
        this.minBalance = minBalance;
        this.setInterestRate(INTEREST);
    }

    public HighInterestSavings(String id, String accountId, String ownerName, double balance) {
        super(id, accountId, ownerName, balance);
        this.setInterestRate(INTEREST);
    }

    public int getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(int minBalance) {
        this.minBalance = minBalance;
    }

    @Override
    public void withdraw(double money) throws IllegalBalance {
        if (this.getBalance() - money > this.minBalance) {
            super.withdraw(money);
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Minimum balance: " + this.minBalance;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (!(obj instanceof HighInterestSavings))
                return false;
            HighInterestSavings highInterestSavings = (HighInterestSavings) obj;
            if (highInterestSavings.minBalance == this.minBalance)
                return true;
        }
        return false;
    }
}
