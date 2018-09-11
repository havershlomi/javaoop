package com.mmn12.q2;

public abstract class CheckingAccount extends BankAccount{

    public CheckingAccount(String id, String accountId, String ownerName, double balance) {
        super(id, accountId, ownerName, balance);
    }

    public abstract void monthlyManage() throws IllegalBalance;

    public void writeCheck(double amount) throws IllegalBalance {
        this.withdraw(amount);
    }
}
