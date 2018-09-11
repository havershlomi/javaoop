package com.mmn12.q2;

public class NoServiceChargeChecking extends CheckingAccount {

    final int MINIMUM_BALANCE = 100;
    private int minBalance = MINIMUM_BALANCE;

    public NoServiceChargeChecking(String id, String accountId, String ownerName, double balance, int minBalance) {
        super(id, accountId, ownerName, balance);
        this.minBalance = minBalance;
    }

    public NoServiceChargeChecking(String id, String accountId, String ownerName, double balance) {
        super(id, accountId, ownerName, balance);
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
    public void monthlyManage() {

    }

    @Override
    public String toString() {
        return super.toString() +
                ", Minimum balance: " + this.minBalance;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (!(obj instanceof NoServiceChargeChecking))
                return false;
            NoServiceChargeChecking noServiceChargeChecking = (NoServiceChargeChecking) obj;
            if (noServiceChargeChecking.minBalance == this.minBalance)
                return true;
        }
        return false;
    }
}
