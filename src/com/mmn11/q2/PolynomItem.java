package com.mmn11.q2;

public class PolynomItem implements Comparable<PolynomItem> {
    private int exponent;
    private double coefficients;

    public int getExponent() {
        return exponent;
    }

    public double getCoefficients() {
        return coefficients;
    }

    public PolynomItem(int exponent, double coefficients) {
        this.exponent = exponent;
        this.coefficients = coefficients;
    }

    @Override
    public int compareTo(PolynomItem o) {
        return this.exponent - o.exponent;
    }
}
