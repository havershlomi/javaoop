package com.mmn11.q2;
/*
 * Shlomi Haver
 * ID: 204096648
 * */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Polynom p1;
    private static Polynom p2;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Please insert the first polynom (coefficients,exponent), write q to finish");
        p1 = getPolynom();
        System.out.println(p1.toString());
        System.out.println("Please insert the second polynom (coefficients,exponent), write q to finish");
        p2 = getPolynom();
        System.out.println(p2.toString());

        int action = 0;
        do {
            String response = performAction(action, in);
            System.out.println(response);
            System.out.println(
                    "1: plus" + "\n" +
                            "2: minus" + "\n" +
                            "3: derivative" + "\n" +
                            "4: print" + "\n" +
                            "5: compare" + "\n" +
                            "6: quit");

        } while ((action = in.nextInt()) != 6);

    }

    //geneate polynom from user input
    public static Polynom getPolynom() {
        Scanner in = new Scanner(System.in);
        ArrayList<Double> coefficients = new ArrayList<Double>();
        ArrayList<Integer> exponenets = new ArrayList<Integer>();
        while (true) {
            String val = in.next();
            if (val.contains("q"))
                break;

            double coefficient = Double.parseDouble(val);
            coefficients.add(coefficient);
            int exponenet = in.nextInt();
            exponenets.add(exponenet);
        }

        return new Polynom(coefficients, exponenets);
    }

    public static String performAction(int action, Scanner in) {
        String response = "";
        switch (action) {
            case 1: {
                response = "[" + p1.toString() + "] + [" + p2.toString() + "]=" + p1.plus(p2).toString();
                break;
            }
            case 2: {
                System.out.println("Press 1 to substract p1 from p2 and 2 to substract p2 from p1");
                int subAction = in.nextInt();
                if (subAction == 1) {
                    response = "[" + p2.toString() + "] - [" + p1.toString() + "]=" + p2.minus(p1).toString();
                } else if (subAction == 2) {
                    response = "[" + p1.toString() + "] - [" + p2.toString() + "]=" + p1.minus(p2).toString();
                }
                break;
            }
            case 3: {
                System.out.println("Press 1 to get derivative of p1 and 2 to get derivative of  p2");
                int subAction = in.nextInt();
                if (subAction == 1) {
                    response = p1.derivative().toString();
                } else if (subAction == 2) {
                    response = p2.derivative().toString();
                }
                break;
            }
            case 4: {
                System.out.println("Press 1 to print p1 and 2 to print p2");
                int subAction = in.nextInt();
                if (subAction == 1) {
                    response = p1.toString();
                } else if (subAction == 2) {
                    response = p2.toString();
                }
                break;
            }
            case 5: {
                int result = p1.compareTo(p2);
                if (result == 0) {
                    System.out.println("p1 = p2");
                } else if (result > 0) {
                    System.out.println("p1 > p2");
                } else {
                    System.out.println("p1 < p2");
                }
            }
        }
        return response;
    }
}
