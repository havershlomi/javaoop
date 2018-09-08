package com.mmn11.q1;

import java.util.ArrayList;

public class CashRegister {

    private double balance;
    private ArrayList<BillRecord> customerItems = new ArrayList<BillRecord>();
    private double totalAmount = 0;

    public CashRegister(double balance) {
        this.balance = balance;
    }

    public CashRegister() {
        this(0.0);
    }

    public void addItem(Item item, double amount) {
        BillRecord record = new BillRecord(item, amount);
        this.totalAmount += record.getTotalPrice();
        this.customerItems.add(record);
    }

    //print the customer bill
    public String printBill() {
        String bill = "";
        if (this.customerItems.size() > 0) {
            for (BillRecord r : this.customerItems) {
                bill += r.toString() + "\n";
            }
        } else {
            bill = "No items bought yet";
        }

        return bill;
    }

    //get bill sum
    public double getTotalAmount() {
        return this.totalAmount;
    }

    //customer pays
    public double pay(double money) {
        double change = money - this.totalAmount;
        this.customerItems = new ArrayList<BillRecord>();
        this.balance += this.totalAmount;
        return change;
    }

    //get balance in cash registry
    public double getRegistryTotalBalance() {
        return this.balance;
    }
}
