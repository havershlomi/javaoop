package com.mmn11.q1;

public class BillRecord {
    private Item item;
    private double numOfUnits;
    private double totalPrice;

    public BillRecord(Item item, double noUnit){
        this.item = item;
        this.numOfUnits = noUnit;
        this.totalPrice = this.numOfUnits * this.item.getPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String toString(){
        return "Item: " +this.item.getName()
                + " Amount: "+ this.numOfUnits
                + " Total Price: "+ this.totalPrice;
    }
}
