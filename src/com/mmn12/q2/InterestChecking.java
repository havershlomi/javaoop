package com.mmn12.q2;

public class InterestChecking extends NoServiceChargeChecking {

    final int MINIMUM_BALANCE = 1000;
    final double INTEREST = 5;
    private double interestRate = INTEREST;

    public InterestChecking(String id, String accountId, String ownerName, double balance, int minBalance, double interestRate) {
        super(id, accountId, ownerName, balance, minBalance);
        this.interestRate = interestRate;
    }

    public InterestChecking(String id, String accountId, String ownerName, double balance, double interestRate) {
        this(id, accountId, ownerName, balance);
        this.interestRate = interestRate;
    }

    public InterestChecking(String id, String accountId, String ownerName, double balance, int minBalance) {
        super(id, accountId, ownerName, balance, minBalance);
        this.interestRate = interestRate;
    }

    public InterestChecking(String id, String accountId, String ownerName, double balance) {
        super(id, accountId, ownerName, balance);
        this.setMinBalance(MINIMUM_BALANCE);
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
    public void monthlyManage(){
        double interest = this.calculateInterest();
        this.deposit(interest);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Interest Rate: " + this.interestRate + "%";
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (!(obj instanceof InterestChecking))
                return false;
            InterestChecking interestChecking = (InterestChecking) obj;
            if (interestChecking.interestRate == this.interestRate)
                return true;
        }
        return false;
    }
}
