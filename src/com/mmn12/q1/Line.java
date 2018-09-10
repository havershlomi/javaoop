package com.mmn12.q1;

//Defines a line in the system
public class Line {
    private Dot d1;
    private Dot d2;
    private double distance;

    public Line(Dot d1, Dot d2) {
        this.d1 = d1;
        this.d2 = d2;
        this.distance = calculateDistence(d1, d2);
    }

    public Dot getD1() {
        return d1;
    }

    public Dot getD2() {
        return d2;
    }

    public double getDistance() {
        return distance;
    }

    //calculate distance using pitagoras
    private double calculateDistence(Dot d1, Dot d2) {
        int diffX = Math.abs(d2.getX() - d1.getX());
        int diffY = Math.abs(d2.getY() - d1.getY());
        double distance = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
        return distance;
    }
}
