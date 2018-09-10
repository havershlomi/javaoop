package com.mmn12.q1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Window win = new Window();
        System.out.println("Please enter x and y (x,y)");
        String line = "";
        while (true) {
            line = in.nextLine();
            if (line.equals(""))
                break;
            String res = win.addDot(line);
            if (res != null)
                System.out.println(res);
        }
    }
}
