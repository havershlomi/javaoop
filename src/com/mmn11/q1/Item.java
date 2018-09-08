package com.mmn11.q1;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class Item {
    private String name;
    private double price;

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
