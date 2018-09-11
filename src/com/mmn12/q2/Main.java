package com.mmn12.q2;

import com.mmn12.q1.Window;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) throws IllegalBalance {
        loadAccounts();
        printAccounts(accounts);

        System.out.println("\ncheck if 2 accounts are the same");
        BankAccount acc = new HighInterestSavings("11117", "7", "Yoav", 880, 50.0);
        isEqual(acc, accounts);

        double deposit = Math.random() * 1000;
        System.out.println("\nDeposit " + deposit + "nis into accounts");
        depositAll(accounts, deposit);
        printAccounts(accounts);

        double withdraw = Math.random() * 100000;
        System.out.println("\nWithdraw " + withdraw + "nis from accounts");
        withdrawAll(accounts, withdraw);
        printAccounts(accounts);

        System.out.println("\nmonthly manage for all accounts");
        monthlyManageAll(accounts);
        printAccounts(accounts);

        System.out.println("\ncheck if 2 accounts are the same");
        isEqual(acc, accounts);
    }

    //create all the accounts
    private static void loadAccounts() {
        accounts.add(new ServiceChargeChecking("11111", "1", "Shlomi", 500));
        accounts.add(new NoServiceChargeChecking("11112", "2", "David", 3300));
        accounts.add(new InterestChecking("11113", "3", "Dana", 34300));
        accounts.add(new SavingsAccount("11114", "4", "Arial", 343));
        accounts.add(new HighInterestSavings("11115", "5", "Yona", 8888));
        accounts.add(new HighInterestSavings("11116", "6", "ron", 88, 50));
        accounts.add(new HighInterestSavings("11117", "7", "Yoav", 880, 50.0));
    }

    //prints all the accounts
    private static void printAccounts(ArrayList<BankAccount> accounts) {
        for (BankAccount acc : accounts) {
            System.out.println(acc.toString() + ", account type: " + acc.getClass().getSimpleName());
        }
    }

    //deiposit money to all the accounts
    private static void depositAll(ArrayList<BankAccount> accounts, double amount) {
        for (BankAccount acc : accounts) {
            acc.deposit(amount);
        }
    }
    //withdraw money from all the accounts
    private static void withdrawAll(ArrayList<BankAccount> accounts, double amount)  {
        for (BankAccount acc : accounts) {
            try {
                acc.withdraw(amount);
            } catch (IllegalBalance e) {
                System.out.println(acc.getOwnerName() + " has " + acc.getBalance() + " not enough to withdraw  " + amount);
            }
        }
    }

    //monthly Manage all the accounts
    private static void monthlyManageAll(ArrayList<BankAccount> accounts) {
        for (BankAccount acc : accounts) {
            try {
                acc.monthlyManage();
            } catch (IllegalBalance e) {
                System.out.println(acc.getOwnerName() + " has " + acc.getBalance() + " not enough for service charge");
            }
        }
    }

    //check equality of an account from the list
    private static void isEqual(BankAccount account, ArrayList<BankAccount> accounts) {
        for (BankAccount acc : accounts) {
            if (account.equals(acc)) {
                System.out.println("account is equal");
            } else {
                System.out.println(acc.getOwnerName() + " is different from " + account.getOwnerName());
            }
        }
    }
}
