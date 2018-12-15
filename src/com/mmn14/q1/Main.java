package com.mmn14.q1;
/*
 * Shlomi Haver
 * MMAN 14
 * 204096648
 * */
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Main {


    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Here you will find the answers for Q1: c, e");
        GenericList<String> list = new GenericList<>();

        String val = "";
        for (int i = 0; i < 6; i++) {
            System.out.println("Enter a string");
            val = in.nextLine();
            list.add(val);
            System.out.println(list.toString());
        }

        Stack<String> stack = new Stack<String>();
        GenericList<String> list2 = new GenericList<>();

        boolean hasError = false;
        try {
            while (!hasError) {
                val = list.remove();
                stack.push(val);
            }
        } catch (EmptyListException e) {
            hasError = true;
        }

        while (!stack.empty()) {
            val = stack.pop();
            list2.add(val);
        }

        System.out.println(list2.toString());


        System.out.println("q1 E show max person in list");
        GenericList<Person> pepole = new GenericList<>();
        pepole.add(new Person("Shlomi", "23", 1998));
        pepole.add(new Person("Davif", "24", 2008));
        pepole.add(new Person("yoav", "25", 1994));
        pepole.add(new Person("amir", "26", 1955));

        System.out.println(pepole.toString());

        Person max = max(pepole);
        if (max != null)
            System.out.println("the oldest person is: " + max.toString());
    }


    public static <T extends Comparable<T>> T max(GenericList<T> list) {
        GenericNode<T> curr = list.getHead();
        T max = null;
        while (curr != null) {
            if (max == null) {
                max = curr.getValue();
            } else {
                if (curr.getValue().compareTo(max) > 0)
                    max = curr.getValue();
            }

            curr = curr.getNext();
        }

        return max;
    }
}
