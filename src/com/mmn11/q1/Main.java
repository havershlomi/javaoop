package com.mmn11.q1;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static CashRegister cashRegister;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int action = -1;

        System.out.println("Please enter initial balance");
        int balance = in.nextInt();
        if (balance == 0) {
            cashRegister = new CashRegister();
            System.out.println("Registry created with no cash");
        } else {
            cashRegister = new CashRegister(balance);
            System.out.println("Registry created with: " + balance + "nis");
        }

        do {
            String response = performCashRegistryAction(action, in);
            System.out.println(response);
            System.out.println(
                    "1: get bill" + "\n" +
                            "2: buy item" + "\n" +
                            "3: get balance" + "\n" +
                            "4: get costumer sum" + "\n" +
                            "5: pay" + "\n" +
                            "6: quit");
        } while ((action = in.nextInt()) != 6);

    }

    public static String performCashRegistryAction(int action, Scanner in) {
        String response = "";
        switch (action) {
            case 1: {
                response = cashRegister.printBill();
                break;
            }
            case 2: {
                System.out.println("Please enter item name");
                String name = in.next();
                System.out.println("Please enter item price");
                double price = in.nextDouble();
                System.out.println("Please enter number of items");
                double noi = in.nextDouble();

                Item newItem = new Item(name, price);
                cashRegister.addItem(newItem, noi);
                response =
                        "item added to cart";
                break;
            }
            case 3: {
                response = "registry balance is: " + cashRegister.getRegistryTotalBalance();
                break;
            }
            case 4: {
                response = "customer need to pay: " + cashRegister.getTotalAmount();
                break;
            }
            case 5: {
                System.out.println("Please enter the amount of money you are going to pay");
                double money = in.nextDouble();
                String bill = cashRegister.printBill();
                double change = cashRegister.pay(money);
                response = "your change is: " + change;
                response += "\n" + bill + "\nnext customer please";
                break;
            }
        }
        return response;
    }
}
