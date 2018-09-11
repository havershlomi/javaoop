package com.mmn12.q2;

public abstract class BankAccount {

    private String id;
    private String accountId;
    private String ownerName;
    private double balance;

    public BankAccount(String id, String accountId, String ownerName, double balance) {
        this.id = id;
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double money) throws IllegalBalance {
        if (money > this.balance)
            throw new IllegalBalance();
        this.balance -= money;
    }

    public void deposit(double money) {
        this.balance += money;
    }

    public abstract void monthlyManage() throws IllegalBalance;

    @Override
    public String toString() {
        return "Name: " + this.ownerName +
                ", Id: " + this.id +
                ", account id: " + this.accountId +
                ", balance: " + this.balance;
    }



    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BankAccount))
            return false;

        BankAccount bObj = (BankAccount) obj;
        if (this.accountId.equals(bObj.accountId) &&
                this.ownerName.equals(bObj.ownerName) &&
                this.id.equals(bObj.id) &&
                this.balance == bObj.balance)
            return true;
        return false;
    }
}
